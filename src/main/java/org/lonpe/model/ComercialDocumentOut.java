package org.lonpe.model;

import java.time.LocalDateTime;
import java.util.Set;

import io.micronaut.core.annotation.Introspected;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Introspected
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pkey"}, name = "comercial_document_out_in_pkey_idx")})
public class ComercialDocumentOut implements IDcLon, Serializable {

    public ComercialDocumentOut() {
    }

    @Id
    @GeneratedValue(generator = "seq_comercial_document_out")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @NotNull
    private String pkey;

    public String getPkey() {
        return this.pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    @NotNull
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    private LocalDateTime documentDate;

    public LocalDateTime getDocumentDate() {
        return this.documentDate;
    }

    public void setDocumentDate(LocalDateTime documentDate) {
        this.documentDate = documentDate;
    }

    @NotBlank
    @NotNull
    private String folio;

    public String getFolio() {
        return this.folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    @NotBlank
    @NotNull
    private String pname;

    public String getPname() {
        return this.pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @NotBlank
    @NotNull
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private LocalDateTime supplyDate;

    public LocalDateTime getSupplyDate() {
        return this.supplyDate;
    }

    public void setSupplyDate(LocalDateTime supplyDate) {
        this.supplyDate = supplyDate;
    }

    @NotNull
    @ManyToOne
    private ContractOut contract;

    public ContractOut getContract() {
        return this.contract;
    }

    public void setContract(ContractOut contract) {
        this.contract = contract;
    }

    @NotNull
    @ManyToOne
    private UserLon userAutor;

    public UserLon getUserAutor() {
        return this.userAutor;
    }

    public void setUserAutor(UserLon userAutor) {
        this.userAutor = userAutor;
    }

    @NotNull
    @ManyToOne
    private ComercialDocumentTypeOut comercialDocumentType;

    public ComercialDocumentTypeOut getComercialDocumentType() {
        return this.comercialDocumentType;
    }

    public void setComercialDocumentType(ComercialDocumentTypeOut comercialDocumentType) {
        this.comercialDocumentType = comercialDocumentType;
    }

    @OneToMany
    @JoinColumn(name = "comercial_document_id")
    private Set<InvoiceLineIn> invoiceLines;

    public Set<InvoiceLineIn> getInvoiceLines() {
        return this.invoiceLines;
    }

    public void setInvoiceLines(Set<InvoiceLineIn> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    @OneToMany
    @JoinColumn(name = "comercial_document_out_id")
    private Set<PaymentOut> payments;

    public Set<PaymentOut> getPayments() {
        return this.payments;
    }

    public void setPayments(Set<PaymentOut> payments) {
        this.payments = payments;
    }

}
