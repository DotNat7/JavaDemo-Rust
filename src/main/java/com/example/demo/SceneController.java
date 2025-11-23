package com.example.demo;

import com.example.demo.model.Proband;
import com.example.demo.model.ProbandDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class SceneController {
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

    private ProbandDAO probandDAO = new ProbandDAO();

    private int calculateAge(LocalDate birthDate) {
        if (birthDate == null) return 0;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    @FXML
    public void savePatient(ActionEvent event) throws IOException {
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
            probandDAO.save(new Proband(name, surname, maleRadio.isSelected(), birthDate, height, weight, LocalDate.now(), comment));
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToPatientList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("PatientsList.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToEditScene(ActionEvent event) throws IOException {
        Proband selected = patientsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("No patient selected");
            alert.setContentText("Please select a patient to edit.");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EditScene.fxml"));
        Parent root = loader.load();

        SceneController editController = loader.getController();
        editController.prepareEditScene(selected); // populate fields on the loaded controller

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private TextField heightFieldE;

    @FXML
    private TextField weightFieldE;

    @FXML
    private RadioButton maleRadioE;

    @FXML
    private RadioButton femaleRadioE;

    @FXML
    private TextField nameFieldE;

    @FXML
    private TextField surnameFieldE;

    @FXML
    private DatePicker birthDatePickerE;

    @FXML
    private TextField commentFieldE;

    @FXML
    private DatePicker measureDateE;

    private Proband currentProband;

    public void prepareEditScene(Proband proband) {
        currentProband = proband;

        if (nameFieldE != null) nameFieldE.setText(proband.getName());
        if (surnameFieldE != null) surnameFieldE.setText(proband.getSurname());
        if (birthDatePickerE != null) birthDatePickerE.setValue(proband.getBirthDate());
        if (heightFieldE != null) heightFieldE.setText(String.valueOf(proband.getHeight()));
        if (weightFieldE != null) weightFieldE.setText(String.valueOf(proband.getWeight()));
        if (commentFieldE != null) commentFieldE.setText(proband.getComment());
        if (maleRadioE != null && femaleRadioE != null) {
            if (proband.isMale()) {
                maleRadioE.setSelected(true);
                femaleRadioE.setSelected(false);
            } else {
                maleRadioE.setSelected(false);
                femaleRadioE.setSelected(true);
            }
        }
        measureDateE.setValue(LocalDate.now());
    }

    public void editSelectedProband(ActionEvent event) {
        Proband toSave = currentProband;
        toSave.setName(nameFieldE.getText());
        toSave.setSurname(surnameFieldE.getText());
        toSave.setBirthDate(birthDatePickerE.getValue());
        toSave.setHeight(Double.parseDouble(heightFieldE.getText()));
        toSave.setWeight(Double.parseDouble(weightFieldE.getText()));
        toSave.setComment(commentFieldE.getText());
        toSave.setMale(maleRadioE.isSelected());
        toSave.setMeasurementDate(measureDateE.getValue());

        try {
            probandDAO.update(toSave);
        } catch (IllegalStateException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Chyba Souběžnosti");
            alert.setHeaderText("Záznam byl upraven jiným uživatelem.");
            alert.setContentText("Znovu načtěte seznam probandů a zkuste to znovu.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            switchToPatientList(event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
            List<Proband> list = probandDAO.findAll();
            patientsTable.setItems(FXCollections.observableArrayList(list));
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
}