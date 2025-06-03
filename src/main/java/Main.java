import GameUtils.GameBoard;
import GameUtils.Simulation;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import static GameUtils.SimulationConstants.*;

public class Main {

    public static void main(String[] args) {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Simulation simulation = new Simulation(new GameBoard(GAMEBOARD_LENGTH, GAMEBOARD_WIDTH));
        simulation.startSimulation();

        try{
            Thread.sleep(10000);
        } catch (InterruptedException e){

        }

        simulation.pauseSimulation();
    }
}





