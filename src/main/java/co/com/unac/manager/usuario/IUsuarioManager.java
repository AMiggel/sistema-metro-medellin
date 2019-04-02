package co.com.unac.manager.usuario;

import java.util.List;

import co.com.unac.manager.usuario.precoditionexception.PreconditionException;
import co.com.unac.model.Civica;
import co.com.unac.model.Trayecto;
import co.com.unac.model.Usuario;

public interface IUsuarioManager  {

	public List<Usuario> listar();
	public Usuario crear (Usuario usuario) throws Exception;
	public Usuario findById (Long id) throws PreconditionException;
	public void borrar (Long id);
	public Usuario modificar(Long id, Usuario usuario) throws PreconditionException;
	public void recargarCivica(Long civicaId, Civica valorRecarga) throws Exception;
	public void viajar (Long  civicaId, Trayecto trayecto)throws Exception;

}
