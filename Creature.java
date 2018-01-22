public class Creature implements java.io.Serializable {
    private String name;
    private CreatureType type;
    private int availability;

    public Creature(String name, CreatureType type){
        this.name = name;
        this.type = type;
        this.availability = 0;
    }

    public String getName(){
        return name;
    }

    public CreatureType getType(){
        return type;
    }

    public int getAvailability(){
        return availability;
    }
}
