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
 * @time 11:36
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.pieces
 * @class PiecesTower
 * ********       to do    ********
 ******/
public class PiecesTower extends Pieces {


    private ColorPiece colorPiece;
    private Position position;
    private GameModel gameModel;
    private List<Position> possTakes;

    /*****
     * contrutor da class
     * @param pos
     * @param gameModel
     * @param colorPiece
     */
    public PiecesTower(Position pos, GameModel gameModel, ColorPiece colorPiece) {

        super(pos, gameModel, colorPiece);
        this.colorPiece = colorPiece;
        this.position = pos;
        this.gameModel = gameModel;
        this.possTakes = new  ArrayList<>();

    }


    /*************
     * DEVOLVE a lista de possiveis movimento da peça
     * @return
     */
    @Override
    public List<Position> possibleMoves() {
        List<Position> possibMove = new  ArrayList<>();
       possTakes = new  ArrayList<>();

        possibMove.addAll(this.checkColLeft(position.getLine(), position.getCol()));
        possibMove.addAll(this.checkTop(position.getLine(), position.getCol()));
        possibMove.addAll(this.checkColRegth(position.getLine(), position.getCol()));
        possibMove.addAll(this.checkDown(position.getLine(), position.getCol()));
        return possibMove;
    }

    /******
     * Metodo para devolver a letra inicial do nome da peça
     * @return T - inicial o nome TOWER
     */
    @Override
    public String getFirstLetter() {
        return "T";
    }


    /*****************
     * Verifica se a esquerda da coluna se exite possiveis casas vazia e adicionar a POSSIBLEMOVE
     * também verifica se há possibletake e adiciona a lista
     * @param line
     * @param col
     * @return devolve a lista de POSSIBLEMOVE DE ESQUERDA DA COLUNA
     */
    private List<Position> checkColLeft(int line, int col)
    {
        List<Position> possibMove = new  ArrayList<>();

        if (this.inBound(line, col - 1))
        {
            for (int j = col - 1; j <= GameModel.getSIZE(); j--)
            {
                if(this.inBound(line, j) && this.gameModel.isFree(line , j))
                {
                    possibMove.add(new Position(line, j));
                }
               else if(this.inBound(line, j) && !this.gameModel.isFree(line , j) )
                {
                    if(/*(this.gameModel.getBoardDate(line , j ).getTypePieces() != TypeOfPieces.Rw) &&
                            (this.gameModel.getBoardDate(line , j).getTypePieces() != TypeOfPieces.Rb) &&*/
                    !(this.gameModel.getBoardDate(line, j).getColorPiece() == this.getColorPiece()))
                    {
                            possTakes.add(new Position(line, j));
                            return possibMove;

                    }else return possibMove;
                }
            }
        }
        return possibMove;

    }


    /*****************
     * Verifica se a DIREITA da coluna se exite possiveis casas vazia e adicionar a POSSIBLEMOVE
     * também verifica se há possibletake e adiciona a lista
     * @param line
     * @param col
     * @return devolve a lista de POSSIBLEMOVE DE DIREITA DA COLUNA
     */
    private List<Position> checkColRegth(int line, int col)
    {
        List<Position> possibMove = new  ArrayList<>();

        if (this.inBound(line , col + 1))
        {
            for (int j = col + 1; j <= GameModel.getSIZE(); j++)
            {
                if(this.inBound(line, j) && this.gameModel.isFree(line , j))
                {
                    possibMove.add(new Position(line, j));
                }

               else if(this.inBound(line, j) && !this.gameModel.isFree(line , j) )
                {
                    if(/*(this.gameModel.getBoardDate(line , j ).getTypePieces() != TypeOfPieces.Rw) &&
                            (this.gameModel.getBoardDate(line , j).getTypePieces() != TypeOfPieces.Rb)&&*/
                            !(this.gameModel.getBoardDate(line, j).getColorPiece() == this.getColorPiece()))
                    {
                        possTakes.add(new Position(line, j));
                        return possibMove;

                    }else return possibMove;
                }
            }
        }
        return possibMove;

    }


