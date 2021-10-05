package com.mwcc.cm.modelo;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TabuleiroTest {

	private Tabuleiro tabuleiro;

	@BeforeEach
	void setUp() {
		tabuleiro = new Tabuleiro(4, 4, 16);
	}

	@Test
	void testarGerarCampos() {
		assertTrue(tabuleiro.getLinhas() > 0 && tabuleiro.getColunas() > 0 && tabuleiro.getMinas() > 0);
	}

	@Test
	void testarReiniciar() {
		Tabuleiro tab1 = tabuleiro;
		tabuleiro.reiniciar();

		boolean result = tabuleiro.getCampos()
				.stream()
				.allMatch(c -> !c.isAberto() &&
						!c.isMarcado());

		assertTrue(result);
	}
}
