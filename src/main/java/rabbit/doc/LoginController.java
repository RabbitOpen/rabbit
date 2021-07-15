package rabbit.doc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import rabbit.doc.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/go2login")
    public String go2login() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "failed";
        }
        long count = userService.createQuery().addFilter("username", username)
                .addFilter("password", password).count();
        if (count == 1) {
            request.getSession().setAttribute("user", username.trim());
        }
        return count == 1 ? "success" : "failed";
    }

}
