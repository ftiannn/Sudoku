package sudoku;
 
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import java.awt.*; // Uses AWT's LayoutManagers
import java.awt.event.*; // Uses AWT's Event Handlers
import java.util.Arrays;
import java.util.Random;
 
import javax.swing.*; // Uses Swing's Container/Components
 
 
 
public class Sudoku extends JFrame {
      
       public enum SoundEffect {
                 Gameover("death.wav"),
                 Start("ready.wav"),
                 Correct("mtt_congrats.wav");
                
                 // Nested class for specifying volume
                 public static enum Volume {
                    MUTE, LOW, MEDIUM, HIGH
                 }
                
                 public static Volume volume = Volume.LOW;
                
                 // Each sound effect has its own clip, loaded with its own sound file.
                 private Clip clip;
                
                 // Constructor to construct each element of the enum with its own sound file.
                 SoundEffect(String soundFileName) {
                    try {
                       // Use URL (instead of File) to read from disk and JAR.
                       URL url = this.getClass().getClassLoader().getResource(soundFileName);
                       // Set up an audio input stream piped from the sound file.
                       AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
                       // Get a clip resource.
                       clip = AudioSystem.getClip();
                       // Open audio clip and load samples from the audio input stream.
                       clip.open(audioInputStream);
                    } catch (UnsupportedAudioFileException e) {
                       e.printStackTrace();
                    } catch (IOException e) {
                       e.printStackTrace();
                    } catch (LineUnavailableException e) {
                       e.printStackTrace();
                    }
                 }
                
                 // Play or Re-play the sound effect from the beginning, by rewinding.
                 public void play() {
                    if (volume != Volume.MUTE) {
                       if (clip.isRunning())
                          clip.stop();   // Stop the player if it is still running
                       clip.setFramePosition(0); // rewind to the beginning
                       clip.start();     // Start playing
                    }
                 }
                
                 
                 // Optional static method to pre-load all the sound files.
                 static void init() {
                    values(); // calls the constructor for all the elements
                 }
              }
      
       public class StatusBar extends JLabel {
              /** Creates a new instance of StatusBar */
              public StatusBar() {
                     super();
                     super.setPreferredSize(new Dimension(100, 16));
                     setMessage("Ready");
              }
 
              public void setMessage(String message) {
                     setText(" " + message);
              }
       }
 
       public class Level extends JFrame {
              private JLabel tag1;
              private JButton butt1;
              private JButton butt2;
              private JButton butt3;
 
              public Level() {
                     super("Difficulty");
                     setLayout(new FlowLayout());
                     setSize(250, 130);
                     setVisible(true);
                     setLocationRelativeTo(null);
                     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                     tag1 = new JLabel("Please Select Your Difficulty.");
                     add(tag1);
                     butt1 = new JButton("Easy.");
                     butt1.setToolTipText("Beginner");
                     add(butt1);
                     butt2 = new JButton("Medium.");
                     butt2.setToolTipText("Intermediate");
                     add(butt2);
                     butt3 = new JButton("Hard.");
                     butt3.setToolTipText("Expert");
                     add(butt3);
                     thehandler handler = new thehandler();
                     butt1.addActionListener(handler);
                     butt2.addActionListener(handler);
                     butt3.addActionListener(handler);
              }
 
              private class thehandler implements ActionListener {
                     public void actionPerformed(ActionEvent event) {
                           String string = "";
 
                           if (event.getSource() == butt1) {
                                  dispose();
                                  SoundEffect.Start.play();
                                  SoundEffect.init();
                                  SoundEffect.volume = SoundEffect.Volume.LOW;
                                  string = "Have fun!";
                                  new Sudoku();
                           } else if (event.getSource() == butt2) {
                                  dispose();
                                  SoundEffect.Start.play();
                                  SoundEffect.init();
                                  SoundEffect.volume = SoundEffect.Volume.LOW;
                                  string = "Good luck!";
                                  new Sudoku();
                           } else if (event.getSource() == butt3) {
                                  dispose();
                                  SoundEffect.Start.play();
                                  SoundEffect.init();
                                  SoundEffect.volume = SoundEffect.Volume.LOW;
                                  string = "All the best!";
                                  new Sudoku();
                           }
                           JOptionPane.showMessageDialog(null, string);
                     }
              }
       }
 
      
       private int[][] puzzle = {
                     {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }, {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }, {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }, {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }, {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }, {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }, {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }, {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }, {
                           0, 0, 0, 0, 0, 0, 0, 0, 0
                     }
              };
      
