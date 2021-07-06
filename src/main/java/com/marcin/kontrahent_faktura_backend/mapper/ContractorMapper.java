package com.marcin.kontrahent_faktura_backend.mapper;

import com.marcin.kontrahent_faktura_backend.domain.contractor.Contractor;
import com.marcin.kontrahent_faktura_backend.domain.contractor.ContractorDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContractorMapper {

    public Contractor mapToContractor(final ContractorDto contractorDto) {
        return new Contractor(
                contractorDto.getTaxId(),
                contractorDto.getContractorName()
        );
    }

    public ContractorDto mapToContractorDto(final Contractor contractor) {
        return new ContractorDto(
                contractor.getTaxId(),
                contractor.getContractorName()
        );
    }

    public List<ContractorDto> mapToContractorDto(final List<Contractor> contractorList) {
        return contractorList.stream()
                .map(this::mapToContractorDto)
                .collect(Collectors.toList());
    }

}
