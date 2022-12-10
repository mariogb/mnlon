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
import javax.persistence.ManyToOne;

@Introspected
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pkey"}, name = "fligth_in_pkey_idx")})
public class Fligth implements IDcLon, Serializable {

    public Fligth() {
    }

    @Id
    @GeneratedValue(generator = "seq_fligth")
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

    @NotNull
    @ManyToOne
    private Airport fromAirport;

    public Airport getFromAirport() {
        return this.fromAirport;
    }

    public void setFromAirport(Airport fromAirport) {
        this.fromAirport = fromAirport;
    }

    @NotNull
    @ManyToOne
    private Airport toAirport;

    public Airport getToAirport() {
        return this.toAirport;
    }

    public void setToAirport(Airport toAirport) {
        this.toAirport = toAirport;
    }

    @NotNull
    @ManyToOne
    private Plane plane;

    public Plane getPlane() {
        return this.plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @OneToMany
    @JoinColumn(name = "the_fligth_id")
    private Set<FligthInstance> fligthInstances;

    public Set<FligthInstance> getFligthInstances() {
        return this.fligthInstances;
    }

    public void setFligthInstances(Set<FligthInstance> fligthInstances) {
        this.fligthInstances = fligthInstances;
    }

}
