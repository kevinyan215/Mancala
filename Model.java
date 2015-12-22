import java.util.Scanner;

/**
*	Data structure representing the Mancala board game
*@author Kevin, Bryan, Nam
*/
public class Model
{
    private int[] pits;
    private int[] copyPits;
    private int previousP1Mancala, previousP2Mancala;
    private boolean gameOver;
    private int counter;
    private int counter2;
    private Player player1;
    private Player player2;

	/**
	*	Creates the data structure and initializes the starting values
	*/
    public Model()
    {
    	previousP1Mancala = 0;
    	previousP2Mancala = 0;
        pits = new int[12];
        copyPits = new int[12];
        boolean gameOver = false;
        counter = 0;
        counter2= 0;
        player1 = new Player();
        player2 = new Player();
    }

	/**
	*	Fill up the data structure with the initial starting stones
	*	@param n the number of stones per pit
	*/
    public void initiatePits(int n)
    {
        for(int i = 0; i < pits.length; i++)
            pits[i] = n;
    }

	/**
	*	Gets the number of stones in this pit
	*	@param index the index, [0 - 11]
	*	@return the number of stones in this pit
	*/
    public int getStonesInPit(int index)
    {
        return pits[index];
    }

	/**
	*	Sets the number of stones to be in this pit
	*	@param index the index, [0 - 11]
	*	@param numOfStones the number to set
	*/
    public void setStonesInPit(int index, int numOfStones)
    {
        pits[index] = numOfStones;
    }

	/**
	*	Adds one stone to Player 1's mancala
	*/
    public void addToP1Mancala()
    {
        player1.addStoneToMancala();
    }

	/**
	*	Adds one stone to Player 2's mancala
	*/
    public void addToP2Mancala() 
	{
        player2.addStoneToMancala();
    }

	/**
	*	Gets the number of stones in Player 1's mancala
	*	@return number of stones
	*/
    public int getStonesInP1Mancala()
    {
        return player1.getStonesInMancala();
    }

	/**
	*	Get the number of stones in Player 2's mancala
	*	@return the number of stones
	*/
    public int getStonesInP2Mancala()
    {
        return player2.getStonesInMancala();
    }
    
	/**
	*	Saves the current state of the board
	*/
    public void saveState()
    {
     System.arraycopy(pits, 0, copyPits, 0, copyPits.length);
     previousP1Mancala = getStonesInP1Mancala();
     previousP2Mancala = getStonesInP2Mancala();
     counter2 = counter;
    }
    
	/**
	*	Restores the state of the board to the previous turn
	*/
    public void restoreState()
    {
     System.arraycopy(copyPits, 0, pits, 0, pits.length);
     player1.setStones(previousP1Mancala);
     player2.setStones(previousP2Mancala);
     counter = counter2;
    }
	
