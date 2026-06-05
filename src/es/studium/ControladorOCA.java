package es.studium;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ControladorOCA extends WindowAdapter implements ActionListener, KeyListener
{
	ModeloOCA moca;
	VistaInicioOCA vioca;
	VistaOpcionesOCA vooca;
	VistaJuegoOCA vjoca;
	VistaRankingOCA vroca;

	int cantidadJugador;
	int[] casillaOCA =
	{ 5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 56 };
	int saltoOCA = 0;
	boolean boolCasillaOCA = false;
	int[] casillaPuente =
	{ 6, 12 };
	int saltoPuente = 0;
	int retrocesoLaberinto = 0;
	int retrocesoMuerte = 0;
	int tipoEvento = 0;
	boolean retroceso = false;
	String nombreGanador;
	int tiradasGanador;
	ArrayList<String> listaRanking = new ArrayList<String>();

	public ControladorOCA(ModeloOCA moca, VistaInicioOCA vioca)
	{
		this.moca = moca;
		this.vioca = vioca;

		vioca.addWindowListener(this);
		vioca.btnInicio.addActionListener(this);
		vioca.btnRanking.addActionListener(this);
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

			else if (e.getSource().equals(vioca.btnRanking))
			{
				listaRanking = moca.consultarRanking();
				int cantidadLabel = listaRanking.size();
				this.vroca = new VistaRankingOCA(cantidadLabel);
				this.vroca.addWindowListener(this);
				this.vroca.btnVolver.addActionListener(this);
				for(int i = 0; i < cantidadLabel; i++)
				{
					vroca.lblList.get(i).setText(listaRanking.get(i));
				}
				vioca.setVisible(false);
			}
			
			else if (e.getSource().equals(vioca.btnInicio))
			{
				vooca = new VistaOpcionesOCA();
				vooca.addWindowListener(this);
				vooca.dlg.addWindowListener(this);
				vooca.btnJugar.addActionListener(this);
				vooca.btnContinuar.addActionListener(this);
				vioca.setVisible(false);
			}
		}

		if (vroca != null)
		{
			if (e.getSource().equals(vroca.btnVolver))
			{
				vioca.setVisible(true);
				vroca.dispose();
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
				vooca.dlg.setVisible(true);
			}
			
			else if (e.getSource().equals(vooca.btnContinuar))
			{
				if(vooca.txfDlg != null)
				{
					crearVistaJuego();
					vooca.dispose();
				}
			}
		}

		if (vjoca != null)
		{
			if (e.getSource().equals(vjoca.btnDado))
			{
				int dado = moca.lanzarDado();
				vjoca.posJ1 += dado;
				tiradasGanador++;
				vjoca.lblTiradasJ1.setText("Tiradas: " + String.valueOf(tiradasGanador));
				
				String n = String.valueOf(dado);
				vjoca.dadoSeleccionado = Toolkit.getDefaultToolkit().getImage("dado" + n + ".png"); //dibuja la cara del dado
				vjoca.cnvDado.repaint();

				boolCasillaOCA = false;
				for (int i = 0; i < casillaOCA.length; i++) //comprueba los saltos de casillaOCA
				{
					if (casillaOCA[i] - 1 == vjoca.posJ1 && i < casillaOCA.length - 1 && boolCasillaOCA == false)
					{
						saltoOCA = casillaOCA[i + 1] - 1;
						vjoca.cnvTablero.repaint();
						vjoca.dlgEvento.setTitle("¡¡¡ENHORABUENA!!!");
						vjoca.lblDlgEvento.setText("¡¡¡De OCA en OCA!!!");
						tipoEvento = 1;
						vjoca.dlgEvento.setVisible(true);
						boolCasillaOCA = true;
					}
				}
				
				if(vjoca.posJ1 == casillaPuente[0]-1) //comprueba los saltos de casillaPuente
				{
					saltoPuente = casillaPuente[1]-1;
					vjoca.cnvTablero.repaint();
					vjoca.dlgEvento.setTitle("¡¡¡ENHORABUENA!!!");
					vjoca.lblDlgEvento.setText("¡¡¡Avanzas en el puente!!!");
					tipoEvento = 2;
					vjoca.dlgEvento.setVisible(true);
				}
				
				else if(vjoca.posJ1 == casillaPuente[1]-1)
				{
					saltoPuente = casillaPuente[0]-1;
					vjoca.cnvTablero.repaint();
					vjoca.dlgEvento.setTitle("¡¡¡Mejor suerte la proxima!!!");
					vjoca.lblDlgEvento.setText("¡¡¡Retrocedes en el puente!!!");
					tipoEvento = 2;
					vjoca.dlgEvento.setVisible(true);
				}
				
				if(vjoca.posJ1 == 41)//comprueba los saltos de casillaLaberinto
				{
					retrocesoLaberinto = vjoca.posJ1-30;
					vjoca.cnvTablero.repaint();
					vjoca.dlgEvento.setTitle("¡¡¡Mejor suerte la proxima!!!");
					vjoca.lblDlgEvento.setText("¡¡¡Retrocedes en el laberinto!!!");
					tipoEvento = 3;
					vjoca.dlgEvento.setVisible(true);
				}
				
				if(vjoca.posJ1 == 57)//comprueba los saltos de casillaEliminacion
				{
					retrocesoMuerte = 0;
					vjoca.cnvTablero.repaint();
					vjoca.dlgEvento.setTitle("¡¡¡Mejor suerte la proxima!!!");
					vjoca.lblDlgEvento.setText("¡¡¡Retrocedes al inicio!!!");
					tipoEvento = 4;
					vjoca.dlgEvento.setVisible(true);
				}

				retroceso = false;
				if ((vjoca.posJ1) > 62) //comprueba los rebotes al llegar al final
				{
					vjoca.posJ1 -= dado;

					for (int i = 0; i < dado; i++)
					{
						if (vjoca.posJ1 == 61)
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
					
					if(vjoca.posJ1 == 57)//comprueba los saltos de casillaEliminacion DENUEVO
					{
						retrocesoMuerte = 0;
						vjoca.cnvTablero.repaint();
						vjoca.dlgEvento.setTitle("¡¡¡Mejor suerte la proxima!!!");
						vjoca.lblDlgEvento.setText("¡¡¡Retrocedes al inicio!!!");
						tipoEvento = 4;
						vjoca.dlgEvento.setVisible(true);
					}
					
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
				tiradasGanador = 0;
				vioca.setVisible(true);
				vjoca.dispose();
			}

			else if (e.getSource().equals(vjoca.btnNoFin))
			{
				tiradasGanador = 0;
				nombreGanador = vjoca.lblNombreJ1.getText();
				tiradasGanador = Integer.parseInt(vjoca.lblTiradasJ1.getText().split(": ")[1]); 
				moca.ranking(nombreGanador, tiradasGanador);
				vioca.setVisible(true);
				vjoca.dispose();
			}

			else if (e.getSource().equals(vjoca.btnSiFin))
			{
				tiradasGanador = 0;
				nombreGanador = vjoca.lblNombreJ1.getText();
				tiradasGanador = Integer.parseInt(vjoca.lblTiradasJ1.getText().split(": ")[1]); 
				moca.ranking(nombreGanador, tiradasGanador);
				vjoca.dispose();
				crearVistaJuego();
			}

			else if (e.getSource().equals(vjoca.btnContinuarEvento))
			{
				if(tipoEvento == 1)
				{
					vjoca.posJ1 = saltoOCA;
					vjoca.cnvTablero.repaint();
					vjoca.dlgEvento.dispose();
				}
				
				else if(tipoEvento == 2)
				{
					vjoca.posJ1 = saltoPuente;
					vjoca.cnvTablero.repaint();
					vjoca.dlgEvento.dispose();
				}
				
				else if(tipoEvento == 3)
				{
					vjoca.posJ1 = retrocesoLaberinto;
					vjoca.cnvTablero.repaint();
					vjoca.dlgEvento.dispose();
				}
				
				else if(tipoEvento == 4)
				{
					vjoca.posJ1 = retrocesoMuerte;
					vjoca.cnvTablero.repaint();
					vjoca.dlgEvento.dispose();
				}
				
			}
		}
	}

	public void windowClosing(WindowEvent e)
	{
		if (vioca != null && e.getSource().equals(vioca))
		{
			System.exit(0);
		}

		else if (vroca != null && e.getSource().equals(vroca))
		{
			vioca.setVisible(true);
			vroca.dispose();
		}
		
		else if (vooca != null && e.getSource().equals(vooca))
		{
			vioca.setVisible(true);
			vooca.dispose();
		}
		
		else if (vooca != null && e.getSource().equals(vooca.dlg))
		{
			vooca.dlg.dispose();
		}

		else if (vjoca != null && e.getSource().equals(vjoca))
		{
			vjoca.dlgSalir.setVisible(true);
		}

		else if (vjoca != null && e.getSource().equals(vjoca.dlgSalir))
		{
			tiradasGanador = 0;
			vjoca.dlgSalir.dispose();
		}

		else if (vjoca != null && e.getSource().equals(vjoca.dlgFin))
		{
			vioca.setVisible(true);
			vjoca.dispose();
		}

		else if (vjoca != null && e.getSource().equals(vjoca.dlgEvento))
		{
			vjoca.dlgEvento.dispose();
		}
	}

	private void crearVistaJuego()
	{
		vjoca = new VistaJuegoOCA(cantidadJugador);
		vjoca.addWindowListener(this);
		vjoca.dlgSalir.addWindowListener(this);
		vjoca.dlgFin.addWindowListener(this);
		vjoca.dlgEvento.addWindowListener(this);
		vjoca.btnDado.addActionListener(this);
		vjoca.btnSiSalir.addActionListener(this);
		vjoca.btnNoSalir.addActionListener(this);
		vjoca.btnSiFin.addActionListener(this);
		vjoca.btnNoFin.addActionListener(this);
		vjoca.btnContinuarEvento.addActionListener(this);
		vjoca.lblNombreJ1.setText(vooca.txfDlg.getText());
	}
}