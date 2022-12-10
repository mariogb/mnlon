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
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pkey"}, name = "payment_out_form_in_pkey_idx")})
public class PaymentOutForm implements IDcLon, Serializable {

    public PaymentOutForm() {
    }

    @Id
    @GeneratedValue(generator = "seq_payment_out_form")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank
    @NotNull
    private String pkey;

    public String getPkey() {
        return this.pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    @NotNull
    @ManyToOne
    private MonetaryAccount monetaryAccount;

    public MonetaryAccount getMonetaryAccount() {
        return this.monetaryAccount;
    }

    public void setMonetaryAccount(MonetaryAccount monetaryAccount) {
        this.monetaryAccount = monetaryAccount;
    }

    @NotNull
    @ManyToOne
    private PaymentOutType paymentOutType;

    public PaymentOutType getPaymentOutType() {
        return this.paymentOutType;
    }

    public void setPaymentOutType(PaymentOutType paymentOutType) {
        this.paymentOutType = paymentOutType;
    }

}
