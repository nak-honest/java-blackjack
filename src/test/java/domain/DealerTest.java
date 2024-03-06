package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {
	List<Card> deck = new ArrayList<>();

	@BeforeEach
	void init() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(suit, rank));
			}
		}
	}

	@DisplayName("딜러는 생성 시에 중복되지 않은 52장의 덱을 갖는다.")
	@Test
	void hasDeckTest() {
		// given & when
		Dealer dealer = new Dealer(deck, new ArrayList<>());

		// then
		assertThat(dealer.deckSize()).isEqualTo(52);
	}

	@DisplayName("딜러는 플레이어들에게 초기 카드를 배분한다.")
	@Test
	void dealInitCardsTest() {
		// given
		Dealer dealer = new Dealer(deck, new ArrayList<>());

		// when
		List<Card> cards = dealer.dealInit();

		// then
		assertThat(cards).hasSize(2);
	}

	@DisplayName("딜러는 자신에게 초기 카드를 배분한다.")
	@Test
	void dealInitCardsSelfTest() {
		// given
		Dealer dealer = new Dealer(deck, new ArrayList<>());

		// when
		dealer.receiveInitCards(dealer.dealInit());

		// then
		assertThat(dealer.getCardHand()).hasSize(2);
	}

	@DisplayName("딜러는 카드 1장을 분배한다.")
	@Test
	void dealCardTest() {
		// given
		Dealer dealer = new Dealer(deck, new ArrayList<>());

		// when
		int beforeDeckSize = dealer.deckSize();
		Card card = dealer.dealCard();

		// then
		Assertions.assertAll(
			() -> assertThat(dealer.deckSize()).isEqualTo(beforeDeckSize - 1),
			() -> assertThat(dealer.deckContains(card)).isFalse()
		);
	}

	@DisplayName("딜러 카드 패의 총 합이 16 이하라면 Hit한다.")
	@Test
	void dealerHitTest() {
		// given
		Dealer dealer = new Dealer(deck,
			new ArrayList<>(List.of(new Card(Suit.HEART, Rank.TEN), new Card(Suit.HEART, Rank.SIX))));
		int beforeDeckSize = dealer.deckSize();

		// when & then
		Assertions.assertAll(
			() -> assertThat(dealer.tryHit()).isTrue(),
			() -> assertThat(dealer.deckSize()).isEqualTo(beforeDeckSize - 1)
		);
	}

	@DisplayName("딜러 카드 패의 총 합이 16 초과라면 Stand한다.")
	@Test
	void dealerStandTest() {
		// given
		Dealer dealer = new Dealer(deck,
			new ArrayList<>(List.of(new Card(Suit.HEART, Rank.TEN), new Card(Suit.HEART, Rank.SEVEN))));
		int beforeDeckSize = dealer.deckSize();

		// when & then
		Assertions.assertAll(
			() -> assertThat(dealer.tryHit()).isFalse(),
			() -> assertThat(dealer.deckSize()).isEqualTo(beforeDeckSize)
		);
	}
}
