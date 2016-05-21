package Effects.Buffs;

import Dudes.Dude;
import Effects.PermanentEffects.PermanentEffect;

public abstract class Buff extends PermanentEffect {
    int duration;
    private boolean firstUsed = true;

    public Buff(String effectName, int value, boolean self, int duration) {
        super(effectName, value, self);
        this.duration = duration;
    }

    public boolean isFirstUsed() {
        return firstUsed;
    }

    public void setFirstUsed(boolean firstUsed) {
        this.firstUsed = firstUsed;
    }

    @Override
    public void afterTurn(Dude target) {
        if (duration > 0){
            duration--;
        }
    }

    @Override
    public boolean isExpired() {
        if (duration > 0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getDuration() {
        return duration;
    }
}
