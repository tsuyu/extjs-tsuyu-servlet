package com.tsuyu.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "children", catalog = "tsuyu",uniqueConstraints = {
		@UniqueConstraint(columnNames = "CHILDRENNAME"),
		@UniqueConstraint(columnNames = "CHILDRENICON"),
		@UniqueConstraint(columnNames = "CHILDRENSEQUENCE"),
		@UniqueConstraint(columnNames = "CHILDRENMAPPER")})
@org.hibernate.annotations.Entity(
		dynamicInsert = true,dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Children implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2433937431230057529L;
	private Integer childrenId;
	private Accordian accordian;
	private transient Integer accordianId;
	private transient String accordianName;
	private String childrenName;
	private Integer childrenSequence;
	private String childrenDescription;
	private String childrenIcon;
	private String childrenMapper;
	private Integer createBy;
	private Date createTime;
	private Integer updatedBy;
	private Date updatedTime;
	private Set<Leaf> leafs = new HashSet<Leaf>(0);
	
	public Children(){}
	
	public Children(String childrenName,Integer childrenSequence,String childrenDescription,
			String childrenIcon,String childrenMapper,Integer createBy,Date createTime,Integer updatedBy, Date updatedTime){
		this.childrenName = childrenName;
		this.childrenSequence = childrenSequence;
		this.childrenDescription = childrenDescription;
		this.childrenIcon = childrenIcon;
		this.childrenMapper = childrenMapper;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;	
	}
	
	public Children(String childrenName,Integer childrenSequence,String childrenDescription,
			String childrenIcon,String childrenMapper,Integer createBy,Date createTime,Integer updatedBy, Date updatedTime,
			Accordian accordian,Set<Leaf> leafs){
		this.childrenName = childrenName;
		this.childrenSequence = childrenSequence;
		this.childrenDescription = childrenDescription;
		this.childrenIcon = childrenIcon;
		this.childrenMapper = childrenMapper;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;
		this.accordian = accordian;
		this.leafs = leafs;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CHILDRENID", unique = true, nullable = false)
	public Integer getChildrenId() {
		return this.childrenId;
	}
	public void setChildrenId(Integer childrenId) {
		this.childrenId = childrenId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCORDIANID", nullable = false)
	public Accordian getAccordian() {
		return this.accordian;
	}
	public void setAccordian(Accordian accordian) {
		this.accordian = accordian;
	}
	@Column(name = "CHILDRENNAME", nullable = false, length = 225)
	public String getChildrenName() {
		return this.childrenName;
	}
	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}
	@Column(name = "CHILDRENSEQUENCE", nullable = false, length = 11)
	public Integer getChildrenSequence() {
		return this.childrenSequence;
	}
	public void setChildrenSequence(Integer childrenSequence) {
		this.childrenSequence = childrenSequence;
	}
	@Column(name = "CHILDRENDESCRIPTION", nullable = true, length = 225)
	public String getChildrenDescription() {
		return this.childrenDescription;
	}
	public void setChildrenDescription(String childrenDescription) {
		this.childrenDescription = childrenDescription;
	}
	@Column(name = "CHILDRENICON", nullable = true, length = 225)
	public String getChildrenIcon() {
		return this.childrenIcon;
	}
	public void setChildrenIcon(String childrenIcon) {
		this.childrenIcon = childrenIcon;
	}
	@Column(name = "CHILDRENMAPPER", nullable = false, length = 225)
	public String getChildrenMapper() {
		return this.childrenMapper;
	}
	public void setChildrenMapper(String childrenMapper) {
		this.childrenMapper = childrenMapper;
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
	@OneToMany(orphanRemoval=true,fetch = FetchType.LAZY, mappedBy = "children")
	@org.hibernate.annotations.Cascade(value = {CascadeType.SAVE_UPDATE,CascadeType.DELETE})
	public Set<Leaf> getLeafs() {
		return this.leafs;
	}
	public void setLeafs(Set<Leaf> leafs) {
		this.leafs = leafs;
	}
	@Transient
	public String getAccordianName() {
		return this.accordianName;
	}

	public void setAccordianName(String accordianName) {
		this.accordianName = accordianName;
	}
	@Transient
	public Integer getAccordianId() {
		return this.accordianId;
	}

	public void setAccordianId(Integer accordianId) {
		this.accordianId = accordianId;
	}
}
