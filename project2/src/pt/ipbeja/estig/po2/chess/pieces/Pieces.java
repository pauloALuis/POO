package pt.ipbeja.estig.po2.chess.pieces;

import pt.ipbeja.estig.po2.chess.gui.View;
import pt.ipbeja.estig.po2.chess.model.ColorPiece;
import pt.ipbeja.estig.po2.chess.model.GameModel;
import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.List;
import java.util.Objects;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 29/mai/2019
 * @time 20:17
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.pieces
 * @class Pieces
 * ********       to do    ********
 *  * ********       to do    ********
 *  * Cada peça tem de utilizar os seguintes nomes:
 *          * • p - peão (note que o p é minúsculo)   * • p - pawn
 *          * • BLACK - Bispo                             * • BLACK - Bishop
 *          * • C - Cavalo                            * • C - Horse
 *          * • T - Torre                             * • T - Tower
 *          * • R - Rei                               * • R - King
 *          * • D - Rainha (Dama)                     * • D - Queen (Lady)
 *  ******/
public abstract class Pieces {

    private Position position;
    private GameModel gameModel;
    private ColorPiece colorPiece;

    /*****
     * construtor da class
     * @param pos
     * @param gameModel
     * @param colorPiece
     */
    public Pieces(Position pos, GameModel gameModel, ColorPiece colorPiece )
    {
        this.position = pos;
        this.gameModel = gameModel;
        this.colorPiece = colorPiece;
    }


    public int getRow()
    {
        return this.position.getLine();
    }

    public int getCol()
    {
        return this.position.getCol();
    }

    public void setCol(int col) {
        this.position.setCol(col);
    }
    private void setRow(int row){
        this.position.setLine(row);
    }

    /*****
     * devolve as coordenadas do possible move da peça
     * @return
     */
    public abstract List<Position> possibleMoves();

    /******
     * Metodo para devolver a letra inicial do nome da peça
     * @return
     */
    public abstract String getFirstLetter();

    /*******
     * devolve as coordenadas das peças adiversarias que pode capturar
     * @return
     */
     public abstract List<Position> possibleTakes();

    /******
     * devolve a string com o nome da peça e as coordenadas
     * @return
     */
    public abstract String toString();

    /****
     * devolve a peça com as suas carateristicas
     * @return
     */
    public abstract Pieces getPieces();

    /*****
     * devolve a cor das peças
     * @return
     */
    public abstract ColorPiece getColorPiece();


    /******
     * devolve o tipo de peça
     * @return
     */
    public abstract TypeOfPieces getTypePieces();

    public abstract void setPosition(Position position);

    /*******
     * verifica se as possicoes estão dentro dos limites da boarddata
     * @param line
     * @param col
     * @return
     */
    public boolean inBound(int line, int col){
        return position.isInside(line, col);
    }

    /****
     *
     * @param colorPiece
     */
    public void setColorPiece(ColorPiece colorPiece) {
        this.colorPiece = colorPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pieces)) return false;
        Pieces pieces = (Pieces) o;
        return Objects.equals(position, pieces.position) &&
                Objects.equals(gameModel, pieces.gameModel) &&
                colorPiece == pieces.colorPiece;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, gameModel, colorPiece);
    }
}
