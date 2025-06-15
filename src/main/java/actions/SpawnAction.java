package actions;

import gameUtils.Coordinates;
import entity.creature.*;
import entity.Entity;
import gameUtils.GameBoard;
import entity.static_object.Grass;
import entity.static_object.Rock;
import entity.static_object.Tree;
import java.util.ArrayList;
import static parameters.Constants.*;

public class SpawnAction extends Action {

    private GameBoard gameBoard;
    private ArrayList<Entity> entityList;

    public SpawnAction (GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.entityList = new ArrayList<>();
    }

    @Override
    public void execute() {
        createEntityList();
        addEntityOnGameBoard();
    }

    private void addEntityOnGameBoard(){
        for(int i = 0; i < entityList.size(); i++){
            Coordinates newCoordinates = gameBoard.getRandomFreeCoordinates();
            gameBoard.getEntities().put(newCoordinates, entityList.get(i));
        }
    }

    private void createEntityList(){
        entityList.add(createEntity(Predator.class));
        entityList.add(createEntity(Grass.class));
        entityList.add(createEntity(Grass.class));
        entityList.add(createEntity(Grass.class));
        entityList.add(createEntity(Grass.class));
        entityList.add(createEntity(Grass.class));
        entityList.add(createEntity(Grass.class));
        entityList.add(createEntity(Grass.class));
        entityList.add(createEntity(Tree.class));
        entityList.add(createEntity(Tree.class));
        entityList.add(createEntity(Tree.class));
        entityList.add(createEntity(Tree.class));
        entityList.add(createEntity(Rock.class));
        entityList.add(createEntity(Rock.class));
        entityList.add(createEntity(Rock.class));
        entityList.add(createEntity(Rock.class));
        entityList.add(createEntity(Rock.class));
        entityList.add(createEntity(Rock.class));
        entityList.add(createEntity(Herbivore.class));
        entityList.add(createEntity(Herbivore.class));
        entityList.add(createEntity(Herbivore.class));
    }

     private <T extends Entity> Entity createEntity(Class<T> typeOfEntity) {
         return switch (typeOfEntity.getSimpleName()) {
             case "Predator" -> new Predator(PREDATOR_HEALTH, PREDATOR_SPEED, PREDATOR_ATTACK_POWER);
             case "Herbivore" -> new Herbivore(HERBIVORE_HEALTH, HERBIVORE_SPEED);
             case "Grass" -> new Grass();
             case "Tree" -> new Tree();
             case "Rock" -> new Rock();
             default -> throw new IllegalStateException("Unexpected value: " + typeOfEntity.getSimpleName());
         };
     }
}