package errores;

/**
 * @author Gustavo Senoráns Varela
 * @version 1.4, 15/11/2023
 * @since jdk 17
 */
public class Errores {

    /**
     * Genera un String con el error de la lectura de Dni
     *
     * @return devuelve el mensaje de error
     */
    public String errorDni() {
        StringBuilder errorDni = new StringBuilder();
        errorDni.append("\nEl Dni no existe en el registro");
        return errorDni.toString();
    }

    /**
     * Genera un String con el error de la lectura del Departamento del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String errorDepartamentEmpleados() {
        StringBuilder errorDpartamentEmpleados = new StringBuilder();
        errorDpartamentEmpleados.append("\nEl departamento no existe en el registro");
        return errorDpartamentEmpleados.toString();
    }

    /**
     * Genera un String con el error de la lectura del nombre de la empresa
     *
     * @return devuelve el mensaje de error
     */
    public String erroNomEmpresa() {
        StringBuilder erroNomEmpresa = new StringBuilder();
        erroNomEmpresa.append("\nEl nombre de la empresa no existe en el registro");
        return erroNomEmpresa.toString();
    }

    /**
     * Genera un String con el error de la lectura del código de tarjeta del
     * empleado
     *
     * @return devuelve el mensaje de error
     */
    public String erroCodicard() {
        StringBuilder erroCodicard = new StringBuilder();
        erroCodicard.append("\nEl codigo de tarjeta no existe en el registro");
        return erroCodicard.toString();
    }

    /**
     * Genera un String con el error de la lectura del mail del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String erroMailEmpleados() {
        StringBuilder erroMailEmpleados = new StringBuilder();
        erroMailEmpleados.append("\nEl mail no existe en el registro");
        return erroMailEmpleados.toString();
    }

    /**
     * Genera un String con el error de la lectura del telefono del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String erroTelephonEmpleados() {
        StringBuilder erroTelephonEmpleados = new StringBuilder();
        erroTelephonEmpleados.append("\nEl telefono no existe en el registro");
        return erroTelephonEmpleados.toString();
    }

    /**
     * Genera un String con el error por un error en la lectura del nombre o
     * apellido
     *
     * @return devuelve el mensaje de error
     */
    public String errorNomApellido() {
        StringBuilder errorNomApellido = new StringBuilder();
        errorNomApellido.append("\nEl nombre o el apellido no existe en el registro");
        return errorNomApellido.toString();
    }

    /**
     * Genera un String con el error de la lectura del nombre del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String errorNom() {
        StringBuilder errorNom = new StringBuilder();
        errorNom.append("\nEl nombre no existe en el registro");
        return errorNom.toString();
    }

    /**
     * Genera un String con el error de la lectura del apellido del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String errorApellido() {
        StringBuilder errorApellido = new StringBuilder();
        errorApellido.append("\nEl apellido no existe en el registro");
        return errorApellido.toString();
    }

    /**
     * Genera un String con el error de la lectura del login del usuario
     * inexistente
     *
     * @return devuelve el mensaje de error
     */
    public String erroLoginUser() {
        StringBuilder erroLoginUser = new StringBuilder();
        erroLoginUser.append("\nEl login no existe en el registro");
        return erroLoginUser.toString();
    }

    /**
     * Genera un String con el error de la lectura del tipo de usuario
     *
     * @return devuelve el mensaje de error
     */
    public String erroNumTipeUser() {
        StringBuilder erroNumTipeUser = new StringBuilder();
        erroNumTipeUser.append("\nEl tipo de numero de usuario no existe en el registro");
        return erroNumTipeUser.toString();
    }

    /**
     * Genera un String con el error de la lectura del la dirección de la
     * empresa
     *
     * @return devuelve el mensaje de error
     */
    public String erroAddressEmpresa() {
        StringBuilder erroAddressEmpresa = new StringBuilder();
        erroAddressEmpresa.append("\nLa direccion de la empresa no existe en el registro");
        return erroAddressEmpresa.toString();
    }

