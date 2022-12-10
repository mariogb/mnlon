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
import javax.persistence.ManyToOne;

@Introspected
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pkey"}, name = "stock_rack_product_in_pkey_idx")})
public class StockRackProduct implements IDcLon, Serializable {

    public StockRackProduct() {
    }

    @Id
    @GeneratedValue(generator = "seq_stock_rack_product")
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
    private Long quantity;

    public Long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @NotBlank
    private String serialNumber;

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @NotNull
    @ManyToOne
    private StockRack stockRack;

    public StockRack getStockRack() {
        return this.stockRack;
    }

    public void setStockRack(StockRack stockRack) {
        this.stockRack = stockRack;
    }

    @NotNull
    @ManyToOne
    private Product product;

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @OneToMany
    @JoinColumn(name = "stock_rack_product_id")
    private Set<InvoiceLineIn> InvoiceLineIns;

    public Set<InvoiceLineIn> getInvoiceLineIns() {
        return this.InvoiceLineIns;
    }

    public void setInvoiceLineIns(Set<InvoiceLineIn> InvoiceLineIns) {
        this.InvoiceLineIns = InvoiceLineIns;
    }

    @OneToMany
    @JoinColumn(name = "stock_rack_product_id")
    private Set<InvoiceLineOut> InvoiceLineOuts;

    public Set<InvoiceLineOut> getInvoiceLineOuts() {
        return this.InvoiceLineOuts;
    }

    public void setInvoiceLineOuts(Set<InvoiceLineOut> InvoiceLineOuts) {
        this.InvoiceLineOuts = InvoiceLineOuts;
    }

}
