package com.marcin.kontrahent_faktura_backend.domain.invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Long> {

    List<Invoice> findAll();

    List<Invoice> findAllByContractor_TaxId(Long taxId);

    Optional<Invoice> findById(Long id);

    boolean existsById(Long id);

    Invoice save(Invoice invoice);

    void deleteById(Long id);

}
