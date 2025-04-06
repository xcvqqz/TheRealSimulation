package entity;




public class Coordinates {

    public int heightOfMap;
    public WidthOfMap widthOfMap;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;
        return heightOfMap == that.heightOfMap && widthOfMap == that.widthOfMap;
    }

    @Override
    public int hashCode() {
        int result = heightOfMap;
        result = 31 * result + widthOfMap.hashCode();
        return result;
    }

    public Coordinates(int heightOfMap, WidthOfMap widthOfMap) {
        this.heightOfMap = heightOfMap;
        this.widthOfMap = widthOfMap;
    }




}
