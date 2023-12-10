package com.Spring.nominaSpring.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Spring.nominaSpring.Entity.Employee;
import com.Spring.nominaSpring.Entity.Nominas;
import com.Spring.nominaSpring.Repository.EmpRepository;

import jakarta.transaction.Transactional;

/**
 * Implementación del servicio para la gestión de empleados y nóminas.
 */
@Service
public class ServiceEmpImpl implements ServiceEmp {

    @Autowired
    private EmpRepository repo;

    @Override
    public List<Employee> listEmployees() {
        return repo.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        System.out.println("Guardando empleado con DNI: " + employee.getDni());
        return repo.save(employee);
    }

    @Override
    public Employee findById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Employee updateEmp(Employee exitedEmp) {
        return repo.save(exitedEmp);
    }

    @Override
    public void deleteEmp(Long id) {
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public List<Nominas> calcularNominas() {
        List<Employee> employees = listEmployees(); // Obtener la lista de empleados

        List<Nominas> nominasCalculadas = new ArrayList<>();

        for (Employee employee : employees) {
            Nominas nominas = new Nominas();
            // Lógica de cálculo de la nómina para el empleado
            // Puedes ajustar esto según tus reglas de negocio
            nominas.setEmployee(employee);
            nominas.setSalary(nominas.calcularSueldo());
            // Otros campos de la nómina...

            nominasCalculadas.add(nominas);

            // Agregar la nómina a la lista de nóminas del empleado
            employee.addPayroll(nominas);
        }

        // Guardar los empleados actualizados y las nóminas en la base de datos
        saveEmployees(employees);

        return nominasCalculadas;
    }

    @Override
    public Employee obtainByDni(String dni) {
        return repo.findByDni(dni);
    }

    @Override
    public void saveEmployees(List<Employee> employees) {
        repo.saveAll(employees);
    }
}
