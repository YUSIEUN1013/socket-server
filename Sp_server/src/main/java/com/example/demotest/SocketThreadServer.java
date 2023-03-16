package com.example.demotest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


public class SocketThreadServer extends Thread {
	
	
	private Socket socket;

	public SocketThreadServer(Socket socket){
		this.socket=socket;
	}
	
	//단순 문자열 Thread server
	public void run(){
		BufferedReader br = null;
		PrintWriter pw = null;
		try{
			String connIp = socket.getInetAddress().getHostAddress();
			System.out.println(connIp + "에서 연결 시도.");
			
			/*
			 * 접근한 소켓 계정의 ip를 체크한다. KTOA 연동 모듈인지 체크 
			 * 정상이면 먼저 정상 접근되었음을 알린다.
			 **/
			pw = new PrintWriter(socket.getOutputStream());
			// 클라이언트에 문자열 전송
			pw.println("수신되었다. 오버");
			pw.flush();
			
			while(true) {
				try {
					br = new BufferedReader(
					        new InputStreamReader(socket.getInputStream()));
					// 클라이언트에서 보낸 문자열 출력
					String ip=(((InetSocketAddress) socket.getRemoteSocketAddress()).getAddress()).toString().replace("/","");
					System.out.println("[From " + ip + "]" + br.readLine());
					Thread.sleep(2000);
					br = null;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					
		}catch(IOException e){
			System.out.println(e);
		}finally{
			try{
				if(pw != null) { pw.close();}
				if(br != null) { br.close();}
				if(socket != null){socket.close();}
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
}
