import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONException;
import org.json.JSONObject;

public class SaveManager {
    private static final String SAVE_FILE_PATH = "savefile.json";
    private JSONObject saveData;

    public SaveManager() {
        loadSaveFile();
    }

    private void loadSaveFile() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(SAVE_FILE_PATH)));
            saveData = new JSONObject(content);
        } catch (Exception e) {
            // If the file doesn't exist or is invalid, initialize an empty JSONObject
            saveData = new JSONObject();
            System.out.println("No save file found or error loading: " + e.getMessage());
        }
    }

    public void saveHighScore(String playerName, int highScore) {
        try {
            // Update the saveData JSONObject with new values
            saveData.put("playerName", playerName);
            saveData.put("highscore", highScore);

            // Write the updated data back to the file
            try (FileWriter file = new FileWriter(SAVE_FILE_PATH)) {
                file.write(saveData.toString(4));
                System.out.println("High score saved successfully!");
            }
        } catch (JSONException e) {
            throw new RuntimeException("Error updating save data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error saving high score: " + e.getMessage());
        }
    }

    public void loadHighScore() {
        try {
            String playerName = saveData.optString("playerName", "Unknown Player");
            int highScore = saveData.optInt("highscore", 0);

            System.out.println("Current High Score: " + highScore + " by " + playerName);
        } catch (Exception e) {
            System.out.println("Error retrieving high score: " + e.getMessage());
        }
    }

    public int getHighScore() {
        return saveData.optInt("highscore", 0); // Return 0 if no high score is found
    }

    public String getPlayerName() {
        return saveData.optString("playerName", "Unknown Player"); // Return a default name if none is found
    }
}
