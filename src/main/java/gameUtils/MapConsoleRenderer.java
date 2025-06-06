package gameUtils;

import entity.creature.*;
import entity.Entity;
import entity.static_object.Grass;
import entity.static_object.Rock;
import entity.static_object.Tree;

public class MapConsoleRenderer {

    private static final String GRASS = "\uD83C\uDF3F";
    private static final String TREE = "\uD83C\uDF33";
    private static final String ROCK = "⛰\uFE0F";
    private static final String HERBIVORE = "\uD83E\uDD8C";
    private static final String PREDATOR = "\uD83D\uDC3A";
    private static final String GROUND = "\uD83D\uDFEB";
    private final GameBoard gameBoard;

    public MapConsoleRenderer(GameBoard gameBoard) {this.gameBoard = gameBoard;}

    public void render(){
        for(int column = 0; column < gameBoard.getLength()+1; column++){
            StringBuilder sb = new StringBuilder();
            for(int row = 0; row < gameBoard.getWidth()+1; row++) {
                sb.append(colorizeAndGetSprite(new Coordinates(row, column)));
            }
            System.out.println(sb.toString());
        }
    }

    private String colorizeAndGetSprite(Coordinates coordinates) {
        Entity entity = gameBoard.getCoordinatesEntityMap().get(coordinates);
        if (entity instanceof Herbivore) {
            return HERBIVORE;}
        if (entity instanceof Grass) {
            return GRASS;}
        if (entity instanceof Tree) {
            return TREE;}
        if(entity instanceof Predator){
            return  PREDATOR;}
        if(entity instanceof Rock){
            return ROCK;}
        return GROUND;
    }
}