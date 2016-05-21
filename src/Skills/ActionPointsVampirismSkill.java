package Skills;

import Dudes.Dude;
import Effects.Effect;
import Effects.PermanentEffects.PermanentAccuracyEffect;

import java.util.List;

public class ActionPointsVampirismSkill extends BuffEffectSkill {
    int vampValue;

    public ActionPointsVampirismSkill(int actionPrice, List<Effect> effects, int vampValue) {
        super(actionPrice, effects);
        this.vampValue = vampValue;
    }

    @Override
    public void use(Dude user, Dude target) {
        int actionPointsAmount;
        if (target.getActionPoints() >= vampValue){
            actionPointsAmount = vampValue;
        }
        else{
            actionPointsAmount = target.getActionPoints();
        }

        user.getEffects().add(new PermanentAccuracyEffect("ActionPointsGain",actionPointsAmount,true));
        target.setActionPoints(target.getActionPoints() - actionPointsAmount);

        super.use(user, target);
    }
}
