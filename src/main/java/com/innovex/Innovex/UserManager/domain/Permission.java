/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.UserManager.domain;

import com.innovex.Innovex.utilities.domain.BaseEntity;
import javax.persistence.Entity;
import lombok.Data;

/**
 *
 * @author
 */
@Entity
@Data
public class Permission extends BaseEntity {

    private String permissionGroup;

    private String name;

    public boolean hasCode(final String checkCode) {
        return this.name.equalsIgnoreCase(checkCode);
    }

}
