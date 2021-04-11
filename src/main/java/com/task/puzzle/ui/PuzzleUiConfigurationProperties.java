package com.task.puzzle.ui;

import lombok.Data;
import lombok.NonNull;

@Data
public class PuzzleUiConfigurationProperties {
    @NonNull
    private int margin;
    @NonNull
    private int dimension;
    @NonNull
    private int size;
    @NonNull
    private int gridSize;
    @NonNull
    private int tileSize;

    public PuzzleUiConfigurationProperties(@NonNull int margin,
                                           @NonNull int dimension,
                                           @NonNull int size) {
        this.margin = margin;
        this.dimension = dimension;
        this.size = validateSize(size);
        this.gridSize = (dimension - 2 * margin);
        this.tileSize = gridSize / size;
    }

    private static int validateSize(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Size cannot be less than 2");
        }
        return size;
    }
}
