package pt.ipbeja.connectfour.model;

import pt.ipbeja.connectfour.gui.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * ConnectFourModel
 * -
 * Model do jogo, classe responsavel pela regras do jogo,
 * bem como comunicar com board e ser testada
 * *********************
 *
 * @author: p_lui 17359 - Paulo António Luís
 * @project 1º Trabalho Pratico de Avaliação POO
 * "Package Name: "   pt.ipbeja.connectfour.gui
 * @date 11/04/2019 * @tame 15:56
 */
public class ConnectFourModel {


    private final View VIEW;
    private final int SIZE_ROW =6;
    private final int SIZE_COL =7;

    private final Cell[][] boardData;
    private int turnCounter = 0;
    private int countUndo = 0;


    private int playerCount1 = (this.SIZE_COL * this.getSIZE_ROW()) - 1;
    private int playerCount2 = (this.SIZE_COL * this.getSIZE_ROW()) - 1;
    private String[] scorePoint = {"0.  ","0.  ", "0.  "};
    private List<Position> logPosition;

    /**
     * Construtor da class
     *
     *
     */
    public ConnectFourModel(View view)
    {
        this.boardData = new Cell[this.SIZE_ROW][this.SIZE_COL];
        this.fillBoard();
        this.VIEW = view;
        this.logPosition = new ArrayList<Position>();
    }





    /***
     * Cria o game board
     * * @author Sascha Geng, Diogo Pina Manique
     *  * @version 24-05-2018
     */
    private void fillBoard()
    {
        System.out.format("Create [%d x %d] board\n", this.SIZE_ROW, this.SIZE_COL);
        for (int line = 0; line <  this.SIZE_ROW; line++) {
            for (int col = 0; col < this.SIZE_COL; col++) {
                this.boardData[line][col] = Cell.EMPTY;
            }
        }

        //this.isCheckDisableItemNewGame(turnCounter);

    }

    /*****
     * Responsavel por cuidar de toda comunicação com jogo,
     * atualiza a boarData, pedi para mudar a cor das elipse da parte grafica, pedi para atualizar os pontos da mudança
     * do estado dos botões de UNDO/REDO/NEWGAME. Atualiza o TURNERCOUNTER
     *
     * @param lineButton
     * @param col
     */
    public int selectedCell(int lineButton, int col)
    {
        assert(0 <= lineButton && (lineButton <= (this.getSIZE_ROW() - 1)) );
        assert(0 <= col  && (col  <= (this.getSIZE_COL() - 1)) );


        int row = this.getLineEmptyBoardData(col);
        assert(row >= 0  && (row <= (this.getSIZE_ROW() - 1)) );

        if (this.isFree(row,col))
        {
             this.updateBoardState(row,  col);
             this.checkBoardState(row,  col);
             this.VIEW.changeColor(this.getBoardData(row, col),row, col);//pedir a board para mudar a cor do objeto
             this.checkDisableButton(row,col);//ver se deve ou não desativar o botão de click
             this.updatePlayerPoint(this.turnCounter);
             this.historicPlay(row, col, this.turnCounter);//guardar lista de Jogadas
             this.turnCounter++;
             this.countUndo = 0;
             this.checkDisableItemUndo(this.turnCounter);
             this.isCheckDisableItemRedo(this.countUndo, this.turnCounter);
             this.isCheckDisableItemNewGame(this.turnCounter);

        }
        return row;
    }

    /****
     * pede a classe ConnectFourBoard para por a disable os botoes de UNDO/REDO
     * @param turn
     */
    private void checkDisableItemUndo(int turn)
    {
        if(this.isDisableItemUNDO(turn))
        {
            this.VIEW.isDisableItemUndo(false);
        }else {
            this.VIEW.isDisableItemUndo(true);

        }
    }

    /******
     * verifcar se o turnCounter é maior que 0 para fazer UNDO
     * @param turn
     * @return devolve true se o turn foi maior que zero
     */
    private boolean isDisableItemUNDO(int turn) {
        return turn > 0;
    }


