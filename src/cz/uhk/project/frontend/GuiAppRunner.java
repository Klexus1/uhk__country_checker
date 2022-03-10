package cz.uhk.project.frontend;

import cz.uhk.project.backend.Country;
import cz.uhk.project.backend.CountryManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.List;
import java.util.Objects;
import java.util.logging.*;

/**
 * Class that spawns the app frame windown in which everything is inserted
 */

public class GuiAppRunner extends JFrame {
//        https://www.youtube.com/watch?v=9qwmQ--pksM&t=980s

    private final static Logger logger = Logger.getLogger(GuiAppRunner.class.getName());

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
        logger.info("Starting the main block.");
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
        logger.info("Starting the GuiAppRunner.");
        init();
    }

    private void init() {
        logger.info("Initializing GuiAppRunner JFrame instance.");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.LIGHT_GRAY);


        addToolButtons();
        addFields();
        addTextArea();
        addFlowButtons();

        pack();
    }

    private void addToolButtons() {
        logger.info("Adding tool buttons.");
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
        textArea.setEditable(false);
        panelTextArea.add(textArea);
    }

    private void addFields() {
        logger.info("Adding fields.");
        labelCountryName.setLabelFor(fieldCountryName);
        labelCountryCapital.setLabelFor(fieldCountryCapital);
        labelCountryInhabitants.setLabelFor(fieldCountryInhabitants);
        labelCountryInhabitants.setToolTipText("Zadejte číslo bez mezer");
        labelCountryArea.setLabelFor(fieldCountryArea);
        labelCountryArea.setToolTipText("Zadejte v km2");

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
        logger.info("Adding button listeners.");

        addClearConfirmButtonListeners();
        addAddButtonListeners();
        addListButtonListeners();
    }

    private void addListButtonListeners() {
        logger.info("Adding List button listeners.");
        ActionListener ListCountriesActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                logger.info("Received request to list countries.");
                textArea.setText("");
                List<Country> countries = CountryManager.countries;
                if (countries.size() == 0){
                    logger.info("No entries found for countries in country lsit.");
                    textArea.append("Žádné záznamy.");
                } else {
                    for (Country country : countries){
                        textArea.append(country.getName() + "\n");
                    }
                }
            }
        };
        buttonList.addActionListener(ListCountriesActionListener);
    }

    private void addAddButtonListeners() {
        logger.info("Adding Add button listeners.");
        ActionListener addCountryActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Country c = createNewCountry();
                CountryManager.countries.add(c);
                clearFields();
            }
        };

        buttonCreate.addActionListener(addCountryActionListener);
    }

    private void addClearConfirmButtonListeners() {
        logger.info("Adding Clear and Confirm button listeners.");
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

    public void clearFields () {
        fieldCountryName.setText("");
        fieldCountryCapital.setText("");
        fieldCountryInhabitants.setText("");
        fieldCountryArea.setText("");
    }

    public Country createNewCountry() {
        logger.info("Entering the Create new Country block.");
        setVisible(true);
            if (
                    !Objects.equals(fieldCountryName.getText(), "") &&
                    !Objects.equals(fieldCountryCapital.getText(), "") &&
                    !Objects.equals(fieldCountryArea.getText(), "") &&
                    !Objects.equals(fieldCountryInhabitants.getText(), "")
            ) {
                logger.info("Creating new country with all fields.");
                System.out.println("Creating new Country");
                return new Country(
                        fieldCountryName.getText(),
                        fieldCountryCapital.getText(),
                        Long.parseLong(fieldCountryArea.getText()),
                        Double.parseDouble(fieldCountryInhabitants.getText())
                );
            } else if(
                    !Objects.equals(fieldCountryName.getText(), "")
            ) {
                logger.info("Creating new country with just country name.");
                return new Country(fieldCountryName.getText());
            }
        logger.info("No country created.");
        return null;
    }

    /**
     * In case that the name of the country that is being edited is already known, update the existing record
     * In case that the name of the country is not known, create new record
     *
     * @param editedCoutnry - country to be edited
     * @return Country
     */
    public Country editCountry(Country editedCoutnry) {
        logger.info("Entering the Edit new Country block.");


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
                    logger.info("A country with this name already found.");
                }
            }
            if (!matchFound) {
                logger.info("A country with this name wasn't found.");
                if (
                        !Objects.equals(fieldCountryCapital.getText(), "") &&
                        !Objects.equals(fieldCountryArea.getText(), "") &&
                        !Objects.equals(fieldCountryInhabitants.getText(), "")
                ) {
                    logger.info("All country fields filled in - creating new country");
                    return new Country(
                            fieldCountryName.getText(),
                            fieldCountryCapital.getText(),
                            Long.parseLong(fieldCountryArea.getText()),
                            Double.parseDouble(fieldCountryInhabitants.getText())
                    );
                } else {
                    logger.info("Only a country name found - creating new country");
                    return new Country(fieldCountryName.getText());
                }
            }
        } else {
            logger.info("Neither a matching country found nor the fields filled in correctly - returning null");
            return null;
        }
        return null; // TODO: 20.02.2022 investigate why
    }
    
}
