# 13549번 숨바꼭질3
[문제 보러가기](https://www.acmicpc.net/problem/13549)

## 🅰 설계
숨바꼭질1 과 같은데 순간이동(x2 이동)에 시간이 걸리지 않는다는 점만 다릅니다.

큰 틀로는 bfs를 이용해서 풀었습니다.   
다만 순간이동에 시간이 걸리지 않기 때문에,   
수빈이는 어떤 지점 X에 존재한다면 2X, 4X, 8X, ... 에 동시에 존재하고 있는 것과 같으므로   
최단 시간을 올바르게 계산하기 위해서는 먼저 어떤 지점에 방문했다면 해당 지점에 대해 인덱스를 벗어나기 전까지의 모든 x2 지점을 큐에 넣어줘야 합니다.
```jsx
int ncur = cur*2;
while(ncur>0 && ncur<=Limit) {
	if(times[ncur]==0) {
		times[ncur] = times[cur];
		queue.add(ncur);
	}
	ncur *= 2;
}
```

숨바꼭질 1에서 bfs를 사용했던 것은   
시작 지점 N에 대해 +1, -1, x2 가 1초라는 동일한 가중치를 가진 자식이 되고,   
각 레벨을 탐색한다는 것은 해당 레벨(초)에 대한 모든 가능성을 탐색하는 것이므로   
bfs는 레벨순으로 탐색하기 때문에 탐색 중 만약 도착 지점 K에 도달했다면 해당 레벨(초)가 가장 최단이라는 것이 보장되었기 때문입니다.   
가장 처음 방문한 것이 최단 시간에 도착한 것이라는 것이 보장되었기 때문에 방문 표시로 그 다음 방문에 대해서는 고려하지 않는 것입니다(느려).

숨바꼭질 3은 순간이동에 0초라는 가중치가 부여되므로 bfs를 통해 문제를 풀려면   
위의 숨바꼭질 1처럼 레벨 별 탐색이 각 시간 초 별 탐색이 되어야 합니다.   
이를 위해 0초만에 이동할 수 있는 x2 순간이동을 수행한 지점들은 먼저 큐에 모두 넣어 같은 시각에 발생하는 경우가 우선적으로 탐색되도록 해줘야 합니다.


### 코드
```jsx
package boj;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main_13549_숨바꼭질3 {

	public static void main(String[] args) {
		
		int Limit = 100000;
		
		// 입력 받기
		Scanner sc = new Scanner(System.in);
	
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		sc.close();
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int[] times = new int[Limit+1];
		
		// bfs
		int cur;
		
		// 초기 루트 설정
		times[N] = 1;
		queue.add(N);
		
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			if(cur==K) {
				System.out.println(times[K]-1);
				break;
			}
			
			int ncur = cur*2;
			while(ncur>0 && ncur<=Limit) {
				if(times[ncur]==0) {
					times[ncur] = times[cur];
					queue.add(ncur);
				}
				ncur *= 2;
			}

			ncur = cur-1;
			if(ncur>=0 && times[ncur]==0) {
				times[ncur] = times[cur]+1;
				queue.add(ncur);
			}
			ncur = cur+1;
			if(ncur<=Limit && times[ncur]==0) {
				times[ncur] = times[cur]+1;
				queue.add(ncur);
			}
		}
		
	}

}

```

## ✅ 후기
처음에 보고 숨바꼭질1 이랑 같은 문제인 줄 알고 ?? 했어요ㅋㅋ 다른 그림 찾기하니까 보이더라구요ㅎ..
.
