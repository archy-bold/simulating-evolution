import java.awt.Color;
import java.util.Random;

/**
 * Player.java
 *
 * Created on 24 October 2007, 11:39
 *
 * Class to simply store the relevant information about a
 * Player and to decide on its strategy in a Game.
 *
 * @author Simon Archer (archers5)
 */
public class Player
{
  // Store the payoff.
  private double payoff = 0.0;
  // Also store the strategy for an iterated game.
  Strategy strategy;
  
  /**
   * Creates a new instance of Player.
   * 
   * @param strat The strategy to be used for the Iterated
   *   Prisoner's Dilemma game
   */
  public Player(Strategy strat)
  {
    strategy = strat;
  } // Player
  
  /**
   * Accessor for the payoff variable.
   * 
   * @return The current payoff of the Player
   */
  public double getPayoff()
  {
    return payoff;
  } // getPayoff
  
  /**
   * Method to update the payoff.
   * 
   * @param newPayoff The new payoff to be added to the
   *  current payoff
   */
  public void updatePayoff(double newPayoff)
  {
    payoff += newPayoff;
  } // updatePayoff
  
  /**
   * Method to reset the payoff to 0.
   */
  public void resetPayoff()
  {
    payoff = 0.0;
  } // resetPayoff
  
  /** 
   * Method to decide on what the chosen straegy will be. 
   * 
   * @return The strategy to be used by the Player
   *  (either COOPERATE or DEFECT)
   */
  public int getStrategy(int lastStrat)
  {
    return strategy.playTurn(lastStrat);
  } // getStrategy
  
  /**
   * Accessor for the Strategy's colour.
   *
   * @return The Strategy's colour
   */
  public Color getColour()
  {
    return strategy.getColour();
  } // getColour
  
  /**
   * Accessor method for the Strategy employed by this Player.
   *
   * @return This Player's Strategy
   */
  public Strategy getPlayersStrategy()
  {
    return strategy;
  } // getStrategy
  
  /**
   * Method to set this Player's Strategy to the one given.
   *
   * @param strat This Player's new Strategy
   */
  public void setPlayersStrategy(Strategy strat)
  {
    strategy = strat;
  } // setPlayersStrategy
  
} // class Player
