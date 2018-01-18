public class Player {
    String name;
    String gender;
    int age;
    int energy;

    public Player(String name, String gender, int age, int energy;){
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
}