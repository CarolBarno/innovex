/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.tasks.repository;

import com.innovex.Innovex.tasks.entity.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CipherCom
 */
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    Page<Tasks> findTopByOrderByIdDesc(Pageable pageable);

    Tasks findTopByOrderByIdDesc();

}
