/**
 * Assignment 6 -- Prisoner's Dilemma -- 2ip90
 * main class
 * 
 * @author Daan Boelhouwers - 1457152
 * @author Richard Farla - 1420380
 * assignment group 38
 * 
 * assignment copyright Kees Huizing
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PrisonersDilemma extends JPanel implements ActionListener, ChangeListener {
    private final int SIZE = 50;
    private JFrame frame;
    private JPanel sliderPanel;
    private JPanel buttonPanel;
    private PlayingField field;
    private JButton jbStartStop;
    private JButton jbReset;
    private JCheckBox jcbTactic;
    private JSlider alphaSlider;
    private JSlider speedSlider;
    private JLabel speedlbl;
    private JLabel alphalbl;
    
    void buildGUI() {
        SwingUtilities.invokeLater( () -> {
            
            // initialize everything
            frame = new JFrame("FREE HONGKONG");
            sliderPanel = new JPanel();
            buttonPanel = new JPanel();
            field = new PlayingField(SIZE);
            jbStartStop = new JButton("Go");
            jbReset = new JButton("Reset");
            jcbTactic = new JCheckBox();
            alphaSlider = new JSlider();
            speedSlider = new JSlider();
            speedlbl = new JLabel("Speed: 1 fps");
            alphalbl = new JLabel("Alpha: 1.0");
            
            // add this to JFrame
            frame.add(this, BorderLayout.SOUTH);
            
            // set layout to GridLayout
            this.setLayout(new GridLayout(2, 1));
            
            // add sliderpanel and buttonpanel to this
            this.add(sliderPanel);
            this.add(buttonPanel);
            
            // create alpha slider and add it to sliderPanel
            sliderPanel.add(alphalbl);
            
            alphaSlider.setMinimum(0);
            alphaSlider.setMaximum(30);
            alphaSlider.setValue(10);
            alphaSlider.setMajorTickSpacing(10);
            alphaSlider.setMinorTickSpacing(1);
            alphaSlider.setPaintTicks(true);
            alphaSlider.addChangeListener(this);
            sliderPanel.add(alphaSlider);
            
            // create simulation speed slider and add it to sliderPanel
            sliderPanel.add(speedlbl);
            
            speedSlider.setMinimum(1);
            speedSlider.setMaximum(60);
            speedSlider.setValue(1);
            speedSlider.setMajorTickSpacing(10);
            speedSlider.setMinorTickSpacing(5);
            speedSlider.setPaintTicks(true);
            speedSlider.addChangeListener(this);
            sliderPanel.add(speedSlider);
            
            // create start / stop button and add it to buttonPanel
            buttonPanel.add(jbStartStop);
            jbStartStop.addActionListener(this);
            
            // create reset button and add it to buttonPanel
            buttonPanel.add(jbReset);
            jbReset.addActionListener(this);
            
            // create prefer own strategy checkbox and add it to buttonPanel (even though its not a button)
            buttonPanel.add(jcbTactic);
            jcbTactic.addActionListener(this);
            jcbTactic.setText("Prefer own strategy");
            
            frame.add(field,BorderLayout.CENTER);
            frame.setSize(900, 900);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true); 
        } );
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        // start / stop button was pressed
        if (e.getSource() == jbStartStop) {
            if (jbStartStop.getText() == "Go") {
                jbStartStop.setText("Pause");
                field.startTimer();
            } else {
                jbStartStop.setText("Go");
                field.stopTimer();
            }
        }
        
        // reset button was pressed
        if (e.getSource() == jbReset) {
            field.reset();
        }
        
        // strategy checkbox was clicked
        if (e.getSource() == jcbTactic) {
            if (jcbTactic.isSelected()) {
                field.setPreferOwnStrat(true);
            } else {
                field.setPreferOwnStrat(false);
            }
        }
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        
        // alpha slider was touched
        if (e.getSource() == alphaSlider) {
            field.setAlpha(alphaSlider.getValue()/10.0);
            alphalbl.setText("Alpha: " + field.getAlpha());
        }
        
        // speed slider was touched
        if (e.getSource() == speedSlider) {
            field.setSpeed(speedSlider.getValue());
            speedlbl.setText("Speed: " + speedSlider.getValue() + " fps");
        }
    }
    
    public static void main( String[] a ) {
        (new PrisonersDilemma()).buildGUI();
    }
}
