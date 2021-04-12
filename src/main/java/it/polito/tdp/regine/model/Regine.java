package it.polito.tdp.regine.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {

	// N è il numero di righe e colonne della scacchiera
	//   (righe e colonne numerate da 0 a N-1)
	// ad ogni livello posizioniamo una regina in una nuova riga
	
	// soluzione parziale: lista delle colonne in cui mettere le regine (prime righe)
	// 		List<Integer>
	// livello = quante righe sono già piene
	// livello = 0 => nessuna riga piena (devo mettere la regina nella riga 0)
	// livello = 3 => 3 righe piene (0, 1, 2), devo mettere la regina nella riga 3
	// [0]
	//     [0, 2]
	//            [0, 2, 1]
	
	private int N;
	private List<Integer> soluzione;
	
	public List<Integer> risolvi(int N){
		this.N = N;
		
		List<Integer> parziale = new ArrayList<Integer>();  //NO LinkedList
		this.soluzione = null;
		
		cerca(parziale, 0);
		
		
		return this.soluzione;
	}
	
	//cerca == true --> trovato 
	//cerca == false --> cerca ancora 
	//mettendo questo boolean ci permette di calcoalare solo UNA SOLUZIONE 
	//non tutte quante 
	
	private boolean cerca(List<Integer>parziale, int livello) {  //[0,6,4,7]
		
		if(livello==N) {
			// caso terminale
			
			//System.out.println(parziale);
			
			soluzione = new ArrayList<Integer>(parziale);
			
			return true;
			
		} else {
			for(int colonna=0; colonna<N; colonna++) {
				// if la possa nella casella [livello][colonna] è valida
				// se sì, aggiungi a parziale e fai ricorsione
				
				if(posValida(parziale, colonna, livello)) {
					
					parziale.add(colonna);   //[0,6,4,7,X]
					boolean trovato = cerca(parziale, livello+1);
					if(trovato)
						return true;
					parziale.remove(parziale.size()-1);  //BACKTRACKING
					
					
					//si potrebbe anche fare così ma non è molto efficiente 
					/*
					List<Integer> parzialeNuovo = new ArrayList<Integer>(parziale);
					parzialeNuovo.add(colonna);
					cerca(parzialeNuovo, livello+1);
					*/
					
				}
			}
			
			return false;
		}
	}

	private boolean posValida(List<Integer> parziale, int colonna, int livello) {
		
		//controlla se viene mangiata in verticale 
		if(parziale.contains(colonna)) {
			return false;
		}
		
		//controlla se viene mangiata in diagonale 
		//devo confrontare la posizione (livello, colonna) con la posizione 
		//(r,c) delle regine esistenti
		
		
		for(int r=0; r<livello; r++) {
			int c = parziale.get(r);
			
			if((r+c) == (livello+colonna) || (r-c) == (livello-colonna)) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
}
