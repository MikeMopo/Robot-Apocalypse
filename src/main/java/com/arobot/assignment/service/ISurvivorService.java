package com.arobot.assignment.service;

import com.arobot.assignment.entity.Robot;
import com.arobot.assignment.entity.Survivor;

import java.util.List;
import java.util.Map;

public interface ISurvivorService {

    Survivor registerSurvivor(Survivor survivor);
    List<Robot> findAllRobotsAndDetails();
    List<Survivor> getAllSurvivor();
    Survivor getSurvivor(Long id);
    Survivor reportSurvivorAsNotInfected(Long id);
    Survivor updateSurvivorLocation(Long id, Survivor survivor);
    Map<String, String> findInfectionRatio();
    Survivor reportSurvivorAsInfected(Long id);
    List<Survivor> negativeInfectionTestResult();
    List<Survivor> positiveInfectionTestResult();
}
