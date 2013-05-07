/*code by lishan
 *2013-5-6
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ThreadTest
{
	public static void main(String[] args)
	{
		JFrame frame = new ThreadFrame();		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class ThreadFrame extends JFrame 
{
	public ThreadFrame()
	{

		setTitle("Thread Test");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);	

		JPanel panel = new JPanel();
		runButton = new JButton("Run"); 
		runButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if(t == null)
					t = new PrintHelloWorld();	
				t.start();
			}
		});
		
		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				t.done = false;
				try
				{
			 		t.join(); //等待指定的线程运行结束
					//System.out.println(t.getName() + " is dead !!!"); //t.getName() 得到线程名字
				}
				catch(InterruptedException e)
				{
			 
				}
			/*	while(t.isAlive()) //isAlive() 判断线程是否处于活动状态
				{
			 		try	
					{
				 		Thread.sleep(2000);
					}
					catch(InterruptedException e)
					{
				 		
					}
				}
			*/
				t = null;
			}
		});
		allthread = new JTextArea(50,50);
		printButton = new JButton("Print");
		printButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
		 		printThreads();
			}
		});
	    exitButton = new JButton("Exit");	
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
		 		System.exit(1);
			}
		});
		add(panel);
		panel.add(runButton);
		panel.add(stopButton);
		panel.add(printButton);
		panel.add(exitButton);
		panel.add(allthread);
	}

	public void printThreads() //打印所有线程
	{
		Thread[] threads = new Thread[Thread.activeCount()]; //activeCount() 返回程序中的线程数包括已创建但没运行的

		int n = Thread.enumerate(threads); //枚举所有线程,将线程放在一个Thread[] 中,返回 Thread[] 的个数

		for( int i = 0 ;i < n ;i++)
		{
			allthread.append("Thread " + i + " is " + threads[i].getName()+"\n");
		}
	}
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	private JButton runButton;
	private JButton stopButton;
	private JButton printButton;
	private JButton exitButton;
	private JTextArea allthread;
	private PrintHelloWorld t;
}
class PrintHelloWorld extends Thread
{
	public PrintHelloWorld()
	{
		//super("Thread \"Hello World\""); //设置线程名字
		setName("Thread \"Hello World\"");
		this.done = true;
	}

	public void run()
	{
		while(done)
		{
			System.out.println("Hello World !");
		}
	}

	public volatile boolean done;
}
