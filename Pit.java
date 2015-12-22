import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * A frame that asks the user how many stones to put in each pit in the beginning of the game
 * @author Kevin, Bryan, Nam
 *
 */
public class Pit
{
    private JFrame frame;
    private JButton stoneNumber3;
    private JButton stoneNumber4;
    private JLabel stoneLabel;
    private Container pane;
    private JPanel pnlStones;
    private int selection;

    /**
     * Creates a pit object that displays how many stones to put in each pit in the beginning of the game
     * @param x the selection for view1 or view2. x equals 0 means the user selected view1. x equals 1 means the user selected view2.
     */
    public Pit(int x)
    {
        frame = new JFrame();
        pane = frame.getContentPane();
        stoneNumber3 = new JButton("3");
        stoneNumber4 = new JButton("4");
        stoneLabel = new JLabel("Select Number of Stones: ");
        pnlStones = new JPanel(null);
        frame.setSize(300, 200);
        selection = x;

        pane.add(pnlStones);
        pnlStones.add(stoneNumber3);
        pnlStones.add(stoneLabel);
        pnlStones.add(stoneNumber4);

        stoneLabel.setBounds(20, 20, 250, 50);
        stoneNumber3.setBounds(30, 80, 50, 50);
        stoneNumber4.setBounds(100, 80, 50, 50);

        frame.setVisible(true);
        frame.setResizable(false);

        stoneNumber3.addActionListener(new Select_Action_3());
        stoneNumber4.addActionListener(new Select_Action_4());

    }

    /**
     * @author Kevin, Bryan, Nam
     *Action listener for selecting 3 stones. The program will then run with the configured layout and stones set.
     */
    public class Select_Action_3 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(selection == 0) {
                Model model = new Model();
                model.initiatePits(3);
                Controller c = new Controller(model, new View());
                frame.dispose();
            }
            else
            {
                Model model = new Model();
                model.initiatePits(3);
                Controller c = new Controller(model, new View2());
                frame.dispose();
            }

        }
    }

    /**
     * @author Kevin, Bryan, Nam
     *Action listener for selecting 4 stones. The program will then run with the configured layout and stones set.
     */
    public class Select_Action_4 implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(selection == 0) {
                Model model = new Model();
                model.initiatePits(4);
                Controller c = new Controller(model, new View());
                frame.dispose();
            }
            else
            {
                Model model = new Model();
                model.initiatePits(4);
                Controller c = new Controller(model, new View2());
                frame.dispose();
            }
        }
    }

}
