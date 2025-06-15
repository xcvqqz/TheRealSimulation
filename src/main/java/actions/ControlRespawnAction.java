package actions;

import gameUtils.Coordinates;
import entity.static_object.Grass;
import entity.creature.Herbivore;
import entity.Entity;
import gameUtils.GameBoard;
import java.util.ArrayList;
import java.util.Map;

import static parameters.Constants.*;

public class ControlRespawnAction extends Action {

    private GameBoard gameBoard;
    private final int herbivoreAmount = MIN_HERBIVORE_ON_BOARD;
    private final int grassAmount = MIN_GRASS_ON_BOARD;

    public ControlRespawnAction(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    @Override
    public void execute() {
        if (countEntities(Herbivore.class) < herbivoreAmount) {
            Coordinates herbivoreCoords = gameBoard.getRandomFreeCoordinates();
            if (herbivoreCoords != null) {
                gameBoard.getEntities().put(herbivoreCoords,
                        new Herbivore(HERBIVORE_HEALTH, HERBIVORE_SPEED));
            }
        }
        if (countEntities(Grass.class) < grassAmount) {
            Coordinates grassCoords = gameBoard.getRandomFreeCoordinates();
            if (grassCoords != null) {
                gameBoard.getEntities().put(grassCoords,
                        new Grass());
            }
        }
    }

    private <T extends Entity> int countEntities(Class<T> typeOfEntity) {
        int count = 0;
        for (Map.Entry<Coordinates, Entity> entry : gameBoard.getEntities().entrySet()) {
            if (typeOfEntity.isInstance(entry.getValue())) {
                count++;
            }
        }
        return count;
    }
}
