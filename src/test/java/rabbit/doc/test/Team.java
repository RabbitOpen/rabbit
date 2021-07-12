package rabbit.doc.test;


import rabbit.open.orm.core.annotation.Column;
import rabbit.open.orm.core.annotation.Entity;

@Entity("T_TEAM")
public class Team {
 
    @Column("ID")
    private Integer id;
     
    @Column("NAME")
    private String name;
    
    @Column("RESOURCE_ID")
	private Resources resource;
    
	public Resources getResource() {
		return resource;
	}

	public void setResource(Resources resource) {
		this.resource = resource;
	}

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
 
    //.....此处省略get set 方法
    
    
}
