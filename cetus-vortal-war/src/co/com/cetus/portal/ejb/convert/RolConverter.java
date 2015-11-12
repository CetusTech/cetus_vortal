package co.com.cetus.portal.ejb.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import co.com.cetus.portal.web.bean.GeneralManagedBean;
import co.com.cetus.vortal.jpa.entity.Rol;
import co.com.cetus.common.exception.ServiceException;

@FacesConverter(forClass = Rol.class, value = "RolPickListConverter")
public class RolConverter extends GeneralManagedBean implements Converter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8570888805597056868L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Rol dto = new Rol();
		dto.setId(Integer.parseInt(value));

		try {
			dto = this.delegate.find(Rol.class, dto.getId());

		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext
	 * , javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		return String.valueOf(((Rol) value).getId());
	}

	@Override
	public void initElement() {

	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public String add() {
		return null;
	}

}
