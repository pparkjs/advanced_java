package threadTest;

public class ThreadTest13 {

	public static void main(String[] args) {
		DataBox databox = new DataBox();
		
		ProducerThread th1 = new ProducerThread(databox);
		ConsumerThread th2 = new ConsumerThread(databox);
		
		th1.start();
		th2.start();
		
	}

}

// 데이터를 공통으로 사용하는 클래스
class DataBox{
	private String value;
	
	// 데이터를 꺼내가는 메서드
	// ==> value변수값이 null이면 value변수에 문자열이 채워질 때까지 기다리고,
	//     value변수에 값이 있으면 해당 문자열을 반환한다.
	//     문자열을 반환 후에는 value변수를  null로 만든다.
	public synchronized String getValue() {
		if(value==null) { // value가 null 일 때
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		} //if문 끝...
		
		// value가 null이 아닐 때 
		String temp = value;
		System.out.println("쓰레드가 읽은 데이터 : "  + temp);
		
		value = null;
		
		notify(); //데이터를 채워줄 쓰레드 깨우기
		
		return temp;
		
		
	}
	
	//데이터를  저장하는 메서드
	// ==> value변수에 값이 있으면 value변수가 null이 될 때까지 기다린다.
	//     value변수가 null이 되면 새로운 데이터값을 저장한다.
	public synchronized void setValue(String value) {
		if(this.value!=null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}// if문 끝..
		
		this.value = value;
		System.out.println("Thread에서 새로 저장한 데이터 : " + this.value);
		
		notify();
	}
}


//데이터를 공급하는 쓰레드
class ProducerThread extends Thread{
	private DataBox box;

	//생성자
	public ProducerThread(DataBox box) {
		this.box = box;
	}

	@Override
	public void run() {
		String[] nameArr = {"홍길동", "일지매", "이순신"};
		for (int i = 0; i < nameArr.length; i++) {
			box.setValue(nameArr[i]);
		}
	}
	
}


// 데이터를 꺼내서 사용하는 쓰레드
class ConsumerThread extends Thread{	
	private DataBox box;

	//생성자
	public ConsumerThread(DataBox box) {
		this.box = box;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			String data = box.getValue();
		}
	}
	
}

