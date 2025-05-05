package entity.Creature;


import entity.Coordinates;
import entity.Entity;
import entity.GameBoard;

//существо имеет скорость(сколько клеток может пройти за 1 ход), количество HP, метод makeMove()
public abstract class Creature extends Entity {

    private int health;
    private final int speed;
    private Coordinates coordinates;

    public Creature(Coordinates coordinates, int health, int speed) {
        this.coordinates = coordinates;
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public abstract void makeMove(GameBoard gameBoard);

}
