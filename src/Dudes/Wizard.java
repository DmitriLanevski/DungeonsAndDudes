package Dudes;

import Effects.Buffs.*;
import Effects.Effect;
import Effects.PermanentEffects.PermanentActionPointsEffect;
import Effects.PermanentEffects.PermanentHealthEffect;
import Skills.ActionPointsVampirismSkill;
import Skills.AttackSkill;
import Skills.BuffEffectSkill;
import Skills.HitOrMissAttackSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by lanev_000 on 10.03.2016.
 */
public class Wizard extends Dude{

    private int ActionPointsRecoverySpeed = 8;

    public Wizard(String name, int accuracy, int armor, int health, int actionPoints) {
        super(name, accuracy, armor, health, actionPoints);
        loadSkills();
    }

    @Override
    public void takeTurn(Dude target) {
        super.takeTurn(target);
        super.setActionPoints(getActionPoints() + ActionPointsRecoverySpeed);
    }

    public void loadSkills(){
        List<Effect> dummyList = new ArrayList<>();
        super.getSkills().put("Heal", new BuffEffectSkill(15, Arrays.asList(new PermanentHealthEffect("Heal",30,true))));
        super.getSkills().put("Rest", new BuffEffectSkill(0, Arrays.asList(new PermanentActionPointsEffect("Rest", 30, true))));
        super.getSkills().put("HealNextFiveTurns", new BuffEffectSkill(0, Arrays.asList(new HealthBuff("HealEveryTurn",3,true,5))));
        super.getSkills().put("ManaShield",new BuffEffectSkill(25, Arrays.asList(new ArmorBuff("ManaShield",45,true,2),
                                           new ActionPointsBuff("ActionPointsRecoverySpeedDebuff",-15,true,2))));
        super.getSkills().put("ConcentrationBuff",new BuffEffectSkill(15, Arrays.asList(new AccuracyBuff("ConcentrationBuff",40,true,3))));
        super.getSkills().put("ManaBolt",new AttackSkill(5, dummyList, 5));
        super.getSkills().put("ManaInferno",new HitOrMissAttackSkill(40, dummyList));
        super.getSkills().put("SoulDrain",new ActionPointsVampirismSkill(20, Arrays.asList(new HealthBuff("SoulLoss",-2,false,5),
                new ArmorBuff("Weakening",-20,false,3), new VulneOrDurabilityBuff("Vullnerability", -50, false, 5, 1)), 25));
    }
}
