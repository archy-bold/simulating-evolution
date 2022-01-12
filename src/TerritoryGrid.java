import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.MenuElement;
import javax.swing.SwingUtilities;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * TerritoryGrid.java
 * 
 * Created on 20 November 2007, 12:17
 *
 * JPanel to display the grid for a visual representation
 * of a Territory. Any JFrame to use this must implement the
 * interface TerritoryGUI in order to receive Players from
 * the Territory upon clicking a button.
 * 
 * @author Simon Archer (archers5)
 */
public class TerritoryGrid extends JPanel
{
  // An array of JButtons.
  private JButton [][] territories;
  // The JPopupMenu.
  private JPopupMenu popup;
  private JMenuItem popupTitle;
  private JRadioButtonMenuItem [] stratMenuItems;
  private int size;
  // The Territory.
  private Territory territory;
  private int selectedRow;
  private int selectedColumn;
  
  /**
   * Creates new form TerritoryGrid. The grid defaults to a 3x3.
   */
  public TerritoryGrid()
  {
    this(3);
  } // TerritoryGrid
  
  /**
   * Creates new form TerritoryGrid.
   * 
   * @param sze Size of the square grid (e.g. 3 gives a 3x3 grid)
   */
  public TerritoryGrid(int sze)
  {
    size = sze;
    // Initialise the array of JButtons.
    territories = new JButton[size][size];
    
    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        territories[i][j] = new JButton();
    
    // Set up the Territory.
    territory = new Territory(size);
    
    initComponents();
  } // TerritoryGrid
  
