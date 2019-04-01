package co.com.unac.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import co.com.unac.manager.usuario.IUsuarioManager;
import co.com.unac.model.Civica;
import co.com.unac.model.Trayecto;
import co.com.unac.model.Usuario;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/usuario")
public class UsuarioController {
	
	@Autowired
	IUsuarioManager usuarioManager;
	
	
	@GetMapping()
	public List<Usuario> obtenerUsuarios() {
		return usuarioManager.listar();
	}
	
	@PostMapping(value="/crear")
	public Usuario crearUsuario(@RequestBody Usuario usuario) throws Exception {
		
		return  usuarioManager.crear(usuario);
	}
	
	@GetMapping(value="/{id}")
	public Usuario findOne(@PathVariable(value="id") Long id) {
		return usuarioManager.findById(id);		
	}
	
	@DeleteMapping (value="/{id}")
	public void borrarUsuario(@PathVariable(value="id") Long id) {
		usuarioManager.borrar(id);
	}
	
	@PutMapping(value ="/{id}")
	public Usuario modificarUsuario (@PathVariable(value="id")Long id,@RequestBody Usuario usuario) {
			return usuarioManager.modificar(id, usuario);
	}
	
	
	@PutMapping (value="/recargarCivica/{civicaId}")
	public void recargarCivica(@PathVariable (value="civicaId")Long civicaId,
			@RequestBody Civica valorRecarga) throws Exception {
		usuarioManager.recargarCivica(civicaId,valorRecarga); 
		
	}
	@PostMapping(value="/viajar/{civicaId}")
	public void viajar(@PathVariable(value="civicaId")Long civicaId,@RequestBody Trayecto trayecto) throws Exception {
		usuarioManager.viajar(civicaId, trayecto);
	}
	

	

}
