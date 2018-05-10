package sample;

import javafx.scene.control.TextArea;

public class Map {
    public int[][] map;
    public TextArea[][] tochki;
    public Map(){
        map=new int[10][10];
        tochki=new TextArea[10][10];
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                map[i][j]=0;
                tochki[i][j]=new TextArea("ne tochka");
            }
        }
    }
}
