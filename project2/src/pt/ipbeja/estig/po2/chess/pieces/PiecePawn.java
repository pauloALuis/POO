package pt.ipbeja.estig.po2.chess.pieces;

import pt.ipbeja.estig.po2.chess.model.ColorPiece;
import pt.ipbeja.estig.po2.chess.model.GameModel;
import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.List;
import java.util.Objects;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 06/jun/2019
 * @time 11:37
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.pieces
 * @class PiecePawn
 * ********       to do    ********
 ******/
public class PiecePawn extends Pieces {

    private ColorPiece colorPiece;
    private Position position;
    private GameModel gameModel;

    /****
     *
     * @param pos
     * @param gameModel
     * @param colorPiece
     */
    public PiecePawn(Position pos, GameModel gameModel, ColorPiece colorPiece) {
        super(pos, gameModel, colorPiece);
        this.colorPiece = colorPiece;
        this.position = pos;
        this.gameModel = gameModel;
    }

    /******
     * Metodo para devolver a letra inicial do nome da peça
     * @return  - inicial o nome PAWN
     */
    @Override
    public String getFirstLetter() {
        return "";
    }
    /***
     *devolve aos possiveis movimentos da peça
     * @return
     */
    @Override
    public List<Position> possibleMoves() {
        return null;
    }


    /*****
     *devolve a possiveis posições a capturar
     * @return
     */
    @Override
    public List<Position> possibleTakes() {
        return null;
    }

    /*****
     *devolve a STRING da peça com as suas carateristicas cor, linha e col
     * @return
     */
    @Override
    public String toString() {
        return "PAWN" + this.getColorPiece() + position.getLine() + position.getCol();
    }

    /****
     *
     * @return
     */
    @Override
    public Pieces getPieces() {
        return new PiecePawn(this.position , this.gameModel, this.colorPiece);
    }


    /*****
     * devolve o tipo de peça com a cor
     * @return typeOfPieces "composto pela inicial do nome da peça e a cor"
     */
    @Override
    public TypeOfPieces getTypePieces() {
        TypeOfPieces typeOfPieces;
        if (this.colorPiece == ColorPiece.BLACK) typeOfPieces = TypeOfPieces.Pb;
        else typeOfPieces = TypeOfPieces.Pw;
        return typeOfPieces;
    }

    /****
     *
     * @param position
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;

    }

    /****
     * devolve a cor
     * @return
     */
    public ColorPiece getColorPiece() {
        return this.colorPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PiecePawn)) return false;
        if (!super.equals(o)) return false;
        PiecePawn piecePawn = (PiecePawn) o;
        return colorPiece == piecePawn.colorPiece &&
                Objects.equals(position, piecePawn.position) &&
                Objects.equals(gameModel, piecePawn.gameModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), colorPiece, position, gameModel);
    }
}
