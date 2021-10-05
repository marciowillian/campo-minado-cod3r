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
	
	public CampoBuilder minar(boolean minado) {
		campo.setMinado(minado);
		return this;
	}
	
	public CampoBuilder abrir() {
		campo.abrir();
		return this;
	}
	
	public CampoBuilder abrirSemExplodir() {
		campo.setAberto(true);
		return this;
	}
	
	public CampoBuilder marcar() {
		campo.setMarcado(true);
		return this;
	}
	
	public Campo agora() {
		return campo;
	}
	
}
