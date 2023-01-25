
package org.lonpe.model;            

            
import java.time.LocalDate;
import java.math.BigDecimal;
         

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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "calificacion_in_pkey_idx")})
public class Calificacion implements IDcLon,Serializable{

    public Calificacion(){
    }

    @Id
    @GeneratedValue(generator = "seq_calificacion")
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
    private BigDecimal calificacion;

    public BigDecimal getCalificacion(){
        return this.calificacion;
    }

    public void setCalificacion(BigDecimal calificacion){
        this.calificacion = calificacion;
    }        
    

        @NotNull    
    private LocalDate fecha;

    public LocalDate getFecha(){
        return this.fecha;
    }

    public void setFecha(LocalDate fecha){
        this.fecha = fecha;
    }        
    

    
    @NotNull
    @ManyToOne    
    private Alumno alumno;

    public Alumno getAlumno(){
        return this.alumno;
    }
    
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
    }
 

    @NotNull
    @ManyToOne    
    private Materia materia;

    public Materia getMateria(){
        return this.materia;
    }
    
    public void setMateria(Materia materia){
        this.materia = materia;
    }
 

    
      
}
        