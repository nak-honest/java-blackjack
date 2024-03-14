## 블랙잭 기능 구현 문서 🔥

## 도메인 요구사항

- [x] 플레이어 이름 입력 기능
    - [x] 쉼표(,)를 기준으로 이름을 구분한다
    - [x] ️ 이름 시작, 종료 지점이 쉼표(,)일 수 없다
    - [x] ⚠️ 플레이어는 1명 이상 8명 이하여야 한다
    - [x] ⚠️ 이름 길이는 1자 이상 5자 이하여야 한다
    - [x] ⚠️ 이름은 중복되어선 안된다
- [ ] 플레이어 배팅 금액 입력 기능
    - [ ] ⚠️ 배팅 금액은 숫자 이어야 한다.
    - [ ] ⚠️ 배팅 금액은 자연수 이어야 한다.
- [x] 초기 카드 배분 기능
    - [x] 딜러, 플레이어에게 카드 2장 배분
    - [x] 카드 출력
        - [x] 플레이어 카드는 2장 다 보여준다
        - [x] 딜러 카드는 첫 1장만 보여준다
- [x] 플레이어 hit 또는 stand 입력 기능
    - [x] 'y'를 입력받으면 hit
    - [x] 'n'를 입력받으면 stand
        - [x] stand 처리 되면 입력 기회가 다음 플레이어로 넘어간다
    - [x] ⚠️ 입력값이 'y' 또는 'n' 이어야 한다.
    - [x] 현재 패의 총합이 21을 넘으면 다음 입력 기회가 플레이어로 넘어간다
- [x] 딜러 카드 추가 기능
    - [x] 딜러의 카드가 16이하이면, 1장의 카드를 추가한다.
        - [x] 17 이상이 될 때까지 반복한다

## UI 요구사항

- [x] 카드 패 출력 기능
    - [x] 뽑은 카드 순서대로 출력한다
    - [x] 카드 패의 총합을 출력한다
- [ ] 최종 승패 계산
    - [ ] 딜러-플레이어 1:1로 각각 비교
        - [ ] 플레이어가 버스트라면 플레이어의 '패'
        - [ ] 플레이어와 딜러 둘 다 버스트가 아니고, 플레이어의 점수가 딜러의 점수보다 낮으면 '패'
        - [ ] 딜러가 버스트이고 플레이어가 버스트가 아니라면 플레이어 '승'
        - [ ] 플레이어와 딜러 둘 다 버스트가 아니고, 플레이어의 점수가 딜러의 점수보다 높으면 '승'
        - [ ] 플레이어와 딜러 둘 다 버스트가 아니고, 플레이어의 점수와 딜러의 점수가 같으면 '무'
    - [ ] 점수 비교
        - [ ] 한 쪽이 블랙잭(2개 카드 합 21점)이라면, 블랙잭인 사람의 점수가 높다.
        - [ ] 둘 다 블랙잭이라면 동일한 점수이다.
        - [ ] 둘 다 블랙잭이 아니라면 카드에 적혀있는 숫자대로 대소 비교를 한다.
- [ ] 최종 수익 계산
    - [ ] 플레이어가 승리하면 딜러에게 배팅 금액을 받는다.
    - [ ] 플레이어가 블랙잭인 경우 배팅 금액의 1.5배를 받는다.
    - [ ] 플레이어가 블랙잭이 아닌 경우 배팅 금액의 1배를 받는다.
    - [ ] 플레이어가 무승부라면 배팅한 금액을 돌려 받는다.
    - [ ] 플레이어가 패배하면 배팅 금액을 잃는다.
- [x] 최종 수익 출력 기능
    - [ ] 딜러와 각 플레이어의 최종 수익을 출력한다.

---

## 요구 사항

- [x] indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- [x] 3항 연산자를 쓰지 않는다.
- [x] else 예약어를 쓰지 않는다.
- [x] 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- [x] 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다
- [x] 배열 대신 컬렉션을 사용한다.
- [x] 모든 원시 값과 문자열을 포장한다
- [x] 줄여 쓰지 않는다(축약 금지).
- [x] 일급 컬렉션을 쓴다.
- [x] 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- [x] 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

---

## 실행 결과

```
게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
pobi,jason

딜러와 pobi, jason에게 2장을 나누었습니다.
딜러: 3다이아몬드
pobi카드: 2하트, 8스페이드
jason카드: 7클로버, K스페이드

pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
y
pobi카드: 2하트, 8스페이드, A클로버
pobi는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)
n
jason카드: 7클로버, K스페이드

딜러는 16이하라 한장의 카드를 더 받았습니다.

딜러 카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21
jason카드: 7클로버, K스페이드 - 결과: 17

## 최종 승패
딜러: 1승 1패
pobi: 승 
jason: 패
```

## 추가 구현 사항

- 방어적 복사
- 메서드 인자 final 처리
