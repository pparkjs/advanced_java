package threadTest;

public class ThreadTest03 {
	public static void main(String[] args) {
		//Thread가 실행되는 시간 체크해 보기
		Thread th = new Thread(new MyRunner2());
		// 1970년 1월 1일 0시 0분 0초(표준시간)로부터 System.currentTimeMillis()메서드가 
		// 실해오딘 시점까지의 경과한 시간을 밀리세컨드(1/1000초) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		
		th.start();
		
		try {
			th.join(); //현재 위치에서 대상이 되는 쓰레드(변수 th 쓰레드)
					   //끝날 때까지 기다린다.
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("경과 시간 : " + (endTime - startTime));
	}
}

class MyRunner2 implements Runnable{
	@Override
	public void run() {
		long sum = 0L;
		for(long i=1L; i <=1000000000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}