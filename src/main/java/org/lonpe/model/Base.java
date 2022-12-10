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
    @UniqueConstraint(columnNames = {"pkey"}, name = "base_in_pkey_idx")})
public class Base implements IDcLon, Serializable {

    public Base() {
    }

    @Id
    @GeneratedValue(generator = "seq_base")
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
    @JoinColumn(name = "base_id")
    private Set<BaseTimePeriod> baseTimePeriods;

    public Set<BaseTimePeriod> getBaseTimePeriods() {
        return this.baseTimePeriods;
    }

    public void setBaseTimePeriods(Set<BaseTimePeriod> baseTimePeriods) {
        this.baseTimePeriods = baseTimePeriods;
    }

    @OneToMany
    @JoinColumn(name = "base_id")
    private Set<WorkSpaceGroup> workSpaceGroups;

    public Set<WorkSpaceGroup> getWorkSpaceGroups() {
        return this.workSpaceGroups;
    }

    public void setWorkSpaceGroups(Set<WorkSpaceGroup> workSpaceGroups) {
        this.workSpaceGroups = workSpaceGroups;
    }

    @OneToMany
    @JoinColumn(name = "base_id")
    private Set<BaseUserLon> baseUserLons;

    public Set<BaseUserLon> getBaseUserLons() {
        return this.baseUserLons;
    }

    public void setBaseUserLons(Set<BaseUserLon> baseUserLons) {
        this.baseUserLons = baseUserLons;
    }

}
