import java.awt.Color;

/**
 * AlwaysC.java
 *
 * Created on 23 November 2007, 15:36
 *
 * The Always Cooperate Strategy.
 *
 * @author Simon Archer (archers5)
 */
public class AlwaysC extends Strategy
{
  /** Creates a new instance of AlwaysC */
  public AlwaysC()
  {
    
  } // AlwaysC
  
  /**
   * Method to make a play decision based on this Strategy.
   * AlwaysC always cooperates.
   *
   * @return Either COOPERATE or DEFECT
   */
  public int playTurn(int lastStrat)
  {
    return COOPERATE;
  } // playTurn
  
  /**
   * Rerurn the String name of the Strategy.
   *
   * @return String representation of this Strategy
   */
  public String toString()
  {
    return "Always Cooperate";
  } // toString
  
  /**
   * Return the Color of this Strategy.
   *
   * @return The Color of this Strategy
   */
  public Color getColour()
  {
    return Color.BLUE;
  } // getColour
  
} // class AlwaysC
