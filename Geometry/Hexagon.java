/*
* Amon Al-Khatib
* 7/23/2015
*http://codeforces.com/problemset/problem/560/D
*/


import java.util.ArrayList;
import java.util.Scanner;

public class Hexagon {
	public static void main(String[] args){
		try(Scanner scan = new Scanner(System.in)) {
			
			int[] lengths = new int[6];
			for (int i = 0; i < 6; i++) {
				lengths[i] = scan.nextInt();
			}
			
			ArrayList<Point> list = new ArrayList<Point>();
			list.add(new Point(0,0));
			list.add(new Point(lengths[0], 0));
			list.add(new Point(list.get(1).x + (double)lengths[1]*Math.cos(Math.PI/3.0), (double)lengths[1]*Math.sin(Math.PI/3.0)));
			list.add(new Point(list.get(2).x - (double)lengths[2]*Math.cos(Math.PI/3.0), list.get(2).y+(double)lengths[2]*Math.sin(Math.PI/3.0)));
			list.add(new Point(list.get(3).x - lengths[3], list.get(3).y));
			//list.add(new Point(list.get(4).x - (double)lengths[4]*Math.sin(Math.PI/3.0), list.get(4).y-(double)lengths[4]*Math.cos(Math.PI/3.0)));
			list.add(new Point(0.0 - (lengths[5]*Math.cos(Math.PI/3.0)), 0.0 + lengths[5]*Math.sin(Math.PI/3.0)));
			list.add(new Point(0,0));
			
			double Area = Area(list);
			double tri = Math.sqrt(3) / 4.0;
			
			System.out.println(Math.round((Area/tri)));
			
			
		}
	}
	private static double Area(ArrayList<Point> list) {
		
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			double h = list.get(i).x - list.get((i+1)%list.size()).x;
			sum += .5 * h * (list.get(i).y+list.get((i+1)%list.size()).y);
		}
		
		return Math.abs(sum);
	}

}

class Point {
	double x, y;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}