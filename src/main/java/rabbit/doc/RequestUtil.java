package rabbit.doc;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RequestUtil {

    public static HttpSession getSession() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        return session;
    }

    public static String getLoginUser() {
        return (String) getSession().getAttribute("username");
    }
}
