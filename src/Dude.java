/**
 * Created by lanev_000 on 7.03.2016.
 */
abstract class Dude {
    private int accuracy;
    private int armor;
    private int health;
    private int actionPoints;

    public Dude(int accuracy, int armor, int health, int actionPoints) {
        this.accuracy = accuracy;
        this.armor = armor;
        this.health = health;
        this.actionPoints = actionPoints;
    }

    abstract void takeTurn(Dude target);

    boolean isAlive(){
        if (health <= 0){
            return false;
        }
        else {
            return true;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getArmor() {
        return armor;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }
}
