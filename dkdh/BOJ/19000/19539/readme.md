# 19539번 사과나무
[문제 보러가기](https://www.acmicpc.net/problem/19539)

## 🅰 설계

나무의 크기를 2와 1의 합으로 나누고, 2와 1의 개수가 같아야 합니다.

서로 다른 나무가 1 씩 커야 한다면 이를 2짜리 물뿌리개로 성장시킬 수 없지만,   
한 나무가 2 만큼 커야 한다면 이는 1짜리 물뿌리개를 두 번 뿌려 성장시킬 수 있습니다.

즉 1 -> 2 는 때에 따라 불가하지만,   
2 -> 1은 무조건 가능합니다.

나무의 크기를 가능한 만큼 2의 합으로 나타내고, 후에 2를 1짜리 두 개로 고려해 개수를 맞출 수 있는지 살펴보는 식으로 풀었습니다.
나무의 크기를 가능한 만큼 2의 합으로 나타내었는데 1이 더 많다면 더 이상의 조정이 불가능하므로 불가능한 것이고,   
2가 더 크다면 2를 1로 바꾸어 개수를 맞춰볼 수 있습니다.

2가 x개이고 1이 y개라고 하면
x-n = y+2n 이 만족해야 2와 1의 개수가 같아지기 때문에   
x-y = 3n 에 따라 x-y가 3의 배수이면 2와 1의 개수가 같아집니다.

```java
public class Main_19539_사과나무 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		int[] tree = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		int one = 0;
		int two = 0;
		for (int i = 0; i < N; i++) {
			two += tree[i]/2;
			one += tree[i]%2;
		}
		
		if(two >= one && (two - one)%3 == 0) System.out.println("YES");
		else System.out.println("NO");
	}

}
```
