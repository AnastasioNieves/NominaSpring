package com.Spring.nominaSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Spring.nominaSpring.Entity.Employee;
import com.Spring.nominaSpring.Repository.EmpRepository;
import com.Spring.nominaSpring.Service.ServiceEmp;

/**
 * Clase principal de la aplicación Spring Boot para la gestión de nóminas.
 * Implementa CommandLineRunner para ejecutar lógica adicional al iniciar la aplicación.
 */
@SpringBootApplication
public class NominaSpringApplication implements CommandLineRunner {

    /**
     * Punto de entrada principal para la aplicación Spring Boot.
     *
     * @param args Argumentos de línea de comandos proporcionados al iniciar la aplicación.
     */
    public static void main(String[] args) {
        SpringApplication.run(NominaSpringApplication.class, args);
    }

    @Autowired
    private EmpRepository repo;

    @Autowired
    private ServiceEmp serviceEmp;

    /**
     * Método que se ejecuta al iniciar la aplicación.
     * Realiza la inicialización de datos y el cálculo y almacenamiento de nóminas.
     *
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     * @throws Exception Posibles excepciones que puedan ocurrir durante la ejecución.
     */
    @Override
    public void run(String... args) throws Exception {
        // Inicialización de empleados
        Employee emp1 = new Employee("30217476E", "Rocio Garrido", 'F', 5, 3);
        saveIfNotExists(emp1);

        Employee emp2 = new Employee("30217070F", "Anastasio Nieves", 'M', 9, 7);
        saveIfNotExists(emp2);

        Employee emp3 = new Employee("30216060N", "Juan Fernandez", 'M', 3, 2);
        saveIfNotExists(emp3);

        // Cálculo y almacenamiento de nóminas
        calcularYGuardarNominas();
    }

    /**
     * Guarda un empleado en el repositorio si aún no existe.
     *
     * @param employee El empleado a guardar.
     */
    private void saveIfNotExists(Employee employee) {
        // Verifica si el empleado ya existe en la base de datos
        if (repo.findByDni(employee.getDni()) == null) {
            // Si no existe, guarda el empleado
            repo.save(employee);
        }
    }

    /**
     * Calcula y guarda las nóminas para todos los empleados.
     */
    private void calcularYGuardarNominas() {
        // Calcular y guardar las nóminas para todos los empleados
        serviceEmp.calcularNominas();
    }
}
