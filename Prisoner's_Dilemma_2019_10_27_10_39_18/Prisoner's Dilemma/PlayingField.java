/**
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * part PlayingField
 * 
 * @author Daan Boelhouwers - 1457152
 * @author Richard Farla - 1420380
 * assignment group 38
 * 
 * assignment copyright Kees Huizing
 */

import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class PlayingField extends JPanel implements ActionListener {
    
    private Patch[][] grid;
    
    private double alpha; // defection award factor
    
    private Timer timer;
    
    private int size; // size of grid
    
    private boolean preferOwnStrat; // if true patch will choose for own strat when score is equal to neighbour
    
    private int speed; // sets fps
    private int clock; // ticks while timer is active
    
    // random number generator
    private static final long SEED = 37L; // seed for random number generator; any number goes
    public static final Random random = new Random( SEED );   
    
    // constructor
    PlayingField(int s) {
        this.size = s;
        this.clock = 0;
        this.alpha = 1;
        this.speed = 60; // sets speed to init slider position
        this.preferOwnStrat = false;
        this.timer = new Timer(1000/60, this);
        this.fill();
        this.reset();
        this.setLayout(new GridLayout(size, size));
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                this.add(grid[a][b]);
                findNeighbours(grid[a][b], a, b);
            }
        }
    }
    
    // fills grid with patches that have a random chance to either C or D 
    public void fill() {
        grid = new Patch[size][size];
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                grid[a][b] = new Patch();
            }
        }
    }
    
    // set boolean prefer own strategy 
    public void setPreferOwnStrat(boolean b) {
        this.preferOwnStrat = b;
    }
    
    // randomizes all patches
    public void reset() {
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                grid[a][b].setCooperating(random.nextBoolean());
                grid[a][b].setChanged(false);
            }
        }
        repaint();
    }
    
    // stores neighbours in patch
    public void findNeighbours(Patch p, int x, int y) {
        int nx;
        int ny;
        
        for (int i = 0; i < 9; i++) {
            nx = (x - 1 + ((int) i/3)) % size;
            if (nx < 0) {
                nx += size;
            }
            
            ny = (y - 1 + (i%3)) % size;
            if (ny < 0) {
                ny += size;
            }
            
            p.setNeighbours(i, grid[nx][ny]);
        }        
    }
    
    
    // sets speed (fps)
    public void setSpeed(int s) {
        this.speed = 60/s;
    }
    
    // start timer
    public void startTimer() {
        this.timer.start();
    }
    
    // stop timer
    public void stopTimer() {
        this.timer.stop();
    }
    
    /**
     * calculate and execute one step in the simulation 
     */
    public void step() {
        for (int a = 0; a < size; a++) {
            for (int b = 0; b < size; b++) {
                grid[a][b].nextStrat(this.alpha, this.preferOwnStrat);
                grid[a][b].willChange();
            }
        }
        for (int m = 0; m < size; m++) {
            for (int n = 0; n < size; n++) {
                grid[m][n].setCooperating(grid[m][n].getNextStrat());
            }
        }
        repaint();
    }
    
    // sets alpha
    public void setAlpha( double alpha ) {
        this.alpha = alpha;
    }
    
    // returns alpha
    public double getAlpha() {
        return alpha; 
    }
    
    // return grid as 2D array of booleans
    // true for cooperators, false for defectors
    // precondition: grid is rectangular, has non-zero size and elements are non-null
    public boolean[][] getGrid() {
        boolean[][] resultGrid = new boolean[grid.length][grid[0].length];
        for (int x = 0; x < grid.length; x++ ) {
            for (int y = 0; y < grid[0].length; y++ ) {
                resultGrid[x][y] = grid[x][y].isCooperating();
            }
        }
        
        return resultGrid; 
    }
    
    // sets grid according to parameter inGrid
    // a patch should become cooperating if the corresponding
    // item in inGrid is true
    public void setGrid( boolean[][] inGrid) {
        for (int a = 0; (a < inGrid.length)&&(a < this.size); a++) {
            for (int b = 0; (b < inGrid[0].length)&&(b < this.size); b++) {
                grid[a][b].setCooperating(inGrid[a][b]);
            }
        }
        repaint();
    }   
    
    @Override public void actionPerformed(ActionEvent e) {
        // every tick of timer adds 1 to clock, then speed is checked to see if step should be run
        if (e.getSource() == timer) {
            clock++;
            if (clock%speed == 0) {
                step();
            }
        }
    }
}

