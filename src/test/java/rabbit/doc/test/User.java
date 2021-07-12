package rabbit.doc.test;

import rabbit.open.orm.core.annotation.Column;
import rabbit.open.orm.core.annotation.Entity;
import rabbit.open.orm.core.annotation.ManyToMany;
import rabbit.open.orm.core.annotation.PrimaryKey;

import java.util.List;


@Entity("T_USER")
public class User {
 
    @PrimaryKey()
    @Column("ID")
    private Integer id;
     
    @Column("USER_NAME")
    private String name;
 
    @ManyToMany(joinTable="T_USER_ROLE", 		//USER和ROLE 多对多的中间表
    		joinColumn="USER_ID", 			//User在中间表中的外键字段名
    		reverseJoinColumn="ROLE_ID") 	//ROLE在中间表中的外键字段名
    List<Role> roles;
 
    //.....此处省略get set 方法

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
    
}