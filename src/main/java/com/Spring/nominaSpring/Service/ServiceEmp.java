package com.Spring.nominaSpring.Service;

import java.util.List;

import com.Spring.nominaSpring.Entity.Employee;
import com.Spring.nominaSpring.Entity.Nominas;

/**
 * Interfaz de servicio para la gestión de empleados y nóminas.
 */
public interface ServiceEmp {

    /**
     * Obtiene la lista de todos los empleados.
     *
     * @return Lista de empleados.
     */
    List<Employee> listEmployees();

    /**
     * Guarda un nuevo empleado.
     *
     * @param employee El empleado a guardar.
     * @return El empleado guardado.
     */
    Employee saveEmployee(Employee employee);

    /**
     * Busca un empleado por su ID.
     *
     * @param id ID del empleado a buscar.
     * @return El empleado encontrado, o null si no existe.
     */
    Employee findById(Long id);

    /**
     * Actualiza la información de un empleado.
     *
     * @param employee El empleado con la información actualizada.
     * @return El empleado actualizado.
     */
    Employee updateEmp(Employee employee);

    /**
     * Elimina un empleado por su ID.
     *
     * @param id ID del empleado a eliminar.
     */
    void deleteEmp(Long id);

    /**
     * Guarda una lista de empleados.
     *
     * @param employees Lista de empleados a guardar.
     */
    void saveEmployees(List<Employee> employees);

    /**
     * Obtiene un empleado por su DNI.
     *
     * @param dni DNI del empleado a buscar.
     * @return El empleado encontrado, o null si no existe.
     */
    Employee obtainByDni(String dni);

    /**
     * Calcula las nóminas para todos los empleados.
     *
     * @return Lista de nóminas calculadas.
     */
    List<Nominas> calcularNominas();
}
