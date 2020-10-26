/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.UserManager.data;

import java.util.Collection;
import lombok.Data;

/**
 *
 * @author
 */
@Data
public class RolePermissionData {

    private final Long id;
    private final String name;
    private final String description;
    private final Boolean disabled;
    private final Collection<PermissionData> permissionUsageData;

    public RolePermissionData(Long id, String name, String description, Boolean disabled, Collection<PermissionData> permissionUsageData) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.disabled = disabled;
        this.permissionUsageData = permissionUsageData;
    }

}
