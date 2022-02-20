package cz.uhk.project.frontend;

import cz.uhk.project.backend.Country;
import cz.uhk.project.backend.CountryManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

public class GuiAppRunner extends JFrame {
//        https://www.youtube.com/watch?v=9qwmQ--pksM&t=980s

    private final JPanel panelActions = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelInputs = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelTextArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
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

    private final JTextArea textArea = new JTextArea(10,20);

    private final JLabel labelCountryName = new JLabel("Název země");
    private final JLabel labelCountryCapital = new JLabel("Hlavní město");
    private final JLabel labelCountryInhabitants = new JLabel("Počet obyvatel");
    private final JLabel labelCountryArea = new JLabel("Rozloha");

    private boolean confirmed = false;

    public static void main(String[] args) {
        new GuiAppRunner().setVisible(true);
//        GuiAppRunner rnr = new GuiAppRunner();
//        Country c = rnr.createNewCountry();
//        if (c != null){
//          System.out.println(c.getName());
//        }
    }

    /**
     * Create app gui window
     */
    public GuiAppRunner() {
        super("COUNTRY CHECKER");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.LIGHT_GRAY);


        addToolButtons();
        addFields();
        addTextArea();
        addFlowButtons();

        pack();
    }

    private void addToolButtons() {
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

    private void addTextArea() {
        panelTextArea.add(textArea);
    }

    private void addFields() {
        labelCountryName.setLabelFor(fieldCountryName);
        labelCountryCapital.setLabelFor(fieldCountryCapital);
        labelCountryInhabitants.setLabelFor(fieldCountryInhabitants);
        labelCountryArea.setLabelFor(fieldCountryArea);

        labelCountryName.setDisplayedMnemonic('J');
        labelCountryCapital.setDisplayedMnemonic('H');
        labelCountryInhabitants.setDisplayedMnemonic('P');
        labelCountryArea.setDisplayedMnemonic('R');

        panelActions.setBorder(new EmptyBorder(10, 0, 40, 0));
        panelInputs.setBorder(new EmptyBorder(10, 20, 30, 20));
        panelTextArea.setBorder(new EmptyBorder(10, 20, 30, 20));

        add(panelActions, BorderLayout.NORTH);
        add(panelInputs, BorderLayout.CENTER);
        add(panelTextArea, BorderLayout.WEST);
        add(panelFlow, BorderLayout.SOUTH);
    }

    private void addFlowButtons() {
        panelFlow.add(buttonConfirm);
        panelFlow.add(buttonClear);

        addButtonListeners();

        getRootPane().setDefaultButton(buttonConfirm);

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

    public Country createNewCountry() {
        fieldCountryName.setText("");
        fieldCountryCapital.setText("");
        fieldCountryInhabitants.setText("");
        fieldCountryArea.setText("");
        setVisible(true);

        if (confirmed) {
            if (!Objects.equals(fieldCountryCapital.getText(), "")) {
                return new Country(
                        fieldCountryName.getText(),
                        fieldCountryCapital.getText(),
                        Long.parseLong(fieldCountryArea.getText()),
                        Double.parseDouble(fieldCountryInhabitants.getText())
                );
            } else {
                return new Country(fieldCountryName.getText());
            }
        } else {
            return null;
        }
    }

    /**
     * In case that the name of the country that is being edited is already known, update the existing record
     * In case that the name of the country is not known, create new record
     *
     * @param editedCoutnry - country to be edited
     * @return Country
     */
    public Country editCountry(Country editedCoutnry) {
        fieldCountryName.setText(editedCoutnry.getName());
        fieldCountryCapital.setText(editedCoutnry.getCapital());
        fieldCountryInhabitants.setText(Long.toString(editedCoutnry.getInhabitants()));
        fieldCountryArea.setText(Double.toString(editedCoutnry.getArea()));
        setVisible(true);
        if (confirmed) {
            String submittedName = fieldCountryName.getText();
            boolean matchFound = false;
            for (Country country : CountryManager.countries) {
                if (Objects.equals(country.getName(), submittedName)) {
                    country.setCapital(fieldCountryCapital.getText());
                    country.setInhabitants(Long.parseLong(fieldCountryArea.getText()));
                    country.setArea(Double.parseDouble(fieldCountryInhabitants.getText()));
                    matchFound = true;
                }
            }
            if (!matchFound) {
                if (!Objects.equals(fieldCountryCapital.getText(), "")) {
                    return new Country(
                            fieldCountryName.getText(),
                            fieldCountryCapital.getText(),
                            Long.parseLong(fieldCountryArea.getText()),
                            Double.parseDouble(fieldCountryInhabitants.getText())
                    );
                } else {
                    return new Country(fieldCountryName.getText());
                }
            }
        } else {
            return null;
        }
        return null; // TODO: 20.02.2022 investigate why
    }
    
}
