package com.arobot.assignment.service.impl;

import com.arobot.assignment.dao.ApocalypseSurvivorDao;
import com.arobot.assignment.entity.Robot;
import com.arobot.assignment.entity.Survivor;
import com.arobot.assignment.repository.SurvivorRepository;
import com.arobot.assignment.service.ISurvivorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SurvivorServiceImpl implements ISurvivorService {

    @Autowired
    private SurvivorRepository survivorRepository;
    @Autowired
    private RobotService robotService;
    @Autowired
    private ApocalypseSurvivorDao apocalypseSurvivorDao;


    @Override
    public Survivor registerSurvivor(Survivor survivor) {
        return survivorRepository.save(survivor);
    }

    @Override
    public List<Survivor> getAllSurvivor() {
        return survivorRepository.findAll();
    }


    @Override
    public List<Robot> findAllRobotsAndDetails() {
        List<Robot> robotDetails = robotService.findAllRobotsAndDetails();
        return robotDetails;
    }

    @Override
    public Survivor getSurvivor(Long id) {
        Optional<Survivor> survivorNow = survivorRepository.findById(id);
        Survivor alive = null;
        if(survivorNow.isPresent()){
            alive = survivorNow.get();
        }
        return alive;
    }

    @Override
    public Survivor reportSurvivorAsInfected(Long id) {
        Optional<Survivor> survivorNow  = survivorRepository.findById(id);
        Survivor infected = null;
        if(survivorNow.isPresent()){
            infected = survivorNow.get();
            infected.setInfected(true);
            return apocalypseSurvivorDao.editSurvivor(infected);}
        return infected;
    }

    @Override
    public Survivor reportSurvivorAsNotInfected(Long id) {
        Optional<Survivor> survivorNow  = survivorRepository.findById(id);
        Survivor infected = null;
        if(survivorNow.isPresent()){
            infected = survivorNow.get();
            infected.setInfected(false);
            return apocalypseSurvivorDao.editSurvivor(infected);}
        return infected;
    }

    @Override
    public Survivor updateSurvivorLocation(Long id, Survivor survivor) {
        Optional<Survivor> survivorNow = survivorRepository.findById(id);
        Survivor survivorLocation = null;
        if(survivorNow.isPresent()) {
            survivorLocation = survivorNow.get();
            survivorLocation.setLocation(survivor.getLocation());

          return apocalypseSurvivorDao.editSurvivor(survivorLocation);
        }
        return survivorLocation;
    }


    @Override
    public Map<String, String> findInfectionRatio() {
        Long positiveInfectionTest;
        Long negativeInfectionTest;
        Long population;

        Map<String,String> ratioTest = new HashMap<>();
        List<Survivor> allSurvivors = apocalypseSurvivorDao.findAllSurvivors();

        if(allSurvivors.isEmpty()==false){
            population = allSurvivors.stream().count();
            positiveInfectionTest = allSurvivors.stream().filter(survivor -> survivor.isInfected()).count();
            negativeInfectionTest = population - positiveInfectionTest;

            ratioTest.put("Positive Test Cases For Survivors",
                    String.valueOf((positiveInfectionTest/population)*100)+"%");
            ratioTest.put("Negative Test Cases For Survivors",
                    String.valueOf((negativeInfectionTest/population)*100)+"%");

        }

        return ratioTest;
    }

    @Override
    public List<Survivor> negativeInfectionTestResult() {
        List<Survivor> survivors = apocalypseSurvivorDao.findAllSurvivors();
        List<Survivor> notInfected = null;
        if (survivors.isEmpty()==false){
            notInfected = survivors
                    .stream()
                    .filter(survivor -> !survivor.isInfected())
                    .collect(Collectors.toList());
        }
        return notInfected;
    }
    @Override
    public List<Survivor> positiveInfectionTestResult() {
        List<Survivor> survivors = apocalypseSurvivorDao.findAllSurvivors();
        List<Survivor> Infected = null;
        if (survivors.isEmpty()==false){
            Infected = survivors
                    .stream()
                    .filter(survivor -> survivor.isInfected())
                    .collect(Collectors.toList());
        }
        return Infected;
    }





}
