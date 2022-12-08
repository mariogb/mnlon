
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "monetary_account_in_pkey_idx")})
public class MonetaryAccount implements IDcLon,Serializable{

    public MonetaryAccount(){
    }

    @Id
    @GeneratedValue(generator = "seq_monetary_account")
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
    

    

    
    @OneToMany
    @JoinColumn(name = "monetary_account_id")    
    private Set<PaymentOut> paymentOuts;

    public Set<PaymentOut> getPaymentOuts(){
        return this.paymentOuts;
    }
    
    public void setPaymentOuts(Set<PaymentOut> paymentOuts){
        this.paymentOuts = paymentOuts;
    }
 


    @OneToMany
    @JoinColumn(name = "monetary_account_id")    
    private Set<PaymentIn> paymentIns;

    public Set<PaymentIn> getPaymentIns(){
        return this.paymentIns;
    }
    
    public void setPaymentIns(Set<PaymentIn> paymentIns){
        this.paymentIns = paymentIns;
    }
 
      
}
        