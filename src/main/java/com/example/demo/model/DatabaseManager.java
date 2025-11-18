package com.example.demo.model;

import java.io.File;
import java.io.IOException;

public class DatabaseManager {
    private static final String DB_PATH = "./secure_db";
    private static final String DB_USER = "sa";
    private static final String ENCRYPTION_KEY = "heslo123";
    private static final String LOCK_FILE_PATH = "./secure_db.lock";

    private static DatabaseManager instance;
    private ProbandH2 probandH2;
    private ProbandService probandService;
    private File lockFile;

    private DatabaseManager() throws IOException {
        lockFile = new File(LOCK_FILE_PATH);

        if (lockFile.exists()) {
            throw new IOException("Databáze je již používána jinou instancí aplikace.");
        }

        if (!lockFile.createNewFile()) {
            throw new IOException("Nepodařilo se vytvořit zámkový soubor");
        }

        lockFile.deleteOnExit();

        try {
            probandH2 = new ProbandH2(DB_PATH, DB_USER, ENCRYPTION_KEY);
            probandService = new ProbandService(probandH2);
        } catch (Exception e) {
            lockFile.delete();
            throw new IOException("Nepodařilo se připojit k databázi: " + e.getMessage(), e);
        }
    }

    public static synchronized DatabaseManager getInstance() throws IOException {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public ProbandH2 getProbandH2() {
        return probandH2;
    }

    public ProbandService getProbandService() {
        return probandService;
    }

    public void close() {
        try {
            if (probandService != null) {
                probandService.close();
            }
        } catch (Exception e) {
            System.err.println("Error closing database: " + e.getMessage());
        } finally {
            if (lockFile != null && lockFile.exists()) {
                lockFile.delete();
            }
            probandH2 = null;
            probandService = null;
            instance = null;
        }
    }

    public static synchronized void resetInstance() {
        if (instance != null) {
            instance.close();
        }
    }

    public static boolean isLocked() {
        return new File(LOCK_FILE_PATH).exists();
    }
}