import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		long k = 10;
		long[] room_number = {1,3,4,1,3,1};
		// return 	[1,3,4,2,5,6]
		System.out.println(Arrays.toString(new Solution().solution(k, room_number)));
	}

}

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