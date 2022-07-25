package com.covidtracker.covidtrackermachinecoding.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.covidtracker.covidtrackermachinecoding.entity.AppUser;

import lombok.Getter;

import org.springframework.stereotype.Component;

@Component
@Getter
public class UserRepository {
    String currentLoggedUser;
    Map<String, AppUser> users;
    Set<String> redZone;
    Set<String> orangeZone;
    Set<String> greenZone;
    UserRepository() {
        currentLoggedUser = null;
        users = new HashMap<>();
        redZone = new HashSet<>();
        redZone.addAll(List.of("a,b,c,d,e"));
        orangeZone = new HashSet<>();
        orangeZone.addAll(List.of("f,g,h,i,j"));
        greenZone = new HashSet<>();
        greenZone.addAll(List.of("k,l,m,n"));
    }
}
