package de.iboticial.untisapi;

import java.util.HashMap;

public class WebUntis {

	HttpRequestManager http = new HttpRequestManager();

	public WebUntis() {

	}

	public String connect(String hostname, String username, String password, String schoolname) {

		HttpResponseWithEntity data;

		http.setCredentials(new Credentials(username, password, schoolname));
		http.setHostname(hostname);

		HashMap<String, String> paramsHashMap = new HashMap<>();
		paramsHashMap.put("user", username);
		paramsHashMap.put("password", password);
		paramsHashMap.put("client", "MY CLIENT");

		data = http.httpPOST("authenticate", paramsHashMap);

		return data.getResult();

	}

	public String getTeachers() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getTeachers", null); // TODO: Test

		return data.getResult();

	}

	public String getClasses() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getKlassen", null);

		return data.getResult();

	}

	public String getSubjects() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getSubjects", null);

		return data.getResult();

	}

	public String getRooms() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getRooms", null);

		return data.getResult();

	}

	public String getDepartments() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getDepartments", null);

		return data.getResult();

	}

	public String getHolidays() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getHolidays", null);

		return data.getResult();

	}

	public String getTimegridUnits() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getTimegridUnits", null);

		return data.getResult();

	}

	public String getStatusData() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getStatusData", null);

		return data.getResult();

	}

	public String getCurrentSchoolYear() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getCurrentSchoolyear", null);

		return data.getResult();

	}

	public String getSchoolYears() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getSchoolyears", null);

		return data.getResult();

	}

	public String getCustomData(String method, HashMap<String, String> paramsHashMap) {

		HttpResponseWithEntity data;

		data = http.httpPOST(method, paramsHashMap);

		return data.getResult();

	}

	public String getTimetable(TimeTableEntity timeTableEntity) {

		if (timeTableEntity != null) {

			HttpResponseWithEntity data;

			HashMap<String, String> paramsHashMap = new HashMap<>();
			paramsHashMap.put("id", String.valueOf(timeTableEntity.getId()));
			paramsHashMap.put("type", String.valueOf(timeTableEntity.getType()));

			data = http.httpPOST("getTimetable", paramsHashMap); // TODO: Test

			return data.getResult();

		} else {

			System.err.println("You cannot pass a null TimeTableEntity!");
			return null;

		}

	}

	public String getTimetable(TimeTableEntity timeTableEntity, int startDate, int endDate) {

		if (timeTableEntity != null) {

			HttpResponseWithEntity data;

			HashMap<String, String> paramsHashMap = new HashMap<>();
			paramsHashMap.put("id", String.valueOf(timeTableEntity.getId()));
			paramsHashMap.put("type", String.valueOf(timeTableEntity.getType()));
			paramsHashMap.put("startDate", String.valueOf(String.valueOf(startDate))); // TODO:Test
			paramsHashMap.put("endDate", String.valueOf(String.valueOf(endDate)));

			data = http.httpPOST("getTimetable", paramsHashMap);

			return data.getResult();

		} else {

			System.err.println("You cannot pass a null TimeTableEntity!");
			return null;

		}

	}

	// TODO: getTimetable

	// TODO: getWeeklyTimetable

	// TODO: getClassRegisterForPeriod

	public String getLastImportTime() {

		HttpResponseWithEntity data;

		data = http.httpPOST("getLastImportTime", null); // TODO: Doesn't
															// actually work

		return data.getResult();

	}

	// TODO: searchPerson

	// TODO: requestSubsitutions

	// TODO: classRegEvents

}