    /**
     * Genera un String con el error de la lectura del la fecha de la jornada
     * laboral
     *
     * @return devuelve el mensaje de error
     */
    public String erroFechaJornada() {
        StringBuilder erroFechaJornada = new StringBuilder();
        erroFechaJornada.append("\nLa fecha no existe en el registro");
        return erroFechaJornada.toString();
    }

    /**
     * Genera un String con el error de la lectura del dni o la fecha
     *
     * @return devuelve el mensaje de error
     */
    public String erroDniFechaJornada() {
        StringBuilder erroDniFechaJornada = new StringBuilder();
        erroDniFechaJornada.append("\nEl dni o la fecha no existe en el registro");
        return erroDniFechaJornada.toString();
    }

    /**
     * Genera un String con el error de la lectura del nombre o la fecha
     *
     * @return devuelve el mensaje de error
     */
    public String erroNomFechaJornada() {
        StringBuilder erroNomFechaJornada = new StringBuilder();
        erroNomFechaJornada.append("\nEl nombre o la fecha no existe en el registro");
        return erroNomFechaJornada.toString();
    }

    /**
     * Genera un String con el error de la lectura del apellido o la fecha
     *
     * @return devuelve el mensaje de error
     */
    public String erroApellidoFechaJornada() {
        StringBuilder erroApellidoFechaJornada = new StringBuilder();
        erroApellidoFechaJornada.append("\nEl apellido o la fecha no existe en el registro");
        return erroApellidoFechaJornada.toString();
    }

    /**
     * Genera un String con el error de la lectura del codigo de tarjeta o fecha
     *
     * @return devuelve el mensaje de error
     */
    public String erroCodicardFechaJornada() {
        StringBuilder erroCodicardFechaJornada = new StringBuilder();
        erroCodicardFechaJornada.append("\nEl codigo de la tarjeta o la fecha no existe en el registro");
        return erroCodicardFechaJornada.toString();
    }

    /**
     * Genera un String con el error de la lectura del nombre, apellido o fecha
     *
     * @return devuelve el mensaje de error
     */
    public String erroNomApellidoFechaJornada() {
        StringBuilder erroNomApellidoFechaJornada = new StringBuilder();
        erroNomApellidoFechaJornada.append("\nEl nombre, apellido o la fecha no existe en el registro");
        return erroNomApellidoFechaJornada.toString();
    }

