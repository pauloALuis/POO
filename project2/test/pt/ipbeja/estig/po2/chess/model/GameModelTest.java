package pt.ipbeja.estig.po2.chess.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipbeja.estig.po2.chess.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 14/jun/2019
 * @time 14:27
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.model
 * @class GameModelTest
 * ********       to do    ********
 ******/
class GameModelTest {

    private ViewTest VIEW = new ViewTest();
    private GameModel model;
    private Pieces[][] boardData;

    @BeforeEach
    void setUp() {

    }



    /***************************************************************************************************
     ***************************************************************************************************
     **************************             TEST PEÇA KING                   ****************************
     * /************************************************************************************************
     ***************************************************************************************************/
    @Test
    void test01Bishop()
    {
        /******  Teste possibleMove Bishop ********/

        model = new GameModel(this.VIEW);
        List<Position> expectedBisp  = Arrays.asList
                                                (
                                                         new Position(6,1), new Position(5,0),
                                                         new Position(6,3), new Position(5,4),
                                                         new Position(4,5), new Position(3,6),
                                                         new Position(2,7)
                                                );
        this.model.selectedPlace(7,2);
        assertArrayEquals(expectedBisp.toArray(), this.model.getBoardDate(7, 2).possibleMoves().toArray());
    }

    /********************************************************************************************/

    @Test
    void test02Bishop()
    {
    /********TEST POSSIBLETAKE BISPO *************  */
        model = new GameModel(this.VIEW);

        /***Possible TAKE esperado ****/
        List<Position> expecteTake = Arrays.asList(
                                                    new Position(3,1),
                                                    new Position(5,7) );


        model.setBoardDate(5,7, new PiecePawn(new Position(5,7), this.model, ColorPiece.BLACK) );
        model.setBoardDate(3,1, new PieceHorse(new Position(3,1), this.model, ColorPiece.BLACK) );


        //fazer um click na peça
        this.model.selectedPlace(7,5);

        assertArrayEquals(expecteTake.toArray(), this.model.getBoardDate(7, 5).possibleTakes().toArray());

        this.model.selectedPlace(3,1);//capturar a peça adversaria
        assertNotEquals(this.model.getBoardDate(3,1).getTypePieces()
                        ,new PieceHorse(new Position(3,1), this.model, ColorPiece.BLACK).getTypePieces());


        /***Possible TAKE esperado ****/
        expecteTake = Arrays.asList(new Position(0,4) );


        this.model.selectedPlace(3,1);//Escolho a peça

        assertArrayEquals(expecteTake.toArray(), this.model.getBoardDate(3, 1).possibleTakes().toArray());


        this.model.selectedPlace(0,4);//escolho a peça que quero capturar adversaria

        assertNotEquals(this.model.getBoardDate(0,4).getTypePieces()
                ,new PieceQueen(new Position(0,4), this.model, ColorPiece.BLACK).getTypePieces());
    }



    /********************************************************************************************/

    @Test
    void test03Bishop()
    {

        /*******************   TESTE MUDANÇA DE POSIÇÃO    *******************/
        model = new GameModel(this.VIEW );

        model.selectedPlace(7, 5);// escolher a peça para movimentar

        TypeOfPieces expectedPieces = new PiecesBishop(new Position(7,5), model, ColorPiece.WHITE).getTypePieces();

        assertEquals(expectedPieces, model.getBoardDate(7,5).getTypePieces());

        /****** verificar se o movimento está na possibleMovi*********/
        assertTrue(model.getBoardDate(7,5).possibleMoves().contains(new Position(5, 3)));
        model.selectedPlace(5, 3);// mover a peça para posição (5, 3)

        assertTrue( this.model.isFree(7,5));//verificar se o anterior lugar da peça está vazio

        expectedPieces = new PiecesBishop(new Position(5,3), model, ColorPiece.WHITE).getTypePieces();
        assertEquals( expectedPieces, model.getBoardDate(5,3).getTypePieces());


        /********** bispo black move -- ******/
        model.selectedPlace(0, 2);// escolher a peça para movimentar
        model.selectedPlace(5, 7);// mover a peça para posição (5, 7)

        assertTrue( this.model.isFree(0,2));//verificar se o anterior lugar da peça está vazio

        expectedPieces = new PiecesBishop(new Position(5, 7), model, ColorPiece.BLACK).getTypePieces();
        assertEquals( expectedPieces, model.getBoardDate(5, 7).getTypePieces());
    }






