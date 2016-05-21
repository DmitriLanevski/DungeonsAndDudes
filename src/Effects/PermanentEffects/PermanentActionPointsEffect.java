package Effects.PermanentEffects;

import Dudes.Dude;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class PermanentActionPointsEffect extends  PermanentEffect {
    public PermanentActionPointsEffect(String effectName, int value, boolean self) {
        super(effectName, value, self);
    }

    @Override
    public void afterTurn(Dude target) {
        target.setActionPoints(target.getActionPoints() + super.getValue());
    }
}
