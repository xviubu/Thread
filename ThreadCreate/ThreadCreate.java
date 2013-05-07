/*
 * code by lishan
 *2013-5-6
 */

public class ThreadCreate
{
	public static void main(String[] args) 
	{
		Runnable r = new PrintHelloWorld();
		Thread t = new Thread(r);
		t.start();
	}
}

class PrintHelloWorld implements Runnable 
{
	public void run() 
	{
		while(true)
		{
			System.out.println("Hello World!");
		}
	}
}
