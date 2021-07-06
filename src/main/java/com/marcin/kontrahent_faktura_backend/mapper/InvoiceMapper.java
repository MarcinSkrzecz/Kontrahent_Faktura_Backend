package com.marcin.kontrahent_faktura_backend.mapper;

import com.marcin.kontrahent_faktura_backend.domain.invoice.Invoice;
import com.marcin.kontrahent_faktura_backend.domain.invoice.InvoiceDto;
import com.marcin.kontrahent_faktura_backend.service.ContractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvoiceMapper {

    @Autowired
    private ContractorService service;

    public Invoice mapToInvoice(final InvoiceDto invoiceDto) {
        if (invoiceDto.getTaxId() == null) {
            return new Invoice(
                    service.getContractorByTaxId(invoiceDto.getTaxId()),
                    invoiceDto.getInvoiceAmount()
            );
        } else {
            return new Invoice(
                    invoiceDto.getInvoiceId(),
                    service.getContractorByTaxId(invoiceDto.getTaxId()),
                    invoiceDto.getInvoiceAmount()
            );
        }
    }

    public InvoiceDto mapToInvoiceDto(final Invoice invoice) {
        return new InvoiceDto(
                invoice.getInvoiceId(),
                invoice.getContractor().getTaxId(),
                invoice.getInvoiceAmount()
        );
    }

    public List<InvoiceDto> mapToInvoiceDto(final List<Invoice> invoiceList) {
        return invoiceList.stream()
                .map(this::mapToInvoiceDto)
                .collect(Collectors.toList());
    }

}

