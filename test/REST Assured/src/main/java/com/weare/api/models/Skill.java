package com.weare.api.models;
import com.github.javafaker.Faker;

import lombok.Getter;
import lombok.Setter;
    @Getter
    @Setter
public class Skill {
    private Integer id;
    private String name;
    private Faker faker = new Faker();

    public Skill() {
        this.id = getId();
        this.name = getRandomSkillName();
    }

    private String getRandomSkillName() {
        return faker.name().firstName();
    }
}