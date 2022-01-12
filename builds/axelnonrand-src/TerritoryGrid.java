import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
  private int size;
  // The Territory.
  private Territory territory;
  
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
        int buttonSize = (int) 750 / size;
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
   * @parma column The Player's column position
   */
  public void updateColour(int row, int column)
  {
    territories[row][column].setBackground(territory.getPlayer(row, column).getColour());
  } // updateColour
  
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
  
  // Method to display a JPopUpMenu to change the Strategy of a Player.
  private void territoryMouseClicked(MouseEvent evt)
  {
    
  } // territoryMouseClicked
  
  // Method to remove all buttons from the container.
  private void cleanUp()
  {
    // Simply remove all JButtons from the JPanel.
    for(int i = 0; i < size; i++)
      for(int j = 0; j < size; j++)
        this.remove(territories[i][j]);
  } // cleanUp
  
} // class TerritoryGrid

