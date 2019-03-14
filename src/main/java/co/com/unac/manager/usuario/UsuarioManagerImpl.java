package co.com.unac.manager.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	public Usuario crear(Usuario usuario) {
		
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
		Usuario actualizarUsuario = usuarioRepository.save(usuario);
		return actualizarUsuario;
	}
	
	@Override
	@Transactional
	public Usuario asignarCivica(Long usuarioId, Civica civica) throws Exception {
		
		Usuario usuario = findById(usuarioId);
		
		if (usuario.getCivica() == null) {
			usuario.setCivica(civica);
			civica.setUsuario(usuario);
			civicaRepository.save(civica);
			Usuario asignarCivica= usuarioRepository.save(usuario);
			return asignarCivica;
		} else {
			throw new Exception("El usuario ya tiene una civica asignada");
		}
	}
	
	@Override
	@Transactional
	public void recargarCivica(Long civicaId, double valorRecarga) throws Exception {
		Civica civica = civicaRepository.findById(civicaId).orElse(null);

		double saldoCivica = civica.getSaldo();
		
		if ( valorRecarga < 1000) {
			throw new Exception("El valor de la recarga debe ser mayor o igual a 1000");			
		} else {
			saldoCivica+= valorRecarga;
			civica.setSaldo(saldoCivica);
			civicaRepository.save(civica);
		}
	}
	@Override
	@Transactional
	public void viajar(Long civicaId, Trayecto trayecto) throws Exception {
		
		Civica civica = civicaRepository.findById(civicaId).orElse(null);
		double valorViaje= calcularValorViajePorTransporte(trayecto.getTipoTransporte());
		double cobro=0;
		
		 if (civica.getSaldo() < valorViaje) {
			throw new Exception("No tiene suficiente saldo en la civica");
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