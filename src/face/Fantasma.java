package face;

import java.awt.Color;
import java.awt.Graphics;

public class Fantasma extends Thread {
	//
	// Constantes
	//
	public final static int TAM_FANTASMA = Tabuleiro.LARGURA - 3;

	//
	// Atributos
	//
	/**
	 * Atributo para referenciar o tabuleiro
	 */
	private Tabuleiro tab;
	/**
	 * Posição X em que o fantasma se encontra
	 */
	private int x;
	/**
	 * Posição Y em que o fantasma se encontra
	 */
	private int y;
	/**
	 * Direção adotada pelo fantasmas
	 */
	private int direcao = Tabuleiro.TECLA_DIREITA;
	
	//
	// Métodos
	//
	/**
	 * Método Construtor
	 */
	public Fantasma(Tabuleiro tab) {
		// guardo a referência ao tabuleiro
		this.tab = tab;
		// Sorteio a posição em que está fantasma
		this.x = sortear(Tabuleiro.TAM_X / Tabuleiro.LARGURA) * Tabuleiro.LARGURA  +  2;
		this.y = sortear(Tabuleiro.TAM_Y / Tabuleiro.LARGURA) * Tabuleiro.LARGURA  +  2;
		// Disparo a execução de uma nova thread vinculada ao PacMan
		this.start();
	}
	
	/**
	 * O método run indica o programa que será executado 
	 * na thread que vai ser aberta para o fantasma 	 
	 */
	public void run() {
		while(true)
			desenhar();
	}

	/**
	 * Método que apaga o fantasma da tela
	 */
	public void apagarFantasma()
	{
		Graphics g = this.tab.getGraphics();
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, Tabuleiro.LARGURA-2, Tabuleiro.LARGURA-2);			
	}
	
	/**
	 * Método que desloca o fantasma pelo eixo X
	 * @param fator
	 */
	public void deslocarX(int fator) {
		if(fator < 0)
		{
			if(this.x <= 2)
				return;
			this.direcao = Tabuleiro.TECLA_ESQUERDA;
		}
		else
		{
			if(this.x >= Tabuleiro.TAM_X - Tabuleiro.LARGURA)
				return;
			this.direcao = Tabuleiro.TECLA_DIREITA;			
		}
		apagarFantasma();
		this.x += fator;
	}

	/**
	 * Método que desloca o fantasma pelo eixo Y
	 * @param fator
	 */
	public void deslocarY(int fator) {
		if(fator < 0)
		{
			if(this.y <= 2)
				return; 
			this.direcao = Tabuleiro.TECLA_ACIMA;
		}
		else
		{
			if(this.y >= Tabuleiro.TAM_Y - Tabuleiro.LARGURA)
				return;
			this.direcao = Tabuleiro.TECLA_ABAIXO;			
		}
		apagarFantasma();
		this.y += fator;
	}

	/**
	 * Método que desenha o fantasma
	 */
	public void desenhar() {
		// Pego o contexto gráfico
		Graphics g = this.tab.getGraphics();
		// Se o contexto gráfico ainda não foi estabelecido, saio do método.
		if (g == null)
			return;
		// Apago o fantasmas
		this.apagarFantasma();
		// Desenho o fantasma
		g.setColor(Color.red);
		g.fillRect(x, y, TAM_FANTASMA, TAM_FANTASMA);
		this.dormir(300);
		// Desloco o fantasma. Para isto, sorteio um número entre 0 e 3 para ver a nova direção do fantasma
		switch(this.sortear(3)){
		case 0:
			deslocarX(-Tabuleiro.LARGURA);
			break;
		case 1:
			deslocarX(Tabuleiro.LARGURA);
			break;
		case 2:
			deslocarY(-Tabuleiro.LARGURA);
			break;
		case 3:
			deslocarY(Tabuleiro.LARGURA);
			break;
		}
	}

	/**
	 * Método que vai sortear um número entre 0 e o limite
	 * @param limite
	 * @return
	 */
	public int sortear(int limite)
	{
		java.util.Random r = new java.util.Random();
		int i = r.nextInt(limite + 1);
		return i;
	}
	
	/** 
	 * Método que faz o fantasma dormir durante determinados milisegundos
	 * @param segundos
	 */
	public void dormir(int segundos) {
		try {
			Thread.sleep(segundos * 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
