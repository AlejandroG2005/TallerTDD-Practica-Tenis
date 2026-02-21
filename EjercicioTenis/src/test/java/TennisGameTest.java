import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TennisGameTest {

    static Stream<TestCase> tennisGameProvider() {
        return Stream.of(
                // Puntuaciones básicas
                new TestCase(0, 0, "Love-Love"),
                new TestCase(1, 0, "Fifteen-Love"),
                new TestCase(0, 1, "Love-Fifteen"),
                new TestCase(1, 1, "Fifteen-All"),
                new TestCase(2, 0, "Thirty-Love"),
                new TestCase(0, 2, "Love-Thirty"),
                new TestCase(2, 2, "Thirty-All"),
                new TestCase(3, 0, "Forty-Love"),
                new TestCase(0, 3, "Love-Forty"),

                // Deuce
                new TestCase(3, 3, "Deuce"),
                new TestCase(4, 4, "Deuce"),
                new TestCase(6, 6, "Deuce"),
                new TestCase(155, 155, "Deuce"),

                // Ventajas
                new TestCase(4, 3, "Advantage Player 1"),
                new TestCase(3, 4, "Advantage Player 2"),
                new TestCase(5, 4, "Advantage Player 1"),
                new TestCase(4, 5, "Advantage Player 2"),
                new TestCase(179, 178, "Advantage Player 1"),

                // Victorias
                new TestCase(4, 0, "Player 1 wins"),
                new TestCase(0, 4, "Player 2 wins"),
                new TestCase(5, 3, "Player 1 wins"),
                new TestCase(3, 5, "Player 2 wins"),
                new TestCase(6, 4, "Player 1 wins"),
                new TestCase(178, 180, "Player 2 wins")

        );
    }

    static Stream<TestCase> invalidCasesProvider() {
        return Stream.of(
                new TestCase(-1, 0, "Negative score"),
                new TestCase(0, -5, "Negative score"),
                new TestCase(4, -1, "Negative score"),
                new TestCase(5, 0, "Impossible score (should have won at 4-0)"),
                new TestCase(0, 5, "Impossible score (should have won at 0-4)"),
                new TestCase(5, 1, "Impossible score (should have won at 4-1)"),
                new TestCase(5, 2, "Impossible score (should have won at 4-2)")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidCasesProvider")
    void should_throw_exception_for_invalid_scores(TestCase testCase) {
        // Arrange
        TennisGame game = new TennisGame();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {

            game.getScore(testCase.player1Score, testCase.player2Score);
        });
    }
    @ParameterizedTest
    @MethodSource("tennisGameProvider")
    void should_return_correct_tennis_score(TestCase testCase) {
        // Arrange
        TennisGame game = new TennisGame();

        // Act
        String result = game.getScore(testCase.player1Score, testCase.player2Score);

        // Assert
        assertEquals(testCase.expectedScore, result);
    }

    static class TestCase {
        int player1Score;
        int player2Score;
        String expectedScore;

        TestCase(int player1Score, int player2Score, String expectedScore) {
            this.player1Score = player1Score;
            this.player2Score = player2Score;
            this.expectedScore = expectedScore;
        }

        @Override
        public String toString() {
            return String.format("%d-%d should be \"%s\"", player1Score, player2Score, expectedScore);
        }
    }
}