package br.com.businesstec.servicejet.service.impl;

import br.com.businesstec.servicejet.http.JetMarca;
import br.com.businesstec.servicejet.mapper.MarcaMapper;
import br.com.businesstec.servicejet.model.ControleExecucaoFluxoEntidade;
import br.com.businesstec.servicejet.model.Marca;
import br.com.businesstec.servicejet.repository.MarcaRepository;
import br.com.businesstec.servicejet.service.ControleExecucaoFluxoEntidadeEntregaService;
import br.com.businesstec.servicejet.service.MarcaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MarcaServiceImpl implements MarcaService {

    private static final Logger logger = LoggerFactory.getLogger(MarcaServiceImpl.class);

    private final MarcaRepository marcaRepository;
    private final ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService;
    private final MarcaMapper marcaMapper;
    private final JetMarca jetMarca;

    public MarcaServiceImpl(MarcaRepository marcaRepository, ControleExecucaoFluxoEntidadeEntregaService controleExecucaoFluxoEntidadeEntregaService, JetMarca jetMarca) {
        this.marcaRepository = marcaRepository;
        this.controleExecucaoFluxoEntidadeEntregaService = controleExecucaoFluxoEntidadeEntregaService;
        this.jetMarca = jetMarca;
        marcaMapper = MarcaMapper.INSTANCE;
    }

    @Override
    public List<Marca> recuperarMarcasNaoIntegradas() {
        return marcaRepository.findByIdMarcaJetIsNull();
    }

    @Override
    public List<Marca> recuperarTodasMarcas() {
        return marcaRepository.findByIdMarcaJetIsNotNull();
    }

    @Override
    public Marca salvarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public void integrarMarcas(List<Marca> marcas, ControleExecucaoFluxoEntidade controleExecucaoFluxoEntidade) {
        marcas.stream().forEach(marca -> {
            try {
                var idMarcaJet = jetMarca.integrarMarca(marcaMapper.map(marca));
                if (Objects.isNull(idMarcaJet)) {
                    throw new RuntimeException("Não foi possível integrar marca, id: " + marca.getIdMarca());
                }
                marca.setIdMarcaJet(Long.parseLong(idMarcaJet));
                var marcaSalva = salvarMarca(marca);
                logger.info("=============================================================================");
                logger.info("MARCA INTEGRADA COM SUCESSO, ID JET:  " + marcaSalva.getIdMarcaJet());
                logger.info("=============================================================================");
                Thread.sleep(250);
                controleExecucaoFluxoEntidadeEntregaService.registrarExecucao(controleExecucaoFluxoEntidade);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
