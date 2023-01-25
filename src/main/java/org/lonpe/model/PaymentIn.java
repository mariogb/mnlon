
package org.lonpe.model;            

            
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "payment_in_in_pkey_idx")})
public class PaymentIn implements IDcLon,Serializable{

    public PaymentIn(){
    }

    @Id
    @GeneratedValue(generator = "seq_payment_in")
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
    private BigDecimal quantity;

    public BigDecimal getQuantity(){
        return this.quantity;
    }

    public void setQuantity(BigDecimal quantity){
        this.quantity = quantity;
    }        
    

    
    @NotNull
    @ManyToOne    
    private PaymentInForm paymentInForm;

    public PaymentInForm getPaymentInForm(){
        return this.paymentInForm;
    }
    
    public void setPaymentInForm(PaymentInForm paymentInForm){
        this.paymentInForm = paymentInForm;
    }
 

    @NotNull
    @ManyToOne    
    private Sale sale;

    public Sale getSale(){
        return this.sale;
    }
    
    public void setSale(Sale sale){
        this.sale = sale;
    }
 

    @NotNull
    @ManyToOne    
    private MonetaryAccount inAccount;

    public MonetaryAccount getInAccount(){
        return this.inAccount;
    }
    
    public void setInAccount(MonetaryAccount inAccount){
        this.inAccount = inAccount;
    }
 

    
      
}
        