import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;


public class GameLogic{
    public boolean firstPlayersTurn = true;

    //these locations are relative to the 5x5 array
    private int boardWidthLoc1 = 0; //describes where leftmost of board is
    private int boardHeightLoc1 = 0; //describes where top of board is
    private int boardWidthLoc2 = 4; //describes where rightmost part of board is
    private int boardHeightLoc2 = 4; //describes where bottom of board is

    //these are the current height and width of the board
    private int innerBoxWidth = 0;
    private int innerBoxHeight = 0;

    GameLogic(JPanel[][] board, JFrame frame){
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                int fontSize = getFontSize(frame);
                final int XCoord = i;
                final int YCoord = j;
                board[i][j].addMouseListener(new MouseAdapter() {
                    //mouseListener added for hover event:
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hoverMove();
                    }
                    //mouseListener added for hover event:
                    @Override
                    public void mouseExited(MouseEvent e) {
                        unHoverMove();
                    }

                    //mouseListener added for click event:
                    @Override
                    public void mousePressed(MouseEvent e) {
                        //board[XCoord][YCoord].removeAll();
                        //board[XCoord][YCoord].getComponent(board[XCoord][YCoord])
                        if(board[XCoord][YCoord].getComponentCount() == 0 && checkDimensions(boardWidthLoc1, boardWidthLoc2, boardHeightLoc1, boardHeightLoc2, XCoord, YCoord)) {
                            placeMove(board[XCoord][YCoord], fontSize, XCoord, YCoord);
                        }


                        //checkWin(board, innerBoxHeight, innerBoxWidth, XCoord, YCoord);
                    }
                });
            }
        }
    }

    void switchTurn(){
        firstPlayersTurn = !firstPlayersTurn;
    }
    public boolean isFirstPlayersTurn() {
        return firstPlayersTurn;
    }



    void hoverMove(){
        //System.out.println("was hovered!");
    }

    void unHoverMove(){
        //System.out.println("was unhovered!");
    }

    void placeMove(JPanel tempPanel, int fontSize, int XCoord, int YCoord){
        //these 2 if statements make sure that the playable board is updated
        if(innerBoxWidth != 3) {
            updateWidth(XCoord);
        }
        if(innerBoxHeight !=3) {
            updateHeight(YCoord);
        }

        String playerTurn = "";
        if(firstPlayersTurn){
            playerTurn = "X";
            placeMove(tempPanel, fontSize, playerTurn);
        } else {
            playerTurn = "O";
            placeMove(tempPanel, fontSize, playerTurn);
        }

        switchTurn();
    }


    void placeMove(JPanel tempPanel, int fontSize, String playerTurn){
        JLabel label = new JLabel(playerTurn, SwingConstants.CENTER); //sets the text and centers horizontally
        label.setFont(new Font("Arial", Font.BOLD, fontSize)); //sets the correct font size based on window width
        label.setForeground(Color.white);
        tempPanel.setBackground(Color.black);
        tempPanel.setLayout(new GridBagLayout()); //centers content vertically
        tempPanel.add(label);
        tempPanel.revalidate(); //refreshes the panel's layout
        tempPanel.repaint(); //redraws the panel to show updates
    }


    public int getFontSize(JFrame frame){
        //sets the font size of our placed letter relative to the window width
        return frame.getWidth()/5 - 10;
    }
/*
    void checkWin(JPanel[][] board, int innerBoxHeight, int innerBoxWidth, int XCoord, int YCoord){
        //because you can't win if the inner box isn't at least 3 in some dimension
        //this also clips some calculations out
        if(boardWidthLoc1 == 2) {
            //add code here to check the right side of the board for a win
        }
        JLabel[] boxText = new JLabel[3];
        for(int i = 0; i < 3; i++){
            boxText[i] = (JLabel) board[0][i].getComponent(0);

        }
        if (Objects.equals(boxText[0].getText(), "X") && Objects.equals(boxText[1].getText(), "X") && Objects.equals(boxText[2].getText(), "X")) {
            System.out.println("WINNNNN!!!!!!!!!!!!");
        }
    }

    void checkRight(JPanel[][] board, int XCoord, int YCoord){
        if(board[XCoord][YCoord].getComponentCount() != 0){
            JLabel boxText = (JLabel) board[XCoord][YCoord].getComponent(0);
            if(Objects.equals(boxText.getText(), "X")){

            }else if(Objects.equals(boxText.getText(), "O")){

            }
        }
    }
    void checkLeft(){

    }
    void checkUp(){

    }
    void checkDown(){

    }
    void checkToBottomRight(){

    }
    void checkToBottomLeft(){

    }
*/


    //this block says if the inner box's width or height is 3,
    //then we should not be able to change either the widths/heights anymore
    //also makes sure if we play on the edge of the board, the inner box width is immutable
    private void updateDimension(int coord, BoardAxis axis) {

        //this makes sure if our inner box width or height is 3, that we can't change dimensions of the box anymore
        int subtractedValue = axis.edge2 - axis.edge1;

        if (coord == 4 || coord == 0 || subtractedValue == 2) {
            axis.innerSize = 3;
            if (coord == 4) {
                axis.edge1 = 2;
            } else if (coord == 0) {
                axis.edge2 = 2;
            }
        }

        //makes it so when you place a move not on the edges that it will keep track
        //of when to set edge2 to 3 and edge1 to 1. Also, this if statement can only be called once
        //because edge1 and edge2 will never be equal to 4 or 0 after it's run once
        if (coord == 1 && axis.edge2 == 4) {
            axis.edge2 -= 1;
        } else if (coord == 3 && axis.edge1 == 0) {
            axis.edge1 += 1;
        }
    }


    private final BoardAxis widthAxis = new BoardAxis(0, 4, 0);
    private final BoardAxis heightAxis = new BoardAxis(0, 4, 0);

    private void updateWidth(int XCoord) {
        updateDimension(XCoord, widthAxis);
        innerBoxWidth = widthAxis.innerSize;
        boardWidthLoc1 = widthAxis.edge1;
        boardWidthLoc2 = widthAxis.edge2;
    }

    private void updateHeight(int YCoord) {
        updateDimension(YCoord, heightAxis);
        innerBoxHeight = heightAxis.innerSize;
        boardHeightLoc1 = heightAxis.edge1;
        boardHeightLoc2 = heightAxis.edge2;
    }



    //returns true if the dimensions of our move is valid ONLY by checking the (possibly shifting) dimensions
    //Essentially, this makes sure our move is between the board widths and heights
    private static boolean checkDimensions(
        int boardWidthLoc1, int boardWidthLoc2,
        int boardHeightLoc1, int boardHeightLoc2,
        int XCoord, int YCoord
    ) {
        return XCoord >= boardWidthLoc1 && XCoord <= boardWidthLoc2 && YCoord >= boardHeightLoc1 && YCoord <= boardHeightLoc2;
    }

}