  /* This method is called from within the constructor to
   * initialize the form.
   */                         
  private void initComponents()
  {
    // Set the colour and size of each button.
    for(int i = 0; i < size; i++)
    {
      for(int j = 0; j < size; j++)
      {
        territories[i][j].setBackground(territory.getPlayer(i, j).getColour());
        territories[i][j].setMinimumSize(new Dimension(10, 10));
        int buttonSize = (int) 625 / size;
        territories[i][j].setPreferredSize(new Dimension(buttonSize, buttonSize));
        
        // Add action and mouse listeners.
        territories[i][j].addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent evt)
          {
            territoryActionPerformed(evt);
          } // actionPerformed
        });
        territories[i][j].addMouseListener(new MouseListener()
        {
          public void mouseClicked(MouseEvent evt)
          {
            territoryMouseClicked(evt);
          } // mousePerformed
          
          public void mouseExited (MouseEvent e) {;}
          
          public void mouseEntered(MouseEvent e){;}
          
          public void mouseReleased(MouseEvent e){;}
          
          public void mousePressed(MouseEvent e){;}
        });
      } // for
    } //for
    
    // Set the layout to be a GridLayout.
    this.setLayout(new GridLayout(size, size));
    
    // Create the Popup Menu.
    popup = new JPopupMenu("");
    Strategy [] strats = Game.getStrategies();
    popup.add(popupTitle = new JMenuItem("non-specified title"));
    popupTitle.setEnabled(false);
    popup.addSeparator();
    
    stratMenuItems = new JRadioButtonMenuItem[strats.length];
    ButtonGroup group = new ButtonGroup();
    for(int i = 0; i < strats.length; i++)
    {
      stratMenuItems[i] = new JRadioButtonMenuItem(strats[i].toString());
      popup.add(stratMenuItems[i]);
      
      group.add(stratMenuItems[i]);
      
      // Add an ActionListener to the JMenuItem.
      stratMenuItems[i].addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent evt)
        {
          // Find the Strategy that matches this Strategy and assign the
          // selected Player that Strategy.
          JRadioButtonMenuItem src = (JRadioButtonMenuItem) evt.getSource();
          
          Strategy [] strats = Game.getStrategies();
          boolean foundYet = false;
          int i;
          for(i = 0; i < strats.length && !foundYet; i++)
            if(strats[i].toString().equals(src.getText()))
              foundYet = true;
          i--;
          
          if(strats[i].toString().equals("Probabilistic"))
            territory.getPlayer(selectedRow, selectedColumn).setPlayersStrategy(new Probabilistic());
          else
            territory.getPlayer(selectedRow, selectedColumn).setPlayersStrategy(strats[i]);
          updateColour(selectedRow, selectedColumn);
        }
      });
    } // for
    
    // Add the buttons to the layout.
    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        this.add(territories[i][j]);
    
  } // initComponents
  
  /**
   * Method to set the size of the grid. An arguement
   * of 3 gives a 3x3 grid.
   * Warning: This completely resets the current Territory and
   * thus the whole progress of the Games so far.
   *
   * @param sze Size of the grid
   */
  public void setSize(int sze)
  {
    cleanUp();
    // Just do the work of the constructor.
    size = sze;
    // Initialise the array of JButtons.
    territories = new JButton[size][size];
    
    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        territories[i][j] = new JButton();
    
    // Set up the Territory.
    territory = new Territory(size, territory.getFourNeighbours());
    
    initComponents();
  } // setSize
  
  /**
   * Play a single generation in the territory.
   */
  public void step()
  {
    territory.step();
    
    // Update colour of every JButton.
    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        territories[i][j].setBackground(territory.getPlayer(i, j).getColour());
  } // step
  
  /**
   * Method to change the territory's neighbour system.
   *
   * @param fourNeigh true for 4 neighbours, false for 8
   */
  public void setNeighbours(boolean fourNeigh)
  {
    territory.setNeighbours(fourNeigh);
  } // changeNeighbours
  
  /**
   * Method to update the colour of the given Player.
   *
   * @param row The Player's row position
   * @param column The Player's column position
   */
  public void updateColour(int row, int column)
  {
    territories[row][column].setBackground(territory.getPlayer(row, column).getColour());
  } // updateColour
  
  /**
   * Method to get the p1 values of all Probabilistic
   * Strategies in this Territory.
   *
   * @return An array of the p1 values
   */
  public double [] getP1Values()
  {
    ArrayList<Double> p1Values = new ArrayList<Double>();
    
    // Get each Player individually.
    for(int i = 0; i < size; i++)
    {
      for(int j = 0; j < size; j++)
      {
        Strategy plyrStrat = territory.getPlayer(i, j).getPlayersStrategy();
        
        // Only continue if is a Probabilistic.
        if(plyrStrat instanceof Probabilistic)
          p1Values.add(((Probabilistic)plyrStrat).getP1());
      } // for
    } // for
    
    double [] finalP1Values = new double[p1Values.size()];
    
    for(int i = 0; i < finalP1Values.length; i++)
      finalP1Values[i] = p1Values.get(i);
    
    return finalP1Values;
  } // getP1Values
  
  /**
   * Method to get the p1 values of all Probabilistic
   * Strategies in this Territory.
   *
   * @return An array of the p1 values
   */
  public double [] getP2Values()
  {
    ArrayList<Double> p2Values = new ArrayList<Double>();
    
    // Get each Player individually.
    for(int i = 0; i < size; i++)
    {
      for(int j = 0; j < size; j++)
      {
        Strategy plyrStrat = territory.getPlayer(i, j).getPlayersStrategy();
        
        // Only continue if is a Probabilistic.
        if(plyrStrat instanceof Probabilistic)
          p2Values.add(((Probabilistic)plyrStrat).getP2());
      } // for
    } // for
    
    double [] finalP2Values = new double[p2Values.size()];
    
    for(int i = 0; i < finalP2Values.length; i++)
      finalP2Values[i] = p2Values.get(i);
    
    return finalP2Values;
  } // getP2Values
  
  /**
   * Method to get the p1 values of all Probabilistic
   * Strategies in this Territory.
   *
   * @return An array of the p1 values
   */
  public double [] getP3Values()
  {
    ArrayList<Double> p3Values = new ArrayList<Double>();
    
    // Get each Player individually.
    for(int i = 0; i < size; i++)
    {
      for(int j = 0; j < size; j++)
      {
        Strategy plyrStrat = territory.getPlayer(i, j).getPlayersStrategy();
        
        // Only continue if is a Probabilistic.
        if(plyrStrat instanceof Probabilistic)
          p3Values.add(((Probabilistic)plyrStrat).getP3());
      } // for
    } // for
    
    double [] finalP3Values = new double[p3Values.size()];
    
    for(int i = 0; i < finalP3Values.length; i++)
      finalP3Values[i] = p3Values.get(i);
    
    return finalP3Values;
  } // getP3Values
  
  /**
   * Method to get the p4 values of all Probabilistic
   * Strategies in this Territory.
   *
   * @return An array of the p1 values
   */
  public double [] getP4Values()
  {
    ArrayList<Double> p4Values = new ArrayList<Double>();
    
    // Get each Player individually.
    for(int i = 0; i < size; i++)
    {
      for(int j = 0; j < size; j++)
      {
        Strategy plyrStrat = territory.getPlayer(i, j).getPlayersStrategy();
        
        // Only continue if is a Probabilistic.
        if(plyrStrat instanceof Probabilistic)
          p4Values.add(((Probabilistic)plyrStrat).getP4());
      } // for
    } // for
    
    double [] finalP4Values = new double[p4Values.size()];
    
    for(int i = 0; i < finalP4Values.length; i++)
      finalP4Values[i] = p4Values.get(i);
    
    return finalP4Values;
  } // getP4Values
  
  /**
   * Method to return an array of the top ten Probabilistic Strategies
   * in this Territory. The Top Strategies are decided by the quantity
   * of each Strategy.
   *
   * @return Array of Probabilistic Strategies
   */
  public HashMap<Probabilistic, Integer> getRankedProbabilistic()
  {
    HashMap<Probabilistic, Integer> strats = new HashMap<Probabilistic, Integer>();
    
    // Loop through all the Players.
    Strategy strat;
    for(int i = 0; i < size; i++)
    {
      for(int j = 0; j < size; j++)
      {
        // Insert each into the HashMap.
        strat = territory.getPlayer(i, j).getPlayersStrategy();
        if(strat instanceof Probabilistic)
        {
          if(strats.containsKey(strat))
            // Increment the value.
            strats.put((Probabilistic)strat, new Integer(strats.get(strat)+1));
          else
            // Give a quantity of 1.
            strats.put((Probabilistic)strat, new Integer(1));
        } // if
      } // for
    } // for
    
    return strats;
  } // getTopTenProbabilistic
  
  // Events handler for buttons.
