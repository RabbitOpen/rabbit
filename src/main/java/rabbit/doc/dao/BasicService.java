package rabbit.doc.dao;

import rabbit.open.orm.core.dml.SessionFactory;
import rabbit.open.orm.core.spring.SpringDaoAdapter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

public abstract class BasicService<T> extends SpringDaoAdapter<T> {

    @Resource(name = "sessionFactory-sqlite")
    protected SessionFactory factory;

    @PostConstruct
    public void setUp() {
        setSessionFactory(factory);
    }
}
