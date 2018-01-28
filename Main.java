import java.util.*;
import java.io.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Garden garden;

    public static void main(String[] args) {
        System.out.println("Welcome in garden simulation!\n");
        handleStart();
        System.out.println("\nYour garden's details:");
        System.out.println(garden + "\n");
        System.out.println("Available commands: :list, :create, :find, :types, :save progress, :exit");
        while (true) {
            String line = scanner.nextLine();
            if (":exit".equals(line)) {
                break;

            } else if (":list".equals(line)) {
                handleList();
                handleMenu();

            } else if (":types".equals(line)) {
                handleCreatureTypes();
                handleMenu();

            } else if (":create".equals(line)) {
                handleCreate();
                handleMenu();

            } else if (":find".equals(line)) {
                handleFindCreature();
                handleMenu();

            } else if (":save progress".equals(line)) {
                handleSave();
                handleMenu();
            } else if (":load".equals(line)) {
                handleLoad();
                handleMenu();
            }

            }
        garden.exit();
        }

    private static void handleCreatureTypes() {
        System.out.println("The available creatures in the garden: ");
        for (CreatureType type : CreatureType.values()) {
            System.out.println("\t" + type);
        }
    }

    private static void handleList() {
        Creature[] creatures = garden.getCreatures();
        Tool[] tools = garden.getTools();

        System.out.println("\nThese creatures are in your garden:\n");
        if (creatures.length == 0) {
            System.out.println("\tThere's no any animal or plant in the garden yet!");
        } else {
            for (int i = 0; i < creatures.length; i++) {
                System.out.println("\t" + creatures[i]);
            }
        }
        System.out.println("\nThese tools are in your garden:\n");
            if (tools.length == 0) {
                System.out.println("\tThere's no any tool in the garden yet!");
            } else {
                for (int i = 0; i < tools.length; i++) {
                    System.out.println("\t" + tools[i]);
                }
            }
        }

    private static void handleCreate() {
        System.out.println("What is the type of the creature? (ANIMAL / PLANT)");
        String typeAsString = scanner.nextLine();
        CreatureType type;
        try {
            type = CreatureType.valueOf(typeAsString);
        } catch (IllegalArgumentException ex) {
            System.out.println("Creation of this creature is forbidden!" + typeAsString + "\n Try ANIMAL or PLANT!");
            return;
        }
        garden.createCreature(type);
        System.out.println("Creation is done!");
    }

    private static void handleFindCreature() {
        Creature[] creatures = garden.getCreatures();
        System.out.println("Type in the name of the creature you're looking for!");
        String nameAsString = scanner.nextLine();
        for (int i = 0; i < creatures.length; i++) {
            if (creatures[i].getName().equals(nameAsString)) {
                System.out.println("Creature is found!");
                System.out.println("\nDetails of the creature:" + "\n" + "Name: " + creatures[i].getName() + ", Type: " + creatures[i].getType() + ", Availability: " + creatures[i].getAvailability());
            } else {
                System.out.println("This creature does not exist! Are you sure about the name?");
            }
        }
    }

    private static void handleMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("Available commands: :list, :create, :find, :types, :save progress, :exit");
    }

    private static void handleSave() {
        try {
         FileOutputStream fileOut =
         new FileOutputStream("progress.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(garden);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in progress.ser");
      } catch (IOException i) {
         i.printStackTrace();
      }
    }

    private static void handleLoad() {
        garden = null;
      try {
         FileInputStream fileIn = new FileInputStream("progress.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         garden = (Garden) in.readObject();
         in.close();
         fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return;
      } catch (ClassNotFoundException c) {
         System.out.println("Garden class not found");
         c.printStackTrace();
         return;
      }
      
      System.out.println("Your previous progress is loaded...");
    }

    private static void handleStart() {
        System.out.println("Would you like to load your previos progress? (y / n)");
        String answer = scanner.nextLine();
        if (answer.toLowerCase().equals("y")) {
            handleLoad();
        } else if (answer.toLowerCase().equals("n")) {
            System.out.println("First of all create your player and garden");
            Player player = Player.createPlayer();
            System.out.println("\nYour player's details:");
            System.out.println(player);
            garden = Garden.createGarden(player);
            garden.uploadTools("Tools.csv");
        }
    }
}