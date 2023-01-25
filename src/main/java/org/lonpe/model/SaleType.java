
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "sale_type_in_pkey_idx")})
public class SaleType implements IDcLon,Serializable{

    public SaleType(){
    }

    @Id
    @GeneratedValue(generator = "seq_sale_type")
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
    @NotNull    
    private String afectStock;

    public String getAfectStock(){
        return this.afectStock;
    }

    public void setAfectStock(String afectStock){
        this.afectStock = afectStock;
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
    

    

    
    @OneToMany
    @JoinColumn(name = "sale_type_id")    
    private Set<Sale> sales;

    public Set<Sale> getSales(){
        return this.sales;
    }
    
    public void setSales(Set<Sale> sales){
        this.sales = sales;
    }
 
      
}
        