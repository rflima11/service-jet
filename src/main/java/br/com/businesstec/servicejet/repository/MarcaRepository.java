package br.com.businesstec.servicejet.repository;

import br.com.businesstec.servicejet.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

    List<Marca> findByIdMarcaJetIsNull();

    List<Marca> findByIdMarcaJetIsNotNull();



}
