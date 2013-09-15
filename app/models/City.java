package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class City extends Model{
	public String name;
	
	public City (String name){
		this.name = name;
		save();
	}
}
