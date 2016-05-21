package Effects.PermanentEffects;

import Dudes.Dude;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class PermanentArmorEffect extends PermanentEffect{
    public PermanentArmorEffect(String effectName, int value, boolean self) {
        super(effectName, value, self);
    }

    @Override
    public void afterTurn(Dude target) {
        target.setArmor(target.getArmor() + super.getValue());
    }
}
