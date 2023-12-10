package com.Spring.nominaSpring.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Clase que representa la nómina de un empleado en el sistema.
 */
@Entity
@Table(name = "Nominas")
public class Nominas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dni_employee", referencedColumnName = "dni")
    private Employee employee;

    @Column(name = "salary")  // Asegúrate de tener esta anotación
    private int salary;

    /**
     * Constructor por defecto de la clase.
     */
    public Nominas() {
    }

    /**
     * Constructor que recibe un empleado y calcula el salario.
     *
     * @param employee Empleado asociado a la nómina.
     */
    public Nominas(Employee employee) {
        this.employee = employee;
        this.salary = calcularSueldo();
    }

    /**
     * Obtiene el ID de la nómina.
     *
     * @return ID de la nómina.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la nómina.
     *
     * @param id ID de la nómina a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el empleado asociado a la nómina.
     *
     * @return Empleado asociado a la nómina.
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Establece el empleado asociado a la nómina y recalcula el salario.
     *
     * @param employee Empleado a asociar a la nómina.
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
        this.salary = calcularSueldo();
    }

    /**
     * Obtiene el salario de la nómina.
     *
     * @return Salario de la nómina.
     */
    public int getSalary() {
        return salary;
    }

    /**
     * Establece el salario de la nómina.
     *
     * @param salary Salario de la nómina a establecer.
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     * Calcula el sueldo según la categoría y los años de servicio del empleado.
     *
     * @return Sueldo calculado.
     */
    public int calcularSueldo() {
        int[] SUELDO_BASE = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000 };
        return SUELDO_BASE[this.employee.getCategory() - 1] + 5000 * this.employee.getYears();
    }
}
