package com.Spring.nominaSpring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Spring.nominaSpring.Entity.Nominas;

/**
 * Interfaz de repositorio para la entidad Nominas.
 */
public interface NominaRepository extends JpaRepository<Nominas, Long> {

    /**
     * Busca una nómina por el DNI del empleado asociado.
     *
     * @param dni DNI del empleado asociado a la nómina.
     * @return Objeto Nominas correspondiente al DNI proporcionado.
     */
    Nominas findByEmployeeDni(String dni);
}
