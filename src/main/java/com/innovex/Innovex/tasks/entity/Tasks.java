/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.tasks.entity;

import com.example.innovexsolutions.tasks.enums.Event;
import com.innovex.Innovex.Auditing.configuration.Auditable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

/**
 *
 * @author CipherCom
 */
@Data
@Entity
public class Tasks extends Auditable {

    private String programTime;
    private String message;
    private String actualTime;
    private String color;
    @Enumerated(EnumType.STRING)
    private Event event;
    private int servers;
    private int runningServers;

}
