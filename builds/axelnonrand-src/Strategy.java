import java.awt.Color;

/**
 * Strategy.java
 *
 * Created on 23 November 2007, 15:15
 *
 * An abstract class to allow new Strategies to be created
 * and managed.
 *
 * @author Simon Archer (archers5)
 */
public abstract class Strategy
{
  /**
   * Field to store the COOPERATE strategy.
   */
  public static final int COOPERATE = 1;
  /**
   * Field to store the DEFECT strategy.
   */
  public static final int DEFECT = 2;
  
  /** Creates a new instance of Strategy */
  public Strategy()
  {
  }
  
  /**
   * Method to make a play decision based on this Strategy.
   *
   * @param lastStrat The last play made by the opponent
   * @return Either COOPERATE or DEFECT
   */
  public abstract int playTurn(int lastStrat);
  
  /**
   * Return the String name of the Strategy.
   *
   * @return String representation of this Strategy
   */
  public abstract String toString();
  
  /**
   * Return the Color of this Strategy.
   *
   * @return The Color of this Strategy
   */
  public abstract Color getColour();
  
} // class Strategy
