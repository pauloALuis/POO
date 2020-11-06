package pt.ipbeja.connectfour.model;

/**
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 1º Trabalho Pratico de Avaliação POO V2
 * @date : 25/04/2019 - 18:21h
 * *********************
 * @package : pt.ipbeja.connectfour.model
 * @class : Position	${CLASS}
 * ------
 * "description"
 * class que registra as posições jogadas e o tipo de Cell.
 * cuida de guardar toda informação das jogadas efetuadas
 */
public class Position
{
    private int col;
    private int row;
    private Cell player;


    /******
     * construtor da class
     * @param row - recebe as coordenadas em linha
     * @param col - recebe as coordenadas em linha
     * @param player  - recebe o tipo de player
     */
    public Position(int row, int col, Cell player)
    {
        this.col = col ;
        this.row = row;
        this.player = player;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Cell getPlayer() {
        return player;
    }

    public void setPlayer(Cell player) {
        this.player = player;
    }
}
