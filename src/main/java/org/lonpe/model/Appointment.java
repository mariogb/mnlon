
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "appointment_in_pkey_idx")})
public class Appointment implements IDcLon,Serializable{

    public Appointment(){
    }

    @Id
    @GeneratedValue(generator = "seq_appointment")
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
    private Integer endHour;

    public Integer getEndHour(){
        return this.endHour;
    }

    public void setEndHour(Integer endHour){
        this.endHour = endHour;
    }        
    

        @NotNull    
    private Integer endMinute;

    public Integer getEndMinute(){
        return this.endMinute;
    }

    public void setEndMinute(Integer endMinute){
        this.endMinute = endMinute;
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
    private Integer startHour;

    public Integer getStartHour(){
        return this.startHour;
    }

    public void setStartHour(Integer startHour){
        this.startHour = startHour;
    }        
    

        @NotNull    
    private Integer startMinute;

    public Integer getStartMinute(){
        return this.startMinute;
    }

    public void setStartMinute(Integer startMinute){
        this.startMinute = startMinute;
    }        
    

        @NotNull    
    private Integer weekDay;

    public Integer getWeekDay(){
        return this.weekDay;
    }

    public void setWeekDay(Integer weekDay){
        this.weekDay = weekDay;
    }        
    

    
    @NotNull
    @ManyToOne    
    private ContractOut contract;

    public ContractOut getContract(){
        return this.contract;
    }
    
    public void setContract(ContractOut contract){
        this.contract = contract;
    }
 

    @NotNull
    @ManyToOne    
    private WorkSpace workSpace;

    public WorkSpace getWorkSpace(){
        return this.workSpace;
    }
    
    public void setWorkSpace(WorkSpace workSpace){
        this.workSpace = workSpace;
    }
 

    @NotNull
    @ManyToOne    
    private DepartamentJobInstance departamentJobInstance;

    public DepartamentJobInstance getDepartamentJobInstance(){
        return this.departamentJobInstance;
    }
    
    public void setDepartamentJobInstance(DepartamentJobInstance departamentJobInstance){
        this.departamentJobInstance = departamentJobInstance;
    }
 

    
      
}
        