package com.weare.api.models;

import com.github.javafaker.Faker;
import lombok.Data;

    @Data
public class Skill extends BaseModel {
    private Integer id;
    private String name;

    public Skill() {
        setRandomSkillName();
    }

    private void setRandomSkillName() {
        name = faker.name().firstName();
    }
}