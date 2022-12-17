package com.arobot.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "survivor")
public class Survivor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (name = "firstname")
    private String firstname;
    @Column(name="surname")
    private String surname;
    @Column(name= "age")
    private Integer age;
    @Column(name="gender")
    private String gender;

    private Set <String> location;
    private Set<String> resources = new HashSet<>();
    @Column(name = "infected",nullable = false)
    private boolean infected;


    public Survivor(String firstname, String surname, Integer age,
                    String gender, Set<String> location,
                    Set<String> resources, boolean infected) {
        super();
        this.firstname = firstname;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.resources = resources;
        this.infected = infected;
    }
}
