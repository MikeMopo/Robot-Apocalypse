package com.arobot.assignment.dao;

import com.arobot.assignment.entity.Survivor;

import java.util.List;
import java.util.Optional;

public interface ApocalypseSurvivorDao {
    public Survivor savePerson(Survivor survivor);

    public Survivor editSurvivor(Survivor survivor);

    public Optional<Survivor> findById(Long id) throws Exception;

    public List<Survivor> findAllSurvivors();

}
