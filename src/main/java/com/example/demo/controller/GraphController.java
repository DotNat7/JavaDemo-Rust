package com.example.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GraphController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private LineChart<Number, Number> bmiChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Label patientLabel;

    public void setPatientName(String fullName) {
        if (patientLabel != null) {
            patientLabel.setText(fullName);
        }
    }

    private String gender;

    public void setGender(String gender) {
        this.gender = gender;
    }

    @FXML
    public void initialize() {
        if (bmiChart == null) return;
        bmiChart.setCreateSymbols(false);
        }

    public void loadMaleBMI() {
        addPercentileSeries("P3", new double[][]{{0.0, 11.4},{0.2, 12.4},{0.4, 13.4},{0.6, 14.0}, {0.8, 14.4}, {1.0, 14.5}, {1.2, 14.5}, {1.4, 14.3}, {1.6, 14.2}, {1.8, 14.0}, {2.0, 13.9}, {2.2, 13.7}, {2.4, 13.7}, {2.6, 13.6}, {2.8, 13.5}, {3.0, 13.5}, {3.5, 13.4}, {4.0, 13.3}, {4.5, 13.2}, {5.0, 13.1}, {5.5, 13.0}, {6.0, 13.1}, {7.0, 13.1}, {8.0, 13.2}, {9.0, 13.5}, {10.0, 13.7}, {11.0, 14.1}, {12.0, 14.5}, {13.0, 15.0}, {14.0, 15.7}, {15.0, 16.4}, {16.0, 17.1}, {17.0, 17.6}, {18.0, 18.2},});
        addPercentileSeries("P10", new double[][]{{0.0, 12.2}, {0.2, 13.2}, {0.4, 14.2}, {0.6, 14.9}, {0.8, 15.3}, {1.0, 15.4}, {1.2, 15.3}, {1.4, 15.1}, {1.6, 15.0}, {1.8, 14.8}, {2.0, 14.6}, {2.2, 14.5}, {2.4, 14.4}, {2.6, 14.3}, {2.8, 14.2}, {3.0, 14.2}, {3.5, 14.0}, {4.0, 13.9}, {4.5, 13.8}, {5.0, 13.8}, {5.5, 13.7}, {6.0, 13.7}, {7.0, 13.8}, {8.0, 13.9}, {9.0, 14.2}, {10.0, 14.5}, {11.0, 14.9}, {12.0, 15.4}, {13.0, 15.9}, {14.0, 16.6}, {15.0, 17.3}, {16.0, 18.0}, {17.0, 18.6}, {18.0, 19.1}});
        addPercentileSeries("P25", new double[][]{{0.0, 13.0}, {0.2, 14.1}, {0.4, 15.1}, {0.6, 15.8}, {0.8, 16.2}, {1.0, 16.3}, {1.2, 16.2}, {1.4, 16.0}, {1.6, 15.8}, {1.8, 15.6}, {2.0, 15.5}, {2.2, 15.3}, {2.4, 15.2}, {2.6, 15.1}, {2.8, 15.0}, {3.0, 14.9}, {3.5, 14.8}, {4.0, 14.7}, {4.5, 14.6}, {5.0, 14.5}, {5.5, 14.4}, {6.0, 14.5}, {7.0, 14.6}, {8.0, 14.8}, {9.0, 15.1}, {10.0, 15.5}, {11.0, 15.9}, {12.0, 16.4}, {13.0, 17.0}, {14.0, 17.7}, {15.0, 18.4}, {16.0, 19.1}, {17.0, 19.7}, {18.0, 20.3}});
        addPercentileSeries("P50", new double[][]{{0.0, 13.9}, {0.2, 15.1}, {0.4, 16.2}, {0.6, 17.0}, {0.8, 17.4}, {1.0, 17.5}, {1.2, 17.3}, {1.4, 17.1}, {1.6, 16.9}, {1.8, 16.7}, {2.0, 16.5}, {2.2, 16.3}, {2.4, 16.2}, {2.6, 16.0}, {2.8, 16.0}, {3.0, 15.9}, {3.5, 15.7}, {4.0, 15.6}, {4.5, 15.5}, {5.0, 15.4}, {5.5, 15.4}, {6.0, 15.4}, {7.0, 15.6}, {8.0, 15.9}, {9.0, 16.3}, {10.0, 16.7}, {11.0, 17.2}, {12.0, 17.8}, {13.0, 18.4}, {14.0, 19.1}, {15.0, 19.8}, {16.0, 20.5}, {17.0, 21.1}, {18.0, 21.7}});
        addPercentileSeries("P75", new double[][]{{0.0, 15.0}, {0.2, 16.3}, {0.4, 17.4}, {0.6, 18.2}, {0.8, 18.6}, {1.0, 18.7}, {1.2, 18.6}, {1.4, 18.3}, {1.6, 18.1}, {1.8, 17.8}, {2.0, 17.6}, {2.2, 17.4}, {2.4, 17.2}, {2.6, 17.1}, {2.8, 17.0}, {3.0, 16.9}, {3.5, 16.7}, {4.0, 16.6}, {4.5, 16.5}, {5.0, 16.4}, {5.5, 16.4}, {6.0, 16.5}, {7.0, 16.8}, {8.0, 17.2}, {9.0, 17.7}, {10.0, 18.3}, {11.0, 18.9}, {12.0, 19.5}, {13.0, 20.1}, {14.0, 20.9}, {15.0, 21.5}, {16.0, 22.3}, {17.0, 22.9}, {18.0, 23.5}});
        addPercentileSeries("P90", new double[][]{{0.0, 16.0}, {0.2, 17.4}, {0.4, 18.6}, {0.6, 19.4}, {0.8, 19.8}, {1.0, 19.9}, {1.2, 19.8}, {1.4, 19.5}, {1.6, 19.2}, {1.8, 18.9}, {2.0, 18.7}, {2.2, 18.5}, {2.4, 18.3}, {2.6, 18.2}, {2.8, 18.0}, {3.0, 17.9}, {3.5, 17.7}, {4.0, 17.6}, {4.5, 17.5}, {5.0, 17.5}, {5.5, 17.5}, {6.0, 17.6}, {7.0, 18.0}, {8.0, 18.6}, {9.0, 19.3}, {10.0, 20.1}, {11.0, 20.8}, {12.0, 21.5}, {13.0, 22.1}, {14.0, 22.9}, {15.0, 23.5}, {16.0, 24.2}, {17.0, 24.8}, {18.0, 25.4}});
        addPercentileSeries("P97", new double[][]{ {0.0, 17.1}, {0.2, 18.6}, {0.4, 19.8}, {0.6, 20.7}, {0.8, 21.1}, {1.0, 21.2}, {1.2, 21.1}, {1.4, 20.8}, {1.6, 20.4}, {1.8, 20.1}, {2.0, 19.9}, {2.2, 19.6}, {2.4, 19.4}, {2.6, 19.3}, {2.8, 19.2}, {3.0, 19.1}, {3.5, 18.8}, {4.0, 18.7}, {4.5, 18.6}, {5.0, 18.7}, {5.5, 18.7}, {6.0, 18.9}, {7.0, 19.5}, {8.0, 20.3}, {9.0, 21.3}, {10.0, 22.3}, {11.0, 23.3}, {12.0, 24.1}, {13.0, 24.7}, {14.0, 25.4}, {15.0, 25.9}, {16.0, 26.6}, {17.0, 27.1}, {18.0, 27.7}});
    }

    public void loadFemaleBMI() {
        addPercentileSeries("P3", new double[][]{{0.0, 11.0}, {0.2, 12.1}, {0.4, 13.1}, {0.6, 13.8}, {0.8, 14.2}, {1.0, 14.3}, {1.2, 14.2}, {1.4, 14.1}, {1.6, 13.9}, {1.8, 13.8}, {2.0, 13.6}, {2.2, 13.5}, {2.4, 13.4}, {2.6, 13.3}, {2.8, 13.3}, {3.0, 13.2}, {3.5, 13.1}, {4.0, 13.1}, {4.5, 12.9}, {5.0, 12.8}, {5.5, 12.7}, {6.0, 12.7}, {7.0, 12.7}, {8.0, 12.8}, {9.0, 13.0}, {10.0, 13.2}, {11.0, 13.6}, {12.0, 14.1}, {13.0, 15.0}, {14.0, 15.8}, {15.0, 16.4}, {16.0, 17.0}, {17.0, 17.4}, {18.0, 17.6}});
        addPercentileSeries("P10", new double[][]{{0.0, 11.7}, {0.2, 12.9}, {0.4, 13.9}, {0.6, 14.6}, {0.8, 15.0}, {1.0, 15.1}, {1.2, 15.0}, {1.4, 14.9}, {1.6, 14.7}, {1.8, 14.5}, {2.0, 14.4}, {2.2, 14.2}, {2.4, 14.1}, {2.6, 14.0}, {2.8, 14.0}, {3.0, 13.9}, {3.5, 13.8}, {4.0, 13.8}, {4.5, 13.6}, {5.0, 13.5}, {5.5, 13.4}, {6.0, 13.4}, {7.0, 13.5}, {8.0, 13.7}, {9.0, 13.9}, {10.0, 14.2}, {11.0, 14.5}, {12.0, 15.1}, {13.0, 16.0}, {14.0, 16.8}, {15.0, 17.4}, {16.0, 18.0}, {17.0, 18.3}, {18.0, 18.6}});
        addPercentileSeries("P25", new double[][]{{0.0, 12.4}, {0.2, 13.7}, {0.4, 14.8}, {0.6, 15.5}, {0.8, 15.9}, {1.0, 16.0}, {1.2, 15.9}, {1.4, 15.7}, {1.6, 15.5}, {1.8, 15.3}, {2.0, 15.2}, {2.2, 15.0}, {2.4, 14.9}, {2.6, 14.8}, {2.8, 14.7}, {3.0, 14.7}, {3.5, 14.6}, {4.0, 14.5}, {4.5, 14.4}, {5.0, 14.2}, {5.5, 14.2}, {6.0, 14.2}, {7.0, 14.4}, {8.0, 14.6}, {9.0, 14.9}, {10.0, 15.2}, {11.0, 15.7}, {12.0, 16.2}, {13.0, 17.1}, {14.0, 18.0}, {15.0, 18.5}, {16.0, 19.1}, {17.0, 19.4}, {18.0, 19.7}});
        addPercentileSeries("P50", new double[][]{{0.0, 13.3}, {0.2, 14.7}, {0.4, 15.8}, {0.6, 16.6}, {0.8, 17.0}, {1.0, 17.1}, {1.2, 17.0}, {1.4, 16.8}, {1.6, 16.5}, {1.8, 16.3}, {2.0, 16.1}, {2.2, 16.0}, {2.4, 15.8}, {2.6, 15.7}, {2.8, 15.6}, {3.0, 15.6}, {3.5, 15.5}, {4.0, 15.4}, {4.5, 15.3}, {5.0, 15.2}, {5.5, 15.2}, {6.0, 15.3}, {7.0, 15.5}, {8.0, 15.9}, {9.0, 16.2}, {10.0, 16.6}, {11.0, 17.1}, {12.0, 17.7}, {13.0, 18.7}, {14.0, 19.5}, {15.0, 19.9}, {16.0, 20.5}, {17.0, 20.9}, {18.0, 21.2}});
        addPercentileSeries("P75", new double[][]{{0.0, 14.3}, {0.2, 15.7}, {0.4, 17.0}, {0.6, 17.8}, {0.8, 18.2}, {1.0, 18.3}, {1.2, 18.1}, {1.4, 17.9}, {1.6, 17.6}, {1.8, 17.4}, {2.0, 17.2}, {2.2, 17.0}, {2.4, 16.8}, {2.6, 16.7}, {2.8, 16.6}, {3.0, 16.6}, {3.5, 16.5}, {4.0, 16.4}, {4.5, 16.4}, {5.0, 16.3}, {5.5, 16.3}, {6.0, 16.4}, {7.0, 16.8}, {8.0, 17.3}, {9.0, 17.8}, {10.0, 18.3}, {11.0, 18.9}, {12.0, 19.6}, {13.0, 20.5}, {14.0, 21.3}, {15.0, 21.7}, {16.0, 22.2}, {17.0, 22.6}, {18.0, 23.0}});
        addPercentileSeries("P90", new double[][]{{0.0, 15.3}, {0.2, 16.8}, {0.4, 18.1}, {0.6, 18.9}, {0.8, 19.4}, {1.0, 19.4}, {1.2, 19.3}, {1.4, 19.0}, {1.6, 18.7}, {1.8, 18.4}, {2.0, 18.2}, {2.2, 18.0}, {2.4, 17.8}, {2.6, 17.7}, {2.8, 17.6}, {3.0, 17.5}, {3.5, 17.4}, {4.0, 17.4}, {4.5, 17.4}, {5.0, 17.3}, {5.5, 17.4}, {6.0, 17.6}, {7.0, 18.1}, {8.0, 18.9}, {9.0, 19.5}, {10.0, 20.2}, {11.0, 20.9}, {12.0, 21.6}, {13.0, 22.6}, {14.0, 23.3}, {15.0, 23.6}, {16.0, 24.1}, {17.0, 24.6}, {18.0, 25.0}});
        addPercentileSeries("P97", new double[][]{{0.0, 16.3}, {0.2, 17.9}, {0.4, 19.3}, {0.6, 20.2}, {0.8, 20.6}, {1.0, 20.7}, {1.2, 20.5}, {1.4, 20.2}, {1.6, 19.9}, {1.8, 19.6}, {2.0, 19.3}, {2.2, 19.1}, {2.4, 18.9}, {2.6, 18.7}, {2.8, 18.6}, {3.0, 18.5}, {3.5, 18.5}, {4.0, 18.5}, {4.5, 18.5}, {5.0, 18.5}, {5.5, 18.6}, {6.0, 18.9}, {7.0, 19.6}, {8.0, 20.6}, {9.0, 21.5}, {10.0, 22.4}, {11.0, 23.3}, {12.0, 24.2}, {13.0, 25.2}, {14.0, 25.8}, {15.0, 26.0}, {16.0, 26.5}, {17.0, 27.0}, {18.0, 27.6}});
    }

    public void showCurvesForGender() {
        if (gender == null) return;

        if (gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("chlapec")) {
            loadMaleBMI();
        } else if (gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("dívka")) {
            loadFemaleBMI();
        }
    }

    private void addPercentileSeries(String name, double[][] data) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);
        for (double[] point : data) {
            series.getData().add(new XYChart.Data<>(point[0], point[1]));
        }
        bmiChart.getData().add(series);
    }

    public void addPatientPoint(int age, double bmi, String fullName) {
        XYChart.Series<Number, Number> patientSeries = new XYChart.Series<>();
        patientSeries.setName("Pacient");

        XYChart.Data<Number, Number> dataPoint = new XYChart.Data<>(age, bmi);

        javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(6);
        circle.setStyle("-fx-fill: red; -fx-stroke: black; -fx-stroke-width: 1;");
        dataPoint.setNode(circle);

        patientSeries.getData().add(dataPoint);
        bmiChart.getData().add(patientSeries);
    }

    @FXML
    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/Scene1.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/Scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToPatientList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/demo/ProbandList.fxml"));;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
