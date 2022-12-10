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
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pkey"}, name = "form_lon_in_pkey_idx")})
public class FormLon implements IDcLon, Serializable {

    public FormLon() {
    }

    @Id
    @GeneratedValue(generator = "seq_form_lon")
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

    @NotBlank
    @NotNull
    private String pname;

    public String getPname() {
        return this.pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @OneToMany
    @JoinColumn(name = "form_lon_id")
    private Set<FormLonField> formLonFields;

    public Set<FormLonField> getFormLonFields() {
        return this.formLonFields;
    }

    public void setFormLonFields(Set<FormLonField> formLonFields) {
        this.formLonFields = formLonFields;
    }

}
