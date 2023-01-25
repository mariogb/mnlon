
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "air_company_in_pkey_idx")})
public class AirCompany implements IDcLon,Serializable{

    public AirCompany(){
    }

    @Id
    @GeneratedValue(generator = "seq_air_company")
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
    private String pname;

    public String getPname(){
        return this.pname;
    }

    public void setPname(String pname){
        this.pname = pname;
    }        
    

    

    
    @OneToMany
    @JoinColumn(name = "la_compania_id")    
    private Set<Plane> planes;

    public Set<Plane> getPlanes(){
        return this.planes;
    }
    
    public void setPlanes(Set<Plane> planes){
        this.planes = planes;
    }
 
      
}
        