import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lanev_000 on 10.03.2016.
 */
public class Wizard extends Dude implements Effect{

    private int ActionPointsRecoverySpeed = 8;
    private int recoveryLimit;

    public void setActionPointsRecoverySpeed(int actionPointsRecoverySpeed) {
        ActionPointsRecoverySpeed = actionPointsRecoverySpeed;
    }

    public Wizard(int accuracy, int armor, int health, int actionPoints) {
        super(accuracy, armor, health, actionPoints);
        recoveryLimit = getActionPoints();
    }

    private void manaShield(){
        int actionPrice = 25;
        if (getActionPoints() > actionPrice){
            EffectParams manaShield = new EffectParams("ManaShield", "Armor", "buff", 45, 2);
            EffectParams actionPointsRecoverySpeedDebuff = new EffectParams("ActionPointsRecoverySpeedDebuff", "ActionPointsRecoverySpeed", "buff", -15, 2);
            implementEffect(manaShield);
            implementEffect(actionPointsRecoverySpeedDebuff);
            getEffects().add(manaShield);
            getEffects().add(actionPointsRecoverySpeedDebuff);
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void concentrationBuff(){
        int actionPrice = 15;
        if (getActionPoints() > actionPrice){
            EffectParams concentrationBuff = new EffectParams("ConcentrationBuff", "Accuracy", "buff", 15, 4);
            implementEffect(concentrationBuff);
            getEffects().add(concentrationBuff);
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void manaBolt(Dude target){
        int actionPrice = 5;
        if (getActionPoints() > actionPrice){
            target.getEffects().add(new EffectParams("ManaBolt", "Health", "permanent", -5, 1));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void manaInferno(Dude target){
        int actionPrice = 40;
        if (getActionPoints() > actionPrice){
            target.getEffects().add(new EffectParams("ManaInferno", "Health", "permanent", hitOrMiss(-40, target), 1));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void soulDrain(Dude target) {
        int actionPrice = 20;
        if (getActionPoints() > actionPrice){
            int manaAmount;
            if (target.getActionPoints() >= 25){
                manaAmount = 25;
            }
            else{
                manaAmount = target.getActionPoints();
            }
            EffectParams soulDrain = new EffectParams("SoulDrain+", "ActionPoints", "permanent", manaAmount, 1);
            implementEffect(soulDrain);
            getEffects().add(soulDrain);
            target.getEffects().add(new EffectParams("SoulDrain-", "ActionPoints", "permanent", -manaAmount, 1));
            target.getEffects().add(new EffectParams("SoulLoss", "Health", "permanent", -2, 5));
            target.getEffects().add(new EffectParams("Weakening", "Armor", "buff", -20, 3));
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void meditation(){
        int actionPrice = 10;
        if (getActionPoints() > actionPrice){
            EffectParams meditation = new EffectParams("Meditation", "ActionPoints", "permanent", 50, 1);
            implementEffect(meditation);
            getEffects().add(meditation);
            setActionPoints(getActionPoints()-actionPrice);
        }
    }

    private void actionRandomSelector(Dude target){
        switch ((int)(Math.random()*6)) {
            case 0: manaShield();
                break;
            case 1: concentrationBuff();
                break;
            case 2: manaBolt(target);
                break;
            case 3: manaInferno(target);
                break;
            case 4: soulDrain(target);
                break;
            case 5: meditation();
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
