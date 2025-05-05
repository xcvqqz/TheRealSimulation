package entity;


import java.util.List;

public class Coordinates {

    private int column;
    private int row;

    public Coordinates(int column, int row) {
        this.column = column;
        this.row = row;
    }



    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;
        return column == that.column && row == that.row;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }



    public boolean readyForAttack(List<Coordinates> allPathList){
        return this.equals(allPathList.get(allPathList.size()-2));
    }



}
