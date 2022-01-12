import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Territory.java
 *
 * Created on 22 November 2007, 14:23
 *
 * A Territory stores all the logical parts associated
 * with a territory in game theory evolution; the Players
 * and the Games.
 *
 * @author Simon Archer (archers5)
 */
public class Territory
{
  // Store the size of the Territory.
  private int size;
  // Store the Players and the Games.
  private Player [][] players;
  private Game [] games;
  // Store a boolean for the neighbour system.
  private boolean fourNeighbours;
  
  /**
   * Creates a new instance of Territory, the neighbour system
   * defaults to 4 neighbours.
   *
   * @param sze Size of the square grid (e.g. 3 gives a 3x3 grid)
   */
  public Territory(int sze)
  {
    // Initialise the variables.
    size = sze;
    fourNeighbours = true;
    
    players = new Player[size][size];
    games = new Game[2 * (size * size)];
    
    Strategy [] strats = Game.getStrategies();
    
    int i, j;
    
    for(i = 0; i < size; i++)
      for(j = 0; j < size; j++)
        players[i][j] = new Player(new Probabilistic());
    
    // Create the games.
    int noGames = 0;
    // Rows
    for(i = 0; i < size; i++)
    {
      for(j = 0; j < size - 1; j++)
      {
        // Game to its right.
        games[noGames] = new Game(players[i][j], players[i][j+1]);
        noGames++;
      } // for
      // Game between last and first (rightmost Player plays to its right).
      games[noGames] = new Game(players[i][j], players[i][0]);
      noGames++;
    } // for
    
    // Columns
    for(j = 0; j < size; j++)
    {
      for(i = 0; i < size - 1; i++)
      {
        // Game below it.
        games[noGames] = new Game(players[i][j], players[i+1][j]);
        noGames++;
      } // for
      // Game between first an last (bottom player plays below it).
      games[noGames] = new Game(players[i][j], players[0][j]);
      noGames++;
    } // for
    
  } // Territory
  
  /**
   * Creates a new instance of Territory, the neighbour system
   * is set to .
   *
   * @param sze Size of the square grid (e.g. 3 gives a 3x3 grid)
   * @param fourNeigh true for 4 neighbours, false for 8
   */
  public Territory(int sze, boolean fourNeigh)
  {
    this(sze);
    fourNeighbours = fourNeigh;
    
    setNeighbours(fourNeighbours);
  } // Territory
  
  /**
   * Accessor for the Players.
   *
   * @param i The row of the Player
   * @param j The column of the Player
   * @return The chosen Player
   */
  public Player getPlayer(int i, int j)
  {
    return players[i][j];
  } // getPlayer
  
  /**
   * Accessor for the fourNeighbours variable.
   *
   * @return true for 4 neighbours, false for 8
   */
  public boolean getFourNeighbours()
  {
    return fourNeighbours;
  } // getFourNeighbours
  
  /**
   * Method to set the neighbour system to either 4 or 8.
   *
   * @param fourNeigh true for 4 neighbours, false for 8
   */
  public void setNeighbours(boolean fourNeigh)
  {
    fourNeighbours = fourNeigh;
    
    if(fourNeighbours)
      // Set the size of games to be 2(size)^2.
      games = Arrays.<Game>copyOf(games, 2 * (size * size));
    else
    {
      // No need to reset the Territory (ie the Players), just create
      // more Games.
      int noGames = 2 * (size * size);
      games = Arrays.<Game>copyOf(games, 2 * noGames);
      
      // Populate from noGames.
      int i = 0, j = 0;
      
      // Do top row.
      //  First one.
      games[noGames] = new Game(players[i][j], players[size-1][size-1]);
      noGames++;
      games[noGames] = new Game(players[i][j], players[size-1][j+1]);
      noGames++;
      j++;
      //  Middle ones.
      for( ; j < size - 1; j++)
      {
        games[noGames] = new Game(players[i][j], players[size-1][j-1]);
        noGames++;
        games[noGames] = new Game(players[i][j], players[size-1][j+1]);
        noGames++;
      } // for
      //  Last one.
      games[noGames] = new Game(players[i][j], players[size-1][j-1]);
      noGames++;
      games[noGames] = new Game(players[i][j], players[size-1][0]);
      noGames++;
      // Start the columns again.
      j = 0; i++;
      
      // Central rows.
      for( ; i < size - 1; i++)
      {
        // First one.
        games[noGames] = new Game(players[i][j], players[i-1][size-1]);
        noGames++;
        games[noGames] = new Game(players[i][j], players[i-1][j+1]);
        noGames++;
        j++;
        // Middle ones.
        for( ; j < size - 1; j++)
        {
          games[noGames] = new Game(players[i][j], players[i-1][j-1]);
          noGames++;
          games[noGames] = new Game(players[i][j], players[i-1][j+1]);
          noGames++;
        } // for
        // Last one.
        games[noGames] = new Game(players[i][j], players[i-1][j-1]);
        noGames++;
        games[noGames] = new Game(players[i][j], players[i-1][0]);
        noGames++;
        // Start the columns again.
        j = 0;
      } // for
      // Do bottom row.
      //  First one.
      games[noGames] = new Game(players[i][j], players[i-1][size-1]);
      noGames++;
      games[noGames] = new Game(players[i][j], players[i-1][j+1]);
      noGames++;
      j++;
      //  Middle ones.
      for( ; j < size - 1; j++)
      {
        games[noGames] = new Game(players[i][j], players[i-1][j-1]);
        noGames++;
        games[noGames] = new Game(players[i][j], players[i-1][j+1]);
        noGames++;
      } // for
      //  Last one.
      games[noGames] = new Game(players[i][j], players[i-1][j-1]);
      noGames++;
      games[noGames] = new Game(players[i][j], players[i-1][0]);
      noGames++;
    } // else
    
  } // setNeighbours
  
