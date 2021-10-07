package hk.ust.cse.comp3021.pa2;

import hk.ust.cse.comp3021.pa2.model.GameState;
import hk.ust.cse.comp3021.pa2.view.panes.MainGamePane;
import hk.ust.cse.comp3021.pa2.view.panes.MainMenuPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.Objects;

/**
 * The Java FX version of the Inertia game.
 */
public class InertiaFxGame {

    private final Stage primaryStage;

    private static final int WINDOW_WIDTH = 640;

    private static final int WINDOW_HEIGHT = 360;

    private final URL styleSheet = Objects.requireNonNull(getClass().getResource("/styles/style.css"));

    public InertiaFxGame(@NotNull Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Starts the game application.
     */
    public void run() {
        primaryStage.setTitle("Inertia Game");
        primaryStage.setWidth(WINDOW_WIDTH);
        primaryStage.setHeight(WINDOW_HEIGHT);
        primaryStage.resizableProperty().set(false);
        primaryStage.show();
        showMainMenu();
    }

    /**
     * Navigates to the main menu of the game.
     */
    public void showMainMenu() {
        var mainMenu = new MainMenuPane(this);
        mainMenu.initializeComponents();
        var scene = new Scene(mainMenu);
        scene.getStylesheets().add(styleSheet.toExternalForm());
        primaryStage.setScene(scene);
    }

    /**
     * Navigates to the game play interface
     *
     * @param gameState The {@link GameState} to start playing.
     */
    public void showGamePane(@NotNull GameState gameState) {
        var gamePane = new MainGamePane(gameState, this);
        gamePane.initializeComponents();
        var scene = new Scene(gamePane);
        scene.getStylesheets().add(styleSheet.toExternalForm());
        primaryStage.setScene(scene);
    }

    /**
     * Gets the primary {@link Stage} representing the game window.
     *
     * @return The primary {@link Stage} representing the game window.
     */
    @NotNull
    public Stage getPrimaryStage() {
        return this.primaryStage;
    }

}
