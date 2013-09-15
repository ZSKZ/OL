package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Tag extends Model {
	public String tagName;
	public Tag(String tagName){
		this.tagName =tagName;
		save();
	}
}
