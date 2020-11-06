package pt.ipbeja.estig.po2.chess.gui;

import pt.ipbeja.estig.po2.chess.pieces.TypeOfPieces;

/**
 * @author 17359 - Paulo António Luís
 * @usar_name p_lui
 * @date 27/mai/2019
 * @time 16:00
 * *****************************************
 * ********* Description *******************
 * @project: 17359_PauloLuis_TP02_PO2_2018-2019_V1
 * @package: pt.ipbeja.estig.po2.chess.gui
 * @class View
 * ********       to do    ********
 * classe interfave da GUI - parte que cuida da comunicação com a GUI
 ******/
public interface View {


    void updateStyleButton(int line, int col, TypeOfPieces pieces);

    void updatePossible(int line, int col, boolean flagPossibleMove);

    void desablePosition(int line, int col, boolean b);
}
