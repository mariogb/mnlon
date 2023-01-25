
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "purchase_contract_in_pkey_idx")})
public class PurchaseContract implements IDcLon,Serializable{

    public PurchaseContract(){
    }

    @Id
    @GeneratedValue(generator = "seq_purchase_contract")
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
    

    
    @NotNull
    @ManyToOne    
    private DepartamentBaseTimePeriod departamentBaseTimePeriod;

    public DepartamentBaseTimePeriod getDepartamentBaseTimePeriod(){
        return this.departamentBaseTimePeriod;
    }
    
    public void setDepartamentBaseTimePeriod(DepartamentBaseTimePeriod departamentBaseTimePeriod){
        this.departamentBaseTimePeriod = departamentBaseTimePeriod;
    }
 

    @NotNull
    @ManyToOne    
    private ThirdPerson thirdPerson;

    public ThirdPerson getThirdPerson(){
        return this.thirdPerson;
    }
    
    public void setThirdPerson(ThirdPerson thirdPerson){
        this.thirdPerson = thirdPerson;
    }
 

    
    @OneToMany
    @JoinColumn(name = "purchase_contract_id")    
    private Set<Appointment> appointments;

    public Set<Appointment> getAppointments(){
        return this.appointments;
    }
    
    public void setAppointments(Set<Appointment> appointments){
        this.appointments = appointments;
    }
 


    @OneToMany
    @JoinColumn(name = "purchase_contract_id")    
    private Set<Purchase> purchases;

    public Set<Purchase> getPurchases(){
        return this.purchases;
    }
    
    public void setPurchases(Set<Purchase> purchases){
        this.purchases = purchases;
    }
 
      
}
        