    /*****
     * adiciona elementos ao Arrays de Posições
     * @param row - corresponde a coordenada da linha ocupada
     * @param col - corresponde a coordenada da Col ocupada
     * @param turn - corresponde ao numero de jogadas
     */
    private void historicPlay(int row, int col, int turn)
    {
        Position position = new  Position(row, col, this.getBoardData(row, col) );
        this.logPosition.add(turn, position);
    }


    /**
     * Check if the board cell is free (no player)
     * @param line Line coordinate
     * @param col Column coordinate
     * @return true if the cell is free
     * @author Sascha Geng, Diogo Pina Manique - copiado do TicTacToe e adaptado
     * @version 24-05-2018
     */
    public boolean isFree(int line, int col)
    {
        return boardData[line][col] == Cell.EMPTY;
    }




    /***
     * Verifica se deve pedir a class board se pode desativar o  botão
     * @param row
     * @param col
     */
    private void checkDisableButton(int row, int col) {
        if(this.lastPlayedColumn(row) && !isFree(row,col))
        {
            assert ( (0 <= row && (row <= this.getSIZE_ROW() -1))
                    || (0 <= col && (col <= this.getSIZE_COL() -1)) );
            VIEW.disablePosition(row, col);
        }
    }

    /***
     * verifica se estou na ultima linha da coluna
     * @param linePlayed
     * @return True se a linha em que se está é 0
     */
    private boolean lastPlayedColumn(int linePlayed)
    {
        return linePlayed == 0;
    }

    /**
     * verica de cima para baixo na BoardDate na coluna"col" se existe uma posição vazia
     * @param col - valor da coluna
     * @return linha vazia ou -1 se não existir a linha vazia
     */
    private int getLineEmptyBoardData(int col)
    {
        for (int newLine = (this.SIZE_ROW - 1); newLine >= 0; newLine--)
        {
                if(this.getBoardData(newLine,col).equals(Cell.EMPTY))
                {
                   return newLine ;
                }
        }
     return 0;
    }



    /****
     * Atualiza a boardData atraves da linha e coluna e pede ao ConnectFourBoard para mudar a cor do circulo
     * @param row
     * @param col
     */
    private void updateBoardState(int row, int col)
    {
        assert ((row >= 0) && (col >= 0));
        boardData[row][col] = (turnCounter % 2 == 0) ? Cell.PLAYER1 : Cell.PLAYER2;
    }

    /*****
     * devolve a  boardData - atraves da coordenadas de linha e col
     * @param row
     * @param col
     * @return devovlve o Cell"Player1/Player2/EMPTY"
     */
    protected Cell getBoardData(int row, int col)
    {
        return this.boardData[row][col];
    }

    /***
     *
     * @return SIZE_ROW
     */
    public int getSIZE_ROW()
    {
        return SIZE_ROW;
    }

    /***
     *
     * @return SIZE_COL
     */
    public int getSIZE_COL()
    {

        return SIZE_COL;
    }


    /******
     * verifica se há possibilidade de vitoria nas jogadas
     * @param line
     * @param col
     */
    private void checkBoardState(int line, int col)
    {
        if (this.inWinnableTurn(this.turnCounter))
        {
            // Só vale a pena começar a verificar condições de vitória a partir do número mínimo de jogadas para algum dos jogadores vencer
            // Ex. para um tabuleiro 6x7, é necessário haver pelo menos 8 jogadas para vencer
            if (this.isWinPosition(line, col))
            {
                String log = this.logStr(this.checkScore((this.turnCounter % 2 == 0 ? this.playerCount1 : this.playerCount2 )));
                this.VIEW.playerWins( (this.turnCounter % 2 == 0 ? Cell.PLAYER1 :  Cell.PLAYER2 ), log); // 0 ou 1
                this.createNewGame();
            }
            else if (this.isDrawPosition())
            {
                this.VIEW.draw();
                this.createNewGame();

            }
        }
    }


    /*******
     * atualiza a pontuação
     * @param turn
     * @return playerCount
     */
    private int updatePlayerPoint(int turn)
    {
            if (turn % 2 == 0)
            {
                return this.playerCount1--;
            }
            else
            {
                  return this.playerCount2--;
            }
    }