    /***************************************************************************************************
     ***************************************************************************************************
     **************************             TEST PEÇA TOWER                 ****************************
     * /************************************************************************************************
     ***************************************************************************************************/
    @Test
    void test01Tower() {
        /******  Teste possibleMove Tower ********/
        model = new GameModel(this.VIEW);


        model.setBoardDate(0,0,null);
        model.setBoardDate(1,0, new PiecesTower(new Position(1,0), model, ColorPiece.BLACK) );
        //*******
        model.setBoardDate(7,0,null);
        model.setBoardDate(5,0, new PiecesTower(new Position(5,0), model, ColorPiece.WHITE) );


        /******************* Verificar se têm uma peça Torre nas posicçao(1, 3) e se na (0, 0) está Livre **************/
        assertEquals( new PiecesTower(new Position(1,0), model, ColorPiece.BLACK).getTypePieces()
                     ,model.getBoardDate(1,0).getTypePieces());

        assertTrue(model.isFree(0, 0));

        /******************* Verificar se têm uma peça Torre nas posicçao(5, 0)  e se na (7, 0) está Livre **************/
        assertEquals( new PiecesTower(new Position(5,0), model, ColorPiece.WHITE).getTypePieces()
                      ,model.getBoardDate(5,0).getTypePieces());

        assertTrue(model.isFree(7, 0));
        assertTrue(model.isFree(6, 0));

        /***PossibleMove esperado ****/
        List<Position> expectedTower  = Arrays.asList
                    (
                            new Position(2,0), new Position(3,0), new Position(4,0),
                            new Position(1,1), new Position(1,2), new Position(1,3),
                            new Position(1,4), new Position(1,5), new Position(1,6),
                            new Position(1,7),
                            new Position(0,0)
                    );
         this.model.selectedPlace(1,0);

        assertArrayEquals(expectedTower.toArray(), this.model.getBoardDate(1, 0).possibleMoves().toArray());
    }

    /********************************************************************************************/

    @Test
    void test02Tower() {
        /******   TEST POSSIBLETAKES  TORRE*****/
        model = new GameModel(this.VIEW);

        /***Possible TAKE esperado ****/
        List<Position> expecteTower = Arrays.asList(new Position(5,1),
                                                    new Position(3,3),
                                                    new Position(0,1) );



        model.setBoardDate(3,1, new PiecesTower(new Position(3,1), this.model, ColorPiece.WHITE) );
        model.setBoardDate(5,1, new PiecesTower(new Position(5,1), this.model, ColorPiece.BLACK) );
        model.setBoardDate(3,3, new PiecesKing(new Position(3,3), this.model, ColorPiece.BLACK) );

        //fazer um click na peça
        this.model.selectedPlace(3,1);

        assertArrayEquals(expecteTower.toArray(), this.model.getBoardDate(3, 1).possibleTakes().toArray());

        this.model.selectedPlace(5,1);//capturar a peça adversaria
        assertNotEquals(this.model.getBoardDate(5,1).getTypePieces()
                        ,new PiecesTower(new Position(5,1), this.model, ColorPiece.BLACK).getTypePieces());


        /***Possible TAKE esperado ****/
       expecteTower = Arrays.asList(new Position(0,1) );

        this.model.selectedPlace(5,1);//Escolho a peça
        assertArrayEquals(expecteTower.toArray(), this.model.getBoardDate(5, 1).possibleTakes().toArray());


        this.model.selectedPlace(0,1);//escolho a peça que quero capturar adversaria

        assertNotEquals(this.model.getBoardDate(0,1).getTypePieces()
                        ,new PieceHorse(new Position(0,1), this.model, ColorPiece.BLACK).getTypePieces());


        this.model.selectedPlace(0,1);//Escolho a peça
        this.model.selectedPlace(0,2);//escolho a peça que quero capturar adversaria
        assertNotEquals(this.model.getBoardDate(0,2).getTypePieces()
                ,new PiecesBishop(new Position(0,2), this.model, ColorPiece.BLACK).getTypePieces());


    }

    /********************************************************************************************/


    @Test
    void test03Tower()
    {

        /*******************   TESTE MUDANÇA DE POSIÇÃO Tower   *******************/

        model = new GameModel(this.VIEW );

        model.selectedPlace(7, 7);// escolher a peça para movimentar
        assertEquals( model.getBoardDate(7,7).getTypePieces(),
                    new PiecesTower(new Position(7,7), model, ColorPiece.WHITE).getTypePieces());

        /****** verificar se o movimento está na possibleMovi*********/
        assertTrue(model.getBoardDate(7,7).possibleMoves().contains(new Position(3, 7)));

        model.selectedPlace(3, 7);// mover a peça para posição (5, 3)

        assertTrue( model.isFree(7,7) );//VERIFICAR SE ESTÁ LIVRE

        assertEquals(new PiecesTower(new Position(3,7), model, ColorPiece.WHITE).getTypePieces()
                        ,model.getBoardDate(3,7).getTypePieces() );

        /*************
         *  teste TOWER black   *****************************************************************************/

        model.setBoardDate(0,0,null);
        model.setBoardDate(3,3, new PiecesTower(new Position(3,3), model, ColorPiece.BLACK) );

        assertEquals( new PiecesTower(new Position(3,3), model, ColorPiece.BLACK).getTypePieces(), model.getBoardDate(3,3).getTypePieces());

        model.selectedPlace(3, 3);// escolher a peça para movimentar

        model.selectedPlace(1, 3);// mover a peça para posição (4, 0)

        assertTrue( model.isFree(3,3));


        assertEquals(new PiecesTower(new Position(1,3), model, ColorPiece.BLACK).getTypePieces(),
                        model.getBoardDate(1,3).getTypePieces()  );
    }






