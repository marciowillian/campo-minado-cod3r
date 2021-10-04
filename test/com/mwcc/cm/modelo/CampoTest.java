package com.mwcc.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CampoTest {

	private Campo campo;
	
	@BeforeEach
	void setUp() {
		campo = new Campo(3, 3);
	}
	
	@Test
	void testeVizinhoRealDistancia1() {
		Campo vizinhoEsquerda = new Campo(3, 2);
		Campo vizinhoDireita = new Campo(3, 4);
		Campo vizinhoCima = new Campo(2, 3);
		Campo vizinhoBaixo = new Campo(4, 3);
				
		boolean resultadoEsquerda = campo.adicionarVizinho(vizinhoEsquerda);
		boolean resultadoDireita = campo.adicionarVizinho(vizinhoDireita);
		boolean resultadoCima = campo.adicionarVizinho(vizinhoCima);
		boolean resultadoBaixo = campo.adicionarVizinho(vizinhoBaixo);		
		
		assertTrue(resultadoEsquerda);
		assertTrue(resultadoDireita);
		assertTrue(resultadoCima);
		assertTrue(resultadoBaixo);
	}
	
	
	@Test
	void testeVizinhoDistancia2(){
		Campo vizinhoDiagonalEsquerdaCima = new Campo(2, 2);
		Campo vizinhoDiagonalDireitaCima = new Campo(2, 4);
		Campo vizinhoDiagonalEsquerdaBaixo = new Campo(4, 2);
		Campo vizinhoDiagonalDireitaBaixo = new Campo(4, 4);

		boolean resultadoDiagonalEsquerdaCima = campo.adicionarVizinho(vizinhoDiagonalEsquerdaCima);
		boolean resultadoDiagonalDireitaCima = campo.adicionarVizinho(vizinhoDiagonalDireitaCima);
		boolean resultadoDiagonalEsquerdaBaixo = campo.adicionarVizinho(vizinhoDiagonalEsquerdaBaixo);
		boolean resultadoDiagonalDireitaBaixo = campo.adicionarVizinho(vizinhoDiagonalDireitaBaixo);
		
		assertTrue(resultadoDiagonalEsquerdaCima);
		assertTrue(resultadoDiagonalDireitaCima);
		assertTrue(resultadoDiagonalEsquerdaBaixo);
		assertTrue(resultadoDiagonalDireitaBaixo);
	}
	
}
