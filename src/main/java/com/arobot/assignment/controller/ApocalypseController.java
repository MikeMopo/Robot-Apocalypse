package com.arobot.assignment.controller;

import com.arobot.assignment.exception.SurvivorNotFoundException;
import com.arobot.assignment.entity.Robot;
import com.arobot.assignment.entity.Survivor;
import com.arobot.assignment.service.impl.SurvivorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApocalypseController {

    @Autowired
    private SurvivorServiceImpl survivorService;

    @PostMapping("/newsurvivor")
    public ResponseEntity<Survivor> registerSurvivor(@RequestBody Survivor survivor) {
        Survivor savedSurvivor = survivorService.registerSurvivor(survivor);
        return new ResponseEntity<>(savedSurvivor, HttpStatus.CREATED);
    }


    @GetMapping("/get/decepticons")
    public ResponseEntity findAllRobotsAndDetails(){
        List<Robot> robots = survivorService.findAllRobotsAndDetails();
        return new ResponseEntity<>(robots, HttpStatus.OK);
    }

    @GetMapping("/get/survivors")
    public ResponseEntity findAllSurvivors(){
        List<Survivor> survivor = survivorService.getAllSurvivor();
        return new ResponseEntity<>(survivor, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Survivor> getSurvivor(@PathVariable ("id") Long id) throws Exception{
        Survivor survivor = survivorService.getSurvivor(id);
        if(survivor == null)
            throw new SurvivorNotFoundException();
        return new ResponseEntity<>(survivor, HttpStatus.OK);
    }

    @PutMapping("/infected/{id}")
    public ResponseEntity<Survivor> reportSurvivorAsInfected(@PathVariable("id") Long id) throws Exception{
        Survivor infected = survivorService.reportSurvivorAsInfected(id);
        if(infected == null)
            throw new SurvivorNotFoundException();
        return new ResponseEntity<>(infected, HttpStatus.OK);
    }

    @PutMapping("/notinfected/{id}")
    public ResponseEntity<Survivor> reportSurvivorAsNotInfected(@PathVariable("id") Long id) throws Exception{
        Survivor notInfected = survivorService.reportSurvivorAsNotInfected(id);
        if(notInfected == null)
            throw new SurvivorNotFoundException();
        return new ResponseEntity<>(notInfected, HttpStatus.OK);
    }

    @PutMapping("/location/{id}")
    public ResponseEntity<Survivor> updateSurvivorLocation(
            @PathVariable("id")Long id,
            @RequestBody Survivor survivor) throws Exception{

                Survivor geoLocation = survivorService.updateSurvivorLocation(id, survivor);
                    if(geoLocation==null)
                         throw new SurvivorNotFoundException();
                    return new ResponseEntity<>(geoLocation, HttpStatus.OK);
    }

    @GetMapping("/ratio")
    public ResponseEntity<Map<String,String>> findInfectionRatio(){
        Map<String, String> survivalRatio = survivorService.findInfectionRatio();
        return new ResponseEntity<>(survivalRatio, HttpStatus.OK);
    }


    @GetMapping("/notinfected")
    public ResponseEntity<List<Survivor>> negativeInfectiontestResult() {
        List<Survivor> negative = survivorService.negativeInfectionTestResult();
        return new ResponseEntity<>(negative, HttpStatus.OK);
    }

    @GetMapping("/infected")
    public ResponseEntity<List<Survivor>> findAllNonInfectedNonSurvivers() {
        List<Survivor> positive = survivorService.positiveInfectionTestResult();
        return new ResponseEntity<>(positive, HttpStatus.OK);
    }
}
