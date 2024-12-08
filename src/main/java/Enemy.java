public class Enemy {
    private String name;
    private int healthPoints;
    private int attackPower;
    private int goldCoinValue;

    public Enemy(String name, int healthPoints, int attackPower) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.goldCoinValue = (healthPoints + attackPower) / 10;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void takeDamage(int damage) {
        this.healthPoints -= damage;
    }

    public int attack() {
        return attackPower;
    }

    public String getName() {
        return name;
    }

    public int getGoldCoinValue(){
        return goldCoinValue;
    }
}
