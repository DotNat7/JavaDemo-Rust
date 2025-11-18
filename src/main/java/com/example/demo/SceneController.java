package com.example.demo;

import com.example.demo.model.DatabaseManager;
import com.example.demo.model.ProbandService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class SceneController {
    private static final String DB_PATH = "./secure_db";
    private static final String DB_USER = "sa";
    private static final String ENCRYPTION_KEY = "heslo123";

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField heightField;

    @FXML
    private TextField weightField;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField commentField;

    private ProbandService probandService;

    @FXML
    public void initialize() {
        connectToDatabase();
    }

    private void connectToDatabase() {
        try {
            DatabaseManager dbManager = DatabaseManager.getInstance();
            probandService = dbManager.getProbandService();
        } catch (IOException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
            showDatabaseErrorDialog(e.getMessage());
        }
    }

    private int calculateAge(LocalDate birthDate) {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @FXML
    public void savePatient(ActionEvent event) throws IOException {
        ProbandService probandService;
        try {
            ProbandH2 probandH2 = new ProbandH2(DB_PATH, DB_USER, ENCRYPTION_KEY);
            probandService = new ProbandService(probandH2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }

        String name = nameField.getText();
        String surname = surnameField.getText();
        String heightText = heightField.getText();
        String weightText = weightField.getText();
        String gender = maleRadio.isSelected() ? "chlapec" : femaleRadio.isSelected() ? "dívka" : "";
        String comment = commentField.getText();

        StringBuilder errors = new StringBuilder();

        if (name.isEmpty()) errors.append("• Zadejte křestní jméno.\n");
        if (surname.isEmpty()) errors.append("• Zadejte příjmení.\n");
        if (gender.isEmpty()) errors.append("• Vyberte pohlaví.\n");

        LocalDate birthDate = birthDatePicker.getValue();
        if (birthDate == null) {
            errors.append("• Zadejte datum narození.\n");
        }

        int age = 0;
        if (birthDate != null) {
            age = calculateAge(birthDate);
        }

        double height = 0, weight = 0;
        try {
            height = Double.parseDouble(heightText);
            if (height < 30 || height > 200) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            errors.append("• Zadejte výšku v rozmezí 30-200 cm\n");
        }

        try {
            weight = Double.parseDouble(weightText);
            if (weight < 1.5 || weight > 200) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            errors.append("• Zadejte váhu v rozmezí 1.5-200 kg\n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chybné údaje");
            alert.setHeaderText("Zkontrolujte zadané hodnoty:");
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return;
        }

        double heightValue = Double.parseDouble(heightText);
        double weightValue = Double.parseDouble(weightText);
        double bmi = weightValue / Math.pow(heightValue / 100, 2);

        // vytvoření loaderu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        Parent root = loader.load();
        GraphController controller = loader.getController();
        controller.addPatientPoint(age, bmi, name);
        controller.setPatientName(name + " " + surname);
        controller.setGender(gender);
        controller.showCurvesForGender();

        try {
            probandService.addProband(
                    name,
                    surname,
                    maleRadio.isSelected(),
                    birthDate,
                    height,
                    weight,
                    LocalDate.now(),
                    comment
            );
            probandService.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // přepni scénu
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToPatientList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PatientsList.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TableView<Proband> patientsTable;

    @FXML
    private TableColumn<Proband, String> nameColumn;

    @FXML
    private TableColumn<Proband, String> surnameColumn;

    @FXML
    private TableColumn<Proband, LocalDate> birthColumn;

    @FXML
    private TableColumn<Proband, String> noteColumn;

    @FXML
    public void initialize() {
        if (patientsTable != null) {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
            birthColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
            noteColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

            loadPatients();   // <-- IMPORTANT
        }
    }

    private void loadPatients() {
        try {
            ProbandH2 probandH2 = new ProbandH2(DB_PATH, DB_USER, ENCRYPTION_KEY);
            ProbandService probandService = new ProbandService(probandH2);

            List<Proband> list = probandService.getAllProbands();
            patientsTable.setItems(FXCollections.observableArrayList(list));

            probandService.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showGraphForSelected(ActionEvent event) {
        Proband selected = patientsTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            System.out.println("No patient selected");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
            Parent root = loader.load();

            GraphController controller = loader.getController();
            controller.setPatientName(selected.getName() + " " + selected.getSurname());
            controller.setGender(selected.isMale() ? "chlapec" : "dívka");
            controller.showCurvesForGender();

            int age = Period.between(selected.getBirthDate(), LocalDate.now()).getYears();
            double bmi = selected.getWeight() / Math.pow(selected.getHeight() / 100.0, 2);

            controller.addPatientPoint(age, bmi, selected.getName() + selected.getSurname());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDatabaseErrorDialog(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Chyba databáze");
        alert.setHeaderText("Nelze se připojit k databázi");
        alert.setContentText(errorMessage + "\n\nCo chcete udělat?");

        ButtonType refreshButton = new ButtonType("Obnovit");
        ButtonType quitButton = new ButtonType("Ukončit aplikaci");

        alert.getButtonTypes().setAll(refreshButton, quitButton);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == refreshButton) {
                DatabaseManager.resetInstance();

                try {
                    DatabaseManager dbManager = DatabaseManager.getInstance();
                    probandService = dbManager.getProbandService();

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Úspěch");
                    successAlert.setHeaderText("Připojení obnoveno");
                    successAlert.setContentText("Databáze je nyní připojena.");
                    successAlert.showAndWait();

                } catch (IOException e) {
                    System.err.println("Reconnection failed: " + e.getMessage());
                    showDatabaseErrorDialog(e.getMessage());
                }
            } else if (result.get() == quitButton) {
                Platform.exit();
            }
        }
    }
}