package pt.ipbeja.estig.po2.chess.model;

import pt.ipbeja.estig.po2.chess.gui.View;
import pt.ipbeja.estig.po2.chess.pieces.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 27/mai/2019
 * @time 15:59
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.model
 * @class GameModel
 * ********       to do    ********
 * gamemodel - class que trata das regras do jogo e comunica com outros intervenientes do jogo,
 * as peças, gui, cor, etc
 ******/
public class GameModel {

    private final View VIEW;
    private Map <Integer,String> algebraABC = new HashMap<Integer, String>();
    private static final int SIZE= 8;
    private static Pieces[][] boardData;
    int lastLine = 0, lastCol = 0;
    private Boolean flag = false;
    private int turnCount =0;
    String firstColunm=" ";



    /*******
     * construtor grafico --
     * @param view
     */
    public GameModel(View view) {
        this.VIEW = view;
        this.boardData = new Pieces[this.SIZE][this.SIZE];
        this.addAlgebraABC();
        this.sortInBoardData();
    }

    /***********
     *construtor para o Test
     * @param VIEW
     * @param boardData
     */
    public GameModel(View VIEW, Pieces[][] boardData)
    {
        this.VIEW = VIEW;
        this.boardData = boardData;
        this.addAlgebraABC();
    }

    /*************
     *pedir para ordenar as peça no taboleiro
     */
    private void sortInBoardData(){
        System.out.println("Create Board 8 * 8");
        this.sortThePieces();
        this.sortDownThePieces();


    }


    /**************
     *ordenar as peças de cor pretas no tabuleiro
     */
    private void sortThePieces()
    {
        this.boardData[0][0] = new PiecesTower(new Position(0,0),this, ColorPiece.BLACK);
        this.boardData[0][1] = new PieceHorse(new Position(0,1), this, ColorPiece.BLACK);
        this.boardData[0][2] = new PiecesBishop(new Position(0,2), this, ColorPiece.BLACK);
        this.boardData[0][3] = new PiecesKing(new Position(0,3), this, ColorPiece.BLACK);
        this.boardData[0][4] = new PieceQueen(new Position(0,4), this, ColorPiece.BLACK);
        this.boardData[0][5] = new PiecesBishop(new Position(0,5), this, ColorPiece.BLACK);
        this.boardData[0][6] = new PieceHorse(new Position(0,6), this, ColorPiece.BLACK);
        this.boardData[0][7] = new PiecesTower(new Position(0,7),this, ColorPiece.BLACK);
    }

    /**************
     *ordena-se as peças de cor brancas no tabuleiro "BoardData"
     */
    private void sortDownThePieces()
    {
        this.boardData[7][0] = new PiecesTower(new Position(7,0),this, ColorPiece.WHITE);
        this.boardData[7][1] = new PieceHorse(new Position(7,1), this, ColorPiece.WHITE);
        this.boardData[7][2] = new PiecesBishop(new Position(7,2), this, ColorPiece.WHITE);
        this.boardData[7][3] = new PiecesKing(new Position(7,3), this, ColorPiece.WHITE);
        this.boardData[7][4] = new PieceQueen(new Position(7,4), this, ColorPiece.WHITE);
        this.boardData[7][5] = new PiecesBishop(new Position(7,5), this, ColorPiece.WHITE);
        this.boardData[7][6] = new PieceHorse(new Position(7,6), this, ColorPiece.WHITE);
        this.boardData[7][7] = new PiecesTower(new Position(7,7),this, ColorPiece.WHITE);

        //this.boardData[6][7] =new PiecePawn(new Position(6,7), this, ColorPiece.WHITE);
    }


