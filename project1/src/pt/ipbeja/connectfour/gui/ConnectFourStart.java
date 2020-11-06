package pt.ipbeja.connectfour.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 1º Trabalho Pratico de Avaliação POO V2
 * @date : 11/04/2019 - 16:30h
 * *********************
 * @package : pt.ipbeja.connectfour.gui
 * @class : ConnectFourStart	${CLASS}
 * ------
 * "description"
 * Classe responsavel por iniciar o jogo trata da inicialização do jogo
 */
public class ConnectFourStart extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ConnectFourBoard board = new ConnectFourBoard();
        Scene scene = new Scene(board);
       //scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }




}//end class
