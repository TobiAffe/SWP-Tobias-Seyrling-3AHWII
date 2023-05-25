package sockets;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

	public static void main(String[] args) {
		try {
			PrintWriter out = null;
			ServerSocket sock = new ServerSocket(6666);
			System.out.println("Echo-Server wartet....");
			Scanner scan = null;
			int noRequests = 0;
			while (noRequests < 1000) {
				Socket s = sock.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				String readStr = (String) dis.readUTF();
				System.out.println("message = " + readStr);
				System.out.println("request: " + noRequests);
				String pathStr = readStr.split("#")[0];
				String dataStr = readStr.split("#")[1];
				String sentStr = "a";
				System.out.println(pathStr);
				if(dataStr.equals("LIST")) {
					scan = new Scanner(new File("testdata/" + pathStr));
					while(scan.hasNextLine()) {
						sentStr += scan.nextLine() + "\n";
					}
				} else {
					sentStr = "Data written: " + dataStr + "\n";
					FileWriter fw = new FileWriter(new File("testdata/" + pathStr));
					fw.append(dataStr);
					fw.flush();
					fw.close();
				}
				
				out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
				out.write(sentStr);
				out.flush();
				noRequests++;
			}
			out.close();
			sock.close();
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
