package sample;

public class Ship {
    public int palubs;
    public boolean isGorizontal;
    public int[] x;
    public int[] y;

    public Ship(int _palubs, boolean _isGorizontal, int _x, int _y){
        palubs=_palubs;
        isGorizontal=_isGorizontal;
        x=new int[palubs];
        y=new int[palubs];
        if(isGorizontal){
            for(int i=0;i<this.palubs;i++){
                x[i]=_x+i;
                y[i]=_y;
            }
        }
        if(!isGorizontal){
            for(int i=0;i<this.palubs;i++){
                x[i]=_x;
                y[i]=_y-i;
            }
        }

    }
    public void deleteInMap(Map map){
        try {
            for (int i = 0; i < this.palubs; i++) {

                map.map[y[i]][x[i]] = 0;

            }
        }catch(Exception e){

        }
    }
    public void toMap(Map map){
        try {
            for (int i = 0; i < this.palubs; i++) {

                map.map[y[i]][x[i]] = 2;
            }
        }catch (Exception e){
            this.deleteInMap(map);
            System.out.println("You can't set ship here!");
        }

         for (int i = 0; i < this.palubs; i++) {
            try {
                if (map.map[y[i] - 1][x[i]] == 0) {
                    map.map[y[i] - 1][x[i]] = 1;
                }
            }catch (Exception e){
                System.out.println("You can't set ship here!");
            }try{
               if (map.map[y[i] - 1][x[i] - 1] == 0) {
                   map.map[y[i] - 1][x[i] - 1] = 1;
               }
             }catch (Exception e){
                 System.out.println("You can't set ship here!");
             }try{
               if (map.map[y[i] - 1][x[i] + 1] == 0) {
                  map.map[y[i] - 1][x[i] + 1] = 1;
             }
             }catch (Exception e){
                 System.out.println("You can't set ship here!");
             }try{
              if (map.map[y[i] + 1][x[i]] == 0) {
                  map.map[y[i] + 1][x[i]] = 1;
              }
             }catch (Exception e){
                 System.out.println("You can't set ship here!");
             }try{
              if (map.map[y[i] + 1][x[i] + 1] == 0) {
                  map.map[y[i] + 1][x[i] + 1] = 1;
              }
             }catch (Exception e){
                 System.out.println("You can't set ship here!");
             }try{
               if (map.map[y[i] + 1][x[i] - 1] == 0) {
                   map.map[y[i] + 1][x[i] - 1] = 1;
               }
             }catch (Exception e){
                 System.out.println("You can't set ship here!");
             }try{
               if (map.map[y[i]][x[i] + 1] == 0) {
                   map.map[y[i]][x[i] + 1] = 1;
                }
             }catch (Exception e){
                 System.out.println("You can't set ship here!");
             }try{
               if (map.map[y[i]][x[i] - 1] == 0) {
                   map.map[y[i]][x[i] - 1] = 1;
              }
             }catch (Exception e){
                 System.out.println("You can't set ship here!");
             }
            }

    }
    public boolean isClear(Map map){
        for (int i = 0; i < this.palubs; i++) {
            if(map.map[y[i]][x[i]]==2||map.map[y[i]][x[i]]==1){
                return false;
            }

        }
        return true;
    }
}
