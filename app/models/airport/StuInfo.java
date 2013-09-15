package models.airport;

import javax.persistence.Entity;

import play.data.validation.Required;
import play.db.jpa.Model;
import play.libs.Codec;

@Entity
public class StuInfo extends Model {
	public String username;
	@Required
	public String name;
	@Required
	public String gender;
	public String phone;
	@Required
	public String email;
	@Required
	public String major;
	@Required
	public String airport;
	@Required
	public String flight;
	@Required
	public int date;
	@Required
	public String time;
	@Required
	public long school;
	@Required
	public boolean isPickup;
	@Required
	public boolean isAccommodation;
	@Required
	public String luggage;
	public String remarks;
	public boolean needConfirmation;

	
}
