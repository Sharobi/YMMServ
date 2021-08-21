package com.retail.ecom.model;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

@SqlResultSetMapping(name = "pathgroupmapping", 
classes = @ConstructorResult(
		targetClass = Path.class, 
		columns = {@ColumnResult(name = "path"),
			   @ColumnResult(name = "rolestr")}
		)
)
@Entity
@Table(name = "gen_m_path")
public class Path {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="path_id")
	private int id;
	@Column(name="path")
	private String path;
	@Column(name="role_id")
	private Integer roleId;
	@OneToOne
	@JoinColumn(name="role_id",insertable=false,updatable=false)
	private Role role;
	
	@Transient
	private String rolestr;
	
	public Path() {
		super();
	}
	
	/**
	 * @param id
	 */
	public Path(int id) {
		super();
		this.id = id;
	}

	public Path(String path, String rolestr) {
		super();
		this.path = path;
		this.rolestr = rolestr;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRolestr() {
		return rolestr;
	}

	public void setRolestr(String rolestr) {
		this.rolestr = rolestr;
	}

	@Override
	public String toString() {
		return "Path [id=" + id + ", path=" + path + ", roleId=" + roleId + ", role=" + role + ", rolestr=" + rolestr
				+ "]";
	}
}
