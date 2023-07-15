package com.infotel.eshop.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity @Table(name="image_data")
public class ImageData {
	@Id
	private int id;
	private String target;
	
	@Column(name="target_id")
	private String targetId;
	
	@Lob
	private byte[] content;
	
	
	@Override
	public String toString() {
		return "ImageData [id=" + id + ", target=" + target + ", targetId=" + targetId + ", content="
				+ Arrays.toString(content) + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}


	public String getTargetId() {
		return targetId;
	}


	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}


	public byte[] getContent() {
		return content;
	}


	public void setContent(byte[] content) {
		this.content = content;
	}
	
	
}
