import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameManager {
    private Player player;
    private Enemy enemy;
    private Shopkeeper shopkeeper;
    private final CombatSystem combatSystem;
    private final Scanner scanner;
    private boolean isGameRunning;
    private int defeatedEnemies;

    public GameManager() {
        this.combatSystem = new CombatSystem();
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        isGameRunning = true;
        defeatedEnemies = 0;
        System.out.println("Welcome to the VerbisRPG!");
        setupGame();

        while (isGameRunning) {
            displayStatus();

            List<MenuOption> options = generateOptions();
            int choice = getPlayerChoice(options);

            options.get(choice - 1).execute(); // Execute selected option
        }

    }

    private void setupGame() {
        player = new Player("Hero", 100, 20);
        generateEnemy(); // Start with an enemy
    }

    private void displayStatus() {
        System.out.println("\nYour health: " + player.getHealthPoints());
        System.out.println("Your gold coins: " + player.getGoldCoins());
        if (enemy != null) {
            System.out.println(enemy.getName() + "'s health: " + enemy.getHealthPoints());
        }
    }

    private List<MenuOption> generateOptions() {
        List<MenuOption> options = new ArrayList<>();

        if (enemy != null) { // Combat phase
            options.add(new MenuOption("Attack", () -> {
                combatSystem.playerAttack(player, enemy);
                if (enemy.getHealthPoints() > 0) {
                    combatSystem.enemyAttack(player, enemy);
                    if (player.getHealthPoints() < 0) endGame();
                } else {
                    System.out.println("You defeated " + enemy.getName() + "!");
                    player.earnGold(enemy.getGoldCoinValue());
                    defeatedEnemies++;
                    generateNextEncounter();
                }
            }));
        } else if (shopkeeper != null) { // Shop phase
            shopkeeper.showShop(player);
            options.add(new MenuOption("Leave Shop", this::generateNextEncounter));
        }

        options.add(new MenuOption("Quit", () -> {
            System.out.println("You quit the game.");
            isGameRunning = false;
        }));

        return options;
    }

    private int getPlayerChoice(List<MenuOption> options) {
        System.out.println("\nChoose an option:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + " - " + options.get(i).getDescription());
        }

        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= options.size()) {
                    break;
                }
                System.out.println("Invalid choice. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
        return choice;
    }

    private void generateNextEncounter() {
        Random random = new Random();
        if (random.nextInt(4) == 0) { // 25% chance for a shopkeeper
            shopkeeper = new Shopkeeper(defeatedEnemies);
            enemy = null;
        } else { // 75% chance for a new enemy
            generateEnemy();
            shopkeeper = null;
        }
    }

    private void generateEnemy() {
        String[] enemyTypes = {"Goblin", "Orc", "Skeleton"};
        Random random = new Random();
        String enemyName = enemyTypes[random.nextInt(enemyTypes.length)];

        // Exponential growth with randomness
        int healthBase = 50;
        double healthMultiplier = 1.2; // Growth rate per defeated enemy
        int healthRandomness = random.nextInt(20 + (8 * defeatedEnemies)) - 10; // Random value in range [-10, 10]
        int health = (int) (healthBase * Math.pow(healthMultiplier, defeatedEnemies)) + healthRandomness;

        int damageBase = 10;
        double damageMultiplier = 1.1; // Growth rate per defeated enemy
        int damageRandomness = random.nextInt(5 + (2 * defeatedEnemies)) - 2; // Random value in range [-2, 2]
        int damage = (int) (damageBase * Math.pow(damageMultiplier, defeatedEnemies)) + damageRandomness;

        // Ensure no negative values due to randomness
        health = Math.max(health, healthBase);
        damage = Math.max(damage, damageBase);

        enemy = new Enemy(enemyName, health, damage);
    }

    private void endGame() {
        if (player.getHealthPoints() <= 0) {
            System.out.println("You have been defeated!");
        } else {
            System.out.println("Game over.");
        }
        System.out.println("You have defeated " + defeatedEnemies + " enemies.");
        isGameRunning = false;

    }
}
