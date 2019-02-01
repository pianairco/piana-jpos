package ir.piana.jpos.test.bazar.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The persistent class for the COUNTRY database table.
 * 
 */
@Entity
@Table(name = "country")
public class CountryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String title;
	private String phoneCode;

	public CountryEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "PHONECODE")
	public String getPhoneCode() {
		return this.phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}
}