    /***************************************************************************************************
     ***************************************************************************************************
     **************************             TEST PEÇA QUEEN                 ****************************
     * /************************************************************************************************
     ***************************************************************************************************/

    @Test
    void test01Queen()
    {
        /*******************   TESTE POSSSIBLEMOVE QUEEN   *******************/
        model = new GameModel(this.VIEW);

        /******************* Verificar se têm uma peça Queen nas posicçao(7, 4) **************/
        assertEquals( new PieceQueen(new Position(7,4), model, ColorPiece.WHITE).getTypePieces()
                     ,model.getBoardDate(7,4).getTypePieces());


        /***PossibleMove esperado ****/
        List<Position> expectedQueen  = Arrays.asList
                (
                        new Position(6,3), new Position(5,2), new Position(4,1),

                        new Position(3,0), new Position(6,5),

                        new Position(5,6), new Position(4,7),

                        new Position(6,4), new Position(5,4), new Position(4,4),
                        new Position(3,4), new Position(2,4), new Position(1,4) );

        this.model.selectedPlace(7,4);


        assertArrayEquals(expectedQueen.toArray(), this.model.getBoardDate(7, 4).possibleMoves().toArray());

    }
    /********************************************************************************************/

    @Test
    void test02Queen() {
        /******TEST - POSSIBLETAKE QUEEN - DAMA ******/

        model = new GameModel(this.VIEW);

        /***Possible TAKE esperado ****/
        List<Position> expecteQueen = Arrays.asList(new Position(7,4));


        //fazer um click na peça
        this.model.selectedPlace(0,4);

        //verificar se o as posições q vou clicar faz parte do possible take
        assertArrayEquals(expecteQueen.toArray(), this.model.getBoardDate(0, 4).possibleTakes().toArray());

        this.model.selectedPlace(7,4);//capturar a peça adversaria
        assertNotEquals(this.model.getBoardDate(7,4).getTypePieces()
                        ,new PieceQueen(new Position(7,4), this.model, ColorPiece.WHITE).getTypePieces());

        assertEquals(this.model.getBoardDate(7,4).getTypePieces()
                ,new PieceQueen(new Position(7,4), this.model, ColorPiece.BLACK).getTypePieces());

       //*************************************************************

        this.model.selectedPlace(7,4);

        //verificar se o as posições q vou clicar faz parte do possible take
        assertTrue(this.model.getBoardDate(7, 4).possibleTakes().contains(new Position(7,5)));
        this.model.selectedPlace(7,5);//----capturar a peça da (7, 5)

        assertNotEquals(this.model.getBoardDate(7,5).getTypePieces()
                ,new PiecesBishop(new Position(7,5), this.model, ColorPiece.WHITE).getTypePieces());


    }

    /********************************************************************************************/

    @Test
    void test03Queen()

    {

        /*******************   TESTE MUDANÇA DE POSIÇÃO Queen   *******************/

        model = new GameModel(this.VIEW );
        model.selectedPlace(7, 4);// escolher a peça para movimentar


        assertEquals( new PieceQueen(new Position(7,4), model, ColorPiece.WHITE).getTypePieces(), model.getBoardDate(7,4).getTypePieces());

        /****** verificar se o movimento está na possibleMovi*********/
        assertTrue(model.getBoardDate(7,4).possibleMoves().contains(new Position(3, 0)));


        model.selectedPlace(3, 0);// mover a peça para posição (5, 3)

        assertTrue( model.isFree(7,4));
        assertEquals( model.getBoardDate(3,0).getTypePieces(), new PieceQueen(new Position(3,0), model, ColorPiece.WHITE).getTypePieces() );

        /*************
         *  teste QUEEN black   *****************************************************************************/

        model.selectedPlace(0, 4);// escolher a peça para movimentar
        assertEquals( new PieceQueen(new Position(0,4), model, ColorPiece.BLACK).getTypePieces(), model.getBoardDate(0,4).getTypePieces());


        model.selectedPlace(5, 4);// mover a peça para posição (5, 4)

        assertEquals( model.getBoardDate(0,4), null );
        assertEquals(new PieceQueen(new Position(5,4), model, ColorPiece.BLACK).getTypePieces(), model.getBoardDate(5,4).getTypePieces()  );

    }




