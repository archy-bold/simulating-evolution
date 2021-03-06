/**
 * EvolutionGUI.java
 *
 * Created on 24 October 2007, 11:34
 *
 * @author Simon Archer (archers5)
 */
public class EvolutionGUI extends javax.swing.JFrame implements TerritoryGUI
{
  // Store the generation.
  private int generation = 0;
  // Territory size.
  private int size = 3;
  // Selected Player.
  private Player selectedPlayer;
  private int row, column;
  // No neighbours.
  boolean fourNeighbours = true;
  
  /**
   * Creates new form EvolutionGUI
   */
  public EvolutionGUI()
  {
    this.setTitle("Simulating Evolution");
    
    initComponents();
  } // EvolutionGUI
    
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents()
  {
    neighbourButtonGroup = new javax.swing.ButtonGroup();
    step = new javax.swing.JButton();
    territoryGrid = territoryGrid = new TerritoryGrid(size);
    generationLabel = new javax.swing.JLabel();
    variablePanel = new javax.swing.JPanel();
    wLabel = new javax.swing.JLabel();
    playerPanel = new javax.swing.JPanel();
    playerPayoff = new javax.swing.JLabel();
    playerPayoffField = new javax.swing.JTextField();
    stratLabel = new javax.swing.JLabel();
    stratCombo = stratCombo = new javax.swing.JComboBox(Game.getStrategies());
    sizeLabel = new javax.swing.JLabel();
    neighbourPanel = new javax.swing.JPanel();
    fourNeighRadioButton = new javax.swing.JRadioButton();
    eightNeighRadioButton = new javax.swing.JRadioButton();
    sizeLabel2 = new javax.swing.JLabel();
    sizeSlider = new javax.swing.JSlider();
    wLabel2 = new javax.swing.JLabel();
    wSlider = new javax.swing.JSlider();
    runButton = new javax.swing.JButton();
    speedLabel = new javax.swing.JLabel();
    speedSlider = new javax.swing.JSlider();
    speedLabel2 = new javax.swing.JLabel();
    jMenuBar1 = new javax.swing.JMenuBar();
    file = new javax.swing.JMenu();
    newGame = new javax.swing.JMenuItem();
    quit = new javax.swing.JMenuItem();
    help = new javax.swing.JMenu();
    about = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    step.setText("Step");
    step.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        stepActionPerformed(evt);
      }
    });

    generationLabel.setText("Generation 0");

    wLabel.setText("w Value:");

    playerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Player"));
    playerPayoff.setText("Payoff");

    playerPayoffField.setEditable(false);

    stratLabel.setText("Strategy");

    stratCombo.setEnabled(false);
    stratCombo.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        stratComboActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout playerPanelLayout = new org.jdesktop.layout.GroupLayout(playerPanel);
    playerPanel.setLayout(playerPanelLayout);
    playerPanelLayout.setHorizontalGroup(
      playerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(playerPanelLayout.createSequentialGroup()
        .add(playerPayoff)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(playerPayoffField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
      .add(playerPanelLayout.createSequentialGroup()
        .add(stratLabel)
        .addContainerGap())
      .add(org.jdesktop.layout.GroupLayout.TRAILING, stratCombo, 0, 144, Short.MAX_VALUE)
    );
    playerPanelLayout.setVerticalGroup(
      playerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(playerPanelLayout.createSequentialGroup()
        .add(playerPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(playerPayoff)
          .add(playerPayoffField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(stratLabel)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(stratCombo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
    );

    sizeLabel.setText("Size:");

    neighbourPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Neighbour System"));
    neighbourButtonGroup.add(fourNeighRadioButton);
    fourNeighRadioButton.setSelected(true);
    fourNeighRadioButton.setText("4 Neighbours");
    fourNeighRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    fourNeighRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
    fourNeighRadioButton.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        fourNeighRadioButtonActionPerformed(evt);
      }
    });

    neighbourButtonGroup.add(eightNeighRadioButton);
    eightNeighRadioButton.setText("8 Neighbours");
    eightNeighRadioButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    eightNeighRadioButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
    eightNeighRadioButton.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        eightNeighRadioButtonActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout neighbourPanelLayout = new org.jdesktop.layout.GroupLayout(neighbourPanel);
    neighbourPanel.setLayout(neighbourPanelLayout);
    neighbourPanelLayout.setHorizontalGroup(
      neighbourPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(neighbourPanelLayout.createSequentialGroup()
        .addContainerGap()
        .add(neighbourPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(fourNeighRadioButton)
          .add(eightNeighRadioButton))
        .addContainerGap(53, Short.MAX_VALUE))
    );
    neighbourPanelLayout.setVerticalGroup(
      neighbourPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(neighbourPanelLayout.createSequentialGroup()
        .add(fourNeighRadioButton)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(eightNeighRadioButton))
    );

    sizeLabel2.setText("3");

    sizeSlider.setMaximum(75);
    sizeSlider.setMinimum(3);
    sizeSlider.setValue(3);
    sizeSlider.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseReleased(java.awt.event.MouseEvent evt)
      {
        sizeSliderMouseReleased(evt);
      }
    });
    sizeSlider.addChangeListener(new javax.swing.event.ChangeListener()
    {
      public void stateChanged(javax.swing.event.ChangeEvent evt)
      {
        sizeSliderStateChanged(evt);
      }
    });

    wLabel2.setText("0.33333");

    wSlider.setMaximum(99999);
    wSlider.setValue(33333);
    wSlider.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseReleased(java.awt.event.MouseEvent evt)
      {
        wSliderMouseReleased(evt);
      }
    });
    wSlider.addChangeListener(new javax.swing.event.ChangeListener()
    {
      public void stateChanged(javax.swing.event.ChangeEvent evt)
      {
        wSliderStateChanged(evt);
      }
    });

    org.jdesktop.layout.GroupLayout variablePanelLayout = new org.jdesktop.layout.GroupLayout(variablePanel);
    variablePanel.setLayout(variablePanelLayout);
    variablePanelLayout.setHorizontalGroup(
      variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, variablePanelLayout.createSequentialGroup()
        .add(variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(wLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
          .add(sizeLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
        .add(34, 34, 34)
        .add(variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(sizeLabel2)
          .add(wLabel2)))
      .add(neighbourPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .add(playerPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .add(sizeSlider, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, wSlider, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
    );
    variablePanelLayout.setVerticalGroup(
      variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(variablePanelLayout.createSequentialGroup()
        .add(variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(sizeLabel)
          .add(sizeLabel2))
        .add(4, 4, 4)
        .add(sizeSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(variablePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(wLabel)
          .add(wLabel2))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(wSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .add(4, 4, 4)
        .add(neighbourPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(playerPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    runButton.setText("Run");
    runButton.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        runButtonActionPerformed(evt);
      }
    });

    speedLabel.setText("Execution Speed:");

    speedSlider.setMaximum(300);
    speedSlider.addMouseListener(new java.awt.event.MouseAdapter()
    {
      public void mouseReleased(java.awt.event.MouseEvent evt)
      {
        speedSliderMouseReleased(evt);
      }
    });
    speedSlider.addChangeListener(new javax.swing.event.ChangeListener()
    {
      public void stateChanged(javax.swing.event.ChangeEvent evt)
      {
        speedSliderStateChanged(evt);
      }
    });

    speedLabel2.setText("0.5 secs");

    file.setText("File");
    newGame.setText("New Game");
    newGame.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        newGameActionPerformed(evt);
      }
    });

    file.add(newGame);

    quit.setText("Quit");
    quit.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        quitActionPerformed(evt);
      }
    });

    file.add(quit);

    jMenuBar1.add(file);

    help.setText("Help");
    help.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        helpActionPerformed(evt);
      }
    });

    about.setText("About");
    about.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(java.awt.event.ActionEvent evt)
      {
        aboutActionPerformed(evt);
      }
    });

    help.add(about);

    jMenuBar1.add(help);

    setJMenuBar(jMenuBar1);

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(territoryGrid, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(generationLabel))
        .add(27, 27, 27)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(org.jdesktop.layout.GroupLayout.TRAILING, speedSlider, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
          .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .add(speedLabel)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 36, Short.MAX_VALUE)
            .add(speedLabel2))
          .add(org.jdesktop.layout.GroupLayout.TRAILING, runButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
          .add(org.jdesktop.layout.GroupLayout.TRAILING, step, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
          .add(variablePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(layout.createSequentialGroup()
            .add(generationLabel)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(territoryGrid, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(variablePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .add(step)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(runButton)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(speedLabel2)
          .add(speedLabel))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(speedSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );
    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void speedSliderMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_speedSliderMouseReleased
  {//GEN-HEADEREND:event_speedSliderMouseReleased
    runSpeed = speedSlider.getValue() * 10;
  }//GEN-LAST:event_speedSliderMouseReleased

  private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_speedSliderStateChanged
  {//GEN-HEADEREND:event_speedSliderStateChanged
    // Set the speedLabel2 to be the slider value.
    speedLabel2.setText(((double)speedSlider.getValue()/100)+ " secs");
    
    runSpeed = speedSlider.getValue() * 10;
  }//GEN-LAST:event_speedSliderStateChanged

  private void wSliderMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_wSliderMouseReleased
  {//GEN-HEADEREND:event_wSliderMouseReleased
    // Simply set w.
    Game.setW((double) wSlider.getValue()/100000);
  }//GEN-LAST:event_wSliderMouseReleased

  private void wSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_wSliderStateChanged
  {//GEN-HEADEREND:event_wSliderStateChanged
    // Set the wLabel2 to be the slider value.
    wLabel2.setText("" + ((double)wSlider.getValue()/100000));
  }//GEN-LAST:event_wSliderStateChanged

  private void sizeSliderStateChanged(javax.swing.event.ChangeEvent evt)//GEN-FIRST:event_sizeSliderStateChanged
  {//GEN-HEADEREND:event_sizeSliderStateChanged
    // Set the sizeLabel2 to be the slider value.
    sizeLabel2.setText("" + sizeSlider.getValue());
  }//GEN-LAST:event_sizeSliderStateChanged

  private void sizeSliderMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_sizeSliderMouseReleased
  {//GEN-HEADEREND:event_sizeSliderMouseReleased
    // If running, stop execution.
    if(timer != null)
    {
      running = false;
      timer.cancel();
      runButton.setText("Run");
    } // if
    
    int sze = sizeSlider.getValue();
    size = sze;
    
    // Set the territory size.
    territoryGrid.setSize(size);
    
    // Reset the generation count.
    generationLabel.setText("Generation 0");
    generation = 0;
    
    // Clear the info.
    playerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Player"));
    stratCombo.setEnabled(false);
    selectedPlayer = null;
    playerPayoffField.setText("");
    
    // Repack because the size of the window has changed.
    pack();
  }//GEN-LAST:event_sizeSliderMouseReleased

  private void eightNeighRadioButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_eightNeighRadioButtonActionPerformed
  {//GEN-HEADEREND:event_eightNeighRadioButtonActionPerformed
    // TODO add your handling code here:
    if(fourNeighbours)
    {
      fourNeighbours = false;
      territoryGrid.setNeighbours(fourNeighbours);
    } // if
  }//GEN-LAST:event_eightNeighRadioButtonActionPerformed

  private void fourNeighRadioButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_fourNeighRadioButtonActionPerformed
  {//GEN-HEADEREND:event_fourNeighRadioButtonActionPerformed
    // TODO add your handling code here:
    if(!fourNeighbours)
    {
      fourNeighbours = true;
      territoryGrid.setNeighbours(fourNeighbours);
    } // if
  }//GEN-LAST:event_fourNeighRadioButtonActionPerformed

  // Store variables to handling running and pausing.
  private boolean running = false;
  private java.util.Timer timer;
  int runSpeed = 500;
  
  // Handle the running and pausing with Timers and delayed TimerTasks.
  private void runButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_runButtonActionPerformed
  {//GEN-HEADEREND:event_runButtonActionPerformed
    running = !running;
    
    if(running)
    {
      runButton.setText("Pause");
      
      territoryGrid.setEnabled(false);
      
      timer = new java.util.Timer();
      
      stepThrough();      
    } // if
    else
    {
      runButton.setText("Run");
      timer.cancel();
      territoryGrid.setEnabled(true);
    } // else
  }//GEN-LAST:event_runButtonActionPerformed

  private void stratComboActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_stratComboActionPerformed
  {//GEN-HEADEREND:event_stratComboActionPerformed
    // TODO add your handling code here:
    if(selectedPlayer != null)
      selectedPlayer.setPlayersStrategy((Strategy)stratCombo.getSelectedItem());
    
    territoryGrid.updateColour(row, column);
  }//GEN-LAST:event_stratComboActionPerformed

  private void quitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_quitActionPerformed
  {//GEN-HEADEREND:event_quitActionPerformed
    // TODO add your handling code here:
    System.exit(0);
  }//GEN-LAST:event_quitActionPerformed

  private void aboutActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_aboutActionPerformed
  {//GEN-HEADEREND:event_aboutActionPerformed
    // TODO add your handling code here:
    new About().setVisible(true);
  }//GEN-LAST:event_aboutActionPerformed

  private void newGameActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_newGameActionPerformed
  {//GEN-HEADEREND:event_newGameActionPerformed
    // If running, stop execution.
    if(timer != null)
    {
      running = false;
      timer.cancel();
      runButton.setText("Run");
    } // if
    
    // Set the territory size.
    territoryGrid.setSize(size);
    
    // Reset the generation count.
    generationLabel.setText("Generation 0");
    generation = 0;
    
    // Clear the info.
    playerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Player"));
    stratCombo.setEnabled(false);
    selectedPlayer = null;
    playerPayoffField.setText("");
    
    // Repack because the size of the window has changed.
    pack();
  }//GEN-LAST:event_newGameActionPerformed

  private void helpActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_helpActionPerformed
  {//GEN-HEADEREND:event_helpActionPerformed
// TODO add your handling code here:
  }//GEN-LAST:event_helpActionPerformed

  private void stepActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_stepActionPerformed
  {//GEN-HEADEREND:event_stepActionPerformed
    territoryGrid.step();
    generation++;
    generationLabel.setText("Generation " + generation);
    
    // Clear the info.
    playerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Player"));
    stratCombo.setEnabled(false);
    selectedPlayer = null;
    playerPayoffField.setText("");
  }//GEN-LAST:event_stepActionPerformed
  
  // Method to run through one step of execution.
  private void stepThrough()
  {
    territoryGrid.step();
    generation++;
    generationLabel.setText("Generation " + generation);
    
    // Clear the info.
    playerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Player"));
    stratCombo.setEnabled(false);
    selectedPlayer = null;
    playerPayoffField.setText("");
    
    timer.schedule(new java.util.TimerTask()
    {
      public void run()
      {
        stepThrough();
      } // run
    }, runSpeed);
  } // stepThrough
  
  /**
   * Get the Player for which the button has been pressed in a 
   * territory. In this instance, update its information.
   *
   * @param plyr The Player
   * @param i The Player's row
   * @param j The Player's column
   */
  public void processButton(Player plyr, int i, int j)
  {
    // Update all the info.
    selectedPlayer = plyr;
    row = i;
    column = j;
    playerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Player " + i + ", " + j));
    stratCombo.setEnabled(true);
    stratCombo.setSelectedItem(selectedPlayer.getPlayersStrategy());
    playerPayoffField.setText("" + plyr.getPayoff());
  } // processButton
  
    /**
     * Simply create and display the Evolution GUI.
     *
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
      java.awt.EventQueue.invokeLater(new Runnable()
      {
        public void run()
        {              
          new EvolutionGUI().setVisible(true);
        } // run
      });
    } // main
    
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem about;
  private javax.swing.JRadioButton eightNeighRadioButton;
  private javax.swing.JMenu file;
  private javax.swing.JRadioButton fourNeighRadioButton;
  private javax.swing.JLabel generationLabel;
  private javax.swing.JMenu help;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.ButtonGroup neighbourButtonGroup;
  private javax.swing.JPanel neighbourPanel;
  private javax.swing.JMenuItem newGame;
  private javax.swing.JPanel playerPanel;
  private javax.swing.JLabel playerPayoff;
  private javax.swing.JTextField playerPayoffField;
  private javax.swing.JMenuItem quit;
  private javax.swing.JButton runButton;
  private javax.swing.JLabel sizeLabel;
  private javax.swing.JLabel sizeLabel2;
  private javax.swing.JSlider sizeSlider;
  private javax.swing.JLabel speedLabel;
  private javax.swing.JLabel speedLabel2;
  private javax.swing.JSlider speedSlider;
  private javax.swing.JButton step;
  private javax.swing.JComboBox stratCombo;
  private javax.swing.JLabel stratLabel;
  private TerritoryGrid territoryGrid;
  private javax.swing.JPanel variablePanel;
  private javax.swing.JLabel wLabel;
  private javax.swing.JLabel wLabel2;
  private javax.swing.JSlider wSlider;
  // End of variables declaration//GEN-END:variables
    
}
