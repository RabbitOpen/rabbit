package rabbit.doc.service;

import org.springframework.stereotype.Service;
import rabbit.doc.dao.BasicService;
import rabbit.doc.entity.User;

import javax.annotation.PostConstruct;

@Service
public class UserService extends BasicService<User>  {

    @PostConstruct
    public void init() {
        createDelete().addFilter("username", "xiaokunqin").execute();
        createDelete().addFilter("username", "wangchengxi").execute();
        User user = new User();
        user.setUsername("xiaokunqin");
        user.setPassword("qinqin9528");
        add(user);
        user = new User();
        user.setUsername("wangchengxi");
        user.setPassword("wangchengxi9528");
        add(user);
    }
}
