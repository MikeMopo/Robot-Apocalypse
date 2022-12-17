package com.arobot.assignment.dao;

import com.arobot.assignment.entity.Survivor;
import com.arobot.assignment.repository.SurvivorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class ApocalypseSurvivorDaoImpl implements ApocalypseSurvivorDao{

    @Autowired
    private SurvivorRepository survivorRepository;

    @Override
    public Survivor savePerson(Survivor survivor) {
        Set<String> resources = new HashSet<>();
        resources.addAll(survivor.getResources());
        return survivorRepository.save(new Survivor(
                        survivor.getFirstname(),
                        survivor.getSurname(),
                        survivor.getAge(),
                        survivor.getGender(),
                        survivor.getLocation(), resources, false));
    }

    @Override
    public Survivor editSurvivor(Survivor survivor) {
        return survivorRepository.save(survivor);
    }

    @Override
    public Optional<Survivor> findById(Long id) throws Exception {
        return survivorRepository.findById(id);
    }

    @Override
    public List<Survivor> findAllSurvivors() {
        return survivorRepository.findAll();
    }
}
