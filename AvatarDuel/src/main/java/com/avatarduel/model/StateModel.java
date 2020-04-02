package com.avatarduel.model;

// State Pattern
public class StateModel {
    private static int phase;
    private static int turn;
    private static int draw;
    private static int land;
    private static int target;
    private static int targetAttack;

    public static void init() {
        phase = 0;
        turn = 1;
        draw = 1;
        land = 1;
        target = -1;
        targetAttack = -1;
    }

    public static int getPhase() {
        return phase;
    }

    public static void setPhase(int value) {
        phase = value;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int value) {
        turn = value;
    }

    public static int getDraw() {
        return draw;
    }

    public static void setDraw(int value) {
        draw = value;
    }

    public static int getLand() {
        return land;
    }

    public static void setLand(int value) {
        land = value;
    }

    public static int getTarget() {
        return target;
    }

    public static void setTarget(int value) {
        target = value;
    }

    public static int getTargetAttack() {
        return targetAttack;
    }

    public static void setTargetAttack(int value) {
        targetAttack = value;
    }
}
