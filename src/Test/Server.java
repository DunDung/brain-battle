package Test;

import java.net.ServerSocket;

public class Server {
	int portNumber; //포트번호
	int queueLength; //서버가 연결되길 기다리는 클라이언트의 최대 개수
	ServerSocket server = new ServerSocket(portNumber, queueLength); //ServerSocket 객체 생성
	
	
}
