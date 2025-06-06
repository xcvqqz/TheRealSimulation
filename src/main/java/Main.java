import gameUtils.GameBoard;
import gameUtils.Simulation;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {

        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Simulation simulation = new Simulation(new GameBoard());
        simulation.startSimulation();

        try{
            Thread.sleep(10000);
        } catch (InterruptedException e){

        }

        simulation.pauseSimulation();
    }
}





