package pt.ipbeja.connectfour.gui;

import pt.ipbeja.connectfour.model.Cell;

/**
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 1º Trabalho Pratico de Avaliação POO
 * @date : 15/04/2019 - 14:10h
 * *********************
 * @package : pt.ipbeja.connectfour.model
 * @class : View	${CLASS}
 * ------
 * "description"
 * VIEW interface da classe Board cuida de toda comunicação
 * feita para a classe q impementa"ConnectFourBoard"
 */
public interface View {

        void changeColor(Cell cellPlayer, int line, int col);
        void disablePosition(int row, int col);
        void draw();
        void playerWins(Cell playerWin, String s);


        String displayTextImputDialog();
        void isDisableItemRedo(boolean isDisable);
        void isDisableItemUndo(boolean isDisable);

        void clearAndResetBoard();


        void isDisableItemNewGame(boolean isDisable);
}
