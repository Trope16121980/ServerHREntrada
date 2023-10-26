package errores;

/**
 *
 * @author gsenorans
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
}
