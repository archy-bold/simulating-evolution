import java.awt.Color;

/**
 * AlwaysD.java
 *
 * Created on 23 November 2007, 15:31
 * 
 * The Always Defect Strategy.
 *
 * @author Simon Archer (archers5)
 */
public class AlwaysD extends Strategy
{
  /** Creates a new instance of AlwaysD */
  public AlwaysD()
  {
    
  } // AlwaysD
  
  /**
   * Method to make a play decision based on this Strategy.
   * AlwaysD always defects.
   *
   * @return Either COOPERATE or DEFECT
   */
  public int playTurn(int lastStrat)
  {
    return DEFECT;
  } // playTurn
  
  /**
   * Return the String name of the Strategy.
   *
   * @return String representation of this Strategy
   */
  public String toString()
  {
    return "Always Defect";
  } // toString
  
  /**
   * Return the Color of this Strategy.
   *
   * @return The Color of this Strategy
   */
  public Color getColour()
  {
    return Color.BLACK;
  } // getColour
  
} // class AlwaysD
