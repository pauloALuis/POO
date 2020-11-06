package pt.ipbeja.estig.po2.chess.pieces;

import pt.ipbeja.estig.po2.chess.model.ColorPiece;
import pt.ipbeja.estig.po2.chess.model.GameModel;
import pt.ipbeja.estig.po2.chess.model.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 05/jun/2019
 * @time 15:13
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.pieces
 * @class PiecesBishop
 * ********       to do    ********
 ******/
public class PiecesBishop extends Pieces {

    private ColorPiece colorPiece;
    private Position position;
    private GameModel gameModel;
    private List<Position> arryTakes;


    public PiecesBishop(Position pos, GameModel gameModel, ColorPiece colorPiece) {
        super(pos, gameModel, colorPiece);
        this.colorPiece = colorPiece;
        this.position = pos;
        this.gameModel = gameModel;
        this.arryTakes = new  ArrayList<>();
    }


    /*****
     * verifica os possiveis movimentos da peça
     * @return
     */

    @Override
    public List<Position> possibleMoves()
    {
        List<Position> possibMove = new  ArrayList<>();
        this.arryTakes = new  ArrayList<>();


        possibMove.addAll(this.diagonalFromDownLeft(position.getLine(), position.getCol()));
        possibMove.addAll(this.diagonalFromTopLeft(position.getLine(), position.getCol()));
        possibMove.addAll(this.diagonalFromTopRegth(position.getLine(), position.getCol()));
        possibMove.addAll(this.diagonalFromDownRegth(position.getLine(), position.getCol()));

        return possibMove;
    }



    /******
     * verifica se existe casa vazias na diagonal da ponta  esqueda de cima - a partir da linha e coluna
     *   também verifica os possible take existente.
     *  @param line
     *  @param col
     *  @return uma lista de possibMove da esqueda de cima da peça clicado
     */
    private List<Position> diagonalFromTopLeft(int line, int col)
    {
        List<Position> possibMove = new  ArrayList<>();

        if (this.inBound(line - 1, col - 1))
        {
            for (int j = col - 1; j <= GameModel.getSIZE(); j--)
            {
                line--;
                if(this.inBound(line, j) && this.gameModel.isFree(line , j))
                {
                    possibMove.add(new Position(line, j));
                }else  if( (this.inBound(line, j) && !this.gameModel.isFree(line, j) ) )
                {
                    if ((this.gameModel.getBoardDate(line, j).getTypePieces() != TypeOfPieces.Rw) &&
                            (this.gameModel.getBoardDate(line, j).getTypePieces() != TypeOfPieces.Rb) &&
                            !(this.gameModel.getBoardDate(line, j).getColorPiece() == this.getColorPiece()))
                    {
                        arryTakes.add(new Position(line, j));
                        return possibMove;
                    }
                }else return possibMove;
            }
        }
        return possibMove;
    }


    /*******
     * verifica se existe casa vazias na diagonal da ponta  direita de cima - a partir da linha e coluna
     * também verifica os possible take existente.
     * @param line
     * @param col
     * @return uma lista de possibMove da direita de cima da peça clicado
     */
    private  List<Position> diagonalFromTopRegth(int line, int col)
    {
        List<Position> possibMove = new  ArrayList<>();


        if (this.inBound(line - 1, col + 1))
        {
            for (int j = col + 1; j <= GameModel.getSIZE(); j++)
            {
                line--;
                if(this.inBound(line, j) && this.gameModel.isFree(line , j))
                {
                    possibMove.add(new Position(line, j));
                }else  if( (this.inBound(line, j) && !this.gameModel.isFree(line, j )))
                {
                    if ((this.gameModel.getBoardDate(line, j).getTypePieces() != TypeOfPieces.Rw) &&
                            (this.gameModel.getBoardDate(line, j).getTypePieces() != TypeOfPieces.Rb) &&
                            !(this.gameModel.getBoardDate(line, j).getColorPiece() == this.getColorPiece()))
                    {
                        arryTakes.add(new Position(line, j));
                        return possibMove;
                    }
                }else return possibMove;
            }
        }
        return possibMove;
    }


