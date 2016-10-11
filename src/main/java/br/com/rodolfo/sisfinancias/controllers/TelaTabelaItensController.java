/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinancias.controllers;

import br.com.rodolfo.sisfinancias.dao.DAOContas;
import br.com.rodolfo.sisfinancias.model.Contas;
import br.com.rodolfo.sisfinancias.util.ServiceLocator;
import br.com.rodolfo.sisfinancias.util.Util;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author h-e-r
 */
public class TelaTabelaItensController {

    private static final ObservableList<String> dataChoiceBox = FXCollections.observableArrayList("Todos", "2016", "2017", "2018", "2019",
            "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032",
            "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044", "2045",
            "2046", "2047", "2048", "2049", "2050");

    //COMPONENTES
    @FXML
    private ChoiceBox boxPesquisa;

    @FXML
    private ChoiceBox boxCalcular;

    @FXML
    private TableView<Contas> tabelaConteudo;

    @FXML
    private DatePicker dataVct;

    @FXML
    private DatePicker dataPgt;

    //BOTOES
    @FXML
    private Button bntLimpar;

    @FXML
    private Button bntPesquisar;

    @FXML
    private Button bntExcluir;

    @FXML
    private Button bntEditar;

    @FXML
    private Button bntNovo;

    @FXML
    private Button bntCancelar;

    @FXML
    private Button bntConfirmar;

    @FXML
    private Button bntAtualizar;

    //TEXTOS
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtReal;

    @FXML
    private TextField txtPgt;

    @FXML
    private TextField txtTJuros;

    @FXML
    private TextField txtTPago;

    //Modo edicao
    private BooleanProperty edit = new SimpleBooleanProperty();
    private StringConverter<Number> converter = new NumberStringConverter();
    private Contas contaAtual;
    private DAOContas daoContas;
    private String anoPesq;
    private String anoCalc;
    private LocalDate date = LocalDate.now();

    @FXML
    private void initialize() {

        anoCalc = anoPesq = "" + date.getYear();

        daoContas = ServiceLocator.getDAOContas();

        tabelaConteudo.setItems(FXCollections.observableArrayList(daoContas.pesquisar(anoPesq)));

        boxPesquisa.setValue("" + date.getYear());
        boxCalcular.setValue("" + date.getYear());

        boxPesquisa.setItems(dataChoiceBox);
        boxCalcular.setItems(dataChoiceBox);

        tabelaConteudo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contas>() {
            public void changed(ObservableValue<? extends Contas> observable, Contas oldValue, Contas newValue) {
                unbindAtributos(oldValue);
                bindAtributos(newValue);
            }
        });

        atualizar();

        bntLimpar.disableProperty().bind(edit);
        bntPesquisar.disableProperty().bind(edit);
        bntNovo.disableProperty().bind(edit);
        bntEditar.disableProperty().bind(tabelaConteudo.getSelectionModel()
                .selectedItemProperty().isNull().or(edit));
        bntExcluir.disableProperty().bind(tabelaConteudo.getSelectionModel()
                .selectedItemProperty().isNull().or(edit));
        bntCancelar.disableProperty().bind(edit.not());
        bntConfirmar.disableProperty().bind(edit.not());
        tabelaConteudo.disableProperty().bind(edit);

