package com.covidtracker.covidtrackermachinecoding.service;

import com.covidtracker.covidtrackermachinecoding.entity.AppUser;
import com.covidtracker.covidtrackermachinecoding.entity.AssessmentInformation;
import com.covidtracker.covidtrackermachinecoding.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CovidService {
    @Autowired
    private UserRepository repository;

    public Object createNewUser(AppUser user) {
        AppUser currentUser = repository.getUsers().get(user.getUsername());
        if(currentUser != null) {
            log.warn("Username already exists: {}", currentUser);
        } else {
            repository.getUsers().put(user.getUsername(), user);
        }
        return repository.getUsers().get(user.getUsername());
    }

    public Object login(String username, String password) {
        AppUser currentUser = repository.getUsers().get(username);
        if(repository.getCurrentLoggedUser() != null){
            log.info("Current user logged in to the system: {}, logging out!", repository.getCurrentLoggedUser());
        }
        if(currentUser != null) {
            if(currentUser.getPassword().equals(password))
                log.info("Login successful, Welcome: {}", username);
            else
                log.error("Invalid credentials, please check password");
        } else {
            log.error("No user exists for username: {} Please register", username);
        }
        return "Login processed";
    }

    public Object assessCurrentUser(AssessmentInformation info) {
        String currentUser = repository.getCurrentLoggedUser();
        AppUser user = repository.getUsers().get(currentUser);
        Boolean visitedCovidRedZone = repository.getRedZone().contains(info.getZoneName());
        int affectedChance = 0;
        if(currentUser != null) {
            log.info("Assessing user: {}", user);
            if(info.getContactedACovidPatient() && !info.getIsVaccinated() && visitedCovidRedZone) {
                affectedChance = 100;
            } else if ((info.getContactedACovidPatient() && !info.getIsVaccinated()) || (info.getIsVaccinated() && visitedCovidRedZone)) {
                affectedChance = 50;
            } else if (info.getContactedACovidPatient() && visitedCovidRedZone) {
                affectedChance = 75;
            }
        }
        return affectedChance;
    }
}
