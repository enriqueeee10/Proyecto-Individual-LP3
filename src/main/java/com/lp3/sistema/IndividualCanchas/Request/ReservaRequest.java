package com.lp3.sistema.IndividualCanchas.Request;

public class ReservaRequest {
    private String fechaReserva;
    private String horaInicio;
    private String horaFinal;
    private int idCanchaFutbol;
    private int idUsuario;
    private int idCliente;
    private String observaciones;
    private int pago;
    private int totalDepositado;

    // Getters y setters

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public int getIdCanchaFutbol() {
        return idCanchaFutbol;
    }

    public void setIdCanchaFutbol(int idCanchaFutbol) {
        this.idCanchaFutbol = idCanchaFutbol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getPago() {
        return pago;
    }

    public void setPago(int pago) {
        this.pago = pago;
    }

    public int getTotalDepositado() {
        return totalDepositado;
    }

    public void setTotalDepositado(int totalDepositado) {
        this.totalDepositado = totalDepositado;
    }
}
