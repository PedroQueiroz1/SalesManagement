package br.com.gvendas.gestaovendas.exception;

public class Error {

	private String msgUsuario;
	private String msgDesenvolvedor;
	
	public Error(String msgUsuario, String msgDesenvolvedor) {
		this.msgUsuario = msgUsuario;
		this.msgDesenvolvedor = msgDesenvolvedor;
	}

	public String getMsgUsuario() {
		return msgUsuario;
	}

	public String getMsgDesenvolvedor() {
		return msgDesenvolvedor;
	}
	
	
	
	
}
