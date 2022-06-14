package cz.uhk.project.frontend;

import cz.uhk.project.backend.Country;
import cz.uhk.project.backend.CountryManager;
import cz.uhk.project.data.DataHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.*;

/**
 * Class that spawns the app frame windown in which everything is inserted
 */

public class GuiAppRunner extends JFrame {
//        https://www.youtube.com/watch?v=9qwmQ--pksM&t=980s

    private final static Logger logger = Logger.getLogger(GuiAppRunner.class.getName());

    private final JPanel panelCountryActions = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelSearchAndFilter = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelActions = new JPanel();
    private final JPanel panelInputs = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelTextArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private final JPanel panelFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private final JButton buttonList = new JButton("Zobrazit seznam");
    private final JButton buttonCreate = new JButton("Přidat záznam");
    private final JButton buttonUpdate = new JButton("Aktualizovat záznam");
    private final JButton buttonDelete = new JButton("Odstranit záznam podle jména");

    private final JButton buttonSearch = new JButton("Vyhledat podle jména");
    private final JTextField searchArea = new JTextField(15);

    private final JButton buttonSave = new JButton("Uložit");
    private final JButton buttonLoad = new JButton("Načíst");

    private final JButton buttonExit = new JButton("Ukončit");
    private final JButton buttonClear = new JButton("Vyčistit pole");

    private final JTextField fieldCountryName = new JTextField(15);
    private final JTextField fieldCountryCapital = new JTextField(15);
    private final JTextField fieldCountryInhabitants = new JTextField(15);
    private final JTextField fieldCountryArea = new JTextField(15);

    private final JTextArea textArea = new JTextArea(10,40);

    private final JLabel labelCountryName = new JLabel("Název země *");
    private final JLabel labelCountryCapital = new JLabel("Hlavní město");
    private final JLabel labelCountryInhabitants = new JLabel("Počet obyvatel");
    private final JLabel labelCountryArea = new JLabel("Rozloha");

    public static void main(String[] args) {
        logger.info("Starting the main block.");
        new GuiAppRunner().setVisible(true);
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
        panelActions.setLayout(new BoxLayout(panelActions, BoxLayout.Y_AXIS));

        panelCountryActions.add(buttonList);
        panelCountryActions.add(buttonCreate);
        panelCountryActions.add(buttonUpdate);
        panelCountryActions.add(buttonDelete);

        panelSearchAndFilter.add(buttonSearch);
        panelSearchAndFilter.add(searchArea);


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

        panelActions.setBorder(new EmptyBorder(10, 0, 30, 0));
        panelInputs.setBorder(new EmptyBorder(10, 20, 30, 20));
        panelTextArea.setBorder(new EmptyBorder(10, 20, 30, 20));
        panelSearchAndFilter.setBorder(new EmptyBorder(10, 10, 20, 10));

        panelActions.add(panelSearchAndFilter);
        panelActions.add(panelCountryActions);
        add(panelActions, BorderLayout.NORTH);
        add(panelInputs, BorderLayout.CENTER);
        add(panelTextArea, BorderLayout.WEST);
        add(panelFlow, BorderLayout.SOUTH);
    }

    private void addFlowButtons() {
        panelFlow.add(buttonSave);
        panelFlow.add(buttonLoad);
        panelFlow.add(buttonClear);
        panelFlow.add(buttonExit);

        addButtonListeners();

        getRootPane().setDefaultButton(buttonExit);

    }

    private void addButtonListeners() {
        logger.info("Adding button listeners.");

        addButtonSearchListeners();

        addClearConfirmButtonListeners();
        addAddButtonListeners();
        addListButtonListeners();
        addUpdateButtonListeners();
        addDeleteButtonListeners();

        addSaveButtonListener();
        addLoadButtonListener();
    }

    public void addCountriesToTextArea(List<Country> countries){
        if (countries.size() == 0){
            logger.info("No entries found for countries in country lsit.");
            textArea.append("Žádné záznamy.");
        } else {
            for (Country country : countries){
                if (country != null){
                    String cap = !Objects.equals(country.getCapital(), null) ? country.getCapital() : "-";
                    long inh = country.getInhabitants() > 0 ? country.getInhabitants() : 0;
                    double area = country.getArea() > 0 ? country.getArea() : 0;

                    textArea.append(
                            country.getCountryId() +
                                    ".  -  " +
                                    country.getName() +
                                    " | " +
                                    cap +
                                    " | " +
                                    inh +
                                    " obyv. | " +
                                    area + " km2\n");
                }
            }
        }
    }

    public void listCountries(){
        logger.info("Received request to list countries.");
        textArea.setText("");
        List<Country> countries = CountryManager.countries;
        addCountriesToTextArea(countries);
    }


    private void addButtonSearchListeners() {
        logger.info("Adding Search button listeners.");
        ActionListener SearchButtonListener = actionEvent -> {
                String searchInput = searchArea.getText();
                List<Country> searchedCountries = CountryManager.searchCountriesByName(searchInput);
                showSearchResults(searchedCountries);
                clearSearchField();
                listCountries();
        };
        buttonList.addActionListener(SearchButtonListener);
    }

