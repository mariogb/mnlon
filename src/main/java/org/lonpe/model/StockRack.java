
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "stock_rack_in_pkey_idx")})
public class StockRack implements IDcLon,Serializable{

    public StockRack(){
    }

    @Id
    @GeneratedValue(generator = "seq_stock_rack")
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
    

    
    @NotNull
    @ManyToOne    
    private WorkSpace workSpace;

    public WorkSpace getWorkSpace(){
        return this.workSpace;
    }
    
    public void setWorkSpace(WorkSpace workSpace){
        this.workSpace = workSpace;
    }
 

    
    @OneToMany
    @JoinColumn(name = "stock_rack_id")    
    private Set<StockRackProduct> stockRackProducts;

    public Set<StockRackProduct> getStockRackProducts(){
        return this.stockRackProducts;
    }
    
    public void setStockRackProducts(Set<StockRackProduct> stockRackProducts){
        this.stockRackProducts = stockRackProducts;
    }
 
      
}
        