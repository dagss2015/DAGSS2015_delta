/*
 Proyecto Java EE, DAGSS-2014
 */

package es.uvigo.esei.dagss.dominio.daos;

import es.uvigo.esei.dagss.dominio.entidades.Cita;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;


@Stateless
@LocalBean
public class CitaDAO  extends GenericoDAO<Cita>{    

    public List<Cita> getAll(String fecha) {
        Query q = em.createQuery("SELECT c FROM Cita AS c "
                + "  WHERE c.fecha = :fecha");
        q.setParameter("fecha",Calendar.getInstance().getTime()); 
        return q.getResultList();
        
        /*
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Query q = em.createQuery("SELECT c FROM Cita AS c "
                + "  WHERE c.fecha = '2015/12/03'");
        q.setParameter("date", dateFormat.format(date)); 
        return q.getResultList();
        */
    }
    // Completar aqui
}
