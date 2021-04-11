package com.task.puzzle.game.valueadapter;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomIntValueProviderTest {

    private RandomIntValueProvider testable = new RandomIntValueProvider();

    @Test
    void shouldReturnExpectedRandomValue() {
        final Integer result = testable.getNext(5);

        assertThat(result).isNotNull()
                .satisfies( r -> assertThat(r).isGreaterThanOrEqualTo(0));
    }

}