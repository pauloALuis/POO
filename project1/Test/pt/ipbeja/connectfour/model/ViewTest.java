package pt.ipbeja.connectfour.model;

import pt.ipbeja.connectfour.gui.View;

/**
 * @author : p_lui 17359 - Paulo António Luís
 * @project : 1º Trabalho Pratico de Avaliação POO V2
 * @date : 19/04/2019 - 10:51h
 * *********************
 * @package : pt.ipbeja.connectfour.model
 * @class : ViewTest	${CLASS}
 * ------
 * "description"
 * VIEW para class teste não faz nada
 * foi impementada para n criar erro ao codigo grafico
 */
public class ViewTest implements View

{

    @Override
    public void changeColor(Cell cellPlayer, int line, int col) {

    }

    @Override
    public void disablePosition(int row, int col) {

    }

    @Override
    public void draw()
    {

    }

    @Override
    public void playerWins(Cell playerWin, String s) {

    }


    @Override
    public String displayTextImputDialog() {
        return null;
    }

    @Override
    public void isDisableItemRedo(boolean redo) {

    }

    @Override
    public void isDisableItemUndo(boolean isDisable) {

    }

    @Override
    public void clearAndResetBoard() {

    }

    @Override
    public void isDisableItemNewGame(boolean isDisable) {

    }

}
