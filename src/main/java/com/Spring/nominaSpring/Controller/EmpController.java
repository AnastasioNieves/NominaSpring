package com.Spring.nominaSpring.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Spring.nominaSpring.Entity.Employee;
import com.Spring.nominaSpring.Entity.Nominas;
import com.Spring.nominaSpring.Service.ServiceEmp;

/**
 * Controlador que gestiona las operaciones relacionadas con los empleados y las nóminas.
 */
@Controller
public class EmpController {

    @Autowired
    private ServiceEmp service;

    /**
     * Muestra la lista de empleados.
     *
     * @param model El modelo utilizado para pasar datos a la vista.
     * @return La vista que muestra la lista de empleados.
     */
    @GetMapping({ "/employee", "/" })
    public String listEmployees(Model model) {
        model.addAttribute("employee", service.listEmployees());
        return "employee";
    }

    /**
     * Muestra el formulario para crear un nuevo empleado.
     *
     * @param model El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de creación de empleados.
     */
    @GetMapping("/employee/nuevo")
    public String createEmployee(Model model) {
        Employee emp = new Employee();
        model.addAttribute("emp", emp);
        System.out.println(emp);
        return "createForm";
    }

    /**
     * Crea un nuevo empleado.
     *
     * @param employee El objeto empleado creado a partir de los datos del formulario.
     * @return Redirecciona a la lista de empleados o a la página deseada.
     */
    @PostMapping("/employee")
    public String createEmployee(@ModelAttribute("employee") Employee employee) {
        // Puedes realizar la lógica para guardar el nuevo empleado en la base de datos
        service.saveEmployee(employee);
        return "redirect:/employee";
    }

    /**
     * Muestra el formulario para editar un empleado.
     *
     * @param id    El ID del empleado a editar.
     * @param model El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de edición de empleados.
     */
    @GetMapping("/employee/editEmp/{id}")
    public String editEmp(@PathVariable Long id, Model model) {
        model.addAttribute("employee", service.findById(id));
        return "editEmp";
    }

    /**
     * Actualiza un empleado existente.
     *
     * @param id       El ID del empleado a actualizar.
     * @param employee El objeto empleado con los nuevos valores.
     * @param model    El modelo utilizado para pasar datos a la vista.
     * @return Redirige a la página de detalles del empleado actualizado.
     */
    @PostMapping("/employee/{id}")
    public String updateEmp(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model) {
        // Obtener el empleado existente por ID
        Employee existingEmp = service.findById(id);

        // Actualizar los atributos del empleado existente con los nuevos valores
        existingEmp.setDni(employee.getDni());
        existingEmp.setName(employee.getName());
        existingEmp.setGenre(employee.getGenre());
        existingEmp.setCategory(employee.getCategory());
        existingEmp.setYears(employee.getYears());

        // Guardar el empleado actualizado en la base de datos
        service.saveEmployee(existingEmp);

        // Redirigir a la página de detalles del empleado actualizado
        return "redirect:/employee";
    }

    /**
     * Elimina un empleado.
     *
     * @param id El ID del empleado a eliminar.
     * @return Redirige a la lista de empleados.
     */
    @GetMapping("/employee/{id}")
    public String deleteEmp(@PathVariable Long id) {
        service.deleteEmp(id);
        return "redirect:/employee";
    }

    /**
     * Muestra el formulario para buscar salarios.
     *
     * @return La vista del formulario de búsqueda de salarios.
     */
    @GetMapping("/employee/salary")
    public String mostrarFormularioBuscarSalarios() {
        return "buscarNominas";
    }

    /**
     * Busca y muestra el salario de un empleado.
     *
     * @param dni    El DNI del empleado para buscar el salario.
     * @param modelo El modelo utilizado para pasar datos a la vista.
     * @return La vista del formulario de búsqueda de salarios con los resultados.
     */
    @PostMapping("/employee/salary")
    public String buscarSalario(@RequestParam String dni, Model modelo) {
        // Obtén el empleado por su DNI
        Employee employee = service.obtainByDni(dni);

        if (employee != null) {
            // Obtén las nóminas asociadas al empleado
            List<Nominas> nominas = service.calcularNominas();
            if (!nominas.isEmpty()) {
                modelo.addAttribute("employee", employee);
                modelo.addAttribute("salary", nominas.get(0).getSalary());
            } else {
                modelo.addAttribute("error", "No hay nóminas para el empleado con DNI " + dni);
            }
        } else {
            modelo.addAttribute("error", "No se encontró ningún empleado con DNI " + dni);
        }

        return "buscarNominas";
    }
}
