package pt.ipbeja.estig.po2.chess.model;

import java.util.Objects;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 05/jun/2019
 * @time 14:50
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.model
 * @class Position
 * ********       to do    ********
 * class responsavel por tratar das posições ou seja coordenadas das peças
 ******/
public class Position {
    private int line, col;

    /****
     * construtor da class
     * @param line
     * @param col
     */
    public Position(int line, int col)
    {
        this.line = line;
        this.col = col;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setCol(int col) {
        this.col = col;
    }

    /******
     * devolve a string das coordenadas
     * @return
     */
    @Override
    public String toString()
    {
        return "(" + line + ", " + col + ")";
    }

    /**
     * @return the line
     */
    public int getLine()
    {
        return this.line;
    }

    /**
     * @return the col
     */
    public int getCol()
    {
        return this.col;
    }

    /**
     * Checks if position is inside the board
     * @return true if inside, false otherwise
     */
   /* public boolean isInside()
    {
        return Position.isInside(this.getLine(), this.getCol());
    }*/

    /**
     * Checks if line col are inside tha board
     * @param line
     * @param col
     * @return true if inside, false otherwise
     */
    public boolean isInside(int line, int col)
    {
        return 0 <= line && line < GameModel.getSIZE()&&
                0 <= col && col < GameModel.getSIZE();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return line == position.line &&
                col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, col);
    }
}
