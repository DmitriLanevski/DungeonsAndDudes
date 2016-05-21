package Effects.PermanentEffects;

import Dudes.Dude;
import Effects.Effect;

/**
 * Created by lanev_000 on 21.05.2016.
 */
public abstract class PermanentEffect implements Effect {
    private String effectName;
    private int value;
    private boolean self;

    public PermanentEffect(String effectName, int value, boolean self) {
        this.effectName = effectName;
        this.value = value;
        this.self = self;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean isSelf() {
        return self;
    }

    @Override
    public void onHit(Dude target) {

    }

    @Override
    public void beforeTurn(Dude target) {

    }

    @Override
    public void afterTurn(Dude target) {

    }

    @Override
    public boolean isExpired() {
        return true;
    }

    @Override
    public Effect clone() {
        try {
            return (Effect) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getEffectName() {
        return effectName;
    }

    @Override
    public int getDuration() {
        return 0;
    }
}
