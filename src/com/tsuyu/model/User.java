package com.tsuyu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

// default package
// Generated 11-Mar-2011 00:10:29 by Hibernate Tools 3.4.0.CR1

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "tsuyu")
@org.hibernate.annotations.Entity(
		dynamicInsert = true,dynamicUpdate = true)
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6984881242221887435L;
	private Integer signinId;
	private String name;
	private String email;
	private SignIn signIn;

	public User() {
	}

	public User(SignIn signIn, Integer signinId, String name, String email) {
		this.signIn = signIn;
		this.signinId = signinId;
		this.name = name;
		this.email = email;
	}
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public SignIn getSignIn() {
		return this.signIn;
	}

	public void setSignIn(SignIn signIn) {
		this.signIn = signIn;
	}
    
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "signIn"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "SIGNINID", unique = true, nullable = false)
	public Integer getSigninId() {
		return this.signinId;
	}

	public void setSigninId(Integer signinId) {
		this.signinId = signinId;
	}
	
	@Column(name = "NAME", unique = true, nullable = false, length = 255)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "EMAIL", unique = true, nullable = false, length = 255)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
