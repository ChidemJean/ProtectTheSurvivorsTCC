package com.psurvivors.utils;

public class Status {

	public static final int NAO_ENCONTRADO = -100;
	public static final int EXECUTADO_COM_SUCESSO = -101;
	public static final int ERRO_INTERNO = -102;
	public static final int JA_EXISTE = -103;
	public static final int SEM_PERMISSAO = -104;
	public static final int SENHA_INCORRETA = -105;
	public static final int SEM_RESPOSTA = -106;
	
	public static String getMessage(int status, String object){
		
		String message = null;
		
		switch (status){
		
			case NAO_ENCONTRADO:
				message = object + " não encontrado! ";
				break;
				
			case EXECUTADO_COM_SUCESSO:
				message = object + " salvo com sucesso! ";
				break;
				
			case ERRO_INTERNO:
				message = " Ocorreu um erro! ";
				break;
				
			case JA_EXISTE:
				message = object + " não disponível! ";
				break;
				
			case SEM_PERMISSAO:
				message = " Você não tem permissões ";
				break;
				
			case SENHA_INCORRETA:
				message = " Senha incorreta ";
				break;
				
			case SEM_RESPOSTA:
				message = " Servidor não está respondendo! ";
				break;
		}
		
		return message;
		
	}
	
}
