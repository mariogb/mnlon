package org.lonpe.model;

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

@Introspected
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pkey"}, name = "product_type_in_pkey_idx")})
public class ProductType implements IDcLon, Serializable {

    public ProductType() {
    }

    @Id
    @GeneratedValue(generator = "seq_product_type")
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
    private Boolean afectStock;

    public Boolean getAfectStock() {
        return this.afectStock;
    }

    public void setAfectStock(Boolean afectStock) {
        this.afectStock = afectStock;
    }

    @NotBlank
    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotBlank
    private String fastKey;

    public String getFastKey() {
        return this.fastKey;
    }

    public void setFastKey(String fastKey) {
        this.fastKey = fastKey;
    }

    @NotNull
    private Boolean isService;

    public Boolean getIsService() {
        return this.isService;
    }

    public void setIsService(Boolean isService) {
        this.isService = isService;
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

    @NotNull
    private Boolean taxable;

    public Boolean getTaxable() {
        return this.taxable;
    }

    public void setTaxable(Boolean taxable) {
        this.taxable = taxable;
    }

    @NotNull
    private Boolean withSerialNumber;

    public Boolean getWithSerialNumber() {
        return this.withSerialNumber;
    }

    public void setWithSerialNumber(Boolean withSerialNumber) {
        this.withSerialNumber = withSerialNumber;
    }

    @OneToMany
    @JoinColumn(name = "product_type_id")
    private Set<Product> products;

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
