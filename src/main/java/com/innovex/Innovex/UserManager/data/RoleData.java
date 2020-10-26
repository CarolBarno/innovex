/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.UserManager.data;

import java.io.Serializable;
import java.util.Collection;
import lombok.Data;

/**
 *
 * @author
 */
@Data
public class RoleData implements Serializable {

    private final Long id;
    private final String name;
    private final String description;
    private final Boolean disabled;

    public RolePermissionData toRolePermissionData(final Collection<PermissionData> permissionUsageData) {
        return new RolePermissionData(this.id, this.name, this.description, this.disabled, permissionUsageData);
    }

    public RoleData(final Long id, final String name, final String description, final Boolean disabled) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.disabled = disabled;
    }

    @Override
    public boolean equals(final Object obj) {
        final RoleData role = (RoleData) obj;
        return this.id.equals(role.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
