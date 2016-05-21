package Effects.PermanentEffects;

import Dudes.Dude;
import Effects.Effect;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class PermanentHealthEffect extends PermanentEffect {
    public PermanentHealthEffect(String effectName, int value, boolean self) {
        super(effectName, value, self);
    }

    @Override
    public void afterTurn(Dude target) {
        target.setHealth(target.getHealth() + super.getValue());
    }
}

