# 2019_KAKAO_Developer_intern_04

## 2019 카카오 개발자 겨울 인턴십 코딩테스트 4번

### 1. 문제설명

input으로 방의 갯수 `long k`, 고객들이 원하는 방 번호를 담은 배열 `int[] room_number`가 들어온다. 원하는 방 번호는 순서대로 배정되며 만약 원하는 방이 이미 배정된 방일 경우 원하는 방보다 번호가 크면서 비어있는 방 중 가장 번호가 작은 방을 배정한다. 이때, 각 고객에게 배정되는 방 번호를 순서대로 배열로 담아 return하는 문제

### 2. 풀이 [정확도 100%, 효율성 0%]

`HashMap<Long, Integer>`에 <방번호, 이용고객 번호>의 값들을 저장한다. 앞의 과정을 마치면 `Iterator`를 이용해 이차원 배열에 `key`와 `value`를 저장하고 이용고객 번호를 뜻하는 `value`값에 따라 오름차순으로 정렬한다. 정렬을 마치면 이용고객 순대로 이용하는 방 번호인 `key`값을 가져와 순서대로 배열로 담고 이를 return하여 해결한다.

```java

class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        HashMap<Long, Integer> map = new HashMap<>();
        
        for (int i = 0; i < room_number.length; i++) {
        	long rNum = room_number[i];
        	
        	if (!map.containsKey(rNum)) {
        		map.put(rNum, i+1);
        	} else {
        		while (map.containsKey(rNum)) {
            		rNum++;
        		}
        		map.put(rNum, i+1);
        	}
        }

        long[][] result = new long[room_number.length][2];
        int rIdx = 0;
        for (Iterator<Entry<Long, Integer>> it = map.entrySet().iterator(); it.hasNext(); ) {
        	Entry<Long, Integer> entry = it.next();
        	result[rIdx][0] = entry.getValue();
        	result[rIdx][1] = entry.getKey();
        	rIdx++;
        }

        Arrays.sort(result, new Comparator<long[]>() {

			@Override
			public int compare(long[] l1, long[] l2) {
				return l1[0] > l2[0] ? 1 : -1;
			}
        	
        });
        
        
        for (int i = 0; i < answer.length; i++) {
        	answer[i] = result[i][1];
        }
        
        return answer;
    }
}

```
