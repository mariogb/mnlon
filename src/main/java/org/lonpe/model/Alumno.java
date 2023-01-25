
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "alumno_in_pkey_idx")})
public class Alumno implements IDcLon,Serializable{

    public Alumno(){
    }

    @Id
    @GeneratedValue(generator = "seq_alumno")
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
    private Boolean activo;

    public Boolean getActivo(){
        return this.activo;
    }

    public void setActivo(Boolean activo){
        this.activo = activo;
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
    private String primer_apellido;

    public String getPrimer_apellido(){
        return this.primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido){
        this.primer_apellido = primer_apellido;
    }        
    

        @NotBlank
    @NotNull    
    private String segundo_apellido;

    public String getSegundo_apellido(){
        return this.segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido){
        this.segundo_apellido = segundo_apellido;
    }        
    

    

    
    @OneToMany
    @JoinColumn(name = "alumno_id")    
    private Set<Calificacion> calificaciones;

    public Set<Calificacion> getCalificaciones(){
        return this.calificaciones;
    }
    
    public void setCalificaciones(Set<Calificacion> calificaciones){
        this.calificaciones = calificaciones;
    }
 
      
}
        