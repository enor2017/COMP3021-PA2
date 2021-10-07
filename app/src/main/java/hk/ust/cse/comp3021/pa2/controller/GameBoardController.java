package hk.ust.cse.comp3021.pa2.controller;

import hk.ust.cse.comp3021.pa2.model.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Controller for {@link GameBoard}.
 *
 * <p>
 * This class is responsible for providing high-level operations to mutate a {@link GameBoard}. This should be the only
 * class which mutates the game board; Other classes should use this class to mutate the game board.
 * </p>
 */
public class GameBoardController {

    @NotNull
    private final GameBoard gameBoard;

    /**
     * Creates an instance.
     *
     * @param gameBoard The instance of {@link GameBoard} to control.
     */
    public GameBoardController(@NotNull final GameBoard gameBoard) {
        this.gameBoard = Objects.requireNonNull(gameBoard);
    }

    /**
     * Moves the player in the given direction.
     *
     * <p>
     * You should ensure that the game board is only mutated if the move is valid and results in the player still being
     * alive. If the player dies after moving or the move is invalid, the game board should remain in the same state as
     * before this method was called.
     * </p>
     *
     * @param direction Direction to move the player in.
     * @return An instance of {@link MoveResult} representing the result of this action.
     */
    @NotNull
    public MoveResult makeMove(@NotNull final Direction direction) {
        Objects.requireNonNull(direction);

        assert gameBoard.getPlayer().getOwner() != null;

        final var origPosition = gameBoard.getPlayer().getOwner().getPosition();
        final var tryMoveResult = tryMove(origPosition, direction);
        if (tryMoveResult instanceof MoveResult.Valid.Alive alive) {
            // Clear all outstanding entities that the player would've picked up
            for (@NotNull final var gemPos : alive.collectedGems) {
                gameBoard.getEntityCell(gemPos).setEntity(null);
            }
            for (@NotNull final var extraLifePos : alive.collectedExtraLives) {
                gameBoard.getEntityCell(extraLifePos).setEntity(null);
            }

            // Move the player directly over
            gameBoard.getEntityCell(alive.newPosition).setEntity(gameBoard.getPlayer());
        }

        return tryMoveResult;
    }

    /**
     * Undoes a move by reverting all changes performed by the specified move.
     *
     * <p>
     * Hint: Undoing a move is effectively the same as reversing everything you have done to make a move.
     * </p>
     *
     * @param prevMove The {@link MoveResult} object to revert.
     */
    public void undoMove(@NotNull final MoveResult prevMove) {
        Objects.requireNonNull(prevMove);
        if (!(prevMove instanceof final MoveResult.Valid.Alive aliveState)) {
            return;
        }

        // Effectively makeMove, but reversed
        gameBoard.getEntityCell(aliveState.origPosition).setEntity(gameBoard.getPlayer());

        for (@NotNull final var gemPos : aliveState.collectedGems) {
            gameBoard.getEntityCell(gemPos).setEntity(new Gem());
        }
        for (@NotNull final var extraLifePos : aliveState.collectedExtraLives) {
            gameBoard.getEntityCell(extraLifePos).setEntity(new ExtraLife());
        }
    }

    /**
     * Tries to move the player from a position in the specified direction as far as possible.
     *
     * <p>
     * Note that this method does <b>NOT</b> actually move the player. It just tries to move the player and return
     * the state of the player as-if it has been moved.
     * </p>
     *
     * @param position  The original position of the player.
     * @param direction The direction to move the player in.
     * @return An instance of {@link MoveResult} representing the type of the move and the position of the player after
     * moving.
     */
    @NotNull
    private MoveResult tryMove(@NotNull final Position position, @NotNull final Direction direction) {
        Objects.requireNonNull(position);
        Objects.requireNonNull(direction);

        final var collectedGems = new ArrayList<Position>();
        final var collectedExtraLives = new ArrayList<Position>();
        Position lastValidPosition = position;
        do {
            final Position newPosition = offsetPosition(lastValidPosition, direction);
            if (newPosition == null) {
                break;
            }

            lastValidPosition = newPosition;

            if (gameBoard.getCell(newPosition) instanceof StopCell) {
                break;
            }

            if (gameBoard.getCell(newPosition) instanceof EntityCell entityCell) {
                if (entityCell.getEntity() instanceof Mine) {
                    return new MoveResult.Valid.Dead(position, newPosition);
                }

                if (entityCell.getEntity() instanceof Gem) {
                    collectedGems.add(newPosition);
                } else if (entityCell.getEntity() instanceof ExtraLife) {
                    collectedExtraLives.add(newPosition);
                }
            }
        } while (true);

        if (lastValidPosition.equals(position)) {
            return new MoveResult.Invalid(position);
        }

        return new MoveResult.Valid.Alive(lastValidPosition, position, collectedGems, collectedExtraLives);
    }

    /**
     * Offsets the {@link Position} in the specified {@link Direction} by one step.
     *
     * @param position  The original position.
     * @param direction The direction to offset.
     * @return The given position offset by one in the specified direction. If the new position is outside of the game
     * board, or contains a non-{@link EntityCell}, returns {@code null}.
     */
    @Nullable
    private Position offsetPosition(
            @NotNull final Position position,
            @NotNull final Direction direction
    ) {
        Objects.requireNonNull(position);
        Objects.requireNonNull(direction);

        final var newPos = position.offsetByOrNull(direction.getOffset(), gameBoard.getNumRows(), gameBoard.getNumCols());

        if (newPos == null) {
            return null;
        }
        if (!(gameBoard.getCell(newPos) instanceof EntityCell)) {
            return null;
        }

        return newPos;
    }
}