//!!!Try my darndest to make this nicer!
  private void territoryActionPerformed(ActionEvent evt)
  {
    JButton src = (JButton) evt.getSource();
    
    // Find where in the array the src is.
    int row = 0, column = 0;
    boolean found = false;
    for(int i = 0; !found && i < size; i++)
      for(int j = 0; !found && j < size; j++)
        if(src == territories[i][j])
        {
          found = true;
          row = i;
          column = j;
        } // if
    
    // Now call processButton in the parent JFrame of this.
    Window window = SwingUtilities.windowForComponent(src);
//!!!Perhaps catch ClassCastException.
    ((TerritoryGUI) window).processButton(territory.getPlayer(row, column), row, column);
  } // territoryActionPerformed
  
  // Method to display a JPopupMenu to change the Strategy of a Player.
  private void territoryMouseClicked(MouseEvent evt)
  {
    if(SwingUtilities.isRightMouseButton(evt))
    {
      JButton src = (JButton) evt.getSource();
      
      // Do not display if the Component is disabled.
      if(src.getParent().isEnabled() == true)
      {
        // Find where in the array the src is.
        int row = 0, column = 0;
        boolean found = false;
        for(int i = 0; !found && i < size; i++)
          for(int j = 0; !found && j < size; j++)
            if(src == territories[i][j])
            {
              found = true;
              row = i;
              column = j;
            } // if
        
        Player player = territory.getPlayer(row, column);
        selectedRow = row;
        selectedColumn = column;
        
        // Display the JPopupMenu.
        popupTitle.setText("Player " + row + "," + column);
        // Find and enable the correct Strategy JRadioButtonMenuItem.
        MenuElement [] menuItems = popup.getSubElements();
        boolean foundYet = false;
        for(int i = 1; i < menuItems.length && !foundYet; i++)
        {
          if(((JRadioButtonMenuItem)menuItems[i]).getText().equals(player.getPlayersStrategy().toString()))
          {
            ((JRadioButtonMenuItem)menuItems[i]).setSelected(true);
            foundYet = true;
          } // if
        } // for
        
        popup.show(src, evt.getX(), evt.getY());
      } // if
    } // if
  } // territoryMouseClicked
  
  // Method to remove all buttons from the container.
  private void cleanUp()
  {
    // Simply remove all JButtons from the JPanel.
    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        this.remove(territories[i][j]);
  } // cleanUp
  
  // Method to find the Player associated with the JButton given.
  private Player playerFromJButton(JButton btn)
  {
    // Find where in the array the src is.
    int row = 0, column = 0;
    boolean found = false;
    for(int i = 0; !found && i < size; i++)
      for(int j = 0; !found && j < size; j++)
        if(btn == territories[i][j])
        {
          found = true;
          row = i;
          column = j;
        } // if
    
    return territory.getPlayer(row, column);
  } // playerFromButton
  
} // class TerritoryGrid

