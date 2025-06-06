package entity.creature;

import gameUtils.Coordinates;
import entity.Entity;
import gameUtils.GameBoard;

public abstract class Creature extends Entity {
    private int health;
    private final int speed;

    public Creature(int health, int speed) {
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
    public abstract void attackFood(GameBoard gameBoard, Coordinates coordinates);
    public abstract void moveCreature(GameBoard gameBoard, Coordinates from, Coordinates to);
}