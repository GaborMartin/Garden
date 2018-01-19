import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome in garden simulation! First of all, please create your profile and garden!");
        Player player = Player.createPlayer();
        Garden garden = new Garden("Kertecske", player);
        System.out.println("Your player's details: ");
        System.out.println(player);


    }
}