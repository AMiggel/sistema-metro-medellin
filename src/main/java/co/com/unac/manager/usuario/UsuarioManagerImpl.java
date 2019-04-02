package co.com.unac.manager.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.unac.manager.usuario.constantes.ExceptionMessage;
import co.com.unac.manager.usuario.constantes.Condicion;
import co.com.unac.manager.usuario.precoditionexception.PreconditionException;
import co.com.unac.model.Civica;
import co.com.unac.model.Trayecto;
import co.com.unac.model.Usuario;
import co.com.unac.repository.civica.ICivicaRepository;
import co.com.unac.repository.trayecto.TrayectoRepository;
import co.com.unac.repository.usuario.IUsuarioRepository;

@Service
public class UsuarioManagerImpl implements IUsuarioManager {
	

	@Autowired
	IUsuarioRepository usuarioRepository;
	
	@Autowired
	ICivicaRepository civicaRepository;
	
	@Autowired
	TrayectoRepository trayectoRepository;
	
	
	@Override
	@Transactional (readOnly =true)
	public List<Usuario> listar() {
		return (List<Usuario>) usuarioRepository.findAll();
	}
	@Override
	@Transactional
	public Usuario crear(Usuario usuario) throws PreconditionException {
		
		Civica civica = new Civica();
		civica.setSaldo(Condicion.VALOR_MINIMO);
		civica.setUsuario(usuario);
				
		if(usuario.getCivica()== null) {
			civicaRepository.save(civica);
			usuario.setCivica(civica);
		} else {
			throw new PreconditionException(ExceptionMessage.YA_TIENE_CIVICA_ASIGNADA);
		}
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional (readOnly = true)
	public Usuario findById(Long id) throws PreconditionException {
		if(id == null) {
			throw new PreconditionException(ExceptionMessage.NO_EXISTE_USUARIO);
		}
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void borrar(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public Usuario modificar(Long id,Usuario usuarioDetails) throws PreconditionException {
		
		Usuario usuario = findById(id);
				
		usuario.setApellido(usuarioDetails.getApellido());
		usuario.setNombre(usuarioDetails.getNombre());
		usuario.setEdad(usuarioDetails.getEdad());
		usuario.setTipoPoblacion(usuarioDetails.getTipoPoblacion());
		Usuario actualizarUsuario = usuarioRepository.save(usuario);
		return actualizarUsuario;
	}
	
	
	@Override
	@Transactional
	public void recargarCivica(Long civicaId, Civica valorRecarga) throws PreconditionException {
		
		Civica civica = civicaRepository.findById(civicaId).orElse(null);

		double saldoCivica = civica.getSaldo();
		
		if ( valorRecarga.getSaldo() < Condicion.VALOR_MINIMO) {
			throw new PreconditionException(ExceptionMessage.VALOR_RECARGA_INVALIDO);			
		} else {
			saldoCivica+= valorRecarga.getSaldo();
			civica.setSaldo(saldoCivica);
			civicaRepository.save(civica);
		}
	}
	@Override
	@Transactional
	public void viajar(Long civicaId, Trayecto trayecto) throws PreconditionException {
		
		Civica civica = civicaRepository.findById(civicaId).orElse(null);
		double valorViaje= calcularValorViajePorTransporte(trayecto.getTipoTransporte());
		double cobro=0;
		
		 if (civica.getSaldo() < valorViaje) {
			throw new PreconditionException(ExceptionMessage.SALDO_INSUFICIENTE);
		} else {
			trayecto.setValorViaje(valorViaje);
			cobro = civica.getSaldo() - trayecto.getValorViaje();
			civica.setSaldo(cobro);
			civicaRepository.save(civica);
			trayecto.setCivica(civica);
			trayectoRepository.save(trayecto);
		}
		System.out.println(valorViaje);
		
	}
	
	public double calcularValorViajePorTransporte(String tipoTransporte) {

		double valorViaje = Condicion.VALOR_MINIMO;
		
		if (tipoTransporte.equals(Condicion.INTEGRADO) ) {
			valorViaje += Condicion.VALOR_INTEGRADO;
		} else if (tipoTransporte.equals(Condicion.METROPLUS)) {
			valorViaje += Condicion.VALOR_METROPLUS;
		}
		return valorViaje;
		
	}

}