package Skills;

import Dudes.Dude;
import Effects.Effect;

import java.util.List;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class HitOrMissAttackSkill extends Skill{

    public HitOrMissAttackSkill(int actionPrice, List<Effect> effects) {
        super(actionPrice, effects);
    }

    @Override
    public void use(Dude user, Dude target) {
        if (user.getActionPoints() >= super.getActionPrice()){
            target.takeDamage(user.hitOrMiss(target), target);
            for (Effect effect : super.getEffects()){
                target.getEffects().add(effect.clone());
            }
            user.setActionPoints(user.getActionPoints() - super.getActionPrice());
        }
    }
}
