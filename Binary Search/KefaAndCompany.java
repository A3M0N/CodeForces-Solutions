import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


public class KefaAndCompany {
	static long[] sum;
	public static void main(String[] args) {
		
		try(Scanner scan = new Scanner(System.in)) {
			
			int n=scan.nextInt(), d = scan.nextInt();
			
			HashMap<Long, Long> map = new HashMap<Long, Long>();
			int count = 0;
			for (int i= 0; i < n; i++){
				long a = scan.nextInt(), b = scan.nextInt();
				if (map.containsKey(a)) map.put(a, map.get(a)+b);
				else{ map.put(a, b);count++;}
			}
			
			Iterator<Long> itr = map.keySet().iterator();
			
			friend[] list = new friend[count];
			sum = new long[count+1];
			
			int k = 0;
			while (itr.hasNext()) {
				long a = itr.next();
				list[k] = new friend(a, map.get(a));
				k++;
			}
			
			Arrays.sort(list);
			
			for (int i=1; i<=count; i++) {
				sum[i]=list[i-1].val+sum[i-1];
			}
			
			long max = Integer.MIN_VALUE;
			for (int i = 0; i < count; i++) {
				best=-1;
				BinSearch(list, i, count-1, list[i].money+d-1);
				max = Math.max(max, sum(i, best));
			}
			
			System.out.println(max);
			
		}
	}
	static int best = -1;
	private static void BinSearch(friend[] list, int i, int j, long max) {
		if (i>j) return;
		int pivot = (i+j) / 2;
		if (list[pivot].money==max) {best = pivot;return;}
		if (list[pivot].money>max)
			BinSearch(list, i, pivot-1, max);
		if (list[pivot].money<max){
			best = Math.max(best, pivot);
			BinSearch(list, pivot+1, j, max);
		}
	}

	private static long sum(int i, int j) {
		return sum[j+1]-sum[i];
	}
}

class friend implements Comparable<friend>{
	long money, val;
	public friend (long money, long val) {
		this.money = money;
		this.val = val;
	}
	@Override
	public int compareTo(friend o) {
		if (this.money < o.money) return -1;
		if (this.money > o.money) return 1;
		else
		return 0;
	}
}