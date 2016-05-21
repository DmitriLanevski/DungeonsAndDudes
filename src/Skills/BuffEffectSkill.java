package Skills;

import Dudes.Dude;
import Effects.Effect;

import java.util.List;

public class BuffEffectSkill extends Skill{
    public BuffEffectSkill(int actionPrice, List<Effect> effects) {
        super(actionPrice, effects);
    }

    @Override
    public void use(Dude user, Dude target) {
        if (user.getActionPoints() >= super.getActionPrice()){
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
