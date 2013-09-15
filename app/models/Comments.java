package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class Comments extends Model {
	public long quesid;
	public long neQuesid;
	public String comment;
	public String author;
	public int answerNum;
	public int praiseNum;
	public int focus;
	
	public Comments( String comment,String author, int answerNum,int praiseNum, int focus ,long quesid,long neQuesid){
		this.comment = comment;
		this.answerNum = answerNum;
		this.praiseNum = praiseNum;
		this.focus =focus;
		this.quesid =quesid;
		this.neQuesid =neQuesid;
		create();
	}
}
