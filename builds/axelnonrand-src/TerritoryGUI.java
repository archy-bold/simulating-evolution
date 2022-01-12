/**
 * TerritoryGUI.java
 *
 * Created on 22 November 2007, 17:46
 *
 * Interface to ensure that any GUI which uses a TerritoryGrid
 * has a processButton method.
 *
 * @author Simon Archer (archers5)
 */
public interface TerritoryGUI
{
  /**
   * Method to pass a Player, and its position, to this from
   * TerritoryGrid
   * 
   * @param plyr The Player
   * @param i The Player's row
   * @param j The Player's column
   */
  void processButton(Player plyr, int i, int j);
} // interface TerritoryGUI
