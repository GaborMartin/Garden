import java.util.Scanner;

public class Garden implements java.io.Serializable {
    private String name;
    private Player owner; 
    private Creature[] creatures;
    private Tool[] tools;
    private static Scanner sc = new Scanner(System.in);

    public Garden(String name, Player owner){
        this.name = name;
        tools = new Tool[0];
        creatures = new Creature[0];
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public Creature[] getCreatures() {
        return creatures;
    }

    public Tool[] getTools() {
        return tools;
    }

    public static Garden createGarden(Player owner) {
        Garden garden;
        System.out.println("\nType in your garden's name: ");
        String gardenName = sc.nextLine();
        garden = new Garden(gardenName, owner);
        return garden;

    }

    public String toString() {
        return "Name: " + name +","
            + " Owner: " + owner + "\n";
    }

    public Creature createCreature(CreatureType type) {
        Creature creature;
        switch (type) {
            case PLANT:
                System.out.println("What's the name of your creature?");
                String plantName = sc.nextLine();
                creature = new Plant(plantName, type);
                break;
            case ANIMAL:
                System.out.println("What's the name of your creature?");
                String animalName = sc.nextLine();
                creature = new Animal(animalName, type, true);
                break;
            default:
                throw new IllegalArgumentException("You can not add this type, try an animal or plant!");
        }
        addCreatureToArray(creature);
        return creature;
    }

    public void addCreatureToArray(Creature creature) {
        Creature[] tempArray = new Creature[creatures.length + 1];
        for (int i = 0; i < creatures.length; i++){
            tempArray[i] = creatures[i];
        }
        tempArray[tempArray.length - 1] = creature;
        creatures = tempArray;
    } 
        

    public void addTool(Tool tool) {
        Tool[] tempArray = new Tool[tools.length + 1];
        for (int i = 0; i < tools.length; i++) {
            tempArray[i] = tools[i];
        }
        tempArray[tempArray.length - 1] = tool;
        tools = tempArray;
    }

    public Creature find(String name) {
        for (Creature creature : creatures) {
            if (creature.getName().equals(name)) {
                return creature;
            }
        }
        return null;
    }

    public void exit() {
        System.out.println("See ya next time!");
    }


}
