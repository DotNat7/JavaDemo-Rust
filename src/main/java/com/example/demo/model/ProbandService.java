package com.example.demo.model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ProbandService {
    private final ProbandH2 probandH2;

    public ProbandService(ProbandH2 probandH2) {
        this.probandH2 = probandH2;
    }

    public void addProband(String name, String surname, boolean isMale, LocalDate birthDate, double heigh, double weight, LocalDate measurementDate, String comment) throws Exception {
        probandH2.addUser(name, surname, isMale, birthDate, heigh, weight, measurementDate, comment);
    }

    public List<Proband> getAllProbands() throws SQLException {
        return probandH2.getAllProbands();
    }

    public void deleteProband(int probandID) throws SQLException {
        probandH2.deleteProband(probandID);
    }

    public void close() throws Exception {
        probandH2.close();
    }

//    pridat hlasku, kdyz databazi nejde nacist
}