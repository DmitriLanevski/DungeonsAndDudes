package Effects;

import Dudes.Dude;

public interface Effect extends  Cloneable{
    void onHit(Dude target);
    void beforeTurn(Dude target);
    void afterTurn(Dude target);
    boolean isExpired();
    boolean isSelf();
    String getEffectName();
    int getValue();
    int getDuration();
    Effect clone();
}
