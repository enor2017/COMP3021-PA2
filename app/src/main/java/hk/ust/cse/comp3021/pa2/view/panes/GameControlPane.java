package hk.ust.cse.comp3021.pa2.view.panes;

import hk.ust.cse.comp3021.pa2.controller.GameController;
import hk.ust.cse.comp3021.pa2.model.Direction;
import hk.ust.cse.comp3021.pa2.util.NotImplementedException;
import hk.ust.cse.comp3021.pa2.view.GameUIComponent;
import hk.ust.cse.comp3021.pa2.view.events.MoveEvent;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link javafx.scene.layout.Pane} representing the control area of the game.
 */
public class GameControlPane extends GridPane implements GameUIComponent {

    private final Button upButton = new Button("\u2191");
    private final Button downButton = new Button("\u2193");
    private final Button leftButton = new Button("\u2190");
    private final Button rightButton = new Button("\u2192");

    private final Button undoButton = new Button("UNDO");

    private GameController gameController;

    private final ObjectProperty<EventHandler<MoveEvent>> moveEvent = new ObjectPropertyBase<>() {
        @Override
        public Object getBean() {
            return GameControlPane.this;
        }

        @Override
        public String getName() {
            return "onMove";
        }
    };

    /**
     * Performs a move action towards the specified {@link Direction}.
     *
     * @param direction The {@link Direction} to move.
     */
    private void move(@NotNull Direction direction) {
        // Perform move action on game controller and trigger the move event with its result.
        var result = gameController.processMove(direction);
        moveEvent.get().handle(new MoveEvent(result));
    }

    /**
     * Sets the {@link EventHandler} for the move event.
     *
     * @param handler The handler.
     */
    public void setOnMove(EventHandler<MoveEvent> handler) {
        this.moveEvent.set(handler);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeComponents() {
        setLayout();

        // Setup and add the move buttons.
        setMoveButtonsHandler();
        styleMoveButtons();
        addMoveButtons();

        // Setup and add the undo button.
        setUndoButtonLayout();
        this.add(undoButton, 0, 3, 3, 1);
    }

    private void setUndoButtonLayout() {
        this.undoButton.setMaxWidth(Double.MAX_VALUE);
        GridPane.setFillWidth(undoButton, true);

        // Add event handler for the click event of the undo button.
        undoButton.setOnAction(e -> performUndo());
    }

    /**
     * Set the event handlers for the move buttons
     */
    private void setMoveButtonsHandler() {
        // Add event handler for the click event of the move buttons.
        upButton.setOnAction(e -> move(Direction.UP));
        downButton.setOnAction(e -> move(Direction.DOWN));
        leftButton.setOnAction(e -> move(Direction.LEFT));
        rightButton.setOnAction(e -> move(Direction.RIGHT));
    }

    /**
     * Sets the layout of the panel.
     */
    private void setLayout() {
        this.setPadding(new Insets(10));
        this.setVgap(5);
        this.setHgap(5);
    }

    /**
     * Adds the move buttons to the appropriate position in the grid.
     */
    private void addMoveButtons() {
        // Place the move buttons to appropriate position of the pane.
        this.add(upButton, 1, 0);
        this.add(downButton, 1, 2);
        this.add(leftButton, 0, 1);
        this.add(rightButton, 2, 1);
    }

    /**
     * Sets the CSS class for the move buttons
     */
    private void styleMoveButtons() {
        this.upButton.getStyleClass().add("move-button");
        this.downButton.getStyleClass().add("move-button");
        this.leftButton.getStyleClass().add("move-button");
        this.rightButton.getStyleClass().add("move-button");
    }

    /**
     * Sets the {@link GameController} on which the move actions are performed.
     *
     * @param gameController The {@link GameController}.
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * Performs an undo action on the game.
     */
    public void performUndo() {
        // Perform undo on the game controller and trigger the move event with the latest move result after undo.
        var moveStack = gameController.getGameState().getMoveStack();
        // if nothing to undo, do nothing
        // else, handle it and trigger the MoveEvent
        if (!moveStack.isEmpty()) {
            var result = moveStack.peek();
            gameController.processUndo();
            moveEvent.get().handle(new MoveEvent(result));
        }
    }
}
