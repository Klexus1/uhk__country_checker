package cz.uhk.project.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class GuiAppRunner extends JFrame {
    JButton buttonList = new JButton("Zobrazit seznam");
    JButton buttonCreate = new JButton("Přidat záznam");
    JButton buttonUpdate = new JButton("Aktualizovat záznam");
    JButton buttonDelete = new JButton("Odstranit záznam");

    JTextField fieldCountryName = new JTextField(15);
    JTextField fieldCountryCapital = new JTextField(15);
    JTextField fieldCountryInhabitants = new JTextField(15);
    JTextField fieldCountryArea = new JTextField(15);

    JLabel labelCountryName = new JLabel("Název země");
    JLabel labelCountryCapital = new JLabel("Hlavní město");
    JLabel labelCountryInhabitants = new JLabel("Počet obyvatel");
    JLabel labelCountryArea = new JLabel("Rozloha");

    JPanel panelActions = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel panelInputs = new JPanel(new FlowLayout(FlowLayout.CENTER));

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

        addButtons();

        labelCountryName.setLabelFor(fieldCountryName);
        labelCountryCapital.setLabelFor(fieldCountryCapital);
        labelCountryInhabitants.setLabelFor(fieldCountryInhabitants);
        labelCountryArea.setLabelFor(fieldCountryArea);

        labelCountryName.setDisplayedMnemonic('J');
        labelCountryCapital.setDisplayedMnemonic('H');
        labelCountryInhabitants.setDisplayedMnemonic('P');
        labelCountryArea.setDisplayedMnemonic('R');

        panelActions.setBorder(new EmptyBorder(10,0,40,0));
        panelInputs.setBorder(new EmptyBorder(10,20,20,20));
        add(panelActions, BorderLayout.NORTH);
        add(panelInputs, BorderLayout.CENTER);

        pack();
    }

    private void addButtons(){
        panelActions.add(buttonList);
        panelActions.add(buttonCreate);
        panelActions.add(buttonUpdate);
        panelActions.add(buttonDelete);

        panelInputs.add(labelCountryName);
        panelInputs.add(fieldCountryName);

        panelInputs.add(labelCountryCapital);
        panelInputs.add(fieldCountryCapital);

        panelInputs.add(labelCountryInhabitants);
        panelInputs.add(fieldCountryInhabitants);

        panelInputs.add(labelCountryArea);
        panelInputs.add(fieldCountryArea);

    }
}
