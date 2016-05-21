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

public class Fighter extends Dude{

    private int ActionPointsRecoverySpeed = 5;

    public Fighter(String name, int accuracy, int armor, int health, int actionPoints) {
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
        super.getSkills().put("Heal", new BuffEffectSkill(15, Arrays.asList(new PermanentHealthEffect("Heal",30,true))));
        super.getSkills().put("Rest", new BuffEffectSkill(0, Arrays.asList(new PermanentActionPointsEffect("Rest",30,true))));
        super.getSkills().put("HealNextFiveTurns", new BuffEffectSkill(0, Arrays.asList(new HealthBuff("HealEveryTurn",10,true,5))));
        super.getSkills().put("ArmorBoost",new BuffEffectSkill(15, Arrays.asList(new ArmorBuff("ArmorBoost",30,true,3),
                new AccuracyBuff("AccuracyDebuff",-15,true,3), new VulneOrDurabilityBuff("Durability", 40, true, 3, 1))));
        super.getSkills().put("AccuracyBuff",new BuffEffectSkill(15, Arrays.asList(new ArmorBuff("ArmorDebuff",-15,true,2),
                                             new AccuracyBuff("AccuracyBuff",20,true,2))));
        super.getSkills().put("SimpleAtack",new AttackSkill(5, dummyList, 10));
        super.getSkills().put("RageAtack",new HitOrMissAttackSkill(20, dummyList));
        super.getSkills().put("SsneakAtack",new AttackSkill(20, Arrays.asList(new HealthBuff("Bleed",-2,false,5),
                new ArmorBuff("Weakening",-20,false,3), new VulneOrDurabilityBuff("Vullnerability", -30, false, 5, 1)), 5));
    }
}
