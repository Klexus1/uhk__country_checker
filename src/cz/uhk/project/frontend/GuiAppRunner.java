package cz.uhk.project.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GuiAppRunner extends JFrame {

    public static void main(String[] args) {
        new GuiAppRunner().setVisible(true);
    }

    /**
     * Create app gui window
     */
    public GuiAppRunner(){
        super("COUNTRY CHECKER");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setBackground(Color.LIGHT_GRAY);
    }
}
