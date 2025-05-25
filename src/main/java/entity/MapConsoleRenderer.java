package entity;

import entity.Creature.*;

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
        for(int column = 0; column < gameBoard.getMaxColumn()+1; column++){
            StringBuilder sb = new StringBuilder();
            for(int row = 0; row < gameBoard.getMaxRow()+1; row++) {
                sb.append(colorizeAndGetSpriteForEntity(new Coordinates(row, column)));
            }
            System.out.println(sb.toString());
        }
    }

    private String colorizeAndGetSpriteForEntity(Coordinates coordinates) {
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



