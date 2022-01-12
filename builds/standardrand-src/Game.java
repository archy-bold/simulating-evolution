import java.util.Random;

/**
 * Game.java
 *
 * Created on 24 October 2007, 11:39
 *
 * This will be the central class to simulate the (Indefinitely)
 * Iterated Prisoner's Dilemma.
 *
 * @author Simon Archer (archers5)
 */
public class Game
{
  // Class variables to store the payoffs and w.
  /**
   * Field to store the payoff for Punishment.
   */
  public static final int PUNISHMENT = 6;
  /**
   * Field to store the payoff for Temptation.
   */
  public static final int TEMPTATION = 56;
  /**
   * Field to store the payoff for Sucker's Payoff.
   */
  public static final int SUCKER = 0;
  /**
   * Field to store the payoff for Reward.
   */
  public static final int REWARD = 29;
  // Store the value for w, probability another game 
  // is played.
  private static double w = (double) 1 / 3;
  // The possible Strategies.
  private static Strategy [] strategies = new Strategy []
      {new Rand(), new AlwaysC(), new AlwaysD(), new TitForTat()};
  
  // Instance variables, store the players of this Game.
  private Player player1;
  private Player player2;
  private int lastPlay;
  
  /**
   * Creates a new instance of Game.
   *
   * @param plyr1 The first Player.
   * @param plyr2 The second Player.
   */
  public Game(Player plyr1, Player plyr2)
  {
    player1 = plyr1;
    player2 = plyr2;
  } // Game
  
  
//!! Consider not using this at all, but having accessors for
//!! the Players' individual parameters (payoff, strategy, etc).
  /**
   * Accessor for Player 1.
   *
   * @return player1
   */
  public Player getPlayer1()
  {
    return player1;
  } // getPlayer1
  
  /**
   * Accessor for Player 2.
   *
   * @return player2
   */
  public Player getPlayer2()
  {
    return player2;
  } // getPlayer2
  
  /**
   * Play through an Iterated Prisoner's Dilemma
   * game.
   */
  public void play()
  {
    lastPlay = -1;
    
    /*double roundsToPlay = 1 / (1 - w);
    
    while(roundsToPlay > 1)
    {
      playPrisoner();
      roundsToPlay -= 1;
    } // while
    
    playPrisoner(roundsToPlay);*/
    
    // Play the first game.
    playPrisoner();
    
    Random rand = new Random();
    // Play the game until a Random number is above w.
    while(rand.nextDouble() < w)
      // Play another game.
      playPrisoner();
    
    // Reset the lastPlay variable.
    lastPlay = -1;
  } // play
  
  /**
   * Play through a game of Prisoner's Dilemma.
   *
   * @return The payoff awarded to player1
   */
  public int playPrisoner()
  {
 //!! Need to check these values?
    int strat1 = 0, strat2 = 0;
    switch(lastPlay)
    {
      case PUNISHMENT:
        strat1 = player1.getStrategy(Strategy.DEFECT);
        strat2 = player2.getStrategy(Strategy.DEFECT);
        break;
      case SUCKER:
        strat1 = player1.getStrategy(Strategy.DEFECT);
        strat2 = player2.getStrategy(Strategy.COOPERATE);
        break;
      case TEMPTATION:
        strat1 = player1.getStrategy(Strategy.COOPERATE);
        strat2 = player2.getStrategy(Strategy.DEFECT);
        break;
      case REWARD:
        strat1 = player1.getStrategy(Strategy.COOPERATE);
        strat2 = player2.getStrategy(Strategy.COOPERATE);
        break;
      default:
        strat1 = player1.getStrategy(-1);
        strat2 = player2.getStrategy(-1);
    } // switch
    
    int payoff = 0;
    
    switch(strat1)
    {
      case Strategy.COOPERATE:
        switch(strat2)
        {
          case Strategy.COOPERATE:
            player1.updatePayoff(REWARD);
            player2.updatePayoff(REWARD);
            payoff = REWARD;
            break;
          case Strategy.DEFECT:
            player1.updatePayoff(SUCKER);
            player2.updatePayoff(TEMPTATION);
            payoff = SUCKER;
            break;
          default:
 //!! Change to exception later.
            break;
        } // switch
        break;
      case Strategy.DEFECT:
        switch(strat2)
        {
          case Strategy.COOPERATE:
            player1.updatePayoff(TEMPTATION);
            player2.updatePayoff(SUCKER);
            payoff = TEMPTATION;
            break;
          case Strategy.DEFECT:
            player1.updatePayoff(PUNISHMENT);
            player2.updatePayoff(PUNISHMENT);
            payoff = PUNISHMENT;
            break;
          default:
 //!! Change to exception later.
            break;
        } // switch
        break;
      default:
 //!! Change to exception later.
        break;
    } // switch
    
    lastPlay = payoff;
    
    return payoff;
    
  } // play
  
