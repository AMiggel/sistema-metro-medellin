package co.com.unac.model;

import java.io.Serializable;


import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="trayectos")
public class Trayecto implements Serializable {

	

	
	private static final long serialVersionUID = 8699270457472845745L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotEmpty
	private String estacionInicial;
	
	@NotEmpty
	private String estacionFinal;
	
	@ManyToOne(fetch =FetchType.LAZY , cascade=CascadeType.ALL)
	@JoinColumn(name="civica_id")
	Civica civica;
	
	private String tipoTransporte;
	
	private double valorViaje;

	public long getId() {
		return id;
	}

	public void setId(long idTrayecto) {
		this.id = idTrayecto;
	}

	public String getEstacionInicial() {
		return estacionInicial;
	}

	public void setEstacionInicial(String estacionInicial) {
		this.estacionInicial = estacionInicial;
	}

	public String getEstacionFinal() {
		return estacionFinal;
	}

	public void setEstacionFinal(String estacionFinal) {
		this.estacionFinal = estacionFinal;
	}

	
	public String getTipoTransporte() {
		return tipoTransporte;
	}

	public void setTipoTransporte(String tipoTransporte) {
		this.tipoTransporte = tipoTransporte;
	}
	

	public double getValorViaje() {
		return valorViaje;
	}

	public void setValorViaje(double valorViaje) {
		this.valorViaje = valorViaje;
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
