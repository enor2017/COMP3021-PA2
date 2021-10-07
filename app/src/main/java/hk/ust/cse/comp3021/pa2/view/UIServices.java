package hk.ust.cse.comp3021.pa2.view;

import hk.ust.cse.comp3021.pa2.InertiaFxGame;
import hk.ust.cse.comp3021.pa2.model.GameState;
import hk.ust.cse.comp3021.pa2.util.GameStateSerializer;
import hk.ust.cse.comp3021.pa2.util.NotImplementedException;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for performing GUI interactions
 */
public class UIServices {

    private static final String FILE_CHOOSER_TITLE = "Load Game";

    /**
     * Creates a {@link FileChooser} for choosing a game file.
     *
     * @return The {@link FileChooser}.
     */
    @NotNull
    public static FileChooser createGameLoadFileChooser() {
        // TODO: Create a file chooser with the provided string above \
        //  and set its initial directory to be the current working directory.
        throw new NotImplementedException();
    }

    /**
     * Gets the working directory of the current program
     *
     * @return A {@link Path} representing the current working directory.
     */
    @NotNull
    public static Path getWorkingDirectory() {
        return Paths.get("").toAbsolutePath();
    }

    /**
     * Prompts the user to choose a file and load it to the game.
     * Shows an {@link Alert} then exception occurred.
     *
     * @param game The {@link InertiaFxGame} instance.
     * @return the {@link GameState} or {@literal null} if exception occurred.
     */
    @Nullable
    public static GameState loadGame(@NotNull InertiaFxGame game) {
        var fileChooser = UIServices.createGameLoadFileChooser();
        var selectedFile = fileChooser.showOpenDialog(game.getPrimaryStage());
        GameState gameState = null;
        if (selectedFile != null) {
            try {
                gameState = GameStateSerializer.loadFrom(selectedFile.toPath());
            } catch (Exception ex) {
                showLoadGameErrorDialog();
            }
        } else {
            showFileNotSelectedDialog();
        }
        return gameState;
    }

    private static final String LOAD_GAME_ERROR_ALERT_TITLE = "Can not load game";

    private static final String LOAD_GAME_ERROR_ALERT_CONTENT_TEXT
            = "Error occurred when loading the game from selected file.";

    /**
     * Shows an {@link Alert} telling that there is an error when trying to open the game file.
     */
    public static void showLoadGameErrorDialog() {
        // TODO: Show a dialog using the provided strings above.
        throw new NotImplementedException();
    }

    private static final String LOAD_GAME_CANCEL_ALERT_TITLE = "Operation Cancelled";

    private static final String LOAD_GAME_CANCEL_ALERT_CONTENT_TEXT = "You didn't select a file.";

    /**
     * Shows an {@link Alert} telling that the user has cancelled loading a game file.
     */
    public static void showFileNotSelectedDialog() {
        // TODO: Show a dialog using the provided strings above.
        throw new NotImplementedException();
    }

    private static final String WIN_ALERT_TITLE = "Congratulations";

    private static final String WIN_ALERT_CONTENT_TEXT = "You won the game!";

    /**
     * Shows an {@link Alert} telling that the user has won the game.
     */
    public static void showWinDialog() {
        // TODO: Show a dialog using the provided strings above.
        throw new NotImplementedException();
    }

    private static final String LOSE_ALERT_TITLE = "Oops";

    private static final String LOSE_ALERT_CONTENT_TEXT = "You lose the game.";

    /**
     * Shows an {@link Alert} telling that the user has lost the game.
     */
    public static void showLoseDialog() {
        // TODO: Show a dialog using the provided strings above.
        throw new NotImplementedException();
    }
}
