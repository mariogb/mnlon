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
    @UniqueConstraint(columnNames = {"pkey"}, name = "program_in_pkey_idx")})
public class Program implements IDcLon, Serializable {

    public Program() {
    }

    @Id
    @GeneratedValue(generator = "seq_program")
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
    private String description;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotBlank
    private String fastKey;

    public String getFastKey() {
        return this.fastKey;
    }

    public void setFastKey(String fastKey) {
        this.fastKey = fastKey;
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
    @JoinColumn(name = "program_id")
    private Set<ProgramBaseTimePeriod> programBaseTimePeriods;

    public Set<ProgramBaseTimePeriod> getProgramBaseTimePeriods() {
        return this.programBaseTimePeriods;
    }

    public void setProgramBaseTimePeriods(Set<ProgramBaseTimePeriod> programBaseTimePeriods) {
        this.programBaseTimePeriods = programBaseTimePeriods;
    }

    @OneToMany
    @JoinColumn(name = "program_id")
    private Set<ProgramUserLon> programUserLons;

    public Set<ProgramUserLon> getProgramUserLons() {
        return this.programUserLons;
    }

    public void setProgramUserLons(Set<ProgramUserLon> programUserLons) {
        this.programUserLons = programUserLons;
    }

}
