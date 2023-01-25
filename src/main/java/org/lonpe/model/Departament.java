
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "departament_in_pkey_idx")})
public class Departament implements IDcLon,Serializable{

    public Departament(){
    }

    @Id
    @GeneratedValue(generator = "seq_departament")
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
    

    

    
    @OneToMany
    @JoinColumn(name = "departament_id")    
    private Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods;

    public Set<DepartamentBaseTimePeriod> getDepartamentBaseTimePeriods(){
        return this.departamentBaseTimePeriods;
    }
    
    public void setDepartamentBaseTimePeriods(Set<DepartamentBaseTimePeriod> departamentBaseTimePeriods){
        this.departamentBaseTimePeriods = departamentBaseTimePeriods;
    }
 


    @OneToMany
    @JoinColumn(name = "departament_id")    
    private Set<DepartamentUserLon> departamentUserLons;

    public Set<DepartamentUserLon> getDepartamentUserLons(){
        return this.departamentUserLons;
    }
    
    public void setDepartamentUserLons(Set<DepartamentUserLon> departamentUserLons){
        this.departamentUserLons = departamentUserLons;
    }
 


    @OneToMany
    @JoinColumn(name = "departament_id")    
    private Set<DepartamentJob> departamenJobs;

    public Set<DepartamentJob> getDepartamenJobs(){
        return this.departamenJobs;
    }
    
    public void setDepartamenJobs(Set<DepartamentJob> departamenJobs){
        this.departamenJobs = departamenJobs;
    }
 
      
}
        