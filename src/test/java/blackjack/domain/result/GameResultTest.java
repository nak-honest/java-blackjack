package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @DisplayName("플레이어가 버스트라면 플레이어의 '패'이다.")
    @Test
    void playerBustTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Player player = Player.newInstance("nak");

        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.ACE));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.SEVEN));

        player.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        player.receiveCard(new Card(Suit.CLOVER, Rank.JACK));
        player.receiveCard(new Card(Suit.CLOVER, Rank.TWO));

        // when & then
        assertThat(GameResult.createPlayerResult(dealer, player))
                .isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 블랙잭이고, 플레이어가 블랙잭이 아니라면 플레이어의 '패'이다.")
    @Test
    void dealerBlackjackAndPlayerNotBlackjackTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Player player = Player.newInstance("nak");

        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.ACE));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.KING));

        player.receiveCard(new Card(Suit.CLOVER, Rank.QUEEN));
        player.receiveCard(new Card(Suit.CLOVER, Rank.JACK));
        player.receiveCard(new Card(Suit.DIAMOND, Rank.ACE));

        // when & then
        assertThat(GameResult.createPlayerResult(dealer, player))
                .isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러가 블랙잭이 아니고 플레이어가 블랙잭이라면 플레이어의 '블랙잭 승'이다.")
    @Test
    void dealerNotBlackjackAndPlayerBlackjackTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Player player = Player.newInstance("nak");

        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.JACK));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.KING));

        player.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        player.receiveCard(new Card(Suit.CLOVER, Rank.ACE));

        // when & then
        assertThat(GameResult.createPlayerResult(dealer, player))
                .isEqualTo(GameResult.BLACKJACK_WIN);
    }

    @DisplayName("딜러가 버스트이고 플레이어가 스탠드라면 플레이어의 '승'이다.")
    @Test
    void dealerBustAndPlayerStandTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Player player = Player.newInstance("nak");

        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.JACK));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.KING));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.TWO));

        player.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        player.receiveCard(new Card(Suit.CLOVER, Rank.JACK));

        // when & then
        assertThat(GameResult.createPlayerResult(dealer, player))
                .isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 플레이어 둘다 블랙잭이라면 '무'이다.")
    @Test
    void dealerBlackjackAndPlayerBlackjackTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Player player = Player.newInstance("nak");

        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.JACK));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.ACE));

        player.receiveCard(new Card(Suit.CLOVER, Rank.KING));
        player.receiveCard(new Card(Suit.CLOVER, Rank.ACE));

        // when & then
        assertThat(GameResult.createPlayerResult(dealer, player))
                .isEqualTo(GameResult.DRAW);
    }

    @DisplayName("딜러와 플레이어 둘다 스탠드이고, 플레이어의 점수가 더 높으면 '승'이다.")
    @Test
    void dealerNotBustAndPlayerNotBustWinTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Player player = Player.newInstance("nak");

        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.JACK));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.NINE));

        player.receiveCard(new Card(Suit.CLOVER, Rank.JACK));
        player.receiveCard(new Card(Suit.CLOVER, Rank.KING));

        // when & then
        assertThat(GameResult.createPlayerResult(dealer, player))
                .isEqualTo(GameResult.WIN);
    }

    @DisplayName("딜러와 플레이어 둘다 스탠드이고, 점수도 같다면 '무'이다.")
    @Test
    void dealerNotBustAndPlayerNotBustDrawTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Player player = Player.newInstance("nak");

        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.JACK));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.KING));

        player.receiveCard(new Card(Suit.CLOVER, Rank.JACK));
        player.receiveCard(new Card(Suit.CLOVER, Rank.KING));

        // when & then
        assertThat(GameResult.createPlayerResult(dealer, player))
                .isEqualTo(GameResult.DRAW);
    }

    @DisplayName("딜러와 플레이어 둘다 스탠드이고, 딜러의 점수가 더 높으면 '패'이다.")
    @Test
    void dealerNotBustAndPlayerNotBustLoseTest() {
        // given
        Dealer dealer = Dealer.newInstance();
        Player player = Player.newInstance("nak");

        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.JACK));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.KING));

        player.receiveCard(new Card(Suit.CLOVER, Rank.JACK));
        player.receiveCard(new Card(Suit.CLOVER, Rank.NINE));

        // when & then
        assertThat(GameResult.createPlayerResult(dealer, player))
                .isEqualTo(GameResult.LOSE);
    }
}