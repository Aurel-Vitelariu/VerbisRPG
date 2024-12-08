public class Player {
    private String name;
    private int healthPoints;
    private int attackPower;
    private int goldCoins;


    public Player(String name, int healthPoints, int attackPower) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.goldCoins = 0;
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

    public void earnGold(int goldCoins){
        this.goldCoins += goldCoins;
    }

    public int getGoldCoins(){
        return goldCoins;
    }

    public void spendGoldCoins(int goldCoins){
        this.goldCoins -= goldCoins;

    }

    public void heal(int healthPoints){
        this.healthPoints += healthPoints;
    }

    public void increaseAttackPower(int attackPower){
        this.attackPower += healthPoints;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return  this.name;
    }




}
