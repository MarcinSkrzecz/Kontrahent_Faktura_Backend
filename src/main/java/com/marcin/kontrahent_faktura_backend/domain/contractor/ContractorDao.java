package com.marcin.kontrahent_faktura_backend.domain.contractor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface ContractorDao extends JpaRepository<Contractor, Long> {

    List<Contractor> findAll();

    Optional<Contractor> findById(Long id);

    boolean existsById(Long id);

    Contractor save(Contractor contractor);

    void deleteById(Long id);

}
