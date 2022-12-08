
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "program_base_time_period_in_pkey_idx")})
public class ProgramBaseTimePeriod implements IDcLon,Serializable{

    public ProgramBaseTimePeriod(){
    }

    @Id
    @GeneratedValue(generator = "seq_program_base_time_period")
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
    private BaseTimePeriod baseTimePeriod;

    public BaseTimePeriod getBaseTimePeriod(){
        return this.baseTimePeriod;
    }
    
    public void setBaseTimePeriod(BaseTimePeriod baseTimePeriod){
        this.baseTimePeriod = baseTimePeriod;
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
 

    
    @OneToMany
    @JoinColumn(name = "program_base_time_period_id")    
    private Set<ContractIn> contracts;

    public Set<ContractIn> getContracts(){
        return this.contracts;
    }
    
    public void setContracts(Set<ContractIn> contracts){
        this.contracts = contracts;
    }
 
      
}
        