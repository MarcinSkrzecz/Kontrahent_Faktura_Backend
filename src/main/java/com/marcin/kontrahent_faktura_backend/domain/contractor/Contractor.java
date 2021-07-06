package com.marcin.kontrahent_faktura_backend.domain.contractor;

import com.marcin.kontrahent_faktura_backend.domain.invoice.Invoice;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Contractors")
public class Contractor {

    @Id
    @EqualsAndHashCode.Include
    @Positive(message = "Tax Id must be positive number")
    @Digits(integer = 10, fraction = 0, message = "Tax Id must have 10 digits and not start with 0")
    @Column(name = "TAX_ID")
    private Long taxId;

    @NotBlank(message = "Contractor name can't be empty")
    @Size(min = 1, max = 100, message = "Contractor name must be between 1 and 100 characters")
    @Column(name = "CONTRACTOR_NAME")
    private String contractorName;

    @OneToMany(mappedBy = "contractor")
    private Set<Invoice> invoices;

    public Contractor(Long taxId, String contractorName) {
        this.taxId = taxId;
        this.contractorName = contractorName;
    }
}
