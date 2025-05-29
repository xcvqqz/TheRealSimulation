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

    public Predator(int health, int speed, int attackPower) {
        super(health, speed);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public void makeMove(GameBoard gameBoard) {
        List<Coordinates> pathToFood = new PathFinder(gameBoard).searchFood(gameBoard.getCoordinates(this), Herbivore.class);
        if (!pathToFood.isEmpty()) {
            moveAlongPath(gameBoard, pathToFood);
        }
    }

    private void moveAlongPath(GameBoard gameBoard, List<Coordinates> pathToHerbivore) {
        int steps = getSpeed();
        for (Coordinates nextStep : pathToHerbivore) {
            if (steps <= 0) break;
            Entity entity = gameBoard.getEntityAt(nextStep);
            if(isObstacle(entity)) {
                steps -= 2;
                continue;
            }

            if (entity instanceof Herbivore && steps >= 1) {
                attackThisFood(gameBoard, nextStep);
                break;
            }
            gameBoard.getCoordinatesEntityMap().remove(gameBoard.getCoordinates(this));
            gameBoard.getCoordinatesEntityMap().put(nextStep, this);
            steps--;
        }
    }

    private boolean isObstacle(Entity entity) {
        return entity instanceof Rock
                || entity instanceof Tree
                || entity instanceof Grass;
    }

    @Override
    public void attackThisFood(GameBoard gameBoard, Coordinates coordinates) {
        Herbivore herbivore = (Herbivore) gameBoard.getEntityAt(coordinates);
        if (herbivore == null) return;
        if (herbivore.getHealth() - getAttackPower() <= 0) {
            gameBoard.getCoordinatesEntityMap().remove(coordinates);
            gameBoard.getCoordinatesEntityMap().remove(gameBoard.getCoordinates(this));
            gameBoard.getCoordinatesEntityMap().put(coordinates, this);
        } else {
            herbivore.setHealth(herbivore.getHealth() - getAttackPower());
        }
    }
}