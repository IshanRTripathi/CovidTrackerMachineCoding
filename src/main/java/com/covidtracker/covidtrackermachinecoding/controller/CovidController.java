package com.covidtracker.covidtrackermachinecoding.controller;

import com.covidtracker.covidtrackermachinecoding.entity.AppUser;
import com.covidtracker.covidtrackermachinecoding.entity.AssessmentInformation;
import com.covidtracker.covidtrackermachinecoding.service.CovidService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/covid-tracker")
public class CovidController {

    @Autowired
    private CovidService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AppUser user){
        log.info("Request received to create a new user: {}", user);
        return ResponseEntity.ok().body(service.createNewUser(user));
    }

    @PostMapping("/login?username={username}&password={password}")
    public ResponseEntity login(@PathVariable String username, @PathVariable String password) {
        log.info("Request received to login for username: {}", username);
        return ResponseEntity.ok().body(service.login(username, password));
    }

    @PostMapping("/assess")
    public ResponseEntity assesUser(@RequestBody AssessmentInformation assessmentInformation) {
        log.info("Assessing current patient with info: {}", assessmentInformation);
        return ResponseEntity.ok().body(service.assessCurrentUser(assessmentInformation));
    }

}
