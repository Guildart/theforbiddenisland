package Test;

import Enumeration.Artefacts;
import IleInterdite.Island;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.jupiter.api.Test; //au choix




import static org.junit.jupiter.api.Assertions.*;

public class IslandTest {
    JFXPanel jfxPanel = new JFXPanel(); // pour que les tests passent...
    // On test juste que ce qu'on a besoin

    Island island= new Island();

    @Before
    public void initIsland() {
        assertEquals(island.getListArtefacts().size(), 0);

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

}