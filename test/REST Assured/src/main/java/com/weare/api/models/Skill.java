package com.weare.api.models;

import com.github.javafaker.Faker;


public class Skill {
    private Integer id;
    private String name;
    private Faker faker = new Faker();

    public Skill() {
        this.id = getId();
        this.name = getRandomSkillName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getRandomSkillName() {

        return faker.name().firstName();
    }
}