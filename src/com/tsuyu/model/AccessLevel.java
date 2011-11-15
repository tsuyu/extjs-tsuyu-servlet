package com.tsuyu.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "access_level", catalog = "tsuyu")
public class AccessLevel implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2713682637498749518L;
	private Integer accessLevelId;
	private String accessLevelName;
	private Set<SignIn> signIns = new HashSet<SignIn>(0);
	private Set<Accordian> accordians = new HashSet<Accordian>(0);
	
	public AccessLevel(){
	}
	
	public AccessLevel(Set<SignIn> signIns, Set<Accordian> accordians){
		this.signIns = signIns;
		this.accordians = accordians;
	}
	
	public AccessLevel(Integer accessLevelId,String accessLevelName,Set<SignIn> signIns,Set<Accordian> accordians){
		this.accessLevelId = accessLevelId;
		this.accessLevelName = accessLevelName;
		this.signIns = signIns;
		this.accordians = accordians;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ACCESSLEVELID", unique = true, nullable = false)
	public Integer getAccessLevelId() {
		return this.accessLevelId;
	}
	public void setAccessLevelId(Integer accessLevelId) {
		this.accessLevelId = accessLevelId;
	}
	@Column(name = "ACCESSLEVELNAME", nullable = false, length = 225)
	public String getAccessLevelName() {
		return this.accessLevelName;
	}
	public void setAccessLevelName(String accessLevelName) {
		this.accessLevelName = accessLevelName;
	}
	
	@OneToMany(orphanRemoval=true,fetch = FetchType.LAZY, mappedBy = "accessLevel")
	@org.hibernate.annotations.Cascade(value = {CascadeType.SAVE_UPDATE})
	public Set<SignIn> getSignIns() {
		return this.signIns;
	}

	public void setSignIns(Set<SignIn> signIns) {
		this.signIns = signIns;
	}
	
	@OneToMany(orphanRemoval=true,fetch = FetchType.LAZY, mappedBy = "accessLevel")
	@org.hibernate.annotations.Cascade(value = {CascadeType.SAVE_UPDATE})
	public Set<Accordian> getAccordians() {
		return this.accordians;
	}

	public void setAccordians(Set<Accordian> accordians) {
		this.accordians = accordians;
	}

}
