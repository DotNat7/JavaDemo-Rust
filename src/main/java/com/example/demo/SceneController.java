package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;


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
    private Label bmiResult;

    @FXML
    private Label pSex;

    @FXML
    private Label pName;

    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    public void setPatientName(String fullName) {
        if (pName != null) {
            pName.setText(fullName);
        }
    }

    @FXML
    public void savePatient(ActionEvent event) throws IOException {
        String name = nameField.getText();
        String surname = surnameField.getText();

        if (name.isEmpty() || surname.isEmpty()) {
            pName.setText("❌ Zadej prosím jméno i příjmení!");
            return;
        }

        // vytvoření loaderu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene3.fxml"));
        Parent root = loader.load();

        // získej controller pro Scene3
        SceneController scene3Controller = loader.getController();
        scene3Controller.setPatientName(name + " " + surname);

        // přepni scénu
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private LineChart<Number, Number> bmiChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public void getSex(ActionEvent event) throws IOException {
        if (maleRadio.isSelected()) {
            pSex.setText(maleRadio.getText());
        } else if(femaleRadio.isSelected()) {
            pSex.setText(femaleRadio.getText());
        }
    }

    @FXML
    public void initialize() {
        if (bmiChart == null) return;
        bmiChart.setCreateSymbols(false); // hladké křivky bez bodů
        addPercentileSeries("P3", new double[][]{{2,14.3},{5,13.8},{10,14.0},{15,17.0},{18,18.2}});
        addPercentileSeries("P50", new double[][]{{2,16.0},{5,15.7},{10,16.5},{15,20.9},{18,22.6}});
        addPercentileSeries("P97", new double[][]{{2,19.2},{5,18.9},{10,20.8},{15,26.0},{18,28.0}});
    }

    private void addPercentileSeries(String name, double[][] data) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (double[] point : data) {
            series.getData().add(new XYChart.Data<>(point[0], point[1]));
        }
        bmiChart.getData().add(series);
    }

    @FXML
    public void calculateBMI() {
        try {
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

        } catch (NumberFormatException e) {
            bmiResult.setText("❌ Zadej platná čísla pro výšku a váhu!");
        }
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
