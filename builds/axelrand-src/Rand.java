import java.awt.Color;
import java.util.Random;

/**
 * Rand.java
 *
 * Created on 23 November 2007, 15:51
 *
 * @author archers5
 */
public class Rand extends Strategy
{
  /** Creates a new instance of Random */
  public Rand()
  {
  }
  
  /**
   * Method to make a play decision based on this Strategy.
   * Random chooses randomly.
   *
   * @return Either COOPERATE or DEFECT
   */
  public int playTurn(int lastStrat)
  {
    Random rand = new Random();
    
    if(rand.nextDouble() > 0.5)
      return COOPERATE;
    else
      return DEFECT;
  } // playTurn
  
  /**
   * Rerurn the String name of the Strategy.
   *
   * @return String representation of this Strategy
   */
  public String toString()
  {
    return "Random";
  } // toString
  
  /**
   * Return the Color of this Strategy.
   *
   * @return The Color of this Strategy
   */
  public Color getColour()
  {
    return Color.RED;
  } // getColour
  
} // class Random