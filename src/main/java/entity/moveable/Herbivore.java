package entity.moveable;


import entity.Coordinates;

//травоядное - стремится найти ресурс(траву), можешь потратить свой ход на движение или поглощение еды.
public class Herbivore extends CreatureMoveable {


    private int speed;
    private int hp;
    private Coordinates coordinates;

    public Herbivore(Coordinates coordinates, int hp, int speed) {
        super(coordinates, hp, speed);
    }


    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHp() {
        return hp;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }



    @Override
    public void makeMove() {

    };



}
