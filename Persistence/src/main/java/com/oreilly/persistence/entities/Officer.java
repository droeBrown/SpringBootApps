package com.oreilly.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "officers")
public class Officer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private Rank rank;

	@Column(name = "first_name", nullable = false)
	private String first;

	@Column(name = "last_name", nullable = false)
	private String last;

	public Officer() {
	}

	public Officer(Integer id, Rank rank, String first, String last) {
		this.id = id;
		this.rank = rank;
		this.first = first;
		this.last = last;
	}

	public Officer(Rank rank, String first, String last) {
		this.rank = rank;
		this.first = first;
		this.last = last;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	@Override
	public String toString() {
		return "officers{" + "id=" + id + ", rank=" + rank + ", first='" + first + '\'' + ", last='" + last + '\''
				+ '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Officer))
			return false;

		Officer office = (Officer) o;

		if (!id.equals(office.id))
			return false;
		if (rank != office.rank)
			return false;
		if (first != null ? !first.equals(office.first) : office.first != null)
			return false;
		return last.equals(office.last);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + rank.hashCode();
		result = 31 * result + (first != null ? first.hashCode() : 0);
		result = 31 * result + last.hashCode();
		return result;
	}
}
