package de.iboticial.untisapi;

public class TimeTableEntity {

	private int id;
	private int type;

	public TimeTableEntity(int id, int type) {
		if (id > 0) {
			if (type > 0 && type <= 5) {
				this.setType(type);
				this.setId(id);
			} else {
				System.err.println("Type has to be a number between 0 and 6!");
			}
		} else {
			System.err.println("Id has to be a number greater than 0!");
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
