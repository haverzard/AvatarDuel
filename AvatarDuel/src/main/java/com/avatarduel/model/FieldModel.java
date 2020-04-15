package com.avatarduel.model;

import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldModel {
    private Map<Pane, List<Integer>> characterSkillList;
    private Map<Pane, Pair<Integer,Integer>> skillInfo;

    public FieldModel() {
        characterSkillList = new HashMap<>();
        skillInfo = new HashMap<>();
    }

    public Map<Pane, List<Integer>> getCharacterSkillList() {
        return characterSkillList;
    }

    public Map<Pane, Pair<Integer, Integer>> getSkillInfo() {
        return skillInfo;
    }
}
