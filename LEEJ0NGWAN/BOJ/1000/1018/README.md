# 1018번: 체스판 다시 칠하기

[문제 보러가기](https://www.acmicpc.net/problem/1018)

## 🅰 설계

걍 무식하게 풀었습니다

## 전체 코드

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1018 {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        String[] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        map = new char[N][];

        for (int i=0; i<N; i++)
        map[i] = br.readLine().toCharArray();

        int min=N*M;
        for (int i=0; i<N-7; i++)
        for (int j=0; j<M-7; j++) {
            int b=0, w=0;
            for (int y=i; y<i+8; y++)
            for (int x=j; x<j+8; x++) {
                if ((x+y)%2==0) {
                    b += (map[y][x]=='W')? 1: 0;
                    w += (map[y][x]=='B')? 1: 0;
                }
                else {
                    b += (map[y][x]=='B')? 1: 0;
                    w += (map[y][x]=='W')? 1: 0;
                }
            }
            min = Math.min(min, Math.min(b, w));
        }
        System.out.println(min);
    }
}
```

## ✅ 후기

### 새롭게 알게되거나 공유해서 알게된 점

없습니다

### 고생한 점

없습니다
