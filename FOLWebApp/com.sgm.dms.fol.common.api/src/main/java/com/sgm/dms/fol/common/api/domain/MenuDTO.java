
package com.sgm.dms.fol.common.api.domain;

import java.util.List;

public class MenuDTO {
	private long id;
	private String name;
	private String url;

	private List<String> permissions;
	private List<MenuDTO> submenus;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	public List<MenuDTO> getSubmenus() {
		return submenus;
	}
	public void setSubmenus(List<MenuDTO> submenus) {
		this.submenus = submenus;
	}
}
