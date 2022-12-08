
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "base_time_period_in_pkey_idx")})
public class BaseTimePeriod implements IDcLon,Serializable{

    public BaseTimePeriod(){
    }

    @Id
    @GeneratedValue(generator = "seq_base_time_period")
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


    

    
    @NotNull
    @ManyToOne    
    private Base base;

    public Base getBase(){
        return this.base;
    }
    
    public void setBase(Base base){
        this.base = base;
    }
 

    @NotNull
    @ManyToOne    
    private TimePeriod timePeriod;

    public TimePeriod getTimePeriod(){
        return this.timePeriod;
    }
    
    public void setTimePeriod(TimePeriod timePeriod){
        this.timePeriod = timePeriod;
    }
 

    
    @OneToMany
    @JoinColumn(name = "base_time_period_id")    
    private Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods;

    public Set<DepartamentBaseTimePeriod> getDepartamentBaseTimePeriods(){
        return this.departamentBaseTimePeriods;
    }
    
    public void setDepartamentBaseTimePeriods(Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods){
        this.departamentBaseTimePeriods = departamentBaseTimePeriods;
    }
 


    @OneToMany
    @JoinColumn(name = "base_time_period_id")    
    private Set<ProgramBaseTimePeriod> programBaseTimePeriods;

    public Set<ProgramBaseTimePeriod> getProgramBaseTimePeriods(){
        return this.programBaseTimePeriods;
    }
    
    public void setProgramBaseTimePeriods(Set<ProgramBaseTimePeriod> programBaseTimePeriods){
        this.programBaseTimePeriods = programBaseTimePeriods;
    }
 
      
}
        