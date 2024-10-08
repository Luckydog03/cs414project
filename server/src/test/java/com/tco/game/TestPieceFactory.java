import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPieceFactory {
    PieceFactory MyAwesomePieceFactory = new PieceFactory();

    @Test
    @DisplayName("enzob: test bishop")
    public void testBishop() {
        Piece piece = MyAwesomePieceFactory.getPiece("Bishop");
        assertTrue(piece instanceof Bishop);
    }

    @Test
    @DisplayName("luckydog: test king")
    public void testKing() {
        Piece piece = MyAwesomePieceFactory.getPiece("King");
        assertTrue(piece instanceof King);
    }

    @Test
    @DisplayName("enzob: test knight")
    public void testKnight() {
        Piece piece = MyAwesomePieceFactory.getPiece("Knight");
        assertTrue(piece instanceof Knight);
    }

    @Test
    @DisplayName("luckydog: test pawn")
    public void testPawn() {
        Piece piece = MyAwesomePieceFactory.getPiece("Pawn");
        assertTrue(piece instanceof Pawn);
    }

    @Test
    @DisplayName("enzob: test queen")
    public void testQueen() {
        Piece piece = MyAwesomePieceFactory.getPiece("Queen");
        assertTrue(piece instanceof Queen);
    }

    @Test
    @DisplayName("luckydog: test rook")
    public void testRook() {
        Piece piece = MyAwesomePieceFactory.getPiece("Rook");
        assertTrue(piece instanceof Rook);
    }
}
