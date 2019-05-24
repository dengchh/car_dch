package car.dch.entity;

import java.sql.Timestamp;

public class Record {
	private int recordID;
	private int cID;
	private int uID;
	private Timestamp borrDate;
	private Timestamp returnDate;
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public int getcID() {
		return cID;
	}
	public void setcID(int cID) {
		this.cID = cID;
	}
	public int getuID() {
		return uID;
	}
	public void setuID(int uID) {
		this.uID = uID;
	}
	public Timestamp getBorrDate() {
		return borrDate;
	}
	public void setBorrDate(Timestamp borrDate) {
		this.borrDate = borrDate;
	}
	public Timestamp getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

}
