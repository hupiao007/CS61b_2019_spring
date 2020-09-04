package creatures;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;

import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void TestChoose() {
        //test color and attack
        Clorus c = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Plip());
        surrounded.put(Direction.BOTTOM, new Empty());
        surrounded.put(Direction.LEFT, new Empty());
        surrounded.put(Direction.RIGHT, new Empty());
        //test color
        Color actualC = c.color();
        Color expectedC = new Color(34, 0, 231);
        assertEquals(actualC, expectedC);
        //test attack
        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected, actual);
        double actualE = c.energy();
        double expectedE = 3;
        assertEquals(expectedE, actualE, 0.01);

        //test stay, no empty space around
        c = new Clorus(2);
        surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Plip());
        surrounded.put(Direction.LEFT, new Plip());
        surrounded.put(Direction.RIGHT, new Plip());
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        //test replicate
        c = new Clorus(2);
        surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);
        assertEquals(expected, actual);

        //test move
        c = new Clorus(0.5);
        surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);
        assertEquals(expected, actual);
    }
}
