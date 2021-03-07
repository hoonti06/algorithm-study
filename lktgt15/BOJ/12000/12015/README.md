# 12015번 가장 긴 증가하는 부분 수열 2
[문제 보러가기](https://www.acmicpc.net/problem/12015)

## 🅰 설계
이 문제는 사실 well-known이라고 불리는.. 사실은 알면 알고 모르면 모르는 그런 유명한 문제였습니다.  

LIS를 구하는 방법 중에 이분탐색으로 구하는 방법이 있습니다.  

입력이 (10,20,10,30,20,50) 으로 들어올 때 가장 긴 증가하는 부분수열을 저장하는 리스트를 따로 만들어서 업데이트하면서 만들어가면 됩니다.  

`ArrayList<Integer> lis` 로 LIS를 담는 리스트에서 *입력으로 들어오는 수보다 크거나 같은 수 중에 가장 작은 수*를 입력받은 수로 대체해가면서 업데이트 해주겠습니다.

1. 10 => lis에 10보다 크거나 같은 수가 없으므로 `lis.add(10)` => lis = {10}  
2. 20 => lis에 20보다 크거나 같은 수가 없으므로 `lis.add(20)` => lis = {10,20}  
3. 10 => lis에 10보다 크거나 같으면서 가장 작은수 = 10의 인덱스 = 0, `lis.set(0,10)` => lis = {10,20}  
4. 30 => lis에 30보다 크거나 같은 수가 없으므로 `lis.add(30)` => lis = {10,20,30}  
5. 20 => lis에 20보다 크거나 같으면서 가장 작은수 = 20의 인덱스 = 1, `lis.set(1,20)` => lis = {10,20,30}  

이런식으로 업데이트 해주면 이 lis 리스트 자체가 가장 긴 증가하는 부분수열은 되지 않지만 길이는 동일하게 됩니다.  

```java
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> lis = new ArrayList<>(); // lis를 담는 배열
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			int x = Integer.parseInt(st.nextToken());
			int l = -1; // left 범위
			int r = lis.size(); // right 범위
			int mid = 0;
			while(l+1<r) {
				mid = (l+r)/2;
				if(lis.get(mid) < x) l = mid; // lis[mid]가 x보다 작으면 l범위를 좁힘
				else r = mid; // lis[mid]가 x보다 크거나 같으면 r범위를 좁힘
			}
			if(r == lis.size()) { // r 이 lis의 size면 r보다 크거나 같은 lis 원소가 존재하지 않음 
				lis.add(x);
			}
			else { // 그렇지 않으면 lis[r]이 x보다 크거나 같으면서 가장 작은 원소가 됨
				lis.set(r, x);
			}
		}
		System.out.println(lis.size());
	}
}
```


## ✅ 후기
```java
else { // 그렇지 않으면 lis[r]이 x보다 크거나 같으면서 가장 작은 원소가 됨
	lis.set(r, x);
}
```
이 부분을

```java
else {
    Integer c = lis.get(r);
    c = x;
}
```
이렇게 고치면 저는 List에 c의 레퍼런스가 들어있고 그 c를 바꿔주면 List에는 c의 레퍼런스가 들어있으니 같이 바뀔거라고 생각했는데 틀렸습니다.. 혹시 훈수 가능하신분 해주시면 감사하겠습니다

