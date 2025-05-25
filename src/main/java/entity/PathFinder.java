package entity;

import entity.Creature.Creature;

import java.util.*;

public class PathFinder {
    private GameBoard gameBoard;
    private Map<Coordinates, Coordinates> parentsPath;
    private ArrayList<Coordinates> visitedPath;
    private Queue<Coordinates> pathQueue;

    public PathFinder(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.parentsPath = new HashMap<>();
        this.visitedPath = new ArrayList<>();
        this.pathQueue = new ArrayDeque<>();
    }

    private boolean isValidateCoordinate(Coordinates coordinates) {
        return ((coordinates.getRow() >= 0 && coordinates.getRow() <= gameBoard.getMaxRow())
                && (coordinates.getColumn() >= 0 && coordinates.getColumn() <= gameBoard.getMaxColumn()));
    }

    public <T extends Entity> List<Coordinates> searchFood(Coordinates start,  Class<T> typeOfEntity) {
        pathQueue.clear();
        visitedPath.clear();
        parentsPath.clear();

        pathQueue.add(start);
        visitedPath.add(start);
        parentsPath.put(start, null);


        while (!pathQueue.isEmpty()) {
            Coordinates current = pathQueue.remove();

            Entity entity = gameBoard.getEntityAt(current);
            if (entity != null && typeOfEntity.isInstance(entity)) {
                return reconstructPath(current);
            }

            for (Coordinates neighbor : getNeighboursCoordinates(current)) {
                if (!visitedPath.contains(neighbor)) {
                    visitedPath.add(neighbor);
                    parentsPath.put(neighbor, current);
                    pathQueue.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }

    private LinkedList<Coordinates> reconstructPath(Coordinates goal) {
        LinkedList<Coordinates> path = new LinkedList<>();
        Coordinates current = goal;

        while (current != null) {
            path.addFirst(current);
            current = parentsPath.get(current);
        }

        if (!path.isEmpty()) {
            path.removeFirst();
        }

        return path;
    }

    private Coordinates[] getShiftDirections() {
        return new Coordinates[]{
                new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)
        };
    }

    private HashSet<Coordinates> getNeighboursCoordinates(Coordinates currentCoordinates) {
        HashSet<Coordinates> result = new HashSet<>();
        for (Coordinates directionCoordinates : getShiftDirections()) {
            int newColumn = currentCoordinates.getColumn() + directionCoordinates.getColumn();
            int newRow = currentCoordinates.getRow() + directionCoordinates.getRow();
            Coordinates newCoordinate = new Coordinates(newColumn, newRow);
            if (isValidateCoordinate(newCoordinate)) {
                result.add(newCoordinate);
            }
        }
        return result;
    }

}


//            COLUMN
//          0 1 2 3 4
//        0 * * * * *
//        1 * * * * *
//   ROW  2 * * * * *
//        3 * * * * *
//        4 * * * * *
//