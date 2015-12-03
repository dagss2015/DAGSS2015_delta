/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uvigo.esei.dagss.controladores.medico;

import es.uvigo.esei.dagss.dominio.daos.CitaDAO;
import es.uvigo.esei.dagss.dominio.daos.MedicoDAO;
import es.uvigo.esei.dagss.dominio.daos.PacienteDAO;
import es.uvigo.esei.dagss.dominio.entidades.Cita;
import es.uvigo.esei.dagss.dominio.entidades.EstadoCita;
import es.uvigo.esei.dagss.dominio.entidades.Medico;
import es.uvigo.esei.dagss.dominio.entidades.Paciente;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author rplopez
 */
@Named(value = "gestionCitasMedico")
@SessionScoped
public class GestionCitasMedico implements Serializable{
    //static final public Integer DURACION_CITA_POR_DEFECTO = 15; // Citas de 15 minutos

    //Calendar.getInstance().getDate();
    @EJB
    CitaDAO citaDAO;

    @EJB
    MedicoDAO medicoDAO;
    
    @EJB
    PacienteDAO pacienteDAO;

    List<Cita> citas;
    Cita citaActual;

    public GestionCitasMedico() {
    }

    @PostConstruct
    public void inicializar() {
        citas = citaDAO.getAll(null);
        
    }

    /*
       Usado en la lista desplegable con los estados de una cita
    */
    public EstadoCita[]  getEstadosCitas() {
        return EstadoCita.values();
    }

    public List<Cita> getCitas() {
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public Cita getCitaActual() {
        return citaActual;
    }

    public void setCitaActual(Cita citaActual) {
        this.citaActual = citaActual;
    }

    public List<Paciente> getListadoPacientes() {
        return pacienteDAO.buscarTodos();
    }

    public List<Medico> getListadoMedicos() {
        return medicoDAO.buscarTodos();
    }

    public void doEliminar() {
        citaDAO.eliminar(citaActual);
        citas = citaDAO.buscarTodos(); // Actualizar lista de centros
    }

    public void doNuevo() {
        citaActual = new Cita(); // Cita valia   
        //citaActual.setDuracion(DURACION_CITA_POR_DEFECTO);
        citaActual.setEstado(EstadoCita.PLANIFICADA);
    }

    public void doEditar(Cita cita) {
        citaActual = cita;   // Otra alternativa: volver a refrescarlos desde el DAO
    }

    public void doGuardarNuevo() {
        // Crea un nuevo centro de salud
        citaActual = citaDAO.crear(citaActual);
        // Actualiza lista de centros de salud a mostrar
        citas = citaDAO.buscarTodos();

    }

    public void doGuardarEditado() {
        // Actualiza un centro de salud
        citaActual = citaDAO.actualizar(citaActual);
        // Actualiza lista de centros de salud a mostrar
        citas = citaDAO.buscarTodos();
    }

    public String doVolver() {
        return "../index?faces-redirect=true";
    }
}
