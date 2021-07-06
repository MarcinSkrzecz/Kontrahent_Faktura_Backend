package com.marcin.kontrahent_faktura_backend.domain.invoice;

import com.marcin.kontrahent_faktura_backend.domain.contractor.Contractor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Invoices")
public class Invoice {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    @Column(name = "INVOICE_ID")
    private Long invoiceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taxId", nullable=false)
    private Contractor contractor;

    @Column(name = "INVOICE_AMOUNT")
    private Double invoiceAmount;

    public Invoice(Contractor contractor, Double invoiceAmount) {
        this.contractor = contractor;
        this.invoiceAmount = invoiceAmount;
    }

    public Invoice(Long invoiceId, Contractor contractor, Double invoiceAmount) {
        this.invoiceId = invoiceId;
        this.contractor = contractor;
        this.invoiceAmount = invoiceAmount;
    }
}
