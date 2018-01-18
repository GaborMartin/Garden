public class Garden {
    private String name;
    private Player owner;
    private Plant[] plants;
    private Animal[] animals;
    private Tool[] tools;

    public Garden(String name, Player owner){
        this.name = name;
        fruits = new Fruit[0];
        vegetables = new Vegetable[0];
        animals = new Animal[0];
        tools = new Tool[0];
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public Player getOwner() {
        return owner;
    }

    public Vegetable[] getPlant() {
        return plants;
    }
    
    public Animal[] getAnimals() {
        return animals;
    }

    public Tool[] getTools() {
        return tools;
    }
}