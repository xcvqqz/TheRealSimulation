package entity.Creature;

import entity.Coordinates;
import entity.GameBoard;
import entity.PathFinder;

import java.util.List;
import java.util.Map;

//травоядное - стремится найти ресурс(траву), можешь потратить свой ход на движение или поглощение еды.
public class Herbivore extends Creature {


    public Herbivore(Coordinates coordinates, int health, int speed) {
        super(coordinates, health, speed);
    }

    @Override
    public void makeMove(GameBoard gameBoard) {

        Herbivore herbivore = (Herbivore) gameBoard.getCoordinatesEntityMap().get(new Coordinates(3, 3));
        Grass grass = (Grass) gameBoard.getCoordinatesEntityMap().get(new Coordinates(0, 0));
        Tree tree = (Tree) gameBoard.getCoordinatesEntityMap().get(new Coordinates(1, 2));

        PathFinder pathFinder = new PathFinder(gameBoard);
        List<Coordinates> roadToFoodlist = pathFinder.searchFood(herbivore.getCoordinates(), grass.getCoordinates());

        int steps = herbivore.getSpeed();
        Coordinates currentPosition = herbivore.getCoordinates();
        Coordinates foodCoordinates = grass.getCoordinates();

        for (Coordinates nextStep : roadToFoodlist) {
            if (steps <= 0) break;


            if (nextStep.equals(tree.getCoordinates())){
                steps = steps - 2;
                continue;}

                gameBoard.getCoordinatesEntityMap().remove(currentPosition);

                if(nextStep.readyForAttack(roadToFoodlist) && (steps>=1)){
                    //moveAndEat();
                    gameBoard.getCoordinatesEntityMap().remove(foodCoordinates);
                    herbivore.setCoordinates(foodCoordinates);
                    gameBoard.getCoordinatesEntityMap().put(foodCoordinates, herbivore);
                    break;}

                herbivore.setCoordinates(nextStep);
                gameBoard.getCoordinatesEntityMap().put(nextStep, herbivore);
                currentPosition = nextStep;
                steps--;

            }
        }
    }



    //       COLUMN
//          0 1 2 3 4
//        0 G * * * *
//        1 @ @ * * *    {2,3} {2,2} {1,2}  {1,1} {0,1} {0,0}
//   ROW  2 * @ @ * *
//        3 * * @ H *
//        4 * * * * *
//










