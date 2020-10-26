package com.innovex.Innovex.UserManager.domain;

import com.innovex.Innovex.UserManager.data.RoleData;
import com.innovex.Innovex.utilities.domain.BaseEntity;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

/**
 *
 */
@Entity
@Data
@Table(name = "Users_Roles")

public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "is_disabled", nullable = false)
    private Boolean disabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "auth_permission_role",
            joinColumns = {
                @JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "permission_id", referencedColumnName = "id")}, uniqueConstraints = {
                @UniqueConstraint(name = "ROLE_PERMISSION_UK_auth_permission_role", columnNames = {"role_id", "permission_id"})})
    private Set<Permission> permissions = new HashSet<>();

    protected Role() {

    }

    public Role(String roleName, String description, Boolean disabled) {
        this.roleName = roleName.trim();
        this.description = description.trim();
        this.disabled = false;
    }

    public boolean updatePermission(final Permission permission, final boolean isSelected) {
        boolean changed = false;
        if (isSelected) {
            changed = addPermission(permission);
        } else {
            changed = removePermission(permission);
        }

        return changed;
    }

    private boolean addPermission(final Permission permission) {
        return this.permissions.add(permission);
    }

    private boolean removePermission(final Permission permission) {
        return this.permissions.remove(permission);
    }

    public Collection<Permission> getPermission() {
        return this.permissions;
    }

    public boolean hasPermissionTo(final String permissionCode) {
        boolean match = false;
        for (final Permission permission : this.permissions) {
            if (permission.hasCode(permissionCode)) {
                match = true;
                break;
            }
        }
        return match;
    }

    public RoleData toData() {
        return new RoleData(getId(), this.roleName, this.description, this.disabled);
    }

}
