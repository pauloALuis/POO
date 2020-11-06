package pt.ipbeja.estig.po2.chess.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 27/mai/2019
 * @time 15:56
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.gui
 * @class Start
 * ********       to do    ********
 ******/
public class Start extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        Gui board = new Gui();

        Scene scene = new Scene(board);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
