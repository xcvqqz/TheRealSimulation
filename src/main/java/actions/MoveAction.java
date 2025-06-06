package actions;

import gameUtils.Coordinates;
import entity.creature.Creature;
import entity.creature.Herbivore;
import entity.Entity;
import gameUtils.GameBoard;

import java.util.*;
import java.util.stream.Collectors;

public class MoveAction extends Action {

    private final GameBoard gameBoard;

     public MoveAction(GameBoard gameBoard){
     this.gameBoard = gameBoard;
 }

    @Override
    public void execute() {
        for(Creature creature : getCreaturesInPriorityOrder()){
            creature.makeMove(gameBoard);
        }
    }

    private List<Creature> getCreaturesInPriorityOrder() {
        return getAllCreature().stream()
                .sorted(Comparator.comparingInt(c ->
                        c instanceof Herbivore ? 0 : 1))
                .collect(Collectors.toList());
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