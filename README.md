# 사다리 게임
## 진행 방법
* 사다리 게임 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/nextstep-step/nextstep-docs/tree/master/codereview)

## Step1 : 1단계 - 자바8 스트림, 람다, Optional

### 기능 요구사항

#### 림다

- [X] 익명 클래스를 람다로 전환
- [X] 람다를 활용해 중복 제거

#### 스트림

- [X] map, reduce, filter 실습 1
- [X] map, reduce, filter 실습 2

#### Optional

- [X] 요구사항 1 - Optional을 활용해 조건에 따른 반환
- [X] 요구사항 2 - Optional에서 값을 반환
- [X] 요구사항 3 - Optional에서 exception 처리

## Step2 : 2단계 - 사다리(생성)

### 기능 요구사항

- 사다리 게임에 참여하는 사람에 이름을 최대5글자까지 부여할 수 있다. 사다리를 출력할 때 사람 이름도 같이 출력한다.
- 사람 이름은 쉼표(,)를 기준으로 구분한다.
- 사람 이름을 5자 기준으로 출력하기 때문에 사다리 폭도 넓어져야 한다.
- 사다리 타기가 정상적으로 동작하려면 라인이 겹치지 않도록 해야 한다.
  - `|-----|-----|` 모양과 같이 가로 라인이 겹치는 경우 어느 방향으로 이동할지 결정할 수 없다.

### 프로그래밍 요구사항

- 자바 8의 스트림과 람다를 적용해 프로그래밍한다.
- 규칙 6: 모든 엔티티를 작게 유지한다.

### 실행 결과

- 위 요구사항에 따라 4명의 사람을 위한 5개 높이 사다리를 만들 경우, 프로그램을 실행한 결과는 다음과 같다.

```text
참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)
pobi,honux,crong,jk

최대 사다리 높이는 몇 개인가요?
5

실행결과

pobi  honux crong   jk
    |-----|     |-----|
    |     |-----|     |
    |-----|     |     |
    |     |-----|     |
    |-----|     |-----|
```

### 힌트

- 2차원 배열을 ArrayList, Generic을 적용해 구현하면 ArrayList<ArrayList<Boolean>>와 같이 이해하기 어려운 코드가 추가된다.
- 사다리 게임에서 한 라인의 좌표 값을 가지는 객체를 추가해 구현해 본다.

```java
public class Line {
    private List<Boolean> points = new ArrayList<>();

    public Line (int countOfPerson) {
        // 라인의 좌표 값에 선이 있는지 유무를 판단하는 로직 추가
    }
}
```
- 위와 같이 Line 객체를 추가하면 `ArrayList<ArrayList<Boolean>>` 코드를 `ArrayList<Line>` 과 같이 구현하는 것이 가능해 진다.

### 기능 구현 사항

- [X] Direction
  - 각 방향을 관리하는 코드
  - RIGHT, LEFT, DOWN

- [X] Point
  - 한 점을 관리하는 객체
  - 현재 위치와 다음 위치, 마지막 위치를 구한다.
  - 이동한 후의 왼쪽에서부터 오른쪽까지 번호를 index로 구한다.

  ```text
  index(0) false     false => 0
  index(0) false-----true => 1
  index(1) true-----false => 0
  index(1) false-----true => 2
  ```

- [X] Movement
  - 각 점의 방향을 결정하는 객체
  - 왼쪽 첫번째 사람은 왼쪽으로 이동할 수 없다.
    - true false   : 왼쪽 이동 (단 왼쪽 첫번째 사람은 출발시 불가능)
    - false false  : 밑으로 이동
    - false ture   : 오른쪽 이동 (단 오른쪽 첫번째 사람은 출발시 불가능)
    - true true    : 이동 불가(익셉션 처리)

  ```text
  pobi-codeleesh-pongdang
  false  true-----false
  ```

- [X] Line 
  - 사다리 한 라인을 조회한다.

  ```text
  0 false-----true => 1
  1 true-----false => 0
  1 false-----false => 0
  ```

- [X] LineCreator
  - 사다리 한 라인을 생성한다.
  - 라인의 시작은 왼쪽으로 이동을 못한다.
  - 라인의 마지막은 오른쪽으로 이동을 못한다.

- [X] ParticipationName
  - 참가지 이름을 관리
  - 빈 값, 공백의 대한 값 검증

- [X] Result
  - 사다리 결과를 확인
  - 결과 출력을 위한 DTO 활용

### 구현 결과

```text
참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)
pobi, code, pong, dang
최대 사다리 높이는 몇 개인가요?
5
실행 결과
pobi  code  pong  dang 
|     |-----|     |
|     |-----|     |
|     |     |-----|
|-----|     |     |
|-----|     |     |
```

## Step3 : 3단계 - 사다리(생성)

```text
참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)
pobi,honux,crong,jk

실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)
꽝,5000,꽝,3000

최대 사다리 높이는 몇 개인가요?
5

사다리 결과

pobi  honux crong   jk
    |-----|     |-----|
    |     |-----|     |
    |-----|     |     |
    |     |-----|     |
    |-----|     |-----|
꽝    5000  꽝    3000

결과를 보고 싶은 사람은?
pobi

실행 결과
꽝

결과를 보고 싶은 사람은?
all

실행 결과
pobi : 꽝
honux : 3000
crong : 꽝
jk : 5000
```

### 코드 리팩토링

- [X] `LineCreator`, `Point` 
  - [X] 마지막 포인트 생성 추가
- [X] `LineDto`
  - [X] DTO 변환 Stream 수정

### 기능 구현 사항

- [X] `LadderWinningResult`
  - [X] 순수 결과 처리할 객체
  - [X] 인덱스로 사다리 결과 계산
- [X] `LadderWinningResultDto`
  - [X] 화면에서 출력할 DTO 객체
  - [X] 참가한 사람의 대한 최종 결과 저장
- [X] `InputView`
  - [X] 결과 검색 입력 
- [X] `ResultView`
  - [X] 결과 검색 출력