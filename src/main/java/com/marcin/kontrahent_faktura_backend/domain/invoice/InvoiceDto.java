package com.marcin.kontrahent_faktura_backend.domain.invoice;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InvoiceDto {

    private Long invoiceId;
    private Long taxId;
    private Double invoiceAmount;

    public InvoiceDto(Long invoiceId, Long taxId, Double invoiceAmount) {
        this.invoiceId = invoiceId;
        this.taxId = taxId;
        this.invoiceAmount = invoiceAmount;
    }
}