  /**
   * Play a single generation in the territory.
   */
  public void step()
  {
    // Reset the payoffs.
    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        players[i][j].resetPayoff();
    
    // Iterate through each game, and play it.
    for(int i = 0; i < games.length; i++)
      games[i].play();
    
    checkBestStrat();
  } // step
  
  /* Method to check which of each Player's strategies has
   * the best payoff, then swap them. */
  private void checkBestStrat()
  {
    // Store an ArrayList for the Players to swap and
    // the Strategy to swap.
    ArrayList<Integer> playersToSwapRow = new ArrayList<Integer>();
    ArrayList<Integer> playersToSwapColumn = new ArrayList<Integer>();
    ArrayList<Strategy> strats = new ArrayList<Strategy>();
    
    // Loop through each Player, checking its neighbours.
    for(int i = 0; i < size; i++)
    {
      for(int j = 0; j < size; j++)
      {
        // Store the highest payoff so far.
        double highPayoff = players[i][j].getPayoff();
        // And the Player that it belongs to. 0 for this, 1 for above, 
        // 2 right, 3 below, 4 left, 5 above-left, 6 above-right,
        // 7 below-left, 8 below-right.
        ArrayList<Integer> highPlayers = new ArrayList<Integer>();
        highPlayers.add(0);
        
        // Check above.
        if(i == 0)
        {
          if(players[size-1][j].getPayoff() > highPayoff)
          {
            highPayoff = players[size-1][j].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(1);
          } // if
          else if(players[size-1][j].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(1);
        } // if
        else
        {
          if(players[i-1][j].getPayoff() > highPayoff)
          {
            highPayoff = players[i-1][j].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(1);
          } // if
          else if(players[i-1][j].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(1);
        } // else
        
        // Check to right.
        if(j == size - 1)
        {
          if(players[i][0].getPayoff() > highPayoff)
          {
            highPayoff = players[i][0].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(2);
          } // if
          else if(players[i][0].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(2);
        } // if
        else
        {
          if(players[i][j+1].getPayoff() > highPayoff)
          {
            highPayoff = players[i][j+1].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(2);
          } // if
          else if(players[i][j+1].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(2);
        } // else
        
        // Check below.
        if(i == size - 1)
        {
          if(players[0][j].getPayoff() > highPayoff)
          {
            highPayoff = players[0][j].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(3);
          } // if
          else if(players[0][j].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(3);
        } // if
        else
        {
          if(players[i+1][j].getPayoff() > highPayoff)
          {
            highPayoff = players[i+1][j].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(3);
          } // if
          else if(players[i+1][j].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(3);
        } // else
        
        // Check to left.
        if(j == 0)
        {
          if(players[i][size-1].getPayoff() > highPayoff)
          {
            highPayoff = players[i][size-1].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(4);
          } // if
          else if(players[i][size-1].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(4);
        } // if
        else
        {
          if(players[i][j-1].getPayoff() > highPayoff)
          {
            highPayoff = players[i][j-1].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(4);
          } // if
          else if(players[i][j-1].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(4);
        } // else
        
        if(!fourNeighbours)
        {
          int iNew, jNew;
          // Check above-left.
          iNew = i - 1;
          jNew = j - 1;
          if(i == 0)
            iNew = size - 1;
          if(j == 0)
            jNew = size - 1;
          if(players[iNew][jNew].getPayoff() > highPayoff)
          {
            highPayoff = players[iNew][jNew].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(5);
          } // if
          else if(players[iNew][jNew].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(5);
          
          // Check above-right.
          iNew = i - 1;
          jNew = j + 1;
          if(i == 0)
            iNew = size - 1;
          if(j == size - 1)
            jNew = 0;
          if(players[iNew][jNew].getPayoff() > highPayoff)
          {
            highPayoff = players[iNew][jNew].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(6);
          } // if
          else if(players[iNew][jNew].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(6);
          
          // Check below-left.
          iNew = i + 1;
          jNew = j - 1;
          if(i == size - 1)
            iNew = 0;
          if(j == 0)
            jNew = size - 1;
          if(players[iNew][jNew].getPayoff() > highPayoff)
          {
            highPayoff = players[iNew][jNew].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(7);
          } // if
          else if(players[iNew][jNew].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(7);
          
          // Check below-right.
          iNew = i + 1;
          jNew = j + 1;
          if(i == size - 1)
            iNew = 0;
          if(j == size - 1)
            jNew = 0;
          if(players[iNew][jNew].getPayoff() > highPayoff)
          {
            highPayoff = players[iNew][jNew].getPayoff();
            highPlayers = new ArrayList<Integer>();
            highPlayers.add(8);
          } // if
          else if(players[iNew][jNew].getPayoff() == highPayoff)
            if(highPlayers.get(0) != 0)
              highPlayers.add(8);
        } // if
        
        // Add the position of the Player to the ArrayList.
        if(highPlayers.get(0) != 0)
        {
          playersToSwapRow.add(i);
          playersToSwapColumn.add(j);
          
          // If the highPlayers is larger than 1, then choose randomly.
          int player;
          if(highPlayers.size() > 1)
          {
            Random rand = new Random();
            player = highPlayers.get(rand.nextInt(highPlayers.size()));
          } // if
          else
            player = highPlayers.get(0);
          
          // Add the Strategy to strats.
          if(player == 1)
          {
            if(i == 0)
              strats.add(players[size-1][j].getPlayersStrategy());
            else
              strats.add(players[i-1][j].getPlayersStrategy());
          } // if
          // Swap to right.
          else if(player == 2)
          {
            if(j == size - 1)
              strats.add(players[i][0].getPlayersStrategy());
            else
              strats.add(players[i][j+1].getPlayersStrategy());
          } // else if
          // Swap below.
          else if(player == 3)
          {
            if(i == size - 1)
              strats.add(players[0][j].getPlayersStrategy());
            else
              strats.add(players[i+1][j].getPlayersStrategy());
          } // else if
          // Swap to left.
          else if(player == 4)
          {
            if(j == 0)
              strats.add(players[i][size-1].getPlayersStrategy());
            else
              strats.add(players[i][j-1].getPlayersStrategy());
          } // else
          // Swap above-left
          else if(player == 5)
          {
            int iNew, jNew;
            iNew = i - 1;
            jNew = j - 1;
            if(i == 0)
              iNew = size - 1;
            if(j == 0)
              jNew = size - 1;
            strats.add(players[iNew][jNew].getPlayersStrategy());
          } // else if
          // Swap above-right
          else if(player == 6)
          {
            int iNew, jNew;
            iNew = i - 1;
            jNew = j + 1;
            if(i == 0)
              iNew = size - 1;
            if(j == size - 1)
              jNew = 0;
            strats.add(players[iNew][jNew].getPlayersStrategy());
          } // else if
          // Swap below-left
          else if(player == 7)
          {
            int iNew, jNew;
            iNew = i + 1;
            jNew = j - 1;
            if(i == size - 1)
              iNew = 0;
            if(j == 0)
              jNew = size - 1;
            strats.add(players[iNew][jNew].getPlayersStrategy());
          } // else if
          // Swap below-right
          else
          {
            int iNew, jNew;
            iNew = i + 1;
            jNew = j + 1;
            if(i == size - 1)
              iNew = 0;
            if(j == size - 1)
              jNew = 0;
            strats.add(players[iNew][jNew].getPlayersStrategy());
          } // else
        } // if
      } // for
    } // for
    
    // Swap the Strategies.
    Iterator<Integer> columnIter = playersToSwapColumn.iterator();
    Iterator<Strategy> stratsIter = strats.iterator();

    for(int i : playersToSwapRow)
    {
      int j = columnIter.next();
      Strategy strat = stratsIter.next();
      
      // If Probabilistic, then create a copy of the Strategy.
      if(strat instanceof Probabilistic)
        players[i][j].setPlayersStrategy(new Probabilistic((Probabilistic)strat));
      else
        players[i][j].setPlayersStrategy(strat);
    } // for
  } // checkBestStrat
  
} // class Territory
