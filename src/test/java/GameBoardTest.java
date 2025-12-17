//in terminal, cd into root directory of the project and run 'mvn test' to run all tests
//or do 'mvn -Dtest=GameBoardTest#testTopRightBox test' to run only the 'testTopRightBox' test

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
                assertPanelEmpty(XCoord, YCoord);
            }
        }
    }

    @Test
    void testPlayerTurnSwitchAfterValidMove() {
        clickPanel(2, 2);
        Assertions.assertFalse(game.isFirstPlayersTurn()); //player 1 -> player 2
        assertPanelNotEmpty(2, 2);
        assertPlayer1(2, 2);
        assertPanelColor(2, 2, Color.BLACK);

        clickPanel(2, 1);
        Assertions.assertTrue(game.isFirstPlayersTurn()); //player 2 -> player 1
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

    @Test
    void testInnerWidthChangerLeft(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(1, 2);
        assertPanelNotEmpty(1, 2);

        clickPanel(4, 0);
        assertPanelEmpty(4, 0);

        clickPanel(4, 1);
        assertPanelEmpty(4, 1);

        clickPanel(4, 2);
        assertPanelEmpty(4, 2);

        clickPanel(4, 3);
        assertPanelEmpty(4, 3);

        clickPanel(4, 4);
        assertPanelEmpty(4, 4);
    }

    @Test
    void testInnerWidthChangerRight(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(3, 2);
        assertPanelNotEmpty(3, 2);

        clickPanel(0, 0);
        assertPanelEmpty(4, 0);

        clickPanel(0, 1);
        assertPanelEmpty(0, 1);

        clickPanel(0, 2);
        assertPanelEmpty(0, 2);

        clickPanel(0, 3);
        assertPanelEmpty(0, 3);

        clickPanel(0, 4);
        assertPanelEmpty(0, 4);
    }

    @Test
    void testOuterWidthChangerLeft(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(0, 2);
        assertPanelNotEmpty(0, 2);

        clickPanel(3, 0);
        assertPanelEmpty(3, 0);

        clickPanel(3, 1);
        assertPanelEmpty(3, 1);

        clickPanel(3, 2);
        assertPanelEmpty(3, 2);

        clickPanel(3, 3);
        assertPanelEmpty(3, 3);

        clickPanel(3, 4);
        assertPanelEmpty(3, 4);

        clickPanel(4, 0);
        assertPanelEmpty(4, 0);

        clickPanel(4, 1);
        assertPanelEmpty(4, 1);

        clickPanel(4, 2);
        assertPanelEmpty(4, 2);

        clickPanel(4, 3);
        assertPanelEmpty(4, 3);

        clickPanel(4, 4);
        assertPanelEmpty(4, 4);

    }

    @Test
    void testOuterWidthChangerRight(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(4, 2);
        assertPanelNotEmpty(4, 2);

        clickPanel(1, 0);
        assertPanelEmpty(1, 0);

        clickPanel(1, 1);
        assertPanelEmpty(1, 1);

        clickPanel(1, 2);
        assertPanelEmpty(1, 2);

        clickPanel(1, 3);
        assertPanelEmpty(1, 3);

        clickPanel(1, 4);
        assertPanelEmpty(1, 4);

        clickPanel(0, 0);
        assertPanelEmpty(0, 0);

        clickPanel(0, 1);
        assertPanelEmpty(0, 1);

        clickPanel(0, 2);
        assertPanelEmpty(0, 2);

        clickPanel(0, 3);
        assertPanelEmpty(0, 3);

        clickPanel(0, 4);
        assertPanelEmpty(0, 4);
    }

    @Test
    void testInnerHeightChangerUp(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(2, 1);
        assertPanelNotEmpty(2, 1);

        clickPanel(0, 4);
        assertPanelEmpty(0, 1);

        clickPanel(1, 4);
        assertPanelEmpty(1, 4);

        clickPanel(2, 4);
        assertPanelEmpty(2, 4);

        clickPanel(3, 4);
        assertPanelEmpty(3, 4);

        clickPanel(4, 4);
        assertPanelEmpty(4, 4);
    }

    @Test
    void testInnerHeightChangerDown(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(2, 3);
        assertPanelNotEmpty(2, 3);

        clickPanel(0, 0);
        assertPanelEmpty(0, 0);

        clickPanel(1, 0);
        assertPanelEmpty(1, 0);

        clickPanel(2, 0);
        assertPanelEmpty(2, 0);

        clickPanel(3, 0);
        assertPanelEmpty(3, 0);

        clickPanel(4, 0);
        assertPanelEmpty(4, 0);
    }

    @Test
    void testOuterHeightChangerUp(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(2, 0);
        assertPanelNotEmpty(2, 0);

        clickPanel(0, 3);
        assertPanelEmpty(0, 3);

        clickPanel(1, 3);
        assertPanelEmpty(1, 3);

        clickPanel(2, 3);
        assertPanelEmpty(2, 3);

        clickPanel(3, 3);
        assertPanelEmpty(3, 3);

        clickPanel(4, 3);
        assertPanelEmpty(4, 3);

        clickPanel(0, 4);
        assertPanelEmpty(0, 4);

        clickPanel(1, 4);
        assertPanelEmpty(1, 4);

        clickPanel(2, 4);
        assertPanelEmpty(2, 4);

        clickPanel(3, 4);
        assertPanelEmpty(3, 4);

        clickPanel(4, 4);
        assertPanelEmpty(4, 4);

    }

    @Test
    void testOuterHeightChangerDown(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(2, 4);
        assertPanelNotEmpty(2, 4);

        clickPanel(0, 0);
        assertPanelEmpty(0, 0);

        clickPanel(1, 0);
        assertPanelEmpty(1, 0);

        clickPanel(2, 0);
        assertPanelEmpty(2, 0);

        clickPanel(3, 0);
        assertPanelEmpty(3, 0);

        clickPanel(4, 0);
        assertPanelEmpty(4, 0);

        clickPanel(0, 1);
        assertPanelEmpty(0, 1);

        clickPanel(1, 1);
        assertPanelEmpty(1, 1);

        clickPanel(2, 1);
        assertPanelEmpty(2, 1);

        clickPanel(3, 1);
        assertPanelEmpty(3, 1);

        clickPanel(4, 1);
        assertPanelEmpty(4, 1);
    }


    @Test
    void testTopLeftCornerBorderChanger(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(0, 0);
        assertPanelNotEmpty(0, 0);

        //I'm able to use for loops here because I'm checking that the boxes are empty, so
        //it won't interfere with the win mechanism
        for(int i = 3; i < 5; i++){
            for(int j = 0; j < 3; j++){
                clickPanel(i, j);
                assertPanelEmpty(i, j);
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 3; j < 5; j++){
                clickPanel(i, j);
                assertPanelEmpty(i, j);
            }
        }
    }

    @Test
    void testTopRightCornerBorderChanger(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(4, 0);
        assertPanelNotEmpty(4, 0);

        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 3; j++){
                clickPanel(i, j);
                assertPanelEmpty(i, j);
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 3; j < 5; j++){
                clickPanel(i, j);
                assertPanelEmpty(i, j);
            }
        }
    }

    @Test
    void testBottomLeftCornerBorderChanger(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(0, 4);
        assertPanelNotEmpty(0, 4);

        for(int i = 3; i < 5; i++){
            for(int j = 2; j < 5; j++){
                clickPanel(i, j);
                assertPanelEmpty(i, j);
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 2; j++){
                clickPanel(i, j);
                assertPanelEmpty(i, j);
            }
        }
    }

    @Test
    void testBottomRightCornerBorderChanger(){
        clickPanel(2, 2);
        assertPanelNotEmpty(2, 2);

        clickPanel(4, 4);
        assertPanelNotEmpty(4, 4);

        for(int i = 0; i < 2; i++){
            for(int j = 2; j < 5; j++){
                clickPanel(i, j);
                assertPanelEmpty(i, j);
            }
        }
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 2; j++){
                clickPanel(i, j);
                assertPanelEmpty(i, j);
            }
        }
    }




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
