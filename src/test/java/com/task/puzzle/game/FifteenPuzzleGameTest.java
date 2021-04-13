package com.task.puzzle.game;

import com.task.puzzle.game.position.PositionProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class FifteenPuzzleGameTest {
    private static final int GAME_SIZE = 4;
    private static final int[] EXPECTED_RESULT = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};

    @Mock
    private PositionProvider positionProvider;

    @BeforeEach
    void setUp() {
        lenient().when(positionProvider.getNext(any()))
                .then(i -> new Random().nextInt(i.getArgument(0)));
    }

    @Test
    void shouldCreateGameSuccessfully() {
        PuzzleGame testable = new FifteenPuzzleGame(GAME_SIZE, positionProvider);

        assertThat(testable).isNotNull()
                .satisfies(g -> assertThat(g.getTitlesField()).isNotNull());

        final int[] titlesFieldResult = (int[]) testable.getTitlesField();
        assertThat(titlesFieldResult).isNotNull()
                .satisfies(f -> assertThat(f).containsExactlyInAnyOrder(EXPECTED_RESULT));

        verify(positionProvider, atLeastOnce()).getNext(any());
    }

    @Test
    void shouldNotMoveBlankToOtherPosition() {
        PuzzleGame testable = new FifteenPuzzleGame(GAME_SIZE, positionProvider);

        final int initBlankPosition = testable.getBlankPosition();

        final int c = testable.getBlankPosition() % GAME_SIZE;
        final int r = testable.getBlankPosition() / GAME_SIZE;

        testable.move(c, r, testable.getBlankPosition() + 1);

        assertThat(testable).isNotNull()
                .satisfies(g -> assertThat(g.getBlankPosition()).isEqualTo(initBlankPosition))
                .satisfies(g -> assertThat(g.isGameOver()).isFalse());
    }

    @Test
    void shouldMoveBlankToOtherPosition() {
        PuzzleGame testable = new FifteenPuzzleGame(GAME_SIZE, positionProvider);

        final int initBlankPosition = testable.getBlankPosition();

        final int cGrid = (testable.getBlankPosition() % GAME_SIZE) - 1;
        final int rGrid = testable.getBlankPosition() / GAME_SIZE;

        testable.move(cGrid, rGrid, testable.getBlankPosition() - 1);

        assertThat(testable).isNotNull()
                .satisfies(g -> assertThat(g.getBlankPosition()).isNotEqualTo(initBlankPosition))
                .satisfies(g -> assertThat(g.isGameOver()).isFalse());
    }

    @Test
    void shouldFinishGame() {
        final PositionProvider preparedPositionProvider = mock(PositionProvider.class);
        when(preparedPositionProvider.getNext(any())).then(i -> {
            final Integer input = (Integer) i.getArgument(0);
            return input - 1;
        });

        PuzzleGame testable = new FifteenPuzzleGame(GAME_SIZE, preparedPositionProvider);

        final int cGrid_step1 = (testable.getBlankPosition() % GAME_SIZE) - 1;
        final int rGrid_step1 = testable.getBlankPosition() / GAME_SIZE;

        testable.move(cGrid_step1, rGrid_step1, testable.getBlankPosition() - 1);

        final int cGrid_step2 = (testable.getBlankPosition() % GAME_SIZE) + 1;
        final int rGrid_step2 = testable.getBlankPosition() / GAME_SIZE;

        testable.move(cGrid_step2, rGrid_step2, testable.getBlankPosition() + 1);

        assertThat(testable).isNotNull()
                .satisfies(g -> assertThat(g.isGameOver()).isTrue())
                .satisfies(g -> assertThat(g.getTitlesField()).isNotNull()
                .satisfies(f -> assertThat((int[]) f).containsExactly(EXPECTED_RESULT)));
    }

}