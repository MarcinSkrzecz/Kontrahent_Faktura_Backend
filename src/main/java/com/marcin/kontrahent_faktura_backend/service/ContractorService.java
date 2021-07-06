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
        if (checkIfContractorExists(taxId)) {
            return repository.findById(taxId).get();
        } else throw new IllegalArgumentException("Contractor not found");
    }

    public Contractor createContractor(Contractor contractor) {
        if (!checkIfContractorExists(contractor.getTaxId())) {
            return repository.save(contractor);
        } else throw new IllegalArgumentException("Contractor with this 'Tax Id' already exists");
    }

    public Contractor updateContractor(Contractor contractor) {
        if (checkIfContractorExists(contractor.getTaxId())) {
            return repository.save(contractor);
        } else throw new IllegalArgumentException("Contractor not found");
    }

    public void deleteContractor(Long taxId) {
        repository.deleteById(taxId);
    }

}

