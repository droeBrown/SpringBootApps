package com.youtube.course;

public class Costumers {
	private int cid;
	private int cname;
	private int cemail;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getCname() {
		return cname;
	}

	public void setCname(int cname) {
		this.cname = cname;
	}

	public int getCemail() {
		return cemail;
	}

	public void setCemail(int cemail) {
		this.cemail = cemail;
	}

	@Override
	public String toString() {
		return "Costumers [cid=" + cid + ", cname=" + cname + ", cemail=" + cemail + "]";
	}
}
