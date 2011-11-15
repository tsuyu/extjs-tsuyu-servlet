package com.tsuyu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="icon", catalog="tsuyu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Icon implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7172703571161703856L;
	private Integer iconId;
	private String iconName;
	private Boolean defaults;
	
	public Icon(Integer iconId,String iconName,Boolean defaults){
		this.iconId = iconId;
		this.iconName = iconName;
		this.defaults = defaults;
	}
	
	public Icon(){
		
	}
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ICONID", unique = true, nullable = false)
	public Integer getIconId() {
		return this.iconId;
	}

	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}
	@Column(name = "ICONNAME", nullable = false, length = 225)
	public String getIconName() {
		return this.iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	@Column(name = "DEFAULT", nullable = false, length = 1)
	public Boolean getDefaults() {
		return this.defaults;
	}

	public void setDefaults(Boolean defaults) {
		this.defaults = defaults;
	}
}
