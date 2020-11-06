package pt.ipbeja.connectfour.gui;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 1º Trabalho Pratico de Avaliação POO V2
 * @date : 11/04/2019 - 20:48h
 * *********************
 * @package : pt.ipbeja.connectfour.gui
 * @class : ConnectFourCircle	${CLASS}
 * ------
 * "description"
 *
 * class responsavel para criar o circulo e tratar dos aspectos da cor do tamanho do raio da Circle
 * definido com 21 em raio
 */
public class ConnectFourCircle extends Circle {

    private static final Color COLOR_PLAYER1 = Color.RED;
    private static final Color COLOR_PLAYER2 = Color.YELLOWGREEN;
    private static final Color COLOR_EMPTY = Color.DARKSLATEGRAY;
    private final int RADIUS = 21;


    /***
     * construtor da class
     */
    public ConnectFourCircle()
    {
        this.setFill(this.COLOR_EMPTY);
        this.setRadius(this.RADIUS);
    }

    /**
     * Sets fill color ('RED')
     */
    public void setPLAYER1 () {
        this.setFill(this.COLOR_PLAYER1);
    }



    /**
     *Sets fill color ('YELLOW')
     */
    public void setPLAYER2 ( ) {
        this.setFill(this.COLOR_PLAYER2);
    }


    /****
     * Sets fill color ('DARKSLATEGRAY')
     */
    public void setEMPTY()
    {
        this.setFill(this.COLOR_EMPTY);
    }
}//END CLASS
