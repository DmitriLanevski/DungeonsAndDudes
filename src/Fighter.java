import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lanev_000 on 7.03.2016.
 */
public class Fighter extends Dude implements Effect{

    private int ActionPointsRecoverySpeed = 5;
    private int recoveryLimit;

    public void setActionPointsRecoverySpeed(int actionPointsRecoverySpeed) {
        ActionPointsRecoverySpeed = actionPointsRecoverySpeed;
    }

    public Fighter(int accuracy, int armor, int health, int actionPoints) {
        super(accuracy, armor, health, actionPoints);
        recoveryLimit = getActionPoints();
    }

    private void armorBoost(){
        int actionPrice = 15;
        if (getActionPoints() > actionPrice){
            EffectParams armorBoost = new EffectParams("ArmorBoost", "Armor", "buff", 30, 3);
            EffectParams accuracyDebuff = new EffectParams("AccuracyDebuff", "Accuracy", "buff", -15, 3);
            implementEffect(armorBoost);
            implementEffect(accuracyDebuff);
            getEffects().add(armorBoost);
            getEffects().add(accuracyDebuff);
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void accuracyBuff(){
        int actionPrice = 15;
        if (getActionPoints() > actionPrice){
            EffectParams accuracyBuff = new EffectParams("AccuracyBuff", "Accuracy", "buff", 20, 2);
            EffectParams armorDebuff = new EffectParams("ArmorDebuff", "Armor", "buff", -15, 2);
            implementEffect(accuracyBuff);
            implementEffect(armorDebuff);
            getEffects().add(accuracyBuff);
            getEffects().add(armorDebuff);
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void simpleAtack(Dude target){
        int actionPrice = 5;
        if (getActionPoints() > actionPrice){
            target.getEffects().add(new EffectParams("SimpleAtack", "Health", "permanent", -10, 1));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void rageAtack(Dude target){
        int actionPrice = 20;
        if (getActionPoints() > actionPrice){
            target.getEffects().add(new EffectParams("RageAtack", "Health", "permanent", hitOrMiss(-30, target), 1));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void sneakAtack(Dude target) {
        int actionPrice = 20;
        if (getActionPoints() > actionPrice){
            target.getEffects().add(new EffectParams("SsneakAtack", "Health", "permanent", -5, 1));
            target.getEffects().add(new EffectParams("Bleed", "Health", "permanent", -2, 5));
            target.getEffects().add(new EffectParams("Weakening", "Armor", "buff", -20, 3));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void actionRandomSelector(Dude target){
        switch ((int)(Math.random()*5)) {
            case 0: armorBoost();
                    break;
            case 1: accuracyBuff();
                break;
            case 2: simpleAtack(target);
                break;
            case 3: rageAtack(target);
                break;
            case 4: sneakAtack(target);
                break;
        }
    }

    public void takeTurn(Dude target){
        implementEffects(getEffects());
        actionRandomSelector(target);
        target.implementEffects(target.getEffects());
        target.debuff(target.getEffects());
        debuff(getEffects());
        if ((getActionPoints() + ActionPointsRecoverySpeed) < recoveryLimit){
            setActionPoints(getActionPoints() + ActionPointsRecoverySpeed);
        }
        else{
            setActionPoints(recoveryLimit);
        }
    }

    public void implementEffect(EffectParams effect){
        effect.setDuration(effect.getDuration() - 1);
        if (effect.getTargetParameter().equals("Health")){
            setHealth(getHealth() + effect.getEffect());
        }
        else if (effect.getTargetParameter().equals("Armor")){
            setArmor(getArmor() + effect.getEffect());
        }
        else if (effect.getTargetParameter().equals("Accuracy")){
            setAccuracy(getAccuracy() + effect.getEffect());
        }
        else if (effect.getTargetParameter().equals("ActionPoints")){
            setActionPoints(getActionPoints() + effect.getEffect());
        }
        else if (effect.getTargetParameter().equals("ActionPointsRecoverySpeed")){
            setActionPointsRecoverySpeed(ActionPointsRecoverySpeed + effect.getEffect());
        }
    }

    public void implementEffects(List<EffectParams> effects){
        for(Iterator<EffectParams> iterator = effects.iterator(); iterator.hasNext(); ) {
            EffectParams effectParams = iterator.next();
            if (effectParams.getDuration() == 0){
                iterator.remove();
            }
        }
        for (EffectParams effect : effects) {
            implementEffect(effect);
        }
    }

    public void debuff(List<EffectParams> effects){
        for (EffectParams effect : effects) {
            if (effect.getAction().equals("buff")){
                if (effect.getTargetParameter().equals("Health")){
                    setHealth(getHealth() - effect.getEffect());
                }
                else if (effect.getTargetParameter().equals("Armor")){
                    setArmor(getArmor() - effect.getEffect());
                }
                else if (effect.getTargetParameter().equals("Accuracy")){
                    setAccuracy(getAccuracy() - effect.getEffect());
                }
                else if (effect.getTargetParameter().equals("ActionPoints")){
                    setActionPoints(getActionPoints() - effect.getEffect());
                }
                else if (effect.getTargetParameter().equals("ActionPointsRecoverySpeed")){
                    setActionPointsRecoverySpeed(ActionPointsRecoverySpeed - effect.getEffect());
                }
            }
        }
    }

    //Ma ei saanud aru mida need meetodid tegema peavad ja seepärast neid ei kasuta.
    //Kuigi ülesannes oli õeldud "Loo interface Effect, milles on järgnevad meetodid:".
    //Seda ma tegin, aga rohkemadki pole küsitud.

    public void onHit(Dude target){
    }

    public void beforeTurn(Dude target){
    }

    public void afterTurn(Dude target){
    }

    public boolean isExpired(){
        return false;
    }
}
