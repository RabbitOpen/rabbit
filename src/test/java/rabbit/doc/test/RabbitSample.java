package rabbit.doc.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext.xml"})
public class RabbitSample{

	@Autowired
	UserService us;
	
	/**
	 * 多对多演示1
	 */
	@Test
	public void joinFetch1(){
		try {
			us.createQuery()
				.joinFetch(Role.class)		//ManyToMany和OneToMany的数据只需一个API就能取出
				//根据id升序
				.asc("id")
				//根据name降序
				.desc("name")
				.execute().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void joinFetch2(){
		try {
			us.createQuery()
	            .joinFetch(Role.class)      //ManyToMany和OneToMany的数据只需一个API就能取出
	            .execute().list();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 中间表操作示例
	 * 
	 */
	@Test
	public void joinFetch3(){
		try {
			//构建用户和角色对象
			User u = new User();
			u.setId(1);
			List<Role> roles = new ArrayList<Role>();
			roles.add(new Role(1));
			roles.add(new Role(2));
			u.setRoles(roles);
			
			//向中间表user_role插入用户1和角色1、角色2的关系(单纯的insert)
			us.addJoinRecords(u);
			
			//从中间表user_role删除用户1的所有角色数据
			us.removeJoinRecords(u, Role.class);
			
			//从中间表user_role删除用户1和角色1、角色2的关系数据
			us.removeJoinRecords(u);
			
			//等同先removeJoinRecords再addJoinRecords
			us.replaceJoinRecords(u, Role.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
