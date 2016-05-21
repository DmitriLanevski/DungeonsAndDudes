package Dudes;

import Effects.Buffs.AccuracyBuff;
import Effects.Buffs.ArmorBuff;
import Effects.Buffs.HealthBuff;
import Effects.Buffs.VulneOrDurabilityBuff;
import Effects.Effect;
import Effects.PermanentEffects.PermanentActionPointsEffect;
import Effects.PermanentEffects.PermanentHealthEffect;
import Skills.AttackSkill;
import Skills.BuffEffectSkill;
import Skills.HitOrMissAttackSkill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lanev_000 on 10.03.2016.
 */
public class Rogue extends Dude{

    private int ActionPointsRecoverySpeed = 30;

    public Rogue(String name, int accuracy, int armor, int health, int actionPoints) {
        super(name, accuracy, armor, health, actionPoints);
        loadSkills();
    }

    @Override
    public void takeTurn(Dude target) {
        super.takeTurn(target);
        super.setActionPoints(getActionPoints() + ActionPointsRecoverySpeed);
    }

    @Override
    public void loadSkills(){
        List<Effect> dummyList = new ArrayList<>();
        super.getSkills().put("Heal", new BuffEffectSkill(15, Arrays.asList(new PermanentHealthEffect("Heal",30, true))));
        super.getSkills().put("Rest", new BuffEffectSkill(0, Arrays.asList(new PermanentActionPointsEffect("Rest", 30, true))));
        super.getSkills().put("HealNextFiveTurns", new BuffEffectSkill(0, Arrays.asList(new HealthBuff("HealEveryTurn",10,true,5))));
        super.getSkills().put("SpeedBoost",new BuffEffectSkill(7, Arrays.asList(new ArmorBuff("SpeedBoost",30,true, 3),
                                           new AccuracyBuff("AccuracyDebuff",-10,true,3))));
        super.getSkills().put("EagleEye",new BuffEffectSkill(10, Arrays.asList(new AccuracyBuff("EagleEye",40,true,3))));
        super.getSkills().put("SimpleShot",new AttackSkill(5, dummyList, 10));
        super.getSkills().put("NinjaStarShot",new HitOrMissAttackSkill(30, dummyList));
        super.getSkills().put("PoisonousDagger",new AttackSkill(20, Arrays.asList(new HealthBuff("Poisoned",-4,false,5),
                new ArmorBuff("Weakening",-10,false,3), new VulneOrDurabilityBuff("Vulnerability", -20, false, 5, 1)), 5));
    }
}
