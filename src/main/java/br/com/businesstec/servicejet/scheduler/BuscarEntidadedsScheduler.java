package br.com.businesstec.servicejet.scheduler;

import br.com.businesstec.servicejet.datasources.jet.http.JetMarca;
import br.com.businesstec.servicejet.parser.MarcaMapper;
import br.com.businesstec.servicejet.repository.MarcaRepository;
import br.com.businesstec.servicejet.service.MarcaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Objects;

@Configuration
@EnableScheduling
public class BuscarEntidadedsScheduler {

    private static final Logger logger = LoggerFactory.getLogger(BuscarEntidadedsScheduler.class);

    private final MarcaService marcaService;
    private final MarcaMapper marcaMapper;
    private final JetMarca jetMarca;

    public BuscarEntidadedsScheduler(MarcaService marcaService, JetMarca jetMarca) {
        this.marcaService = marcaService;
        this.jetMarca = jetMarca;
        marcaMapper = MarcaMapper.INSTANCE;
    }

    @Scheduled(fixedRate = 10000)
    public void integrarMarcas() {
        var marcas = marcaService.recuperarMarcasNaoIntegradas();

        logger.info("=============================================================================");
        logger.info("MARCAS ENCONTRADAS: " + marcas.size());
        logger.info("=============================================================================");

        marcas.stream().forEach(marca -> {
            try {
                var idMarcaJet = jetMarca.integrarMarca(marcaMapper.map(marca));
                if (Objects.isNull(idMarcaJet)) {
                    throw new RuntimeException("Não foi possível integrar marca, id: " + marca.getIdMarca());
                }
                marca.setIdMarcaJet(Long.parseLong(idMarcaJet));
                var marcaSalva = marcaService.salvarMarca(marca);
                logger.info("=============================================================================");
                logger.info("MARCA INTEGRADA COM SUCESSO, ID JET:  " + marcaSalva.getIdMarcaJet());
                logger.info("=============================================================================");
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
