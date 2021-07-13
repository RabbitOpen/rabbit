package rabbit.doc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import rabbit.open.orm.core.dml.AbstractQuery;

@ControllerAdvice
public class ExceptionHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception e) {
        logger.error(e.getMessage(), e);
        AbstractQuery a;
    }
}
