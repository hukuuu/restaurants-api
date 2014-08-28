package com.restaurants.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonIgnore
	@Column(name = "DELETED")
	private Boolean deleted;
	
	@JsonIgnore
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@JsonIgnore
	@Column(name = "LAST_MODIFIED")
	private Date lastModified;
	
	@PrePersist
	protected void prePersist() {
		this.creationDate = new Date();
		this.lastModified = new Date();
		this.deleted = false;
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.lastModified = new Date();
	}


	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	@Transient
	@JsonIgnore
	public boolean isNew() {
		if(getId()== null) return true;
		return getId() == 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + (int) (getId() ^ (getId() >>> 32));

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		
		if (this == obj) {
			return true;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		BaseEntity other = (BaseEntity) obj;

		if (getId() != other.getId()) {
			return false;
		}

		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
