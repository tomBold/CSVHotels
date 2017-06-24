package dataHotels;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

public class Hotel {
	public Hotel(int snapshotID, String snapshotDate, String checkinDate, int days, int originalPrice,
			int discountPrice, int discountCode, int availableRooms, String hotelName, int hotelStars) {
		super();
		SnapshotID = snapshotID;
		SnapshotDateStr = snapshotDate;
		CheckinDateStr = checkinDate;
		Days = days;
		OriginalPrice = originalPrice;
		DiscountPrice = discountPrice;
		DiscountCode = discountCode;
		AvailableRooms = availableRooms;
		HotelName = hotelName;
		HotelStars = hotelStars;

		SimpleDateFormat fromUser = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");

		try {

			String reformattedSnapshotDateStr = myFormat.format(fromUser.parse(SnapshotDateStr));
			SnapshotDate = new Date(reformattedSnapshotDateStr);

			String reformattedCheckinDateStr = myFormat.format(fromUser.parse(CheckinDateStr));
			CheckinDate = new Date(reformattedCheckinDateStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		DayDiff = org.joda.time.Days.daysBetween(new DateTime(getSnapshotDate()), new DateTime(getCheckinDate()))
				.getDays();
		
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("E", Locale.US);
		WeekDay = simpleDateformat.format(CheckinDate);

		DiscountDiff = getOriginalPrice() - getDiscountPrice();

		double x = (double) getDiscountDiff() / (double) getOriginalPrice();
		DiscountPerc = x * 100;

	}

	private int SnapshotID;
	private Date SnapshotDate;
	private Date CheckinDate;
	private int Days;
	private int OriginalPrice;
	private int DiscountPrice;
	private int DiscountCode;
	private int AvailableRooms;
	private String HotelName;
	private int HotelStars;
	private String SnapshotDateStr;
	private String CheckinDateStr;
	private int DayDiff;
	private String WeekDay;
	private int DiscountDiff;
	private double DiscountPerc;

	public int getSnapshotID() {
		return SnapshotID;
	}

	public void setSnapshotID(int snapshotID) {
		SnapshotID = snapshotID;
	}

	public Date getSnapshotDate() {
		return SnapshotDate;
	}

	public void setSnapshotDate(Date snapshotDate) {
		SnapshotDate = snapshotDate;
	}

	public Date getCheckinDate() {
		return CheckinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		CheckinDate = checkinDate;
	}

	public int getDays() {
		return Days;
	}

	public void setDays(int days) {
		Days = days;
	}

	public int getOriginalPrice() {
		return OriginalPrice;
	}

	public void setOriginalPrice(int originalPrice) {
		OriginalPrice = originalPrice;
	}

	public int getDiscountPrice() {
		return DiscountPrice;
	}

	public void setDiscountPrice(int discountPrice) {
		DiscountPrice = discountPrice;
	}

	public int getDiscountCode() {
		return DiscountCode;
	}

	public void setDiscountCode(int discountCode) {
		DiscountCode = discountCode;
	}

	public int getAvailableRooms() {
		return AvailableRooms;
	}

	public void setAvailableRooms(int availableRooms) {
		AvailableRooms = availableRooms;
	}

	public String getHotelName() {
		return HotelName;
	}

	public void setHotelName(String hotelName) {
		HotelName = hotelName;
	}

	public int getHotelStars() {
		return HotelStars;
	}

	public void setHotelStars(int hotelStars) {
		HotelStars = hotelStars;
	}

	public String getSnapshotDateStr() {
		return SnapshotDateStr;
	}

	public void setSnapshotDateStr(String snapshotDateStr) {
		SnapshotDateStr = snapshotDateStr;
	}

	public String getCheckinDateStr() {
		return CheckinDateStr;
	}

	public void setCheckinDateStr(String checkinDateStr) {
		CheckinDateStr = checkinDateStr;
	}

	public int getDayDiff() {
		return DayDiff;
	}

	public void setDayDiff(int dayDiff) {
		DayDiff = dayDiff;
	}

	public String getWeekDay() {
		return WeekDay;
	}

	public void setWeekDay(String weekDay) {
		WeekDay = weekDay;
	}

	public int getDiscountDiff() {
		return DiscountDiff;
	}

	public void setDiscountDiff(int discountDiff) {
		DiscountDiff = discountDiff;
	}

	public double getDiscountPerc() {
		return DiscountPerc;
	}

	public void setDiscountPerc(double discountPerc) {
		DiscountPerc = discountPerc;
	}

}
