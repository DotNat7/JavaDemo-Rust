package com.example.demo.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProbandH2 {
    private Connection connection;

    public ProbandH2(String dbPath, String user, String encryptionKey) throws Exception {
        Class.forName("org.h2.Driver");
        String dbUrl = "jdbc:h2:" + dbPath + ";CIPHER=AES";
        connection = DriverManager.getConnection(dbUrl, user, encryptionKey + " " + encryptionKey);

        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS probands (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    surname VARCHAR(255) NOT NULL,
                    isMale BOOLEAN NOT NULL,
                    birthDate DATE NOT NULL,
                    height DOUBLE NOT NULL,
                    weight DOUBLE NOT NULL,
                    measurementDate DATE NOT NULL,
                    comment VARCHAR(1024)
                )
                """;

        connection.createStatement().execute(createTableSQL);
    }

    public void addUser(String name, String surname, boolean isMale, LocalDate birthDate, double heigh,
                        double weight, LocalDate measurementDate, String comment) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO probands (name, surname, isMale, birthDate, height, weight, measurementDate, comment) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        );
        stmt.setString(1, name);
        stmt.setString(2, surname);
        stmt.setBoolean(3, isMale);
        stmt.setObject(4, birthDate);
        stmt.setDouble(5, heigh);
        stmt.setDouble(6, weight);
        stmt.setObject(7, measurementDate);
        stmt.setString(8, comment);
        stmt.executeUpdate();
        stmt.close();
    }

    public List<Proband> getAllProbands() throws SQLException {
        List<Proband> probands = new ArrayList<>();

        ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM probands ORDER BY id");

        while (rs.next()) {
            Proband newProband = new Proband(
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getBoolean("isMale"),
                    rs.getObject("birthDate", LocalDate.class),
                    rs.getDouble("height"),
                    rs.getDouble("weight"),
                    rs.getObject("measurementDate", LocalDate.class),
                    rs.getString("comment"));
            newProband.setId(rs.getInt("id"));
            probands.add(newProband);
        }

        rs.close();
        return probands;
    }

    public void deleteProband(int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM probands WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}