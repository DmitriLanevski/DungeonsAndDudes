/**
 * Created by lanev_000 on 7.03.2016.
 */
public class Fighter extends Dude{

    public Fighter(int accuracy, int armor, int health, int actionPoints) {
        super(accuracy, armor, health, actionPoints);
    }

    private int ArmorBoostDuration = 0;
    private boolean DoubleAttack = false;
    private int initialArmor = getArmor();
    private int initialAccuracy = getAccuracy();
    private int initialHealth = getHealth();
    private int initialActionPoints = getActionPoints();

    private void armorBoost(){
        setArmor(initialArmor*2);
        setAccuracy(initialAccuracy/2);
        ArmorBoostDuration = 3;
    }

    private void atackBuff()


}
