package co.com.unac.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7734679854843463361L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	@Size(min = 2, max= 20)
	private String nombre;
	
	@NotEmpty
	@Size(min = 2, max= 20)
	private String apellido;
	
	@Size(min = 7, max= 10)
	@NotEmpty
	private String identificacion;
	
	@NotEmpty
	private String tipoIdentificacion;
	
	@NotNull
	@Size(min = 2)
	private String edad;
	
	@NotNull
	@Size(min = 2)
	private String tipoPoblacion;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.REMOVE)
	private Civica civica;
	
		
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	public String getTipoPoblacion() {
		return tipoPoblacion;
	}
	public void setTipoPoblacion(String tipoPoblacion) {
		this.tipoPoblacion = tipoPoblacion;
	}
	public Civica getCivica() {
		return civica;
	}
	public void setCivica(Civica civica) {
		this.civica = civica;
	}
		

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