    /*****
     * tratar de verificar as jogadas efetuadas e comunica com outros metodo para atualizar e modificar a boardata
     * @param line
     * @param col
     */
    public void selectedPlace(int line, int col)
    {
        assert(line >= 0 && line < this.getSIZE() && col >= 0 && col < this.getSIZE());

        if (!this.isFree(line, col) && !flag)
        {
            this.lastLine = line;
            this.lastCol = col;
            this.updateButton(this.boardData[line][col].possibleMoves(), true);
            this.updateButton(this.boardData[line][col].possibleTakes(), false);
            this.flag = true;


        }
        else if (this.isFree(line, col) && this.flag)
        {
            this.movePiece(new Position(line, col), new Position(lastLine, lastCol));
            this.updateButtons();
        }
        else if (!this.isFree(line, col) && this.flag)
        {
            this.movePiece(new Position(line, col), new Position(lastLine, lastCol));
            this.updateButtons();
        }
    }


    /******
     * trata de pedir a GUI para modificar a cor da border do botoes
     * @param positionList
     * @param flagPossibleMove
     */
    private void updateButton(List<Position> positionList, boolean flagPossibleMove) {

        if(positionList.size() >= 1){
            for (Position pos: positionList  ) {
                this.VIEW.updatePossible(pos.getLine(), pos.getCol(), flagPossibleMove );
            }
        }

    }


    /*****
     * trata do movimento da peça no tabuleiro
     * @param newPosition
     * @param oldPosition
     */
    private void movePiece(Position newPosition, Position oldPosition)
    {
        assert (newPosition.getLine() >= 0 && newPosition.getLine() < this.getSIZE()
                && newPosition.getCol() >= 0 && newPosition.getCol() < this.getSIZE());

        assert (oldPosition.getLine() >= 0 && oldPosition.getLine() < this.getSIZE()
                && oldPosition.getCol() >= 0 && oldPosition.getCol() < this.getSIZE());


        if(this.boardData[oldPosition.getLine()][oldPosition.getCol()].possibleMoves().contains(newPosition))
        {
            try {
                /******
                 * se as coordenadas clicada estiver nos possiveis move então move a peças de lugar e deixa
                 * o lugar anterior livre e atualiza as coordenadas
                 */
                this.setBoardDate(newPosition.getLine(), newPosition.getCol(), this.getBoardDate(oldPosition.getLine(),oldPosition.getCol()));
                this.boardData[newPosition.getLine()][newPosition.getCol()].setPosition(newPosition);
                this.boardData[oldPosition.getLine()][oldPosition.getCol()] = null;
                this.flag = false;
                this.checkIfRecord(newPosition.getLine(), newPosition.getCol(), "");
                this.disableButton(newPosition.getLine(), newPosition.getCol());


            } catch (Exception e) {
                System.out.println("MOVIMENTO DA PEÇA NÃO REGISTADO");
                e.printStackTrace();
            }

        }
        else if(this.boardData[oldPosition.getLine()][oldPosition.getCol()].possibleTakes().contains(newPosition))
        {
            try {

                /********
                 * se as coordenadas estiver em possibleTake então trata-se de uma captura de um peça adiversaria então
                 * trata de mudar a peça de lugar e atualiza as posições
                 */

                this.setBoardDate(newPosition.getLine(), newPosition.getCol(), this.getBoardDate(oldPosition.getLine(),oldPosition.getCol()));
                this.boardData[newPosition.getLine()][newPosition.getCol()].setPosition(newPosition);
                this.boardData[oldPosition.getLine()][oldPosition.getCol()] = null;
                this.flag = false;
                this.checkIfRecord(newPosition.getLine(), newPosition.getCol(), "x");
                this.disableButton(newPosition.getLine(), newPosition.getCol());

            } catch (Exception e) {
                System.out.println("MOVIMENTO DE CAPTURA NÃO REGISTADO");
                e.printStackTrace();
            }

        }
        else {
            this.disablePossibleTake(this.boardData[this.lastLine][this.lastCol].possibleTakes());
             this.flag = false;
        }

    }



