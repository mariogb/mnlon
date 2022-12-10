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
    @UniqueConstraint(columnNames = {"pkey"}, name = "work_space_in_pkey_idx")})
public class WorkSpace implements IDcLon, Serializable {

    public WorkSpace() {
    }

    @Id
    @GeneratedValue(generator = "seq_work_space")
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
    private Long capacity;

    public Long getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
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

    @NotBlank
    @NotNull
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotNull
    @ManyToOne
    private WorkSpaceGroup workSpaceGroup;

    public WorkSpaceGroup getWorkSpaceGroup() {
        return this.workSpaceGroup;
    }

    public void setWorkSpaceGroup(WorkSpaceGroup workSpaceGroup) {
        this.workSpaceGroup = workSpaceGroup;
    }

    @OneToMany
    @JoinColumn(name = "work_space_id")
    private Set<Appointment> appointments;

    public Set<Appointment> getAppointments() {
        return this.appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    @OneToMany
    @JoinColumn(name = "work_space_id")
    private Set<StockRack> stockRack;

    public Set<StockRack> getStockRack() {
        return this.stockRack;
    }

    public void setStockRack(Set<StockRack> stockRack) {
        this.stockRack = stockRack;
    }

}