    private void showSearchResults(List<Country> countriesToShow) {
        logger.info("About to show the search results.");
        addCountriesToTextArea(countriesToShow);
    }

    private void addListButtonListeners() {
        logger.info("Adding List button listeners.");
        ActionListener ListCountriesActionListener = actionEvent -> listCountries();
        buttonList.addActionListener(ListCountriesActionListener);
    }

    private void addUpdateButtonListeners() {
        logger.info("Adding Update button listeners.");
        ActionListener UpdateCountriesActionListener = actionEvent -> {
            logger.info("Received request to edit a country.");
            editCountry();
            listCountries();
            clearFields();
        };
        buttonUpdate.addActionListener(UpdateCountriesActionListener);
    }

    private void addAddButtonListeners() {
        logger.info("Adding Add button listeners.");
        ActionListener addCountryActionListener = actionEvent -> {
            Country c = createNewCountry();
            CountryManager.countries.add(c);
            listCountries();
            clearFields();
        };
        buttonCreate.addActionListener(addCountryActionListener);
    }

    private void addDeleteButtonListeners() {
        logger.info("Adding Delete button listeners.");
        ActionListener deleteCountryActionListener = actionEvent -> {
            deleteCountry();
            listCountries();
            clearFields();
        };
        buttonDelete.addActionListener(deleteCountryActionListener);
    }

    private void deleteCountry() {
        String countryName = fieldCountryName.getText();
        if (!Objects.equals(countryName, "")){
            logger.info("Country to be deleted: " + countryName);
            boolean countryExists = CountryManager.countryExists(countryName);
            if (countryExists){
                Country c = CountryManager.getCountryFromKnownName(countryName);
                CountryManager.removeCountry(c);
            }
        }
    }

    private void addSaveButtonListener() {
        logger.info("Adding save button listener - writes countries to CountryData.txt.");
        ActionListener saveListener = actionEvent -> {
            boolean save;
            save = actionEvent.getSource() == buttonSave;
            if (save){
                DataHandler.saveDataToFile(CountryManager.countries);
            }
        };
        buttonSave.addActionListener(saveListener);
    }

    private void addLoadButtonListener() {
        logger.info("Adding load button listener - loads countries from CountryData.txt.");
        ActionListener loadListener = actionEvent -> {
            boolean load;
            load = actionEvent.getSource() == buttonLoad;
            if (load){
                DataHandler.readDataFromFile();
                listCountries();
            }
        };
        buttonLoad.addActionListener(loadListener);
    }

    private void addClearConfirmButtonListeners() {
        logger.info("Adding Exit and Clear button listeners.");
        ActionListener exitListener = actionEvent -> setVisible(false);
        ActionListener clearListener = actionEvent -> clearFields();
        buttonExit.addActionListener(exitListener);
        buttonClear.addActionListener(clearListener);
    }

    public void clearSearchField () {
        searchArea.setText("");
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
            if(
                !Objects.equals(fieldCountryName.getText(), "")
            ) {
                logger.info("Creating new country with all fields.");
                long area = 0;
                long inhab = 0;
                try{
                    area = Long.parseLong(fieldCountryArea.getText());
                } catch (Exception ignored){
                }
                try{
                    inhab = Long.parseLong(fieldCountryInhabitants.getText());
                } catch (Exception ignored){
                }
                return new Country(
                                fieldCountryName.getText(),
                                fieldCountryCapital.getText(),
                                inhab,
                                area
                );
            } else {
                logger.info("No country created.");
                return null;
        }
    }

    /**
     * In case that the name of the country that is being edited is already known, update the existing record
     * In case that the name of the country is not known, create new record
     */
    public void editCountry() {
        logger.info("Entering the Edit or create new Country block.");
        setVisible(true);
        String submittedName = fieldCountryName.getText();
        for (Country country : CountryManager.countries) {
            if (country != null){
                if (Objects.equals(country.getName().toLowerCase(Locale.ROOT), submittedName.toLowerCase(Locale.ROOT))) {
                    country.setCapital(fieldCountryCapital.getText());
                    if (!Objects.equals(fieldCountryArea.getText(), "")) {
                        country.setArea(Double.parseDouble(fieldCountryArea.getText()));
                    }
                    if (!Objects.equals(fieldCountryInhabitants.getText(), "")){
                        country.setInhabitants(Long.parseLong(fieldCountryInhabitants.getText()));
                    }
                    logger.info("A country with this name found and updated.");
                }
            }
        }
        logger.info("A country with this name wasn't found.");
        if (
                !Objects.equals(fieldCountryName.getText(), "")
        ) {
            logger.info("Creating new country - an existing country with a matching name wasn't found.");
            new Country(
                    fieldCountryName.getText(),
                    fieldCountryCapital.getText(),
                    !Objects.equals(fieldCountryArea.getText(), "") ? Long.parseLong(fieldCountryArea.getText()) : 0,
                    !Objects.equals(fieldCountryInhabitants.getText(), "") ? Double.parseDouble(fieldCountryInhabitants.getText()) : 0
            );
            clearFields();
            logger.info("New country created.");
        } else {
            logger.info("Country details not filled in.");
            clearFields();
        }
    }
}
