package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class inetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com
		InetAddress ip = InetAddress.getByName("www.naver.com");
		
		System.out.println("HostName : " + ip.getHostName());
		System.out.println("HostAddress : " + ip.getHostAddress());
		System.out.println("toString : " + ip.toString());
		System.out.println();
		
		// 자신 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("HostName : " + localIp.getHostName());
		System.out.println("HostAddress : " + localIp.getHostAddress());
		System.out.println("toString : " + localIp.toString());
		System.out.println();

		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] ipArr = InetAddress.getAllByName("daum.net");
		for (InetAddress tempIp : ipArr) {
			System.out.println(tempIp.toString());
		}
	}
}
