package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    //constructor
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public Color color() {
        return color(34, 0, 231);
    }

    public void move() {
        energy -= 0.03;
        energy = Math.min(energy, 0);
    }

    public void stay() {
        energy -= 0.01;
        energy = Math.min(energy, 0);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Creature replicate() {
        Creature newC = new Clorus(energy/2);
        energy /= 2;
        return newC;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        boolean noEmptySpace = true;
        boolean noPlip = true;
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name() == "empty") {
                emptyNeighbors.add(d);
                noEmptySpace = false;
            }
            if (neighbors.get(d).name() == "plip") {
                plipNeighbors.add(d);
                noPlip = false;
            }
        }
        //Rules
        if (noEmptySpace) {
            stay();
            return new Action(Action.ActionType.STAY);
        } else if (!noPlip) {
            Direction d = huglife.HugLifeUtils.randomEntry(plipNeighbors);
            attack((Creature) neighbors.get(d));
            return new Action(Action.ActionType.ATTACK, d);
        } else if (energy >= 1) {
            replicate();
            Direction d = huglife.HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        } else {
            move();
            Direction d = huglife.HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.MOVE, d);
        }
    }
}
