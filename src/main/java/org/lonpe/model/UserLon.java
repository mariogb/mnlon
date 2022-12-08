
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "user_lon_in_pkey_idx")})
public class UserLon implements IDcLon,Serializable{

    public UserLon(){
    }

    @Id
    @GeneratedValue(generator = "seq_user_lon")
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
    private String email;

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }        
    

        @NotNull    
    private Boolean enabled;

    public Boolean getEnabled(){
        return this.enabled;
    }

    public void setEnabled(Boolean enabled){
        this.enabled = enabled;
    }        
    

        @NotBlank
    @NotNull    
    private String password;

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
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
    private String typeLon;

    public String getTypeLon(){
        return this.typeLon;
    }

    public void setTypeLon(String typeLon){
        this.typeLon = typeLon;
    }        
    

        @NotBlank
    @NotNull    
    private String username;

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }        
    

    

    
    @OneToMany
    @JoinColumn(name = "user_lon_id")    
    private Set<DepartamentUserLon> departamentUserLons;

    public Set<DepartamentUserLon> getDepartamentUserLons(){
        return this.departamentUserLons;
    }
    
    public void setDepartamentUserLons(Set<DepartamentUserLon> departamentUserLons){
        this.departamentUserLons = departamentUserLons;
    }
 


    @OneToMany
    @JoinColumn(name = "user_lon_id")    
    private Set<ProgramUserLon> programUserLons;

    public Set<ProgramUserLon> getProgramUserLons(){
        return this.programUserLons;
    }
    
    public void setProgramUserLons(Set<ProgramUserLon> programUserLons){
        this.programUserLons = programUserLons;
    }
 


    @OneToMany
    @JoinColumn(name = "user_lon_id")    
    private Set<BaseUserLon> baseUserLons;

    public Set<BaseUserLon> getBaseUserLons(){
        return this.baseUserLons;
    }
    
    public void setBaseUserLons(Set<BaseUserLon> baseUserLons){
        this.baseUserLons = baseUserLons;
    }
 


    @OneToMany
    @JoinColumn(name = "user_lon_id")    
    private Set<ComercialDocumentIn> comercialDocumentIns;

    public Set<ComercialDocumentIn> getComercialDocumentIns(){
        return this.comercialDocumentIns;
    }
    
    public void setComercialDocumentIns(Set<ComercialDocumentIn> comercialDocumentIns){
        this.comercialDocumentIns = comercialDocumentIns;
    }
 


    @OneToMany
    @JoinColumn(name = "user_lon_id")    
    private Set<ComercialDocumentOut> comercialDocumentOuts;

    public Set<ComercialDocumentOut> getComercialDocumentOuts(){
        return this.comercialDocumentOuts;
    }
    
    public void setComercialDocumentOuts(Set<ComercialDocumentOut> comercialDocumentOuts){
        this.comercialDocumentOuts = comercialDocumentOuts;
    }
 


    @OneToMany
    @JoinColumn(name = "user_lon_id")    
    private Set<UserRole> userRoles;

    public Set<UserRole> getUserRoles(){
        return this.userRoles;
    }
    
    public void setUserRoles(Set<UserRole> userRoles){
        this.userRoles = userRoles;
    }
 


    @OneToMany
    @JoinColumn(name = "user_lon_id")    
    private Set<UserThirdPerson> userThirdPersons;

    public Set<UserThirdPerson> getUserThirdPersons(){
        return this.userThirdPersons;
    }
    
    public void setUserThirdPersons(Set<UserThirdPerson> userThirdPersons){
        this.userThirdPersons = userThirdPersons;
    }
 
      
}
        