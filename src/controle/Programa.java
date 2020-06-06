package controle;

import face.Tabuleiro;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Programa {
	public static void main(String args[]) {
		final JFrame frame = new JFrame();
		Tabuleiro canvas = new Tabuleiro();
		frame.add(canvas);
		frame.setSize(600, 600);
		frame.getContentPane().setBackground(Color.black);
		frame.setTitle("Canvas");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.validate();
		frame.setVisible(true);
		frame.addKeyListener(canvas);
		dormir(100);
		canvas.desenharGrade();
	}
	
	/** 
	 * Método que faz o programa dormir durante determinados milisegundos
	 * @param segundos
	 */
	public static void dormir(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
