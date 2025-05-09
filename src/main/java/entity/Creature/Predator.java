package entity.Creature;


//Хищник - в дополнение к полям класса родителя creature.Creature имеет силу атаки
//Хищник может:
//1) переместиться
//2) атаковать травоядное. количество HP травоядного уменьшается на силу атаки хищника.
//Если значение HP жертвы опускается до 0, травояденое исчезает.


import entity.Coordinates;
import entity.Entity;
import entity.GameBoard;
import entity.PathFinder;

import java.util.List;

public class Predator extends Creature {

    private final int attackPower;

    public Predator(Coordinates coordinates, int health, int speed, int attackPower) {
        super(coordinates, health, speed);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        List<Coordinates> pathToFood = new PathFinder(gameBoard).searchFood(getCoordinates(), Herbivore.class);
        if (!pathToFood.isEmpty()) {
            moveAlongPath(gameBoard, pathToFood);
        }
    }

    private void moveAlongPath(GameBoard gameBoard, List<Coordinates> pathToHerbivore) {
        int steps = getSpeed();

        for (int i = 0; i < pathToHerbivore.size(); i++) {
            Coordinates nextStep = pathToHerbivore.get(i);
            Entity entityAtNextStep = gameBoard.getEntityAt(nextStep);

            if (steps <= 0) break;

            if (entityAtNextStep instanceof Tree || entityAtNextStep instanceof Rock) {
                steps -= 2;
                continue;
            }

            // Если на клетке травоядное – атака
            if (entityAtNextStep instanceof Herbivore && steps >= 1) {
                attackThisFood(gameBoard, nextStep); // nextStep – координаты жертвы
                break;
            }

            gameBoard.getCoordinatesEntityMap().remove(getCoordinates());
            setCoordinates(nextStep);
            gameBoard.getCoordinatesEntityMap().put(nextStep, this);

            steps--;
        }
    }

    private boolean readyForAttack(Coordinates current, List<Coordinates> pathList) {
        return current.equals(pathList.get(pathList.size() - 1));
    }

    private void attackThisFood(GameBoard gameBoard, Coordinates herbivoreCoordinates) {
        Herbivore herbivore = (Herbivore) gameBoard.getEntityAt(herbivoreCoordinates);

        if (herbivore == null) return;

        if (herbivore.getHealth() - getAttackPower() <= 0) {
            gameBoard.getCoordinatesEntityMap().remove(herbivoreCoordinates);
            gameBoard.getCoordinatesEntityMap().remove(getCoordinates());
            setCoordinates(herbivoreCoordinates);
            gameBoard.getCoordinatesEntityMap().put(herbivoreCoordinates, this);
        } else {
            herbivore.setHealth(herbivore.getHealth() - getAttackPower());
        }
    }

}

//            COLUMN
//          0 1 2 3 4
//        0 * * * * *
//        1 H * * * *    {2,3} {2,2}  {1,2} {3,3}
//   ROW  2 * H * * *
//        3 * * * P *
//        4 * * * * *
//