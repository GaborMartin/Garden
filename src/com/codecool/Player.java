package com.codecool;

import java.util.Scanner;

public class Player implements java.io.Serializable {
    String name;
    String gender;
    int age;
    int energy;
    private static Scanner sc = new Scanner(System.in);

    public Player(String name, String gender, int age, int energy){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.energy = energy;
    }

    public String getName(){
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energylevel) {
        this.energy -= energylevel;
    }

    public static Player createPlayer() {
        Player player = null;
        try {
            System.out.println("Type in your player name: ");
            String playerName = sc.nextLine();
            System.out.println("Type in your gender: ");
            String playerGender = sc.nextLine();
            System.out.println("Type in your age: ");
            int playerAge = sc.nextInt();
            System.out.println("Type in your energylevel: ");
            int playerEnergy = sc.nextInt();
            if(playerEnergy > 100){
                playerEnergy = 100;
            }
            player = new Player(playerName, playerGender, playerAge, playerEnergy);
        } catch (Exception ex) {
            System.out.println("Invalid input!");
        }
        return player;
    }
    public String toString() {
        return "Name: " + name +","
            + " Gender: " + gender +","
            + " Age: " + age +","
            + " Energylevel: " + energy + "\n";
    }
}