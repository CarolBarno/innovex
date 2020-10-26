/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.UserManager.service;

import com.innovex.Innovex.UserManager.data.RoleData;
import com.innovex.Innovex.UserManager.domain.Role;
import com.innovex.Innovex.UserManager.repository.PermissionRepository;
import com.innovex.Innovex.UserManager.repository.RoleRepository;
import com.innovex.Innovex.utilities.exception.APIException;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author
 */
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public RoleData createRole(RoleData data) {

        if (getRoleByName(data.getName()).isPresent()) {
            throw APIException.conflict("Role with name {0} already exists.", data.getName());
        }

        Role role = new Role(data.getName(), data.getDescription(), data.getDisabled());
        Role savedRole = roleRepository.save(role);
        return savedRole.toData();
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByRoleName(name);
    }
}
