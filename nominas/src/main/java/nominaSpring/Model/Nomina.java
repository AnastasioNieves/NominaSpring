package nominaSpring.Model;

import jakarta.persistence.Entity;
import nominaSpring.service.EmpleadoService;

/**
 * Clase Nomina de cálculo de sueldos
 */
@Entity
public class Nomina {

    /**
     * Declaración de biblioteca de sueldos base
     */
    private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000 };

  
    /**
     * Método de cálculo de sueldo para un empleado
     * 
     * @param empleado Empleado para el cálculo de sueldo
     * @return sueldo total calculado
     */
    public double sueldo(Empleado empleado) {
        int sueldoBase = SUELDO_BASE[empleado.getCategoria() - 1];
        return sueldoBase + 5000 * empleado.getAnyos();
    }

    /**
     * Método para obtener el sueldo de un empleado por su DNI
     * 
     * @param dni DNI del empleado
     * @return Sueldo total calculado para el empleado con el DNI proporcionado
     */
    public double obtenerSueldoPorDNI(String dni) {
        Empleado empleado = EmpleadoService.obtenerEmpleado(dni);
        return sueldo(empleado);
    }
}

