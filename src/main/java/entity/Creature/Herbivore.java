package entity.Creature;

import gameUtils.Coordinates;
import entity.Entity;
import gameUtils.GameBoard;
import gameUtils.PathFinder;
import entity.staticObject.Grass;
import entity.staticObject.Rock;
import entity.staticObject.Tree;
import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(int health, int speed) {
        super(health, speed);
    }
    public void makeMove(GameBoard gameBoard) {
        List<Coordinates> pathToFood = new PathFinder(gameBoard).searchFood(gameBoard.getCoordinates(this), Grass.class);
        if (!pathToFood.isEmpty()) {
            moveAlongPath(gameBoard, pathToFood);
        }
    }

    private void moveAlongPath(GameBoard gameBoard, List<Coordinates> pathToGrass) {
        int steps = getSpeed();
        for (Coordinates nextStep : pathToGrass) {
            if (steps <= 0) break;
            Entity entity = gameBoard.getEntityAt(nextStep);
            if(isObstacle(entity)) {
                steps -= 2;
                continue;
            }
            if (entity instanceof Grass && steps >= 1) {
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
                || entity instanceof Predator
                || entity instanceof Herbivore;
    }

    @Override
    public void attackThisFood(GameBoard gameBoard, Coordinates coordinates) {
        Grass grass = (Grass) gameBoard.getEntityAt(coordinates);
        if (grass == null) return;
        gameBoard.getCoordinatesEntityMap().remove(coordinates);
        gameBoard.getCoordinatesEntityMap().remove(gameBoard.getCoordinates(this));
        gameBoard.getCoordinatesEntityMap().put(coordinates, this);
    }
}