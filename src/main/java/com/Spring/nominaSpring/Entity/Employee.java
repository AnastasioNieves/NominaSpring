package com.Spring.nominaSpring.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Clase que representa a un empleado en el sistema.
 */
@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni", nullable = false, unique = true)
    private String dni;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "Genre", nullable = false)
    private char genre;

    @Column(name = "Category", nullable = false)
    private int category;

    @Column(name = "Years", nullable = false)
    private int years;

    @Column(name = "deleted", nullable = false, columnDefinition = "char default 'N'")
    private char deleted;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Nominas> payrolls = new ArrayList<>();

    /**
     * Obtiene la lista de nóminas asociadas al empleado.
     *
     * @return Lista de nóminas asociadas al empleado.
     */
    public List<Nominas> getPayrolls() {
        return payrolls;
    }

    /**
     * Establece la lista de nóminas asociadas al empleado.
     *
     * @param payrolls Lista de nóminas a establecer.
     */
    public void setPayrolls(List<Nominas> payrolls) {
        this.payrolls = payrolls;
    }

    /**
     * Agrega una nómina a la lista de nóminas del empleado.
     *
     * @param nominas Nómina a agregar.
     */
    public void addPayroll(Nominas nominas) {
        payrolls.add(nominas);
        nominas.setEmployee(this);
    }

    /**
     * Elimina una nómina de la lista de nóminas del empleado.
     *
     * @param nominas Nómina a eliminar.
     */
    public void removePayroll(Nominas nominas) {
        payrolls.remove(nominas);
        nominas.setEmployee(null);
    }

    /**
     * Constructor por defecto de la clase.
     */
    public Employee() {

    }

    /**
     * Constructor con parámetros de la clase.
     *
     * @param id       ID del empleado.
     * @param dni      DNI del empleado.
     * @param name     Nombre del empleado.
     * @param genre    Género del empleado.
     * @param category Categoría del empleado.
     * @param years    Años de servicio del empleado.
     * @param deleted  Estado de eliminación del empleado.
     */
    public Employee(Long id, String dni, String name, char genre, int category, int years, char deleted) {
        super();
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.genre = genre;
        this.category = category;
        this.years = years;
        this.deleted = deleted;
    }

    /**
     * Constructor con parámetros de la clase (sin ID y sin estado de eliminación).
     *
     * @param dni      DNI del empleado.
     * @param name     Nombre del empleado.
     * @param genre    Género del empleado.
     * @param category Categoría del empleado.
     * @param years    Años de servicio del empleado.
     */
    public Employee(String dni, String name, char genre, int category, int years) {
        super();

        this.dni = dni;
        this.name = name;
        this.genre = genre;
        this.category = category;
        this.years = years;
        deleted = 'N';
    }

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public char getGenre() {
		return genre;
	}

	public int getCategory() {
		return category;
	}

	public int getYears() {
		return years;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public char getDeleted() {
		return deleted;
	}

	public void setDeleted(char deleted) {
		this.deleted = deleted;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenre(char genre) {
		this.genre = genre;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setYears(int years) {
		this.years = years;
	}

}
