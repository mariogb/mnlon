
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "user_role_in_pkey_idx")})
public class UserRole implements IDcLon,Serializable{

    public UserRole(){
    }

    @Id
    @GeneratedValue(generator = "seq_user_role")
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
    private UserLon userLon;

    public UserLon getUserLon(){
        return this.userLon;
    }
    
    public void setUserLon(UserLon userLon){
        this.userLon = userLon;
    }
 

    @NotNull
    @ManyToOne    
    private Role role;

    public Role getRole(){
        return this.role;
    }
    
    public void setRole(Role role){
        this.role = role;
    }
 

    
      
}
        