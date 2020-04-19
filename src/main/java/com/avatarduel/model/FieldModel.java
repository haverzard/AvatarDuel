package com.avatarduel.model;

import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Field Model in AvatarDuel application
 */
public class FieldModel {
    private Map<Pane, List<Integer>> characterSkillList;
    private Map<Pane, Pair<Integer,Integer>> skillInfo;

    /**
     * Creates a new field model.
     */
    public FieldModel() {
        characterSkillList = new HashMap<>();
        skillInfo = new HashMap<>();
    }

    /**
     * Get the Character's skills
     * @return character's skills
     */
    public Map<Pane, List<Integer>> getCharacterSkillList() {
        return characterSkillList;
    }

    /**
     * Get character's information.
     * @return character's information
     */
    public Map<Pane, Pair<Integer, Integer>> getSkillInfo() {
        return skillInfo;
    }
}