    /*********************
     * verifica se existe casa vazias na diagonal da ponta  esqueda baixo - a partir da linha e coluna
     * também verifica os possible take existente.
     * @param line
     * @param col
     * @return uma lista de possibMove da esqueda de baixo da peça clicado
     */
    private  List<Position> diagonalFromDownLeft(int line, int col) {

        List<Position> possibMove = new  ArrayList<>();

        if (this.inBound(line + 1, col - 1 ))
        {
            for (int i = line + 1; i <= GameModel.getSIZE(); i++)
            {
                col--;
                if(this.inBound(i, col  ) && this.gameModel.isFree( i, col ))
                {
                    possibMove.add(new Position( i, col ));
                }else  if( (this.inBound(i, col) && !this.gameModel.isFree(i , col) ) )
                {
                    if (/*(this.gameModel.getBoardDate(i , col ).getTypePieces() != TypeOfPieces.Rw) &&
                            (this.gameModel.getBoardDate(i , col).getTypePieces() != TypeOfPieces.Rb) &&*/
                            !(this.gameModel.getBoardDate(i, col).getColorPiece() == this.getColorPiece()))
                    {
                        arryTakes.add(new Position(i, col));
                        return possibMove;
                    }
                }else return possibMove;
            }
        }
        return possibMove;
    }

    /*******
     * verifica se existe casa vazias na diagonal da direita baixo - a partir da linha e coluna
     * também verifica os possible take existente.
     * @param line
     * @param col
     * @return uma lista de possibMove da direita baixo
     */
    private  List<Position> diagonalFromDownRegth(int line, int col)
    {
        List<Position> possibMove = new  ArrayList<>();

        if (this.inBound(line + 1, col ))
        {
            for (int i = line + 1; i <= GameModel.getSIZE(); i++)
            {
                col++;
                if(this.inBound( i, col ) && this.gameModel.isFree( i, col ))
                {
                    possibMove.add(new Position( i, col ));
                }else  if( (this.inBound(i, col) && !this.gameModel.isFree(i , col) ))
                {
                     if (/*(this.gameModel.getBoardDate(i , col ).getTypePieces() != TypeOfPieces.Rw) &&
                        (this.gameModel.getBoardDate(i , col).getTypePieces() != TypeOfPieces.Rb) &&*/
                        !(this.gameModel.getBoardDate(i, col).getColorPiece() == this.getColorPiece()))
                        {
                            arryTakes.add(new Position(i, col));
                            return possibMove;
                        }
                }else return possibMove;
            }
        }
        return possibMove;
    }



    @Override
    public List<Position> possibleTakes() {
        /**ATT: COMO este metodo é parecido ao POSSIBLEMOVE - optei por usar um atributo global e reutilizar
         * os metodos do POSSIBLEMOVE p/ verificar os POSSIBLETAKES, com isso este metodo têm uma dependecia do POSSIBLEMOVE
         * e não é possivel usar este metodo sem ANTES chamar o metodo POSSIBLEMOVE      *******/
         return arryTakes;
    }


    /***
     *
     * @return
     */
    @Override
    public int getRow() {
        return super.getRow();
    }


    /*****
     *
     * @return
     */
    @Override
    public int getCol() {
        return super.getCol();
    }

    /****
     * devolve string da peça
     * @return
     */
    @Override
    public String toString() {
        return "Bishop" + this.getColorPiece() + position.getLine() + position.getCol();
    }

    /*****
     * devolve a peça com as suas carateristicas
     * @return
     */
    @Override
    public Pieces getPieces() {
        return new PiecesBishop( this.position, this.gameModel, this.getColorPiece() );
    }


    /*****
     * devolve o tipo de peça
     * @return typeOfPieces "composto pela inicial do nome da peça e a cor"
     */
    @Override
    public TypeOfPieces getTypePieces() {
        TypeOfPieces typeOfPieces;
        if (this.colorPiece == ColorPiece.BLACK) typeOfPieces = TypeOfPieces.Bb;
        else typeOfPieces = TypeOfPieces.Bw;
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
     * devolve a cor
     * @return
     */
    public ColorPiece getColorPiece() {
        return this.colorPiece;
    }


    /******
     * Metodo para devolver a letra inicial do nome da peça
     * @return B - inicial o nome BISHOP
     */
    @Override
    public String getFirstLetter() {
        return "B";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PiecesBishop)) return false;
        if (!super.equals(o)) return false;
        PiecesBishop that = (PiecesBishop) o;
        return colorPiece == that.colorPiece &&
                Objects.equals(position, that.position) &&
                Objects.equals(gameModel, that.gameModel) &&
                Objects.equals(arryTakes, that.arryTakes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), colorPiece, position, gameModel, arryTakes);
    }
}
