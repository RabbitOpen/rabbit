package rabbit.doc.test;


import rabbit.open.orm.common.dml.Policy;
import rabbit.open.orm.core.annotation.Column;
import rabbit.open.orm.core.annotation.Entity;
import rabbit.open.orm.core.annotation.PrimaryKey;

@Entity("t_org")
public class Organization {

    @PrimaryKey(policy = Policy.AUTOINCREMENT)
    @Column("ID")
    private Integer id;

    @Column("ORG_CODE")
    private String orgCode;

    @Column("ORG_NAME")
    private String orgName;

    @Column("RESOURCE_ID")
    private Resources resource;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Resources getResource() {
        return resource;
    }

    public void setResource(Resources resource) {
        this.resource = resource;
    }


}
