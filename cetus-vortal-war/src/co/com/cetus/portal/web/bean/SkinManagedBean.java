package co.com.cetus.portal.web.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "skinManagedBean")
@SessionScoped
public class SkinManagedBean {
	private String skin;

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

}
