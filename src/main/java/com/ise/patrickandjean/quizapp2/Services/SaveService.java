package com.ise.patrickandjean.quizapp2.Services;

import com.google.gson.*;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class SaveService {
    /// Constants
    /**
     * The location of SaveData - relative to Services/SaveService
     */
    private static final String SAVE_DATA_RELATIVE_PATH_STRING = SaveService.class.getResource("SaveData.json").getPath().substring(1);

    /// Variables
    /**
     * Stores the contents of SaveData.json as a JsonObject
     */
    private static JsonObject allSaves;

    private static String currentlyAuthenticatedUser = "";

    /// Helper Functions

    /**
     * Writes the current contents of allSaves to SaveData.json
     */
    public static boolean writeSaveDataToFile(String jsonDataAsString) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(SAVE_DATA_RELATIVE_PATH_STRING))) {
            fileWriter.write(jsonDataAsString);
        } catch (IOException e) {
            UtilityService.print("Failed to write jsonStringData", jsonDataAsString, "to disk due to:", e.getMessage());
            return false;
        }

        return true;
    }

    private static void assertAuthStateIs(boolean expectedAuthState) {
        boolean isStateAsExpected = currentlyAuthenticatedUser.isEmpty() == !expectedAuthState;
        if (!isStateAsExpected) {
            UtilityService.print("CUR:", currentlyAuthenticatedUser.isEmpty(), "EXP:", expectedAuthState);
            throw new IllegalStateException("Auth State Mismatch!");
        }
    }

    public static String getCurrentUser() {
        assertAuthStateIs(true);
        return currentlyAuthenticatedUser;
    }

    public static void initialiseSaveFileToMemory() {
        try {
            Path saveLocation = Path.of(SAVE_DATA_RELATIVE_PATH_STRING);
            allSaves = JsonParser.parseString(Files.readString(saveLocation, StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (JsonSyntaxException | IOException fileLoadFailException) {
            if (fileLoadFailException instanceof IOException) {
                UtilityService.print("No save file created - creating one for you!");
            } else {
                UtilityService.print("Save file was corrupted - creating a new one!");
            }

            boolean didCreateSaveFile = writeSaveDataToFile("{}");
            if (!didCreateSaveFile) {
                UtilityService.print("UNRECOVERABLE ERROR: Could not create a new save file!");
                System.exit(-1);
            }

            /// Parse empty file
            allSaves = JsonParser.parseString("{}").getAsJsonObject();
        }
    }

    public static boolean doesUserExist(String username) {
        return allSaves.has(username.toLowerCase());
    }

    public static void registerUser(String username, String password) {
        /// Sanity
        assertAuthStateIs(false);

        /// Create a new save for this new user
        JsonObject newSaveData = new JsonObject();
        newSaveData.addProperty("password", password);

        /// Add this save to the existing data object
        allSaves.add(username, newSaveData);
        writeSaveDataToFile(allSaves.toString());

        /// Assigned :)
        currentlyAuthenticatedUser = username;
    }

    public static boolean loginUser(String username, String password) {
        /// Sanity
        assertAuthStateIs(false);

        /// Ask them to provide their password
        String actualPassword = allSaves.get(username).getAsJsonObject().get("password").getAsString();

        /// Is the password correct?
        if (!password.toLowerCase().equals(actualPassword)) {
            return false;
        }

        /// Assigned :)
        currentlyAuthenticatedUser = username;

        ///
        return true;
    }

    /// Load save file into memory :D
    static {
        initialiseSaveFileToMemory();
    }
}