import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameLogic implements WinChecker{
    public boolean firstPlayersTurn = true;

    //these locations are relative to the 5x5 array
    private int boardWidthLoc1 = 0; //describes where leftmost of board is
    private int boardHeightLoc1 = 0; //describes where top of board is
    private int boardWidthLoc2 = 4; //describes where rightmost part of board is
    private int boardHeightLoc2 = 4; //describes where bottom of board is

    //these are the current height and width of the board
    private int innerBoxWidth = 0;
    private int innerBoxHeight = 0;

    private final int roundNum = 0;

    GameLogic(JPanel[][] board, JFrame frame){
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                int fontSize = getFontSize(frame);
                final int row = i;
                final int col = j;
                board[row][col].addMouseListener(new MouseAdapter() {
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
                        //board[row][col].removeAll();
                        //board[row][col].getComponent(board[row][col])
                        if(board[row][col].getComponentCount() == 0 && checkDimensions(boardWidthLoc1, boardWidthLoc2, boardHeightLoc1, boardHeightLoc2, row, col)) {
                            placeMove(board, fontSize, row, col);
                        }

                        //checkWinX(row);
                        //checkWin(board, innerBoxHeight, innerBoxWidth, row, col);
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

    void placeMove(JPanel[][] board, int fontSize, int row, int col){
        JPanel tempPanel = board[row][col];
        boolean isVertical; //describes if the move played is right/left or up/down which
                                    // is used when shading unplayable squares

        //these 2 if statements make sure that the playable board is updated
        if(row != 2 && innerBoxHeight != 3) {
            isVertical = true;
            updateDimension(board, row, heightAxis, isVertical);
            updateHeight();
        }
        if(col != 2 && innerBoxWidth != 3) {
            isVertical = false;
            updateDimension(board, col, widthAxis, isVertical);
            updateWidth();
        }

        String playerTurn;
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
        //tempPanel.setBackground(Color.black);
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
    void checkWin(JPanel[][] board, int innerBoxHeight, int innerBoxWidth, int row, int col){
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

    void checkRight(JPanel[][] board, int row, int col){
        if(board[row][col].getComponentCount() != 0){
            JLabel boxText = (JLabel) board[row][col].getComponent(0);
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
    private void updateDimension(JPanel[][] board, int coord, BoardAxis axis, boolean isVertical) {

        //this makes sure if our inner box width or height is 3, that we can't change dimensions of the box anymore
        int subtractedValue = axis.edge2 - axis.edge1;


        if (coord == 4 || coord == 0 || subtractedValue == 2) {
            axis.innerSize = 3;
            if (coord == 4) {
                axis.edge1 = 2;

                if(!isVertical) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 2; j++) {
                            board[i][j].setBackground(Color.red);
                        }
                    }
                } else if (isVertical) {
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 5; j++) {
                            board[i][j].setBackground(Color.red);
                        }
                    }
                }
            } else if (coord == 0) {
                axis.edge2 = 2;

                if(isVertical) {
                    for (int i = 3; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            board[i][j].setBackground(Color.red);
                        }
                    }
                } else if(!isVertical) {
                    for (int i = 0; i < 5; i++) {
                        for (int j = 3; j < 5; j++) {
                            board[i][j].setBackground(Color.red);
                        }
                    }
                }
            }
        }

        //makes it so when you place a move not on the edges that it will keep track
        //of when to set edge2 to 3 and edge1 to 1.
        if (coord == 1 && axis.edge2 == 4) {
            axis.edge2 -= 1;

            if(!isVertical) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 4; j < 5; j++) {
                        board[i][j].setBackground(Color.red);
                    }
                }
            } else if (isVertical) {
                for (int i = 4; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        board[i][j].setBackground(Color.red);
                    }
                }
            }

        } else if (coord == 3 && axis.edge1 == 0) {
            axis.edge1 += 1;

            if(isVertical) {
                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 5; j++) {
                        board[i][j].setBackground(Color.red);
                    }
                }
            } else if (!isVertical) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 1; j++) {
                        board[i][j].setBackground(Color.red);
                    }
                }
            }
        }
    }


    private final BoardAxis widthAxis = new BoardAxis(0, 4, 0);
    private final BoardAxis heightAxis = new BoardAxis(0, 4, 0);

    private void updateWidth() {
        innerBoxWidth = widthAxis.innerSize;
        boardWidthLoc1 = widthAxis.edge1;
        boardWidthLoc2 = widthAxis.edge2;
    }

    private void updateHeight() {
        innerBoxHeight = heightAxis.innerSize;
        boardHeightLoc1 = heightAxis.edge1;
        boardHeightLoc2 = heightAxis.edge2;
    }



    //returns true if the dimensions of our move is valid ONLY by checking the (possibly shifting) dimensions
    //Essentially, this makes sure our move is between the board widths and heights
    private static boolean checkDimensions(
            int boardWidthLoc1, int boardWidthLoc2,
            int boardHeightLoc1, int boardHeightLoc2,
            int row, int col
    ) {
        return row >= boardHeightLoc1 && row <= boardHeightLoc2 && col >= boardWidthLoc1  && col <= boardWidthLoc2;

    }



    //implemented as interface for future ease of testing
    @Override
    public void checkWinX(int row) {
        //round 5 is the first round a win is possible
        //System.out.println(innerBoxWidth);
        //System.out.println(roundNum);
        if(roundNum >= 5){
            //System.out.println(row);
            if(innerBoxWidth == 3){
                //System.out.println("check for win horizontally");
            }

        }
    }

    @Override
    public void checkWinY(int col){
        //System.out.println(innerBoxHeight);
        if(roundNum >= 5){
            if(innerBoxHeight == 3){
                //System.out.println("check for win vertically");
            }
        }
    }
























}