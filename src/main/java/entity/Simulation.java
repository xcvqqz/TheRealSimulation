package entity;


//Рендерер ответственен за визуализацию состояния поля, его отрисовку. Интерфейс может быть консольным, либо графическим.

//включает в себя:
//-карту
//-Счётчик ходов
//-Рендерер поля
//-Actions - список действий, исполняемых перед стартом симуляции или на каждом ходу

import actions.Action;
import actions.CheckAndRespawnAction;
import actions.MoveAction;
import actions.SpawnAction;

import java.util.List;

public class Simulation {

    private final GameBoard gameBoard;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final MapConsoleRenderer mapConsoleRenderer;

    public Simulation(GameBoard gameBoard, List<Action> initActions, List<Action> turnActions, MapConsoleRenderer mapConsoleRenderer) {
        this.gameBoard = gameBoard;
        this.initActions = initActions;
        this.turnActions = turnActions;
        this.mapConsoleRenderer = new MapConsoleRenderer(gameBoard);
    }


    private void initSimulation() {
        initActions.add(new SpawnAction(gameBoard));
        for (Action action : initActions) {
            action.execute();
        }
        render();
    }

    private void turnSimulation(){
        turnActions.add(new MoveAction(gameBoard));
        turnActions.add(new CheckAndRespawnAction(gameBoard));
        for (Action action : turnActions){
            action.execute();
        }
        render;
    }


   private void renderSimulation(){
        mapConsoleRenderer.render();
   }


}



    /* nextTurn() - просимулировать и отрендерить один ход*/

    /* startSimulation() - запустить бесконечный цикл симуляции и рендеринга*/

    /* pauseSimulation() - приостановить бесконечный цикл симуляции и рендеринга*/

    /* initActions - действия, совершаемые перед стартом симуляции. Пример - расставить объекты и существа на карте*/

        /*turnActions - действия, совершаемые каждый ход. Пример - передвижение существ, добавить травы или травоядных,
        если их осталось мало*/

