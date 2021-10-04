package com.mwcc.cm.modelo.builders;

import com.mwcc.cm.modelo.Campo;

public class CampoBuilder {

	private Campo campo;
	
	private CampoBuilder() {
		
	}
	
	public static CampoBuilder umCampo() {
		CampoBuilder builder = new CampoBuilder();
		builder.campo = new Campo(0, 0);
		return builder;
	}
	
	public CampoBuilder linhaEColuna(int linha, int coluna) {
		campo = new Campo(linha, coluna);
		return this;
	}
	
	public Campo agora() {
		return campo;
	}
	
}
