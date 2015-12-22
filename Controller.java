
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
*	Manipulates the data structure within the Model when user	
*	interaction is received
*@author Kevin, Bryan, Nam
*/
public class Controller
{
    private int counter;
    private Model model;
    private BoardStrategy view;

	/**
	*	Creates a Controller to interact with the data Model and View	
	*	@param model The model to manipulate
	*	@param view The view to display the data on
	*/
    public Controller(final Model model, final AbstractStrategy view)
    {
        this.model = model;
        this.view = view;
        counter = 0;
        view.setRowAPits(model.getStonesInPit(0));
        view.setRowBPits(model.getStonesInPit(0));

        this.view.addMancalaActionListener(new ActionListener() {
			
            public void actionPerformed(ActionEvent e) {
                String mancalaName = ((JButton) e.getSource()).getName();
                if (mancalaName.equals("playerA")) {
                    view.getMancala().get(0).setText(model.getStonesInP2Mancala() + ""); //change asterisk into the number
                } else {
                    view.getMancala().get(1).setText(model.getStonesInP1Mancala() + "");
                }
            }

        });

        this.view.addPitActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pitName = ((JButton) e.getSource()).getName(); //pitName A5, B1, etc

                //need to create an if statement to check if game is over
                if (model.checkGameOver()) {
                    if (model.getStonesInP1Mancala() > model.getStonesInP2Mancala())
                        JOptionPane.showMessageDialog(view.getFrame(), "Player A Wins.");
                    else if (model.getStonesInP2Mancala() > model.getStonesInP1Mancala())
                        JOptionPane.showMessageDialog(view.getFrame(), "Player B Wins.");
                    else
                        JOptionPane.showMessageDialog(view.getFrame(), "Draw Game.");
                } else if (!model.checkIfValid(pitName)) {
                    JOptionPane.showMessageDialog(view.getFrame(), "Invalid Move.");
                } else {
                    model.pickPitNumber(pitName);
					model.checkWinner();
						
                    for (int i = 0; i < view.getAllButtons().size(); i++) {
                        view.getAllButtons().get(i).setText(setStones(model.getStonesInPit(i)));
                    }

                    view.getMancala().get(0).setText(setStones(model.getStonesInP2Mancala())); //updating mancala1
                    view.getMancala().get(1).setText(setStones(model.getStonesInP1Mancala()));
                    if (model.getCounter() % 2 == 0) {
                        view.player2Turn(false);
                        view.player1Turn(true);
                    } else {
                        view.player1Turn(false);
                        view.player2Turn(true);
                    }
                    if (model.checkGameOver()) {
                        if (model.getStonesInP1Mancala() > model.getStonesInP2Mancala())
                            JOptionPane.showMessageDialog(view.getFrame(), "Player 1 Wins.");
                        else if (model.getStonesInP2Mancala() > model.getStonesInP1Mancala())
                            JOptionPane.showMessageDialog(view.getFrame(), "Player 2 Wins.");
                        else
                            JOptionPane.showMessageDialog(view.getFrame(), "Draw Game.");
                    }
                }
            }
        });
        this.view.addUndoActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
				if(model.getCounter() % 2 == 1 && model.getP1UndoCount() >= 3)
				{
					JOptionPane.showMessageDialog(view.getFrame(), "Maximum Undo limit reached.");
					return;
				}
				else if(model.getCounter() % 2 == 0 && model.getP2UndoCount() >= 3)
				{
					JOptionPane.showMessageDialog(view.getFrame(), "Maximum Undo limit reached.");
					return;
				}
		
                model.restoreState();
                for (int i = 0; i < view.getAllButtons().size(); i++) {
                    view.getAllButtons().get(i).setText(setStones(model.getStonesInPit(i)));
                }
				
                view.getMancala().get(0).setText(setStones(model.getStonesInP2Mancala())); //updating mancala1
                view.getMancala().get(1).setText(setStones(model.getStonesInP1Mancala()));
                if (model.getCounter() % 2 == 0) {
                    view.player2Turn(false);
                    view.player1Turn(true);
                } else {
                    view.player1Turn(false);
                    view.player2Turn(true);
                }
                if (model.checkGameOver()) {
                    if (model.getStonesInP1Mancala() > model.getStonesInP2Mancala())
                        JOptionPane.showMessageDialog(view.getFrame(), "Player 1 Wins.");
                    else if (model.getStonesInP2Mancala() > model.getStonesInP1Mancala())
                        JOptionPane.showMessageDialog(view.getFrame(), "Player 2 Wins.");
                    else
                        JOptionPane.showMessageDialog(view.getFrame(), "Draw Game.");
                }
				
				if(model.getCounter() % 2 == 0)
					model.addP1Undo();
				else
					model.addP2Undo();
            }

        });

        view.addQuitActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

	/**
	*	Creates a String containing asterisks to represent
	*	the number of stones
	*	@param number The number of stones
	*	@return A string representing the stones
	*/
    public String setStones(int number)
	{
        String stones = "";
        for(int i =0; i < number; i++){
            stones += "*";
        }
        return stones;
    }
}