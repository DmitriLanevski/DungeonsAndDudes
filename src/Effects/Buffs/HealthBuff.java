package Effects.Buffs;

import Dudes.Dude;

import static java.lang.Math.abs;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class HealthBuff extends Buff{
    public HealthBuff(String effectName, int value, boolean self, int duration) {
        super(effectName, value, self, duration);
    }

    @Override
    public void beforeTurn(Dude target) {
        if (!super.isFirstUsed()){
            if (super.getValue() > 0){
                target.setHealth(target.getHealth() + super.getValue());
            } else {
                target.takeDamage(super.getValue(), target);
            }
        }
    }

    @Override
    public void afterTurn(Dude target) {
        super.afterTurn(target);
        if (super.isFirstUsed()){
            if (super.getValue() > 0){
                target.setHealth(target.getHealth() + super.getValue());
                super.setFirstUsed(false);
            } else {
                target.takeDamage(abs(super.getValue()), target);
                super.setFirstUsed(false);
            }
        }
    }
}
