package co.com.unac.repository.usuario;


import org.springframework.data.jpa.repository.JpaRepository;

import co.com.unac.model.Usuario;


public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{
	
	
}
