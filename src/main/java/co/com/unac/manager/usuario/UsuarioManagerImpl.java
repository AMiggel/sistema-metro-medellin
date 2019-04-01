package co.com.unac.manager.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.unac.manager.usuario.constantes.ExceptionMessage;
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
	public Usuario crear(Usuario usuario) throws Exception {
		
		Civica civica = new Civica();
		civica.setSaldo(1000.00);
		civica.setUsuario(usuario);
				
		if(usuario.getCivica()== null) {
			civicaRepository.save(civica);
			usuario.setCivica(civica);
		} else {
			throw new Exception("El usuario ya tiene una civica asignada");
		}
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional (readOnly = true)
	public Usuario findById(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void borrar(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	@Override
	@Transactional
	public Usuario modificar(Long id,Usuario usuarioDetails) {
		
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
		
		if ( valorRecarga.getSaldo() < 1000) {
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

		double valorViaje = 1000;
		
		if (tipoTransporte.equals("Integrado") ) {
			valorViaje += 500;
		} else if (tipoTransporte.equals("Metro Plus")) {
			valorViaje += 1000;
		}
		return valorViaje;
		
	}

}