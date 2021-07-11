package com.marcin.kontrahent_faktura_backend.controller;

import com.marcin.kontrahent_faktura_backend.domain.invoice.InvoiceDto;
import com.marcin.kontrahent_faktura_backend.mapper.InvoiceMapper;
import com.marcin.kontrahent_faktura_backend.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private InvoiceMapper mapper;
    @Autowired
    private InvoiceService services;

    @RequestMapping(method = RequestMethod.GET, value = "/invoice")
    public List<InvoiceDto> getAllInvoices() {
        return mapper.mapToInvoiceDto(services.getAllInvoices());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoiceByTaxId/{taxId}")
    public List<InvoiceDto> getAllInvoicesByTaxId(Long taxId) {
        return mapper.mapToInvoiceDto(services.getAllInvoicesByContractorTaxId(taxId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/invoice/{id}")
    public InvoiceDto getInvoiceById(@RequestParam Long id) {
        if (services.checkIfInvoiceExists(id)) {
            return mapper.mapToInvoiceDto(services.getInvoiceById(id));
        } else throw new IllegalArgumentException("Invoice not found");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/invoice", consumes = APPLICATION_JSON_VALUE)
    public InvoiceDto createInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        if (!services.checkIfInvoiceExists(invoiceDto.getInvoiceId())) {
            return mapper.mapToInvoiceDto(services.createInvoice(mapper.mapToInvoice(invoiceDto)));
        } else throw new IllegalArgumentException("Invoice with this 'Id' already exists");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/invoice", consumes = APPLICATION_JSON_VALUE)
    public InvoiceDto updateInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        if (services.checkIfInvoiceExists(invoiceDto.getInvoiceId())) {
            return mapper.mapToInvoiceDto(services.updateInvoice(mapper.mapToInvoice(invoiceDto)));
        } else throw new IllegalArgumentException("Invoice not found");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/invoice/{id}")
    public void deleteInvoice(@RequestParam Long id) {
        if (services.checkIfInvoiceExists(id)) {
            services.deleteInvoice(id);
        } else throw new IllegalArgumentException("Invoice not found");
    }

}
