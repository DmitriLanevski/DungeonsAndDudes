package Skills;

import Dudes.Dude;
import Effects.Effect;

import java.util.List;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public class AttackSkill extends Skill{
    int value;

    public AttackSkill(int actionPrice, List<Effect> effects, int value) {
        super(actionPrice, effects);
        this.value = value;
    }

    @Override
    public void use(Dude user, Dude target) {
        if (user.getActionPoints() >= super.getActionPrice()){
            target.takeDamage(value, target);
            for (Effect effect : super.getEffects()){
                if (effect.isSelf()){
                    user.getEffects().add(effect.clone());
                } else {
                    target.getEffects().add(effect.clone());
                }
            }
            user.setActionPoints(user.getActionPoints() - super.getActionPrice());
        }
    }
}
