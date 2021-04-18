# 1697번 숨바꼭질
[문제 보러가기](https://www.acmicpc.net/problem/1697)

## 🅰 설계

<img src="https://user-images.githubusercontent.com/69133236/107168440-bf7a3900-69fe-11eb-9a46-0e55efa26b3a.jpg" height="400">
처음엔 메모이제이션으로 접근하였는데 그렇게 한다면 뒤로 빠졌다가 가는 경우를 알지 못하는 문제가 있었고, 고민 끝내 bfs로 접근하였다.

## 주요 코드
### 각 자리별 걸리는 시간을 저장할 배열
```java
static int[] time;
```

### bfs를 활용한 움직임
```java
private static void move(int N) {
    queue.offer(N);

    while (!queue.isEmpty()) {
        int X = queue.poll();

        if (X == K)
            break;

        if (X - 1 >= 0 && time[X - 1] == 0) {
            queue.offer(X - 1);
            time[X - 1] = time[X] + 1;
        }
        if (X + 1 <= 100000 && time[X + 1] == 0) {
            queue.offer(X + 1);
            time[X + 1] = time[X] + 1;
        }
        if (X * 2 <= 100000 && time[X * 2] == 0) {
            queue.offer(X * 2);
            time[X * 2] = time[X] + 1;
        }
    }
}
```


## ✅ 후기
### 다양한 문제를 더 많이 풀어보면서  문제를 분석하고 판단하는 능력을 길러야겠다. 
### 큐를 응용하는 bfs를 구현하는데 어려움이 있어 시간이 좀 걸렸지만 조금씩 적응해 나가는 느낌이 들어 기분이 좋다...