    /*****************
     * Verifica se a EM CIMA  da coluna se exite possiveis casas vazia e adicionar a POSSIBLEMOVE
     * também verifica se há possibletake e adiciona a lista
     * @param line
     * @param col
     * @return devolve a lista de POSSIBLEMOVE DE CIMA DA COLUNA
     */
    private  List<Position> checkTop(int line, int col) {
        List<Position> possibMove = new  ArrayList<>();

        if (this.inBound(line + 1, col ))
        {
            for (int i = line + 1; i <= GameModel.getSIZE(); i++)
            {
                if(this.inBound(i, col) && this.gameModel.isFree( i,col ))
                {
                    possibMove.add(new Position( i, col));
                }
               else if(this.inBound(i, col) && !this.gameModel.isFree(i , col) )
                {
                    if(/*(this.gameModel.getBoardDate(i , col ).getTypePieces() != TypeOfPieces.Rw) &&
                            (this.gameModel.getBoardDate(i , col).getTypePieces() != TypeOfPieces.Rb) &&*/
                            !(this.gameModel.getBoardDate(i, col).getColorPiece() == this.getColorPiece()))
                    {
                        possTakes.add(new Position(i, col));
                        return possibMove;

                    }else return possibMove;
                }
            }
        }
        return possibMove;
    }




    /*****************
     * Verifica se a EM BAIXO  da coluna se exite possiveis casas vazia e adicionar a POSSIBLEMOVE
     * também verifica se há possibletake e adiciona a lista
     * @param line
     * @param col
     * @return devolve a lista de POSSIBLEMOVE DE BAIXO DA COLUNA
     */
    private List<Position> checkDown(int line, int col)
    {
        List<Position> possibMove = new  ArrayList<>();
        if (this.inBound(line - 1, col ))
        {
            for (int i = line - 1; i <= GameModel.getSIZE(); i--)
            {
                if(this.inBound( i, col ) && this.gameModel.isFree( i, col ))
                {
                    possibMove.add(new Position( i, col ));
                }

                else if(this.inBound(i, col) && !this.gameModel.isFree(i , col) )
                {
                       if(/*(this.gameModel.getBoardDate(i , col ).getTypePieces() != TypeOfPieces.Rw) &&
                             (this.gameModel.getBoardDate(i , col).getTypePieces() != TypeOfPieces.Rb)&&*/
                               !(this.gameModel.getBoardDate(i, col).getColorPiece() == this.getColorPiece()))
                       {
                           possTakes.add(new Position(i, col));
                           return possibMove;

                       }else return possibMove;
                }
            }
        }
        return possibMove;
    }


    /******
     * METODO QUE DEVOLVE AS POSSIVEIS CASA PARA A PEÇA CAPTURAR
     * @return RETURNA A LISTA DE POSSIBLETAKES
     */
    @Override
    public List<Position> possibleTakes()
    {
        /**ATT: COMO este metodo é parecido ao POSSIBLEMOVE - optei por usar um atributo global e reutilizar
         * os metodos do POSSIBLEMOVE p/ verificar os POSSIBLETAKES, com isso este metodo têm uma dependecia do POSSIBLEMOVE
         * e não é possivel usar este metodo sem ANTES chamar o metodo POSSIBLEMOVE      *******/


        return possTakes;
    }


    /***********
     * devolve nome da peça e linha, col, e cor
     * @return
     */
    @Override
    public String toString() {
        return "TOWER" + this.getColorPiece() + position.getLine() + position.getCol();
    }

    /***
     * getPieces devolve a peça
     * @return
     */
    @Override
    public Pieces getPieces() {
        return new PiecesTower(this.position , this.gameModel, this.colorPiece);
    }


    /*****
     * devolve o tipo de peça
     * @return typeOfPieces "composto pela inicial do nome da peça e a cor"
     */
    @Override
    public TypeOfPieces getTypePieces() {
        TypeOfPieces typeOfPieces;
        if (this.colorPiece == ColorPiece.BLACK) typeOfPieces = TypeOfPieces.Tb;
        else typeOfPieces = TypeOfPieces.Tw;
        return typeOfPieces;
    }

    /*********
     *
     * @param position
     */
    @Override
    public void setPosition(Position position) {
        this.position = position;

    }

    /*************
     * devolve a cor da peça
     * @return
     */
    public ColorPiece getColorPiece() {
        return this.colorPiece;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PiecesTower)) return false;
        if (!super.equals(o)) return false;
        PiecesTower that = (PiecesTower) o;
        return colorPiece == that.colorPiece &&
                Objects.equals(position, that.position) &&
                Objects.equals(gameModel, that.gameModel) &&
                Objects.equals(possTakes, that.possTakes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), colorPiece, position, gameModel, possTakes);
    }
}