        txtNome.disableProperty().bind(edit.not());
        txtReal.disableProperty().bind(edit.not());
        txtPgt.disableProperty().bind(edit.not());
        dataVct.disableProperty().bind(edit.not());
        dataPgt.disableProperty().bind(edit.not());

    }

    @FXML
    private void limpar() {

        boxPesquisa.setValue("" + date.getYear());
        anoPesq = "" + date.getYear();
        refresh();

    }

    @FXML
    private void itenSelecionadoPesquisa() {

        boxPesquisa.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                anoPesq = newValue.equals("Todos") ? null : newValue;
            }
        });

    }

    @FXML
    private void itenSelecionadoCalcular() {

        boxCalcular.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                anoCalc = newValue.equals("Todos") ? null : newValue;
            }
        });

    }

    @FXML
    private void pesquisar() {

        refresh();
    }

    @FXML
    private void atualizar() {

        List<Contas> consultaCalcular = daoContas.pesquisar(anoCalc);
        String[] valores = Util.calcularTotais(consultaCalcular);

        txtTJuros.setText("R$" + valores[0]);
        txtTPago.setText("R$" + valores[1]);

    }

    @FXML
    private void novo() {

        tabelaConteudo.getSelectionModel().clearSelection();
        edit.set(true);
        contaAtual = new Contas();
        bindAtributos(contaAtual);
        txtNome.requestFocus();

    }

    @FXML
    private void editar() {

        edit.set(true);
        contaAtual = tabelaConteudo.getSelectionModel().getSelectedItem();

    }

    @FXML
    private void excluir() {

        if (mostrarMsgConfirmacao()) {
            daoContas.excluir(tabelaConteudo.getSelectionModel().getSelectedItem());
            refresh();
            atualizar();
        }

        tabelaConteudo.getSelectionModel().clearSelection();
        limparCampos();

    }

    @FXML
    private void cancelar() {

        edit.set(false);

        unbindAtributos(contaAtual);
        tabelaConteudo.getSelectionModel().clearSelection();
        limparCampos();
        refresh();

    }

    @FXML
    private void confirmar() {

        String msgValidacao = validarCampos();

        if (!msgValidacao.isEmpty()) {

            mostrarMsgValidacao(msgValidacao);
            return;
        }

        edit.set(false);

        daoContas.salvar(contaAtual);

        unbindAtributos(contaAtual);
        tabelaConteudo.getSelectionModel().clearSelection();
        limparCampos();

        atualizar();
        refresh();
    }

    private void bindAtributos(Contas conta) {

        if (conta != null) {

            txtNome.textProperty().bindBidirectional(conta.nomeProperty());
            dataVct.valueProperty().bindBidirectional(conta.datVctProperty());
            dataPgt.valueProperty().bindBidirectional(conta.datPgtProperty());
            txtReal.textProperty().bindBidirectional(conta.valorRealProperty(), converter);
            txtPgt.textProperty().bindBidirectional(conta.valorPagoProperty(), converter);

        }

    }

    private void unbindAtributos(Contas conta) {

        if (conta != null) {

            txtNome.textProperty().unbindBidirectional(conta.nomeProperty());
            dataVct.valueProperty().unbindBidirectional(conta.datVctProperty());
            dataPgt.valueProperty().unbindBidirectional(conta.datPgtProperty());
            txtReal.textProperty().unbindBidirectional(conta.valorRealProperty());
            txtPgt.textProperty().unbindBidirectional(conta.valorPagoProperty());

            limparCampos();
        }

    }

    private void limparCampos() {

        txtNome.clear();
        dataVct.setValue(null);
        dataPgt.setValue(null);
        txtReal.clear();
        txtPgt.clear();

    }

    private void refresh() {

        tabelaConteudo.setItems(FXCollections.observableArrayList(daoContas.pesquisar(anoPesq)));

    }

    private String validarCampos() {

        StringBuilder msg = new StringBuilder();

        if (Util.isEmpty(txtNome.getText())) {
            msg.append("Necessário informar o nome da conta.").append(Util.getNewLine());
        }
        
        if (Util.comparator(txtReal.getText(), txtPgt.getText())) {
            msg.append("O valor da conta é maior que o valor pago.").append(Util.getNewLine());
        }

        if (Util.isEmpty(txtPgt.getText())) {
            msg.append("Necessário informar o valor que foi pago.").append(Util.getNewLine());
        }

        if (Util.isEmpty(txtReal.getText())) {
            msg.append("Necessário informar o valor real da conta.").append(Util.getNewLine());
        }

        if (Util.isEmpty(dataVct)) {
            msg.append("Necessário informar o dia de vencimento da conta.").append(Util.getNewLine());
        }

        if (Util.isEmpty(dataPgt)) {
            msg.append("Necessário informar o dia de pagamento da conta.").append(Util.getNewLine());
        }

        return (msg.toString());

    }

    private void mostrarMsgValidacao(String msg) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro de validação");
        alert.setHeaderText("Informação(ões) incorreta(s)");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private boolean mostrarMsgConfirmacao() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Confirmação de exclusão");
        alert.setContentText("Gostaria realmente de excluir a conta ?");
        alert.showAndWait();

        return (alert.getResult().getText().equals("OK"));
    }

}
