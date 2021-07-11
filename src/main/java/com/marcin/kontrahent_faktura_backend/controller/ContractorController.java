package com.marcin.kontrahent_faktura_backend.controller;

import com.marcin.kontrahent_faktura_backend.domain.contractor.ContractorDto;
import com.marcin.kontrahent_faktura_backend.mapper.ContractorMapper;
import com.marcin.kontrahent_faktura_backend.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class ContractorController {

    @Autowired
    private ContractorMapper mapper;
    @Autowired
    private ContractorService services;

    @RequestMapping(method = RequestMethod.GET, value = "/contractor")
    public List<ContractorDto> getAllContractors() {
        return mapper.mapToContractorDto(services.getAllContractors());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/contractor/{taxId}")
    public ContractorDto getContractorByTaxId(@RequestParam Long taxId) {
        if (services.checkIfContractorExists(taxId)) {
            return mapper.mapToContractorDto(services.getContractorByTaxId(taxId));
        } else throw new IllegalArgumentException("Contractor not found");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/contractor", consumes = APPLICATION_JSON_VALUE)
    public ContractorDto createContractor(@RequestBody @Valid ContractorDto contractorDto) {
        if (!services.checkIfContractorExists(contractorDto.getTaxId())) {
            return mapper.mapToContractorDto(services.createContractor(mapper.mapToContractor(contractorDto)));
        } else throw new IllegalArgumentException("Contractor with this 'Tax Id' already exists");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/contractor", consumes = APPLICATION_JSON_VALUE)
    public ContractorDto updateContractor(@RequestBody @Valid ContractorDto contractorDto) {
        if (services.checkIfContractorExists(contractorDto.getTaxId())) {
            return mapper.mapToContractorDto(services.updateContractor(mapper.mapToContractor(contractorDto)));
        } else throw new IllegalArgumentException("Contractor not found");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/contractor/{taxId}")
    public void deleteContractor(@RequestParam Long taxId) {
        if (services.checkIfContractorExists(taxId)) {
            services.deleteContractor(taxId);
        } else throw new IllegalArgumentException("Contractor not found");
    }

}
