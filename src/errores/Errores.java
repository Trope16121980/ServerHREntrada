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
		StringBuilder errorNomEmpresaEmpleados = new StringBuilder();
		errorNomEmpresaEmpleados.append("\nEl nombre de la empresa no existe en el registro");
		return errorNomEmpresaEmpleados.toString();
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
	
	public String erroNomEmpresa() {
		StringBuilder erroNomEmpresa = new StringBuilder();
		erroNomEmpresa.append("\nEl nombre de la empresa no existe en el registro");
		return erroNomEmpresa.toString();
	}
	
	public String erroAddressEmpresa() {
		StringBuilder erroAddressEmpresa = new StringBuilder();
		erroAddressEmpresa.append("\nLa direccion de la empresa no existe en el registro");
		return erroAddressEmpresa.toString();
	}
	
	public String erroTelephonEmpresa() {
		StringBuilder erroTelephonEmpresa = new StringBuilder();
		erroTelephonEmpresa.append("\nEl numero de telefono no existe en el registro");
		return erroTelephonEmpresa.toString();
	}

	public String erroDniJornada() {
		StringBuilder erroDniJornada = new StringBuilder();
		erroDniJornada.append("\nEl dni no existe en el registro");
		return erroDniJornada.toString();
	}

	public String erroCodicarJornada() {
		StringBuilder erroCodicarJornada = new StringBuilder();
		erroCodicarJornada.append("\nEl coodigo de tarjeta no existe en el registro");
		return erroCodicarJornada.toString();
	}
	
	public String erroFechaJornada() {
		StringBuilder erroFechaJornada = new StringBuilder();
		erroFechaJornada.append("\nLa fecha no existe en el registro");
		return erroFechaJornada.toString();
	}
	
	public String erroNomApellidoJornada() {
		StringBuilder erroNomApellidoJornada = new StringBuilder();
		erroNomApellidoJornada.append("\nEl nombre o el apellido no existe en el registro");
		return erroNomApellidoJornada.toString();
	}
	
	public String erroNomJornada() {
		StringBuilder erroNomJornada = new StringBuilder();
		erroNomJornada.append("\nEl nombre no existe en el registro");
		return erroNomJornada.toString();
	}
	
	public String erroApellidoJornada() {
		StringBuilder erroApellidoJornada = new StringBuilder();
		erroApellidoJornada.append("\nEl apellido no existe en el registro");
		return erroApellidoJornada.toString();
	}
	
	public String erroDniFechaJornada() {
		StringBuilder erroDniFechaJornada = new StringBuilder();
		erroDniFechaJornada.append("\nEl dni o la fecha no existe en el registro");
		return erroDniFechaJornada.toString();
	}
	
	public String erroNomFechaJornada() {
		StringBuilder erroNomFechaJornada = new StringBuilder();
		erroNomFechaJornada.append("\nEl nombre o la fecha no existe en el registro");
		return erroNomFechaJornada.toString();
	}
	
	public String erroApellidoFechaJornada() {
		StringBuilder erroApellidoFechaJornada = new StringBuilder();
		erroApellidoFechaJornada.append("\nEl apellido o la fecha no existe en el registro");
		return erroApellidoFechaJornada.toString();
	}
	
	public String erroCodicardFechaJornada() {
		StringBuilder erroCodicardFechaJornada = new StringBuilder();
		erroCodicardFechaJornada.append("\nEl codigo de la tarjeta o la fecha no existe en el registro");
		return erroCodicardFechaJornada.toString();
	}
	
	public String erroNomApellidoFechaJornada() {
		StringBuilder erroNomApellidoFechaJornada = new StringBuilder();
		erroNomApellidoFechaJornada.append("\nEl nombre, apellido o la fecha no existe en el registro");
		return erroNomApellidoFechaJornada.toString();
	}
}
