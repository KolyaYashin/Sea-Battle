package sample;

public class Map {
    public int[][] map;
    public Map(){
        map=new int[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                map[i][j]=0;
            }
        }
    }
    public void write(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println("");
        }
    }
}