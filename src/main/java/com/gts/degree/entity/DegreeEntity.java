package com.gts.degree.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GTS_DEGREES")
public class DegreeEntity {

	@Id
	@Column(nullable = false)
	private long gts_degree_id;
	private String gts_degree_name;
	private String gts_degree_description;
	private boolean gts_degree_status=true;
	
	public DegreeEntity() {
		super();
	}

	public DegreeEntity(long gts_degree_id, String gts_degree_name, String gts_degree_description,
			boolean gts_degree_status) {
		super();
		this.gts_degree_id = gts_degree_id;
		this.gts_degree_name = gts_degree_name;
		this.gts_degree_description = gts_degree_description;
		this.gts_degree_status = gts_degree_status;
	}

	public long getGts_degree_id() {
		return gts_degree_id;
	}

	public void setGts_degree_id(long gts_degree_id) {
		this.gts_degree_id = gts_degree_id;
	}

	public String getGts_degree_name() {
		return gts_degree_name;
	}

	public void setGts_degree_name(String gts_degree_name) {
		this.gts_degree_name = gts_degree_name;
	}

	public String getGts_degree_description() {
		return gts_degree_description;
	}

	public void setGts_degree_description(String gts_degree_description) {
		this.gts_degree_description = gts_degree_description;
	}

	public boolean isGts_degree_status() {
		return gts_degree_status;
	}

	public void setGts_degree_status(boolean gts_degree_status) {
		this.gts_degree_status = gts_degree_status;
	}

	@Override
	public String toString() {
		return "DegreeEntity [gts_degree_id=" + gts_degree_id + ", gts_degree_name=" + gts_degree_name
				+ ", gts_degree_description=" + gts_degree_description + ", gts_degree_status=" + gts_degree_status
				+ "]";
	}
	
	

	



}
