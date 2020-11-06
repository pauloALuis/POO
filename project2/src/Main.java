import pt.ipbeja.estig.po2.chess.gui.View;
import pt.ipbeja.estig.po2.chess.model.GameModel;
import pt.ipbeja.estig.po2.chess.pieces.TypeOfPieces;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/****
 * Class para expermentar algumas coisas caso necessario
 * esta classe é independente e não contará para o trabalho
 */

public class Main {

    public static void main(String[] args)
    {
        System.out.println("Hello World!");
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                System.out.println((i + 5) +" , "+ (j + 4) );
            }

        }

   /*
            List<Position> possibMove = new ArrayList<>();

            if (inBound(line + 1, col ))
            {
                for (int i = line + 1; i <= GameModel.getSIZE(); i++)
                {
                    col++;
                    if(this.inBound( i, col ) && this.gameModel.isFree( i, col ))
                    {
                        possibMove.add(new Position( i, col ));
                    }else  if( (this.inBound(i, col) && !this.gameModel.isFree(i , col) ))
                    {
                        if ((this.gameModel.getBoardDate(i , col ).getTypePieces() != TypeOfPieces.Rw) &&
                                (this.gameModel.getBoardDate(i , col).getTypePieces() != TypeOfPieces.Rb) &&
                                !(this.gameModel.getBoardDate(i, col).getColorPiece() == this.getColorPiece()))
                        {
                            arryTakes.add(new Position(i, col));
                            return possibMove;
                        }
                    }else return possibMove;
                }
            }
        */
        //public void writeNewUser() {

        final View VIEW = new View() {

            public void updateStyleButton(int line, int col, TypeOfPieces pieces, String s) {

            }

            @Override
            public void updateStyleButton(int line, int col, TypeOfPieces pieces) {

            }

            @Override
            public void updatePossible(int line, int col, boolean flagPossibleMove) {

            }

            @Override
            public void desablePosition(int line, int col, boolean b) {

            }
        };

        GameModel game = new GameModel(VIEW);

        for (int i = 0; i < 8; i++) {
            String str = game.getBoardDate(i, 0).getFirstLetter();

            str += game.getAlgebraABC(0) + i;
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt", true)))
            {
                bw.write(i + str +" , testo a ser escrito");
                bw.newLine();
            } catch (IOException e) {
                System.out.println("CONTEUDO NÃO FOI GRAVADO!");
                e.printStackTrace();
            }
        }






        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                System.out.println((i + 5) +" , "+ (j + 4) );

        try (
            BufferedWriter bw = new BufferedWriter(new FileWriter("test.txt", true))) {
            bw.write((i + 5) +" , "+ (j + 4) + "testo a ser escrito");
            //bw.newLine();
           // bw.write(tfPassword.getText());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
            }

}
    //}

    }
}
