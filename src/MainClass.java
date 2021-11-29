import java.util.InputMismatchException;

public class MainClass {
	
	public static void main(String[] args) {}

	public static int MOD(int a, int m) {
		if (!(m > 0)) throw new InputMismatchException("Wrong m value");
		if (a > 0)	{if (!(a < m)) while (!(a < m))	a -= m;}
		else		{while (a < 0)	a += m;}
		return a;		
	}
}
