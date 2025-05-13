package entity.Creature;

import entity.Coordinates;
import entity.Entity;
import entity.GameBoard;

public abstract class Creature extends Entity {

    private int health;
    private final int speed;

    public Creature(Coordinates coordinates, int health, int speed) {
        super(coordinates);
        this.health = health;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public abstract void makeMove(GameBoard gameBoard);

}
