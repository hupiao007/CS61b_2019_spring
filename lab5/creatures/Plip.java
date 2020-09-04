package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        r = 99;
        b = 76;
        if (energy == 0) {
            g = 63;
        } else {
            g = (int) (96 * energy + 63);
        }
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.15;
        energy = Math.max(0, energy);
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy += 0.2;
        energy = Math.min(energy, 2);
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        Plip newP = new Plip(energy/2);
        energy /= 2;
        return newP;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        boolean noEmptySpace = true;
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        for (Direction x : neighbors.keySet()) {
            if (neighbors.get(x).name() == "empty") {
                noEmptySpace = false;
                emptyNeighbors.add(x);
            }
            if (neighbors.get(x).name() == "clorus") {
                anyClorus = true;
            }
        }
        // Rule 1
        if (noEmptySpace) {
            stay();
            return new Action(Action.ActionType.STAY);
        }
        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (energy >= 1) {
            Plip newP = replicate();
            Direction d = huglife.HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        }

        // Rule 3
        if (anyClorus && Math.random() < 0.5) {
            Direction d = huglife.HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.MOVE, d);
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}
