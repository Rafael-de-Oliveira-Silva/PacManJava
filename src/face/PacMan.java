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
	 * Posição X em que o PacMan se encontra
	 */
	private int x;
	/**
	 * Posição Y em que o PacMan se encontra
	 */
	private int y;
	/**
	 * Atributo para indicar se a boca está aberta ou fechada
	 */
	private boolean bocaAberta = true;
	/**
	 * Direção adotada pelo pacman
	 */
	private int direcao = Tabuleiro.TECLA_DIREITA;
	
	//
	// Métodos
	//
	/**
	 * Método Construtor
	 */
	public PacMan(Tabuleiro t, int x, int y) {
		// guardo a referência ao tabuleiro
		this.tab = t;
		// Guardo o ponto x,y do pacman
		this.x = x + 1;
		this.y = y + 1;
		// Disparo a execução de uma nova thread vinculada ao PacMan
		this.start();
	}
	
	/**
	 * O método run indica o programa que será executado 
	 * na thread que vai ser aberta para o pacman 	 
	 */
	public void run() {
		while(true)
			this.desenhar();
	}

	/**
	 * Método que apaga o pacman da tela
	 */
	public void apagarPacMan()
	{
		Graphics g = this.tab.getGraphics();
		g.setColor(Color.white);
		g.fillRect(this.x, this.y, Tabuleiro.LARGURA-2, Tabuleiro.LARGURA-2);			
	}
	
	/**
	 * Método que desloca o pacman pelo eixo X
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
	 * Método que desloca o pacman pelo eixo Y
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
	 * Método que desenha o pacman
	 */
	public void desenhar() {
		// Pego o contexto gráfico
		Graphics g = this.tab.getGraphics();
		// Se o contexto gráfico ainda não foi estabelecido, saio do método.
		if (g == null)
			return;
		// Variável que determinará o ângulo inicial de abertura da boca do pacman
		int anguloInicialDoArco = 30;
		// De acordo com a direção, determino o ângulo inicial de abertura da boca do pacman
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
		// Se a boca está aberta, fechamos a boca do pacman. Senão, abrimos a boca do pacman
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
	 * Método que faz o pacman dormir durante determinados milisegundos
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
