/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.Auditing.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import com.innovex.Innovex.config.SystemConstants;

/**
 *
 * @author 
 */
public class EntityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SpringSecurityUtils.getCurrentUserLogin().orElse(SystemConstants.SYSTEM_ACCOUNT));
    }

}
