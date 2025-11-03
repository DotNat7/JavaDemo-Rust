package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;


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
        controller.addPatientPoint(age, bmi);
        controller.setPatientName(name + " " + surname);
        controller.setGender(gender);
        controller.showCurvesForGender();

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
}



    /*public void getSex(ActionEvent event) throws IOException {
        if (maleRadio.isSelected()) {
            pSex.setText(maleRadio.getText());
        } else if(femaleRadio.isSelected()) {
            pSex.setText(femaleRadio.getText());
        }
    }*/

    /*@FXML
    public void calculateBMI() {

            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());

            // výpočet BMI
            double bmi = weight / Math.pow(height / 100, 2); // výška v metrech

            String gender;
            if (maleRadio.isSelected()) {
                gender = "chlapec";
            } else if (femaleRadio.isSelected()) {
                gender = "dívka";
            } else {
                gender = "neuvedeno";
            }

            // slovní hodnocení podle BMI
            String category;
            if (bmi < 18.5) {
                category = "Podváha";
            } else if (bmi < 25) {
                category = "Normální váha";
            } else if (bmi < 30) {
                category = "Nadváha";
            } else {
                category = "Obezita";
            }

            bmiResult.setText(String.format("Pohlaví: %s | BMI: %.1f (%s)", gender, bmi, category));
    }*/

/*@FXML
private Label bmiResult;

@FXML
private Label pSex;

@FXML
private Label age;

@FXML
private Label pName;*/