       public void RandomiseBoard() {
              Random random = new Random();
              int number = random.nextInt(10);
              while (number == 0) {
                     number = random.nextInt(10);
              }
              int count = number;
              int num1 = number;
              int num2 = number;
              int num3 = number;
 
              for (int col = 0; col < 7; col = col + 3) {
 
                     puzzle[0][0 + col] = count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[2][2 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[1][0 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[0][2 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[2][1 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[1][2 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[0][1 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[2][0 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[1][1 + col] = ++count;
 
                     num1++;
                     if (num1 == 10) {
                           count = 1;
                           num1 = 1;
                     } else count = num1;
              }
 
              if (number == 9) {
                     count = 3;
              } else if (number == 8) {
                     count = 2;
              } else if (number == 7) {
                     count = 1;
              } else count = number + 3;
              num2 = count;
              for (int col1 = 0; col1 < 7; col1 = col1 + 3) {
 
                     puzzle[3][0 + col1] = count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[5][2 + col1] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[4][0 + col1] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[3][2 + col1] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[5][1 + col1] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[4][2 + col1] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[3][1 + col1] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[5][0 + col1] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[4][1 + col1] = ++count;
 
                     num2++;
                     if (num2 == 10) {
                           count = 1;
                           num2 = 1;
                     } else count = num2;
              }
              if (number == 9) {
                     count = 6;
              } else if (number == 8) {
                     count = 5;
              } else if (number == 7) {
                     count = 4;
              } else if (number == 6) {
                     count = 3;
              } else if (number == 5) {
                     count = 2;
              } else if (number == 4) {
                     count = 1;
              } else count = number + 6;
              num3 = count;
 
              for (int col = 0; col < 7; col = col + 3) {
 
                     puzzle[6][0 + col] = count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[8][2 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[7][0 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[6][2 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[8][1 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[7][2 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[6][1 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[8][0 + col] = ++count;
                     if (count >= 9) {
                           count = 0;
                     }
                     puzzle[7][1 + col] = ++count;
 
                     num3++;
                     if (num3 == 10) {
                           count = 1;
                           num3 = 1;
                     } else count = num3;
              }
 
 
       }
 
 
 
       // For testing, turn on only 2 cells.
       private boolean[][] masks = {
              {
                     false, false, false, false, false, true, false, false, false
              }, {
                     false, false, false, false, false, false, false, false, true
              }, {
                     false, false, false, false, false, false, false, false, false
              }, {
                     false, false, false, false, false, false, false, false, false
              }, {
                     false, false, false, false, false, false, false, false, false
              }, {
                     false, false, false, false, false, false, false, false, false
              }, {
                     false, false, false, false, false, false, false, false, false
              }, {
                     false, false, false, false, false, false, false, false, false
              }, {
                     false, false, false, false, false, false, false, false, false
              }
       };
 
       // Named constants for the various dimensions
       public static final int SIZE = 9; // Size of the board
       public static final int SUBBLOCK_SIZE = 3; // Size of the sub-block
       public static final int CELL_SIZE = 60; // Cell width/height in pixel
       public static final int CANVAS_WIDTH = CELL_SIZE * SIZE;
       public static final int CANVAS_HEIGHT = CELL_SIZE * SIZE;
      
 
       // Game board
       private JTextField[][] tfCells = new JTextField[9][9]; // TextFields
       private int[][] cells = new int[9][9]; // Number in TextFields: 1-9, or 0 if
       // empty
 
       // Puzzle to be solved and the mask (which can be used to control the
       // difficulty level).
       // Hard coded here. Extra credit for automatic puzzle generation with
       // various difficulty levels/
       /** * Constructor to setup the game and the UI Components */
      
       public Sudoku() {
              Container cp = getContentPane();
              cp.setLayout(new BorderLayout());
              RandomiseBoard();
              JPanel smallpanel1 = new JPanel(new GridLayout(3, 3));
              smallpanel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel smallpanel2 = new JPanel(new GridLayout(3, 3));
              smallpanel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel smallpanel3 = new JPanel(new GridLayout(3, 3));
              smallpanel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel smallpanel4 = new JPanel(new GridLayout(3, 3));
              smallpanel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel smallpanel5 = new JPanel(new GridLayout(3, 3));
              smallpanel5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel smallpanel6 = new JPanel(new GridLayout(3, 3));
              smallpanel6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel smallpanel7 = new JPanel(new GridLayout(3, 3));
              smallpanel7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel smallpanel8 = new JPanel(new GridLayout(3, 3));
              smallpanel8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel smallpanel9 = new JPanel(new GridLayout(3, 3));
              smallpanel9.setBorder(BorderFactory.createLineBorder(Color.BLACK));
              JPanel BigPanel = new JPanel(new GridLayout(3, 3));
 
              BigPanel.add(smallpanel1);
              BigPanel.add(smallpanel2);
              BigPanel.add(smallpanel3);
              BigPanel.add(smallpanel4);
              BigPanel.add(smallpanel5);
              BigPanel.add(smallpanel6);
              BigPanel.add(smallpanel7);
              BigPanel.add(smallpanel8);
              BigPanel.add(smallpanel9);
 
              cp.add(BigPanel, BorderLayout.CENTER);
              JLabel statusBar = new StatusBar();
              cp.add(statusBar, BorderLayout.PAGE_END);
 
              JMenuBar menuBar = new JMenuBar();
              setJMenuBar(menuBar);
              JMenu menu = new JMenu("Menu");
              menuBar.add(menu);
              JMenuItem newDifficulty = new JMenuItem("Select New Difficulty");
              menu.add(newDifficulty);
              JMenuItem reset = new JMenuItem("New Puzzle");
              menu.add(reset);
              JMenuItem exit = new JMenuItem("Exit");
              menu.add(exit);
 
              class DifficultyAction implements ActionListener {
                     public void actionPerformed(ActionEvent e) {
                           dispose();
                           new Level();
                     }
              }
 
              newDifficulty.addActionListener(new DifficultyAction());
 
              class ExitAction implements ActionListener {
                     public void actionPerformed(ActionEvent e) {
                           System.exit(0);
                     }
              }
 
              exit.addActionListener(new ExitAction());
 
              class ResetAction implements ActionListener {
                     public void actionPerformed(ActionEvent e) {
                           dispose();
                           new Level();
                     }
              }
 
              reset.addActionListener(new ResetAction());
 
              // Allocate array
              tfCells = new JTextField[SIZE][SIZE];
              cells = new int[SIZE][SIZE];
 
              // Allocate a common listener as the ActionEvent listener for all the
              // JTextFields
              InputListener listener = new InputListener();
 
              // Create 9x9 JTextFields and place on the grid
              for (int row = 0; row < SIZE; ++row) {
                     for (int col = 0; col < SIZE; ++col) {
                           tfCells[row][col] = new JTextField(); // allocate element of
                           // array
                           if (row < 3)
                                  if (col < 3)
                                         smallpanel1.add(tfCells[row][col]);
                                  if (col < 6 && col > 2)
                                         smallpanel2.add(tfCells[row][col]);
                                  if (col < 9 && col > 5)
                                         smallpanel3.add(tfCells[row][col]);
                           if (row < 6)
                                  if (col < 3 && row > 2)
                                         smallpanel4.add(tfCells[row][col]);
                                  if (col < 6 && row > 2 && col > 2)
                                         smallpanel5.add(tfCells[row][col]);
                                  if (col < 9 && row > 2 && col > 5)
                                         smallpanel6.add(tfCells[row][col]);
 
                           if (row < 9)
                                  if (col < 3 && row > 5)
                                         smallpanel7.add(tfCells[row][col]);
                                  if (col < 6 && row > 5 && col > 2)
                                         smallpanel8.add(tfCells[row][col]);
                                  if (col < 9 && row > 5 && col > 5)
                                         smallpanel9.add(tfCells[row][col]);
                                 
                           int number = puzzle[row][col];
 
                           if (masks[row][col]) {
                                  cells[row][col] = 0; // do not display
                                  tfCells[row][col].setText(""); // empty
                                  tfCells[row][col].setEditable(true);
                                  tfCells[row][col].setBackground(Color.YELLOW);
 
                                  // Add ActionEvent listener to process the input
                                  tfCells[row][col].addActionListener(listener);
                           } else {
                                  cells[row][col] = number;
                                  tfCells[row][col].setText(number + "");
                                  tfCells[row][col].setEditable(false);
                                  tfCells[row][col].setForeground(Color.BLACK);
                           }
 
                           // Beautify all the cells
                           tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
                           tfCells[row][col].setFont(new Font("CANDARA", Font.BOLD, 20));
                     }
              }
 
              cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
              // Exit program if JFrame's close-window button clicked
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              setSize(600, 600);
              setTitle("MySudoku");
              setVisible(true);
       }
 
       /** The entry main() entry method */
       public static void main(String[] args) {
              SwingUtilities.invokeLater(new Runnable() {@Override
                     public void run() {
                           new Sudoku();
                     }
              });
       }
 
       // Inner class
       private class InputListener implements ActionListener {@Override
              public void actionPerformed(ActionEvent e) {
                     // Determine the (row, col) of the JTextField that triggers the
                     // input
                     int rowSelected = -1;
                     int colSelected = -1;
 
                     // Get the source object that fired the event
                     JTextField source = (JTextField) e.getSource();
 
                     // Scan all rows and columns, and match with the source object
                     for (int row = 0; row < SIZE; ++row) {
                           for (int col = 0; col < SIZE; ++col) {
                                  if (tfCells[row][col] == source) {
                                         rowSelected = row;
                                         colSelected = col;
                                         break;
                                  }
                           }
                     }
 
                     /*
                     * 1. Get the input number via
                     * tfCells[rowSelected][colSelected].getText() 2. Update the
                     * cells[rowSelected][colSelected]
                     */
                     int numberIn = Integer.parseInt(tfCells[rowSelected][colSelected].getText());
 
                     // Check if the input is acceptable. If so, display in green;
                     // otherwise, display in red.
                     if (numberIn == puzzle[rowSelected][colSelected]) {
                           tfCells[rowSelected][colSelected].setBackground(Color.GREEN);
                           SoundEffect.Correct.play();
                           SoundEffect.init();
                           SoundEffect.volume = SoundEffect.Volume.LOW;
                           JOptionPane.showMessageDialog(null, "Congratulation!");
 
                     } else {
                           tfCells[rowSelected][colSelected].setBackground(Color.RED);
                           SoundEffect.Gameover.play();
                           SoundEffect.init();
                           SoundEffect.volume = SoundEffect.Volume.LOW;
                           JOptionPane.showMessageDialog(null, "Opps, Try Again!");
                     }
              }
       }
      
 
}