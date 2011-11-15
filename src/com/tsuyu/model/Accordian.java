package com.tsuyu.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CascadeType;

import javax.persistence.OneToMany;

@Entity
@Table(name = "accordian", catalog = "tsuyu",uniqueConstraints = {
		@UniqueConstraint(columnNames = "ACCORDIANNAME"),
		@UniqueConstraint(columnNames = "ACCORDIANICON"),
		@UniqueConstraint(columnNames = "ACCORDIANSEQUENCE")})
@org.hibernate.annotations.Entity(
		dynamicInsert = true,dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Accordian implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3260538210287359226L;
	private Integer accordianId;
	private Integer accordianSequence;
	private String accordianName;
	private String accordianDescription;
	private String accordianIcon;
	private Integer createBy;
	private Date createTime;
	private Integer updatedBy;
	private Date updatedTime;
	private Set<Children> childrens = new HashSet<Children>(0);
	private AccessLevel accessLevel; 
	private transient Integer accessLevelId;

	public Accordian(){}
	
	public Accordian(Integer accordianSequence,String accordianName,String accordianDescription,
			String accordianIcon,Integer createBy,Date createTime,Integer updatedBy, Date updatedTime){
		this.accordianSequence = accordianSequence;
		this.accordianName = accordianName;
		this.accordianDescription = accordianDescription;
		this.accordianIcon = accordianIcon;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;	
	}
	
	public Accordian(Integer accordianSequence,String accordianName,String accordianDescription,
			String accordianIcon,Integer createBy,Date createTime,Integer updatedBy, Date updatedTime,
			Set<Children> childrens, AccessLevel accessLevel){
		this.accordianSequence = accordianSequence;
		this.accordianName = accordianName;
		this.accordianDescription = accordianDescription;
		this.accordianIcon = accordianIcon;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.childrens = childrens;
		this.accessLevel = accessLevel;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ACCORDIANID", unique = true, nullable = false)
	public Integer getAccordianId() {
		return this.accordianId;
	}
	public void setAccordianId(Integer accordianId) {
		this.accordianId = accordianId;
	}
	@Column(name = "ACCORDIANSEQUENCE", nullable = false, length = 11)
	public Integer getAccordianSequence() {
		return this.accordianSequence;
	}
	public void setAccordianSequence(Integer accordianSequence) {
		this.accordianSequence = accordianSequence;
	}
	@Column(name = "ACCORDIANNAME", nullable = false, length = 225)
	public String getAccordianName() {
		return this.accordianName;
	}
	public void setAccordianName(String accordianName) {
		this.accordianName = accordianName;
	}
	@Column(name = "ACCORDIANDESCRIPTION", nullable = true, length = 225)
	public String getAccordianDescription() {
		return this.accordianDescription;
	}
	public void setAccordianDescription(String accordianDescription) {
		this.accordianDescription = accordianDescription;
	}
	@Column(name = "ACCORDIANICON", nullable = true, length = 225)
	public String getAccordianIcon() {
		return this.accordianIcon;
	}
	public void setAccordianIcon(String accordianIcon) {
		this.accordianIcon = accordianIcon;
	}
	@Column(name = "CREATEBY", nullable = true, length = 11)
	public Integer getCreateBy() {
		return this.createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	@JsonSerialize(using=com.tsuyu.util.JsonDateSerializer.class)
	@Column(name = "CREATETIME", nullable = true, length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name = "UPDATEDBY", nullable = true, length = 11)
	public Integer getUpdatedBy() {
		return this.updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	@JsonSerialize(using=com.tsuyu.util.JsonDateSerializer.class)
	@Column(name = "UPDATEDTIME", nullable = true, length = 10)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	@OneToMany(orphanRemoval=true,fetch = FetchType.LAZY, mappedBy = "accordian")
	@org.hibernate.annotations.Cascade(value = {CascadeType.SAVE_UPDATE,CascadeType.DELETE})
	public Set<Children> getChildrens() {
		return this.childrens;
	}
	public void setChildrens(Set<Children> childrens) {
		this.childrens = childrens;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCESSLEVELID", nullable = false)
	public AccessLevel getAccessLevel() {
		return this.accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}
	@Transient
	public Integer getAccessLevelId() {
		return this.accessLevelId;
	}

	public void setAccessLevelId(Integer accessLevelId) {
		this.accessLevelId = accessLevelId;
	}
}
