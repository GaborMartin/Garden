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
        System.out.println("Available commands: :list, :create, :creature interact, :find, :types, :save progress, :exit");
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
            } else if (":creature interact".equals(line)) {
                handleCreatureInteract();
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
        List<String> fridge = garden.getFridge();

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

        System.out.println("\n These meats are in your fridge:\n");
        if (fridge.size() == 0) {
            System.out.println("\tThere's no any meat in the fridge yet!");
        } else {
            for (int i = 0; i < fridge.size(); i++) {
                System.out.println("\t" + fridge.get(i));
            }
        }
    }

    private static void handleCreate() {
        System.out.println("What is the type of the creature? (ANIMAL / PLANT)");
        String typeAsString = scanner.nextLine();
        typeAsString.toUpperCase();
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
            }
        }
    }

    private static void handleMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("Available commands: :list, :create, :creature interact, :find, :types, :save progress, :exit");
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

    private static void handleCreatureInteract() {
        System.out.println("Choose a creature! (ANIMAL to feed animals, PLANT to water plants)");
        Creature[] creatures = garden.getCreatures();
        Tool wateringCan = garden.findTool("Watering can");
        String typeAsString = scanner.nextLine();
        Player player = garden.getOwner();
        if (creatures.length == 0) {
            System.out.println("There's no creature to interact");
        } else {
            for (int i = 0; i < creatures.length; i++) {
                if (typeAsString.toUpperCase().equals("PLANT") && creatures[i].getType().equals(CreatureType.valueOf("PLANT"))) {
                    System.out.println("Plant to water: " + creatures[i].getName() + " (availability: " + creatures[i].getAvailability() + ")");
                    System.out.println("\nType in the plant's name to water it!");
                    String plantNameAsString = scanner.nextLine();
                    if (plantNameAsString.equals(creatures[i].getName())) {
                        creatures[i].toIncreaseAvailability();
                        player.setEnergy(10);
                        System.out.println("\n" + creatures[i].getName() + "'s availability increased to: " + creatures[i].getAvailability());
                        System.out.println("Your energy has decreased to: " + garden.getOwner().getEnergy());
                    }
                }
                else if (typeAsString.toUpperCase().equals("ANIMAL") && creatures[i].getType().equals(CreatureType.valueOf("ANIMAL"))) {
                    System.out.println("Animal to feed: " + creatures[i].getName() + " (Availability: " + creatures[i].getAvailability() + ")");
                    System.out.println("\nType in the animal's name to feed it!");
                    String animalNameAsString = scanner.nextLine();
                    if (animalNameAsString.equals(creatures[i].getName())) {
                        creatures[i].toIncreaseAvailability();
                        wateringCan.use();
                        player.setEnergy(10);
                        System.out.println("\n" + creatures[i].getName() + "'s availability increased to: " + creatures[i].getAvailability());
                        System.out.println("Your energy has decreased to: " + garden.getOwner().getEnergy());
                        if (creatures[i].getAvailability() == 10) {
                            System.out.println("\n" + creatures[i].getName() + "'s availability increased to: " + creatures[i].getAvailability());
                            System.out.println("Your energy has decreased to: " + garden.getOwner().getEnergy());
                            System.out.println("Your animal is ready for slaughtering! Type in it's name to slaughter!");
                            animalNameAsString = scanner.nextLine();
                            if (animalNameAsString.equals(creatures[i].getName())) {
                                garden.useAnimal((Animal)creatures[i]);
                                player.setEnergy(30);
                                System.out.println(creatures[i].getName() + " is slaughtered!");
                                garden.removeCreatureFromArray(creatures[i]);
                            } else {
                            System.out.println("\n" + creatures[i].getName() + "'s availability increased to: " + creatures[i].getAvailability());
                            System.out.println("Your energy has decreased to: " + garden.getOwner().getEnergy());
                            }
                        }
                    }
                }
            }
        }
    }
}
                         
                        