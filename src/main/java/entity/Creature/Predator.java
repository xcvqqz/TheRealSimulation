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

        Predator predator = returnPredatorFromTheBoard(gameBoard);
        Herbivore food = returnHerbivoreFromTheBoard(gameBoard);
        Entity entityOnCurrentCoordinates;
        List<Coordinates> roadToFoodlist;
        int steps = getSpeed();

        roadToFoodlist = new PathFinder(gameBoard).searchFood(predator.getCoordinates(), food.getCoordinates());

        for (Coordinates nextStep : roadToFoodlist) {
            entityOnCurrentCoordinates = gameBoard.getCoordinatesEntityMap().get(nextStep);
            if (steps <= 0) break;
            if ((entityOnCurrentCoordinates instanceof Tree) || (entityOnCurrentCoordinates instanceof Rock)) {
                steps = steps - 2;
                continue;
            }

            if (readyForAttack(nextStep, roadToFoodlist) && (steps >= 1)) {
                attackThisFood(gameBoard, predator.getCoordinates(), food.getCoordinates());
                break;
            }

            gameBoard.getCoordinatesEntityMap().remove(predator.getCoordinates());
            predator.setCoordinates(nextStep);
            gameBoard.getCoordinatesEntityMap().put(nextStep, predator);
            steps--;
        }
    }

    private void attackThisFood(GameBoard gameBoard, Coordinates predatorCoordinates, Coordinates foodCoordinates) {
        Predator predator = (Predator) gameBoard.getCoordinatesEntityMap().get(predatorCoordinates);
        Herbivore herbivore = (Herbivore) gameBoard.getCoordinatesEntityMap().get(foodCoordinates);
        if (herbivore.getHealth() - predator.getAttackPower() <= 0) {
            gameBoard.getCoordinatesEntityMap().remove(foodCoordinates);
            gameBoard.getCoordinatesEntityMap().remove(predatorCoordinates);
            predator.setCoordinates(foodCoordinates);
            gameBoard.getCoordinatesEntityMap().put(foodCoordinates, predator);
        } else {
            herbivore.setHealth(herbivore.getHealth() - predator.getAttackPower());
        }
    }

    private boolean readyForAttack(Coordinates current, List<Coordinates> allPathList) {
        return current.equals(allPathList.get(allPathList.size() - 1));
    }


    private Predator returnPredatorFromTheBoard(GameBoard gameBoard) {
        Predator predator = null;
        for (Coordinates nextBoardCoordinates : gameBoard.getBoards()) {
            if (gameBoard.getCoordinatesEntityMap().get(nextBoardCoordinates) instanceof Predator) {
                predator = (Predator) gameBoard.getCoordinatesEntityMap().get(nextBoardCoordinates);
            }
        }
        return predator;
    }

    private Herbivore returnHerbivoreFromTheBoard(GameBoard gameBoard) {
        Herbivore herbivore = null;
        for (Coordinates nextBoardCoordinates : gameBoard.getBoards()) {
            if (gameBoard.getCoordinatesEntityMap().get(nextBoardCoordinates) instanceof Herbivore) {
                herbivore = (Herbivore) gameBoard.getCoordinatesEntityMap().get(nextBoardCoordinates);
            }
        }
        return herbivore;
    }

}
