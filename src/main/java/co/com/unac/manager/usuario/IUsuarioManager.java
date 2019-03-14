package co.com.unac.manager.usuario;

import java.util.List;

import co.com.unac.model.Civica;
import co.com.unac.model.Trayecto;
import co.com.unac.model.Usuario;

public interface IUsuarioManager  {

	public List<Usuario> listar();
	public Usuario crear (Usuario usuario);
	public Usuario findById (Long id);
	public void borrar (Long id);
	public Usuario modificar(Long id, Usuario usuario);
	public Usuario asignarCivica(Long usuarioId,Civica civica)throws Exception;
	public void recargarCivica(Long civicaId, double valorRecarga) throws Exception;
	public void viajar (Long  civicaId, Trayecto trayecto)throws Exception;

}
