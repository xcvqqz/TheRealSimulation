package entity;

import entity.moveable.Herbivore;
import entity.notMoveable.Grass;
import entity.GameMap;

import java.util.HashMap;
import java.util.Map;

public class MapConsoleRenderer {

    GameMap gameMap = new GameMap();

    public void render(GameMap map){
        for(HeightOfMap heigthOfMap : HeightOfMap.values()){
            String line = "";
            for(WidthOfMap widthOfMap : WidthOfMap.values()) {
                line += colorizeAndGetSpriteForEntity(new Coordinates(heigthOfMap.getId(), widthOfMap));
            }
            System.out.println(line);
        }
    }



    private String colorizeAndGetSpriteForEntity(Coordinates coordinates){
        //format = background color + font color + text (ANSI COLORS)

        if(gameMap.coordinateIsEmpty(coordinates)){
            return "#";}

            return switch (gameMap.getEntity(coordinates).getClass().getSimpleName()){
                case "Herbivore" -> "Her";
                case "Grass" -> "Gr";
                case "Tree" -> "Tr";
                case "Rock" -> "R";
                default -> "";
            };

        }

    }





//    private String getSpriteForMap(Coordinates coordinates){
//        return colorizeSprite(" ", entity.c);
//    }




