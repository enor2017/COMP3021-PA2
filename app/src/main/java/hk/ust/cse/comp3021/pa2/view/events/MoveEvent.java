package hk.ust.cse.comp3021.pa2.view.events;

import hk.ust.cse.comp3021.pa2.model.MoveResult;
import javafx.event.Event;
import javafx.event.EventType;
import org.jetbrains.annotations.NotNull;

/**
 * A {@link Event} representing a move action in {@link hk.ust.cse.comp3021.pa2.view.panes.GameControlPane}.
 */
public class MoveEvent extends Event {

    /**
     * The result of the move.
     */
    private final MoveResult moveResult;

    /**
     * Creates a new instance of {@link MoveEvent}.
     * @param moveResult The corresponding {@link MoveResult}.
     */
    public MoveEvent(@NotNull MoveResult moveResult) {
        super(EventType.ROOT);
        this.moveResult = moveResult;
    }

    /**
     * Gets the {@link MoveResult} of this move.
     * @return The {@link MoveResult}
     */
    @NotNull
    public MoveResult getMoveResult() {
        return moveResult;
    }
}
