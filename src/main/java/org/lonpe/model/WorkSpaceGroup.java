
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "work_space_group_in_pkey_idx")})
public class WorkSpaceGroup implements IDcLon,Serializable{

    public WorkSpaceGroup(){
    }

    @Id
    @GeneratedValue(generator = "seq_work_space_group")
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
    private String typeLon;

    public String getTypeLon(){
        return this.typeLon;
    }

    public void setTypeLon(String typeLon){
        this.typeLon = typeLon;
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
 

    
    @OneToMany
    @JoinColumn(name = "work_space_group_id")    
    private Set<WorkSpace> workSpaces;

    public Set<WorkSpace> getWorkSpaces(){
        return this.workSpaces;
    }
    
    public void setWorkSpaces(Set<WorkSpace> workSpaces){
        this.workSpaces = workSpaces;
    }
 
      
}
        