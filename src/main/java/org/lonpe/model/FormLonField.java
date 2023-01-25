
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "form_lon_field_in_pkey_idx")})
public class FormLonField implements IDcLon,Serializable{

    public FormLonField(){
    }

    @Id
    @GeneratedValue(generator = "seq_form_lon_field")
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
    private FormLon formLon;

    public FormLon getFormLon(){
        return this.formLon;
    }
    
    public void setFormLon(FormLon formLon){
        this.formLon = formLon;
    }
 

    
      
}
        