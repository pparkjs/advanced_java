package stackQueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * 	Stack ==> 후입선출(LIFO)의 자료구조
 * 	Queue ==> 선입선출(FIFO)의 자료구조
 *	
 */
public class StackQueueTest {

	public static void main(String[] args) {
		/*
		 Stack의 명령
		 1. 자료 입력 : push(입력값)
		 2. 자료 출력 : pop() => 자료를 꺼내온 후 꺼내온 자료를 Stack에서 삭제한다.
						peek() => 삭제없이 자료를 꺼내온다.
		 */
		Stack<String> stack = new Stack<>();
		stack.push("홍길동");	
		stack.push("일지매");	
		stack.push("성춘향");	
		stack.push("변학도");	
		
		System.out.println("현재 stack ==> " + stack);
		
		String data = stack.pop();
		System.out.println("꺼내온 값 : " + data);
		System.out.println("꺼내온 값 : " + stack.pop());
		System.out.println("현재 stack ==> " + stack);
		
		stack.push("강감찬");
		System.out.println("현재 stack ==> " + stack);
		
		System.out.println("꺼내온 값: " + stack.pop());
		System.out.println("현재 stack ==>" + stack);
		
		System.out.println("삭제없이 꺼내온 값 : " + stack.peek());
		System.out.println("현재 stack ==>" + stack);
		System.out.println("=======================================================");
		
		/*
		 * Queue의 명령
		 * 1. 자료 입력 : offer(입력값)
		 * 2. 자료 출력 : poll() ==> 자료를 꺼내오고 꺼내온 자료를 Queue에서 삭제한다.
		 *                peek() ==> 삭제없이 자료를 꺼내온다.
		 * Queue는 LinkedList를 이용해서 사용할 수 있다.	 				
		 */
		Queue<String> queue = new LinkedList<>();
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("성춘향");
		queue.offer("변학도");
		
		System.out.println("현재 queue ==>" + queue);
		String temp = queue.poll();
		System.out.println("꺼내온 값 : " + temp);
		System.out.println("꺼내온 값 : " + queue.poll());
		
		System.out.println("현재 queue ==> " + queue);
		
		queue.offer("강감찬");
		System.out.println("현재 queue ==>" + queue);
		
		System.out.println("꺼내온 값 :" + queue.poll());
		
		System.out.println("삭제없이 꺼내온 값 : " + queue.peek());
		System.out.println("현재 queue ==>" + queue);
		
		
	}

}
