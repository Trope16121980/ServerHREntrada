package errores;

/**
 *
 * @author Gustavo_Senorans
 */
public class Errores {

	public String errorDni() {
		StringBuilder errorDni = new StringBuilder();
		errorDni.append("\nEl Dni no existe en el registro");
		return errorDni.toString();
	}
	
	public String errorDepartamentEmpleados() {
		StringBuilder errorDpartamentEmpleados = new StringBuilder();
		errorDpartamentEmpleados.append("\nEl departamento no existe en el registro");
		return errorDpartamentEmpleados.toString();
	}
	
	public String erroNomEmpresa() {
		StringBuilder erroNomEmpresa = new StringBuilder();
		erroNomEmpresa.append("\nEl nombre de la empresa no existe en el registro");
		return erroNomEmpresa.toString();
	}

	public String erroCodicard() {
		StringBuilder erroCodicard = new StringBuilder();
		erroCodicard.append("\nEl codigo de tarjeta no existe en el registro");
		return erroCodicard.toString();
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

	public String errorNomApellido() {
		StringBuilder errorNomApellido = new StringBuilder();
		errorNomApellido.append("\nEl nombre o el apellido no existe en el registro");
		return errorNomApellido.toString();
	}
	
	public String errorNom() {
		StringBuilder errorNom = new StringBuilder();
		errorNom.append("\nEl nombre no existe en el registro");
		return errorNom.toString();
	}
	
	public String errorApellido() {
		StringBuilder errorApellido = new StringBuilder();
		errorApellido.append("\nEl apellido no existe en el registro");
		return errorApellido.toString();
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
	public String erroAddressEmpresa() {
		StringBuilder erroAddressEmpresa = new StringBuilder();
		erroAddressEmpresa.append("\nLa direccion de la empresa no existe en el registro");
		return erroAddressEmpresa.toString();
	}
	public String erroFechaJornada() {
		StringBuilder erroFechaJornada = new StringBuilder();
		erroFechaJornada.append("\nLa fecha no existe en el registro");
		return erroFechaJornada.toString();
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
	
	public String errorInsertEmpresa() {
		StringBuilder errorInsertEmpresa = new StringBuilder();
		errorInsertEmpresa.append("\nAlgo ha fallado en la creacion de la empresa\nrevise la lista de empresas y vuelva a intentarlo");
		return errorInsertEmpresa.toString();
	}
}
