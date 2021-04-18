# 17413번 단어 뒤집기 2
[문제 보러가기](https://www.acmicpc.net/problem/17413)

## 🅰 설계
<img src="https://user-images.githubusercontent.com/43156636/106006447-92718080-60f8-11eb-9a21-23a217093961.jpg">

### 1. 태그 분리
먼저 문자열과 태그를 분리하여 배열에 담아두었습니다.

태그는 항상 문자열의 시작에 위치하는 규칙을 고려하여 맨 앞부터 태그를 찾고

StringBuilder의 delete를 통해 앞부분을 잘라내며 순차적으로 분리를 하였습니다.

```jsx
StringBuilder str = new StringBuilder(br.readLine()); // 입력 문자열 저장
		StringBuilder ans = new StringBuilder(); // 출력 문자열 저장
		int cnt = 0;
		
		//태그와 문자열을 분리하여 저장할 배열
		for (char c : str.toString().toCharArray())
			if (c == '<')
				cnt++;
		if(cnt == 0) cnt = 1; // 태그가 없는 경우 
		
		String[] sub = new String[cnt * 2];

		cnt = 0;
		// 맨 처음부터 탐색 및 분리
		while (str.length() > 0) {
			// 태그면
			if (str.charAt(0) == '<') {
				int close = str.indexOf(">");

				// 분리
				sub[cnt++] = str.substring(0, close + 1);
				str.delete(0, close + 1);
			}
			// 태그가 아니면
			else {
				int open = str.indexOf("<");
				if (open > 0) { // 뒤에 태그가 있으면
					// 분리
					sub[cnt++] = str.substring(0, open);
					str.delete(0, open);
				
				} else { // 단어만 있으면
					sub[cnt++] = str.toString();
					str.delete(0, str.length());
				}
			}
		}
```
### 2. append 작업
그 후, 분리된 substring들을 StringBuilder를 이용해 출력할 문자열에 차례대로 붙여넣었습니다.

태그가 아닌 substirng은 단어를 분리하고 가공을 거친 후 차례대로 붙여넣었습니다.
```jsx
		for (int i = 0; i < cnt; i++) {
			// 태그면 append
			if (sub[i].charAt(0) == '<') {
				ans.append(sub[i]);
			}
			// 단어들이면
			else {
				// 단어 분리
				String[] word = sub[i].split(" ");

				// 뒤집어 append
				for (String w : word) {
					StringBuilder rvs = new StringBuilder(w);
					rvs.reverse();
					ans.append(rvs).append(" ");
				}
				// 마지막 공백 제거
				ans.deleteCharAt(ans.length() - 1);
			}
		}
		System.out.println(ans.toString());
	}
```

## ✅ 후기

### 고생한 점
문자열을 다루는 문제는 익숙하지 않아서 원하는 문자열 작업의 메소드를 찾고 

콘솔에 출력해보며 디버깅 작업을 하다 보니 시간이 오래 걸렸습니다.

또 에러나 엣지 케이스를 하나하나 예외처리를 하면서 너무 무식하게 푸는 건 아닌가 하는 생각이 들었습니다.

### 새롭게 알게되거나 공유해서 알게된 점
StringBuiler로 웬만한 문자열 처리 작업은 소화할 수 있다는 것을 알았습니다.

또 낯설어 지지 않도록 앞으로 주기적으로 문자열 문제를 풀어야겠습니다.
