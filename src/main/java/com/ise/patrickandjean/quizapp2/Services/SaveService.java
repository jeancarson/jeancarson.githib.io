package com.ise.patrickandjean.quizapp2.Services;

import com.google.gson.*;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;


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
    private static boolean writeSaveDataToFile(String jsonDataAsString) {
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

        /// Add empty game data arrays
        newSaveData.add("eliminationGameHistory", new JsonArray());
        newSaveData.add("increasingDifficultyGameHistory", new JsonArray());
        newSaveData.add("randomlyChosenGameHistory", new JsonArray());

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

    public static void addNewGameResult(String saveFileIndex, int score) {
        /// Sanity
        assertAuthStateIs(true);

        /// Get current save, and add the new result
        JsonObject currentSave = allSaves.get(currentlyAuthenticatedUser).getAsJsonObject();
        JsonArray allGamesOfThisTypeData = currentSave.get(saveFileIndex).getAsJsonArray();
        allGamesOfThisTypeData.add(score);

        /// Then update the file
        writeSaveDataToFile(allSaves.toString());
    }

    public static int[] getAllResultsForCurrentUserInGameMode(String saveKeyIndex) {
        /// Sanity
        assertAuthStateIs(true);

        /// Get current save for this gamemode
        JsonObject currentSave = allSaves.get(currentlyAuthenticatedUser).getAsJsonObject();
        JsonArray allScores = currentSave.get(saveKeyIndex).getAsJsonArray();

        /// int[] intArray = gson.fromJson(jsonArray, int[].class);
        return new Gson().fromJson(allScores, int[].class);
    }

    public static int[] getAllResultsForGameMode(String saveKeyIndex) {
        /// Sanity
        assertAuthStateIs(true);

        /// Vars
        ArrayList<Integer> mergedList = new ArrayList<>();

        /// Loop through all saves, and dump data into merge
        for (Map.Entry<String, JsonElement> entry : allSaves.entrySet()) {
            JsonObject userSave = entry.getValue().getAsJsonObject();
            JsonArray scores = userSave.getAsJsonArray(saveKeyIndex);
            for (JsonElement element : scores) {
                mergedList.add(element.getAsInt());
            }
        }

        /// Convert from ArrayList to int[]
        int[] scores = new int[mergedList.size()];
        for (int idx = 0; idx < mergedList.size(); idx++) {
            scores[idx] = mergedList.get(idx);
        }

        ///
        return scores;
    }

    /// Load save file into memory :D
    static {
        initialiseSaveFileToMemory();
    }
}