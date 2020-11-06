package pt.ipbeja.connectfour.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import pt.ipbeja.connectfour.model.Cell;
import pt.ipbeja.connectfour.model.ConnectFourModel;

/**
 * ConnectFourBoard
 * <p>
 *     classe responsavel pela parte grafica do jogo. bem como criar os botões,
 *     elipse, gridPane e os elemntos do Menus
 * *********************
 *
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 1º Trabalho Pratico de Avaliação POO V2
 * "Package Name: "   pt.ipbeja.connectfour.gui
 * @date 11/04/2019 * @tame 15:55
 */
public class ConnectFourBoard extends VBox implements View {

    private ConnectFourModel gameModel;
    private ConnectFourCircle[][] circles;
    private CellButton[][] buttons;
    private  GridPane gridForCircles;
    protected static GridPane gridForButtons;
    final String MENU_GAME = "Menu";
    private MenuButton menuBar;


    // create menuitems
    private MenuItem itemNewGame;
    private MenuItem itemUndo;
    private MenuItem itemRedo;


    /****
     * construtor da class ConnectFourBoard
     */
    public ConnectFourBoard()
    {
        this.gameModel =  new ConnectFourModel(this );
        final int ROW = this.gameModel.getSIZE_COL() - this.gameModel.getSIZE_ROW();

        this.gridForCircles = new GridPane();
        this.gridForButtons = new GridPane();

        this.circles = new ConnectFourCircle[gameModel.getSIZE_ROW() ][gameModel.getSIZE_COL()];
        this.buttons = new CellButton[ROW][gameModel.getSIZE_COL()];
        this.menuBar = new MenuButton();

        this.createMenuBar();
        this.createBoard();
    }


    /*****
     * cria a board grafica com todos os seus componentes
     */
    private void createBoard()
    {
        this.createButtonBoard();
        this.createCircleBoard();
        this.disableItemMenus();

    }


    /*****
     *
     * cria o menuBar
     */
    private void createMenuBar()
    {
        final String ITEN_NEW_GAME = "NEW GAME";
        final String ITEN_REDO = "REDO";
        final String ITEN_UNDO = "UNDO";

        // create menuitems
        this.itemNewGame = new MenuItem();
        this.itemUndo = new MenuItem();
        this.itemRedo = new MenuItem();

        this.createItemMenu(ITEN_NEW_GAME, ITEN_UNDO, ITEN_REDO);
        this.createHandlerItemMenu();

        this.menuBar.getItems().addAll(itemNewGame,itemRedo,itemUndo);

    }


    /***
     * responsavel por atribui o ID dos item Menu e mudar o icon das item menu
      * @param ITEN_NEW_GAME
     * @param ITEN_UNDO
     * @param ITEN_REDO
     */
    private void createItemMenu(String ITEN_NEW_GAME, String ITEN_UNDO, String ITEN_REDO)
    {


        this.menuBar.setGraphic(this.configureImage(this.MENU_GAME));
        this.changeViewItemMenu(this.itemNewGame, ITEN_NEW_GAME);
        this.changeViewItemMenu(this.itemRedo, ITEN_REDO);
        this.changeViewItemMenu(this.itemUndo, ITEN_UNDO);

        this.itemNewGame.setId(ITEN_NEW_GAME);
        this.itemUndo.setId(ITEN_UNDO);
        this.itemRedo.setId(ITEN_REDO);


    }

    /*****
     *muda a imagem do itemMenu
     * @param itemMenu
     * @param namePIC
     */
    private void changeViewItemMenu(MenuItem itemMenu, String namePIC)
    {
        itemMenu.setGraphic(this.configureImage(namePIC));
    }


    /*****
     * configura a imagem inserida nos menu item
     * @param namePIC
     * @return ImageView
     */
    private ImageView configureImage(String namePIC) {
        final int WIDTH_HEIGTH_IMAGEM = 15;

        ImageView openView = new ImageView("resources/picMenuBar/" + namePIC + ".png");
        openView.setFitWidth(WIDTH_HEIGTH_IMAGEM);
        openView.setFitHeight(WIDTH_HEIGTH_IMAGEM);
        return openView;
    }


    /****
     * cria o handler dos Item Menu
     */
    private void createHandlerItemMenu() {
        HandlerItemMenu menuHandler = new HandlerItemMenu();
        this.itemNewGame.setOnAction(menuHandler);
        this.itemUndo.setOnAction(menuHandler);
        this.itemRedo.setOnAction(menuHandler);
    }



    /***
     * cria grid de botão de jogos 1 * 6
     */
    private void createButtonBoard()
    {
        ButtonHandler bHandler = new ButtonHandler();

        final int LINE = 0;
            for (int col = 0; col < gameModel.getSIZE_COL(); col++)
            {
                CellButton button = new CellButton();
                button.setOnAction(bHandler);
                button.setId(""+ LINE + col);//acriar id dos botõe

                this.gridForButtons.add(button, col, LINE);
                this.buttons[LINE][col] = button;

            }

    }



    /*****
     *criar grelha de circlulos na gridPane com dimensão ROW * Col
     */
    private void createCircleBoard()
    {
        for (int line = 0; line < gameModel.getSIZE_ROW(); line++)
        {
            for (int col = 0; col < gameModel.getSIZE_COL(); col++)
            {
                      ConnectFourCircle circles = new ConnectFourCircle();

                      this.circles[line][col] = circles;
                      this.gridForCircles.add(circles, col, line);
            }
        }
        this.getChildren().addAll(this.menuBar, this.gridForButtons, this.gridForCircles);
    }


