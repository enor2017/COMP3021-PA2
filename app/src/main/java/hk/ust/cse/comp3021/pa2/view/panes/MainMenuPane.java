package hk.ust.cse.comp3021.pa2.view.panes;

import hk.ust.cse.comp3021.pa2.InertiaFxGame;
import hk.ust.cse.comp3021.pa2.view.GameUIComponent;
import hk.ust.cse.comp3021.pa2.view.UIServices;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * A {@link javafx.scene.layout.Pane} representing the main menu of the game.
 */
public class MainMenuPane extends VBox implements GameUIComponent {

    private final InertiaFxGame game;

    private final Label gameTitle = new Label("Inertia Game");

    private final Button startGameButton = new Button("Load Game");

    /**
     * Creates a new instances of {@link MainMenuPane}.
     *
     * @param game The {@link InertiaFxGame}.
     */
    public MainMenuPane(InertiaFxGame game) {
        this.game = game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeComponents() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.gameTitle.getStyleClass().add("game-title");
        this.startGameButton.getStyleClass().add("main-menu-button");
        this.getChildren().addAll(
                gameTitle,
                startGameButton
        );
        this.startGameButton.setOnAction(this::onStartButtonClick);
    }

    /**
     * Event handler for the start game button.
     *
     * @param e The {@link ActionEvent} for the button click.
     */
    private void onStartButtonClick(ActionEvent e) {
        var gameState = UIServices.loadGame(game);
        if (gameState != null) {
            game.showGamePane(gameState);
        }
    }
}
