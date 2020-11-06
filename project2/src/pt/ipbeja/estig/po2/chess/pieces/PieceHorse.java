package pt.ipbeja.estig.po2.chess.pieces;

import pt.ipbeja.estig.po2.chess.model.ColorPiece;
import pt.ipbeja.estig.po2.chess.model.GameModel;
import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.*;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 06/jun/2019
 * @time 11:36
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.pieces
 * @class PieceHorse
 * ********       to do    ********
 ******/
public class PieceHorse extends Pieces {

    private ColorPiece colorPiece;
    private Position position;
    private GameModel gameModel;
    private List<Position> arryTakes;

    public PieceHorse(Position pos, GameModel gameModel, ColorPiece colorPiece) {
        super(pos, gameModel, colorPiece);
        this.colorPiece = colorPiece;
        this.position = pos;
        this.gameModel = gameModel;
        this.arryTakes = new ArrayList<>();

    }


    /*****
     * devolve a lista de coordenadas dos possiveis movimento da peça
     * @return
     */
    @Override
    public List<Position> possibleMoves() {
        List<Position> arrayMove = new ArrayList<>();
        arrayMove.addAll(this.verifyPossiblePlayLine(position.getLine(), position.getCol()));
        arrayMove.addAll(this.verifyPossiblePlayCol(position.getLine(), position.getCol()));
        return arrayMove;
    }

    /*else  if( (this.inBound(line, j) && !this.gameModel.isFree(line, j) ) )
    {
        if (!(this.gameModel.getBoardDate(line, j).getColorPiece() == this.getColorPiece()))
        {
            arryTakes.add(new Position(line, j));
            return possibMove;
        }
    }else return possibMove;*/

    private List<Position> verifyPossiblePlayLine(int line, int col) {
        List<Position> arrayMove = new ArrayList<>();

        if ((this.inBound(line + 2, col + 1) && this.gameModel.isFree(line + 2, col + 1))) {
            arrayMove.add(new Position(line + 2, col + 1));

        } else if ((this.inBound(line + 2, col + 1) && !this.gameModel.isFree(line + 2, col + 1)))
        {
            if (!(this.gameModel.getBoardDate(line + 2, col + 1).getColorPiece() == this.getColorPiece())) {
                arryTakes.add(new Position(line + 2, col + 1));
            }
        }

        if (this.inBound(line + 2, col - 1) && this.gameModel.isFree(line + 2, col - 1)) {
            arrayMove.add(new Position(line + 2, col - 1));
        } else if ((this.inBound(line + 2, col - 1) && !this.gameModel.isFree(line + 2, col - 1)))
        {
            if (!(this.gameModel.getBoardDate(line + 2, col - 1).getColorPiece() == this.getColorPiece())) {
                arryTakes.add(new Position(line + 2, col - 1));
            }
        }

        if ((this.inBound(line - 2, col + 1) && this.gameModel.isFree(line - 2, col + 1))) {
            arrayMove.add(new Position(line - 2, col + 1));
        }
        else if ((this.inBound(line - 2, col + 1) && !this.gameModel.isFree(line - 2, col + 1)))
        {
            if (!(this.gameModel.getBoardDate(line - 2, col + 1).getColorPiece() == this.getColorPiece())) {
                arryTakes.add(new Position(line - 2, col + 1));
            }
        }



        if (this.inBound(line - 2, col - 1) && this.gameModel.isFree(line - 2, col - 1)) {
            arrayMove.add(new Position(line - 2, col - 1));
        } else if ((this.inBound(line - 2, col - 1) && !this.gameModel.isFree(line - 2, col - 1)))
        {
            if (!(this.gameModel.getBoardDate(line - 2, col - 1).getColorPiece() == this.getColorPiece())) {
                arryTakes.add(new Position(line - 2, col - 1));
            }
        }
        return arrayMove;
    }


