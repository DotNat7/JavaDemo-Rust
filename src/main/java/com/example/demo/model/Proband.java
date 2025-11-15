package com.example.demo.model;

import java.time.LocalDate;

public class Proband {
    private int id;
    private String name;
    private String surname;
    private boolean isMale;
    private LocalDate birthDate;
    private double height;
    private double weight;
    private LocalDate measurementDate;
    private String comment;

    public Proband(String name, String surname, boolean isMale, LocalDate birthDate, double height, double weight, LocalDate measurementDate, String comment) {
        this.name = name;
        this.surname = surname;
        this.isMale = isMale;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.measurementDate = measurementDate;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isMale() {
        return isMale;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public LocalDate getMeasurementDate() {
        return measurementDate;
    }

    public String getComment() {
        return comment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setMeasurementDate(LocalDate measurementDate) {
        this.measurementDate = measurementDate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}