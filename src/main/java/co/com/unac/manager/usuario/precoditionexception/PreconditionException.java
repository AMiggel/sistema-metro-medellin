package co.com.unac.manager.usuario.precoditionexception;

public class PreconditionException extends Exception {


		/**
	 * 
	 */
	private static final long serialVersionUID = -3927610537095413901L;
		private final String message;

		public PreconditionException(String message) {
			super(message);
			this.message = message;
		}

		@Override
		public String getMessage() {
			return message;
		}
		
	}

