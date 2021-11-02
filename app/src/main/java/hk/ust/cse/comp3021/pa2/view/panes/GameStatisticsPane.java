package hk.ust.cse.comp3021.pa2.view.panes;

import hk.ust.cse.comp3021.pa2.model.GameState;
import hk.ust.cse.comp3021.pa2.util.NotImplementedException;
import hk.ust.cse.comp3021.pa2.view.GameUIComponent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * A {@link javafx.scene.layout.Pane} for displaying the current statistics of the game.
 */
public class GameStatisticsPane extends GridPane implements GameUIComponent {

    private final Label numGemsLabel = new Label();
    private final Label numMovesLabel = new Label();
    private final Label numUndoesLabel = new Label();
    private final Label numDeathsLabel = new Label();
    private final Label numLivesLabel = new Label();
    private final Label scoreLabel = new Label();

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeComponents() {
        this.setPadding(new Insets(2));

        this.add(numGemsLabel, 0, 0);
        this.add(numMovesLabel, 1, 0);
        this.add(numUndoesLabel, 2, 0);
        this.add(numDeathsLabel, 0, 1);
        this.add(numLivesLabel, 1, 1);
        this.add(scoreLabel, 2, 1);

        for (int i = 0; i < 3; i++) {
            var col = new ColumnConstraints();
            col.setPercentWidth(100.0f / 3.0f);
            this.getColumnConstraints().add(col);
        }
    }

    /**
     * Updates the statistics display with latest {@link GameState}.
     *
     * @param gameState The latest {@link GameState}.
     */
    public void showStatistics(GameState gameState) {
        setNumGems(gameState.getNumGems());
        setNumMoves(gameState.getNumMoves());
        setNumUndoes(gameState.getMoveStack().getPopCount());
        setNumDeaths(gameState.getNumDeaths());
        setNumLives(gameState.getNumLives(), gameState.hasUnlimitedLives());
        setScore(gameState.getScore());
    }

    private void setNumGems(int value) {
        // Update the text of the corresponding label with Gems: <number> (e.g., Gems: 1).
        numGemsLabel.setText("Gems: " + value);
    }

    private void setNumMoves(int value) {
        // Update the text of the corresponding label with Move <number> (e.g., Move: 1).
        numMovesLabel.setText("Move: " + value);
    }

    private void setNumUndoes(int value) {
        // Update the text of the corresponding label with Undoes: <number> (e.g., Undoes: 1).
        numUndoesLabel.setText("Undoes: " + value);
    }

    private void setNumDeaths(int value) {
        // Update the text of the corresponding label with Deaths: <number> (e.g., Deaths: 1).
        numDeathsLabel.setText("Deaths: " + value);
    }

    private void setNumLives(int value, boolean hasUnlimitedLives) {
        // Update the text of the corresponding label with Lives: <number>, \
        //  shows "Unlimited" if the lives are unlimited (e.g., Lives: 1 or Lives: Unlimited).
        numLivesLabel.setText("Lives: " + (hasUnlimitedLives ? "Unlimited" : value));
    }

    private void setScore(int value) {
        // Update the text of the corresponding label with Score: <number> (e.g., Score: 1).
        scoreLabel.setText("Score: " + value);
    }

}
