package com.weare.api.Services;

import com.weare.api.Models.Skill;
import com.weare.api.Utils.Constants;
import com.weare.api.Utils.JSONRequests;

public class SkillService {
    private SkillService() {}
    public static String generateCreateSkill(Skill skill) {
        return String.format(JSONRequests.CREATE_SKILL,
                Constants.CATEGORY_ID, Constants.CATEGORY_NAME, skill.getName());
    }
}
