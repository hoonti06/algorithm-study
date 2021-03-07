# 2533번 사회망 서비스(SNS)
[문제 보러가기](https://www.acmicpc.net/problem/2533)

## 🅰 설계
트리의 특징은 계층관계를 이룬다는 것이고 그런 계층관계는 항상 재귀적으로 풀린다는 것입니다.  

문제에서 root는 주어지지 않는데 어떤 번호에서 시작하여도 트리의 전체 구조는 바뀌지 않기 때문에 동일한 결과를 얻을 수 있습니다.  

### 1. 입력

친구의 관계 (a,b)는 무향 edge로 주어지기 때문에 a에서 b로 갈수도, b에서 a로 갈수도 있어야 합니다.  

```java
n = Integer.parseInt(br.readLine());
dp = new int[n+1][2];
path = new List[n+1];
for(int i=1;i<=n;i++) { // dp 배열을 -1로 초기화, path 인접 리스트를 생성
	dp[i][0] = dp[i][1] = -1;
	path[i] = new ArrayList<>();
}
for(int i=0;i<n-1;i++) { // 무향 그래프를 만듦
	st = new StringTokenizer(br.readLine());
	int a = Integer.parseInt(st.nextToken());
	int b = Integer.parseInt(st.nextToken());
	path[a].add(b);
	path[b].add(a);
}
```

### 2. 탑다운 dp함수 정의

문제에서는 트리 구조로 친구 관계가 주어진다고 했으므로 root노드를 제외한 각 노드의 부모는 하나입니다.  

그런데 입력은 무향 edge로 받았으니까 탐색 단계에서 자신의 이전 노드 (= 부모 노드)만 제외하고 탐색하면 트리처럼 탐색할 수 있습니다.  

```java
static int f(int cur,int prev,int early) { // cur 노드에서 early일 때 남은 노드들을 최소로 얼리어답터로 만드는 경우
	if(dp[cur][early] != -1) return dp[cur][early];
	int ret = early; // 자신이 early ( 1 == 얼리어답터, 0 == 얼리어답터가 아님 )
	
	for(int nxt : path[cur]) { // 인접 리스트 확인
		if(nxt == prev) continue; // 이전 노드면 pass
		
		if(early == 1) { // 자신이 얼리어답터면 다음 노드는 얼리어답터일 수도 있고 아닐수도 있음
			ret += Math.min(f(nxt,cur,0),f(nxt,cur,1));
		}
		else { // 자신이 얼리어답터가 아니면 다음 노드는 무조건 얼리어답터여야 함
			ret += f(nxt,cur,1);
		}
	}
	
	return dp[cur][early] = ret;
}
```
탑다운 dp 함수의 정의입니다. *cur 노드에서 early일 때 남은 노드들을 최소로 얼리어답터로 만드는 경우*가 됩니다.  
1. 자신이 얼리어답터인 경우 : 다음 노드는 얼리어답터일 수도, 얼리어답터가 아닐 수도 있습니다.  
2. 자신이 얼리어답터가 아닌 경우 : 친구 관계가 모두 얼리어답터여야 하므로 다음 노드는 반드시 얼리어답터여야 합니다.  

이를 그대로 구현해주면 됩니다.

### 전체코드
```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	static int n;
	static List<Integer>[] path;
	static int[][] dp;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new int[n+1][2];
		path = new List[n+1];
		for(int i=1;i<=n;i++) { // dp 배열을 -1로 초기화, path 인접 리스트를 생성
			dp[i][0] = dp[i][1] = -1;
			path[i] = new ArrayList<>();
		}
		for(int i=0;i<n-1;i++) { // 무향 그래프를 만듦
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			path[a].add(b);
			path[b].add(a);
		}
		
		System.out.println(Math.min(f(1,0,1), f(1,0,0))); // cur = 시작 노드를 n으로 바꿔도 동일하게 답을 얻을 수 있음
	}
	
	static int f(int cur,int prev,int early) { // cur 노드에서 early일 때 남은 노드들을 최소로 얼리어답터로 만드는 경우
		if(dp[cur][early] != -1) return dp[cur][early];
		int ret = early; // 자신이 early ( 1 == 얼리어답터, 0 == 얼리어답터가 아님 )
		
		for(int nxt : path[cur]) { // 인접 리스트 확인
			if(nxt == prev) continue; // 이전 노드면 pass
			
			if(early == 1) { // 자신이 얼리어답터면 다음 노드는 얼리어답터일 수도 있고 아닐수도 있음
				ret += Math.min(f(nxt,cur,0),f(nxt,cur,1));
			}
			else { // 자신이 얼리어답터가 아니면 다음 노드는 무조건 얼리어답터여야 함
				ret += f(nxt,cur,1);
			}
		}
		
		return dp[cur][early] = ret;
	}
}
```


## ✅ 후기
트리 dp라는 거창한 태그를 달고 있지만 트리의 특성만 잘 알고 있다면 제약조건이 좀 생기기 때문에 그렇게 까다롭지는 않은 문제였던것 같습니다. 
