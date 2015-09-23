import java.util.ArrayList;
import java.util.Scanner;

public class KoyoyaColors {
	public static void main(String[] args) {
		try (Scanner scan= new Scanner(System.in)) {
			
			int k = scan.nextInt();
			
			int total = 0;
			long ways = 1;
			for (int i = 0; i < k; i++){
				int n = scan.nextInt();
				total+=n;
				ways = (ways*lucas(total-1, n-1, 1000000007))%1000000007;
			}
			System.out.println(ways);
		}
	}
	
	private static long lucas (long n, long k, long p) {
		
		if (n < k) return 0;
		
		long n_ = n;
		long k_ = k;
		ArrayList<Long> numer = new ArrayList<Long>();
		ArrayList<Long> denom = new ArrayList<Long>();
		
		while (n_!=0){
			numer.add(n_%p);
			n_/=p;
		}
		while(k_!=0){
			denom.add(k_%p);
			k_/=p;
		}
		
		long ans = 1;
		for (int i = 0; i < numer.size(); i++) {
			long a = numer.get(i);
			long b;
			if (i >= denom.size()) b = 0;
			else b = denom.get(i);
			
			ans = (ans*choose(a, b, p))%p;
		}
		return ans;
	}
	
	
	static long choose(long n, long k, long p) {
		//WARNING - DOES NOT CATCH WHEN N < K
		int num_degree= GetDegree(n,p)-GetDegree(n-k,p);
		int den_degree= GetDegree(k,p);
		if(num_degree>den_degree) return 0;
		
		long result = 1;
		
		for (long i = n; i > n-k; --i){
			long ti = i;
			while (ti%p == 0) {
				ti/=p;
			}
			result = (result*ti)%p;
		}
		for (long i = 1; i <=k; ++i){
			long ti = i;
			while (ti%p==0){
				ti/=p;
			}
			result = (result*FastExp(ti, p-2, p))%p;
		}
		return result;
	}

	private static long FastExp(long a, long b, long c) {
		long x = 1; long y = a;
		while (b > 0){
			if(b%2==1)
				x=(x*y)%c;
			y=(y*y)%c;
			b/=2;
		}
		return x%c;
	}

	private static int GetDegree(long n, long p) {
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
