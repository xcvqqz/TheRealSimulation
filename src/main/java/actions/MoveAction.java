package actions;

import entity.Coordinates;
import entity.Creature.Creature;
import entity.Entity;
import entity.GameBoard;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MoveAction extends Action {

    private final GameBoard gameBoard;

     public MoveAction(GameBoard gameBoard){
     this.gameBoard = gameBoard;
 }

    @Override
    public void execute() {
        for(Creature creature : getAllCreature()){
            moveCreature(creature);
        }
    }

 private void moveCreature(Creature creature){
        creature.makeMove(gameBoard);
 }


    private Set<Creature> getAllCreature(){
        Set<Creature> allCreatures = new HashSet<>();
        for(Map.Entry<Coordinates, Entity> entry : gameBoard.getCoordinatesEntityMap().entrySet()){
            Entity entity = entry.getValue();
            if(entity instanceof Creature){
                allCreatures.add((Creature) entity);
            }
        }
        return allCreatures;
    }
}
