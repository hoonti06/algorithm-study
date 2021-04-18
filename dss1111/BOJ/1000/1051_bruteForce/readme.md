# 1051번 숫자 정사각형
[문제 보러가기](https://www.acmicpc.net/problem/1051)

## 🅰 설계
무식하게 브루트포스로 풀었습니다. 그러다보니 시간복잡도가 n^3이네요  
순회하면서 인덱스에 정사각형 길이만큼 더해주어서 꼭짓점을 계산했습니다.
```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Integer,Integer>map = new HashMap<>();
	static int N,M;
	static int[][]arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		arr=new int[N][M];
		for(int i=0;i<N;i++) {
			String str=br.readLine();
			for(int j=0;j<M;j++) {
				arr[i][j]=str.charAt(j)-'0';
			}
		}
		int min=Math.min(M, N);
		int result =1;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int k=1;k<min;k++) {
					if(i+k<N && j+k<M && arr[i][j]==arr[i][j+k] && arr[i][j]==arr[i+k][j] && arr[i][j]==arr[i+k][j+k]) {
						result=Math.max(result,k+1);
					}
				}
			}
		}
		System.out.println(result*result);
	}
}
```
## ✅ 후기
### 새롭게 알게되거나 공유해서 알게된 점
없습니다.  

### 고생한 점
없습니다.   
