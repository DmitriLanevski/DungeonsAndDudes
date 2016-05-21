package Dudes;

import Effects.Effect;
import Skills.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lanev_000 on 20.05.2016.
 */
public abstract class Dude {
    private String name;
    private int accuracy;
    private int armor;
    private int health;
    private int actionPoints;
    private int damage;
    private InitialStatBundle ISB;
    private List<Effect> effects = new ArrayList<>();
    private Map<String, Skill> skills = new HashMap<>();

    public Dude(String name, int accuracy, int armor, int health, int actionPoints) {
        this.name = name;
        this.accuracy = accuracy;
        this.armor = armor;
        this.health = health;
        this.actionPoints = actionPoints;
        ISB = new InitialStatBundle(accuracy,armor,health,actionPoints);
    }

    public void takeTurn(Dude target){
        //Before turn
        cleanEffects(target);
        for (Effect effect : effects) {
            effect.beforeTurn(this);
        }
        //Turn
        useRandomSkill(target);
        //After turn
        for (Effect effect : effects) {
            effect.afterTurn(this);
        }
    }

    public void cleanEffects(Dude target){
        List<Effect> cleaEfeectsListThis = new ArrayList<>();
        //List<Effect> cleaEfeectsListTarget = new ArrayList<>();

        for (Effect effect : effects) {
            if (!effect.isExpired()){
                cleaEfeectsListThis.add(effect);
            }
        }
        setEffects(cleaEfeectsListThis);

        /*for (Effect effect : target.getEffects()) {
            if (!effect.isExpired()){
                cleaEfeectsListTarget.add(effect);
            }
        }
        target.setEffects(cleaEfeectsListThis);*/
    }

    public void useRandomSkill(Dude target){
        int index = (int)(Math.random()*skills.keySet().size());
        String key = (String)skills.keySet().toArray()[index];
        System.out.println(" ");
        System.out.println(getName() + " used skill " + key);
        System.out.println(" ");
        skills.get(key).use(this, target);
    }

    public void takeDamage(int amount, Dude target){
        this.damage = amount;
        setHealth(getHealth() - amount);
        if (amount > 0){
            for (Effect effect : effects) {
                effect.onHit(target);
            }
        }
    }

    public void loadSkills(){}

    public boolean isAlive(){
        if (health > 0){
            return true;
        } else {
            return false;
        }
    }

    public int hitOrMiss(Dude target){
        int hitValue;
        if ((hitValue = (getAccuracy() + ((int)(Math.random()*20)+1))) >= target.getArmor()){
            return hitValue;
        }
        else {
            return 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getArmor() {
        return armor;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public int getDamage() {
        return damage;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public Map<String, Skill> getSkills() {
        return skills;
    }

    public String getName() {
        return name;
    }

    public InitialStatBundle getISB() {
        return ISB;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void setHealth(int health) {
        if (health > ISB.getHealth()){
            this.health = ISB.getHealth();
        } else if (health < 0){
            this.health = 0;
        } else {
            this.health = health;
        }
    }

    public void setActionPoints(int actionPoints) {
        if (actionPoints > ISB.getActionPoints()){
            this.actionPoints = ISB.getActionPoints();
        } else {
            this.actionPoints = actionPoints;
        }
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}
