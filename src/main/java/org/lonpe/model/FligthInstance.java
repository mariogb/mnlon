package org.lonpe.model;

import java.time.LocalDateTime;

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
    @UniqueConstraint(columnNames = {"pkey"}, name = "fligth_instance_in_pkey_idx")})
public class FligthInstance implements IDcLon, Serializable {

    public FligthInstance() {
    }

    @Id
    @GeneratedValue(generator = "seq_fligth_instance")
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
    private LocalDateTime inLocalDateTime;

    public LocalDateTime getInLocalDateTime() {
        return this.inLocalDateTime;
    }

    public void setInLocalDateTime(LocalDateTime inLocalDateTime) {
        this.inLocalDateTime = inLocalDateTime;
    }

    @NotNull
    private LocalDateTime outLocalDateTime;

    public LocalDateTime getOutLocalDateTime() {
        return this.outLocalDateTime;
    }

    public void setOutLocalDateTime(LocalDateTime outLocalDateTime) {
        this.outLocalDateTime = outLocalDateTime;
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

    @NotNull
    @ManyToOne
    private Fligth theFligth;

    public Fligth getTheFligth() {
        return this.theFligth;
    }

    public void setTheFligth(Fligth theFligth) {
        this.theFligth = theFligth;
    }

}
