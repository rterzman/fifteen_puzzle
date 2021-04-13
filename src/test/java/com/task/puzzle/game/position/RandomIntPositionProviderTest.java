package com.task.puzzle.game.position;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomIntPositionProviderTest {

    private RandomIntPositionProvider testable = new RandomIntPositionProvider();

    @Test
    void shouldReturnExpectedRandomValue() {
        final Integer result = testable.getNext(5);

        assertThat(result).isNotNull()
                .satisfies( r -> assertThat(r).isGreaterThanOrEqualTo(0));
    }

}