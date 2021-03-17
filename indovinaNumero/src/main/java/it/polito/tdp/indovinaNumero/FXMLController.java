package it.polito.tdp.indovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX = 100; 
	private final int TMAX = 8;
	private int segreto; 
	private int tentativiFatti; 
	private boolean inGioco = false; 

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuovaPartita;
    
    @FXML
    private HBox layoutTentativo;

    @FXML
    private TextField txtTentativi;

    @FXML
    private TextField txtTentativoUtente;

    @FXML
    private Button btnProva;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	//gestione inizio nuova partita
    	this.segreto = (int)(Math.random()*NMAX)+1;
    	this.tentativiFatti = 0; 
    	this.inGioco = true; 
    	
    	//gestione dell'interfaccia
    	this.txtTentativi.setText(Integer.toString(TMAX));
    	this.layoutTentativo.setDisable(false);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	//lettura input dell'utente
    	String ts = txtTentativoUtente.getText();
    	
    	int tentativo; 
    	try {
    		tentativo = Integer.parseInt(ts);
    	}catch(NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero!");	//append text non cancella quello che c'era prima
    		return; 
    	}
    	
    	this.txtTentativoUtente.setText("");
    	this.tentativiFatti++;
    	this.txtTentativi.setText(Integer.toString(TMAX-this.tentativiFatti));
    	
    	if(tentativo == this.segreto) {
    		//HO INDOVINATO
    		txtRisultato.setText("HAI VINTO CON "+this.tentativiFatti+" TENTATIVI");
    		this.inGioco = false; 
    		this.layoutTentativo.setDisable(true);
    		return; 
    	}
    	
    	if(this.tentativiFatti == TMAX) {
    		//ho esaurito i tentativi
    		txtRisultato.setText("HAI PERSO. IL SEGRETO ERA: "+this.segreto);
    		this.inGioco = false; 
    		this.layoutTentativo.setDisable(true);
    		return; 
    	}
    	
    	//Non ho vinto -> devo informare l'utente circa la bontà del suo tentativo
    	if(tentativo<this.segreto) {
    		txtRisultato.setText("Tentativo troppo basso");
    	}
    	else {
    		txtRisultato.setText("Tentativo troppo alto");
    	}
    	
    	
    }

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
