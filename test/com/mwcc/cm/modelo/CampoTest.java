package com.mwcc.cm.modelo;

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

		vizinhos = Arrays.asList(cp1, cp2, cp3, cp4, cp5, cp6, cp7, cp8, cp9, cp10, cp11, cp12);

		for (Campo campoTemp : vizinhos) {
			campo.adicionarVizinho(campoTemp);
			campo.abrir();
		}

		assertTrue(campo.vizinhancaSegura());
	}
	
	@Test
	void testarMetodoObjetivoAlcancadoDesvendado() {
		Campo cp1 = CampoBuilder.umCampo().abrir().agora();
		
		boolean result = cp1.objetivoAlcancado();
		
		assertTrue(result);
	}
	
	@Test
	void testarMetodoObjetivoAlcancadoProtegido() {
		Campo cp1 = CampoBuilder.umCampo().minar(true).marcar().agora();
		
		boolean result = cp1.objetivoAlcancado();
		
		assertTrue(result);
	}
	
	@Test
	void testarMinasNaVizinhanca() {
		Campo cp1 = CampoBuilder.umCampo().linhaEColuna(2, 2).minar(true).agora();
		Campo cp2 = CampoBuilder.umCampo().linhaEColuna(2, 3).minar(true).agora();
		Campo cp3 = CampoBuilder.umCampo().linhaEColuna(2, 4).minar(true).agora();
		Campo cp4 = CampoBuilder.umCampo().linhaEColuna(3, 2).minar(true).agora();
		Campo cp5 = CampoBuilder.umCampo().linhaEColuna(3, 4).minar(true).agora();
		
		Campo cp6 = CampoBuilder.umCampo().linhaEColuna(3, 3).agora();

		cp6.adicionarVizinho(cp1);
		cp6.adicionarVizinho(cp2);
		cp6.adicionarVizinho(cp3);
		cp6.adicionarVizinho(cp4);
		cp6.adicionarVizinho(cp5);
		
		assertTrue(cp6.minasNaVizinhanca() > 0);		
	}
	
	@Test
	void testarReiniciar() {
		Campo cp1 = CampoBuilder.umCampo().linhaEColuna(2, 2).minar(true).marcar().abrir().agora();
		
		cp1.reiniciar();
		
		assertTrue(!cp1.isAberto() && !cp1.isMinado() && !cp1.isMarcado());
	}
	
	@Test
	void testarToStringMarcado() {
		Campo cp1 = CampoBuilder.umCampo().linhaEColuna(2, 2).marcar().agora();
		
		String result = cp1.toString();
		
		assertTrue(result.equals("x"));
	}
	
	@Test
	void testarToStringMinado() {
		Campo cp1 = CampoBuilder.umCampo().linhaEColuna(2, 2).minar(true).abrirSemExplodir().agora();
		
		String result = cp1.toString();
		
		assertTrue(result.equals("*"));
	}
	
	@Test
	void testarToStringAbertoMinasNaVizinhanca() {
		Campo cp1 = CampoBuilder.umCampo()
				.linhaEColuna(2, 2)
				.abrir()
				.agora();
		
		Campo cp2 = CampoBuilder.umCampo()
				.linhaEColuna(2, 3)
				.minar(true)
				.agora();
		
		cp1.adicionarVizinho(cp2);
		
		String result = cp1.toString();
		
		assertTrue(result.equals("1"));
	}
	
	@Test
	void testarToStringAberto() {
		Campo cp1 = CampoBuilder.umCampo().linhaEColuna(2, 2).abrir().agora();
		
		String result = cp1.toString();
		
		assertTrue(result.equals(" "));
	}
	
	@Test
	void testarToStringCampoNovo() {
		Campo cp1 = CampoBuilder.umCampo().linhaEColuna(2, 2).agora();
		
		String result = cp1.toString();
		
		assertTrue(result.equals("?"));
	}
	
}
