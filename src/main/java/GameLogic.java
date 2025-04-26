import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameLogic{
    public boolean firstPlayersTurn = true;
//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
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
                        JPanel tempPanel = board[XCoord][YCoord];
                        if(board[XCoord][YCoord].getComponentCount() == 0 && checkDimensions(boardWidthLoc1, boardWidthLoc2, boardHeightLoc1, boardHeightLoc2, innerBoxWidth, innerBoxHeight, XCoord, YCoord)) {
                            placeMove(board[XCoord][YCoord], fontSize, XCoord, YCoord, board);
                        }
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

    void placeMove(JPanel tempPanel, int fontSize, int XCoord, int YCoord, JPanel[][] board){
        //changeDimensions(panels,  boardWidth, boardHeight);

        //these 2 if statements make sure that the playable board won't
        if(innerBoxWidth != 3) {
            updateWidth(XCoord, board);
        }
        if(innerBoxHeight !=3) {
            updateHeight(YCoord, board);
        }

        if(firstPlayersTurn){
            placeX(tempPanel, fontSize);
        } else {
            placeO(tempPanel, fontSize);
        }

        switchTurn();
    }

    void placeX(JPanel tempPanel, int fontSize){
        JLabel label = new JLabel("X", SwingConstants.CENTER); //sets the text and centers horizontally
        label.setFont(new Font("Arial", Font.BOLD, fontSize)); //sets the correct font size based on window width
        label.setForeground(Color.white);
        tempPanel.setBackground(Color.black);
        tempPanel.setLayout(new GridBagLayout()); //centers content vertically
        tempPanel.add(label);
        tempPanel.revalidate(); //refreshes the panel's layout
        tempPanel.repaint(); //redraws the panel to show updates
    }
    void placeO(JPanel tempPanel, int fontSize){
        JLabel label = new JLabel("O", SwingConstants.CENTER); //sets the text and centers horizontally
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

    void checkWin(JPanel[][] board){

    }
    //can optimize by first checking what the new boundaries are, then checking only within those boundaries
    void checkWidth(JPanel[][] board){

    }
    void checkHeight(JPanel[][] board){

    }

    private void updateWidth(int XCoord, JPanel[][] board) {
        //this line says if the inner box's width is 3,
        // then we should not be able to change the Width anymore for anything
        int subtractedWidthValue = boardWidthLoc2 - boardWidthLoc1;
        System.out.println("subtractedWidthValue " + subtractedWidthValue);
        if(XCoord == 4 || XCoord == 0 || subtractedWidthValue == 2){
            innerBoxWidth = 3;
            if(XCoord == 4){
                boardWidthLoc1 = 2;
            }
            if(XCoord == 0){
                boardWidthLoc2 = 2;
            }
            System.out.println("the Width is immutable!");
        }

        //makes it so when you place a move not on the edges that it will keep track
        //of when to set subtractedWidthValue to 3. Also, this if statement can only be called once
        //because the boardWidthLoc will never be equal to 4 or 0 after it's run once
        if(XCoord == 1 && boardWidthLoc2 == 4) {
            boardWidthLoc2 = boardWidthLoc2 - 1;
        } else if (XCoord == 3 && boardWidthLoc1 == 0) {
            boardWidthLoc1 = boardWidthLoc1 + 1;
            System.out.println(boardWidthLoc1);
        }
    }


    private void updateHeight(int YCoord, JPanel[][] board){
        //this block says if the inner box's width is 3,
        // then we should not be able to change the Height anymore for anything
        int subtractedHeightValue = boardHeightLoc2 - boardHeightLoc1;
        System.out.println("subtractedHeightValue " + subtractedHeightValue);
        if(YCoord == 4 || YCoord == 0 || subtractedHeightValue == 2){
            innerBoxHeight = 3;
            if(YCoord == 4){
                boardHeightLoc1 = 2;
            }
            if(YCoord == 0){
                boardHeightLoc2 = 2;
            }
            System.out.println("the Height is immutable!");
        }

        //makes it so when you place a move not on the edges that it will keep track
        //of when to set subtractedHeightValue to 3. Also, this if statement can only be called once
        //because the boardHeightLoc will never be equal to 4 or 0 after it's run once
        if(YCoord == 1 && boardHeightLoc2 == 4) {
            boardHeightLoc2 = boardHeightLoc2 - 1;
        } else if (YCoord == 3 && boardHeightLoc1 == 0) {
            boardHeightLoc1 = boardHeightLoc1 + 1;
            System.out.println(boardHeightLoc1);
        }
    }



    //returns true if the dimensions of our move is valid ONLY by checking dimensions
    private static boolean checkDimensions(int boardWidthLoc1, int boardWidthLoc2, int boardHeightLoc1, int boardHeightLoc2, int innerBoxWidth, int innerBoxHeight, int XCoord, int YCoord){
        //this makes sure our move is between the board widths and heights

        //System.out.println("XCoord: " + XCoord + " " + "YCoord: " + YCoord + " " + "boardWidthLoc1: " + boardWidthLoc1 + " " + "boardWidthLoc2: " + boardWidthLoc2 + " " + "boardHeightLoc1: " + boardHeightLoc1 + " " + "boardHeightLoc1: " + boardHeightLoc2);
        return XCoord >= boardWidthLoc1 && XCoord <= boardWidthLoc2 && YCoord >= boardHeightLoc1 && YCoord <= boardHeightLoc2;
    }

}