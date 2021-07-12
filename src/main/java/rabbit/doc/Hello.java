package rabbit.doc;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component()
public class Hello {

	public Logger logger = LoggerFactory.getLogger(getClass());
	
	@PostConstruct
	public void print() {
		logger.info("系统启动了");
	}
}
