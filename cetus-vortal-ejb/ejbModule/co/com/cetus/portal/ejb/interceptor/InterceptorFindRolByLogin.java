package co.com.cetus.portal.ejb.interceptor;

import static co.com.cetus.common.util.UtilCommon.createMessageFAILURE_WS;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import co.com.cetus.portal.ejb.process.CustomerProcess;
import co.com.cetus.portal.ejb.process.PortalProcessLocal;
import co.com.cetus.portal.ejb.util.AppConstants;
import co.com.cetus.portal.ejb.util.ConstantBussines.Internalizacion;
import co.com.cetus.portal.ejb.util.Util;
import co.com.cetus.vortal.ws.dto.FindRolsByLoginResponseDTO;
import co.com.cetus.vortal.ws.dto.ValidPermServiceRequestDTO;
import co.com.cetus.common.dto.ResponseWSDTO;
import co.com.cetus.common.dto.UserWSDTO;

/**
 * The Class InterceptorFindRolByApplication.
 * 
 * @author Andres Herrera Hdez - Cetus Technology
 * @version cetus-vortal-ejb (6/10/2013)
 */
public class InterceptorFindRolByLogin extends CustomerProcess {

	/** The portal process. */
	@EJB(name = "cetus-vortal-ear/PortalProcess/local")
	private PortalProcessLocal portalProcess;

	/**
	 * </p> Intercept service. </p>
	 * 
	 * @param ctx
	 *            the ctx
	 * @return el object
	 * @throws Exception
	 *             the exception
	 * @author Andres Herrera Hdez - Cetus Technology
	 * @since cetus-vortal-ejb (6/10/2013)
	 */
	@AroundInvoke
	public Object interceptService(InvocationContext ctx) throws Exception {
		ResponseWSDTO response = null;
		FindRolsByLoginResponseDTO responseDTO = null;
		Object[] parameter = null;
		ValidPermServiceRequestDTO validPermDTO = null;
		UserWSDTO userWSDTO = null;
		try {
			responseDTO = new FindRolsByLoginResponseDTO();
			Util.CETUS_CORE.info("Interceptado servicio ::> "
					+ ctx.getMethod().getName());
			parameter = ctx.getParameters();
			userWSDTO = (UserWSDTO) parameter[0];

			// Validar permisos del usuario para ejecutar el servicio
			validPermDTO = new ValidPermServiceRequestDTO();
			validPermDTO.setApplication(AppConstants.ID_APPLICATION_CETUS);
			validPermDTO.setService(ctx.getMethod().getName());
			validPermDTO.setUser(userWSDTO.getUser());
			validPermDTO.setPassword(userWSDTO.getPassword());

			response = this.validPermissionService(validPermDTO);

			if (!Util.validateResponseSuccess(response)) {
				Util.CETUS_CORE
						.info("Usuario no autorizado para ejecutar el servicio");
				responseDTO.setResponseWSDTO(createMessageFAILURE_WS());
				responseDTO.getResponseWSDTO().setMessage(
						Internalizacion.NO_PERMISSION_SERVICE);
			} else {
				Util.CETUS_CORE
						.info("Usuario autorizado para ejecutar el servicio");
				responseDTO = (FindRolsByLoginResponseDTO) ctx.proceed();
			}

		} catch (Exception e) {
			Util.CETUS_CORE.error("Error ::> " + e.getMessage(), e);
			responseDTO.setResponseWSDTO(createMessageFAILURE_WS());
			responseDTO.getResponseWSDTO().setMessage(
					responseDTO.getResponseWSDTO().getMessage() + "-"
							+ e.getMessage());
		}
		return responseDTO;
	}

}
