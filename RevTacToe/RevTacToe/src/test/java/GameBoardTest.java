import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class GameBoardTest {
    private FrameFixture frameFixture;
    private myFrame frame;
    private GameLogic game;

    @BeforeEach
    void setUp() {
        // Initialize the GUI and GameLogic on the Event Dispatch Thread (EDT)
        frame = GuiActionRunner.execute(myFrame::new);
        game = frame.getGameLogic();
        frameFixture = new FrameFixture(frame);
    }

    @Test
    void testInitialBoardIsEmpty() {
        // Verify all 5x5 panels start as gray (empty)
        for (int YCoord = 0; YCoord < 5; YCoord++) {
            for (int XCoord = 0; XCoord < 5; XCoord++) {
                assertPanelEmpty(YCoord, XCoord);
            }
        }
    }

    @Test
    void testPlayerTurnSwitchAfterValidMove() {
        clickPanel(2, 2);
        assertFalse(game.isFirstPlayersTurn()); // Player 1 -> Player 2
        assertPanelNotEmpty(2, 2);
        assertPlayer1(2, 2);
        assertPanelColor(2, 2, Color.BLACK);

        clickPanel(2, 1);
        assertTrue(game.isFirstPlayersTurn()); // Player 2 -> Player 1
        assertPanelNotEmpty(2, 1);
        assertPlayer2(2, 1);
        assertPanelColor(2, 1, Color.BLACK);
    }


    //can't automate these tests with a for loop because it will cause a win
    @Test
    void testTopLeftBox() {
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(0, 0);
        assertPanelNotEmpty(0, 0);

        clickPanel(0, 2);
        assertPanelNotEmpty(0, 2);

        clickPanel(1, 2);
        assertPanelNotEmpty(1, 2);

        clickPanel(0, 1);
        assertPanelNotEmpty(0, 1);

        clickPanel(1, 1);
        assertPanelNotEmpty(1, 1);

        clickPanel(2, 0);
        assertPanelNotEmpty(2, 0);

        clickPanel(2, 1);
        assertPanelNotEmpty(2, 1);

        clickPanel(1, 0);
        assertPanelNotEmpty(1, 0);

    }

    @Test
    void testBottomLeftBox() {
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(2, 4);
        assertPanelNotEmpty(2, 4);

        clickPanel(0, 2);
        assertPanelNotEmpty(0, 2);

        clickPanel(1, 2);
        assertPanelNotEmpty(1, 2);

        clickPanel(2, 3);
        assertPanelNotEmpty(2, 3);

        clickPanel(1, 3);
        assertPanelNotEmpty(1, 3);

        clickPanel(0, 4);
        assertPanelNotEmpty(0, 4);

        clickPanel(0, 3);
        assertPanelNotEmpty(0, 3);

        clickPanel(1, 4);
        assertPanelNotEmpty(1, 4);

    }

    @Test
    void testTopRightBox() {
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(4, 2);
        assertPanelNotEmpty(4, 2);

        clickPanel(4, 0);
        assertPanelNotEmpty(4, 0);

        clickPanel(3, 0);
        assertPanelNotEmpty(3, 0);

        clickPanel(4, 1);
        assertPanelNotEmpty(4, 1);

        clickPanel(3, 1);
        assertPanelNotEmpty(3, 1);

        clickPanel(2, 0);
        assertPanelNotEmpty(2, 0);

        clickPanel(2, 1);
        assertPanelNotEmpty(2, 1);

        clickPanel(3, 2);
        assertPanelNotEmpty(3, 2);

    }

    @Test
    void testBottomRightBox() {
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(4, 4);
        assertPanelNotEmpty(4, 4);

        clickPanel(4, 2);
        assertPanelNotEmpty(4, 2);

        clickPanel(3, 2);
        assertPanelNotEmpty(3, 2);

        clickPanel(4, 3);
        assertPanelNotEmpty(4, 3);

        clickPanel(3, 3);
        assertPanelNotEmpty(3, 3);

        clickPanel(2, 4);
        assertPanelNotEmpty(2, 4);

        clickPanel(2, 3);
        assertPanelNotEmpty(2, 3);

        clickPanel(3, 4);
        assertPanelNotEmpty(3, 4);
    }

    @Test
    void testMiddleTopBox(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(1, 0);
        assertPanelNotEmpty(1, 0);

        clickPanel(1, 2);
        assertPanelNotEmpty(1, 2);

        clickPanel(3, 2);
        assertPanelNotEmpty(3, 2);

        clickPanel(1, 1);
        assertPanelNotEmpty(1, 1);

        clickPanel(2, 1);
        assertPanelNotEmpty(2, 1);

        clickPanel(3, 0);
        assertPanelNotEmpty(3, 0);

        clickPanel(3, 1);
        assertPanelNotEmpty(3, 1);

        clickPanel(2, 0);
        assertPanelNotEmpty(2, 0);
    }

    @Test
    void testMiddleBottomBox(){
        clickPanel(2, 4);
        assertPanelNotEmpty(2, 4);

        clickPanel(1, 2);
        assertPanelNotEmpty(1, 2);

        clickPanel(1, 4);
        assertPanelNotEmpty(1, 4);

        clickPanel(3, 4);
        assertPanelNotEmpty(3, 4);

        clickPanel(1, 3);
        assertPanelNotEmpty(1, 3);

        clickPanel(2, 3);
        assertPanelNotEmpty(2, 3);

        clickPanel(3, 2);
        assertPanelNotEmpty(3, 2);

        clickPanel(3, 3);
        assertPanelNotEmpty(3, 3);

        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);
    }


    @Test
    void testNormalBox(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(3, 3);
        assertPanelNotEmpty(3, 3);

        clickPanel(2, 3);
        assertPanelNotEmpty(2, 3);

        clickPanel(1, 3);
        assertPanelNotEmpty(1, 3);

        clickPanel(3, 1);
        assertPanelNotEmpty(3, 1);

        clickPanel(3, 2);
        assertPanelNotEmpty(3, 2);

        clickPanel(1, 2);
        assertPanelNotEmpty(1, 2);

        clickPanel(2, 1);
        assertPanelNotEmpty(2, 1);

        clickPanel(1, 1);
        assertPanelNotEmpty(1, 1);
    }

    @Test
    void testMiddleLeftBox(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(2, 3);
        assertPanelNotEmpty(2, 3);

        clickPanel(1, 3);
        assertPanelNotEmpty(1, 3);

        clickPanel(0, 3);
        assertPanelNotEmpty(0, 3);

        clickPanel(2, 1);
        assertPanelNotEmpty(2, 1);

        clickPanel(1, 2);
        assertPanelNotEmpty(1, 2);

        clickPanel(0, 2);
        assertPanelNotEmpty(0, 2);

        clickPanel(1, 1);
        assertPanelNotEmpty(1, 1);

        clickPanel(0, 1);
        assertPanelNotEmpty(0, 1);
    }

    @Test
    void testMiddleRightBox(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(4, 3);
        assertPanelNotEmpty(4, 3);

        clickPanel(3, 3);
        assertPanelNotEmpty(3, 3);

        clickPanel(2, 3);
        assertPanelNotEmpty(2, 3);

        clickPanel(4, 1);
        assertPanelNotEmpty(4, 1);

        clickPanel(4, 2);
        assertPanelNotEmpty(4, 2);

        clickPanel(3, 2);
        assertPanelNotEmpty(3, 2);

        clickPanel(3, 1);
        assertPanelNotEmpty(3, 1);

        clickPanel(2, 1);
        assertPanelNotEmpty(2, 1);
    }



    // -----------------------
    // Helper Methods
    // -----------------------

    private void clickPanel(int XCoord, int YCoord) {
        String panelName = "panel_" + XCoord + "_" + YCoord   ;
        System.out.println("Clicking panel: " + panelName);
        frameFixture.panel(panelName).click();
    }

    private void assertPanelColor(int XCoord, int YCoord, Color expected) {
        assertThat(frame.board[XCoord][YCoord].getBackground()).isEqualTo(expected);
    }

    private void assertPanelEmpty(int XCoord, int YCoord) {
        assertThat(frame.board[XCoord][YCoord].getComponentCount()).isEqualTo(0);
    }

    private void assertPanelNotEmpty(int XCoord, int YCoord) {
        assertThat(frame.board[XCoord][YCoord].getComponentCount()).isNotEqualTo(0);
    }

    private void assertPlayer1(int XCoord, int YCoord) {
        Component[] components = frame.board[XCoord][YCoord].getComponents();
        JLabel label = (JLabel) components[0];
        assertThat(label.getText()).isEqualTo("X");
    }

    private void assertPlayer2(int XCoord, int YCoord) {
        Component[] components = frame.board[XCoord][YCoord].getComponents();
        JLabel label = (JLabel) components[0];
        assertThat(label.getText()).isEqualTo("O");
    }

    @AfterEach
    void tearDown() {
        frameFixture.cleanUp(); // Clean up resources
    }
}
