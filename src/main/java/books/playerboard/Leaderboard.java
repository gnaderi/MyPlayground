package books.playerboard;

import java.util.*;

public class Leaderboard {
    private final PriorityQueue<Player> topPlayers;
    private final Map<Integer, Player> leaderboard;

    public Leaderboard() {
        topPlayers = new PriorityQueue<>(new PlayerComparator());
        leaderboard = new HashMap<>();
    }

    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();
        leaderboard.addScore(1, 73);   // leaderboard = [[1,73]];
        leaderboard.addScore(2, 56);   // leaderboard = [[1,73],[2,56]];
        leaderboard.addScore(3, 39);   // leaderboard = [[1,73],[2,56],[3,39]];
        leaderboard.addScore(4, 51);   // leaderboard = [[1,73],[2,56],[3,39],[4,51]];
        leaderboard.addScore(5, 4);    // leaderboard = [[1,73],[2,56],[3,39],[4,51],[5,4]];
        System.out.println("leaderboard.top(1) = " + leaderboard.top(1));           // returns 73;
        leaderboard.reset(1);         // leaderboard = [[2,56],[3,39],[4,51],[5,4]];
        leaderboard.reset(2);         // leaderboard = [[3,39],[4,51],[5,4]];
        leaderboard.addScore(2, 51);   // leaderboard = [[2,51],[3,39],[4,51],[5,4]];
        System.out.println("leaderboard.top(3) = " + leaderboard.top(3));// returns 141 = 51 + 51 + 39;
    }

    public void addScore(int playerId, int score) {
        if (!leaderboard.containsKey(playerId)) {
            leaderboard.put(playerId, new Player(playerId, 0));
        }
        Player player = leaderboard.get(playerId);
        player.score = player.score + score;
        leaderboard.put(playerId, player);
        topPlayers.remove(player);
        topPlayers.offer(player);
    }

    public int top(int k) {
        return topPlayers.stream().limit(k).map(p -> p.score).reduce(Integer::sum).orElse(0);
    }

    public void reset(int playerId) {
        Player remove = leaderboard.remove(playerId);
        topPlayers.remove(remove);
    }
}

class Player {
    public int playerId;
    public int score;

    public Player(int playerId, int score) {
        this.playerId = playerId;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return playerId == player.playerId && score == player.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, score);
    }
}

class PlayerComparator implements Comparator<Player> {
    public int compare(Player player1, Player player2) {
        if (player1.score < player2.score)
            return 1;
        else if (player1.score > player2.score)
            return -1;
        return 0;
    }
}