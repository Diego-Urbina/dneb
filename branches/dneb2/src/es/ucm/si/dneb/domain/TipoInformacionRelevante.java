package es.ucm.si.dneb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@Entity
@Table(name="TIP_INFO_REL")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "alias",
    "description"
})
@XmlRootElement(name = "TipoInformacionRelevante")
public class TipoInformacionRelevante {
	
	
	@Id
    @Column(name="ID")
    @XmlElement(required = true)
    private long id;
	
	@Column(name="ALIAS", nullable =false)
	@XmlElement(required = true)
	private String alias;
	
	@Column(name="DESCRIPCION", nullable =false)
	@XmlElement
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";
	    
	    String retValue = "";
	    
	    retValue = "TipoInformacionRelevante ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "alias = " + this.alias + TAB
	        + "description = " + this.description + TAB
	        + " )";
	
	    return retValue;
	}
	
	
	
}
