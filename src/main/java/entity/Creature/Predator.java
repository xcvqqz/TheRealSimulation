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

        Herbivore nearestHerbivore = findNearestHerbivore(gameBoard);

        if (nearestHerbivore == null) {
            return;
        }

        Coordinates predatorCoordinates = getCoordinates();
        Coordinates herbivoreCoordinates = nearestHerbivore.getCoordinates();


        PathFinder pathFinder = new PathFinder(gameBoard);
        List<Coordinates> pathToHerbivore = pathFinder.searchFood(predatorCoordinates, herbivoreCoordinates);


        moveAlongPath(gameBoard, pathToHerbivore, predatorCoordinates, herbivoreCoordinates);
    }

    private Herbivore findNearestHerbivore(GameBoard gameBoard) {
        List<Herbivore> herbivores = gameBoard.getEntities(Herbivore.class);
        Herbivore nearestHerbivore = null;
        double minDistance = Double.MAX_VALUE;

        for (Herbivore herbivore : herbivores) {
            double distance = calculateDistance(getCoordinates(), herbivore.getCoordinates());
            if (distance < minDistance) {
                minDistance = distance;
                nearestHerbivore = herbivore;
            }
        }

        return nearestHerbivore;
    }

    private void moveAlongPath(GameBoard gameBoard, List<Coordinates> pathToHerbivore, Coordinates predatorCoordinates, Coordinates herbivoreCoordinates) {
        int steps = getSpeed();
        Entity entityOnCurrentCoordinates;

        for (Coordinates nextStep : pathToHerbivore) {
            entityOnCurrentCoordinates = gameBoard.getCoordinatesEntityMap().get(nextStep);

            if (steps <= 0) break;


            if ((entityOnCurrentCoordinates instanceof Tree) || (entityOnCurrentCoordinates instanceof Rock)) {
                steps -= 2;
                continue;
            }


            if (readyForAttack(nextStep, pathToHerbivore) && (steps >= 1)) {
                attackThisFood(gameBoard, predatorCoordinates, herbivoreCoordinates);
                break;
            }

            // Перемещаем хищника на следующую клетку
            gameBoard.getCoordinatesEntityMap().remove(predatorCoordinates);
            setCoordinates(nextStep);
            gameBoard.getCoordinatesEntityMap().put(nextStep, this);

            steps--;
        }
    }

    private boolean readyForAttack(Coordinates current, List<Coordinates> pathList) {
        return current.equals(pathList.get(pathList.size() - 1));
    }

    private void attackThisFood(GameBoard gameBoard, Coordinates predatorCoordinates, Coordinates herbivoreCoordinates) {
        Predator predator = (Predator) gameBoard.getCoordinatesEntityMap().get(predatorCoordinates);
        Herbivore herbivore = (Herbivore) gameBoard.getCoordinatesEntityMap().get(herbivoreCoordinates);

        if (herbivore.getHealth() - predator.getAttackPower() <= 0) {
            gameBoard.getCoordinatesEntityMap().remove(herbivoreCoordinates);
            gameBoard.getCoordinatesEntityMap().remove(predatorCoordinates);
            predator.setCoordinates(herbivoreCoordinates);
            gameBoard.getCoordinatesEntityMap().put(herbivoreCoordinates, predator);
        } else {
            herbivore.setHealth(herbivore.getHealth() - predator.getAttackPower());
        }
    }

    private double calculateDistance(Coordinates start, Coordinates end) {
        return Math.sqrt(Math.pow(end.getRow() - start.getRow(), 2) + Math.pow(end.getColumn() - start.getColumn(), 2));
    }
}

