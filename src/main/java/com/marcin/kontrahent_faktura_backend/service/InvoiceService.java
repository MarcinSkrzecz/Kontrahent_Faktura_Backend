package com.marcin.kontrahent_faktura_backend.service;

import com.marcin.kontrahent_faktura_backend.domain.invoice.Invoice;
import com.marcin.kontrahent_faktura_backend.domain.invoice.InvoiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceDao repository;

    public Boolean checkIfInvoiceExists(Long id) {
        return repository.existsById(id);
    }

    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    public List<Invoice> getAllInvoicesByContractorTaxId(Long taxId) {
        return repository.findAllByContractor_TaxId(taxId);
    }

    public Invoice getInvoiceById(Long id) {
        if (checkIfInvoiceExists(id)) {
            return repository.getById(id);
        } else throw new IllegalArgumentException("Invoice not found");
    }

    public Invoice createInvoice(Invoice invoice) {
        if (!checkIfInvoiceExists(invoice.getInvoiceId())) {
            return repository.save(invoice);
        } else throw new IllegalArgumentException("Invoice with this 'Id' already exists");
    }

    public Invoice updateInvoice(Invoice invoice) {
        if (checkIfInvoiceExists(invoice.getInvoiceId())) {
            return repository.save(invoice);
        } else throw new IllegalArgumentException("Invoice not found");
    }

    public void deleteInvoice(Long id) {
        repository.deleteById(id);
    }

}