    /******
     * para por desativo todos os botões do ItemMenu
     */
    private void disableItemMenus()
    {
        this.isDisableItemNewGame(true);
        this.isDisableItemRedo(true);
        this.isDisableItemUndo(true);
    }


    /*******
     * trata de pedir o nome do utilizador que tem o novo score e verificar se o nome tem maximo de 7 carateres
     * @return devolve o nome do joagador
     */
    @Override
    public String displayTextImputDialog() {

        final int MAX_CARATER = 7;

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Livro de Record");//.setTitle("Text Input Dialog");
        dialog.setHeaderText("Digite o nome,para registrar a marca");//.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");

        String nomePlay="";
        do
        {
           nomePlay = dialog.showAndWait().get();
        }while ( ( nomePlay.length()  > MAX_CARATER ) || (nomePlay.equals("")) );




        return nomePlay;
    }

    /*****
     * serve para desativar o botão de Redo
     * @param isDisable
     */
    @Override
    public void isDisableItemRedo(boolean isDisable)

    {
        this.itemRedo.setDisable(isDisable);
    }

    /****
     * desativar o itemMenu Undo
     * @param isDisable
     */
    @Override
    public void isDisableItemUndo(boolean isDisable) {
        this.itemUndo.setDisable(isDisable);

    }


    /****
     * limpa os objetos da board e cria a nova board
     */
    @Override
    public void clearAndResetBoard()
    {
            this.getChildren().clear();
            this.gridForButtons.getChildren().clear();
            this.gridForCircles.getChildren().clear();
            this.createBoard();
    }

    @Override
    public void isDisableItemNewGame(boolean isDisable) {
        this.itemNewGame.setDisable(isDisable);
    }


    /***
     * metodo que trata de mudar a cor dos circulos atraves de um pedido do model
     * @param cellPlayer - recebe a Cell "enum " - recebe o joagor que jogo em determinada posição
     * @param line - recece a coordena da em linha do circulo vazio p/ mudar a cor
     * @param col  - recece a coordena da em coluna do circulo vazio p/ mudar a cor
     */
    @Override
    public void changeColor(Cell cellPlayer,  int line, int col)
    {
        assert(line >= 0 && line <= (this.gameModel.getSIZE_ROW() - 1));
        assert(col >= 0 && col <= (this.gameModel.getSIZE_COL() - 1));
        assert(cellPlayer == Cell.PLAYER1 || cellPlayer == Cell.PLAYER2 || cellPlayer == Cell.EMPTY);

        if(cellPlayer == Cell.PLAYER1)
        {
            this.circles[line][col].setPLAYER1(); // Se o CELL for PLAYER1, pedimos ao botão para colocar a A COR VERMELHA (PLAYER1)
        }
        else if(cellPlayer == Cell.PLAYER2)
        {
            this.circles[line][col].setPLAYER2(); // Caso contrário, a Cell será O e pedimos ao botão para colocar a COR VERDE O (PLAYER2)
        }
        else {
            this.circles[line][col].setEMPTY(); // Caso contrário, a Cell será O e pedimos ao botão para colocar a COR VERDE O (PLAYER2)
        }
    }


    /***
     * Desativar o botão quando for ultimo click ou seja desativa o botão primido quando fazer o maximo de
     * jogadas permitida numa coluna
     *      * 1 min de tempo gasto
     * @param row
     * @param col
     */
    @Override
    public void disablePosition(int row, int col)
    {
        assert(row >= 0 && row <= (this.gameModel.getSIZE_ROW() - 1));
        assert(col >= 0 && col <= (this.gameModel.getSIZE_COL() - 1));

        this.buttons[row][col].setDisable(true);
    }

    /**
     * Shows a message stating that the game ended in a draw.
     */
    @Override
    public void draw()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Draw!\n" );

        alert.showAndWait();

    }

    /****
     * Apreseta a informação de jogo ganho
     * @param player
     * @param s
     */
    @Override
    public void playerWins(Cell player, String s)
    {
        assert (player.equals(Cell.PLAYER1) || player.equals(Cell.PLAYER2) );

        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "'" + player + "' won!\n [Pontos-Nome]" + s );
        alert.showAndWait();

    }


    /***
     *class hander trata do evento de click no botão e comunicar com o model"linha 129 "
     * 1 min de tempo gasto
     */
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event) {
           CellButton button = (CellButton)event.getSource();

            int line =   ConnectFourBoard.gridForButtons.getRowIndex(button);
            int col  =   ConnectFourBoard.gridForButtons.getColumnIndex(button);

          ConnectFourBoard.this.gameModel.selectedCell(line, col);
        }
    }



    /***
     *class hander trata do evento de click no botão e comunicar com o model"linha 129 "
     * 1 min de tempo gasto
     */
    private class HandlerItemMenu implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            MenuItem itemMenu = (MenuItem)event.getSource();

            switch (itemMenu.getId()){
                case "UNDO":
                    ConnectFourBoard.this.gameModel.UndoMove();//comunica ao gameModel  se pode desfazer uma jogada
                    break;
                case "REDO":
                    ConnectFourBoard.this.gameModel.redoMove();//comunica ao gameModel se pode refazer uma jogada
                    break;
                default:
                    ConnectFourBoard.this.gameModel.createNewGame();//comunica a gameMode Se pode crair novo cenario de jogo
                    break;
            }
        }
    }




}//end class
