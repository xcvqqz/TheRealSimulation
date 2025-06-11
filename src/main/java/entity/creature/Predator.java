package entity.creature;

import gameUtils.Coordinates;
import entity.Entity;
import gameUtils.GameBoard;
import gameUtils.PathFinder;

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

            if (entity instanceof Herbivore && steps >= 1) {
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
        Herbivore herbivore = (Herbivore) gameBoard.getEntityAt(coordinates);
        if (herbivore == null) return;
        herbivore.setHealth(herbivore.getHealth() - getAttackPower());
        if (herbivore.getHealth() <= 0) {
            gameBoard.getEntities().remove(coordinates);
        }
    }

    @Override
    public void moveCreature(GameBoard gameBoard, Coordinates from, Coordinates to){
        gameBoard.getEntities().remove(from);
        gameBoard.getEntities().put(to, this);
    }

}