package Test;

import static org.junit.Assert.*;

import IleInterdite.Position;

import org.junit.Before;
import org.junit.jupiter.api.Test; //au choix


class PositionTest {

    Position v01 = new Position(0, 1);
    Position v00 = new Position(0, 1);
    Position v03 = new Position(0, 3);


    @Before
    void initPosition() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {

    }

    @Test
    void testToString() {
        assertEquals(v01.toString(),"x: 0 y: 1");
        assertEquals(v00.toString(),"x: 0 y: 0");
        assertEquals(v03.toString(),"x: 0 y: 3");

    }

    @Test
    void testEquals() {
        assertEquals(v01.equals(new Position(0,1)),true ) ;
        assertNotEquals(v01.equals(new Position(1,1)),true);
    }

}