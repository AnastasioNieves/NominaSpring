package nominaSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nominaSpring.DAO.DatosNoCorrectosException;
import nominaSpring.DAO.EmpleadoDAO;
import nominaSpring.Model.Empleado;

@Service
public class EmpleadoService {

    @Autowired
    private static EmpleadoDAO empleadoDAO;

    public List<Empleado> obtenerEmpleados() {
        // Utiliza el método proporcionado por el repositorio
        return empleadoDAO.findByDeleted('N');
    }

    public static Empleado obtenerEmpleado(String dniEmpleado) {
        // Utiliza el método proporcionado por el repositorio
        Optional<Empleado> empleado = empleadoDAO.findByDeletedAndDni('N', dniEmpleado).stream().findFirst();
        return empleado.orElse(null);
    }

    public boolean eliminar(String dniEmpleado) {
        // Lógica de eliminación utilizando el repositorio
        Optional<Empleado> empleado = empleadoDAO.findByDeletedAndDni('N', dniEmpleado).stream().findFirst();
        if (empleado.isPresent()) {
            Empleado emp = empleado.get();
            emp.setDeleted('Y');
            empleadoDAO.save(emp);
            return true;
        }
        return false;
    }

    public boolean editar(Empleado empleado) {
        // Lógica de edición utilizando el repositorio
        Optional<Empleado> empleadoExistente = empleadoDAO.findByDeletedAndDni('N', empleado.getDni()).stream().findFirst();
        if (empleadoExistente.isPresent()) {
            Empleado empExistente = empleadoExistente.get();
            empExistente.setNombre(empleado.getNombre());
            empExistente.setSexo(empleado.getSexo());
            try {
                empExistente.setCategoria(empleado.getCategoria());
            } catch (DatosNoCorrectosException e) {
                // Manejar la excepción de categoría no válida
                e.printStackTrace();
            }
            empExistente.setAnyos(empleado.getAnyos());

            // Lógica adicional si es necesario (por ejemplo, actualizar salario en la tabla de nóminas)
            // ...

            empleadoDAO.save(empExistente);
            return true;
        }
        return false;
    }

    public List<Empleado> buscarPorDNI(String dniBusqueda) {
        // Utiliza el método proporcionado por el repositorio
        return empleadoDAO.findByDeletedAndDni('N', dniBusqueda);
    }

    public List<Empleado> buscarPorNombre(String nombreBusqueda) {
        // Utiliza el método proporcionado por el repositorio
        return empleadoDAO.findByDeletedAndNombreContaining('N', nombreBusqueda);
    }

    public List<Empleado> buscarPorSexo(String sexoBusqueda) {
        // Utiliza el método proporcionado por el repositorio
        return empleadoDAO.buscarPorSexo(sexoBusqueda);
    }

    public List<Empleado> buscarPorCategoria(String categoriaBusqueda) {
        // Utiliza el método proporcionado por el repositorio
        return empleadoDAO.buscarPorCategoria(Integer.parseInt(categoriaBusqueda));
    }

    public List<Empleado> buscarPorAnyosTrabajados(String anyosBusqueda) {
        // Utiliza el método proporcionado por el repositorio
        return empleadoDAO.buscarPorAnyosTrabajados(Double.parseDouble(anyosBusqueda));
    }

    public List<Empleado> buscarEmpleadosPorCriterio(String tipoBusqueda, String valorBusqueda) {
        switch (tipoBusqueda) {
            case "dni":
                return empleadoDAO.findByDeletedAndDni('N', valorBusqueda);
            case "nombre":
                return empleadoDAO.findByDeletedAndNombreContaining('N', valorBusqueda);
            case "sexo":
                return empleadoDAO.buscarPorSexo(valorBusqueda);
            case "categoria":
                try {
                    int categoria = Integer.parseInt(valorBusqueda);
                    return empleadoDAO.buscarPorCategoria(categoria);
                } catch (NumberFormatException e) {
                    // Manejar error de conversión
                    e.printStackTrace();
                }
                break;
            case "anyos":
                try {
                    double anyos = Double.parseDouble(valorBusqueda);
                    return empleadoDAO.buscarPorAnyosTrabajados(anyos);
                } catch (NumberFormatException e) {
                    // Manejar error de conversión
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Criterio de búsqueda no válido");
        }
        return null;
    }
}
