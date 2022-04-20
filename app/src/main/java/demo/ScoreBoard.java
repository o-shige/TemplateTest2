package demo;

import java.util.List;
import java.util.ArrayList;

public class ScoreBoard {

    ArrayList<Integer> balls = new ArrayList<Integer>();

    private static class Frame {
        int itsFrame = 1;

        private void next() {
            itsFrame++;
        }

        private boolean isLast() {
            return itsFrame >= 10;
        }
    }

    interface State {
        int calcScore(List<Integer> balls, int currentScore, int currentIndex);

        boolean needsNextFrame();

        State next();
    }

    static class FirstThrow implements State {

        boolean needsNextFrame = false;
        boolean isFirstScore = true;

        @Override
        public int calcScore(List<Integer> balls, int currentScore, int currentIndex) {
            var result = 0;
            if (isStrike(currentScore)) {
                result = calcFirstThrowBallScoreIfStrike(balls, currentScore, currentIndex);
                needsNextFrame = true;
            } else {
                isFirstScore = false;
            }
            return result;
        }

        @Override
        public boolean needsNextFrame() {
            return needsNextFrame;
        }

        @Override
        public State next() {
            if (isFirstScore) {
                return this;
            } else {
                return new SecondThrow();
            }
        }

        private boolean isStrike(int score) {
            if (score == 10) {
                return true;
            } else {
                return false;
            }
        }

        private int calcFirstThrowBallScoreIfStrike(List<Integer> balls, int score, int currentIndex) {
            var res = 0;

            if (currentIndex + 1 < balls.size()) {
                res += balls.get(currentIndex + 1);
            }
            if (currentIndex + 2 < balls.size()) {
                res += balls.get(currentIndex + 2);
            }
            return res;
        }

    }

    static class SecondThrow implements State {

        @Override
        public int calcScore(List<Integer> balls, int currentScore, int currentIndex) {
            var result = 0;
            if (isSpare(balls, currentScore, currentIndex)) {
                result = calcSecondThrowBallScoreIfSpare(balls, currentScore, currentIndex);
            }

            return result;
        }

        @Override
        public boolean needsNextFrame() {
            return true;
        }

        @Override
        public State next() {
            return new FirstThrow();
        }

        private boolean isSpare(List<Integer> balls, int score, int currentIndex) {
            if (score + balls.get(currentIndex - 1) == 10) {
                return true;
            } else {
                return false;
            }
        }

        private int calcSecondThrowBallScoreIfSpare(List<Integer> balls, int score, int currentIndex) {
            var res = 0;

            if (currentIndex + 1 < balls.size()) {
                res += balls.get(currentIndex + 1);
            }

            return res;
        }
    }

    public void throwBall(int score) {
        balls.add(score);
    }

    public int score() {
        var itsScore = 0;

        var frame = new Frame();
        State state = new FirstThrow();

        for (int i = 0; i < balls.size(); i++) {
            var currentScore = balls.get(i);
            itsScore += currentScore;

            if (frame.isLast()) {
                continue;
            }

            itsScore += state.calcScore(balls, currentScore, i);
            if (state.needsNextFrame()) {
                frame.next();
            }
            state = state.next();
        }
        return itsScore;
    }
}
