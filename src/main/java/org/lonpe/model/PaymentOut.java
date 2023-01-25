
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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"pkey"},name = "payment_out_in_pkey_idx")})
public class PaymentOut implements IDcLon,Serializable{

    public PaymentOut(){
    }

    @Id
    @GeneratedValue(generator = "seq_payment_out")
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
    private PaymentOutForm paymentOutForm;

    public PaymentOutForm getPaymentOutForm(){
        return this.paymentOutForm;
    }
    
    public void setPaymentOutForm(PaymentOutForm paymentOutForm){
        this.paymentOutForm = paymentOutForm;
    }
 

    @NotNull
    @ManyToOne    
    private Purchase purchase;

    public Purchase getPurchase(){
        return this.purchase;
    }
    
    public void setPurchase(Purchase purchase){
        this.purchase = purchase;
    }
 

    @NotNull
    @ManyToOne    
    private MonetaryAccount outAccount;

    public MonetaryAccount getOutAccount(){
        return this.outAccount;
    }
    
    public void setOutAccount(MonetaryAccount outAccount){
        this.outAccount = outAccount;
    }
 

    
      
}
        