    /*******
     * Verifica a pontuação atual é maior que as 3 melhores
     * @param newScore
     * @return devolve o newScore
     */
    private int checkScore(int newScore)
    {
           for (int i = 0; i < 3; i++)
            {
               String [] score = scorePoint[i].split("\\.");

                if(this.conertStringForInt(score[0]) <= newScore)
                {
                    String valorArr = newScore + "." + this.VIEW.displayTextImputDialog();
                    scorePoint[2]= valorArr;
                    this.sortReverseOrder();
                    break;
                }
            }


        return newScore;
    }


    /****
     * cria String de posições e verifica se o record feito atualmente está na lista
     * @param newScore
     * @return a lista das 3 melhores jogadas
     */
     private String logStr(int newScore)
     {
         String str="";

         for (int i = 0; i < 3; i++)
         {
              String [] score = this.scorePoint[i].split("\\.");
              if(this.conertStringForInt(score[0]) == newScore)
               {
                   str += "\n"+ score[0] + "-" + score[1]  + "*** ";
               }
              else {

                  str += "\n" + score[0] + "-" + score[1] ;
              }

         }
         return str;
     }

    /******
     * Ordena deforma decrescente o array
     */
    private void sortReverseOrder()
     {
        Arrays.sort(this.scorePoint,Collections.reverseOrder());

     }


    /****
     * convert String para Inteiro
     * @param valorString
     * @return
     */
    private int conertStringForInt(String valorString)
    {
        return Integer.parseInt(valorString); // Caso você queira tipo int, que é o usual.

    }


    /**
     * Checks if it is possible to win in this turn
     * @param turn the turn to check
     * @return true if can win false otherwise
     *
     */
    private boolean inWinnableTurn(int turn)
    {
        return turn >= this.SIZE_ROW;
    }

    /**
     * Check if the game ended in a draw
     * @return true if the draw condition was found
     */
    protected boolean isDrawPosition()
    {
        return this.turnCounter >= ((this.SIZE_ROW * this.SIZE_COL) - 1 );
    }

    /**
     * Check for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    protected boolean isWinPosition(int line, int col) {
        // Verificar se a jogada resultou numa sequência vencedora
        // Os métodos são invocados sequencialmente e quando um deles devolve 'true' os seguintes já não são invocados

        return ( this.hasWinnigAntiDiagonalBiggerIqualCol(line, col) || this.hasWinningHorizontalLine(line)
                || this.hasWinnigVertical(col)                       || this.hasWinningLateralDiagonalMod1(line, col)
                || this.hasWinningLateralDiagonalMod02(line, col)    || this.hasWinningMainDiagonal(line, col)
                || this.hasWinnigAntiDiagonalLessCol(line, col)   );
        // Por exemplo:: Se hasWinningHorizontalLine -> false ...continua... se hasWinnigVerticalCol -> true,
        // termina aqui e devolve true.
    }

    /******
     * Verifica a situação de vitoria nas diagonais em que a soma da linha + col é superior a ROW e a COL
     * @param line
     * @param col
     * @return true se encontrar sequencia de peças iguais na diagonal
     */
    private boolean hasWinnigAntiDiagonalBiggerIqualCol(int line, int col)
    {

        if (this.checkSumBiggerIqualCol(line, col))
        { // Se a soma dos valores da linha e da coluna for igual ao tamanho da grelha -1, estamos na antidiagonal

            int nCount=0;

            int j = this.SIZE_COL-1;

            for (int i = this.getLineStarted(line, col); i < (this.SIZE_ROW - 1); i++)
            {
                if ( (this.boardData[i][j] == ( this.boardData[i + 1][j - 1]) )  &&
                        (this.boardData[i][j] != Cell.EMPTY) )
                {
                    nCount++;
                }
                else{
                    nCount=0;

                }

                j--;


                if(nCount>= 3)
                {
                    return true;
                }


            }
        }

        return false;


    }


