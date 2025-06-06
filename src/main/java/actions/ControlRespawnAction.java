package actions;

import gameUtils.Coordinates;
import entity.static_object.Grass;
import entity.creature.Herbivore;
import entity.Entity;
import gameUtils.GameBoard;
import java.util.ArrayList;
import java.util.Map;

import static gameUtils.SimulationConstants.*;

public class ControlRespawnAction extends Action {

    private GameBoard gameBoard;

    public ControlRespawnAction(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void execute() {
        if (!isEntityAvailable(Herbivore.class)) {
            Coordinates herbivoreCoords = gameBoard.getRandomFreeCoordinates();
            if (herbivoreCoords != null) {
                gameBoard.getCoordinatesEntityMap().put(herbivoreCoords,
                        new Herbivore(HERBIVORE_HEALTH, HERBIVORE_SPEED));
            }
        }
        if (!isEntityAvailable(Grass.class)) {
            Coordinates grassCoords = gameBoard.getRandomFreeCoordinates();
            if (grassCoords != null) {
                gameBoard.getCoordinatesEntityMap().put(grassCoords,
                        new Grass());
            }
        }
    }

    private <T extends Entity> boolean isEntityAvailable(Class<T> typeOfEntity) {
        ArrayList<T> entityList = new ArrayList<>();
        for (Map.Entry<Coordinates, Entity> entity : gameBoard.getCoordinatesEntityMap().entrySet()) {
            if (typeOfEntity.isInstance(entity.getValue())) {
                entityList.add((T) entity.getValue());
            }
        }
        if (entityList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}