import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class myFrame extends JFrame{
    GameLogic game;
    public JPanel[][] panels = new JPanel[5][5];
    myFrame(){
        JFrame frame = new JFrame();
        frame.setSize(550, 600);
        frame.setLayout(null);//new GridLayout(5, 5)
        frame.setTitle("Revitalized Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        //frame.pack();

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                //need to instantiate a new panel each time we run the loop
                panels[i][j] = new JPanel();  // Initialize panel before using it
                panels[i][j].setBackground(Color.gray);
                panels[i][j].setBounds(100 * i, 100 * j, 100, 100);
                panels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                frame.add(panels[i][j]);  // Add panel to frame
            }
        }
        game = new GameLogic(panels, frame);
        //this line always needs to be last or else it won't load everything in the correct order
        frame.setVisible(true);
    }
}
