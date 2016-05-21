import Dudes.Dude;
import Dudes.Fighter;
import Dudes.Rogue;
import Dudes.Wizard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanev_000 on 10.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        Dude Lancelot = new Fighter("Lancelot", 40, 60, 100, 100);
        Dude Merlin = new Wizard("Merlin", 60, 30, 80, 120);

        trackBattle(Lancelot, Merlin);
        System.out.println("----------------------------------------------------------------------------------------");
        battle(Lancelot, Merlin);

        Dude RobinHood = new Rogue("Robin Hood", 50, 50, 100, 110);
        Dude Oz = new Wizard("Oz", 60, 30, 75, 110);

        trackBattle(RobinHood, Oz);
        System.out.println("----------------------------------------------------------------------------------------");
        battle(RobinHood, Oz);

    }

    static void battle(Dude oponent1, Dude oponent2){
        int start = 0;
        while (oponent1.isAlive() && oponent2.isAlive()){
            if (start == 0){
                start = (int)(Math.random()*2) + 1;
            }
            if (start == 1){
                System.out.println(oponent1.getName() + "'s turn:");
                oponent1.takeTurn(oponent2);
                trackBattle(oponent1, oponent2);
                start = 2;
            }
            else if (start == 2){
                System.out.println(oponent2.getName() + "'s turn:");
                oponent2.takeTurn(oponent1);
                trackBattle(oponent1, oponent2);
                start = 1;
            }
        }
        if (start == 1){
            System.out.println(oponent2.getName() + " won.");
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------------------");
        }
        else if (start == 2){
            System.out.println(oponent1.getName() + " won.");
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("----------------------------------------------------------------------------------------");
        }
    }

    static void trackBattle(Dude oponent1, Dude oponent2){
        System.out.print(toRightLength("Name: " + oponent1.getName()) + "Name: " + oponent2.getName() +"\r\n");
        System.out.print(toRightLength("Health: " + (oponent1.getHealth() + "(" + oponent1.getISB().getHealth() + ")"))
                                     + "Health: " + (oponent2.getHealth() + "(" + oponent2.getISB().getHealth() + ")") +"\r\n");
        System.out.print(toRightLength("Armor: " + (oponent1.getArmor() + "(" + oponent1.getISB().getArmor() + ")"))
                                     + "Armor: " + (oponent2.getArmor() + "(" + oponent2.getISB().getArmor() + ")") +"\r\n");
        System.out.print(toRightLength("Accuracy: " + (oponent1.getAccuracy() + "(" + oponent1.getISB().getAccuracy() + ")"))
                                     + "Accuracy: " + (oponent2.getAccuracy() + "(" + oponent2.getISB().getAccuracy() + ")") +"\r\n");
        System.out.print(toRightLength("ActionPoints: " + (oponent1.getActionPoints() + "(" + oponent1.getISB().getActionPoints() + ")"))
                                     + "ActionPoints: " + (oponent2.getActionPoints() + "(" + oponent2.getISB().getActionPoints() + ")") +"\r\n");
        System.out.println("Buffs and debuffs are:");
        List<String> buffs = new ArrayList<>();
        if (oponent1.getEffects().size() >= oponent2.getEffects().size()){
            for (int i = 0; i < oponent1.getEffects().size(); i++){
                buffs.add(toRightLength(oponent1.getEffects().get(i).getEffectName() +
                        ": " + oponent1.getEffects().get(i).getValue() +
                        ", Duration: " + oponent1.getEffects().get(i).getDuration()));
            }
            for (int i = 0; i < buffs.size(); i++){
                if (i < oponent2.getEffects().size()) {
                    buffs.set(i, buffs.get(i) + (oponent2.getEffects().get(i).getEffectName() +
                            ": " + oponent2.getEffects().get(i).getValue() +
                            ", Duration: " + oponent2.getEffects().get(i).getDuration()) + "\r\n");
                }
                else{
                    buffs.set(i, buffs.get(i) + "\r\n");
                }
            }
        }
        else if (oponent2.getEffects().size() > oponent1.getEffects().size()){
            for (int i = 0; i < oponent2.getEffects().size(); i++){
                buffs.add(oponent2.getEffects().get(i).getEffectName() + ", " +
                        ": " + oponent2.getEffects().get(i).getValue() +
                        ", Duration: " + oponent2.getEffects().get(i).getDuration() + "\r\n");
            }
            for (int i = 0; i < buffs.size(); i++){
                if (i < oponent1.getEffects().size()) {
                    buffs.set(i, toRightLength((oponent1.getEffects().get(i).getEffectName() +
                            ": " + oponent1.getEffects().get(i).getValue() +
                            ", Duration: " + oponent1.getEffects().get(i).getDuration())) + buffs.get(i));
                }
                else{
                    buffs.set(i, toRightLength(" ") +  buffs.get(i));
                }
            }
        }
        for (String buff : buffs) {
            System.out.print(buff);
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }

    static String toRightLength(String string){
        while(string.length() < 50){
            string = string + " ";
        }
        return string;
    }
}
