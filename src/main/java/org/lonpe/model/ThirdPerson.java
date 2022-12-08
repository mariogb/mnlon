
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "third_person_in_pkey_idx")})
public class ThirdPerson implements IDcLon,Serializable{

    public ThirdPerson(){
    }

    @Id
    @GeneratedValue(generator = "seq_third_person")
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
    

        @NotBlank
    @NotNull    
    private String rfc;

    public String getRfc(){
        return this.rfc;
    }

    public void setRfc(String rfc){
        this.rfc = rfc;
    }        
    

        @NotBlank
    @NotNull    
    private String tipo;

    public String getTipo(){
        return this.tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }        
    

    

    
    @OneToMany
    @JoinColumn(name = "third_person_id")    
    private Set<ContractIn> contractIns;

    public Set<ContractIn> getContractIns(){
        return this.contractIns;
    }
    
    public void setContractIns(Set<ContractIn> contractIns){
        this.contractIns = contractIns;
    }
 


    @OneToMany
    @JoinColumn(name = "third_person_id")    
    private Set<ContractOut> contractOuts;

    public Set<ContractOut> getContractOuts(){
        return this.contractOuts;
    }
    
    public void setContractOuts(Set<ContractOut> contractOuts){
        this.contractOuts = contractOuts;
    }
 


    @OneToMany
    @JoinColumn(name = "third_person_id")    
    private Set<UserThirdPerson> userThirdPersons;

    public Set<UserThirdPerson> getUserThirdPersons(){
        return this.userThirdPersons;
    }
    
    public void setUserThirdPersons(Set<UserThirdPerson> userThirdPersons){
        this.userThirdPersons = userThirdPersons;
    }
 
      
}
        