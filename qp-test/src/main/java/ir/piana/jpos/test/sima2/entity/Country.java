package ir.piana.jpos.test.sima2.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the COUNTRY database table.
 * 
 */
@Entity
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;
	private long countryId;
	private long countryCode;
	private String countryName;
	private String countryTel;

	public Country() {
	}

	@Column(name = "COUNTRY_CODE")
	public long getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(long countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "COUNTRY_NAME")
	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name = "COUNTRY_TEL")
	public String getCountryTel() {
		return this.countryTel;
	}

	public void setCountryTel(String countryTel) {
		this.countryTel = countryTel;
	}

	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="COUNTRY_ID")
	public long getCountryId() {
		return countryId;
	}
}