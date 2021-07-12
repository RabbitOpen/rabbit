package rabbit.doc.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rabbit.open.orm.core.dml.Query;
import rabbit.open.orm.core.spring.SpringDaoAdapter;


@Service("UserService")
public class UserService extends SpringDaoAdapter<User> {

	/**
	 * 事务方法demo
	 */
	@Transactional
	public void transactionMethod(){
		
	    Query<User> query = createQuery();
	    //构建JoinFilter
//        JoinFilter filter = JoinFilterBuilder.prepare(query)
//                .join(Role.class)
//                .join(Resources.class).on("id", 1)
//                .build();
//        query.addInnerJoinFilter(filter).execute().list();
	    
	}
	
}
 
