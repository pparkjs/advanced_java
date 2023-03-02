package threadTest;

// 쓰레드의 상태를 출력하는 예제
public class StateOfThread01 {
	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
	
		th.start();
	}
}

//쓰레드 상태의 검사 대상이 되는 쓰레드

class TargetThread extends Thread{
	@Override
	public void run() {
		double sum = 0.0;
		for (long i = 1L; i <= 2_000_000_000L; i++) {
			sum+=i;
		}
		
		//1.5초동안 멈춰있는상태 TIMED_WAITING
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		// 실행되는 상태 RUNNABLE
		for (long i = 1L; i <= 2_000_000_000L; i++) {
			sum+=i;
		}
		
	}
}

//TargetThread의 상태를 검사해서 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TargetThread target;
	
	// 생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			// 쓰레드의 상태값 구하기 ==> getState()메서드 이용
			Thread.State state = target.getState();
			System.out.println("TargetThread의 현재 상태값 : " + state);
			
			if(state == Thread.State.NEW) { // 쓰레드의 상태가 NEW상태이면
				target.start();
			}
			
			if(state == Thread.State.TERMINATED) { // 쓰레드의 상태가 종료상태이면...
				break;
			}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
		}
	}
	
}