    /***************************************************************************************************
     ***************************************************************************************************
     **************************             TEST PEÇA KING                   ****************************
     * /************************************************************************************************
     ***************************************************************************************************/

    @Test
    void test01King() {

        /******TESTE POSSIBLE MOVE KING *****************/
        model = new GameModel(this.VIEW);


        model.setBoardDate(7,3,null);
        model.setBoardDate(6,3, new PiecesKing(new Position(6,3), model, ColorPiece.WHITE) );
        //*******

        /******************* Verificar se têm uma peça KING nas posicçao(6, 3) e se na (7, 0) está Livre **************/
        assertEquals( new PiecesKing(new Position(6,3), model, ColorPiece.WHITE).getTypePieces()
                ,model.getBoardDate(6,3).getTypePieces());

        assertTrue(model.isFree(7, 3));


        /***PossibleMove esperado ****/
        List<Position> expectedKing = Arrays.asList
                                                    (
                                                            new Position(5,2), new Position(5,3),
                                                            new Position(5,4), new Position(6,2),
                                                            new Position(6,4), new Position(7,3)
                                                    );
        //fazer um click na peça
        this.model.selectedPlace(6,3);
        assertArrayEquals(expectedKing.toArray(), this.model.getBoardDate(6, 3).possibleMoves().toArray());
    }

    @Test
    void test02King() {
        /****** TESTE POSSIBLE TAKE ****/

        model = new GameModel(this.VIEW);

        /***Possible TAKE esperado ****/
        List<Position> expectedKing = Arrays.asList(
                                                       new Position(3,4),
                                                       new Position(5,5)
                                                   );

        model.setBoardDate(7,2, null);
        model.setBoardDate(7,3, null);
        model.setBoardDate(7,7, null);

        model.setBoardDate(4,4, new PiecesKing(new Position(4,4),this.model, ColorPiece.BLACK));

        assertEquals( new PiecesKing(new Position(4,4), model, ColorPiece.BLACK).getTypePieces()
                                     ,model.getBoardDate(4,4).getTypePieces());


        model.setBoardDate(3,4, new PiecesTower(new Position(3,4), this.model, ColorPiece.WHITE) );
        model.setBoardDate(5,5, new PiecesBishop(new Position(5,5), this.model, ColorPiece.WHITE) );
        model.setBoardDate(5,3, new PiecesKing(new Position(5,3), this.model, ColorPiece.WHITE) );


        //fazer um click na peça
        this.model.selectedPlace(4,4);
        assertArrayEquals(expectedKing.toArray(), this.model.getBoardDate(4, 4).possibleTakes().toArray());

        this.model.selectedPlace(3,4);//capturar a peça adversaria
        assertNotEquals(this.model.getBoardDate(3,4).getTypePieces(), new PiecesTower(new Position(3,4), this.model, ColorPiece.WHITE).getTypePieces());
    }

    /*********************************************************/
    @Test
    void test03King()
    {
            /***************** move para uma posição livre(o utilizador indicou aposição inicial eﬁnal)************/

            model = new GameModel(this.VIEW );
            model.selectedPlace(0, 3);// escolher a peça para movimentar
            assertEquals( new PiecesKing(new Position(0,3), model, ColorPiece.BLACK).getTypePieces()
                                    ,model.getBoardDate(0,3).getTypePieces());


            assertTrue(model.getBoardDate(0,3).possibleMoves().contains(new Position(1, 4)));
            model.selectedPlace(1, 4);// mover a peça para posição (5, 3)

            assertTrue(model.isFree(0,3));
            assertEquals(new PiecesKing(new Position(1,4), model, ColorPiece.BLACK).getTypePieces()
                                        ,model.getBoardDate(1,4).getTypePieces() );

            /********************Mover rei Branco******************/

            model.selectedPlace(7, 3);// escolher a peça para movimentar
            assertEquals( new PiecesKing(new Position(7,3), model, ColorPiece.WHITE).getTypePieces()
                                ,model.getBoardDate(7,3).getTypePieces());


            assertTrue(model.getBoardDate(7,3).possibleMoves().contains(new Position(6, 2)));
            model.selectedPlace(6, 2);// mover a peça para posição

            assertTrue(model.isFree(7,3));
            assertEquals(new PiecesKing(new Position(6,2), model, ColorPiece.WHITE).getTypePieces()
                                                     ,model.getBoardDate(6,2).getTypePieces() );

    }





}//END CLASS GAME_MODEL_TEST