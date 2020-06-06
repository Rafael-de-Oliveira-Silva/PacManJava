package face;
	
import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tabuleiro extends Canvas implements KeyListener {

	//
	// CONSTANTES UTILIZADAS
	//
	public final static int TECLA_ESQUERDA = 37;
	public final static int TECLA_ACIMA = 38;
	public final static int TECLA_DIREITA = 39;
	public final static int TECLA_ABAIXO = 40;
	public final static int TECLA_ESPACO = 32;
	public final static int INICIO_X = 0;
	public final static int INICIO_Y = 0;
	public final static int LARGURA = 30;
	public final static int TAM_X = LARGURA * 17;
	public final static int TAM_Y = LARGURA * 17;

	//
	// ATRIBUTOS
	//
	/**
	 * Atributo utilizado para referenciar o pacman que anda pelo tabuleiro
	 */
	private PacMan 	    pMan; 
	
	/**
	 * Atributo utilizado para referenciar os fantasmas que andam pelo tabuleiro
	 */
	private Fantasma[]  ghosts;
	
	//
	// MÉTODOS
	//
	/**
	 * Método Construtor
	 * Quando o tabuleiro é criado (na classe Programa), ele através de seu
	 * método construtor (inicializador) vai criar o pacman e os fantasmas.
	 */
	public Tabuleiro(){
		// Crio o pacman e guardo a referência para ele através do atributo pMan
		this.pMan = new PacMan(this, INICIO_X, INICIO_Y);
		// Crio o array com quatro posições para referenciar os fantasmas
		this.ghosts = new Fantasma[4];
		// Crio os fantasmas e guardo a referência para eles no array
		this.ghosts[0] = new Fantasma(this);
		this.ghosts[1] = new Fantasma(this);
		this.ghosts[2] = new Fantasma(this);
		this.ghosts[3] = new Fantasma(this);
	}
	
	/**
	 * Desenha a grade na tela
	 */
	public void desenharGrade() {
		int i,j;		
		Graphics g;
		
		// Aguardando a obtenção do contexto gráfico
		do
			g = this.getGraphics();
		while(g == null);
					
		// Coloca as linhas verticais
		g.setColor(Color.white);
		for(i = INICIO_X; i < TAM_X; i+=LARGURA) 
			g.drawLine(i, INICIO_Y, i, TAM_Y);

		// Coloca as linhas horizontais
		for(i = INICIO_Y; i < TAM_Y; i+=LARGURA) 
			g.drawLine(INICIO_X, i, TAM_X, i);
	
		// Coloca as bolinhas
		for(i = INICIO_X; i < TAM_X; i+=LARGURA) 
			for(j = INICIO_Y; j < TAM_Y; j+=LARGURA) 
				g.fillOval(i + LARGURA/2, j + LARGURA/2, LARGURA/5, LARGURA/5);
	}

	/**
	 * Método para processar as teclas pressionadas
	 */
	public void keyPressed(KeyEvent e) {
		// Recuperamos o contexto gráfico da janela
		Graphics g = this.getGraphics();		
		// verificamos a tecla pressionada
		switch (e.getKeyCode()) {
			case TECLA_ESQUERDA:
				// mando para o pacman a mensagem para se deslocar pelo eixo X (negativamente)
				this.pMan.deslocarX(-LARGURA);
				break;
			case TECLA_DIREITA:
				// mando para o pacman a mensagem para se deslocar pelo eixo X (positivamente)
				this.pMan.deslocarX(LARGURA);
				break;
			case TECLA_ACIMA:
				// mando para o pacman a mensagem para se deslocar pelo eixo Y (negativamente)
				this.pMan.deslocarY(-LARGURA);
				break;
			case TECLA_ABAIXO:
				// mando para o pacman a mensagem para se deslocar pelo eixo Y (positivamente)
				this.pMan.deslocarY(LARGURA);
				break;
			case TECLA_ESPACO:
				break;
		}		
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}