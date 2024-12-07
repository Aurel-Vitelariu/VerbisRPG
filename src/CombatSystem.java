public class CombatSystem {
    public void playerAttack(Player player, Enemy enemy) {
        int damage = player.attack();
        enemy.takeDamage(damage);
        System.out.println("You attack " + enemy.getName() + " for " + damage + " damage!");
    }

    public void enemyAttack(Player player, Enemy enemy) {
        int damage = enemy.attack();
        player.takeDamage(damage);
        System.out.println(enemy.getName() + " attacks you for " + damage + " damage!");
    }
}