    /*****
     * comunica com gui para atualizar a boardData
     */
    public void updateButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
            {
                 if ( boardData[i][j] == null ) VIEW.updateStyleButton(i, j, null);
                 else VIEW.updateStyleButton(i, j, boardData[i][j].getTypePieces());
            }
        }

    }



    /*******
     * pedi a GUI para desativar ou ativar os botões
     * @param line
     * @param col
     */
    public void disableButton(int line, int col)
    {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++)
            {
                if ( !this.isFree(i, j) )
                {
                    if ( boardData[i][j].getColorPiece() == this.getBoardDate(line, col).getColorPiece())
                    {/**** se a cor do botão é igual ao clicado então desativa **/
                        this.VIEW.desablePosition(i, j, true);
                    }
                    else this.VIEW.desablePosition(i, j, false);
                }
            }
        }
    }

    /******
     * trata de alterar a boardData com uma nova peça
     * @param row
     * @param col
     * @param pieces
     */
    public void setBoardDate(int row, int col, Pieces pieces) {
        this.boardData[row][col] = pieces;
    }


    /******
     * verifica se as posições estão livre
     * @param line
     * @param col
     * @return TRUE/FALSE se dada as coordenadas neste lugar da boardData está null
     */
    public static boolean isFree(int line, int col) {
        return boardData[line][col] == null;
    }



    /*****
     * devolve a letras mediante uma chave
     * @param key
     * @return
     */
    public String getAlgebraABC(int key)
    {
        return algebraABC.get(key);
    }


    /**
     * Metodo que devolve o tamanho  do
     * @return
     */
    public static int getSIZE() {
        return SIZE;
    }

    /******
     *devolve a peça mediante uma coordenada
     * @param line
     * @param col
     * @return Pieces
     */
    public Pieces getBoardDate(int line, int col) {
        return this.boardData[line][col];
    }

    /******
     * verifica se está em situação para guardar as posições e escrever no ficheiro
     * @param line
     * @param col
     * @param status
     */
    public void checkIfRecord(int line, int col, String status)
    {
        String secondColunm="";
        boolean flagSave = false;

        if(this.getBoardDate(line, col).getColorPiece() == ColorPiece.WHITE && ((this.turnCount % 2 ) == 0))
        {
            this.turnCount++;
            firstColunm = this.getBoardDate(line, col).getFirstLetter() +
                    status + this.getAlgebraABC(col) + line + " ";
        }
        else if (this.getBoardDate(line, col).getColorPiece() == ColorPiece.BLACK && ((this.turnCount % 2) != 0))
        {
            secondColunm = firstColunm + this.getBoardDate(line, col).getFirstLetter() + status + this.getAlgebraABC(col) + line;
            flagSave = true;

        }

        this.saveFile(flagSave, secondColunm);

    }

    /********************
     * salva as jogadas no ficheiro
     * @param flagSave
     * @param textLine
     */
    private void saveFile(boolean flagSave, String textLine )
    {
        if (flagSave){

            /***** verifica se o ficheiro existe senão apaga***/
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter("File1.txt", true)))
            {
                bw.write(textLine);
                bw.newLine();
            }
            catch (IOException e)
            {
                System.out.println("CONTEUDO NÃO FOI GRAVADO!");
                e.printStackTrace();
            }

        }
    }


    /******
     * Metodo para pedir a GUI PARA desativar o possible possible take em caso de uma jogada invalida
     * @param positionList
     */
    private void disablePossibleTake(List<Position> positionList)
    {
        for (Position pos: positionList  ) {
            this.VIEW.desablePosition(pos.getLine(), pos.getCol(),true);
        }
    }


    /*****
     * Adicionar os valores ao map
     */
    private void addAlgebraABC()
    {
        algebraABC.put(0, "a");
        algebraABC.put(1, "b");
        algebraABC.put(2, "c");
        algebraABC.put(3, "d");
        algebraABC.put(4, "e");
        algebraABC.put(5, "f");
        algebraABC.put(6, "g");
        algebraABC.put(7, "h");
    }
}

