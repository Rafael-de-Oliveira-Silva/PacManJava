package face;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.Robot;
import java.util.Random;

public class PacMan extends Thread {
	//
	// Constantes
	//
	public final static int TAM_X = Tabuleiro.LARGURA - 2;
	public final static int TAM_Y = Tabuleiro.LARGURA - 2;

	//
	// Atributos
	//
	/**
	 * Atributo para referenciar o tabuleiro
	 */
	private Tabuleiro tab;
	/**
	 * Posi��o X em que o PacMan se encontra
	 */
	private int x;
	/**
	 * Posi��o Y em que o PacMan se encontra
	 */
	private int y;
	/**
	 * Atributo para indicar se a boca est� aberta ou fechada
	 */
	private boolean bocaAberta = true;
	/**
	 * Dire��o adotada pelo pacman
	 */
	private int direcao = Tabuleiro.TECLA_DIREITA;
	
	//
	// M�todos
	//
	/**
	 * M�todo Construtor
	 */
	public PacMan(Tabuleiro t, int x, int y) {
		// guardo a refer�ncia ao tabuleiro
		this.tab = t;
		// Guardo o ponto x,y do pacman
		this.x = x + 1;
		this.y = y + 1;
		// Disparo a execu��o de uma nova thread vinculada ao PacMan
		this.start();
	}
	
	/**
	 * O m�todo run indica o programa que ser� executado 
	 * na thread que vai ser aberta para o pacman 	 
	 */
	public void run() {
		while(true)
			this.desenhar();
	}

	/**
	 * M�todo que apaga o pacman da tela
	 */
	public void apagarPacMan()
	{
		Graphics g = this.tab.getGraphics();
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, Tabuleiro.LARGURA-2, Tabuleiro.LARGURA-2);			
	}
	
	/**
	 * M�todo que desloca o pacman pelo eixo X
	 * @param fator
	 */
	public void deslocarX(int fator) {
		this.apagarPacMan();
		if(fator < 0)
		{
			if(this.x <= 1)
				return;
			this.direcao = Tabuleiro.TECLA_ESQUERDA;
		}
		else
		{
			if(this.x >= Tabuleiro.TAM_X - Tabuleiro.LARGURA)
				return;
			this.direcao = Tabuleiro.TECLA_DIREITA;			
		}
		this.x += fator;
	}

	/**
	 * M�todo que desloca o pacman pelo eixo Y
	 * @param fator
	 */
	public void deslocarY(int fator) {
		this.apagarPacMan();
		if(fator < 0)
		{
			if(this.y <= 1)
				return; 
			this.direcao = Tabuleiro.TECLA_ACIMA;
		}
		else
		{
			if(this.y >= Tabuleiro.TAM_Y - Tabuleiro.LARGURA)
				return;
			this.direcao = Tabuleiro.TECLA_ABAIXO;			
		}
		this.y += fator;
	}

	/**
	 * M�todo que desenha o pacman
	 */
	public void desenhar() {
		// Pego o contexto gr�fico
		Graphics g = this.tab.getGraphics();
		// Se o contexto gr�fico ainda n�o foi estabelecido, saio do m�todo.
		if (g == null)
			return;
		// Vari�vel que determinar� o �ngulo inicial de abertura da boca do pacman
		int anguloInicialDoArco = 30;
		// De acordo com a dire��o, determino o �ngulo inicial de abertura da boca do pacman
		switch(direcao) {
			default: 
			case Tabuleiro.TECLA_DIREITA: 
			anguloInicialDoArco = 30;
			break;
		case Tabuleiro.TECLA_ESQUERDA: 
			anguloInicialDoArco = 210;
			break;
		case Tabuleiro.TECLA_ACIMA: 
			anguloInicialDoArco = 120;
			break;
		case Tabuleiro.TECLA_ABAIXO: 
			anguloInicialDoArco = 300;
			break;
		}
		// Apago o pacman
		this.apagarPacMan();
		// Cor do pacman
		g.setColor(Color.yellow);
		// Se a boca est� aberta, fechamos a boca do pacman. Sen�o, abrimos a boca do pacman
		if(bocaAberta)
			g.fillArc(this.x, this.y, TAM_X, TAM_Y, anguloInicialDoArco - 25, 350);
		else  
			g.fillArc(this.x, this.y, TAM_X, TAM_Y, anguloInicialDoArco, 300);
		// Desenhando o olho do pacman
		g.setColor(Color.black);
		g.fillOval(this.x+15, this.y+5, 5, 5);
		// fico parado 150 milisegundos 
		this.dormir(150);
		// Inverto o booleano associado com bocaAberta
		this.bocaAberta = !this.bocaAberta;
	}

	/** 
	 * M�todo que faz o pacman dormir durante determinados milisegundos
	 * @param segundos
	 */
	public void dormir(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
