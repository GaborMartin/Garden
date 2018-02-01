package com.codecool;

public class Animal extends Creature {
    boolean isAlive;

    public Animal(String name, CreatureType type, boolean isAlive) {
        super(name, type);
        isAlive = true;
    }
    public boolean getIsAlive() {
        return isAlive;
    }

    public void setIsAlive() {
        isAlive = false;
    }


}