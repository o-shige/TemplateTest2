package demo;

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

    public void throwBall(int score) {
        balls.add(score);
    }

    public int score() {
        var isFirstScore = true;
        var itsScore = 0;

        var frame = new Frame();

        for (int i = 0; i < balls.size(); i++) {
            var currentScore = balls.get(i);
            itsScore += currentScore;

            if (frame.isLast()) {
                continue;
            }

            if (isFirstScore) {
                if (isStrike(currentScore)) {
                    itsScore += calcFirstThrowBallScoreIfStrike(currentScore, i);
                    frame.next();
                } else {
                    isFirstScore = false;
                }
            } else {
                if (isSpare(currentScore, i)) {
                    itsScore += calcSecondThrowBallScoreIfSpare(currentScore, i);
                }
                isFirstScore = true;
                frame.next();
            }
        }
        return itsScore;
    }

    private int calcFirstThrowBallScoreIfStrike(int score, int currentIndex) {
        var res = 0;

        if (currentIndex + 1 < balls.size()) {
            res += balls.get(currentIndex + 1);
        }
        if (currentIndex + 2 < balls.size()) {
            res += balls.get(currentIndex + 2);
        }
        return res;
    }

    private int calcSecondThrowBallScoreIfSpare(int score, int currentIndex) {
        var res = 0;

        if (currentIndex + 1 < balls.size()) {
            res += balls.get(currentIndex + 1);
        }

        return res;
    }

    private boolean isStrike(int score) {
        if (score == 10) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isSpare(int score, int currentIndex) {
        if (score + balls.get(currentIndex - 1) == 10) {
            return true;
        } else {
            return false;
        }
    }

}
