import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameLogic{
    public boolean firstPlayersTurn = true;
    private int boardWidth, boardHeight; //these are the current height and width of the board

    //these locations are relative to the 5x5 array
    private int boardWidthLoc1; //describes where leftmost of board is
    private int boardHeightLoc1; //describes where top of board is
    private int boardWidthLoc2; //describes where rightmost part of board is
    private int boardHeightLoc2; //describes where bottom of board is


    GameLogic(JPanel[][] panels, JFrame frame){
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                int fontSize = getFontSize(frame);
                int XCoord = i;
                int YCoord = j;
                panels[i][j].addMouseListener(new MouseAdapter() {
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
                        //panels[XCoord][YCoord].removeAll();
                        //panels[XCoord][YCoord].getComponent(panels[XCoord][YCoord])

                        if(panels[XCoord][YCoord].getComponentCount() == 0 && checkDimensions(boardWidthLoc1, boardWidthLoc2, boardHeightLoc1, boardHeightLoc2, XCoord, YCoord)){
                            placeMove(panels[XCoord][YCoord], fontSize, XCoord, YCoord);
                            System.out.println(boardWidthLoc1 + " " + boardWidthLoc2 + " " + boardHeightLoc1 + " " + boardHeightLoc2);
                        }

                    }
                });


            }
        }
    }

    void switchTurn(){
        firstPlayersTurn = !firstPlayersTurn;
    }

    void hoverMove(){
        System.out.println("was hovered!");
    }

    void unHoverMove(){
        System.out.println("was unhovered!");
    }

    void placeMove(JPanel panels, int fontSize, int XCoord, int YCoord){
        changeDimensions(panels,  boardWidth, boardHeight);

        if(firstPlayersTurn){
            placeX(panels, fontSize);
            updateWidth();
            updateHeight();
        } else {
            placeO(panels, fontSize);
            updateWidth();
            updateHeight();
        }
        switchTurn();
    }

    void placeX(JPanel panels, int fontSize){
        JLabel label = new JLabel("X", SwingConstants.CENTER); //sets the text and centers horizontally
        label.setFont(new Font("Arial", Font.BOLD, fontSize)); //sets the correct font size based on window width
        label.setForeground(Color.white);
        panels.setBackground(Color.black);
        panels.setLayout(new GridBagLayout()); //centers content vertically
        panels.add(label);
        panels.revalidate(); //refreshes the panel's layout
        panels.repaint(); //redraws the panel to show updates
    }
    void placeO(JPanel panels, int fontSize){
        JLabel label = new JLabel("0", SwingConstants.CENTER); //sets the text and centers horizontally
        label.setFont(new Font("Arial", Font.BOLD, fontSize)); //sets the correct font size based on window width
        label.setForeground(Color.white);
        panels.setBackground(Color.black);
        panels.setLayout(new GridBagLayout()); //centers content vertically
        panels.add(label);
        panels.revalidate(); //refreshes the panel's layout
        panels.repaint(); //redraws the panel to show updates
    }

    public int getFontSize(JFrame frame){
        //sets the font size of our placed letter relative to the window width
        return frame.getWidth()/5 - 10;
    }

    void checkWin(JPanel[][] panels){

    }
    //can optimize by first checking what the new boundaries are, then checking only within those boundaries
    void checkWidth(JPanel[][] panels){

    }
    void checkHeight(JPanel[][] panels){

    }

    private int updateWidth(int boardWidth){
        boardWidth = this.boardWidth;
        return boardWidth;
    }

    private int updateHeight(int boardHeight){
        boardHeight = this.boardHeight;
        return boardHeight;
    }

    void changeDimensions(JPanel panels, int boardWidth, int boardHeight){
        updateWidth(boardWidth);
        updateHeight(boardHeight);
    }

    //returns true if the dimensions of our move is valid ONLY by checking dimensions
    private boolean checkDimensions(int borderWidth1, int borderWidth2, int borderHeight1, int borderHeight2, int XCoord, int YCoord){
        return XCoord >= borderWidth1 && XCoord <= borderWidth2 && YCoord >= borderHeight1 && YCoord <= borderHeight2;
    }



}
