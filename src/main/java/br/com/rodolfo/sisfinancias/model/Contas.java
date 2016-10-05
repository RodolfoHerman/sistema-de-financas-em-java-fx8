/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rodolfo.sisfinancias.model;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author h-e-r
 */
@Entity
@Table (name = "Contas")
public class Contas {
    
    //    @Temporal (TemporalType.DATE)
    
    private IntegerProperty codCon = new SimpleIntegerProperty();
    private StringProperty nome = new SimpleStringProperty();
    private ObjectProperty <LocalDate> datVct = new SimpleObjectProperty<LocalDate>();
    private ObjectProperty <LocalDate> datPgt = new SimpleObjectProperty<LocalDate>();
    private DoubleProperty valorReal = new SimpleDoubleProperty();
    private DoubleProperty valorPago = new SimpleDoubleProperty();

    
    public final void setCodCon (Integer value) {
        codCon.set(value);
    }
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "CodCon")
    public final Integer getCodCon () {
        return codCon.get();
    }
    
    public final IntegerProperty codConProperty () {
        return codCon;
    }
    
    public final void setNome(String value) {
        nome.set(value);
    }

    @Column (name = "NomCon", length = 55)
    public final String getNome() {
        return nome.get();
    }

    public final StringProperty nomeProperty() {
        return nome;
    }
   
    public final void setDatVct (LocalDate value){
        datVctProperty().set(value);
    }
    
    public final ObjectProperty<LocalDate> datVctProperty () {
        return datVct;
    }
    
    @Column (name = "DatVct")
    public final LocalDate getDatVct() {
        return datVctProperty().get();
    }
    
    public final void setDatPgt (LocalDate value) {
        datPgtProperty().set(value);
    }
    
    public final ObjectProperty<LocalDate> datPgtProperty () {
        return datPgt;
    }
    
    @Column (name = "DatPgt")
    public final LocalDate getDatPgt () {
        return datPgtProperty().get();
    }

    public final void setValorReal(Double value) {
        valorReal.set(value);
    }

    @Column (name = "ValRea", scale = 2)
    public final Double getValorReal() {
        return valorReal.get();
    }

    public final DoubleProperty valorRealProperty() {
        return valorReal;
    }

    public final void setValorPago(Double value) {
        valorPago.set(value);
    }
    
    @Column (name = "ValPag", scale = 2)
    public final Double getValorPago() {
        return valorPago.get();
    }

    public final DoubleProperty valorPagoProperty() {
        return valorPago;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.codCon != null ? this.codCon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contas other = (Contas) obj;
        if (this.codCon != other.codCon && (this.codCon == null || !this.codCon.equals(other.codCon))) {
            return false;
        }
        return true;
    }

}
