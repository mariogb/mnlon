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

@Introspected
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pkey"}, name = "me_usr_interface_in_pkey_idx")})
public class MeUsrInterface implements IDcLon, Serializable {

    public MeUsrInterface() {
    }

    @Id
    @GeneratedValue(generator = "seq_me_usr_interface")
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
    private String dc;

    public String getDc() {
        return this.dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    @NotBlank
    @NotNull
    private String label;

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @NotNull
    private Integer level;

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
