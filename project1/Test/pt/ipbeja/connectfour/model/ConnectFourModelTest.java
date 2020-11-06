package pt.ipbeja.connectfour.model;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 1º Trabalho Pratico de Avaliação POO V2
 * @date : 16/04/2019 - 11:38h
 * *********************
 * @package : pt.ipbeja.connectfour.model
 * @class : ConnectFourModelTest	${CLASS}
 * ------
 * "description"
 * Class que testa a class ConnectFourModel - serve  para garantir o correto funcionamento da classe modelo
 * e garantir a qualidada do jogo testa os metodos que comunicam com utilizado"PArte grafica " e inserção na board
 */
class ConnectFourModelTest
{
    private ViewTest VIEW = new ViewTest();

    private ConnectFourModel model = new ConnectFourModel(this.VIEW );

    /****
     * Req. 2 - Código de teste
     *     put in an empty position
     *     1. Colocação de uma peça numa coluna vazia; a peça deve ficar na linha de baixo (linha
     * cinco);
     */
    @Test
    void test01()
    {
        for (int col = 0; col < (this.model.getSIZE_COL() - 1) ; col++)
        {
            int fristPosition = this.model.selectedCell(0, col);
            /***
             * testa-se todas as colunas existe á pôr a primeira peça e deve-se esperar retomar 5 em cada coluna
             */
            assertEquals(5,fristPosition);

        }



    }


    /****
     * Req. 2 - Código de teste
     * 2. Colocação de uma peça numa coluna contendo já uma peça na linha quatro, a segunda linha a contar de baixo;
     * sendo a linha de baixo a linha cinco, a nova peça deve
     * ficar por cima da que lá estava, portanto na linha três;
     */
    @Test
    void test02()
    {

        assertEquals(5, this.model.selectedCell(0,0));
        assertEquals(4, this.model.selectedCell(0,0));
        assertEquals(3, this.model.selectedCell(0,0));


    }


    /****
     * Req. 2 - Código de teste
     * 3. Tentativa de colocação de uma peça numa coluna cheia; o modelo deve ficar igual;
     */
    @Test
    void test03()
    {
        Cell[] arrayFillCol = new Cell[this.model.getSIZE_ROW()];


        for (int line = (this.model.getSIZE_ROW() - 1) ; line >= 0 ; line--)
        {
            this.model.selectedCell(0,0);
            //copiar o valor atual da boad enquanto preencho
            arrayFillCol[line] = this.model.getBoardData(line,0);
        }

        this.model.selectedCell(0,0);
        //verificar se apos inserir novo valor ele não mudou.
        assertTrue(this.model.compareColumn(0, arrayFillCol));
    }


    /*******
     * Res4 - 4. Uma jogada em que o jogador não ganha; esta é a situação mais frequente;
     */
    @Test
    void test04()
    {


        for (int i = 0; i < 5; i++)
        {
            if (i % 2 == 0 )
            {
                this.model.selectedCell(0,i);
            }
            else
            {
                this.model.selectedCell(i, 1) ;
            }

        }
        /***
         * testa-se das colunas"col 4 e col 0 " se existe uma jogada vencedora deve-se esperar retomar true em cada coluna uma vez q há mais de  4 jogadas igauis
         */
        assertFalse(this.model.isWinPosition(0, 4));
        assertFalse(this.model.isWinPosition(0, 0));



    }




    /******
     * 5. Uma jogada em que o jogador ganha com uma linha de quatro na horizontal "row";
     */
    @Test
    void test05()
    {


        for (int i = 0; i <3; i++)
        {
            /*****
             * por três JOGADAS de em tres colunas mas em linhas de horizontal"linha 5 "DAS COLUNAS "COL 0, 1, 2"- tem o PLAYER1 COM 3 ULTIMAS POSICOES OCUPADAS EM 3 COLUNAS "COL 0, 1, 2""
             * por três JOGADAS de em tres colunas mas em linhas de horizontal"linha 4 "DAS COLUNAS "COL 0, 1, 2"- tem o PLAYER2 COM 3 ULTIMAS POSICOES OCUPADAS EM 3 COLUNAS "COL 0, 1, 2""
             ***/
            assertEquals(5, this.model.selectedCell(0,i));
            assertEquals(4, this.model.selectedCell(0,i));
        }


        assertEquals(5, this.model.selectedCell(0,3));
        assertEquals(4, this.model.selectedCell(0,3));
        /*****
         *VERIFICAR se há uma situação de vitoria nas linhas (5 e 4) - sendo q a primeira jogada é do player1"
         ***/
        assertTrue(this.model.isWinPosition(5,3));
        assertTrue(this.model.isWinPosition(4,2));

    }

    /*****
     * Req. 2 - Código de teste
     * 6. Uma jogada em que o jogador ganha com uma linha de quatro na vertical" coluna tem jogada vencedora";
     */
    @Test
    void test06()
    {


       for (int i = 0; i < 8; i++)
        {
            if (i % 2 == 0 )
            {
                this.model.selectedCell(0,3);
            }
             else
                {
                    this.model.selectedCell(0, 1) ;
            }

        }
        /***
         * testa-se das colunas"col 3 e col 1 " se existe uma jogada vencedora deve-se esperar retomar true em cada coluna uma vez q há mais de  4 jogadas igauis
         */
        assertTrue(this.model.isWinPosition(0,3));

        assertTrue(this.model.isWinPosition(0,1));


        //testa-se na coluna 4 o mesmo tem de dar FALSE porque nada está nesta coluna
        assertFalse( this.model.isWinPosition(0,4));

    }


