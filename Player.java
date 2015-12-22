/**
*	A player in a game of Mancala. Each player knows how many stones are in their
*	Mancala at any given time and how many times they have used the Undo button
*@author Kevin, Bryan, Nam
*/
public class Player
{
    private int stonesInMancala;
	private int undoCounter;

	/**
	*	Create a new Player
	*	Initial mancala has 0 stones
	*	Total of used undo is 0
	*/
    public Player()
    {
        setStones(0);
		undoCounter = 0;
    }
	
	/**
	*	Set the number of stones in this player's mancala
	*	@param n The number of stones
	*/
    public void setStones(int n)
    {
        stonesInMancala = n;
    }

	/**
	*	Increment the number of stones in this player's mancala by 1	
	*/
    public void addStoneToMancala()
    {
        stonesInMancala++;
    }

	/**
	*	Get the number of stones in this player's mancala	
	*	@return The number of stones
	*/
    public int getStonesInMancala()
    {
        return stonesInMancala;
    }
	
	/**
	*	Get how many times this player has used the Undo button
	*	@return The number of Undos
	*/
	public int getUndoCounter()
	{
		return undoCounter;
	}
	
	/**
	*	Increment the number of times this player has used the Undo 
	*	button by 1	
	*/
	public void addUndo()
	{
		undoCounter++;
	}
}
