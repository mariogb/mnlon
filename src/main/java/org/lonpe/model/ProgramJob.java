
package org.lonpe.model;            

            

         

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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "program_job_in_pkey_idx")})
public class ProgramJob implements IDcLon,Serializable{

    public ProgramJob(){
    }

    @Id
    @GeneratedValue(generator = "seq_program_job")
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
    

        @NotNull    
    private Integer nhoras;

    public Integer getNhoras(){
        return this.nhoras;
    }

    public void setNhoras(Integer nhoras){
        this.nhoras = nhoras;
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
    private Program program;

    public Program getProgram(){
        return this.program;
    }
    
    public void setProgram(Program program){
        this.program = program;
    }
 

    @NotNull
    @ManyToOne    
    private DepartamentJob departamentJob;

    public DepartamentJob getDepartamentJob(){
        return this.departamentJob;
    }
    
    public void setDepartamentJob(DepartamentJob departamentJob){
        this.departamentJob = departamentJob;
    }
 

    
      
}
        