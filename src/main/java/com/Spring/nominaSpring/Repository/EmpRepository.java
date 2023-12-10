package com.Spring.nominaSpring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Spring.nominaSpring.Entity.Employee;

/**
 * Interfaz de repositorio para la entidad Employee.
 */
@Repository
public interface EmpRepository extends JpaRepository<Employee, Long> {

    /**
     * Busca un empleado por su DNI.
     *
     * @param dni DNI del empleado a buscar.
     * @return Objeto Employee correspondiente al DNI proporcionado.
     */
    Employee findByDni(String dni);

    /**
     * Guarda una lista de empleados.
     *
     * @param employees Lista de empleados a guardar.
     * @return Lista de empleados guardados.
     */
    Employee save(List<Employee> employees);
}
