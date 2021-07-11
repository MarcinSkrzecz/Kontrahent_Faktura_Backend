package com.marcin.kontrahent_faktura_backend.service;

import com.marcin.kontrahent_faktura_backend.domain.contractor.Contractor;
import com.marcin.kontrahent_faktura_backend.domain.contractor.ContractorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractorService {

    @Autowired
    private ContractorDao repository;

    public Boolean checkIfContractorExists(Long taxId) {
        return repository.existsById(taxId);
    }

    public List<Contractor> getAllContractors() {
        return repository.findAll();
    }

    public Contractor getContractorByTaxId(Long taxId) {
        return repository.findById(taxId).get();
    }

    public Contractor createContractor(Contractor contractor) {
        return repository.save(contractor);
    }

    public Contractor updateContractor(Contractor contractor) {
        return repository.save(contractor);
    }

    public void deleteContractor(Long taxId) {
        repository.deleteById(taxId);
    }

}