    private List<Position> verifyPossiblePlayCol(int line, int col) {

        List<Position> arrayMove = new ArrayList<>();

        if ((this.inBound(line + 1, col + 2) && this.gameModel.isFree(line + 1, col + 2))) {
            arrayMove.add(new Position(line + 1, col + 2));
        }
        else if ((this.inBound(line + 1, col + 2)  && !this.gameModel.isFree(line + 1, col + 2) ))
        {
            if (!(this.gameModel.getBoardDate(line + 1, col + 2) .getColorPiece() == this.getColorPiece())) {
                arryTakes.add(new Position(line + 1, col + 2) );
            }
        }

        if (this.inBound(line - 1, col + 2) && this.gameModel.isFree(line - 1, col + 2)) {
            arrayMove.add(new Position(line - 1, col + 2));
        }
        else if ((this.inBound(line - 1, col + 2)  && !this.gameModel.isFree(line - 1, col + 2) ))
        {
            if (!(this.gameModel.getBoardDate(line - 1, col + 2) .getColorPiece() == this.getColorPiece())) {
                arryTakes.add(new Position(line - 1, col + 2) );
            }
        }

        if ((this.inBound(line + 1, col - 2) && this.gameModel.isFree(line + 1, col - 2))) {
            arrayMove.add(new Position(line + 1, col - 2));
        }
        else if ((this.inBound(line + 1, col - 2)   && !this.gameModel.isFree(line + 1, col - 2)  ))
        {
            if (!(this.gameModel.getBoardDate(line + 1, col - 2)  .getColorPiece() == this.getColorPiece())) {
                arryTakes.add(new Position(line + 1, col - 2)  );
            }
        }

        if (this.inBound(line - 1, col - 2) && this.gameModel.isFree(line - 1, col - 2)) {
            arrayMove.add(new Position(line - 1, col - 2));
        }
        else if ((this.inBound(line - 1, col - 2)   && !this.gameModel.isFree(line - 1, col - 2)  ))
        {
            if (!(this.gameModel.getBoardDate(line - 1, col - 2).getColorPiece() == this.getColorPiece())) {
                arryTakes.add(new Position(line - 1, col - 2)  );
            }
        }

        return arrayMove;
    }


    @Override
    public List<Position> possibleTakes() {
        /**ATT: COMO este metodo é parecido ao POSSIBLEMOVE - optei por usar um atributo global e reutilizar
         * os metodos do POSSIBLEMOVE p/ verificar os POSSIBLETAKES, com isso este metodo têm uma dependecia do POSSIBLEMOVE
         * e não é possivel usar este metodo sem ANTES chamar o metodo POSSIBLEMOVE      *******/
        return arryTakes;
    }


    /****
     *
     * @return
     */
    public ColorPiece getColorPiece() {
        return colorPiece;
    }

    /****
     * devolve a string das peça com os seus atributos
     * @return
     */
    @Override
    public String toString() {
        return "HORSE" + this.getColorPiece() + position.getLine() + position.getCol();
    }

    /*****
     * devolve a peça com as suas carateristicas
     * @return
     */
    @Override
    public Pieces getPieces() {
        return new PieceHorse(this.position, this.gameModel, this.colorPiece);
    }


    /*****
     * devolve o tipo de peça
     * @return typeOfPieces "composto pela inicial do nome da peça e a cor"
     */
    @Override
    public TypeOfPieces getTypePieces() {
        TypeOfPieces typeOfPieces;
        if (this.colorPiece == ColorPiece.BLACK) typeOfPieces = TypeOfPieces.Cb;
        else typeOfPieces = TypeOfPieces.Cw;
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

    /******
     * Metodo para devolver a letra inicial do nome da peça
     * @return HORSE - inicial o nome HORSE
     */
    @Override
    public String getFirstLetter() {
        return "H";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceHorse)) return false;
        if (!super.equals(o)) return false;
        PieceHorse that = (PieceHorse) o;
        return colorPiece == that.colorPiece &&
                Objects.equals(position, that.position) &&
                Objects.equals(gameModel, that.gameModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), colorPiece, position, gameModel);
    }
}
