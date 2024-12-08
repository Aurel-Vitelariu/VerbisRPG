import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Shopkeeper {
    private final List<Item> items;

    public Shopkeeper(int difficultyLevel) {
        this.items = generateItems(difficultyLevel);
    }

    private List<Item> generateItems(int difficultyLevel) {
        List<Item> items = new ArrayList<>();
        Random random = new Random();

        // Randomized Health Potion
        int healthEffect = 15 + random.nextInt(10) + (difficultyLevel * 5);
        int healthCost = 5 + random.nextInt(5) + (difficultyLevel * 2);
        items.add(new Item("Health Potion", healthEffect, healthCost));

        // Randomized Strength Elixir
        int strengthEffect = 5 + random.nextInt(10) + (difficultyLevel * 5);
        int strengthCost = 5 + random.nextInt(5) + (difficultyLevel * 2);
        items.add(new Item("Strength Elixir", strengthEffect, strengthCost));


        return items;
    }

    public void showShop(Player player) {
        System.out.println("\nWelcome to the shop! Here are the items available:");
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println((i + 1) + " - " + item.getName() + " (Effect: +"
                    + item.getEffect() + ", Cost: " + item.getCost() + " gold)");
        }
        System.out.println((items.size() + 1) + " - Leave Shop");

        Scanner scanner = new Scanner(System.in);
        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= items.size() + 1) {
                    break;
                }
                System.out.println("Invalid choice. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }

        if (choice <= items.size()) {
            Item selectedItem = items.get(choice - 1);
            if (player.getGoldCoins() >= selectedItem.getCost()) {
                player.spendGoldCoins(selectedItem.getCost());
                if (selectedItem.getName().contains("Health")) {
                    player.heal(selectedItem.getEffect());
                } else if (selectedItem.getName().contains("Strength")) {
                    player.increaseAttackPower(selectedItem.getEffect());
                }
                System.out.println("You purchased " + selectedItem.getName() + "!");
            } else {
                System.out.println("You don't have enough gold.");
            }
        } else {
            System.out.println("You leave the shop.");
        }
    }
}
