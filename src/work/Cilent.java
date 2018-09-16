package work;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cilent {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client=new Socket("localhost",8088);//连接服务器
		Scanner input=new Scanner(System.in);//键盘输入数据
		Scanner scan=new Scanner(client.getInputStream());//接收服务器回应
		PrintStream out=new PrintStream(client.getOutputStream());
		boolean flag=true;
		while(flag) {
			System.out.println("请输入要发送的数据");
			if(input.hasNext()) {
				String str=input.next().trim();
				out.println(str);
				if(str.equalsIgnoreCase("byebye")) {
					flag=false;
				}
				if(scan.hasNext()) {
					System.out.println(scan.next());
				}
			}
		}
		
		if(scan.hasNext()) {
			System.out.println("回应数据"+scan.next());
		}
		input.close();
		out.close();
		client.close();
		scan.close();
	}
}
