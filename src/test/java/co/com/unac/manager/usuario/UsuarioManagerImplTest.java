package co.com.unac.manager.usuario;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.unac.builder.entity.civica.CivicaBuilderTest;
import co.com.unac.manager.usuario.constantes.ExceptionMessage;
import co.com.unac.manager.usuario.precoditionexception.PreconditionException;
import co.com.unac.model.Civica;

public class UsuarioManagerImplTest {
	
	private UsuarioManagerImpl usuarioManager;
	private UsuarioManagerImpl usuarioManagerImpl;
	
	
	
	@Before
	public void setUp() {
		//usuarioManagerImpl = Mockito.mock(UsuarioManagerImpl.class);
		usuarioManagerImpl= new UsuarioManagerImpl();
		
	}
	
	/*@Test
	public void recargarCivicaTest() throws PreconditionException {
		double recarga= 1000;
		long id =1;
	try {
		
	usuarioManagerImpl.recargarCivica(id,recarga);
	}catch(PreconditionException e) {
		
	assertEquals(ExceptionMessage.VALOR_RECARGA_INVALIDO,e.getMessage());
	}
	}*/
	

}
