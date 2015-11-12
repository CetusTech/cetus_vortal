
package co.com.cetus.portal.ejb.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.com.cetus.portal.ejb.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateUserResponse_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "createUserResponse");
    private final static QName _FindRolByApplication_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "findRolByApplication");
    private final static QName _FindRolByLogin_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "findRolByLogin");
    private final static QName _FindParameterResponse_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "findParameterResponse");
    private final static QName _UserWSDTO_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "userWSDTO");
    private final static QName _ParameterDTO_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "parameterDTO");
    private final static QName _UpdateUser_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "updateUser");
    private final static QName _FindRolByLoginResponse_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "findRolByLoginResponse");
    private final static QName _ValidPermServiceRequestDTO_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "validPermServiceRequestDTO");
    private final static QName _DeleteUser_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "deleteUser");
    private final static QName _ValidPermissionServiceResponse_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "validPermissionServiceResponse");
    private final static QName _ValidPermissionService_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "validPermissionService");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "updateUserResponse");
    private final static QName _DeleteUserResponse_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "deleteUserResponse");
    private final static QName _CreateUser_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "createUser");
    private final static QName _FindRolByApplicationResponse_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "findRolByApplicationResponse");
    private final static QName _FindParameter_QNAME = new QName("http://service.ejb.portal.cetus.com.co/", "findParameter");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.com.cetus.portal.ejb.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindParameterResponse }
     * 
     */
    public FindParameterResponse createFindParameterResponse() {
        return new FindParameterResponse();
    }

    /**
     * Create an instance of {@link UserWSDTO }
     * 
     */
    public UserWSDTO createUserWSDTO() {
        return new UserWSDTO();
    }

    /**
     * Create an instance of {@link ParameterDTO }
     * 
     */
    public ParameterDTO createParameterDTO() {
        return new ParameterDTO();
    }

    /**
     * Create an instance of {@link FindRolByApplication }
     * 
     */
    public FindRolByApplication createFindRolByApplication() {
        return new FindRolByApplication();
    }

    /**
     * Create an instance of {@link FindRolByLogin }
     * 
     */
    public FindRolByLogin createFindRolByLogin() {
        return new FindRolByLogin();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link DeleteUserResponse }
     * 
     */
    public DeleteUserResponse createDeleteUserResponse() {
        return new DeleteUserResponse();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link FindRolByApplicationResponse }
     * 
     */
    public FindRolByApplicationResponse createFindRolByApplicationResponse() {
        return new FindRolByApplicationResponse();
    }

    /**
     * Create an instance of {@link FindParameter }
     * 
     */
    public FindParameter createFindParameter() {
        return new FindParameter();
    }

    /**
     * Create an instance of {@link ValidPermissionServiceResponse }
     * 
     */
    public ValidPermissionServiceResponse createValidPermissionServiceResponse() {
        return new ValidPermissionServiceResponse();
    }

    /**
     * Create an instance of {@link ValidPermissionService }
     * 
     */
    public ValidPermissionService createValidPermissionService() {
        return new ValidPermissionService();
    }

    /**
     * Create an instance of {@link ValidPermServiceRequestDTO }
     * 
     */
    public ValidPermServiceRequestDTO createValidPermServiceRequestDTO() {
        return new ValidPermServiceRequestDTO();
    }

    /**
     * Create an instance of {@link FindRolByLoginResponse }
     * 
     */
    public FindRolByLoginResponse createFindRolByLoginResponse() {
        return new FindRolByLoginResponse();
    }

    /**
     * Create an instance of {@link DeleteUser }
     * 
     */
    public DeleteUser createDeleteUser() {
        return new DeleteUser();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link FindParameterResponseDTO }
     * 
     */
    public FindParameterResponseDTO createFindParameterResponseDTO() {
        return new FindParameterResponseDTO();
    }

    /**
     * Create an instance of {@link ServiceDTO }
     * 
     */
    public ServiceDTO createServiceDTO() {
        return new ServiceDTO();
    }

    /**
     * Create an instance of {@link UserWDTO }
     * 
     */
    public UserWDTO createUserWDTO() {
        return new UserWDTO();
    }

    /**
     * Create an instance of {@link ServletDTO }
     * 
     */
    public ServletDTO createServletDTO() {
        return new ServletDTO();
    }

    /**
     * Create an instance of {@link ResponseWSDTO }
     * 
     */
    public ResponseWSDTO createResponseWSDTO() {
        return new ResponseWSDTO();
    }

    /**
     * Create an instance of {@link FindRolsByApplicationRequestDTO }
     * 
     */
    public FindRolsByApplicationRequestDTO createFindRolsByApplicationRequestDTO() {
        return new FindRolsByApplicationRequestDTO();
    }

    /**
     * Create an instance of {@link FindRolsByLoginRequestDTO }
     * 
     */
    public FindRolsByLoginRequestDTO createFindRolsByLoginRequestDTO() {
        return new FindRolsByLoginRequestDTO();
    }

    /**
     * Create an instance of {@link TipoEstiloDTO }
     * 
     */
    public TipoEstiloDTO createTipoEstiloDTO() {
        return new TipoEstiloDTO();
    }

    /**
     * Create an instance of {@link FindParameterRequestDTO }
     * 
     */
    public FindParameterRequestDTO createFindParameterRequestDTO() {
        return new FindParameterRequestDTO();
    }

    /**
     * Create an instance of {@link ComponentDTO }
     * 
     */
    public ComponentDTO createComponentDTO() {
        return new ComponentDTO();
    }

    /**
     * Create an instance of {@link CreateUserRequestDTO }
     * 
     */
    public CreateUserRequestDTO createCreateUserRequestDTO() {
        return new CreateUserRequestDTO();
    }

    /**
     * Create an instance of {@link UserDTO }
     * 
     */
    public UserDTO createUserDTO() {
        return new UserDTO();
    }

    /**
     * Create an instance of {@link UsuarioDTO }
     * 
     */
    public UsuarioDTO createUsuarioDTO() {
        return new UsuarioDTO();
    }

    /**
     * Create an instance of {@link UsuarioRolDTO }
     * 
     */
    public UsuarioRolDTO createUsuarioRolDTO() {
        return new UsuarioRolDTO();
    }

    /**
     * Create an instance of {@link RolMenuDTO }
     * 
     */
    public RolMenuDTO createRolMenuDTO() {
        return new RolMenuDTO();
    }

    /**
     * Create an instance of {@link MenuDTO }
     * 
     */
    public MenuDTO createMenuDTO() {
        return new MenuDTO();
    }

    /**
     * Create an instance of {@link ParametroDTO }
     * 
     */
    public ParametroDTO createParametroDTO() {
        return new ParametroDTO();
    }

    /**
     * Create an instance of {@link FindRolsByApplicationResponseDTO }
     * 
     */
    public FindRolsByApplicationResponseDTO createFindRolsByApplicationResponseDTO() {
        return new FindRolsByApplicationResponseDTO();
    }

    /**
     * Create an instance of {@link RolDTO }
     * 
     */
    public RolDTO createRolDTO() {
        return new RolDTO();
    }

    /**
     * Create an instance of {@link PermissionDTO }
     * 
     */
    public PermissionDTO createPermissionDTO() {
        return new PermissionDTO();
    }

    /**
     * Create an instance of {@link CreateUserResponseDTO }
     * 
     */
    public CreateUserResponseDTO createCreateUserResponseDTO() {
        return new CreateUserResponseDTO();
    }

    /**
     * Create an instance of {@link UpdateUserRequestDTO }
     * 
     */
    public UpdateUserRequestDTO createUpdateUserRequestDTO() {
        return new UpdateUserRequestDTO();
    }

    /**
     * Create an instance of {@link AplicationServletDTO }
     * 
     */
    public AplicationServletDTO createAplicationServletDTO() {
        return new AplicationServletDTO();
    }

    /**
     * Create an instance of {@link AplicacionDTO }
     * 
     */
    public AplicacionDTO createAplicacionDTO() {
        return new AplicacionDTO();
    }

    /**
     * Create an instance of {@link DeleteUserRequestDTO }
     * 
     */
    public DeleteUserRequestDTO createDeleteUserRequestDTO() {
        return new DeleteUserRequestDTO();
    }

    /**
     * Create an instance of {@link FindRolsByLoginResponseDTO }
     * 
     */
    public FindRolsByLoginResponseDTO createFindRolsByLoginResponseDTO() {
        return new FindRolsByLoginResponseDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRolByApplication }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "findRolByApplication")
    public JAXBElement<FindRolByApplication> createFindRolByApplication(FindRolByApplication value) {
        return new JAXBElement<FindRolByApplication>(_FindRolByApplication_QNAME, FindRolByApplication.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRolByLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "findRolByLogin")
    public JAXBElement<FindRolByLogin> createFindRolByLogin(FindRolByLogin value) {
        return new JAXBElement<FindRolByLogin>(_FindRolByLogin_QNAME, FindRolByLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindParameterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "findParameterResponse")
    public JAXBElement<FindParameterResponse> createFindParameterResponse(FindParameterResponse value) {
        return new JAXBElement<FindParameterResponse>(_FindParameterResponse_QNAME, FindParameterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserWSDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "userWSDTO")
    public JAXBElement<UserWSDTO> createUserWSDTO(UserWSDTO value) {
        return new JAXBElement<UserWSDTO>(_UserWSDTO_QNAME, UserWSDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "parameterDTO")
    public JAXBElement<ParameterDTO> createParameterDTO(ParameterDTO value) {
        return new JAXBElement<ParameterDTO>(_ParameterDTO_QNAME, ParameterDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRolByLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "findRolByLoginResponse")
    public JAXBElement<FindRolByLoginResponse> createFindRolByLoginResponse(FindRolByLoginResponse value) {
        return new JAXBElement<FindRolByLoginResponse>(_FindRolByLoginResponse_QNAME, FindRolByLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidPermServiceRequestDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "validPermServiceRequestDTO")
    public JAXBElement<ValidPermServiceRequestDTO> createValidPermServiceRequestDTO(ValidPermServiceRequestDTO value) {
        return new JAXBElement<ValidPermServiceRequestDTO>(_ValidPermServiceRequestDTO_QNAME, ValidPermServiceRequestDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "deleteUser")
    public JAXBElement<DeleteUser> createDeleteUser(DeleteUser value) {
        return new JAXBElement<DeleteUser>(_DeleteUser_QNAME, DeleteUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidPermissionServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "validPermissionServiceResponse")
    public JAXBElement<ValidPermissionServiceResponse> createValidPermissionServiceResponse(ValidPermissionServiceResponse value) {
        return new JAXBElement<ValidPermissionServiceResponse>(_ValidPermissionServiceResponse_QNAME, ValidPermissionServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidPermissionService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "validPermissionService")
    public JAXBElement<ValidPermissionService> createValidPermissionService(ValidPermissionService value) {
        return new JAXBElement<ValidPermissionService>(_ValidPermissionService_QNAME, ValidPermissionService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "deleteUserResponse")
    public JAXBElement<DeleteUserResponse> createDeleteUserResponse(DeleteUserResponse value) {
        return new JAXBElement<DeleteUserResponse>(_DeleteUserResponse_QNAME, DeleteUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindRolByApplicationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "findRolByApplicationResponse")
    public JAXBElement<FindRolByApplicationResponse> createFindRolByApplicationResponse(FindRolByApplicationResponse value) {
        return new JAXBElement<FindRolByApplicationResponse>(_FindRolByApplicationResponse_QNAME, FindRolByApplicationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindParameter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ejb.portal.cetus.com.co/", name = "findParameter")
    public JAXBElement<FindParameter> createFindParameter(FindParameter value) {
        return new JAXBElement<FindParameter>(_FindParameter_QNAME, FindParameter.class, null, value);
    }

}
