package com.avatarduel.controller;

import com.avatarduel.model.StateModel;

// Context for State
public class StateController {

    public static void nextTurn() {
        int temp = StateModel.getTurn();
        StateModel.init();
        StateModel.setTurn(temp%2 + 1);
    }

    public static void nextPhase() {
        StateModel.setPhase(StateModel.getPhase()+1);
    }

    public static void updateLand(int change) {
        StateModel.setLand(StateModel.getLand()+change);
    }

    public static boolean updateTarget(int newTarget) {
        if  (StateModel.getTarget() != newTarget) {
            StateModel.setTarget(newTarget);
            return true;
        }
        return false;
    }

    public static boolean updateTargetAttack(int newTarget) {
        if  (StateModel.getTargetAttack() != newTarget) {
            StateModel.setTargetAttack(newTarget);
            return true;
        }
        return false;
    }
    
    public static boolean updateTargetSkill(int newTarget) {
        if  (StateModel.getTargetSkill() != newTarget) {
            StateModel.setTargetSkill(newTarget);
            return true;
        }
        return false;
    }

    public static boolean checkState(String state) {
        switch (state) {
            case "Use land":
                return StateModel.getLand() > 0 && (StateModel.getPhase() == 1 || StateModel.getPhase() == 3);
            case "Card selected":
                return StateModel.getTarget() != -1;
            case "Attack card selected":
                return StateModel.getTargetAttack() != -1;
            case "Skill card selected":
                return StateModel.getTargetSkill() != -1;
            case "Main phase":
                return StateModel.getPhase() == 1 || StateModel.getPhase() == 3;
            case "Battle phase":
                return StateModel.getPhase() == 2;
            case "Draw phase":
                return StateModel.getDraw() > 0 && StateModel.getPhase() == 0;
            case "Player 1 turn":
                return StateModel.getTurn() == 1;
        }
        return false;
    }

    public static void updateState(String state) {
        switch (state) {
            case "Use land":
                updateLand(-1);
                updateTarget(-1);
                break;
            case "Draw card":
                StateModel.setDraw(0);
                break;
            case "Release attack card":
                StateModel.setTargetAttack(-1);
                break;
            case "Release skill card":
                StateModel.setTargetSkill(-1);
                break;
        }
    }
}
