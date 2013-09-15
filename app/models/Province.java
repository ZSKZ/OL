package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class Province extends Model {
	
	public String name;
	
	public Province (String name){
		this.name = name;
		save();
		
	}

}
