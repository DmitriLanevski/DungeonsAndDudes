package Dudes;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class InitialStatBundle {
    private int accuracy;
    private int armor;
    private int health;
    private int actionPoints;

    public InitialStatBundle(int accuracy, int armor, int health, int actionPoints) {
        this.accuracy = accuracy;
        this.armor = armor;
        this.health = health;
        this.actionPoints = actionPoints;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getArmor() {
        return armor;
    }

    public int getHealth() {
        return health;
    }

    public int getActionPoints() {
        return actionPoints;
    }
}
