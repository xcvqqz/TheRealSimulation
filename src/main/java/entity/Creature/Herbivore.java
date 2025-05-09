package entity.Creature;

import entity.Coordinates;
import entity.Entity;
import entity.GameBoard;
import entity.PathFinder;

import java.util.List;
import java.util.Map;


public class Herbivore extends Creature {

    public Herbivore(Coordinates coordinates, int health, int speed) {
        super(coordinates, health, speed);
    }

    public void makeMove(GameBoard gameBoard) {
        List<Coordinates> pathToFood = new PathFinder(gameBoard).searchFood(getCoordinates(), Grass.class);
        if (!pathToFood.isEmpty()) {
            moveAlongPath(gameBoard, pathToFood);
        }
    }

    private void moveAlongPath(GameBoard gameBoard, List<Coordinates> pathToGrass) {
        int steps = getSpeed();

        for (int i = 0; i < pathToGrass.size(); i++) {
            Coordinates nextStep = pathToGrass.get(i);
            Entity entityAtNextStep = gameBoard.getEntityAt(nextStep);

            if (steps <= 0) break;

            if (entityAtNextStep instanceof Tree || entityAtNextStep instanceof Rock) {
                steps -= 2;
                continue;
            }

            if (entityAtNextStep instanceof Grass && steps >= 1) {
                attackThisFood(gameBoard, nextStep); // nextStep – координаты жертвы
                break;
            }

            // Перемещение на свободную клетку
            gameBoard.getCoordinatesEntityMap().remove(getCoordinates());
            setCoordinates(nextStep);
            gameBoard.getCoordinatesEntityMap().put(nextStep, this);

            steps--;

            }
        }


    private void attackThisFood(GameBoard gameBoard, Coordinates grassCoordinates) {
        Grass grass = (Grass) gameBoard.getEntityAt(grassCoordinates);
        if (grass == null) return;
        gameBoard.getCoordinatesEntityMap().remove(grassCoordinates);
        gameBoard.getCoordinatesEntityMap().remove(getCoordinates());
        setCoordinates(grassCoordinates);
        gameBoard.getCoordinatesEntityMap().put(grassCoordinates, this);

    }


    }




//     gameBoard.getCoordinatesEntityMap().remove(foodCoordinates);
//                    herbivore.setCoordinates(foodCoordinates);
//                    gameBoard.getCoordinatesEntityMap().put(foodCoordinates, herbivore);
//                    break;}
//
//                            herbivore.setCoordinates(nextStep);
//                gameBoard.getCoordinatesEntityMap().put(nextStep, herbivore);
//        currentPosition = nextStep;
//        steps--;
//


    //       COLUMN
//          0 1 2 3 4
//        0 * * * * *
//        1 * * * * *    {2,3} {2,2}  {1,2} {3,3}
//   ROW  2 * G @ * *
//        3 * * @ H *
//        4 * * * * *
//