    /****
     * 7. Uma jogada em que o jogador ganha com uma linha de quatro na diagonal;
     */
    @Test
    void test07()
    {

        this.fillCol(this.model.getSIZE_ROW()+1,0);
        assertEquals(0, this.model.selectedCell(0,0));




        assertEquals(5, this.model.selectedCell(0,6));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(2,0));


        this.fillCol(3,1);
        assertEquals(2, this.model.selectedCell(0,1));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(3,1));

        assertEquals(4, this.model.selectedCell(0,6));

        this.fillCol(4,2);
        assertEquals(1, this.model.selectedCell(0,2));
        assertEquals(Cell.PLAYER1, this.model.getBoardData(1,2));


        assertEquals(5, this.model.selectedCell(0,3));


        /*****
         * verificar se na diagonal estão jogadas Player iguais
         */
        assertEquals(Cell.PLAYER2, this.model.getBoardData(5,3));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(4,2));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(3,1));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(2,0));


        assertTrue(this.model.isWinPosition(5,3));//Cell.PLAYER2, model.getBoardData(4,2));
        assertEquals(Cell.PLAYER2, model.getBoardData(4,2));
    }

    /***
     *
     * @param nLength
     * @param nCol
     */
    private void fillCol(int nLength, int nCol)
    {
        for (int i = 0; i < nLength; i++)
        {
            this.model.selectedCell(0,nCol);//p2  -3*
        }
    }








    /**
     * 8. Uma jogada em que o jogo termina sem vitória; note que neste caso o modelo do
        teste deve ser iniciado só com uma posição EMPTY na linha de cima (linha zero) e
        depois realizar uma jogada que resulte em empate.
     ***/
    @Test
    void test08()
    {
        //preencher os dois 1º pares de colunas col 0 e col 1
         this.fillOneTwo(0,1);//

        //preencher os dois 1º pares de colunas col 0 e col 1
         this.fillOneTwo(1,0);//


        /**
         * certificar se que na coluna foi feita as sequencias de 3 jogadas q não gerar nenhuma vitoria
         */
        assertEquals(Cell.PLAYER2, this.model.getBoardData(0,0));
        assertEquals(Cell.PLAYER1, this.model.getBoardData(0,1));


        //preencher os dois 2º pares de colunas col 2 e col 3
        this.fillOneTwo(2,3);//

        //preencher os dois 2º pares de colunas col 2 e col 2
        this.fillOneTwo(3,2);//
        //this.test09();

        //preencher os dois 3º pares de colunas col 4 e col 5
        this.fillOneTwo(4,5);//
        assertEquals(Cell.PLAYER1, this.model.getBoardData(5,4));
        assertEquals(Cell.PLAYER1, this.model.getBoardData(4,4));
        assertEquals(Cell.PLAYER1, this.model.getBoardData(3,4));




        //preencher os dois 3º pares de colunas col 4 e col 5
        this.fillOneTwo(5,4);//
        assertEquals(Cell.PLAYER1, this.model.getBoardData(2,5));
        assertEquals(Cell.PLAYER1, this.model.getBoardData(1,5));
        assertEquals(Cell.PLAYER1, this.model.getBoardData(0,5));

        assertEquals(Cell.PLAYER2, this.model.getBoardData(2,4));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(1,4));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(0,4));


        this.playsOfDraws(6);//


        assertEquals(0, this.model.selectedCell(0,6));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(0,6));

        assertTrue(this.model.isDrawPosition());
        assertFalse(this.model.isWinPosition(0,6));


    }

    /***
     * faz 5 jogadas em uma só coluna
     * @param col
     */
    private void playsOfDraws(int col)
    {
        for (int i = 0; i < 5; i++)
        {
            this.model.selectedCell(0,col);
        }
    }

    /****
     * faz o maximo  de 3 jogadas em duas colunas diferentes " colOne and ColTwo"
     * @param colOne - valor para primeira coluna
     * @param colTwo - valor para segunda coluna
     */
    private void fillOneTwo(int colOne, int colTwo)
    {
        for (int col = 0; col < 3; col++)
        {
            this.model.selectedCell(0,colOne);//p2  -3*
            this.model.selectedCell(0,colTwo);//p2  -3*
        }
    }


    /****
     * test que verificas na boardData se sequencias de jogadas estão corretas de
     */
    @Test
    void test09()
    {
        this.model.selectedCell(0,2);//
        this.model.selectedCell(0,2);//

        assertEquals(Cell.PLAYER1, this.model.getBoardData(5,2));
        assertEquals(Cell.PLAYER2, this.model.getBoardData(4,2));
        assertEquals(Cell.EMPTY, this.model.getBoardData(3,2));

    }
}//end class ConnectFourModelTest



