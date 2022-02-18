package cz.uhk.project.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class GuiAppRunner extends JFrame {

    private final JPanel panelActions = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelInputs = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private final JButton buttonList = new JButton("Zobrazit seznam");
    private final JButton buttonCreate = new JButton("Přidat záznam");
    private final JButton buttonUpdate = new JButton("Aktualizovat záznam");
    private final JButton buttonDelete = new JButton("Odstranit záznam");

    private final JButton buttonConfirm = new JButton("Ok");
    private final JButton buttonClear = new JButton("Zrušit");

    private final JTextField fieldCountryName = new JTextField(15);
    private final JTextField fieldCountryCapital = new JTextField(15);
    private final JTextField fieldCountryInhabitants = new JTextField(15);
    private final JTextField fieldCountryArea = new JTextField(15);

    private final JLabel labelCountryName = new JLabel("Název země");
    private final JLabel labelCountryCapital = new JLabel("Hlavní město");
    private final JLabel labelCountryInhabitants = new JLabel("Počet obyvatel");
    private final JLabel labelCountryArea = new JLabel("Rozloha");

    private boolean confirmed = false;

    public static void main(String[] args) {
        new GuiAppRunner().setVisible(true);
    }

    /**
     * Create app gui window
     */
    public GuiAppRunner(){
        super("COUNTRY CHECKER");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.LIGHT_GRAY);

        addToolButtons();
        addFields();
        addFlowButtons();

        pack();
    }

    private void addToolButtons(){
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

    private void addFields(){
        labelCountryName.setLabelFor(fieldCountryName);
        labelCountryCapital.setLabelFor(fieldCountryCapital);
        labelCountryInhabitants.setLabelFor(fieldCountryInhabitants);
        labelCountryArea.setLabelFor(fieldCountryArea);

        labelCountryName.setDisplayedMnemonic('J');
        labelCountryCapital.setDisplayedMnemonic('H');
        labelCountryInhabitants.setDisplayedMnemonic('P');
        labelCountryArea.setDisplayedMnemonic('R');

        panelActions.setBorder(new EmptyBorder(10,0,40,0));
        panelInputs.setBorder(new EmptyBorder(10,20,30,20));

        add(panelActions, BorderLayout.NORTH);
        add(panelInputs, BorderLayout.CENTER);
        add(panelFlow, BorderLayout.SOUTH);
    }

    private void addFlowButtons(){
        panelFlow.add(buttonConfirm);
        panelFlow.add(buttonClear);
        
        addButtonListeners();

    }

    private void addButtonListeners() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                confirmed = actionEvent.getSource() == buttonConfirm;
                setVisible(false);
            }
        };
        buttonConfirm.addActionListener(listener);
        buttonClear.addActionListener(listener);
    }

}
