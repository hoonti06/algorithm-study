# 9663번 N-Queen
[문제 보러가기](https://www.acmicpc.net/problem/9663)

## 🅰 설계

큰 틀은 재귀와 isSelected 배열을 통한 순열 구하기와 같습니다.

체스의 퀸은 가로, 세로, 대각선으로 이동하며 공격할 수 있기 때문에 이러한 경로가 겹치면 안 됩니다.

모든 경우의 수를 구하는 것이므로 한 행씩 보면서 퀸을 둘 자리를 선택하고   
해당 자리를 선택함에 따라 중복방지를 위한 표시를 하고   
재귀로 들어가서 또 고르고   
나와서 중복방지를 위한 표시를 해제하는   
방식입니다.

### 중복 방지
중복방지를 위한 표시를 어떻게 할 것인가가 첫 번째 고민점이었습니다.   
처음에는 2차원 boolean 배열에 선택 가능한 자리는 false로 생각하고   
퀸을 둘 자리를 선택함에 따라 그 퀸이 공격할 수 있는 가로, 세로, 대각선을 죄다 true로 바꿔서 중복을 방지하는 방법을 생각했었는데   
재귀로 들어갔다가 나왔을 때 이 true를 다시 false로 바꿔줘야 하는데   
이 전 행들에 둔 퀸 때문에 true인 건지 방금 전 선택한 퀸 때문에 true인 건지 구별하는 문제가 복잡해져서 그만뒀습니다.

그래서 다른 방법을 생각했는데요
가로, 세로, 대각선에 대해 각각 1차원의 isSelected 배열을 두는 방식입니다.가
가로는 사실 행 별로 퀸을 둘 자리를 선택하기 때문에 중복방지 때문에 필요한 것은 아니고,   
한 행을 지났는데 선택된 퀸이 없다면 방법이 없는 것이므로 return해야 하는데 그 때 사용합니다.   
가로와 세로는 각각 선택한 행/열 인덱스에 대해 true로 바꾸도록 했고,   

대각선도 잘 살펴보니 규칙성이 있었습니다.   
왼쪽위->오른쪽아래로 내려오는 대각선에 대해 생각해보면, 같은 대각선상에 있는 인덱스 i, j는 그 차가 같습니다.   
오른쪽위->왼쪽아래로 내려오는 대각선에 대해 생각해보면, 같은 대각선상에 있는 인덱스 i, j는 그 합이 같습니다.   
이를 기반으로 대각선에 대한 선택 확인 배열 두 가지를 만들어서 대각선에 대한 중복을 방지했습니다.

### 코드
```jsx
package boj;

import java.util.Scanner;

// 13872kb 2984ms
public class Main_9663_NQueen {
	
	public static int N;
	
	public static boolean[] rowSelected;
	public static boolean[] colSelected;
	public static boolean[] crossSelected1;
	public static boolean[] crossSelected2;
	
	public static int totalCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		sc.close();

		rowSelected = new boolean[N]; // 가로
		colSelected = new boolean[N]; // 세로
		crossSelected1 = new boolean[2*N-1]; // 차 (대각 왼위->오아래)
		crossSelected2 = new boolean[2*N-1]; // 합 (대각 오위->왼아래)
		
		nQueen(0);
		System.out.println(totalCnt);
	}
	
	public static void nQueen(int rStart) {
		
		if(rStart==N) {
			totalCnt++;
			return;
		}
		
		for (int i = rStart; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!rowSelected[i] && !colSelected[j]
						&& !crossSelected1[i-j+N-1]
								&& !crossSelected2[i+j]) {
					rowSelected[i] = true;
					colSelected[j] = true;
					crossSelected1[i-j+N-1] = true;
					crossSelected2[i+j] = true;
					
					nQueen(i+1);
					
					rowSelected[i] = false;
					colSelected[j] = false;
					crossSelected1[i-j+N-1] = false;
					crossSelected2[i+j] = false;
				}
			}
			// 한 행에 선택된 칸이 하나도 없으면 경우의 수가 없는 것이므로 return
			if(!rowSelected[i]) {
				return;
			}
		}
		
		return;
	}
	
	
}

```
