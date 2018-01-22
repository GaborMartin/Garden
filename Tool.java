public class Tool implements java.io.Serializable {
    private String name;
    private String material;
    private int durability;

    public Tool(String name, String material) {
        this.name = name;
        this.material = material;
        this.durability = 10;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    public int getDurability() {
        return durability;
    }

    public void use() {
        durability--;
    }
}