package Test;

import Enumeration.Artefacts;
import IleInterdite.Island;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.jupiter.api.Test; //au choix




import static org.junit.jupiter.api.Assertions.*;

public class IslandTest {
    JFXPanel jfxPanel = new JFXPanel(); // pour que les tests passent...

    Island island= new Island();

    @Before
    public void initBolide() {
        assertEquals(island.getListArtefacts().size(), 0);

    }

    @Test
    public void init() {
    }

    @Test
    public void enableTestEndOfGame() {
    }

    @Test
    public void getRandomPoint() {
    }

    @Test
    public void nextRound() {
    }

    @Test
    public void getEndOfGame() {
    }

    @Test
    public void  setLoader() {
    }

    @Test
    public void  setStage() {
    }

    @Test
    public void compteVoisines() {
    }

    @Test
    public void getZone() {
    }

    @Test
    public void displayLose() {
    }

    @Test
    public void displayWin() {
    }

    @Test
    public void win() {
    }

    @Test
    public void haveFourElements() {
    }

    @Test
    public void lose() {
    }

    @Test
    public void setRoundOf() {
    }

    @Test
    public void getRoundOf() {
    }

    @Test
    public void getListPlayers() {
    }

    @Test
    public void getListArtefacts() {
        Island island1 = new Island();
        assertEquals(island1.getListArtefacts().size(), 0);
        island1.getListArtefacts().add(Artefacts.air);
        island1.getListArtefacts().add(Artefacts.eau);
        island1.getListArtefacts().add(Artefacts.feu);
        island1.getListArtefacts().add(Artefacts.terre);
        assertEquals(  island1.getListArtefacts().size(), 4);
    }

    @Test
    public void risingWater() {
    }

    @Test
    public void getSeaLevel() {
    }

    @Test
    public void addToDefausseCarteTresor() {
    }

    @Test
    public void getGrille() {
    }

    @Test
    public void getSafeZones() {
    }

    @Test
    public void getZoneArround() {
    }

    @Test
    public void getSafeZoneArround() {
    }

    @Test
    public void getDefausseTresorCard() {
    }
}