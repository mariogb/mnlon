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
    @UniqueConstraint(columnNames = {"pkey"}, name = "role_in_pkey_idx")})
public class Role implements IDcLon, Serializable {

    public Role() {
    }

    @Id
    @GeneratedValue(generator = "seq_role")
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
    @JoinColumn(name = "role_id")
    private Set<UserRole> userRoles;

    public Set<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @OneToMany
    @JoinColumn(name = "role_id")
    private Set<EntityPermisionRole> entityPermisionRoles;

    public Set<EntityPermisionRole> getEntityPermisionRoles() {
        return this.entityPermisionRoles;
    }

    public void setEntityPermisionRoles(Set<EntityPermisionRole> entityPermisionRoles) {
        this.entityPermisionRoles = entityPermisionRoles;
    }

}
