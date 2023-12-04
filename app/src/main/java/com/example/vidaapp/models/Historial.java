package com.example.vidaapp.models;

public class Historial {
    int id;
    String servicio;
    int estrato;
    float valorUnit;
    int lec_ini;
    int lec_fin;
    int consumo;
    int valorPago;
    int image;

    public Historial(String servicio, int estrato, float valorUnit, int lec_ini, int lec_fin, int consumo, int valorPago, int image) {
        this.servicio = servicio;
        this.estrato = estrato;
        this.valorUnit = valorUnit;
        this.lec_ini = lec_ini;
        this.lec_fin = lec_fin;
        this.consumo = consumo;
        this.valorPago = valorPago;
        this.image = image;
    }

    public Historial() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public float getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(float valorUnit) {
        this.valorUnit = valorUnit;
    }

    public int getLec_ini() {
        return lec_ini;
    }

    public void setLec_ini(int lec_ini) {
        this.lec_ini = lec_ini;
    }

    public int getLec_fin() {
        return lec_fin;
    }

    public void setLec_fin(int lec_fin) {
        this.lec_fin = lec_fin;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public int getValorPago() {
        return valorPago;
    }

    public void setValorPago(int valorPago) {
        this.valorPago = valorPago;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}


