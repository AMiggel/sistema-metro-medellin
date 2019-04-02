package co.com.unac.manager.usuario;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.com.unac.builder.entity.civica.CivicaBuilderTest;
import co.com.unac.builder.entity.usuario.UsuarioBuilderTest;
import co.com.unac.manager.usuario.constantes.ExceptionMessage;
import co.com.unac.manager.usuario.precoditionexception.PreconditionException;
import co.com.unac.model.Civica;
import co.com.unac.model.Usuario;
public class UsuarioManagerImplTest {
	
	private UsuarioManagerImpl usuarioManager;
	private UsuarioManagerImpl usuarioManagerImpl;
	
	Usuario usuario = new UsuarioBuilderTest().buildUsuario(); 
	
	
	@Before
	public void setUp() {
		usuarioManagerImpl = Mockito.mock(UsuarioManagerImpl.class);
		
		usuarioManager= new UsuarioManagerImpl();
		
	}
	
	@Test
	public void buscarUsuarioNoExistente() throws PreconditionException {
		
		//arrange
		when(usuarioManagerImpl.findById(null)).thenThrow(new PreconditionException (ExceptionMessage.NO_EXISTE_USUARIO));
		
		try {
		//act
		usuarioManagerImpl.findById(null);
		
		} catch (PreconditionException e){
		
		//Assert	
		assertEquals(ExceptionMessage.NO_EXISTE_USUARIO, e.getMessage());
		}
	}
	
	@Test
	public void buscarUsuarioExistente() throws PreconditionException {
		
		//arrange
		long id = 1;
				
		when(usuarioManagerImpl.findById(usuario.getId())).thenReturn(usuario);
		
		
		//act
		Usuario encontrado = usuarioManagerImpl.findById(id);
		
	
		//Assert	
		assertEquals(usuario, encontrado);
	
	}

	@Test
	public void calcularValorViajePorTransporteIntegrado(){
		//arrange 
		String tipoTransporte = "INTEGRADO";
				 	
		//act
		double cobro= usuarioManager.calcularValorViajePorTransporte(tipoTransporte);
		
		//Assert	
		assertEquals(1500.00, cobro,0);
		
	}
	
	@Test
	public void calcularValorViajePorTransporteMetroPlus(){
		//arrange 
		String tipoTransporte = "METROPLUS";
				 	
		//act
		double cobro= usuarioManager.calcularValorViajePorTransporte(tipoTransporte);
		
		//Assert	
		assertEquals(2000.00, cobro,0);
		
	}
	
	

}
