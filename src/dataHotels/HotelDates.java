package dataHotels;

import java.util.ArrayList;
import java.util.List;

public class HotelDates {
	private List<CheckinDatePrices> checkinDates;
	private String hotelName;
	
	
	public HotelDates(List<String> checkinDateNames, String hotelName) {
		super();
		
		this.checkinDates  = new ArrayList<>();
		
		for (String name : checkinDateNames) {
			CheckinDatePrices curr = new CheckinDatePrices();
			curr.setCheckinDate(name);
			this.checkinDates.add(curr);
		}

		this.hotelName = hotelName;
	}
	public List<CheckinDatePrices> getCheckinDates() {
		return checkinDates;
	}
	public void setCheckinDates(List<CheckinDatePrices> checkinDates) {
		this.checkinDates = checkinDates;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
		
}
