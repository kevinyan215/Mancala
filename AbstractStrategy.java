

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * 
 *Abstract layout of the BoardStrategy. Contains methods that are the same in both view1 and view2 as well as methods from BoardStrategy.
 * @author Kevin, Bryan, Nam
 */
public abstract class AbstractStrategy implements BoardStrategy
{
    private JFrame frame;
    final Container pane;
    final JPanel pnlMancala;

    private ArrayList<JButton> AJButtons = new ArrayList<JButton>();
    private ArrayList<JButton> BJButtons = new ArrayList<JButton>();
    private ArrayList<JButton> mancala = new ArrayList<JButton>();
    private ArrayList<JLabel> playerLabel = new ArrayList<JLabel>();
    private ArrayList<JLabel> pitLabel = new ArrayList<JLabel>();
    private ArrayList<JButton> allButtons = new ArrayList<JButton>();
    private JButton undoButton;
    private JButton quitButton;
    private JLabel Player1Turn, Player2Turn;


    /**
     *Creates an instance of the board
     */
    public AbstractStrategy()
    {
        //prepare frame
        frame = new JFrame("Mancala Board");
        frame.setSize(1200, 600);
        pane = frame.getContentPane();
        pane.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pnlMancala = new JPanel(null);
        CreateMancala();
        CreateLabels();
        CreateRowAPits();
        CreateRowBPits();
        insertButtons();
        CreateLabelsForPlayerA();
        undoButton = new JButton("UNDO");
        quitButton = new JButton("QUIT");
        undoButton.setBounds(10, 500, 75, 75);
        quitButton.setBounds(1100, 500, 75, 75);
        pnlMancala.add(undoButton);
        pnlMancala.add(quitButton);
        Player1Turn = new JLabel("Player A -->");
        Player2Turn = new JLabel("<-- Player B ");
        addPlayerTurns();

    }

    /**
     * Adds the player turn label onto the frame
     */
    public void addPlayerTurns()
    {

        Player1Turn.setForeground(Color.red);
        Player2Turn.setForeground(Color.blue);
        Player1Turn.setBounds(450, 300, 100, 100);
        Player2Turn.setBounds(450, 100, 100, 100);
        pnlMancala.add(Player1Turn);
        pnlMancala.add(Player2Turn);
        Player2Turn.setVisible(false);
    }

    /**
     * Displays player1 turn onto the board
     * @param setter boolean to turn off/on the label
     */
    public void player1Turn(boolean setter){

        Player1Turn.setVisible(setter);
    }

    /**
     * Displays player2 turn onto the board
     * @param setter boolean to turn off/on the label
     */
    public void player2Turn(boolean setter){
        Player2Turn.setVisible(setter);
    }
    
    public void CreateRowAPits()
    {
        //creates row of A pits
        int x = 200;
        for (int i = 0; i < 6; i++) {
            AJButtons.add(new JButton());
            AJButtons.get(i).setBounds(x, 260, 100, 40);
            x += 100;
            pnlMancala.add(AJButtons.get(i));
            String buttonNumber = "A" + (i + 1);
            AJButtons.get(i).setName(buttonNumber);
        }
    }

    /**
     * fills in player A's pits with the set number of stones
     * @param numStones sets the number of stones per pit
     */
    public void setRowAPits (int numStones)
    {
        for (int i = 0; i < 6; i++)
        {
            int numOfStones = (numStones);
            String buttonNumber = setStones(numOfStones);
            AJButtons.get(i).setText(buttonNumber);
        }
    }
    
    /**
     * fills in player B's pits with the set number of stones
     * @param numStones numStones sets the number of stones per pit
     */
    public void setRowBPits (int numStones)
    {
        for (int i = 0; i < 6; i++)
        {
            int numOfStones = (numStones);
            String buttonNumber = setStones(numOfStones);
            BJButtons.get(i).setText(buttonNumber);
        }
    }

