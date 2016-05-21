package Effects.Buffs;

import Dudes.Dude;

import static java.lang.Math.abs;

public class VulneOrDurabilityBuff extends Buff{
    int deepnes;
    int i = 0;

    public VulneOrDurabilityBuff(String effectName, int value, boolean self, int duration, int deepnes) {
        super(effectName, value, self, duration);
        this.deepnes = deepnes;
    }

    @Override
    public void onHit(Dude target) {
        if (i != deepnes){
            if (super.getValue() > 0){
                double percent = abs(target.getDamage() * super.getValue()/100.0);
                target.setHealth(target.getHealth() + (int)percent);
                super.setFirstUsed(false);
            } else {
                double percent = abs(target.getDamage() * super.getValue()/100.0);
                target.takeDamage((int)percent, target);
                i ++;
            }
        } else {
            i = 0;
        }
    }
}
