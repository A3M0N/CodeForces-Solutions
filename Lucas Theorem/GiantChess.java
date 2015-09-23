import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GiantChess {
	
	static long[] fact = new long[1000001];
	static long[] modinv = new long[1000001];
	public static void main(String[] args) {
		long P = 1000000007;
		fact[0] = 1;
		modinv[0] = 1;
		for (int i = 1; i < 1000001; i++) fact[i] = (fact[i-1]*i)%P;
		for (int i = 1; i < 1000001; i++) modinv[i] = FastExp(fact[i], P-2, P);
		
		try (Scanner scan = new Scanner(System.in)){
			
			int h = scan.nextInt(), w = scan.nextInt(), n = scan.nextInt();
			ArrayList<Square> list = new ArrayList<Square>();
			for (int i = 0; i < n; i++) {
				int row = scan.nextInt()-1;
				int col = scan.nextInt()-1;
				
				list.add(new Square(col, row));
			}
			
			list.add(new Square(w-1, h-1));
			
			Collections.sort(list);
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).paths = (int) choose(list.get(i).x+list.get(i).y, list.get(i).x, P);
				for (int j = 0; j < i; j++) {
					int xDiff = list.get(i).x - list.get(j).x;
					int yDiff = list.get(i).y - list.get(j).y;
					
					if (yDiff < 0) continue;
					
					list.get(i).paths = ModSub((int)list.get(i).paths, 
							(int)((list.get(j).paths*choose(xDiff+yDiff, xDiff, P))%P), P);
				}
			}
			System.out.println(list.get(list.size()-1).paths);
		}
	}
	
	
	
	static int ModSub(int a, int b, long P) {
		int val = (a-b);
		while (val < 0){
			val+=P;
		}
		return val;
	}
	
	static long choose(int n, int k, long p) {
		//WARNING - DOES NOT CATCH WHEN N < K
		int num_degree= GetDegree(n,p)-GetDegree(n-k,p);
		int den_degree= GetDegree(k,p);
		if(num_degree>den_degree) return 0;
		
		long result = 1;
		
		result = fact[n];
		
		result = (result*modinv[k])%p;
		result = (result*modinv[n-k])%p;
		return result;
	}

	private static long FastExp(long a, long b, long c) {
		if(a==1) return 1;
		long x = 1; long y = a;
		while (b > 0){
			if(b%2==1)
				x=(x*y)%c;
			y=(y*y)%c;
			b/=2;
		}
		return x%c;
	}

	private static int GetDegree(int n, long p) {
		int degree = 0;
		long u = p;
		long temp = n;
		while (u<=temp){
			degree+=temp/u;
			u*=p;
		}
		return degree;
	}
}

class Square implements Comparable<Square>{
	int x, y;
	long paths;
	
	public Square (int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Square s) {
		if (this.x < s.x) return -1;
		if (this.x > s.x) return 1;
		else {
			if (this.y < s.y) return -1;
			if (this.y > s.y) return 1;
			else return 0;
		}
	}
}