	/**
	*	Removes all stones from given pit and distributes the stones
	*	in sequential order. Adds to player's own mancala, but skips	
	*	the opponent's mancala.
	*	@param pit The picked pit, e.g. A1, A4, B3, B6, etc
	*/
    public void pickPitNumber(String pit)
    {
        saveState();

        int pitNumber = Integer.parseInt(pit.substring(1, 2)) - 1;
        if(pit.substring(0, 1).equals("B"))
            pitNumber = pitNumber + 6;
		
		// determines how many stones to distribute
        int stonesToPlace = pits[pitNumber]; 
        pits[pitNumber] = 0;

        for(int i = pitNumber + 1, j = 0; j < stonesToPlace; i++, j++)
        {
            if(i == 6 && counter % 2 == 0) // Update P1 Mancala
            {
                player1.addStoneToMancala();
				// if there are more stones to be added, add to next pit
                if(j < stonesToPlace - 1) 
                {
                    pits[i % 12]++;
                    j++; //skip next pit since stone was already added
                }
                else if(j == stonesToPlace - 1) // if there are no more stones to be added
                    counter++;	// Player 1 gets another turn for ending in mancala
            }
            else if(i == 12 && counter % 2 == 1) // Update P2 mancala
            {
                player2.addStoneToMancala();
				// if there are more stones to be added
                if(j < stonesToPlace - 1)
                {
                    pits[i % 12]++;
                    j++; //skip next pit since stone was already added
                }
                else if(j == stonesToPlace - 1)
                    counter++; // player 2 gets another turn for ending in mancala
            }
            else
                pits[i % 12]++;

            // Begin checking captures
			// if there are no more stones to place and last stone landed in p1's empty pit
            if(j == stonesToPlace - 1 && counter % 2 == 0 && pits[i % 12] == 1) 
            {
                if(i % 12 <= 5) // if index is on player 1's side
                {
                    player1.setStones(player1.getStonesInMancala() + pits[pits.length - (i % 12) - 1]);
                    pits[pits.length - (i % 12) - 1] = 0;
                }
            }
			// if there are no more stones to place and last stone landed in p2's empty pit
            else if(j == stonesToPlace - 1 && counter % 2 == 1 && pits[i % 12] == 1) 
            {
                if(i % 12 >= 6) // if index is on player 2's side
                {
                    player2.setStones(player2.getStonesInMancala() + pits[pits.length - (i % 12) - 1]);
                    pits[pits.length - (i % 12) - 1] = 0;
                }
            }
        }

        checkWinner();
        counter++; // next player's turn
    }

	/**
	*	Checks the board state to determine if a winner was found
	*/
    public void checkWinner()
    {
        int zerosFound = 0;
        if(counter % 2 == 0)
        {
            for(int i = 0; i < pits.length / 2; i++)
                if(pits[i] == 0)
                    zerosFound++;
        }
        else if(counter % 2 == 1)
        {
            for(int i = 6; i < pits.length; i++)
                if(pits[i] == 0)
                    zerosFound++;
        }

        if(zerosFound == 6)
            gameOver = true;
    }
	
	/**
	*	Get how many turns have been taken
	*	@return the number of turns
	*/
	public int getCounter()
	{
		return counter;
	}
    
	/**
	*	Check to see if a winner was determined
	*	@return true if a winner was found, false otherwise
	*/
    public boolean checkGameOver() 
	{
    	return gameOver;
    }  
	
	/**
	*	Check to see if this move is valid
	*	@param pit The pit, e.g. A1, A5, B2, B6
		@return true if move is valid, false otherwise
	*/
    public boolean checkIfValid(String pit)
	{
		boolean result = false;
		int index = 0;
		
    	if(counter % 2 == 0 && pit.substring(0, 1).equals("A"))
		{
        	result = true;
			index = Integer.parseInt(pit.substring(1, pit.length())) - 1;
		}
		else if(counter % 2 == 1 && pit.substring(0, 1).equals("B"))
		{
			result = true;
 			index = Integer.parseInt(pit.substring(1, pit.length())) - 1 + 6;
		}
		
		if(pits[index] == 0)
			result = false;
		
    	return result;
    }

	/**
	*	Get how many times player 1 has used the Undo button
	*	@return The number of times
	*/
	public int getP1UndoCount()
	{
		return player1.getUndoCounter();
	}
	
	/**
	*	Get how many times player 2 has used the Undo button
	*	@return The number of times
	*/
	public int getP2UndoCount()
	{
		return player2.getUndoCounter();
	}
	
	/**
	*	Increment the number of times Player 1 has used the Undo button
	*/
	public void addP1Undo()
	{
		player1.addUndo();
	}
	
	/**
	*	Increment the number of times Player 2 has used the Undo button
	*/
	public void addP2Undo()
	{
		player2.addUndo();
	}
	
	/**
	*	Notify the data structure that a Undo occurred
	*/
	public void undoOccured()
	{
		counter++;
	}
	
    private int pitIndex(String pit)
	{
        int pitNumber = Integer.parseInt(pit.substring(1, 2)) - 1;
        if(pit.substring(0, 1).equals("B"))
            pitNumber = pitNumber + 6;
		
        return pitNumber;
    }
}