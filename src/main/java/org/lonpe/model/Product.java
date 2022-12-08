
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "product_in_pkey_idx")})
public class Product implements IDcLon,Serializable{

    public Product(){
    }

    @Id
    @GeneratedValue(generator = "seq_product")
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


    
        @NotBlank    
    private String description;

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }        
    

        @NotBlank    
    private String fastKey;

    public String getFastKey(){
        return this.fastKey;
    }

    public void setFastKey(String fastKey){
        this.fastKey = fastKey;
    }        
    

        @NotBlank
    @NotNull    
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

        @NotBlank    
    private String sku;

    public String getSku(){
        return this.sku;
    }

    public void setSku(String sku){
        this.sku = sku;
    }        
    

    
    @NotNull
    @ManyToOne    
    private ProductType productType;

    public ProductType getProductType(){
        return this.productType;
    }
    
    public void setProductType(ProductType productType){
        this.productType = productType;
    }
 

    
    @OneToMany
    @JoinColumn(name = "product_id")    
    private Set<InvoiceLineIn> invoiceLineIns;

    public Set<InvoiceLineIn> getInvoiceLineIns(){
        return this.invoiceLineIns;
    }
    
    public void setInvoiceLineIns(Set<InvoiceLineIn> invoiceLineIns){
        this.invoiceLineIns = invoiceLineIns;
    }
 


    @OneToMany
    @JoinColumn(name = "product_id")    
    private Set<InvoiceLineOut> invoiceLineOuts;

    public Set<InvoiceLineOut> getInvoiceLineOuts(){
        return this.invoiceLineOuts;
    }
    
    public void setInvoiceLineOuts(Set<InvoiceLineOut> invoiceLineOuts){
        this.invoiceLineOuts = invoiceLineOuts;
    }
 
      
}
        