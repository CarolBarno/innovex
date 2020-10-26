/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.tasks.service;

import com.innovex.Innovex.tasks.entity.Tasks;
import com.innovex.Innovex.tasks.repository.TasksRepository;
import com.innovex.Innovex.utilities.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CipherCom
 */
@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    @Transactional
    public Tasks saveTasks(Tasks tasks) {
        return tasksRepository.save(tasks);
    }

    public Page<Tasks> fetchAllTasks(Pageable pageable) {
        return tasksRepository.findAll(pageable);
    }

    @Transactional
    public Tasks updateTasks(Tasks tasks) {
        return tasksRepository.save(tasks);
    }

    public Tasks findById(Long id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> APIException.notFound("Service covered identified by id {0} not found", id));
    }

    @Transactional
    public void deleteService(Long taskId) {
        tasksRepository.deleteById(taskId);
    }

    public Page<Tasks> findLatestRecord(Pageable pageable) {
        return tasksRepository.findTopByOrderByIdDesc(pageable);
    }
    
    public Tasks findLatestRecord() {
        return tasksRepository.findTopByOrderByIdDesc();
    }

}
