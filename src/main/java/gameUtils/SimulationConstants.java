package gameUtils;

import java.io.IOException;

public final class SimulationConstants {

    private SimulationConstants() throws IOException {
        throw new UnsupportedOperationException("Util ity class");
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final int GAMEBOARD_LENGTH = 7;
    public static final int GAMEBOARD_WIDTH = 7;
    public static final int HERBIVORE_SPEED = 3;
    public static final int HERBIVORE_HEALTH = 6;
    public static final int PREDATOR_SPEED = 3;
    public static final int PREDATOR_HEALTH = 10;
    public static final int PREDATOR_ATTACK_POWER = 3;
    public static final String RENDERER_VISUAL_LINE = "\u001B[96m" + ANSI_RESET;
    public static final String TURN_COUNTER_INFO_MESSAGE = "ХОД ПОД НОМЕРОМ ";
}
