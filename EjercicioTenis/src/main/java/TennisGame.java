public class TennisGame {

    public String getScore(int player1Score, int player2Score) {
        if(player1Score<0 || player2Score<0){
            throw new IllegalArgumentException();
        }
        if (player1Score == player2Score) {
            return translateTie(player1Score);
        }
        if (player1Score >= 4 || player2Score >= 4){
            return translateEndGame(player1Score, player2Score);
        }
        return translateScore(player1Score) + "-" + translateScore(player2Score);
    }

    private String translateTie(int score) {
        switch (score) {
            case 0: return "Love-Love";
            case 1: return "Fifteen-All";
            case 2: return "Thirty-All";
            default: return "Deuce";
        }
    }

    private String translateEndGame(int p1, int p2) {
        if ((p1 > 4 && p2 < 3) || (p2 > 4 && p1 < 3)) {
            throw new IllegalArgumentException("Impossible score: Game should have ended earlier.");
        }

        int difference = p1 - p2;

        if (difference == 1) return "Advantage Player 1";
        if (difference == -1) return "Advantage Player 2";
        if (difference >= 2) return "Player 1 wins";

        return "Player 2 wins";
    }

    private String translateScore(int score) {
        switch (score) {
            case 0: return "Love";
            case 1: return "Fifteen";
            case 2: return "Thirty";
            default: return "Forty";
        }
    }
}