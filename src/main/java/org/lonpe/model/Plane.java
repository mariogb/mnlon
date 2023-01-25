
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "plane_in_pkey_idx")})
public class Plane implements IDcLon,Serializable{

    public Plane(){
    }

    @Id
    @GeneratedValue(generator = "seq_plane")
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
    private String plate;

    public String getPlate(){
        return this.plate;
    }

    public void setPlate(String plate){
        this.plate = plate;
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
    private Integer seats;

    public Integer getSeats(){
        return this.seats;
    }

    public void setSeats(Integer seats){
        this.seats = seats;
    }        
    

    
    @NotNull
    @ManyToOne    
    private AirCompany laCompania;

    public AirCompany getLaCompania(){
        return this.laCompania;
    }
    
    public void setLaCompania(AirCompany laCompania){
        this.laCompania = laCompania;
    }
 

    
    @OneToMany
    @JoinColumn(name = "plane_id")    
    private Set<Fligth> fligths;

    public Set<Fligth> getFligths(){
        return this.fligths;
    }
    
    public void setFligths(Set<Fligth> fligths){
        this.fligths = fligths;
    }
 
      
}
        