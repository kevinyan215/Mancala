import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *First layout of the mancala board
 *@author Kevin, Bryan, Nam
 */
public class View extends AbstractStrategy
{
    private JLabel mancalaPlayerA;
    private JLabel mancalaPlayerB;

    public void CreateMancala()
    {
        //creates mancala B and then mancala A
    	String player = "A";
        int x = 80;
        for (int i = 0; i < 2; i++) {
            getMancala().add(new JButton());
            getMancala().get(i).setBounds(x, 100, 60, 300);
            x += 780;
            getMancala().get(i).setName("player" + player);
            pnlMancala.add(getMancala().get(i));
            player = "B";
        }
    }

    public void CreateLabels()
    {
        //creates the label for the mancalas
        mancalaPlayerA = new JLabel("Player A");
        mancalaPlayerB = new JLabel("Player B");
        mancalaPlayerA.setForeground(Color.red);
        mancalaPlayerB.setForeground(Color.blue);
        getPlayerLabel().add(mancalaPlayerB);
        getPlayerLabel().add(mancalaPlayerA);
        int x = 80;
        for (int i = 0; i < getPlayerLabel().size(); i++)
        {
            getPlayerLabel().get(i).setBounds(x, 40, 100, 100);
            x += 780;
            pnlMancala.add(getPlayerLabel().get(i));
        }
    }
}