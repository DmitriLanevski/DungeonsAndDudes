import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lanev_000 on 10.03.2016.
 */
public class Rogue extends Dude implements Effect{

    private int ActionPointsRecoverySpeed = 30;
    private int recoveryLimit;

    public void setActionPointsRecoverySpeed(int actionPointsRecoverySpeed) {
        ActionPointsRecoverySpeed = actionPointsRecoverySpeed;
    }

    public Rogue (String name, int accuracy, int armor, int health, int actionPoints) {
        super(name, accuracy, armor, health, actionPoints);
        recoveryLimit = getActionPoints();
    }

    private void speedBoost(){
        int actionPrice = 7;
        if (getActionPoints() > actionPrice){
            setAction(new EffectParams("SpeedBoost", "Armor", "buff", 30, 3));
            setAction(new EffectParams("AccuracyDebuff", "Accuracy", "buff", -10, 3));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void eagleEye(){
        int actionPrice = 10;
        if (getActionPoints() > actionPrice){
            setAction(new EffectParams("EagleEye", "Accuracy", "buff", 40, 3));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void simpleShot(Dude target){
        int actionPrice = 5;
        if (getActionPoints() > actionPrice){
            target.setAction(new EffectParams("SimpleShot", "Health", "permanent", -10, 1));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void NinjaStarShot(Dude target){
        int actionPrice = 30;
        if (getActionPoints() > actionPrice){
            target.setAction(new EffectParams("NinjaStarShot", "Health", "permanent", hitOrMiss(-30, target), 1));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void poisonousDagger(Dude target) {
        int actionPrice = 20;
        if (getActionPoints() > actionPrice){
            target.setAction(new EffectParams("PoisonousDagger", "Health", "permanent", -10, 1));
            target.setAction(new EffectParams("Poisoned", "Health", "permanent", -4, 5));
            target.setAction(new EffectParams("Weakening", "Armor", "buff", -10, 3));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void actionRandomSelector(Dude target){
        switch ((int)(Math.random()*6)) {
            case 0: speedBoost();
                break;
            case 1: eagleEye();
                break;
            case 2: simpleShot(target);
                break;
            case 3: NinjaStarShot(target);
                break;
            case 4: poisonousDagger(target);
                break;
        }
    }

    public void takeTurn(Dude target){
        implementEffects(getEffects());
        target.implementEffects(target.getEffects());
        actionRandomSelector(target);
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

    public void setAction(EffectParams effect){
        implementEffect(effect);
        getEffects().add(effect);
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
