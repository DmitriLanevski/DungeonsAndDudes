package Effects.Buffs;

import Dudes.Dude;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class AccuracyBuff extends Buff {
    public AccuracyBuff(String effectName, int value, boolean self, int duration) {
        super(effectName, value, self, duration);
    }

    @Override
    public void afterTurn(Dude target) {
        super.afterTurn(target);
        if (super.isFirstUsed()){
            target.setAccuracy(target.getAccuracy() + super.getValue());
            super.setFirstUsed(false);
        } else if (super.isExpired()){
            target.setAccuracy(target.getAccuracy() - super.getValue());
        }
    }
}
