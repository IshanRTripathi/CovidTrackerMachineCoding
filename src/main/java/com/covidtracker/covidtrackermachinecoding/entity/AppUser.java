package com.covidtracker.covidtrackermachinecoding.entity;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AppUser {
    UserType userType;
    String username;
    String password;
    String zone;
}
