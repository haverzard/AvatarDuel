package com.avatarduel.model;

import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldModel {
    private static Map<Pane, List<Integer>> characterSkillList;
    private static Map<Pane, Pair<Integer,Integer>> skillInfo;

    public static void init() {
        characterSkillList = new HashMap<>();
        skillInfo = new HashMap<>();
    }

    public static Map<Pane, List<Integer>> getCharacterSkillList() {
        return characterSkillList;
    }

    public static Map<Pane, Pair<Integer, Integer>> getSkillInfo() {
        return skillInfo;
    }
}
