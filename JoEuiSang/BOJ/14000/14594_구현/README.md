# 14594 동방프로젝트
[문제 보러가기](https://www.acmicpc.net/problem/14594)

## 🅰 설계
1. 동아리 방을 관리하는 room class 를 선언했습니다.
2. room 클래스에 그룹의 시작을 알리는 start 와, 그룹의 끝을 알리는 end 변수를 선언하였습니다.




## 주요 코드 설명
### 모든 동방 초기화
```java
public room(int no, int start, int end) {
		this.no = no;
		this.start = start;
		this.end = end;
	}

for (int i = 1; i <= N; i++) {
    rooms[i] = new room(i, i, i);
}
```

### 빅 종빈빌런이 붕괴할 시작, 끝 동방 입력 후 처리
```java
for (int i = 0; i < M; i++) {
    StringTokenizer st = new StringTokenizer(br.readLine(), " ");
    X = Integer.parseInt(st.nextToken()); 	//시작 방
    Y = Integer.parseInt(st.nextToken());	//끝 방

    for (int num = X; num <= Y; num++) {
        //현재 방과 연결된 시작 방에서 끝방까지
        for (int start = rooms[num].start; start <= rooms[num].end; start++) {
            rooms[start].start = rooms[X].start;
            rooms[start].end = rooms[Y].end;
        }
        num = rooms[num].end + 1;	//뛰어 넘어가기
    }
}
```


## ✅ 후기
### 뭔가 연상을 줄여줄 방법이 더 있을거 같아 시도해 보았지만 실패하였습니다. 입력의 크기가 커지는 Large 버젼이 있다는걸 알게 되어 위 코드 그대로 제출해보았지만 시간초과가 떴습니다. 따로 문제를 풀어 보겠습니다. 단순한 구현 문제로 보일지라도 입력의 크기가 커진다면 필요한 알고리즘과 자료구조가 명확해 지는 현상이 당연하지만 신기하게 느껴집니다.