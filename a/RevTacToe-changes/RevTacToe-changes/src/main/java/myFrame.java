import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class myFrame extends JFrame{
    GameLogic game;
    public JPanel[][] board = new JPanel[5][5];
    myFrame(){

        this.setTitle("Revitalized Tic Tac Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        //adds the primary panel
        JPanel primaryPanel = new JPanel();
        primaryPanel.setLayout(new GridLayout(5, 5));
        primaryPanel.setPreferredSize(new Dimension(500, 500));
        
        //adds the 5x5 panels to the primary panel
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                //need to instantiate a new panel each time we run the loop
                board[i][j] = new JPanel();
                board[i][j].setName("panel_" + i + "_" + j);
                board[i][j].setOpaque(true);
                board[i][j].setBackground(Color.gray);
                board[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                primaryPanel.add(board[i][j]);
            }
        }

        //this line always needs to be last or else it won't load everything in the correct order
        this.add(primaryPanel, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //need to call GameLogic after setting the board and packing it so everything is the correct size
        game = new GameLogic(board, this);
    }
    public GameLogic getGameLogic() {
        return game; // Returns the game logic instance
    }

}
