package pt.ipbeja.estig.po2.chess.gui;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import pt.ipbeja.estig.po2.chess.pieces.TypeOfPieces;


import static pt.ipbeja.estig.po2.chess.pieces.TypeOfPieces.*;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 29/mai/2019
 * @time 17:35
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.gui
 * @class ChessButton
 * ********       to do    ********
 ******/
public class ChessButton extends Button {


    private static final double SIZE_BUTTON = 49;
    private static final double SIZE_IMAGEM = 30;
    private final String STR_BORDER_STYLE = "-fx-border-color: #1a7f29; -fx-border-width: 1.8px;";
    private final String STR_BORDER_STYLE_TAKES = "-fx-border-color: #f48942; -fx-border-width: 1.8px;";

    private static final Image BLACK_BISHOP = new Image("/resources/pic/BLACK_BISHOP.png");
    private static final Image BLACK_KING = new Image("/resources/pic/BLACK_KING.png");
    private static final Image BLACK_KNIGHT = new Image("/resources/pic/BLACK_KNIGHT.png");
    private static final Image BLACK_PAWN = new Image("/resources/pic/BLACK_PAWN.png");
    private static final Image BLACK_QUEEN = new Image("/resources/pic/BLACK_QUEEN.png");
    private static final Image BLACK_ROOK = new Image("/resources/pic/BLACK_ROOK.png");


    private static final Image WHITE_BISHOP = new Image("/resources/pic/WHITE_BISHOP.png");
    private static final Image WHITE_KING = new Image("/resources/pic/WHITE_KING.png");
    private static final Image WHITE_KNIGHT = new Image("/resources/pic/WHITE_KNIGHT.png");
    private static final Image WHITE_PAWN = new Image("/resources/pic/WHITE_PAWN.png");
    private static final Image WHITE_QUEEN = new Image("/resources/pic/WHITE_QUEEN.png");
    private static final Image WHITE_ROOK = new Image("/resources/pic/WHITE_ROOK.png");



    private final ImageView imageView = new ImageView();



    /***
     * Construtor da class CellButton
     */
    public ChessButton() {
         this.buttonSize();
    }


    /*****
     * remove a imagem do botão e mantei o formato do botão
     */
    public void removeImageView(){
        this.buttonSize();
        this.setGraphic(null);

    }


    /*****
     * metodo responsavel por cuidar de mudar a cor do BackGround do botão
     */
    public void setColor()
    {
        this.setBackground(new Background(new BackgroundFill(Color.SILVER, null, null)));
    }

    /******
     * metodo responsavel por cuidar de por no botão a BorderStyle
     */
    public void setBorderStyle(){
        this.setStyle(this.STR_BORDER_STYLE);
    }

    /******
     * metodo responsavel por alterar a cor do botão para laranja
     */
    public void setBorderStyleTakes(){
        this.setStyle(this.STR_BORDER_STYLE_TAKES);
    }

    /*****
     * metodo que apaga a BorderStyle dos botões
     */
    public void setEmptyBorderStyle(){
        this.setStyle(null);
    }


    /*****
     * metodo para formatar o tamanho
     */
    protected void buttonSize()
    {
        this.imageView.setFitWidth(this.SIZE_IMAGEM);
        this.imageView.setFitHeight(this.SIZE_IMAGEM);
        this.setPrefSize(this.SIZE_BUTTON, this.SIZE_BUTTON);
    }


    /******
     * metodo que atribui as imagens ao botão mediate o tipo depeças
     * @param type - tipo de peça corresponde a uma imagem
     */
    public void setPicButton(TypeOfPieces type)
    {
        switch(type) {
            case Pb:
                this.imageView.setImage(this.BLACK_PAWN);
                break;

            case Pw:
                this.imageView.setImage(this.WHITE_PAWN);
                break;
    //-----------------------------------------------------------
            case Bb:
                this.imageView.setImage(this.BLACK_BISHOP);
                break;

            case Bw:
                this.imageView.setImage(this.WHITE_BISHOP);
                break;
    //-------------------------------------------------------------
            case Cb:
                this.imageView.setImage(this.BLACK_KNIGHT);
                break;

            case Cw:
                this.imageView.setImage(this.WHITE_KNIGHT);
                break;
    //---------------------------------------------------------------

            case Tb:
                this.imageView.setImage(this.BLACK_ROOK);
                break;

            case Tw:
                this.imageView.setImage(this.WHITE_ROOK);
                break;
    //---------------------------------------------------------------

            case Rb:
                this.imageView.setImage(this.BLACK_QUEEN);
                break;

            case Rw:
                this.imageView.setImage(this.WHITE_QUEEN);
                break;

    //---------------------------------------------------------------
            case Db:
                this.imageView.setImage(this.BLACK_KING);
                break;

            case Dw:
                this.imageView.setImage(this.WHITE_KING);
                break;
    //---------------------------------------------------------------
            default:
                throw new IllegalStateException("Unexpected value: " + type + "não corresponde");
        }

        this.buttonSize();
        this.setGraphic(this.imageView);
    }



}