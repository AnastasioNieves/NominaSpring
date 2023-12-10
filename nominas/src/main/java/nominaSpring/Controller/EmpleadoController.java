package nominaSpring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import nominaSpring.Model.Empleado;
import nominaSpring.Model.Nomina;
import nominaSpring.service.EmpleadoService;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/listarEmpleado")
    public String listarEmpleado(Model model) {
        List<Empleado> lista = empleadoService.obtenerEmpleados();
        model.addAttribute("lista", lista);
        return "listarEmpleado";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam String dni) {
        empleadoService.eliminar(dni);
        return "redirect:/empresa/listarEmpleado";
    }

    @GetMapping("/buscarEmpleado")
    public String buscarEmpleado(Model model) {
        return "buscarEmpleado";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute Empleado empleado, Model model) {
        if (empleadoService.editar(empleado)) {
            return "redirect:/empresa/listarEmpleado";
        } else {
            model.addAttribute("error", "Error al editar empleado.");
            return "editar";
        }
    }

    @PostMapping("/buscarEmpleado")
    public String buscarEmpleado(@RequestParam String tipoBusqueda, @RequestParam String valorBusqueda, Model model) {
        List<Empleado> lista = empleadoService.buscarEmpleadosPorCriterio(tipoBusqueda, valorBusqueda);
        model.addAttribute("lista", lista);
        return "redirect:/empresa/listarEmpleado";
    }

    @GetMapping("/listarSalario")
    public String listarSalario(Model model) {
        return "listarSalario";
    }

    @PostMapping("/buscarSalario")
    public String buscarSalario(@RequestParam String dniBusqueda, Model model) {
        if (dniBusqueda != null && !dniBusqueda.isEmpty()) {
            Nomina nomina = new Nomina();
            try {
                Empleado empleado = EmpleadoService.obtenerEmpleado(dniBusqueda);
                double salario = nomina.sueldo(empleado);
                model.addAttribute("empleado", empleado);
                model.addAttribute("sueldo", salario);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "listarSalario";
    }


}
