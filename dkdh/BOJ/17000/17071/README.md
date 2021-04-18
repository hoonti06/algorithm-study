# 17071번 숨바꼭질 5
[문제 보러가기](https://www.acmicpc.net/problem/17071)

## 🅰 설계
수빈이와 동생의 숨바꼭질

수빈이 위치 N, 동생의 위치 K. (0≤ N, K ≤ 500,000) 정수

수빈이는 1초 후 x-1, x+1, 2\*x 로 이동.

동생은 처음 위치는 K, 1초가 지난 후 위치는 K+1, 2초가 지난 후 위치는 K+1+2, 3초가 지난 후의 위치는 K+1+2+3이다.

수빈이가 동생을 찾을 수 있는 가장 빠른 시간은 몇 초 후인가?

1. 최단 시간(최소 비용)을 구함
2. 한 번의 이동에 1초가 걸리므로 가중치가 1인 간선으로 연결된 그래프 문제로 풀 수 있음

⇒ BFS를 활용

단, 목적지가 움직이므로 이미 방문한 위치도 다시 방문할 필요가 있음.

초마다 수빈이의 모든 위치를 큐에 담아 탐색하는 방법은 최대 50만 개의 점을 최대 약 루트50만 (1→ 50만까지 동생이 움직이는 시간) 정도 반복하므로 시간 초과가 발생한다.

매 초 꼭 모든 정점을 다시 살펴봐야하는가?   
→ 이동이 x-1, x+1, 2\*x 이기 때문에 -1, +1을 반복하면 2초 뒤에는 다시 x에 방문한다. 즉 어떤 점 x에 t초 후 도달한다면, t+2, t+4, ... 에도 x에 도달할 수 있다.

짝수 초 t에 어떤 점 x에 도달했다면 그 이후 모든 짝수 초에 대해 x에 방문하게 되므로 x를 다시 탐색할 필요가 없고, 홀수 초도 마찬가지이다. 이처럼 짝수 초와 홀수 초를 나눠 고려하면 각각에 대해 한 번 방문한 점에 대해 다시 방문할 필요가 없기 때문에 50만 개의 점을 2번씩만 방문하면 된다.

### 코드
```java
public class Main_17071_숨바꼭질5 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		if(N==K) {
			System.out.println("0");
			System.exit(0);
		}
		
		sc.close();
		
		int MAX = 500000;
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		boolean[][] visited = new boolean[MAX+1][2];
		int dest = K;
		int time = 0;
		
		q.offer(N);
		visited[N][0] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();

			time++;
			dest += time;
			
			// time초 후 방문하는 모든 위치에 대해
			while(size-- > 0) {
				int cur = q.poll();
				
				for (int ncur : new int[]{cur-1, cur+1, cur*2}) {
					if(ncur>=0 && ncur<=MAX
							&& !visited[ncur][time%2]) {
						visited[ncur][time%2] = true;
						q.offer(ncur);
					}

				}
				
			}
			
			
			if(dest > MAX) break;
			
			if(visited[dest][time%2]) {
				System.out.println(time);
				System.exit(0);
			}
		}
		
		System.out.println("-1");
		
	}

}
```

## ✅ 후기
매 탐색마다 해당 시간에 탐색 가능한 모든 정점을 전부 큐에 담았다가 시간초과를 맛봤습니다.
앞으로 짝수 홀수를 떠올려봐야겠네요
