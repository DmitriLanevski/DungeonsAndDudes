import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanev_000 on 7.03.2016.
 */
abstract class Dude implements Effect{
    private String name;
    private int accuracy;
    private int armor;
    private int health;
    private int actionPoints;
    private List<EffectParams> effects = new ArrayList<>();

    public Dude(String name, int accuracy, int armor, int health, int actionPoints) {
        this.name = name;
        this.accuracy = accuracy;
        this.armor = armor;
        this.health = health;
        this.actionPoints = actionPoints;
    }

    abstract void takeTurn(Dude target);
    abstract void implementEffect(EffectParams effect);
    abstract void implementEffects(List<EffectParams> effects);
    abstract void debuff(List<EffectParams> effects);
    abstract void setAction(EffectParams effect);

    boolean isAlive(){
        if (health <= 0){
            return false;
        }
        else {
            return true;
        }
    }

    public int hitOrMiss(int hitValue, Dude target){
        if ((getAccuracy() + ((int)(Math.random()*20)+1)) >= target.getArmor()){
            return hitValue;
        }
        else {
            return 0;
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
    public List<EffectParams> getEffects() {
        return effects;
    }
    public String getName() {
        return name;
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
    public void setEffects(List<EffectParams> effects) {
        this.effects = effects;
    }
    public void setName(String name) {
        this.name = name;
    }
}