    /**
     * Genera un String con el error de la inserción del nombre de la empresa
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertEmpresa() {
        StringBuilder errorInsertEmpresa = new StringBuilder();
        errorInsertEmpresa.append("\nLa empresa que intenta crear ya esta\ndada de alta.\nRevise la lista de empresas.");
        return errorInsertEmpresa.toString();
    }

    /**
     * Genera un String con el error de la inserción del nombre del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertUsers() {
        StringBuilder errorInsertUsers = new StringBuilder();
        errorInsertUsers.append("\nEl empleado que utilizara este usuario\n"
                + "no esta dado de alta como empleado.\nRevise la lista de empleados y cree\n"
                + "el empleado antes de crear el usuario.");
        return errorInsertUsers.toString();
    }

    /**
     * Genera un String con el error de la inserción del dni del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertUsersDni() {
        StringBuilder errorInsertUsersLogin = new StringBuilder();
        errorInsertUsersLogin.append("\nEl dni que intenta utilizar ya\n"
                + "se esta utilizando por otro usuario.\n"
                + "Revise la lista de usuarios y vuelva a intentarlo.");
        return errorInsertUsersLogin.toString();
    }

    /**
     * Genera un String con el error de la inserción del login del usuario
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertUsersLogin() {
        StringBuilder errorInsertUsersLogin = new StringBuilder();
        errorInsertUsersLogin.append("\nEl login que intenta utilizar ya\n"
                + "se esta utilizando por otro usuario.\n"
                + "Revise la lista de usuarios y vuelva a intentarlo.");
        return errorInsertUsersLogin.toString();
    }

    /**
     * Genera un String con el error de la inserción del dni del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertEmpleadoDni() {
        StringBuilder errorInsertEmpleadoDni = new StringBuilder();
        errorInsertEmpleadoDni.append("\nEl dni que intenta utilizar ya\n"
                + "se esta utilizando por otro empleado.\n"
                + "Revise la lista de empleados y vuelva a intentarlo.");
        return errorInsertEmpleadoDni.toString();
    }

    /**
     * Genera un String con el error de la inserción del nombre del empleado ya
     * que la empresa no existe
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertEmpleadoNomempresa() {
        StringBuilder errorInsertEmpleadoNomempresa = new StringBuilder();
        errorInsertEmpleadoNomempresa.append("\nLa empresa en la que intenta añadir\n"
                + "el empleado no existe.\n"
                + "Revise la lista de empresas y vuelva a intentarlo.");
        return errorInsertEmpleadoNomempresa.toString();
    }

    /**
     * Genera un String con el error de la inserción del codigo de tarjeta
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertEmpleadoCodicard() {
        StringBuilder errorInsertEmpleadoCodicard = new StringBuilder();
        errorInsertEmpleadoCodicard.append("\nEl codigo de tarjeta que intenta añadir\n"
                + "ya existe en la lista de empleados.\n"
                + "Revise la lista de empleados y vuelva a intentarlo.");
        return errorInsertEmpleadoCodicard.toString();
    }

    /**
     * Genera un String con el error de la inserción del tipo de usuario
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertEmpleadoNumtipe() {
        StringBuilder errorInsertEmpleadoNumtipe = new StringBuilder();
        errorInsertEmpleadoNumtipe.append("\nEl tipo de usuario solo puede ser 0 o 1.\n"
                + "ejemplo:\nadmin = 0\nuser = 1.\n"
                + "Revise bien en tipo de usuario y vuelva a intentarlo.");
        return errorInsertEmpleadoNumtipe.toString();
    }

    /**
     * Genera un String con el error de la inserción del inicio de jornada
     *
     * @return devuelve el mensaje de error
     */
    public String errorInsertJornadaDentro() {
        StringBuilder errorInsertJornadaDentro = new StringBuilder();
        errorInsertJornadaDentro.append("\nEl empleado que intenta iniciar jornada\n"
                + "ya la tiene iniciada.\nRevise las jornada del dia de hoy.");
        return errorInsertJornadaDentro.toString();
    }

    /**
     * Genera un String con el error del update del nombre de la empresa
     *
     * @return devuelve el mensaje de error
     */
    public String errorUpdateEmpresa() {
        StringBuilder errorUpdateEmpresa = new StringBuilder();
        errorUpdateEmpresa.append("\nEl nuevo nombre de empresa que quiere utilizar\n"
                + "ya esta en la lista de empresas\n"
                + "Revise la lista de empresas y vuelva a intentarlo.");
        return errorUpdateEmpresa.toString();
    }

    /**
     * Genera un String con el error del update del dni del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String errorUpdateEmpleadoDni() {
        StringBuilder errorUpdateEmpleadoDni = new StringBuilder();
        errorUpdateEmpleadoDni.append("\nEl nuevo dni del empleado que quiere utilizar\n"
                + "ya esta en la lista de empleados\n"
                + "Revise la lista de empleados y vuelva a intentarlo.");
        return errorUpdateEmpleadoDni.toString();
    }
    
     /**
     * Genera un String con el error del update del dni del empleado
     *
     * @return devuelve el mensaje de error
     */
    public String errorUpdateJornadaDni() {
        StringBuilder errorUpdateJornadaDni = new StringBuilder();
        errorUpdateJornadaDni.append("\nLa jornada ya esta finalizada con anterioridad.");
        return errorUpdateJornadaDni.toString();
    }
}
