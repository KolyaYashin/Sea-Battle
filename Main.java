package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;


public class Main extends Application {
    private void drawMap(Map map, Pane pane,int startX,int startY,ImageView[][] images){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(map.map[i][j]==0) {
                    images[i][j] = new ImageView("/sample/cell.jpg");
                    images[i][j].setLayoutX(startX + j * 50 + 25);
                    images[i][j].setLayoutY(startY + i * 50 + 25);
                    pane.getChildren().add(images[i][j]);
                }
                if(map.map[i][j]==2) {
                    images[i][j] = new ImageView("/sample/1_palubsShip.jpg");
                    images[i][j].setLayoutX(startX + j * 50 + 25);
                    images[i][j].setLayoutY(startY + i * 50 + 25);
                    pane.getChildren().add(images[i][j]);

                }
                if(map.map[i][j]==1){
                    images[i][j] = new ImageView("/sample/krestik.jpg");
                    images[i][j].setLayoutX(startX + j * 50 + 25);
                    images[i][j].setLayoutY(startY + i * 50 + 25);
                    pane.getChildren().add(images[i][j]);
                }
            }
        }

    }
    private void redrawMap(Map map,ImageView[][] images){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(map.map[i][j]==0){
                    Image im=new Image("/sample/cell.jpg");
                    images[i][j].setImage(im);
                }
                if(map.map[i][j]==2){
                    Image im=new Image("/sample/1_palubsShip.jpg");
                    images[i][j].setImage(im);
                }
                if(map.map[i][j]==1){
                    Image im=new Image("/sample/krestik.jpg");
                    images[i][j].setImage(im);
                }
            }
        }
    }
    @Override
    public void start(Stage stage) throws Exception{
        Map map1=new Map();
        Map map2=new Map();
        ImageView[][] images=new ImageView[10][10];
        ImageView[][] images2=new ImageView[10][10];
        Pane pane=new Pane();
        Scene scene=new Scene(pane,500,500);
        Button btn=new Button("Play");
        btn.setPrefWidth(125);
        btn.setPrefHeight(60);
        btn.setLayoutX(250-btn.getPrefWidth()/2);
        btn.setLayoutY(250-btn.getPrefHeight()/2);
        pane.setStyle("-fx-background-color: blue;");
        btn.setStyle("-fx-font-size: 2.5em; -fx-border-color: red; -fx-border-width: 4px; -fx-text-fill:#DC143C; -fx-background-color: #66CDAA");
        btn.setOnMouseClicked(event -> {
            Pane pane1=new Pane();
            Button exitBtn=new Button("<-");
            Button nextBtn=new Button("next player");
            Text text=new Text("Player 1");
            text.setLayoutX(455);
            text.setLayoutY(60);
            text.setStyle("-fx-font: normal bold 20px 'serif';");
            nextBtn.setLayoutX(900);
            nextBtn.setLayoutY(10);
            exitBtn.setPrefHeight(30);
            exitBtn.setPrefWidth(40);
            pane1.getChildren().add(exitBtn);
            pane1.getChildren().add(nextBtn);
            pane1.getChildren().add(text);
            Scene scene1=new Scene(pane1,screenSize.getWidth(),screenSize.getHeight());
            stage.setScene(scene1);
            stage.setTitle("Player 1");
            stage.setX(0);
            stage.setY(0);
            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    map1.map[i][j]=0;
                }
            }
            drawMap(map1,pane1,425,50,images);
            ImageView[] palub_1=new ImageView[4];
            for(int i=0;i<4;i++){
                final int ii=i;
                palub_1[i]=new ImageView("/sample/1_palubsShip.jpg");
                palub_1[i].setLayoutY(50);
                palub_1[i].setLayoutX(50+i*75);
                palub_1[i].setOnMouseDragged(event1 -> {
                    if(palub_1[ii].getRotate()==0) {
                        palub_1[ii].setLayoutX(event1.getSceneX() - 25);
                        palub_1[ii].setLayoutY(event1.getSceneY() - 25);
                    }
                });
                pane1.getChildren().add(palub_1[i]);
                palub_1[ii].setOnMouseReleased(event1 -> {
                    double x=event1.getSceneX();
                    double y=event1.getSceneY();
                    if(x>425&&x<925&&y>50&&y<550){
                        System.out.println((x-425)+" "+((x-425)/50)+" "+((y-50)/50)+" "+(y-50));
                        Ship ship4=new Ship(1,palub_1[ii].getRotate()==0,(int)Math.round(((x-425)/50))-1,(int)Math.round(((y-50)/50))-1);
                        ship4.toMap(map1);
                        redrawMap(map1,images);
                        pane1.getChildren().remove(palub_1[ii]);
                    }
                });
            }
            ImageView[] palub_2=new ImageView[3];
            for(int i=0;i<3;i++){
                final int ii=i;
                palub_2[i]=new ImageView("/sample/2_palubsShip.jpg");
                palub_2[i].setLayoutY(150);
                palub_2[i].setLayoutX(50+i*125);
                palub_2[i].setOnMouseDragged(event1 -> {
                    if(palub_2[ii].getRotate()==0) {
                        palub_2[ii].setLayoutX(event1.getSceneX() - 25);
                        palub_2[ii].setLayoutY(event1.getSceneY() - 25);
                    }else{
                        palub_2[ii].setLayoutX(event1.getSceneX() - 50);
                        palub_2[ii].setLayoutY(event1.getSceneY() -50);
                    }

                });
                palub_2[ii].setOnMouseClicked(event2 -> {
                    if(event2.getClickCount()==2) {
                        if (palub_2[ii].getRotate() == 90) {
                            palub_2[ii].setRotate(0);
                        } else {
                            palub_2[ii].setRotate(90);
                        }
                    }
                });
                pane1.getChildren().add(palub_2[i]);
                palub_2[ii].setOnMouseReleased(event1 -> {
                    double x=event1.getSceneX();
                    double y=event1.getSceneY();
                    if(x>425&&x<925&&y>50&&y<550){
                        System.out.println((x-425)+" "+((x-425)/50)+" "+((y-50)/50)+" "+(y-50));
                        Ship ship4=new Ship(2,palub_2[ii].getRotate()==0,(int)Math.round(((x-425)/50))-1,(int)Math.round(((y-50)/50))-1);
                        ship4.toMap(map1);
                        redrawMap(map1,images);
                        pane1.getChildren().remove(palub_2[ii]);
                    }
                });
            }
            ImageView[] palub_3=new ImageView[2];
            for(int i=0;i<2;i++){
                final int ii=i;
                palub_3[i]=new ImageView("/sample/3_palubsShip.jpg");
                palub_3[i].setLayoutY(150+((i+1)*100));
                palub_3[i].setLayoutX(50);
                palub_3[i].setOnMouseDragged(event1 -> {
                    if(palub_3[ii].getRotate()==0) {
                        palub_3[ii].setLayoutX(event1.getSceneX() - 25);
                        palub_3[ii].setLayoutY(event1.getSceneY() - 25);
                    }else{
                        palub_3[ii].setLayoutX(event1.getSceneX() - 75);
                        palub_3[ii].setLayoutY(event1.getSceneY() -75);
                    }

                });
                palub_3[ii].setOnMouseClicked(event2 -> {
                    if(event2.getClickCount()==2) {
                        if (palub_3[ii].getRotate() == 90) {
                            palub_3[ii].setRotate(0);
                        } else {
                            palub_3[ii].setRotate(90);
                        }
                    }
                });
                pane1.getChildren().add(palub_3[i]);
                palub_3[ii].setOnMouseReleased(event1 -> {
                    double x=event1.getSceneX();
                    double y=event1.getSceneY();
                    if(x>425&&x<925&&y>50&&y<550){
                        System.out.println((x-425)+" "+((x-425)/50)+" "+((y-50)/50)+" "+(y-50));
                        Ship ship4=new Ship(3,palub_3[ii].getRotate()==0,(int)Math.round(((x-425)/50))-1,(int)Math.round(((y-50)/50))-1);
                        ship4.toMap(map1);
                        redrawMap(map1,images);
                        pane1.getChildren().remove(palub_3[ii]);
                    }
                });

            }
            ImageView palub_4=new ImageView("/sample/4_palubsShip.jpg");
            palub_4.setLayoutY(450);
            palub_4.setLayoutX(50);
            palub_4.setOnMouseDragged(event1 -> {
                if(palub_4.getRotate()==0) {
                    palub_4.setLayoutX(event1.getSceneX() - 25);
                    palub_4.setLayoutY(event1.getSceneY() - 25);
                }else{
                    palub_4.setLayoutX(event1.getSceneX() - 100);
                    palub_4.setLayoutY(event1.getSceneY() -100);
                }

            });
            palub_4.setOnMouseClicked(event2 -> {
                if(event2.getClickCount()==2) {
                    if (palub_4.getRotate() == 90) {
                        palub_4.setRotate(0);
                    } else {
                        palub_4.setRotate(90);
                    }
                }
            });
            pane1.getChildren().add(palub_4);
            palub_4.setOnMouseReleased(event1 -> {
                double x=event1.getSceneX();
                double y=event1.getSceneY();
                if(x>425&&x<925&&y>50&&y<550){
                    System.out.println((x-425)+" "+((x-425)/50)+" "+((y-50)/50)+" "+(y-50));
                    Ship ship4=new Ship(4,palub_4.getRotate()==0,(int)Math.round(((x-425)/50))-1,(int)Math.round(((y-50)/50))-1);
                    ship4.toMap(map1);
                    redrawMap(map1,images);
                    pane1.getChildren().remove(palub_4);
                }
            });
            exitBtn.setOnMouseClicked(event1 -> {
                stage.setScene(scene);
                stage.setTitle("Sea Battle");
            });
            nextBtn.setOnMouseClicked(event1 -> {
                    Pane pane2 = new Pane();
                    Button exitBtn2 = new Button("<-");
                    Button nextBtn2 = new Button("Start");
                    Text text1=new Text("Player 2");
                    text1.setLayoutX(455);
                    text1.setLayoutY(60);
                    text1.setStyle("-fx-font: normal bold 20px 'serif'");
                    nextBtn2.setLayoutX(925);
                    nextBtn2.setLayoutY(10);
                    exitBtn2.setPrefHeight(30);
                    exitBtn2.setPrefWidth(40);
                    pane2.getChildren().add(exitBtn2);
                    pane2.getChildren().add(nextBtn2);
                    pane2.getChildren().add(text1);
                    Scene scene2 = new Scene(pane2, screenSize.getWidth(), screenSize.getHeight());
                    stage.setScene(scene2);
                    stage.setX(0);
                    stage.setY(0);
                    stage.setTitle("Player 2");
                    for (int i = 0; i < 10; i++) {
                        for (int j = 0; j < 10; j++) {
                            map2.map[i][j] = 0;
                        }
                    }
                    drawMap(map2, pane2, 425, 50, images2);
                    ImageView[] palub2_1 = new ImageView[4];
                    for (int i = 0; i < 4; i++) {
                        final int ii = i;
                        palub2_1[i] = new ImageView("/sample/1_palubsShip.jpg");
                        palub2_1[i].setLayoutY(50);
                        palub2_1[i].setLayoutX(50 + i * 75);
                        palub2_1[i].setOnMouseDragged(event3 -> {
                            if (palub2_1[ii].getRotate() == 0) {
                                palub2_1[ii].setLayoutX(event3.getSceneX() - 25);
                                palub2_1[ii].setLayoutY(event3.getSceneY() - 25);
                            }
                        });
                        pane2.getChildren().add(palub2_1[i]);
                        palub2_1[ii].setOnMouseReleased(event3 -> {
                            double x = event3.getSceneX();
                            double y = event3.getSceneY();
                            if (x > 425 && x < 925 && y > 50 && y < 550) {
                                System.out.println((x - 425) + " " + ((x - 425) / 50) + " " + ((y - 50) / 50) + " " + (y - 50));
                                Ship ship4 = new Ship(1, palub2_1[ii].getRotate() == 0, (int) Math.round(((x - 425) / 50)) - 1, (int) Math.round(((y - 50) / 50)) - 1);
                                ship4.toMap(map2);
                                redrawMap(map2, images2);
                                pane2.getChildren().remove(palub2_1[ii]);
                            }
                        });
                    }
                    ImageView[] palub2_2 = new ImageView[3];
                    for (int i = 0; i < 3; i++) {
                        final int ii = i;
                        palub2_2[i] = new ImageView("/sample/2_palubsShip.jpg");
                        palub2_2[i].setLayoutY(150);
                        palub2_2[i].setLayoutX(50 + i * 125);
                        palub2_2[i].setOnMouseDragged(event3 -> {
                            if (palub2_2[ii].getRotate() == 0) {
                                palub2_2[ii].setLayoutX(event3.getSceneX() - 25);
                                palub2_2[ii].setLayoutY(event3.getSceneY() - 25);
                            } else {
                                palub2_2[ii].setLayoutX(event3.getSceneX() - 50);
                                palub2_2[ii].setLayoutY(event3.getSceneY() - 50);
                            }

                        });
                        palub2_2[ii].setOnMouseClicked(event3 -> {
                            if (event3.getClickCount() == 2) {
                                if (palub2_2[ii].getRotate() == 90) {
                                    palub2_2[ii].setRotate(0);
                                } else {
                                    palub2_2[ii].setRotate(90);
                                }
                            }
                        });
                        pane2.getChildren().add(palub2_2[i]);
                        palub2_2[ii].setOnMouseReleased(event3 -> {
                            double x = event3.getSceneX();
                            double y = event3.getSceneY();
                            if (x > 425 && x < 925 && y > 50 && y < 550) {
                                System.out.println((x - 425) + " " + ((x - 425) / 50) + " " + ((y - 50) / 50) + " " + (y - 50));
                                Ship ship4 = new Ship(2, palub2_2[ii].getRotate() == 0, (int) Math.round(((x - 425) / 50)) - 1, (int) Math.round(((y - 50) / 50)) - 1);
                                ship4.toMap(map2);
                                redrawMap(map2, images2);
                                pane2.getChildren().remove(palub2_2[ii]);
                            }
                        });
                    }
                    ImageView[] palub2_3 = new ImageView[2];
                    for (int i = 0; i < 2; i++) {
                        final int ii = i;
                        palub2_3[i] = new ImageView("/sample/3_palubsShip.jpg");
                        palub2_3[i].setLayoutY(150 + ((i + 1) * 100));
                        palub2_3[i].setLayoutX(50);
                        palub2_3[i].setOnMouseDragged(event3 -> {
                            if (palub2_3[ii].getRotate() == 0) {
                                palub2_3[ii].setLayoutX(event3.getSceneX() - 25);
                                palub2_3[ii].setLayoutY(event3.getSceneY() - 25);
                            } else {
                                palub2_3[ii].setLayoutX(event3.getSceneX() - 75);
                                palub2_3[ii].setLayoutY(event3.getSceneY() - 75);
                            }

                        });
                        palub2_3[ii].setOnMouseClicked(event3 -> {
                            if (event3.getClickCount() == 2) {
                                if (palub2_3[ii].getRotate() == 90) {
                                    palub2_3[ii].setRotate(0);
                                } else {
                                    palub2_3[ii].setRotate(90);
                                }
                            }
                        });
                        pane2.getChildren().add(palub2_3[i]);
                        palub2_3[ii].setOnMouseReleased(event3 -> {
                            double x = event3.getSceneX();
                            double y = event3.getSceneY();
                            if (x > 425 && x < 925 && y > 50 && y < 550) {
                                System.out.println((x - 425) + " " + ((x - 425) / 50) + " " + ((y - 50) / 50) + " " + (y - 50));
                                Ship ship4 = new Ship(3, palub2_3[ii].getRotate() == 0, (int) Math.round(((x - 425) / 50)) - 1, (int) Math.round(((y - 50) / 50)) - 1);
                                ship4.toMap(map2);
                                redrawMap(map2, images2);
                                pane2.getChildren().remove(palub2_3[ii]);
                            }
                        });

                    }
                    ImageView palub2_4 = new ImageView("/sample/4_palubsShip.jpg");
                    palub2_4.setLayoutY(450);
                    palub2_4.setLayoutX(50);
                    palub2_4.setOnMouseDragged(event3 -> {
                        if (palub2_4.getRotate() == 0) {
                            palub2_4.setLayoutX(event3.getSceneX() - 25);
                            palub2_4.setLayoutY(event3.getSceneY() - 25);
                        } else {
                            palub2_4.setLayoutX(event3.getSceneX() - 100);
                            palub2_4.setLayoutY(event3.getSceneY() - 100);
                        }

                    });
                    palub2_4.setOnMouseClicked(event3 -> {
                        if (event3.getClickCount() == 2) {
                            if (palub2_4.getRotate() == 90) {
                                palub2_4.setRotate(0);
                            } else {
                                palub2_4.setRotate(90);
                            }
                        }
                    });
                    pane2.getChildren().add(palub2_4);
                    palub2_4.setOnMouseReleased(event3 -> {
                        double x = event3.getSceneX();
                        double y = event3.getSceneY();
                        if (x > 425 && x < 925 && y > 50 && y < 550) {
                            System.out.println((x - 425) + " " + ((x - 425) / 50) + " " + ((y - 50) / 50) + " " + (y - 50));
                            Ship ship4 = new Ship(4, palub2_4.getRotate() == 0, (int) Math.round(((x - 425) / 50)) - 1, (int) Math.round(((y - 50) / 50)) - 1);
                            ship4.toMap(map2);
                            redrawMap(map2, images2);
                            pane2.getChildren().remove(palub2_4);
                        }
                    });
                    exitBtn2.setOnMouseClicked(event3 -> {
                        stage.setScene(scene1);
                        stage.setTitle("Player 1");
                    });
                    nextBtn2.setOnMouseClicked(event2 -> {
                        Pane pane3=new Pane();
                        stage.setX(0);
                        stage.setY(0);
                        drawMap(map1,pane3,50,50,images);
                        Scene scene3=new Scene(pane3, screenSize.getWidth(),screenSize.getHeight());
                        stage.setScene(scene3);
                        stage.setTitle("Battle");

                    });
                });

        });
        pane.getChildren().add(btn);
        stage.setScene(scene);
        stage.setTitle("Sea Battle");
        stage.show();
    }
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static void main(String[] args) {launch(args); }
}
