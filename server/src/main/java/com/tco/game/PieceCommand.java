import java.util.ArrayList;
import java.util.List;

public class PieceCommand {
    //list of pieces
    private List<Piece> pieces = new ArrayList<Piece>(); 

    private PieceFactory factory = new PieceFactory();

    public void setPieces(){
        //start of game
        for(int i = 0; i < 8; i++){
            pieces.add(factory.getPiece("Pawn"));
        }
        for(int j = 0; j < 2; j++){
            pieces.add(factory.getPiece("Rook"));
            pieces.add(factory.getPiece("Bishop"));
            pieces.add(factory.getPiece("Knight"));
        }
        pieces.add(factory.getPiece("King"));
        pieces.add(factory.getPiece("Queen"));
    }

    public void removePiece(int index){
        //when piece dies
        pieces.remove(index);

    }

    public void upgradePawn(int index, String piece){
        //when pawn gets to other side
        pieces.set(index, factory.getPiece(piece));
    }

    public void endPieces(){
        //when game end
        pieces.clear();
    }

    public List getPieces(){
        return pieces;
    }
}
