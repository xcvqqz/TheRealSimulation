package entity.creature;

import gameUtils.Coordinates;
import entity.Entity;
import gameUtils.GameBoard;
import gameUtils.PathFinder;
import entity.static_object.Grass;
import entity.static_object.Rock;
import entity.static_object.Tree;
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
            if (entity instanceof Grass && steps >= 1) {
                attackFood(gameBoard, nextStep);
                moveCreature(gameBoard, gameBoard.getCoordinates(this), nextStep);
                break;
            }

            moveCreature(gameBoard, gameBoard.getCoordinates(this), nextStep);
            steps--;
        }
    }


    @Override
    public void attackFood(GameBoard gameBoard, Coordinates coordinates) {
        if (coordinates == null) return;
        gameBoard.getCoordinatesEntityMap().remove(coordinates);

    }

    @Override
    public void moveCreature(GameBoard gameBoard, Coordinates from, Coordinates to){
        gameBoard.getCoordinatesEntityMap().remove(from);
        gameBoard.getCoordinatesEntityMap().put(to, this);
    }

}