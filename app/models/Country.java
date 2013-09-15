package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class Country extends Model{
	public String name;
	public Country (String name){
		this.name = name;
		save();
	}
}
