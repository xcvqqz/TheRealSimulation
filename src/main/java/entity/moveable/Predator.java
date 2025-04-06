package entity.moveable;


//Хищник - в дополнение к полям класса родителя creature.Creature имеет силу атаки
//Хищник может:
//1) переместиться
//2) атаковать травоядное. количество HP травоядного уменьшается на силу атаки хищника.
//Если значение HP жертвы опускается до 0, травояденое исчезает.


import entity.Coordinates;

public class Predator extends CreatureMoveable {

    private int speed;
    private int hp;
    private Coordinates coordinates;

    public Predator(Coordinates coordinates, int hp, int speed) {
        super(coordinates, hp, speed);
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    @Override
    public void makeMove() {

    };


}
