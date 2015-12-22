import java.awt.event.ActionListener;

/**
 * Created by bryannguyen on 4/24/15.
 * @author Kevin, Bryan, Nam
 */
public interface BoardStrategy
{
    /**
     * create player A and player B's mancala
     */
    void CreateMancala();
    
    /**
     * create player A and player B's mancala label
     */
    void CreateLabels();
    
    /**
     * create player A's pit
     */
    void CreateRowAPits();
    
    /**
     * create player B's pits
     */
    void CreateRowBPits();
    
    /**
     * Puts both player A and player B's pits into one arraylist
     */
    void insertButtons();
    
    /**
     * create the labels for the player A and player B's pits
     */
    void CreateLabelsForPlayerA();
    
    /**
     * Adds an action listener to each of the player's pits
     * @param l action listener to be added to the pit
     */
    void addPitActionListener(ActionListener l);
    
	/**
	 * Adds an action listener to each of the player's mancala
     * @param l action listener to be added to the mancala
	 */
	void addMancalaActionListener(ActionListener l);
	
	/**
	 * Adds an action listener to the undo button
	 * @param l the action listener to be added 
	 */
	void addUndoActionListener(ActionListener l);
	
}
