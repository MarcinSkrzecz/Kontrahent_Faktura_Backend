package com.marcin.kontrahent_faktura_backend.domain.contractor;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ContractorDto {

    private Long taxId;
    private String contractorName;

    public ContractorDto(Long taxId, String contractorName) {
        this.taxId = taxId;
        this.contractorName = contractorName;
    }
}
