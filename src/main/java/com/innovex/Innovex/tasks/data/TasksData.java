/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.tasks.data;

import com.example.innovexsolutions.tasks.enums.Event;
import com.innovex.Innovex.tasks.entity.Tasks;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

/**
 *
 * @author CipherCom
 */
@Data
public class TasksData {
    
    @ApiModelProperty(hidden = true, required = false)
    private Long taskId;
    @ApiModelProperty(hidden = true, required = false)
    private String programTime;
    @ApiModelProperty(hidden = true, required = false)
    private String message;
    @ApiModelProperty(hidden = true, required = false)
    private String actualTime;
    @ApiModelProperty(hidden = true, required = false)
    private String color;
    @ApiModelProperty(hidden = true, required = false)
    @Enumerated(EnumType.STRING)
    private Event event;
    @ApiModelProperty(hidden = true, required = false)
    private int servers;
    @ApiModelProperty(hidden = true, required = false)
    private int runningServers;
    @ApiModelProperty(hidden = true, required = false)
    private String displayMessage;
    
    public static Tasks map(TasksData data) {
        Tasks tasks = new Tasks();
        
        return tasks;
    }
    
    public static TasksData map(Tasks tasks) {
        TasksData data = new TasksData();
        
        data.setActualTime(tasks.getActualTime());
        data.setColor(tasks.getColor());
        data.setEvent(tasks.getEvent());
        data.setMessage(tasks.getMessage());
        data.setProgramTime(tasks.getProgramTime());
        data.setTaskId(tasks.getId());
        data.setServers(tasks.getServers());
        data.setRunningServers(tasks.getRunningServers());
        data.setDisplayMessage(tasks.getProgramTime() + " - " + tasks.getMessage());
        
        return data;
    }
    
}
