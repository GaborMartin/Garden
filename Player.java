import java.util.Scanner;

public class Player {
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

    public static Player createPlayer() {
        System.out.println("Type in your player name: ");
        String playerName = sc.nextLine();
        System.out.println("Type in your gender: ");
        String playerGender = sc.nextLine();
        System.out.println("Type in your age: ");
        int playerAge = sc.nextInt();
        System.out.println("Type in your energylevel: ");
        int playerEnergy = sc.nextInt();
        Player player = new Player(playerName, playerGender, playerAge, playerEnergy);
        return player;
    }
    public String toString() {
        return "Name: " + name +","
            + " Gender: " + gender +","
            + " Age: " + age +","
            + " Energylevel: " + energy;
    }
}