    /******
     * Metodo para vericar onde começa a linha dependendo da soma da linha + col
     * @param line
     * @param col
     * @return
     */
    private int getLineStarted(int line, int col) {
        if(this.getSumLineCol(line, col)==(this.SIZE_COL - 1) )
        {
         return 0;
        }
        else if (this.getSumLineCol(line, col)==(this.SIZE_COL ) )
        {
            return 1;
        }
        else if (this.getSumLineCol(line, col)==(this.SIZE_COL + 1) )
        {
            return 2;
        }

        return 0;
    }

    /****
     * verifica se a linha e a col onde foi jogada se pertence ao grupo de col maior ou igaul SIZE_COL
     * @param line
     * @param col
     * @return
     */
    private boolean checkSumBiggerIqualCol(int line, int col)
    {

        return ((this.SIZE_COL + 1)>= (line + col) ) && ((line + col) >= (this.SIZE_COL -1));
    }

    /**
     * Check line for winning condition
     * @param line
     * @return true if a winning condition was found
     */
    private boolean hasWinningHorizontalLine(int line)
    {
        int count=0;
        // Verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        for (int j = 0; j < this.SIZE_COL - 1; j++)
        {
            if (this.getBoardData(line,j).equals(this.getBoardData(line,(j + 1))) &&
                    this.boardData[line][j] != Cell.EMPTY  )
            {
                count++;
            }
            else
            {
                count=0; // Se os valores diferem, podemos terminar aqui e devolver false

            }

            if(count >= 3)
            {
                return true; // Só chegamos a este ponto se de facto todos os valores forem iguais
            }
        }

        return false; // Só chegamos a este ponto se de facto todos os valores forem diferentes
    }

    /**
     * Check column for winning condition
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinnigVertical(int col) {

        int counte = 0;
        // Verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        for (int i = 0 ;i < (this.SIZE_ROW - 1);  i++)
        {

            if ((this.getBoardData(i,col).equals(this.getBoardData((i + 1),col))) &&
                    this.boardData[i][col] != Cell.EMPTY   )
            {
                counte++;
            }
            else
            {
                counte=0; //se forem diferentes reinicia a contagem
            }


            if(counte >= 3 )
            {
                return true; // Só chegamos a este ponto se de facto todos os valores forem iguais
            }

        }

        return false; // Só chegamos a este ponto se de facto todos os valores forem diferentes

    }


    /**
     * Check diagonal for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinningMainDiagonal(int line, int col) {
        // Se jogada ocorreu na diagonal, verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        if ( inMainDiagonal(line, col))
        {
            int nCount=0;
            for (int i = 0; i < this.SIZE_COL - 2; i++) {
                if ( (this.boardData[i][i] == this.boardData[i + 1][i + 1])  &&
                        this.boardData[i][i] != Cell.EMPTY )
                {
                    nCount++;
                }

                else
                {
                    nCount=0;
                }

                if(nCount>= 3)
                {
                    return true;
                }
            }


        }
        return false;
    }

    /**
     * Se os valores da linha e coluna forem os mesmos, sabemos estar numa diagonal
     * @param line line value
     * @param col column value
     * @return true if (line, col) is in main diagonal false otherwise
     */
    private boolean inMainDiagonal(int line, int col) {
        return line == col;
    }



    /**
     * Check diagonal for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinningLateralDiagonalMod1(int line, int col) {
        // Se jogada ocorreu na diagonal, verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        if ( this.inLateralDiagonal(line, col))
        {
            int nCount=0, j=0;
            for (int i = this.getAbsLineCol(line, col); i < this.SIZE_COL - 1; i++)
            {

                if ( (this.boardData[j][ i] == this.boardData[j +1][ i + 1])  &&
                        (this.boardData[j][i] != Cell.EMPTY) )
                 {
                        nCount++;
                 }
                else
                    {
                        nCount=0;
                    }

                    if(nCount>= 3)
                    {
                        return true;
                    }
                j++;

            }


        }
        return false;
    }



    /**
     * Se os valores da linha e coluna o modulo for  1, sabemos que esta numa diagonal lateral
     * @param line line value
     * @param col column value
     * @return true if (line, col) is in main diagonal false otherwise
     */
    private boolean inLateralDiagonal(int line, int col)
    {

        return (line < col) && ( (this.getAbsLineCol(line, col) == 1) ||
                (this.getAbsLineCol(line, col) == 2) || (this.getAbsLineCol(line, col)== 3) ) ;
    }


