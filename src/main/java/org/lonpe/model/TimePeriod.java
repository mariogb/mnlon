
package org.lonpe.model;            

            
import java.time.LocalDate;
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "time_period_in_pkey_idx")})
public class TimePeriod implements IDcLon,Serializable{

    public TimePeriod(){
    }

    @Id
    @GeneratedValue(generator = "seq_time_period")
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
    private LocalDate beginDate;

    public LocalDate getBeginDate(){
        return this.beginDate;
    }

    public void setBeginDate(LocalDate beginDate){
        this.beginDate = beginDate;
    }        
    

        @NotNull    
    private LocalDate endDate;

    public LocalDate getEndDate(){
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate){
        this.endDate = endDate;
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
    

    

    
    @OneToMany
    @JoinColumn(name = "time_period_id")    
    private Set<BaseTimePeriod> baseTimePeriods;

    public Set<BaseTimePeriod> getBaseTimePeriods(){
        return this.baseTimePeriods;
    }
    
    public void setBaseTimePeriods(Set<BaseTimePeriod> baseTimePeriods){
        this.baseTimePeriods = baseTimePeriods;
    }
 
      
}
        