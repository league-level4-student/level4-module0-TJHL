import java.util.Random;

public class Checkpoint {
	
	public static void main(String[] args) {
	
		int[][] a = new int[5][5];
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				int random= new Random().nextInt(10);
				a[i][j]=random;
			}
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
}
