import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Garden garden;

    public static void main(String[] args) {
        System.out.println("Welcome in garden simulation! First of all, please create your profile and garden!\n");
        Player player = Player.createPlayer();
        System.out.println("\nYour player's details:");
        System.out.println(player);
        garden = Garden.createGarden(player);
        System.out.println(garden.getCreatures().length);
        System.out.println("\nYour garden's details:");
        System.out.println(garden + "\n");
        System.out.println("Available commands: :list, :create, :find, :types, :exit");
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
                //handleFind();
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

        System.out.println("\nThese creatures are in your garden: ");
        if (garden.getCreatures().length == 0) {
            System.out.println("\tThere's no any animal or plant in the garden yet!");
        } else {
            for (int i = 0; i < garden.getCreatures().length; i++) {
                System.out.println("\t" + garden.getCreatures()[i].getName());
            }
        }
    }
    private static void handleCreate() {
        System.out.println("What is the type of the creature?");
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

    private static void handleMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("Available commands: :list, :create, :find, :types, :exit");
    }
}