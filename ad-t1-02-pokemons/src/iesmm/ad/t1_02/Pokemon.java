package iesmm.ad.t1_02;

import java.io.Serializable;

public class Pokemon implements Serializable {

    private String nombre;
    private String tipo;
    private int vida;
    private int ataque;
    private int defensa;
    private int velocidad;

    public Pokemon(String nombre, String tipo, int vida, int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public String toString() {
        return "nombre=" + nombre +
                ", tipo=" + tipo +
                ", vida=" + vida +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", velocidad=" + velocidad;
    }

}