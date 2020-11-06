package pt.ipbeja.estig.po2.chess.pieces;

import pt.ipbeja.estig.po2.chess.model.ColorPiece;
import pt.ipbeja.estig.po2.chess.model.GameModel;
import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 06/jun/2019
 * @time 11:35
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.pieces
 * @class PiecesKing
 * ********       to do    ********
 ******/
public class PiecesKing extends Pieces {
    private ColorPiece colorPiece;
    private Position position;
    private GameModel gameModel;

    public PiecesKing(Position pos, GameModel gameModel, ColorPiece colorPiece) {
        super(pos, gameModel, colorPiece);
        this.colorPiece = colorPiece;
        this.position  = pos;
        this.gameModel = gameModel;
    }


    public Position getPosition() {
        return position;
    }



    @Override
    public List<Position> possibleMoves()
    {
        List<Position> arrayMove = new ArrayList<>();
        arrayMove.addAll(this.checkMove(position.getLine(), position.getCol()));
        return arrayMove;
    }




    private  List<Position> checkMove(int line, int col)
    {

        List<Position> arrayMove = new ArrayList<>();

       /* if (this.inBound(line - 1, col) && this.gameModel.isFree(line - 1, col)) {
            arrayMove.add(new Position(line - 1, col));
        }*/
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(this.inBound(line + i, col + j) && this.gameModel.isFree(line + i, col + j) )
                {
                        arrayMove.add(new Position(line + i, col + j));

                }//return arrayMove;

            }

        }
        return arrayMove;
    }


    private List<Position> checkPossibleTakes(int line, int col){
        List<Position> possTakes = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(this.inBound(line + i, col + j) && !this.gameModel.isFree(line + i, col + j) )
                {
                    if(/*(this.gameModel.getBoardDate(line + i, col + j).getTypePieces() != TypeOfPieces.Rw) &&
                            (this.gameModel.getBoardDate(line + i, col + j).getTypePieces() != TypeOfPieces.Rb) &&*/
                            (this.gameModel.getBoardDate(line + i, col + j).getColorPiece() != this.getColorPiece()))

                        possTakes.add(new Position(line + i, col + j));

                }

            }

        }
        return possTakes;
    }


    /*****
     * devolve as possiveis POSSIBLE TAKE - DA PEÇA
     * @return
     */
    @Override
    public List<Position> possibleTakes() {
            List<Position> possTakes = new ArrayList<>();
            possTakes.addAll(checkPossibleTakes(position.getLine(), position.getCol()));

        for (Position p : possTakes) {
            System.out.println(p.getLine() + ":" + p.getCol());

        }
        return possTakes;
    }


    /****
     * DEVOLVE a string da pela com os seus atributos
     * @return
     */
    @Override
    public String toString() {
        return "King" + this.getColorPiece() + position.getLine() + position.getCol();
    }


    /****
     * devolve a peça com as suas carateristicas
     * @return
     */
    @Override
    public Pieces getPieces() {
        return new PiecesKing(position ,  gameModel,  colorPiece) ;
    }



    /*****
     * devolve o tipo de peça
     * @return typeOfPieces "composto pela inicial do nome da peça e a cor"
     */
    @Override
    public TypeOfPieces getTypePieces() {
        TypeOfPieces typeOfPieces;
        if (this.colorPiece == ColorPiece.BLACK) typeOfPieces = TypeOfPieces.Rb;
        else typeOfPieces = TypeOfPieces.Rw;
        return typeOfPieces;
    }

    /*****
     *
     * @param position
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;

    }

    /*****
     *
     * @return
     */
    public ColorPiece getColorPiece() {
        return this.colorPiece;
    }


    /******
     * Metodo para devolver a letra inicial do nome da peça
     * @return K - inicial o nome KING
     */
    @Override
    public String getFirstLetter() {
        return "K";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PiecesKing)) return false;
        if (!super.equals(o)) return false;
        PiecesKing that = (PiecesKing) o;
        return colorPiece == that.colorPiece &&
                Objects.equals(position, that.position) &&
                Objects.equals(gameModel, that.gameModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), colorPiece, position, gameModel);
    }
}
