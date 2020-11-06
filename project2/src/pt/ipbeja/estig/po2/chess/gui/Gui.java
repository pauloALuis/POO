package pt.ipbeja.estig.po2.chess.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

import pt.ipbeja.estig.po2.chess.model.ColorPiece;
import pt.ipbeja.estig.po2.chess.model.GameModel;
import pt.ipbeja.estig.po2.chess.pieces.TypeOfPieces;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 27/mai/2019
 * @time 15:58
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.gui
 * @class Gui
 * ********       to do    ********
 * A Gui é do tipo BoarderPane como objectos em certas localização em Layout
 *  BorderPane - configuração a baixo demonstra a organização da Gui
 *  ______________________________________________
 * |     top -  labelABC                          |
 * |______________________________________________|
 * |left VBOX1 |center - paneButton |right - VBOX |
 * |___________|____________________|_____________|
 * |       bottom - Label                         |
 * |______________________________________________|
 *
 *
 *
 *
 ******/
public class Gui extends BorderPane implements View{



    private GameModel gameModel;
    private ChessButton[][] buttons;
    private GridPane paneButton;
    private Label labelABC = new Label();


    public Gui()
    {

        this.gameModel = new GameModel(this);
        this.paneButton = new GridPane();
        this.buttons = new ChessButton[gameModel.getSIZE() ][gameModel.getSIZE()];
        this.createButtonBoard();
        this.createBoard();
    }


    /******
     * criar a board e incluir os objectos
     */
    private void createBoard()
    {
        this.setTop(this.labelABC);//inserir no top da BoarderPane
        this.setBottom(new Label(this.labelABC.getText()));//inserir no em Baixo da BoarderPane
        this.setCenter(this.paneButton);//inserir no centro da BoarderPane

        this.createLabelForRightLeft();
        this.gameModel.updateButtons();
        this.gameModel.disableButton(0, 0);


    }

    /***
     * cria grid de botão de jogos 8 * 8
     */
    private void createButtonBoard()
    {
        ButtonHandler bHandler = new ButtonHandler();
        String stringLabel = "";

        for (int line = 0; line < this.gameModel.getSIZE(); line++)
        {
            stringLabel ="" ;
            for (int col = 0; col < this.gameModel.getSIZE(); col++)
            {
                String str ="";
                //this.gameModel.getAlgebraABC(col) + (line +  1);
                ChessButton button = new ChessButton( );
                this.buttons[line][col] = button;

                if (((col + line) % 2 ) == 0 )
                {
                    button.setColor();
                    button.setId("" + this.gameModel.getAlgebraABC(col)+ ColorPiece.BLACK + (line +  1));//acriar id dos botõe
                }
                else {
                   button.setId("" + this.gameModel.getAlgebraABC(col)+ ColorPiece.WHITE + (line +  1));//acriar id dos botõe
                }

                button.buttonSize();
                button.setOnAction(bHandler);
                this.paneButton.add(button, col, line);

                stringLabel += "             " + this.gameModel.getAlgebraABC(col);

            }
        }
        //atualizar a Label
        this.labelABC.setText(stringLabel);
    }



    /*****
     * criar label para esquerda e direita da BorderPane
     */
    private void createLabelForRightLeft()
    {
        VBox vBox1 = new VBox();
        VBox vBox = new VBox();

        for (int i = 0; i < this.gameModel.getSIZE(); i++)
        {
            String str = (i + 1)+ "";
            Label label = new Label( str);
            Label label1 = new Label(str);

            label.setPrefSize(20, 50);
            label1.setPrefSize(20, 50);

            vBox.getChildren().add( label);
            vBox1.getChildren().add(label1);
        }

        /****
         * Pôr as vbox a Direita e Esquerda da BoarderPane
         */
        this.setRight(vBox);
        this.setLeft(vBox1);
    }


    /******
     * remover a imagem o borderStyle depende
     * @param line
     * @param col
     * @param pieces
     */
    @Override
    public void updateStyleButton(int line, int col, TypeOfPieces pieces)
    {
        if(pieces != null )
        {
            this.buttons[line][col].setPicButton(pieces);
        }
        else
            {
            this.buttons[line][col].removeImageView();
        }
        this.buttons[line][col].setEmptyBorderStyle();


    }

    /******
     * Altera a bordeStyle do possiveis movimento e captura dependendo do estado da flag
     * @param line
     * @param col
     * @param flagPossibleMove
     */
    @Override
    public void updatePossible(int line, int col, boolean flagPossibleMove)
    {
        if (flagPossibleMove){
            this.buttons[line][col].setBorderStyle();
        }else {
            this.buttons[line][col].setBorderStyleTakes();

        }

        desablePosition(line, col, false);
    }



    /***
     * Desativar o botão quando for ultimo click ou seja desativa o botão primido quando fazer o maximo de
     * jogadas permitida numa coluna
     *      * 1 min de tempo gasto
     * @param line coordenada da linha
     * @param col coordenada da linha
     * @param enable
     */
    @Override
    public void desablePosition(int line, int col, boolean enable) {
          assert(line >= 0 && line <= (this.gameModel.getSIZE() - 1));
          assert(col >= 0 && col <= (this.gameModel.getSIZE() - 1));

          this.buttons[line][col].setDisable(enable);

    }

    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            ChessButton button = (ChessButton)event.getSource();
            /**
             *
             */
            int line =   Gui.this.paneButton.getRowIndex(button);
            int col  =   Gui.this.paneButton.getColumnIndex(button);
            System.out.println("("+ line + " , " + col + ")__ID: " +  button.getId()  + "- " + button.getText());

            Gui.this.gameModel.selectedPlace(line, col);

        }
    }





}
