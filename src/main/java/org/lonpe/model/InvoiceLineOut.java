
package org.lonpe.model;            

            
import java.time.LocalDateTime;
import java.math.BigDecimal;
         

import io.micronaut.core.annotation.Introspected;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;  
import javax.persistence.ManyToOne;


@Introspected
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "invoice_line_out_in_pkey_idx")})
public class InvoiceLineOut implements IDcLon,Serializable{

    public InvoiceLineOut(){
    }

    @Id
    @GeneratedValue(generator = "seq_invoice_line_out")
    private Long id;
      
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }

    @NotBlank 
    @NotNull
    private String pkey;

    public String getPkey(){
        return this.pkey;
    }

    public void setPkey(String pkey){
        this.pkey = pkey;
    }


    
        
    private LocalDateTime askDate;

    public LocalDateTime getAskDate(){
        return this.askDate;
    }

    public void setAskDate(LocalDateTime askDate){
        this.askDate = askDate;
    }        
    

        @NotNull    
    private BigDecimal askQuantity;

    public BigDecimal getAskQuantity(){
        return this.askQuantity;
    }

    public void setAskQuantity(BigDecimal askQuantity){
        this.askQuantity = askQuantity;
    }        
    

        @NotNull    
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate(){
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate){
        this.createdDate = createdDate;
    }        
    

        @NotNull    
    private BigDecimal descountPorcent;

    public BigDecimal getDescountPorcent(){
        return this.descountPorcent;
    }

    public void setDescountPorcent(BigDecimal descountPorcent){
        this.descountPorcent = descountPorcent;
    }        
    

        @NotNull    
    private Integer orden;

    public Integer getOrden(){
        return this.orden;
    }

    public void setOrden(Integer orden){
        this.orden = orden;
    }        
    

        @NotNull    
    private BigDecimal price;

    public BigDecimal getPrice(){
        return this.price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }        
    

        @NotBlank
    @NotNull    
    private String status;

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }        
    

        
    private LocalDateTime supplyDate;

    public LocalDateTime getSupplyDate(){
        return this.supplyDate;
    }

    public void setSupplyDate(LocalDateTime supplyDate){
        this.supplyDate = supplyDate;
    }        
    

        @NotNull    
    private BigDecimal supplyQuantity;

    public BigDecimal getSupplyQuantity(){
        return this.supplyQuantity;
    }

    public void setSupplyQuantity(BigDecimal supplyQuantity){
        this.supplyQuantity = supplyQuantity;
    }        
    

        @NotNull    
    private BigDecimal taxPorcent;

    public BigDecimal getTaxPorcent(){
        return this.taxPorcent;
    }

    public void setTaxPorcent(BigDecimal taxPorcent){
        this.taxPorcent = taxPorcent;
    }        
    

    
    @NotNull
    @ManyToOne    
    private Sale sale;

    public Sale getSale(){
        return this.sale;
    }
    
    public void setSale(Sale sale){
        this.sale = sale;
    }
 

    @NotNull
    @ManyToOne    
    private Product product;

    public Product getProduct(){
        return this.product;
    }
    
    public void setProduct(Product product){
        this.product = product;
    }
 

    @NotNull
    @ManyToOne    
    private StockRackProduct stockRackProduct;

    public StockRackProduct getStockRackProduct(){
        return this.stockRackProduct;
    }
    
    public void setStockRackProduct(StockRackProduct stockRackProduct){
        this.stockRackProduct = stockRackProduct;
    }
 

    
      
}
        