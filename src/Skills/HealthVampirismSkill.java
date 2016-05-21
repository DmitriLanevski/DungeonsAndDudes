package Skills;

import Dudes.Dude;
import Effects.Effect;
import Effects.PermanentEffects.PermanentAccuracyEffect;
import Effects.PermanentEffects.PermanentHealthEffect;

import java.util.List;

public class HealthVampirismSkill extends AttackSkill{
    int vampValue;

    public HealthVampirismSkill(int actionPrice, List<Effect> effects, int value, int vampValue) {
        super(actionPrice, effects, value);
        this.vampValue = vampValue;
    }

    @Override
    public void use(Dude user, Dude target) {
        int healthAmountBefore = target.getHealth();
        super.use(user, target);
        int healthAmountAfter = target.getHealth();
        int difference = healthAmountBefore - healthAmountAfter;
        user.getEffects().add(new PermanentHealthEffect("HealthGain", difference, true));
    }
}
