package com.mwcc.cm.modelo;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mwcc.cm.excecao.ExplosaoException;
import com.mwcc.cm.modelo.builders.CampoBuilder;

public class CampoTest {

	private Campo campo;
	private List<Campo> vizinhos;

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
	void testeVizinhoDistancia2() {
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

	@Test
	void testeNaoVizinho() {
		Campo naoVizinho = new Campo(1, 3);

		boolean resultadoNaoVizinhoCima = campo.adicionarVizinho(naoVizinho);

		assertFalse(resultadoNaoVizinhoCima);
	}

	@Test
	void testeValorPadraoAtributoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcacaoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAbrirCampoNaoMinado() {
		assertTrue(campo.abrir());
	}

	@Test
	void testeAbrirCampoNaoMinadoAberto() {
		campo.setAberto(true);

		assertFalse(campo.abrir());
	}

	@Test()
	void testarAbirCampoMinado() {
		campo.minar();

		assertThrows(ExplosaoException.class, () -> campo.abrir());
	}

	@Test
	void testarAbrirCamposSemMina() {
		Campo cp1 = CampoBuilder.umCampo().linhaEColuna(2, 2).agora();
		Campo cp2 = CampoBuilder.umCampo().linhaEColuna(2, 3).agora();
		Campo cp3 = CampoBuilder.umCampo().linhaEColuna(2, 4).agora();
		Campo cp4 = CampoBuilder.umCampo().linhaEColuna(3, 2).agora();
		Campo cp5 = CampoBuilder.umCampo().linhaEColuna(3, 4).agora();
		Campo cp6 = CampoBuilder.umCampo().linhaEColuna(4, 2).agora();
		Campo cp7 = CampoBuilder.umCampo().linhaEColuna(4, 3).agora();
		Campo cp8 = CampoBuilder.umCampo().linhaEColuna(4, 4).agora();
		Campo cp9 = CampoBuilder.umCampo().linhaEColuna(1, 1).agora();
		Campo cp10 = CampoBuilder.umCampo().linhaEColuna(1, 2).agora();
		Campo cp11 = CampoBuilder.umCampo().linhaEColuna(1, 3).agora();
		Campo cp12 = CampoBuilder.umCampo().linhaEColuna(1, 4).agora();

		vizinhos = Arrays
				.asList(cp1, cp2, cp3, cp4, cp5, cp6, cp7, cp8, cp9, cp10, cp11, cp12);

		campo.setVizinhos(vizinhos);
		
		campo.abrir();
		
		assertTrue(campo.vizinhancaSegura());
	}
}
