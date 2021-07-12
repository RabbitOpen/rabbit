package rabbit.doc.test;

import rabbit.open.orm.core.annotation.Column;
import rabbit.open.orm.core.annotation.Entity;
import rabbit.open.orm.core.annotation.ManyToMany;
import rabbit.open.orm.core.annotation.PrimaryKey;

import java.util.List;


@Entity("ROLE")
public class Role {

	@PrimaryKey()
    @Column("ID")
    private Integer id;
     
    @Column("ROLE_NAME")
    private String rolename;
    
    @ManyToMany(joinTable="T_ROLE_RESOURCE",
            joinColumn="ROLE_ID",            
            reverseJoinColumn="RESOURCE_ID")     
    private List<Resources> resources;
    
    //.....此处省略get set 方法
	
    public Role(Integer id) {
		this.id = id;
	}

	public Role() {
		super();
	}
    
}