    public void CreateRowBPits() {
        //creates row of B pits
        int x = 700;
        for (int i = 0; i < 6; i++) {
            BJButtons.add(new JButton());
            BJButtons.get(i).setBounds(x, 220, 100, 40);
            x -= 100;
            String buttonNumber = "B" + (i + 1);
            BJButtons.get(i).setName(buttonNumber);
            //BJButtons.get(i).setText(buttonNumber);
            pnlMancala.add(BJButtons.get(i));
        }
    }

   

    public void insertButtons() {
        //inserts all the buttons into one arraylist
        for (int i = 0; i < AJButtons.size(); i++) {
            allButtons.add(AJButtons.get(i));
        }
        for (int i = 0; i < BJButtons.size(); i++) {
            allButtons.add(BJButtons.get(i));
        }
    }

    public void CreateLabelsForPlayerA()
    {
        // Creates labels for Player A's pits
        for (int i = 0; i < 6; i++) {
            final JLabel aLabel = new JLabel("A" + (i + 1));
            aLabel.setForeground(Color.red);
            pitLabel.add(aLabel);

            int xCoord = AJButtons.get(i).getX() + AJButtons.get(i).getWidth() / 2;
            int yCoord = AJButtons.get(i).getY() + AJButtons.get(i).getHeight();
            aLabel.setBounds(xCoord, yCoord, AJButtons.get(i).getWidth(), AJButtons.get(i).getHeight() / 2);
        }

        // Creates labels for Player B's pits
        for (int i = 0; i < 6; i++) {
            final JLabel bLabel = new JLabel("B" + (i + 1));
            pitLabel.add(bLabel);
            bLabel.setForeground(Color.blue);
            int xCoord = BJButtons.get(i).getX() + BJButtons.get(i).getWidth() / 2;
            int yCoord = BJButtons.get(i).getY() - BJButtons.get(i).getHeight() / 2;
            bLabel.setBounds(xCoord, yCoord, BJButtons.get(i).getWidth(), BJButtons.get(i).getHeight() / 2);
        }
        for (JLabel label : pitLabel) {
            pnlMancala.add(label);
        }


        pnlMancala.setBounds(0, 0, 1200, 600);
        pnlMancala.setBorder(BorderFactory.createTitledBorder("Mancala"));
        pane.add(pnlMancala);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public void addPitActionListener(ActionListener l)
    {
        for(JButton button: allButtons){
            button.addActionListener(l);
        }
    }

    public void addMancalaActionListener(ActionListener l){
        for(JButton button: mancala){
            button.addActionListener(l);
        }
    }

    /**
     * Adds an action listener to the quit button
     * @param l the action listener to be added
     */
    public void addQuitActionListener(ActionListener l)
    {
        quitButton.addActionListener(l);
    }

    public void addUndoActionListener(ActionListener l)
    {
        undoButton.addActionListener(l);
    }

    /**
     * returns an arraylist that holds both playerA and playerB's mancala
     * @return an arraylist that holds both playerA and playerB's mancala
     */
    public ArrayList<JButton> getMancala()
    {
        return mancala;
    }

    /**
     * returns an arraylist that holds both playerA and playerB's mancala label
     * @return an arraylist that holds both playerA and playerB's mancala label
     */
    public ArrayList<JLabel> getPlayerLabel()
    {
        return playerLabel;
    }

    /**
     * gets the arraylist that holds playerA and playerB's pits
     * @return the arraylist that holds playerA and playerB's pits
     */
    public ArrayList<JButton> getAllButtons(){
        return allButtons;
    }

    /**
     * Takes in an integer value and returns the number of asterisks for the value
     * @param number the number of asterisks to be returns
     * @return a number of asterisks
     */
    public String setStones(int number){
        String stones = "";
        for(int i =0; i < number; i++){
            stones += "*";
        }
        return stones;
    }

    /**
     * returns the frame of the layout
     * @return the frame of the layout
     */
    public JFrame getFrame(){
        return frame;
    }
}