  /**
   * Play through a game of Prisoner's Dilemma. Give
   * player1's strategy, for use with testing. 
   *
   * @param strat1 The startegy to be used by the user.
   * @return The payoff awarded to player1
   */
  public int playPrisoner(int strat1)
  {
 //!! Need to check these values?
    int strat2 = 0;
    
    switch(lastPlay)
    {
      case PUNISHMENT:
        strat2 = player2.getStrategy(Strategy.DEFECT);
        break;
      case SUCKER:
        strat2 = player2.getStrategy(Strategy.COOPERATE);
        break;
      case TEMPTATION:
        strat2 = player2.getStrategy(Strategy.DEFECT);
        break;
      case REWARD:
        strat2 = player2.getStrategy(Strategy.COOPERATE);
        break;
      default:
        strat2 = player2.getStrategy(-1);
        break;
    } // switch
    
    int payoff = 0;
    
    switch(strat1)
    {
      case Strategy.COOPERATE:
        switch(strat2)
        {
          case Strategy.COOPERATE:
            player1.updatePayoff(REWARD);
            player2.updatePayoff(REWARD);
            payoff = REWARD;
            break;
          case Strategy.DEFECT:
            player1.updatePayoff(SUCKER);
            player2.updatePayoff(TEMPTATION);
            payoff = SUCKER;
            break;
          default:
 //!! Change to exception later.
            break;
        } // switch
        break;
      case Strategy.DEFECT:
        switch(strat2)
        {
          case Strategy.COOPERATE:
            player1.updatePayoff(TEMPTATION);
            player2.updatePayoff(SUCKER);
            payoff = TEMPTATION;
            break;
          case Strategy.DEFECT:
            player1.updatePayoff(PUNISHMENT);
            player2.updatePayoff(PUNISHMENT);
            payoff = PUNISHMENT;
            break;
          default:
 //!! Change to exception later.
            break;
        } // switch
        break;
      default:
 //!! Change to exception later.
        break;
    } // switch
    
    lastPlay = payoff;
    
    return strat2;
  } // play
  
  /**
   * Acessor for w, probability another game is played.
   *
   * @return The value of w, between 0 and 1
   */
  public static double getW()
  {
    return w;
  } // setW
  
  /**
   * Set the value for w, probability another game is played.
   *
   * @param newW The new value of w, between 0 and 1
   */
  public static void setW(double newW)
  {
//!!!Check value of w.
    w = newW;
  } // setW
  
  /**
   * Method to return the array of available Strategies.
   *
   * @return The strategies array
   */
  public static Strategy [] getStrategies()
  {
    return strategies;
  } // getStrategies
  
  
  public int playPrisoner(double x)
  {
 //!! Need to check these values?
    int strat1 = 0, strat2 = 0;
    switch(lastPlay)
    {
      case PUNISHMENT:
        strat1 = player1.getStrategy(Strategy.DEFECT);
        strat2 = player2.getStrategy(Strategy.DEFECT);
        break;
      case SUCKER:
        strat1 = player1.getStrategy(Strategy.DEFECT);
        strat2 = player2.getStrategy(Strategy.COOPERATE);
        break;
      case TEMPTATION:
        strat1 = player1.getStrategy(Strategy.COOPERATE);
        strat2 = player2.getStrategy(Strategy.DEFECT);
        break;
      case REWARD:
        strat1 = player1.getStrategy(Strategy.COOPERATE);
        strat2 = player2.getStrategy(Strategy.COOPERATE);
        break;
      default:
        strat1 = player1.getStrategy(-1);
        strat2 = player2.getStrategy(-1);
    } // switch
    
    int payoff = 0;
    
    switch(strat1)
    {
      case Strategy.COOPERATE:
        switch(strat2)
        {
          case Strategy.COOPERATE:
            player1.updatePayoff(x * REWARD);
            player2.updatePayoff(x * REWARD);
            payoff = REWARD;
            break;
          case Strategy.DEFECT:
            player1.updatePayoff(x * SUCKER);
            player2.updatePayoff(x * TEMPTATION);
            payoff = SUCKER;
            break;
          default:
 //!! Change to exception later.
            break;
        } // switch
        break;
      case Strategy.DEFECT:
        switch(strat2)
        {
          case Strategy.COOPERATE:
            player1.updatePayoff(x * TEMPTATION);
            player2.updatePayoff(x * SUCKER);
            payoff = TEMPTATION;
            break;
          case Strategy.DEFECT:
            player1.updatePayoff(x * PUNISHMENT);
            player2.updatePayoff(x * PUNISHMENT);
            payoff = PUNISHMENT;
            break;
          default:
 //!! Change to exception later.
            break;
        } // switch
        break;
      default:
 //!! Change to exception later.
        break;
    } // switch
    
    lastPlay = payoff;
    
    return payoff;
    
  } // play
    
} // class Game
