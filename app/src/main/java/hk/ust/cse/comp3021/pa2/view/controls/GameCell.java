package hk.ust.cse.comp3021.pa2.view.controls;

import hk.ust.cse.comp3021.pa2.model.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The UI element representing a cell of the game board.
 */
public class GameCell extends ImageView {

    /**
     * Creates a new instance of {@link GameCell}
     *
     * @param cell The {@link Cell} to be displayed.
     */
    public GameCell(Cell cell) {
        Image image = loadImageForCell(cell);
        this.setImage(image);
        this.setFitHeight(40);
        this.setFitWidth(40);
    }

    /**
     * A map for caching the loaded image for the resource names.
     */
    private static final Map<String, Image> IMAGE_CACHE = new HashMap<>();

    /**
     * Loads the image for the specified {@link Cell} with caching mechanism.
     * The same image should be returned when this method is called with two cells with the same type.
     *
     * @param cell The {@link Cell}
     * @return The corresponding {@link Image} for the {@link Cell}.
     */
    @NotNull
    private Image loadImageForCell(Cell cell) {
        var resourceName = getResourceNameByCell(cell);
        return IMAGE_CACHE.computeIfAbsent(resourceName, rn -> {
            var resourceUrl = Objects.requireNonNull(getClass().getResource(rn));
            return new Image(resourceUrl.toExternalForm());
        });
    }

    /**
     * Get the name of the image resource based on the type of the {@link Cell}.
     *
     * @param cell The {@link Cell}.
     * @return The name of the image resource.
     */
    @NotNull
    private static String getResourceNameByCell(Cell cell) {
        return switch (cell.toASCIIChar()) {
            case '@' -> "/images/player.png";
            case '#' -> "/images/stop_cell.png";
            case '*' -> "/images/gem.png";
            case 'L' -> "/images/live.png";
            case 'W' -> "/images/wall.png";
            case 'X' -> "/images/mine.png";
            default -> "/images/blank.png";
        };
    }
}
