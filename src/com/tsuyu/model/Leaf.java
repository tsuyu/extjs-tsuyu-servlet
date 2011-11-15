package com.tsuyu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;


import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "leaf", catalog = "tsuyu", uniqueConstraints = {
		@UniqueConstraint(columnNames = "LEAFNAME"),
		@UniqueConstraint(columnNames = "LEAFICON"),
		@UniqueConstraint(columnNames = "LEAFSEQUENCE")})
@org.hibernate.annotations.Entity(
		dynamicInsert = true,dynamicUpdate = true)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Leaf implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1199992696450519178L;
	private Integer leafId;
	private Children children;
	private transient Integer childrenId;
	private transient String childrenName;
	private Integer leafSequence;
	private String leafName;
	private String leafDescription;
	private String leafMapper;
	private String leafIcon;
	private Integer createBy;
	private Date createTime;
	private Integer updatedBy;
	private Date updatedTime;
	
	public Leaf(){}
	
	public Leaf(Integer leafSequence,String leafName,String leafDescription,String leafMapper,
			String leafIcon,Integer createBy,Date createTime,Integer updatedBy, Date updatedTime){
		this.leafSequence = leafSequence;
		this.leafName = leafName;
		this.leafDescription = leafDescription;
		this.leafMapper = leafMapper;
		this.leafIcon = leafIcon;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updatedBy = updatedBy;
		this.updatedTime = updatedTime;	
	}
	
	public Leaf(Children children){
		this.children = children;
	}
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LEAFID", unique = true, nullable = false)
	public Integer getLeafId() {
		return this.leafId;
	}
	public void setLeafId(Integer leafId) {
		this.leafId = leafId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CHILDRENID", nullable = false)
	public Children getChildren() {
		return this.children;
	}
	public void setChildren(Children children) {
		this.children = children;
	}
	@Column(name = "LEAFSEQUENCE", nullable = false, length = 11)
	public Integer getLeafSequence() {
		return this.leafSequence;
	}
	public void setLeafSequence(Integer leafSequence) {
		this.leafSequence = leafSequence;
	}
	@Column(name = "LEAFNAME", nullable = false, length = 225)
	public String getLeafName() {
		return this.leafName;
	}
	public void setLeafName(String leafName) {
		this.leafName = leafName;
	}
	@Column(name = "LEAFDESCRIPTION", nullable = true, length = 225)
	public String getLeafDescription() {
		return this.leafDescription;
	}
	public void setLeafDescription(String leafDescription) {
		this.leafDescription = leafDescription;
	}
	@Column(name = "LEAFMAPPER", nullable = false, length = 225)
	public String getLeafMapper() {
		return this.leafMapper;
	}
	public void setLeafMapper(String leafMapper) {
		this.leafMapper = leafMapper;
	}
	@Column(name = "LEAFICON", nullable = true, length = 225)
	public String getLeafIcon() {
		return this.leafIcon;
	}
	public void setLeafIcon(String leafIcon) {
		this.leafIcon = leafIcon;
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
	@Transient
	public String getChildrenName() {
		return this.childrenName;
	}

	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}
	@Transient
	public Integer getChildrenId() {
		return this.childrenId;
	}

	public void setChildrenId(Integer childrenId) {
		this.childrenId = childrenId;
	}
}
