public class Creature {
    private String name;
    private String type;
    private int availability;

    public Creature(String name, String type , int availability){
        this.name = name;
        this.type = type;
        this.availability = 0;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public int getAvailability(){
        return availability;
    }
}
