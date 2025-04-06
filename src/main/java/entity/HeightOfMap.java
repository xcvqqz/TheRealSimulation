package entity;

public enum HeightOfMap {

    One(1), two(2), three(3), four(4), five(5), six(6);


    HeightOfMap(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    private int id;

    public HeightOfMap getTitleByOrdinal(int id){
       for(HeightOfMap value : HeightOfMap.values()){
           if(value.id == id){
               return value;
           }
        }
       throw new IllegalArgumentException("Invalid id " + id);
    }


}


//    seven(7), eight(8),
//    nine(9), ten(10), eleven(11), twelve(12), thirteen(13), fourteen(14),
//    fifteen(15), sixteen(16);