    /**
     * Check diagonal for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinningLateralDiagonalMod02(int line, int col) {
        // Se jogada ocorreu na diagonal, verificar se a linha onde a jogada ocorreu têm uma sequência vencedora
        if ( this.inLateralDiagonal02(line, col))
        {
            int nCount=0, j=0;
            for (int i = this.getAbsLineCol(line, col); i < this.SIZE_ROW - 1; i++)
            {
                if ( (this.boardData[i][j ] == this.boardData[i + 1][j +1 ])  &&
                        this.boardData[i][j] != Cell.EMPTY )
                {
                    nCount++;
                }
                else
                {
                    nCount=0;
                }

                if(nCount>= 3)
                {
                    return true;
                }
                j++;

            }


        }
        return false;
    }

    /**
     * Se os valores da linha e coluna o modulo for  1, sabemos que esta numa diagonal lateral
     * @param line line value
     * @param col column value
     * @return true if (line, col) is in main diagonal false otherwise
     */
    private boolean inLateralDiagonal02(int line, int col)
    {

        return (line > col) && ( (this.getAbsLineCol(line, col) == 1) ||
                (this.getAbsLineCol(line, col) == 2) || (this.getAbsLineCol(line, col)== 3) ) ;
    }

    /******
     * devolve o calcula e devolve o valor absoluto da linha - coluna
     * @param line
     * @param col
     * @return valor absoluto da Linha - Col
     */
    private int getAbsLineCol(int line, int col) {
        return Math.abs(line - col);
    }


    /**
     * Check antidiagonal for winning condition
     * @param line
     * @param col
     * @return true if a winning condition was found
     */
    private boolean hasWinnigAntiDiagonalLessCol(int line, int col) {
        // Se jogada ocorreu na antidiagonal, verificar se a linha onde a jogada ocorreu têm uma sequência vencedora

        if (this.checkAntDiagonal(line, col))
        { // Se a soma dos valores da linha e da coluna for igual ao tamanho da grelha -1, estamos na antidiagonal

            int nCount=0;
            int j = this.getSumLineCol(col,line);

            for (int i = 0; i < this.getSumLineCol(col,line); i++)
            {

                if ( (this.boardData[i][j] == this.boardData[i + 1][j - 1] )  &&
                        (this.boardData[i][j] != Cell.EMPTY) )
                {
                    nCount++;
                }
                else{
                    nCount=0;

                }
                j--;

                if(nCount >= 3)
                {
                    return true;
                }
            }
        }

        return false;
    }

    /****
     * calcula a soma da linha e col
     * @param col
     * @param line
     * @return
     */
    private int getSumLineCol(int col, int line) {
        return col + line;
    }


    /******
     * verifica se a jogada atual foi feita na antiDiagonal
     * @param line
     * @param col
     * @return TRUE/FALSE SE FOI FEITA NA ANTIDIAGONAL INFERIOR AO VALOR DA COLUNA
     */
    private boolean checkAntDiagonal(int line, int col)
    {
        return ((line + col) < this.getSIZE_COL() - 1);  // || ( (line + col) <= this.getSIZE_COL() - 1) ;
    }


    /****
     *compara se os valores que estão nos dois array são iguais
     * @param col
     * @param arrayFillCol
     * @return devolve falso encontrar algum elemento diferentes do outro em uma das posições
     */
    protected boolean compareColumn(int col, Cell[] arrayFillCol)
    {
        for (int line = getSIZE_ROW() - 1; line >= 0 ; line--) {
            if( this.getBoardData(line,col)!= arrayFillCol[line])
            {
                return false;
            }
        }
        return true;
    }

