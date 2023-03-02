package threadTest;

public class ThreadTest02 {
	public static void main(String[] args) {
		// 멀티 쓰레드 프로그램
		
		// Thread를 사용하는 방법
		
		//방법1
		MyThread1 th1 = new MyThread1();
		th1.start();
		
		//방법2-1
		MyRunner1 r1 = new MyRunner1();
		Thread th2 = new Thread(r1);
		th2.start();
		
		
		//방법 2-2  익명구현체
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO: handle exception
					}
				}
			}
		};
		Thread th3 = new Thread(r2);
		th3.start();
		
	}
}
// 방법 1 ==> Thread를 상속한 class작성하기
class MyThread1 extends Thread{
	@Override
	public void run() {
		//이 run()메서드는 쓰레드가 처리할 내용을 기술하는 곳이다.
		for (int i = 1; i <= 200; i++) {
			System.err.print("*");
			try {
				// Thread.sleep(시간)메서드 ==> 주어진 시간동안 잠시 멈춘다.
				//시간은 밀리세컨드 단위를 사용한다. (즉, 1000은 1초)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
}
// 방법 2 ==> Runnable인터페이스를 구현한 class작성하기
class MyRunner1 implements Runnable{
	@Override
	public void run() {
		//쓰레드가 처리할 내용을 이 run()메서드에 기술한다.
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
	
}
