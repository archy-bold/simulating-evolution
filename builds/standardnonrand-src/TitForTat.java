import java.awt.Color;

/**
 * TitForTat.java
 *
 * Created on 24 November 2007, 17:38
 *
 * The Tit For Tat Strategy.
 *
 * @author Simon
 */
public class TitForTat extends Strategy
{
  
  /** Creates a new instance of TitForTat */
  public TitForTat()
  {
  }
  
  /**
   * Method to make a play decision based on this Strategy.
   * TitForTat COOPERATES first, then mirrors the last choice
   * of the othe atrategy after that.
   *
   * @return Either COOPERATE or DEFECT
   */
  public int playTurn(int lastStrat)
  {
    if(lastStrat == COOPERATE || lastStrat == -1)
      return COOPERATE;
    else
      return DEFECT;
  } // playTurn
  
  /**
   * Return the String name of the Strategy.
   *
   * @return String representation of this Strategy
   */
  public String toString()
  {
    return "Tit For Tat";
  } // toString
  
  /**
   * Return the Color of this Strategy.
   *
   * @return The Color of this Strategy
   */
  public Color getColour()
  {
    return Color.WHITE;
  } // getColour
  
} // class TitForTat
