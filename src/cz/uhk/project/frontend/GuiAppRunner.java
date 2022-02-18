package cz.uhk.project.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GuiAppRunner extends JFrame {
    JButton buttonList = new JButton("Zobrazit seznam");
    JButton buttonCreate = new JButton("Přidat záznam");
    JButton buttonUpdate = new JButton("Aktualizovat záznam");
    JButton buttonDelete = new JButton("Odstranit záznam");

    JPanel panel = new JPanel();

    public static void main(String[] args) {
        new GuiAppRunner().setVisible(true);
    }

    /**
     * Create app gui window
     */
    public GuiAppRunner(){
        super("COUNTRY CHECKER");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setBackground(Color.LIGHT_GRAY);

        this.getContentPane().add(panel);
        addButtons(panel);
    }

    private void addButtons(JPanel panel){
        panel.add(buttonList);
        panel.add(buttonCreate);
        panel.add(buttonUpdate);
        panel.add(buttonDelete);
    }
}
