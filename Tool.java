public class Tool {
    private String name;
    private String material;
    private String usability;
    private int durability;

    public Tool(String name; String material; String usability){
        this.name = name;
        this.material = material;
        this.usability = usability;
        this.durability = 10;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }
    
    public String getUsability() {
        return usability;
    }

    public String getDurability() {
        return durability;
    }

    public void use() {
        durability--;
    }
}