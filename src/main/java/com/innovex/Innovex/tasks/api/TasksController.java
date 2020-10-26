/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.tasks.api;

import com.example.innovexsolutions.tasks.enums.Event;
import com.innovex.Innovex.tasks.data.TasksData;
import com.innovex.Innovex.tasks.entity.Tasks;
import com.innovex.Innovex.tasks.service.TasksService;
import com.innovex.Innovex.utilities.utility.PageDetails;
import com.innovex.Innovex.utilities.utility.Pager;
import io.swagger.annotations.Api;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author CipherCom
 */
@Api
@RestController
@RequestMapping("api")
@Component
@EnableScheduling
public class TasksController {

    @Autowired
    private TasksService tasksService;

    @Scheduled(initialDelay = 30000, fixedRate = 30000)
    @PostMapping("start")
    public void startTask() throws ParseException {

        Pageable pageable = null;
        Tasks tasks = new Tasks();

        //servers number
        Random ra = new Random();
        int low = 10;
        int high = 20;
        int result = ra.nextInt((high - low) + 1) + low;
        int running = 0;

        //color
        int randNum = ra.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", randNum);

        Page<TasksData> rs = tasksService.findLatestRecord(pageable).map(a -> TasksData.map(a));

        String myTime = "12:00:00";
        String myTime1 = "10:00:00";
        if (!rs.isEmpty()) {

            for (TasksData d : rs.getContent()) {
                running = result + d.getRunningServers();
                System.out.println("running is " + running);
            }

        } else {
            running = result;
        }

        //program time
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");

        LocalTime pt = LocalTime.parse("12:00:00").plus(get(), ChronoUnit.SECONDS);
        LocalTime at = LocalTime.parse("10:00:00").plus(getA(), ChronoUnit.SECONDS);

        tasks.setEvent(Event.START);

        tasks.setServers(result);

        tasks.setRunningServers(running);

        tasks.setColor(colorCode);

        tasks.setMessage(
                "Start " + result + " servers");
        tasks.setProgramTime(
                (pt.toString()));
        tasks.setActualTime(at.toString());

        Tasks save = tasksService.saveTasks(tasks);

        System.out.println(
                "task is " + save);
        Pager<TasksData> pager = new Pager();

        pager.setCode(
                "0");
        pager.setMessage(
                "Start task details have successfully been saved");
        pager.setContent(TasksData.map(save));

//        return ResponseEntity.status(HttpStatus.CREATED).body(pager);
    }

    public static int count;
    public static int count1;
    public static int count2;
    public static int count3;
    public static int count4;
    public static int count5;

    public int get() {
        count = count + 30;
        return count;
    }

    public int getA() {
        count1 = count1 + 30;
        return count1;
    }

    public int get1() {
        count2 = count2 + 40;
        return count2;
    }

    public int get1A() {
        count3 = count3 + 40;
        return count3;
    }

    public int get2() {
        count4 = count4 + 50;
        return count4;
    }

    public int get2A() {
        count5 = count5 + 50;
        return count5;
    }

    @Scheduled(initialDelay = 40000, fixedRate = 40000)
    @PostMapping("stop")
    public void stopTask() throws ParseException {

        Pageable pageable = null;
        Tasks tasks = new Tasks();

        Page<TasksData> rs = tasksService.findLatestRecord(pageable).map(a -> TasksData.map(a));

        int high = 0;
        for (TasksData d : rs.getContent()) {
            high = d.getRunningServers();
        }
        //servers number
        Random ra = new Random();
        int low = 5;

        int result = ra.nextInt((high - low) + 1) + low;
        int running = 0;

        //color
        int randNum = ra.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", randNum);

        String myTime = "12:00:00";
        String myTime1 = "10:00:00";

        for (TasksData d : rs.getContent()) {
            running = d.getRunningServers() - result;
            System.out.println("running is " + running);
        }

        //program time
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");

        LocalTime pt = LocalTime.parse("12:00:00").plus(get1(), ChronoUnit.SECONDS);
        LocalTime at = LocalTime.parse("10:00:00").plus(get1A(), ChronoUnit.SECONDS);

        tasks.setEvent(Event.STOP);
        tasks.setServers(result);
        tasks.setRunningServers(running);
        tasks.setColor(colorCode);
        tasks.setMessage("Stop " + result + " servers");
        tasks.setProgramTime(pt.toString());
        tasks.setActualTime(at.toString());

        Tasks save = tasksService.saveTasks(tasks);

        System.out.println("task is " + save);
        Pager<TasksData> pager = new Pager();
        pager.setCode("0");
        pager.setMessage("Stop task details have successfully been saved");
        pager.setContent(TasksData.map(save));

//        return ResponseEntity.status(HttpStatus.CREATED).body(pager);
    }

    @Scheduled(initialDelay = 50000, fixedRate = 50000)
    @PostMapping("report")
    public void reportTask() throws ParseException {

        Pageable pageable = null;
        Tasks tasks = new Tasks();

        Page<TasksData> rs = tasksService.findLatestRecord(pageable).map(a -> TasksData.map(a));

        Random ra = new Random();

        int running = 0;

        //color
        int randNum = ra.nextInt(0xffffff + 1);
        String colorCode = String.format("#%06x", randNum);

        for (TasksData d : rs.getContent()) {
            running = d.getRunningServers();
            System.out.println("running is " + running);
        }

        //program time
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss");

        LocalTime pt = LocalTime.parse("12:00:00").plus(get2(), ChronoUnit.SECONDS);
        LocalTime at = LocalTime.parse("10:00:00").plus(get2A(), ChronoUnit.SECONDS);

        tasks.setEvent(Event.REPORT);
        tasks.setServers(running);
        tasks.setRunningServers(running);
        tasks.setColor(colorCode);
        tasks.setMessage("Report " + running + " servers running");
        tasks.setProgramTime(pt.toString());
        tasks.setActualTime(at.toString());

        Tasks save = tasksService.saveTasks(tasks);

        System.out.println("task is " + save);
        Pager<TasksData> pager = new Pager();
        pager.setCode("0");
        pager.setMessage("Start task details have successfully been saved");
        pager.setContent(TasksData.map(save));

//        return ResponseEntity.status(HttpStatus.CREATED).body(pager);
    }

    @GetMapping("task-report")
    public @ResponseBody
    ResponseEntity<?> fetchAllTasks(Pageable pageable) {
        Page<TasksData> results = tasksService.fetchAllTasks(pageable).map(a -> TasksData.map(a));

        Pager pager = new Pager();
        pager.setCode("200");
        pager.setContent(results.getContent());
        pager.setMessage("Tasks report fetched successfully");
        PageDetails details = new PageDetails();
        details.setPage(1);
        details.setPerPage(10);
        details.setReportName("Tasks report fetched");
        details.setTotalElements(results.getTotalElements());
        details.setTotalPage(results.getTotalPages());
        pager.setPageDetails(details);
        return ResponseEntity.ok(pager);

    }

    @GetMapping("due-report")
    public @ResponseBody
    ResponseEntity<?> fetchLatestTask() {
        Tasks results = tasksService.findLatestRecord();

        Pager pager = new Pager();
        pager.setCode("200");
        pager.setContent(results);
        pager.setMessage("Latest report fetched successfully");
        PageDetails details = new PageDetails();
        details.setPage(1);
        details.setPerPage(10);
        details.setReportName("Latest report fetched");
//        details.setTotalElements(results.getTotalElements());
//        details.setTotalPage(results.getTotalPages());
        pager.setPageDetails(details);
        return ResponseEntity.ok(pager);

    }
}
