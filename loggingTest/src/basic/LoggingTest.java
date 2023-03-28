package basic;

import org.apache.log4j.Logger;

public class LoggingTest {
	// Logger 클래스의 인스턴스를 받아온다.
	static Logger logger = Logger.getLogger(LoggingTest.class);
	
	public static void main(String[] args) {
		// 로그 기록을 남기는 방법
		// 형식) Logger객체변수.로그레벨명("출력할 메시지")
		logger.trace("이것은 Log4J의 Trace레벨의 메시지입니다.");
		logger.debug("이것은 Log4J의 Debug레벨의 메시지입니다.");
		logger.info("이것은 Log4J의 Info레벨의 메시지입니다.");
		logger.warn("이것은 Log4J의 Warn레벨의 메시지입니다.");
		logger.error("이것은 Log4J의 Error레벨의 메시지입니다.");
		logger.fatal("이것은 Log4J의 Fatal레벨의 메시지입니다.");
		
	}
}
