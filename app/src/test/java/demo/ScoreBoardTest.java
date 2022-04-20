package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(Parameterized.class)
public class ScoreBoardTest {

    static class TestParam {
        final int[] throwBalls;
        final int expectedScore;

        TestParam(int[] throwBalls, int expectedScore) {
            this.throwBalls = throwBalls;
            this.expectedScore = expectedScore;
        }

        static TestParam of(int[] throwBalls, int expectedScore) {
            return new TestParam(throwBalls, expectedScore);
        }
    }

    final TestParam param;

    public ScoreBoardTest(TestParam param) {
        this.param = param;
    }

    @Parameters
    public static List<TestParam> testParams() {
        return List.of(
                TestParam.of(new int[] { 5 }, 5),
                TestParam.of(new int[] { 7 }, 7),
                TestParam.of(new int[] { 3, 5 }, 8),
                TestParam.of(new int[] { 3, 7, 5 }, 20),
                TestParam.of(new int[] { 3, 7, 0, 6, 2 }, 18),
                TestParam.of(new int[] { 0, 10, 3, 7, 8 }, 39),
                TestParam.of(new int[] { 10, 3, 6 }, 28),
                TestParam.of(new int[] { 10, 3, 6, 5, 5, 0, 10, 7 }, 62),
                TestParam.of(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 0),
                TestParam.of(new int[] { 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 }, 300),
                TestParam.of(new int[] { 10, 10, 10, 10, 10, 10, 10, 9, 1, 9, 1, 9, 1, 9 }, 256),
                TestParam.of(new int[] { 10, 10, 10, 10, 10, 10, 10, 9, 1, 9, 1, 9, 0 }, 246));
    }

    @Test
    public void test() {
        var scoreBoard = new ScoreBoard();
        for (var pins : param.throwBalls) {
            scoreBoard.throwBall(pins);
        }
        var score = scoreBoard.score();
        assertEquals(param.expectedScore, score);
    }

}
