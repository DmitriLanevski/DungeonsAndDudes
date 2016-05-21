package Skills;

import Dudes.Dude;
import Effects.Effect;

import java.util.List;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public abstract class Skill {
    private int actionPrice;
    private List<Effect> effects;

    public Skill(int actionPrice, List<Effect> effects) {
        this.actionPrice = actionPrice;
        this.effects = effects;
    }

    public void use(Dude user, Dude target){}

    public List<Effect> getEffects() {
        return effects;
    }

    public int getActionPrice() {
        return actionPrice;
    }
}
