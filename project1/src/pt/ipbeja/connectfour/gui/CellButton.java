package pt.ipbeja.connectfour.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * CellButton
 * <p>
 *     classe responsavel do tipo de botão usado no jogo,
 *     cuida tbm da troca da imagens
 * *********************
 *
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 1º Trabalho Pratico de Avaliação POO V2
 * "Package Name: "   pt.ipbeja.connectfour.gui
 * @date 11/04/2019 * @tame 15:57
 */
public class CellButton extends Button
{

    private static final Image PLAY_EMPTY = new Image("/resources/picButton/Empty.png");
    private static final Image PLAYER1 = new Image("/resources/picButton/Player1.PNG");
    private static final Image PLAYER2 = new Image("/resources/picButton/Player2.png");
    private final ImageView imageView;
    private final int SIZE_BUTTON = 25;


    /***
     * Construtor da class CellButton
     * @param
     */
    public CellButton()
    {
        this.imageView = new ImageView(this.PLAY_EMPTY);
        this.imageView.setFitWidth(this.SIZE_BUTTON);
        this.imageView.setFitHeight(this.SIZE_BUTTON);


        this.setWidth(this.SIZE_BUTTON);
        this.setHeight(this.SIZE_BUTTON);
        this.setGraphic(this.imageView);
    }


    /**
     * Sets Tic image ('PLAYER1')
     */
    public void setTic()
    {
        this.imageView.setImage(this.PLAYER1);
    }

    /**
     * Sets Tac image ('PLAYER2')
     */
    public void setTac() {
        this.imageView.setImage(this.PLAYER2);
    }

}//end class CellButton
