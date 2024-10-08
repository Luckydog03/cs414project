public class PieceFactory {
    public Piece getPiece(String pieceType) {
        switch (pieceType) {
            case "Bishop":
                return new Bishop();
            case "King":
                return new King();
            case "Knight":
                return new Knight();
            case "Pawn":
                return new Pawn();
            case "Queen":
                return new Queen();
            case "Rook":
                return new Rook();
            default:
                return null;
        }
    }
}