    /*****
     * cria novo cenario de jogo ou seja, limpar a board
     * limpa o ArrayList de posições
     * limpa a BoardData
     * E atualiza o turnCounter, playerCount
     */
    public void createNewGame()
    {
        if(this.isCreateNewGame(this.turnCounter))
        {
            this.fillBoard();
            this.logPosition.clear();
            this.turnCounter = 0;
            this.playerCount2 = (this.SIZE_COL * this.getSIZE_ROW()) - 1;
            this.playerCount1 = (this.SIZE_COL * this.getSIZE_ROW()) - 1;

            this.VIEW.clearAndResetBoard();//comunicar a board para limpar
        }

        this.isCheckDisableItemNewGame(this.turnCounter);

    }

    private void isCheckDisableItemNewGame(int counter)
    {
        if(counter == 0) {
            this.VIEW.isDisableItemNewGame(true);
        }else {
            this.VIEW.isDisableItemNewGame(false);

        }

    }

    /******
     * vefica se existe condição p/ permitir criar novo jogo
     * @param turn
     * @return
     */
    private boolean isCreateNewGame(int turn)
    {
        return turn > 0;
    }

    /****
     * verifica se pode fazer o Redo ou seja rezar a jogada disfeita pelo UNDO
     */
    public void redoMove()
    {
        if (doRedo(this.turnCounter))
        {
            int nextCol = this.logPosition.get(turnCounter).getCol();
            int nextRow = this.logPosition.get(turnCounter).getRow();

            this.updatePlayerPoint(this.turnCounter);//atualizar os pontos
            this.boardData[nextRow][nextCol] = this.logPosition.get(turnCounter).getPlayer() ;
            this.VIEW.changeColor(this.getBoardData(nextRow, nextCol),nextRow, nextCol);
            this.turnCounter++;
            this.VIEW.isDisableItemUndo(false);
            this.countUndo--;
        }

        this.isCheckDisableItemRedo(this.countUndo, this.turnCounter);

    }

    /******
     * Coloca em disable o item menu Redo sempre q a condição se verificar
     * @param countU - valor do undo efetuado
     * @param turn - valor da jogada atual
     */
    private void isCheckDisableItemRedo(int countU, int turn) {
        if((countU == 0) || (turn >= this.logPosition.size() ))
        {
            this.VIEW.isDisableItemRedo(true);

        }

    }

    /*****
     * verifica se está em situação de poder fazer o redo
     * @param turn - numero de jogadas efetuadas
     * @return true  o Nº de jogadas efetuadas forem menor q ROW * COL e Nº Jogadas superior a 0 e superior ou igual ao tamanho do array
     */
    private boolean doRedo(int turn) {
        return (turn < ((this.getSIZE_COL() * this.getSIZE_COL()) - 1) ) && (turn >= 0)
                && (turn <= this.logPosition.size() )  && (this.countUndo > 0);
    }


    /******
     * faz o Undo ou seja desfaz a jogada etuada pelo utilizador.
     */
    public void UndoMove()
    {

         if (this.doUndo(turnCounter))
         {
             this.turnCounter--;

             int backCol = this.logPosition.get(this.turnCounter).getCol();
             int backRow = this.logPosition.get(this.turnCounter).getRow();

             this.boardData[backRow][backCol] = Cell.EMPTY;
             this.VIEW.changeColor(getBoardData(backRow, backCol), backRow, backCol);//mudar a cor da elipse
             this.VIEW.isDisableItemRedo(false);//desativar o botão
             this.countUndo++;
         }


        this.isCheckDisableItemUndo(this.turnCounter);





    }

    private void isCheckDisableItemUndo(int i)
    {
        if(this.logPosition.size() == 0 || (i == 0))
        {
            this.VIEW.isDisableItemUndo(true);

        }

    }

    /****
     * verifica se está em situação de se efetuar o Undo
     * @param turn
     * @return
     */
    private boolean doUndo(int turn) {
        return turn > 0 && (0 <= this.logPosition.size() );
    }
}//end class
