package rabbit.doc.test;


import rabbit.open.orm.core.annotation.Column;
import rabbit.open.orm.core.annotation.Entity;
import rabbit.open.orm.core.annotation.PrimaryKey;

@Entity("T_RESOURCE")
public class Resources {

	@PrimaryKey
    @Column("ID")
	private Integer id;
	
	@Column("RESOURCE")
	private String resource;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
	
}
