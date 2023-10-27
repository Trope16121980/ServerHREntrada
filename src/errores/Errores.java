package errores;

/**
 *
 * @author Gustavo_Senorans
 */
public class Errores {

	public String errorDniEmpleados() {
		StringBuilder errorDniEmpleados = new StringBuilder();
		errorDniEmpleados.append("\nEl Dni no existe en el registro");
		return errorDniEmpleados.toString();
	}

	public String errorDepartamentEmpleados() {
		StringBuilder errorDpartamentEmpleados = new StringBuilder();
		errorDpartamentEmpleados.append("\nEl departamento no existe en el registro");
		return errorDpartamentEmpleados.toString();
	}

	public String errorNomEmpresaEmpleados() {
		StringBuilder errorDniEmpleados = new StringBuilder();
		errorDniEmpleados.append("\nEl nombre de la empresa no existe en el registro");
		return errorDniEmpleados.toString();
	}

	public String erroCodicardEmpleados() {
		StringBuilder errorCodicardEmpleados = new StringBuilder();
		errorCodicardEmpleados.append("\nEl codigo de tarjeta no existe en el registro");
		return errorCodicardEmpleados.toString();
	}

	public String erroMailEmpleados() {
		StringBuilder erroMailEmpleados = new StringBuilder();
		erroMailEmpleados.append("\nEl mail no existe en el registro");
		return erroMailEmpleados.toString();
	}

	public String erroTelephonEmpleados() {
		StringBuilder erroTelephonEmpleados = new StringBuilder();
		erroTelephonEmpleados.append("\nEl telefono no existe en el registro");
		return erroTelephonEmpleados.toString();
	}

	public String erroNomApellidoEmpleados() {
		StringBuilder erroNomApellidoEmpleados = new StringBuilder();
		erroNomApellidoEmpleados.append("\nEl nombre o el apellido no existe en el registro");
		return erroNomApellidoEmpleados.toString();
	}

	public String erroNomEmpleados() {
		StringBuilder erroNomEmpleados = new StringBuilder();
		erroNomEmpleados.append("\nEl nombre no existe en el registro");
		return erroNomEmpleados.toString();
	}

	public String erroApellidoEmpleados() {
		StringBuilder erroApellidoEmpleados = new StringBuilder();
		erroApellidoEmpleados.append("\nEl apellido no existe en el registro");
		return erroApellidoEmpleados.toString();
	}

	public String erroLoginUser() {
		StringBuilder erroLoginUser = new StringBuilder();
		erroLoginUser.append("\nEl login no existe en el registro");
		return erroLoginUser.toString();
	}

	public String erroNumTipeUser() {
		StringBuilder erroNumTipeUser = new StringBuilder();
		erroNumTipeUser.append("\nEl tipo de numero de usuario no existe en el registro");
		return erroNumTipeUser.toString();
	}

	public String erroDniUser() {
		StringBuilder erroDniUser = new StringBuilder();
		erroDniUser.append("\nEl dni no existe en el registro");
		return erroDniUser.toString();
	}

}
