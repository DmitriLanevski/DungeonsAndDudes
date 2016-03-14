/**
 * Created by lanev_000 on 10.03.2016.
 */

//Ma ei saanud aru mida need meetodid tegema peavad ja seepärast neid ei kasuta.
//Kuigi ülesannes oli õeldud "Loo interface Effect, milles on järgnevad meetodid:".
//Seda ma tegin, aga rohkemadki pole küsitud.

public interface Effect {
    void onHit(Dude target);
    void beforeTurn(Dude target);
    void afterTurn(Dude target);
    boolean isExpired();
}
