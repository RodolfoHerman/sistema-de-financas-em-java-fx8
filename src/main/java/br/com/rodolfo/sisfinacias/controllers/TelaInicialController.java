/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinacias.controllers;

import br.com.rodolfo.sisfinacias.util.Util;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author h-e-r
 */
public class TelaInicialController {
    
    @FXML
    public void programador () {

        final String msgCabeca = "Informações do programador";
        final String msgCorpo = "Prorama desenvolvido por Rodolfo Herman Lara e Silva"
                + Util.getNewLine() + "data de fabricação: 04/10/2016"
                + Util.getNewLine() + "e-mail: ciencia.rodolfo@gmail.com";
        mostrarMsgItenMenu (msgCorpo, msgCabeca);
        
    }
    
    @FXML
    public void programa () {
        
        final String msgCabeca = "Informações do programa";
        final String msgCorpo = "O obetivo deste programa é o de possuir um banco de dados de todas as contas"
                + " para quem possam ser analisadas verificando o quanto foi gasto durante o ano com o valor real e com os juros/multas cobrados.";
        mostrarMsgItenMenu (msgCorpo, msgCabeca);
        
    }
    
    @FXML
    public void fechar () {
        Platform.exit();
        System.exit(0);
    }
    
    private void mostrarMsgItenMenu (String msgCorpo, String msgCabeca) {
        
        Alert alert = new Alert (AlertType.INFORMATION);
        alert.setTitle("Informações");
        alert.setHeaderText(msgCabeca);
        alert.setContentText(msgCorpo);
        alert.showAndWait();
        
    }
    
}
