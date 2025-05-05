package entity;

import entity.Creature.Herbivore;
import entity.Creature.Grass;
import entity.Creature.Tree;

public class MapConsoleRenderer {




    private final GameBoard gameBoard;

    public MapConsoleRenderer(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void render(){
        for(int column = 0; column < gameBoard.getMaxColumn(); column++){
            String line = "";
            for(int row = 0; row < gameBoard.getMaxRow(); row++) {
                line += colorizeAndGetSpriteForEntity(new Coordinates(row, column));
            }
            System.out.println(line);
        }
    }

    //1 3
//    1 2
//    1 4


    private String colorizeAndGetSpriteForEntity(Coordinates coordinates){
        //format = background color + font color + text (ANSI COLORS)

            if(!gameBoard.coordinateIsNotEmpty(coordinates)){
                 return "#";
            }

        if(gameBoard.getCoordinatesEntityMap().get(coordinates) instanceof Herbivore) {
            return "H";
        } if (gameBoard.getCoordinatesEntityMap().get(coordinates) instanceof Grass){
            return "G";}
        if(gameBoard.getCoordinatesEntityMap().get(coordinates) instanceof Tree){
            return "T";}

        return  "";
        }
    }





//    private String getSpriteForMap(Coordinates coordinates){
//        return colorizeSprite(" ", entity.c);
//    }




