/**
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * part Patch
 * 
 * @author Daan Boelhouwers - 1457152
 * @author Richard Farla - 1420380
 * assignment group 38
 * 
 * assignment copyright Kees Huizing
 */
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

class Patch extends JPanel implements MouseListener {
    private boolean cooperating; // true when C, false when D
    
    private boolean nextStrategy; // strategy for next round
    
    private boolean changed; // true if patch changed it's strategy this round
    
    // array of neighbour patches (reading order)
    // size of array is 8 because each patch has 8 neighbours
    private Patch[] neighbours = new Patch[8]; 
    
    private double score; // score of patch
    
    private int height; // height of panel
    private int width; // width of panel
    
    // constructor 
    Patch() {
        this.addMouseListener(this);
    }
    
    // checks if current strategie and next strategy differ and adjusts changed
    void willChange() {
        if (cooperating == nextStrategy) {
            this.changed = false;
        } else {
            this.changed = true;
        }
    }
    
    // set the changed property of patch
    void setChanged(boolean b) {
        this.changed = b;
    }

    // returns true if and only if patch will cooperate next round
    boolean getNextStrat() {
        return nextStrategy;
    }
    
    // returns true if and only if patch is cooperating
    boolean isCooperating() {
        return cooperating;
    }
    
    // set strategy to C if isC is true and to D if false
    void setCooperating(boolean isC) {
        if (isC) {
            this.cooperating = true;
        } else {
            this.cooperating = false;
        }
    }
    
    // change strategy from C to D and vice versa
    void toggleStrategy() {
        if (this.cooperating) {
            this.cooperating = false;
        } else {
            this.cooperating = true;
        }
        this.changed = true;
        repaint();
    }
    
    // return score of this patch in current round
    double getScore(double alpha) {
        double sc = 0;
        for (Patch p : neighbours) {
            if (p.isCooperating()) {
                sc++;
            }
        }
        if (!this.isCooperating()) {
            sc *= alpha;
        }
        return sc; 
    }
    
    // get height and width
    void measureSize() {
        height = this.getHeight();
        width = this.getWidth();        
    }
    
    // store neighbour in array at place i
    void setNeighbours(int i, Patch nb) {
        if (i < 4) {
            this.neighbours[i] = nb;
        }
        else if (i > 4) {
            this.neighbours[i-1] = nb;
        }
    }
    
    // calculate strat for next round, patch will choose own strat over neighbour strat when scores are equal and b true
    void nextStrat(double alpha, boolean b) {
        ArrayList<Patch> highscore = new ArrayList<Patch>();
        highscore.add(this);
        for (Patch p : neighbours) {
            
            boolean equalScores = p.getScore(alpha) == highscore.get(0).getScore(alpha);
            boolean notOwnScore = !b || ( this.getScore(alpha) < p.getScore(alpha) );
            
            // if score of p is bigger than current highscore, replace it
            if (p.getScore(alpha) > highscore.get(0).getScore(alpha)) {
                highscore.clear();
                highscore.add(p);
            }
            
            // else if score is equal to current score, add it to array
            else if (equalScores && notOwnScore) {
                highscore.add(p);
            }
        }
        
        // pick random patch from array of highscoring patches
        int listLength = highscore.size();
        int randInt = PlayingField.random.nextInt(listLength); 
        this.nextStrategy = highscore.get(randInt).isCooperating();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        this.measureSize();
        
        // paint backgroudn
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        
        // paint roundrect, color depending on current strategy
        if (changed) {
            if (this.cooperating) {
                g.setColor(new Color(51, 153, 255)); // light blue
            } else {
                g.setColor(new Color(255, 153, 51)); // orange
            }
        } else if (this.cooperating) {
            g.setColor(new Color(51, 51, 255)); // blue
        } else {
            g.setColor(new Color(255, 51, 51)); // red
        }
        g.fillRoundRect(0, 0, width, height, width/2, height/2);
    }
    
    // change strategy when clicked
    @Override 
    public void mouseClicked(MouseEvent e) {
        this.toggleStrategy();
    }
    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
