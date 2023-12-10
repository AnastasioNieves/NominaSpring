package nominaSpring.DAO;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nominaSpring.Model.Empleado;

@Repository
public interface EmpleadoDAO extends JpaRepository<Empleado, String> {

    List<Empleado> findByDeleted(char deleted);

    List<Empleado> findByDeletedAndDni(char deleted, String dni);

    List<Empleado> findByDeletedAndNombreContaining(char deleted, String nombre);

    // Ejemplo de consulta personalizada utilizando JPQL
    @Query("SELECT e FROM Empleado e WHERE e.deleted = 'N' AND e.sexo = :sexoBusqueda")
    List<Empleado> buscarPorSexo(@Param("sexoBusqueda") String sexoBusqueda);

    @Query("SELECT e FROM Empleado e WHERE e.deleted = 'N' AND e.categoria = :categoriaBusqueda")
    List<Empleado> buscarPorCategoria(@Param("categoriaBusqueda") int categoriaBusqueda);

    @Query("SELECT e FROM Empleado e WHERE e.deleted = 'N' AND e.anyos = :anyosBusqueda")
    List<Empleado> buscarPorAnyosTrabajados(@Param("anyosBusqueda") double anyosBusqueda);

    // Los métodos CRUD básicos (save, delete, findById, findAll, etc.) son proporcionados por JpaRepository y no es necesario definirlos aquí.
    // Agrega este método
    List<Empleado> buscarEmpleadosPorCriterio(String tipoBusqueda, String valorBusqueda);
}
