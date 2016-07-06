package ImplementationSimulateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fr.litis.probability.law.Poisson;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.awt.event.ActionEvent;

public class saisie extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JLabel lblNombreOpration;
	private JTextField textField_4;
	private JTextField textField_6;
	private JLabel lblDureLectureTr;
	private JLabel lblDureLectureCl;
	private JLabel lblDureEcritureCl;
	private JLabel lblDureSimulation;
	private JLabel lblLambda;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					saisie frame = new saisie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public saisie() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 564);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre R\u00E9el \u00E0 Simuler");
		lblNewLabel.setBounds(34, 30, 208, 25);
		lblNewLabel.setBackground(Color.BLUE);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(252, 32, 125, 20);
		textField.setBackground(Color.LIGHT_GRAY);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Intervale de Validit\u00E9");
		lblNewLabel_1.setBounds(33, 121, 125, 14);
		lblNewLabel_1.setBackground(Color.BLUE);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(252, 118, 51, 20);
		textField_1.setBackground(Color.LIGHT_GRAY);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNombreClassique = new JLabel("Nombre Classique \u00E0 Simuler");
		lblNombreClassique.setBounds(33, 73, 208, 25);
		lblNombreClassique.setBackground(Color.BLUE);
		contentPane.add(lblNombreClassique);
		
		textField_2 = new JTextField();
		textField_2.setBounds(252, 75, 125, 20);
		textField_2.setBackground(Color.LIGHT_GRAY);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Valider");
		btnNewButton.setBounds(491, 460, 146, 35);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num1, num2, num3, num4,num6,num7, num8, num9, num10,num11,num12; double num13;
				try{
					
					num1 = Integer.parseInt(textField.getText()); // nombre temps réel a simuler
					num2 = Integer.parseInt(textField_1.getText()); // borne inf
					num3 = Integer.parseInt(textField_3.getText()); // borne sup
					num4 = Integer.parseInt(textField_2.getText());// nombre classique à simuler
					
			     	 num6 = Integer.parseInt(textField_5.getText()); // true 1, false 0.
			     	num7 = Integer.parseInt(textField_4.getText()); // borne inf 
					num8 = Integer.parseInt(textField_6.getText()); // borne sup
					num9 = Integer.parseInt(textField_7.getText()); // lecture temps réels 
					num10 = Integer.parseInt(textField_8.getText());// lecture données classiques
					num11 = Integer.parseInt(textField_9.getText()); // écriture données classiques
			     	num12 = Integer.parseInt(textField_10.getText()); // durée de simulation
			     	num13 = Double.parseDouble(textField_11.getText());// lambda
					
			     	System.out.println("================================================");
					System.out.println("                                          Simulation en cours ...  ");
					System.out.println("================================================");
					System.out.println("******************************************************************");
					System.out.println("Génération des données temps réels");
					BlockingQueue<SGBDTR> queue =  new ArrayBlockingQueue<SGBDTR>(num1);
					for(int i = 0; i < num1; i++){
					SGBDTR s1= new SGBDTR(i,num2,num3);
					queue.put(s1);
					System.out.println("donnees d'identifiant : "+queue.take().getLogin()+" est générer");
					}	
					System.out.println("**********************");
					System.out.println("******************************************************************");
					System.out.println("Génération des données classiques");
					
					BlockingQueue<SGBD> queue2 =  new ArrayBlockingQueue<SGBD>(num4);
					for(int j = 0; j < num4; j++){
					SGBD s2 = new SGBD(0,j,num2,num3);
					queue2.put(s2);
					
					System.out.println("donnees d'identifiant : "+queue2.take().getLogin()+" est générer");
					
					}	
					System.out.println("**********************");
					
					System.out.println("******************************************************************");
					System.out.println("Génération des transactions des mises à jours données temps réel");
				
					BlockingQueue<GenerateTransactionMS> queue3 =  new ArrayBlockingQueue<GenerateTransactionMS>(num1);
					for(int j = 0; j < num1; j++){
						GenerateTransactionMS s3 = new GenerateTransactionMS(j,queue.take(),num1);
						queue3.put(s3);
						}	
					
					GenerateTransactionMS t = new GenerateTransactionMS(5);
					t.setQueue2(queue3);
					
					Thread[]  genereT = new Thread[num1];
					System.out.println("Encours des générations des "+queue3.size()+" mises à jour");
					for (int i =  0 ; i < genereT.length ; i++) {
						genereT[i] =  new Thread(t);
						genereT[i].start();
					} 
					
					System.out.println("******************************************************************");
					System.out.println(" Fin de Générations des transactions des mises à jours.");
					System.out.println("******************************************************************");
					System.out.println("Début de Générations des transactions des utilisateurs.");
					System.out.println("***************************");
					System.out.println("Générations des transactions des utilisateurs Temps réels.");
					System.out.println("***************************");
					BlockingQueue<GenerateTransactionUSER> queue4 =  new ArrayBlockingQueue<GenerateTransactionUSER>(num8);
					for(int j = 0; j < num8; j++){
							GenerateTransactionUSER g = new GenerateTransactionUSER(num7,num8,num9,num10,num11, num12,num13,queue.take(),queue2.take());
							queue4.put(g);
					}	
					GenerateTransactionUSER t1 = new GenerateTransactionUSER();
					t1.setQueue2(queue4);
					Thread[]  genereTU = new Thread[num8];
					System.out.println("Encours des générations de "+queue3.size()+" transactions des utilisateurs");
					for (int i =  0 ; i < genereTU.length ; i++) {
						genereTU[i] =  new Thread(t1);
						
						genereTU[i].start();
					} 
					System.out.println("******************************************************************");
					System.out.println(" Fin de Générations des transactions des utilisateurs.");
					System.out.println("******************************************************************");
					
				//  le processus de poisson
			     	//Poisson p = new Poisson(num6);
					//p.generatePoissonProcess(num7);
					
					
					 
					System.out.println("================================================");
					System.out.println("                                         Fin de Simulation :)  ");
					System.out.println("================================================");
					System.out.println("******************************************************************");
					
				}catch(Exception err){
					
					JOptionPane.showMessageDialog(null,"Veuillez entrer des nombres valide");
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(Color.DARK_GRAY);
		contentPane.add(btnNewButton);
		
		textField_3 = new JTextField();
		textField_3.setBounds(327, 118, 51, 20);
		textField_3.setBackground(Color.LIGHT_GRAY);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblVerrouillage = new JLabel("Verrouillage(0 ou 1)");
		lblVerrouillage.setBackground(Color.BLUE);
		lblVerrouillage.setBounds(34, 175, 136, 25);
		contentPane.add(lblVerrouillage);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBackground(Color.LIGHT_GRAY);
		textField_5.setBounds(252, 177, 51, 20);
		contentPane.add(textField_5);
		
		lblNombreOpration = new JLabel("nombre op\u00E9ration");
		lblNombreOpration.setBackground(Color.BLUE);
		lblNombreOpration.setBounds(34, 229, 136, 25);
		contentPane.add(lblNombreOpration);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBackground(Color.LIGHT_GRAY);
		textField_4.setBounds(252, 231, 51, 20);
		contentPane.add(textField_4);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBackground(Color.LIGHT_GRAY);
		textField_6.setBounds(326, 231, 51, 20);
		contentPane.add(textField_6);
		
		lblDureLectureTr = new JLabel("Dur\u00E9e Lecture TR");
		lblDureLectureTr.setBackground(Color.BLUE);
		lblDureLectureTr.setBounds(34, 265, 136, 25);
		contentPane.add(lblDureLectureTr);
		
		lblDureLectureCl = new JLabel("Dur\u00E9e Lecture CL");
		lblDureLectureCl.setBackground(Color.BLUE);
		lblDureLectureCl.setBounds(34, 301, 136, 25);
		contentPane.add(lblDureLectureCl);
		
		lblDureEcritureCl = new JLabel("Dur\u00E9e Ecriture CL");
		lblDureEcritureCl.setBackground(Color.BLUE);
		lblDureEcritureCl.setBounds(34, 337, 136, 25);
		contentPane.add(lblDureEcritureCl);
		
		lblDureSimulation = new JLabel("Dur\u00E9e Simulation");
		lblDureSimulation.setBackground(Color.BLUE);
		lblDureSimulation.setBounds(34, 385, 136, 25);
		contentPane.add(lblDureSimulation);
		
		lblLambda = new JLabel("lambda");
		lblLambda.setBackground(Color.BLUE);
		lblLambda.setBounds(34, 421, 136, 25);
		contentPane.add(lblLambda);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBackground(Color.LIGHT_GRAY);
		textField_7.setBounds(252, 273, 51, 20);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBackground(Color.LIGHT_GRAY);
		textField_8.setBounds(252, 303, 51, 20);
		contentPane.add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBackground(Color.LIGHT_GRAY);
		textField_9.setBounds(252, 339, 51, 20);
		contentPane.add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBackground(Color.LIGHT_GRAY);
		textField_10.setBounds(252, 387, 51, 20);
		contentPane.add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBackground(Color.LIGHT_GRAY);
		textField_11.setBounds(252, 423, 51, 20);
		contentPane.add(textField_11);
	}
}
