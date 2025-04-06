package entity.moveable;


import entity.Coordinates;
import entity.Entity;

//существо имеет скорость(сколько клеток может пройти за 1 ход), количество HP, метод makeMove()
public abstract class CreatureMoveable extends Entity {

    private int hp;
    private int speed;

    public CreatureMoveable(Coordinates coordinates, int hp, int speed) {
        super(coordinates);
        this.hp = hp;
        this.speed = speed;
    }



    public abstract void makeMove();

}
