package hk.ust.cse.comp3021.pa2.view.panes;

import hk.ust.cse.comp3021.pa2.InertiaFxGame;
import hk.ust.cse.comp3021.pa2.controller.GameController;
import hk.ust.cse.comp3021.pa2.model.GameState;
import hk.ust.cse.comp3021.pa2.util.NotImplementedException;
import hk.ust.cse.comp3021.pa2.view.GameUIComponent;
import hk.ust.cse.comp3021.pa2.view.events.MoveEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * A {@link javafx.scene.layout.Pane} representing the game play interface of the game.
 */
public class MainGamePane extends VBox implements GameUIComponent {

    private final Label gameTitle = new Label("Inertia Game");

    private final GameBoardPane gameBoard = new GameBoardPane();

    private final GameStatisticsPane gameStatisticsPane = new GameStatisticsPane();

    private final GameControlPane gameControlPane = new GameControlPane();

    private final GameState gameState;

    private final GameController gameController;

    private final InertiaFxGame game;

    /**
     * Creates a new instance of {@link MainGamePane}.
     *
     * @param gameState The {@link GameState} to start playing.
     */
    public MainGamePane(GameState gameState, InertiaFxGame game) {
        this.gameState = gameState;
        this.gameController = new GameController(gameState);
        this.game = game;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeComponents() {
        this.gameTitle.getStyleClass().add("game-title");

        // Initialize and add the game board pane
        this.gameBoard.initializeComponents();
        this.gameBoard.showGameState(gameState);

        // Initialize and add the game static pane
        this.gameStatisticsPane.initializeComponents();
        this.gameStatisticsPane.showStatistics(gameState);

        var operationArea = new HBox(
                gameBoard,
                gameControlPane
        );
        this.getChildren().addAll(
                gameTitle,
                operationArea,
                gameStatisticsPane
        );
        VBox.setVgrow(operationArea, Priority.ALWAYS);
        HBox.setHgrow(gameBoard, Priority.ALWAYS);
        this.gameControlPane.initializeComponents();
        this.gameControlPane.setGameController(gameController);
        this.gameControlPane.setOnMove(this::gameMoveHandler);
    }

    /**
     * {@link javafx.event.Event} handler for a game move operation triggered by {@link GameControlPane}.
     *
     * @param e The corresponding {@link MoveEvent}.
     */
    private void gameMoveHandler(MoveEvent e) {
        // TODO: Update the game board and game statistics

        // TODO: Show a dialog if the user wins the game or loses the game.
        throw new NotImplementedException();
    }
}
