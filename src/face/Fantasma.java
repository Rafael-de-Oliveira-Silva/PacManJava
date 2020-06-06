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
	 * Posi��o X em que o fantasma se encontra
	 */
	private int x;
	/**
	 * Posi��o Y em que o fantasma se encontra
	 */
	private int y;
	/**
	 * Dire��o adotada pelo fantasmas
	 */
	private int direcao = Tabuleiro.TECLA_DIREITA;
	
	//
	// M�todos
	//
	/**
	 * M�todo Construtor
	 */
	public Fantasma(Tabuleiro tab) {
		// guardo a refer�ncia ao tabuleiro
		this.tab = tab;
		// Sorteio a posi��o em que est� fantasma
		this.x = sortear(Tabuleiro.TAM_X / Tabuleiro.LARGURA) * Tabuleiro.LARGURA  +  2;
		this.y = sortear(Tabuleiro.TAM_Y / Tabuleiro.LARGURA) * Tabuleiro.LARGURA  +  2;
		// Disparo a execu��o de uma nova thread vinculada ao PacMan
		this.start();
	}
	
	/**
	 * O m�todo run indica o programa que ser� executado 
	 * na thread que vai ser aberta para o fantasma 	 
	 */
	public void run() {
		while(true)
			desenhar();
	}

	/**
	 * M�todo que apaga o fantasma da tela
	 */
	public void apagarFantasma()
	{
		Graphics g = this.tab.getGraphics();
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, Tabuleiro.LARGURA-2, Tabuleiro.LARGURA-2);			
	}
	
	/**
	 * M�todo que desloca o fantasma pelo eixo X
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
	 * M�todo que desloca o fantasma pelo eixo Y
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
	 * M�todo que desenha o fantasma
	 */
	public void desenhar() {
		// Pego o contexto gr�fico
		Graphics g = this.tab.getGraphics();
		// Se o contexto gr�fico ainda n�o foi estabelecido, saio do m�todo.
		if (g == null)
			return;
		// Apago o fantasmas
		this.apagarFantasma();
		// Desenho o fantasma
		g.setColor(Color.red);
		g.fillRect(x, y, TAM_FANTASMA, TAM_FANTASMA);
		this.dormir(300);
		// Desloco o fantasma. Para isto, sorteio um n�mero entre 0 e 3 para ver a nova dire��o do fantasma
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
	 * M�todo que vai sortear um n�mero entre 0 e o limite
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
	 * M�todo que faz o fantasma dormir durante determinados milisegundos
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
