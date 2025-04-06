import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class myFrame extends JFrame{
    GameLogic game;
    public JPanel[][] board = new JPanel[5][5];
    myFrame(){

        this.setSize(550, 600);
        this.setLayout(null);//new GridLayout(5, 5)
        this.setTitle("Revitalized Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        //frame.pack();

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                //need to instantiate a new panel each time we run the loop
                board[i][j] = new JPanel();  // Initialize panel before using it
                board[i][j].setName("panel_" + i + "_" + j); //set unique name so we can use during testing
                board[i][j].setOpaque(true);
                board[i][j].setBackground(Color.gray);
                board[i][j].setBounds(100 * i, 100 * j, 100, 100);
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                this.add(board[i][j]);  // Add panel to frame
            }
        }
        game = new GameLogic(board, this);

        //this line always needs to be last or else it won't load everything in the correct order
        this.setVisible(true);
    }
    public GameLogic getGameLogic() {
        return game; // Returns the game logic instance
    }

}
