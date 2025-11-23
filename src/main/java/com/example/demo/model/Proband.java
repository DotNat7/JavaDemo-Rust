package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "probands")
public class Proband {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private boolean isMale;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double weight;

    @Column(nullable = false)
    private LocalDate measurementDate;

    @Column
    private String comment;

    @Version
    private Long version;

    public Proband() {
    }

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

    public Long getId() {
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

    public Long getVersion() {
        return version;
    }

    public void setId(Long id) {
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

    public void setVersion(Long version) {
        this.version = version;
    }
}