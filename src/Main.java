import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanev_000 on 10.03.2016.
 */
public class Main {
    public static void main(String[] args) {
        Dude Knight = new Fighter(40, 60, 100, 100);
        Dude Merlin = new Wizard(60, 30, 80, 120);
        int start = 0;

        trackBattle(Knight, Merlin);
        while (Knight.isAlive() && Merlin.isAlive()){
            if (start == 0){
                start = (int)(Math.random()*2) + 1;
            }
            if (start == 1){
                Knight.takeTurn(Merlin);
                System.out.println("Knight's turn:");
                trackBattle(Knight, Merlin);
                start = 2;
            }
            else if (start == 2){
                Merlin.takeTurn(Knight);
                System.out.println("Merlin's turn:");
                trackBattle(Knight, Merlin);
                start = 1;
            }
        }
        if (start == 1){
            System.out.println("Merlin won.");
        }
        else if (start == 2){
            System.out.println("Knight won.");
        }

        int start1 = 0;
        Dude RobinHood = new Rogue(50, 50, 100, 110);
        Dude Oz = new Wizard(60, 30, 75, 110);

        trackBattle(Knight, Merlin);
        while (RobinHood.isAlive() && Oz.isAlive()){
            if (start1 == 0){
                start1 = (int)(Math.random()*2) + 1;
            }
            if (start1 == 1){
                Oz.takeTurn(RobinHood);
                System.out.println("Oz's turn:");
                trackBattle(Oz, RobinHood);
                start1 = 2;
            }
            else if (start1 == 2){
                RobinHood.takeTurn(Oz);
                System.out.println("RobinHood's turn:");
                trackBattle(Oz, RobinHood);
                start1 = 1;
            }
        }
        if (start1 == 1){
            System.out.println("RobinHood won.");
        }
        else if (start1 == 2){
            System.out.println("Oz won.");
        }

    }

    static void trackBattle(Dude oponent1, Dude oponent2){
        System.out.print("Health: " + oponent1.getHealth() + "\t\t\t\t\t\t\t\t\t\t" + "Health: " + oponent2.getHealth() +"\r\n");
        System.out.print("Armor: " + oponent1.getArmor() + "\t\t\t\t\t\t\t\t\t\t" + "Armor: " + oponent2.getArmor() +"\r\n");
        System.out.print("Accuracy: " + oponent1.getAccuracy() + "\t\t\t\t\t\t\t\t\t" + "Accuracy: " + oponent2.getAccuracy() +"\r\n");
        System.out.print("Action points: " + oponent1.getActionPoints() + "\t\t\t\t\t\t\t\t" + "Action points: " + oponent2.getActionPoints() +"\r\n");
        System.out.println("Buffs and debuffs are:");
        List<String> buffs = new ArrayList<>();
        if (oponent1.getEffects().size() >= oponent2.getEffects().size()){
            for (int i = 0; i < oponent1.getEffects().size(); i++){
                if (oponent1.getEffects().get(i).getDuration() >= 0){
                    buffs.add(oponent1.getEffects().get(i).getName() + ", " + oponent1.getEffects().get(i).getTargetParameter() +
                              ": " + oponent1.getEffects().get(i).getEffect() + ", Duration: " + oponent1.getEffects().get(i).getDuration()
                              + "\t\t\t\t");
                }
            }
            for (int i = 0; i < buffs.size(); i++){
                if (i < oponent2.getEffects().size()) {
                    if (oponent2.getEffects().get(i).getDuration() >= 0){
                        buffs.set(i, buffs.get(i) + (oponent2.getEffects().get(i).getName() + ", " + oponent2.getEffects().get(i).getTargetParameter() +
                                ": " + oponent2.getEffects().get(i).getEffect() + ", Duration: " + oponent2.getEffects().get(i).getDuration()) + "\r\n");
                    }
                    else{
                        buffs.set(i, buffs.get(i) + "\r\n");
                    }
                }
                else{
                    buffs.set(i, buffs.get(i) + "\r\n");
                }
            }
        }
        else if (oponent2.getEffects().size() > oponent1.getEffects().size()){
            for (int i = 0; i < oponent2.getEffects().size(); i++){
                if (oponent2.getEffects().get(i).getDuration() >= 0){
                    buffs.add("\t\t\t\t" + oponent2.getEffects().get(i).getName() + ", " + oponent2.getEffects().get(i).getTargetParameter() +
                            ": " + oponent2.getEffects().get(i).getEffect() + ", Duration: " + oponent2.getEffects().get(i).getDuration() + "\r\n");
                }
            }
            for (int i = 0; i < buffs.size(); i++){
                if (i < oponent1.getEffects().size()) {
                    if (oponent1.getEffects().get(i).getDuration() >= 0){
                        buffs.set(i, (oponent1.getEffects().get(i).getName() + ", " + oponent1.getEffects().get(i).getTargetParameter() +
                                ": " + oponent1.getEffects().get(i).getEffect() + ", Duration: " + oponent1.getEffects().get(i).getDuration()) + buffs.get(i));
                    }
                    else{
                        buffs.set(i, "\t\t\t\t\t\t\t\t" +  buffs.get(i));
                    }
                }
                else{
                    buffs.set(i, "\t\t\t\t\t\t\t\t" +  buffs.get(i));
                }
            }
        }
        for (String buff : buffs) {
            System.out.print(buff);
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
