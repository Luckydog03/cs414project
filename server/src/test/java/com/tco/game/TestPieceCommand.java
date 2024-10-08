import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPieceCommand {
    PieceCommand MyAwesomePieceCommand = new PieceCommand();

    @Test
    @DisplayName("enzob: test setPiece")
    public void testSetPiece() {
        MyAwesomePieceCommand.setPieces();
        assertEquals(16, MyAwesomePieceCommand.getPieces().size());
    }

    @Test
    @DisplayName("luckydog: removePiece")
    public void testRemovePiece() {
        MyAwesomePieceCommand.setPieces();
        MyAwesomePieceCommand.removePiece(0);
        assertEquals(15, MyAwesomePieceCommand.getPieces().size());
    }

    @Test
    @DisplayName("enzob: test upgradepawn")
    public void testUpgradePawn() {
        MyAwesomePieceCommand.setPieces();
        MyAwesomePieceCommand.upgradePawn(0, "Queen");
        assertTrue(MyAwesomePieceCommand.getPieces().get(0) instanceof Queen);
    }

    @Test
    @DisplayName("luckydog: test endPieces")
    public void testEndPieces() {
        MyAwesomePieceCommand.setPieces();
        MyAwesomePieceCommand.endPieces();
        assertEquals(0, MyAwesomePieceCommand.getPieces().size());
    }

    @Test
    @DisplayName("enzob: test getPieces")
    public void testGetPieces() {
        MyAwesomePieceCommand.setPieces();
        assertEquals(16, MyAwesomePieceCommand.getPieces().size());
    }
}
