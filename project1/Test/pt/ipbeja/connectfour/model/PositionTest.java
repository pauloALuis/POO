package pt.ipbeja.connectfour.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 17359_PauloLuis_TPA01_PO2_2018-2019
 * @date : 02/05/2019 - 15:58h
 * *********************
 * @package : pt.ipbeja.connectfour.model
 * @class : PositionTest	${CLASS}
 * ------
 * "description"
 */
class PositionTest
{
    private Position position;
    @BeforeEach
    void setUp() {
       // Position position = new Position();
    }

    /******
     * test em as coordenadaas
     */
    @Test
    void testLineCol() {
        position = new Position(1,1,Cell.EMPTY);

        assertEquals(1,this.position.getCol());
        assertEquals(1,this.position.getRow());

    }

    /*****
     * test os atributos das classes e os seus get
     */
    @Test
    void testPLAYER()
    {
        position = new Position(1,1,Cell.PLAYER2);
        assertEquals(Cell.PLAYER2, position.getPlayer());


       Position position1 = new Position(4,1,Cell.PLAYER1);
       assertEquals(Cell.PLAYER1, position1.getPlayer());

        Position positionEmpt = new Position(4,1,Cell.EMPTY);
        assertEquals(Cell.EMPTY, positionEmpt.getPlayer());
    }

    /*******
     *test uma listá de posições provisoria
     */
    @Test
    void testListPosition() {

        List<Position> positionList = new ArrayList<>();
        Position product;


        product = new Position(0,2,Cell.PLAYER2);
        positionList.add(product);


        Position saveProd = positionList.get(0);
        assertEquals(product, saveProd);

    }
}