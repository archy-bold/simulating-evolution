import java.awt.Color;
import java.util.Random;

/*
 * Probabilistic.java
 *
 * Created on 20 February 2008, 17:31
 *
 * The Probabilistic Strategy class.
 * 
 * @author Simon Archer (archers5)
 */
public class Probabilistic extends Strategy
{
  // Values to store the probabilities.
  private double p1;
  private double p2;
  private double p3;
  private double p4;
  // Also store the last move made.
  private int myLastStrat = -1;
  
  /** Creates a new instance of Probabilistic */
  public Probabilistic()
  {
    // Set all the probabilities as random.
    Random rand = new Random();
    
    p1 = rand.nextDouble();
    p2 = rand.nextDouble();
    p3 = rand.nextDouble();
    p4 = rand.nextDouble();
  } // Probabilistic
  
  /**
   * Create a copy of the other Probabilistic instance.
   *
   * @param other The Probabilistic to copy
   */
  public Probabilistic(Probabilistic other)
  {
    p1 = other.getP1();
    p2 = other.getP2();
    p3 = other.getP3();
    p4 = other.getP4();
  } // Probabilistic
  
  /**
   * Accessor method for the p1 variable.
   *
   * @return p1
   */
  public double getP1()
  {
    return p1;
  } // getP1
  
  /**
   * Accessor method for the p2 variable.
   *
   * @return p2
   */
  public double getP2()
  {
    return p2;
  } // getP2
  
  /**
   * Accessor method for the p3 variable.
   *
   * @return p3
   */
  public double getP3()
  {
    return p3;
  } // getP3
  
  /**
   * Accessor method for the p4 variable.
   *
   * @return p4
   */
  public double getP4()
  {
    return p4;
  } // getP4
  
  /**
   * Method to make a play decision based on this Strategy.
   * Probabilistic decides this with the probabilities.
   *
   * @return Either COOPERATE or DEFECT
   */
  public int playTurn(int lastStrat)
  {
    Random rand = new Random();
    
    if(myLastStrat == -1)
    {
      if(rand.nextBoolean())
        myLastStrat = COOPERATE;
      else
        myLastStrat = DEFECT;
      
      return myLastStrat;
    } // if
      
    double prob = rand.nextDouble();
    
    if(myLastStrat == COOPERATE)
    {
      if(lastStrat == COOPERATE)
      {
        if(prob < p1)
          myLastStrat = COOPERATE;
        else
          myLastStrat = DEFECT;
        
        return myLastStrat;
      } // if
      else
      {
        if(prob < p2)
          myLastStrat = COOPERATE;
        else
          myLastStrat = DEFECT;
        
        return myLastStrat;
      } // else
    } // if
    else
    {
      if(lastStrat == COOPERATE)
      {
        if(prob < p3)
          myLastStrat = COOPERATE;
        else
          myLastStrat = DEFECT;
        
        return myLastStrat;
      } // if
      else
      {
        if(prob < p4)
          myLastStrat = COOPERATE;
        else
          myLastStrat = DEFECT;
        
        return myLastStrat;
      } // else
    } // else
    
  } // playTurn
  
  /**
   * Rerurn the String name of the Strategy.
   *
   * @return String representation of this Strategy
   */
  public String toString()
  {
    return "Probabilistic";
  } // toString
  
  /**
   * Return the Color of this Strategy.
   *
   * @return The Color of this Strategy
   */
  public Color getColour()
  {
    return new Color((float)p1, (float)p2, (float)p3);
  } // getColour
  
  /**
   * Determines whether some other Object is equal to this one.
   *
   * @param other The other Object
   * @return true if they are equal, false otherwise
   */
  public boolean equals(Object other)
  {
    if(other == this)
      return true;
    
    if (!(other instanceof Probabilistic))
      return false;
    
    
    Probabilistic prob = (Probabilistic)other;
    
    return prob.getP1() == p1 && prob.getP2() == p2 &&
       prob.getP3() == p3 && prob.getP4() == p4;
  } // equals
  
  /**
   * Returns a hash code value for the object.
   * 
   * @return a has code value for this Object
   */
  public int hashCode()
  {
    int result = 17;
    long f = Double.doubleToLongBits(p1);
    result = 37*result + (int)(f ^ (f >>> 32));
    f = Double.doubleToLongBits(p2);
    result = 37*result + (int)(f ^ (f >>> 32));
    f = Double.doubleToLongBits(p3);
    result = 37*result + (int)(f ^ (f >>> 32));
    f = Double.doubleToLongBits(p4);
    result = 37*result + (int)(f ^ (f >>> 32));
    return result;
  } // hashCode
  
} // class Probabilistic
