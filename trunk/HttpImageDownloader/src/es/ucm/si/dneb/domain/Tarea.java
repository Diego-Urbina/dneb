
package es.ucm.si.dneb.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="TAREA")
@NamedQueries({
	@NamedQuery(name="buscarUsuarioContrasenia",query="select u from User u where username = :user and password = :pass")
})
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID_TAREA")
    private long idTarea;

    private Date fechaCreacion;
    
    private Date fechaUltimaActualizacion;
    
    private String arInicial;
    
    private String decInicial;
    
    private String arFinal;
    
    private String decFinal;
    
    private double alto;
    
    private double ancho;
    
    
    
    
    

}
