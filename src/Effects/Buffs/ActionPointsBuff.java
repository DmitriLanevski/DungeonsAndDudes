package Effects.Buffs;

import Dudes.Dude;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class ActionPointsBuff extends Buff{
    public ActionPointsBuff(String effectName, int value, boolean self, int duration) {
        super(effectName, value, self, duration);
    }

    @Override
    public void beforeTurn(Dude target) {
        if (!super.isFirstUsed()){
            target.setActionPoints(target.getActionPoints() + super.getValue());
        }
    }

    @Override
    public void afterTurn(Dude target) {
        super.afterTurn(target);
        if (super.isFirstUsed()){
            target.setActionPoints(target.getActionPoints() + super.getValue());
            super.setFirstUsed(false);
        }
    }
}
