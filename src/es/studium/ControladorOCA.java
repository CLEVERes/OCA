package es.studium;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControladorOCA extends WindowAdapter implements ActionListener, KeyListener
{
	ModeloOCA moca;
	VistaInicioOCA vioca;
	VistaOpcionesOCA vooca;
	VistaJuegoOCA vjoca;

	int cantidadJugador;
	int[] casillaOCA = {5,9,14,18,23,27,32,36,41,45,50,54,56};
	boolean boolCasillaOCA = false;
	boolean retroceso = false;

	public ControladorOCA(ModeloOCA moca, VistaInicioOCA vioca)
	{
		this.moca = moca;
		this.vioca = vioca;

		vioca.addWindowListener(this);
		vioca.btnInicio.addActionListener(this);
		vioca.btnAyuda.addActionListener(this);
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public void keyPressed(KeyEvent e)
	{
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void actionPerformed(ActionEvent e)
	{
		if (vioca != null)
		{
			if (e.getSource().equals(vioca.btnAyuda))
			{

			}

			else if (e.getSource().equals(vioca.btnInicio))
			{
				vooca = new VistaOpcionesOCA();
				vooca.addWindowListener(this);
				vooca.btnJugar.addActionListener(this);
				vioca.setVisible(false);
			}
		}

		if (vooca != null)
		{
			/*
			 * if (vooca.chbUno.getState() == true) { cantidadJugador = 1; }
			 * 
			 * else if (vooca.chbDos.getState() == true) { cantidadJugador = 2; }
			 * 
			 * else if (vooca.chbTres.getState() == true) { cantidadJugador = 3; }
			 * 
			 * else if (vooca.chbCuatro.getState() == true) { cantidadJugador = 4;
			 * 
			 * }
			 */
			// para la cantidad de jugadores

			if (e.getSource().equals(vooca.btnJugar))
			{
				crearVistaJuego();
				vooca.dispose();
			}
		}

		if (vjoca != null)
		{
			if (e.getSource().equals(vjoca.btnDado))
			{
				int dado = moca.lanzarDado();
				vjoca.posJ1 += dado;
				
				for (int i = 1; i <= 6; i++)
				{
					if (i == dado)
					{
						String n = String.valueOf(dado);
						vjoca.dadoSeleccionado = Toolkit.getDefaultToolkit().getImage("dado" + n + ".png");
						vjoca.cnvDado.repaint();
					}
				}
				
				for(int i = 0; i < casillaOCA.length; i++)
				{
					if((casillaOCA[i]-1 == vjoca.posJ1) && i < casillaOCA.length - 1 && boolCasillaOCA == false)
					{
						vjoca.posJ1 = casillaOCA[i+1]-1;
						boolCasillaOCA = true;
						
						vjoca.cnvTablero.repaint();
						vjoca.dlgOCA.setVisible(true);
					}
				}
				
				boolCasillaOCA = false;

				if ((vjoca.posJ1) > 62)
				{
					vjoca.posJ1 -= dado;
					
					for(int i = 0; i < dado; i++)
					{
						if(vjoca.posJ1 == 61)
						{
							retroceso = true;
						}
						
						if (vjoca.posJ1 < 61 && retroceso == false)
						{
							vjoca.posJ1++;
						}
						
						else 
						{
							vjoca.posJ1--;
						}
					}
					retroceso = false;
				}
				
				vjoca.cnvTablero.repaint();

				if (vjoca.posJ1 == 62)
				{
					vjoca.dlgFin.setVisible(true);
				}

			}

			else if (e.getSource().equals(vjoca.btnNoSalir))
			{
				vjoca.dlgSalir.dispose();
			}

			else if (e.getSource().equals(vjoca.btnSiSalir))
			{
				vioca.setVisible(true);
				vjoca.dispose();
			}

			else if (e.getSource().equals(vjoca.btnNoFin))
			{
				vjoca.dispose();
				vioca.setVisible(true);
			}

			else if (e.getSource().equals(vjoca.btnSiFin))
			{
				vjoca.dispose();
				crearVistaJuego();

			}
			
			else if (e.getSource().equals(vjoca.btnContinuarOCA))
			{
				vjoca.dlgOCA.dispose();
			}
		}
	}

	public void windowClosing(WindowEvent e)
	{
		if (vioca != null && e.getSource().equals(vioca))
		{
			System.exit(0);
		}

		else if (vooca != null && e.getSource().equals(vooca))
		{
			vioca.setVisible(true);
			vooca.dispose();
		}

		else if (vjoca != null && e.getSource().equals(vjoca))
		{

			vjoca.dlgSalir.setVisible(true);
		}

		else if (vjoca != null && e.getSource().equals(vjoca.dlgSalir))
		{
			vjoca.dlgSalir.dispose();
		}

		else if (vjoca != null && e.getSource().equals(vjoca.dlgFin))
		{
			vioca.setVisible(true);
			vjoca.dispose();
		}
	}

	private void crearVistaJuego()
	{
		vjoca = new VistaJuegoOCA(cantidadJugador);
		vjoca.addWindowListener(this);
		vjoca.dlgSalir.addWindowListener(this);
		vjoca.dlgFin.addWindowListener(this);
		vjoca.dlgOCA.addWindowListener(this);
		vjoca.btnDado.addActionListener(this);
		vjoca.btnSiSalir.addActionListener(this);
		vjoca.btnNoSalir.addActionListener(this);
		vjoca.btnSiFin.addActionListener(this);
		vjoca.btnNoFin.addActionListener(this);
		vjoca.btnContinuarOCA.addActionListener(this);
	}
}