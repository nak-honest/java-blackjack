package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.domain.money.Betting;
import blackjack.domain.money.PlayerProfits;
import blackjack.domain.money.Profit;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.state.HandState;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {
    private static final int INIT_CARD_COUNT = 2;

    private final Deck deck;

    private BlackjackGame(Deck deck) {
        this.deck = deck;
    }

    public static BlackjackGame create() {
        return new BlackjackGame(Deck.createShuffled());
    }

    public Dealer createDealer() {
        return Dealer.newInstance();
    }

    public Players createPlayers(List<String> playerNames) {
        return new Players(playerNames);
    }

    public void updateBettingBoard(Map<Player, Betting> bettingBoard, Player player, long bettingAmount) {
        Betting betting = new Betting(bettingAmount);
        bettingBoard.put(player, betting);
    }

    public void dealInitCards(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            player.receiveInitCards(deck.drawCards(INIT_CARD_COUNT));
        }
        dealer.receiveInitCards(deck.drawCards(INIT_CARD_COUNT));
    }

    public boolean isHittable(Player player) {
        return player.isState(HandState.NOT_BUST);
    }

    public boolean isHit(Dealer dealer) {
        return dealer.hasHitScore();
    }

    public void hit(Dealer dealer) {
        dealer.receiveCard(deck.drawCard());
    }

    public void hit(Player player) {
        player.receiveCard(deck.drawCard());
    }

    public PlayerProfits calculateProfits(Map<Player, Betting> bettingBoard, Dealer dealer) {
        return new PlayerProfits(bettingBoard.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> calculateProfit(dealer, entry.getKey(), entry.getValue()))));
    }

    private Profit calculateProfit(Dealer dealer, Player player, Betting betting) {
        PlayerResult playerResult = PlayerResult.of(dealer, player);

        return playerResult.calculateProfit(betting);
    }
}
