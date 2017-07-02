package dataHotels;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVHotelFileWriter {

	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String[] FILE_HEADER = { "Snapshot ID", "Snapshot Date", "Checkin Date", "Days",
			"Original Price", "Discount Price", "Available Rooms", "Hotel Name", "Hotel Stars", "DayDiff", "WeekDay",
			"DiscountDiff", "DiscountPerc", "Discount Code" };

	public static void writeCsvFile(String fileName, List<Hotel> hotels) {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {

			// initialize FileWriter object
			fileWriter = new FileWriter(fileName);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			// Create CSV file header
			csvFilePrinter.printRecord(FILE_HEADER);

			// Write a new hotel object list to the CSV file
			for (Hotel hotel : hotels) {
				List hotelDataRecord = new ArrayList();
				hotelDataRecord.add(String.valueOf(hotel.getSnapshotID()));
				hotelDataRecord.add(String.valueOf(hotel.getSnapshotDateStr()));
				hotelDataRecord.add(String.valueOf(hotel.getCheckinDateStr()));
				hotelDataRecord.add(String.valueOf(hotel.getDays()));
				hotelDataRecord.add(String.valueOf(hotel.getOriginalPrice()));
				hotelDataRecord.add(String.valueOf(hotel.getDiscountPrice()));
				hotelDataRecord.add(String.valueOf(hotel.getAvailableRooms()));
				hotelDataRecord.add("'" + hotel.getHotelName() + "'");
				// hotelDataRecord.add(hotel.getHotelName());
				hotelDataRecord.add(String.valueOf(hotel.getHotelStars()));
				hotelDataRecord.add(String.valueOf(hotel.getDayDiff()));
				hotelDataRecord.add(hotel.getWeekDay());
				hotelDataRecord.add(String.valueOf(hotel.getDiscountDiff()));
				hotelDataRecord.add(String.valueOf(hotel.getDiscountPerc()));
				hotelDataRecord.add(String.valueOf(hotel.getDiscountCode()));
				csvFilePrinter.printRecord(hotelDataRecord);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}
	

	public static void writeFinish(String fileName, List<HotelResult> hotels) {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);

		try {

			// initialize FileWriter object
			fileWriter = new FileWriter(fileName);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			
			List hotelDataHeader = new ArrayList<>();
			hotelDataHeader.add("Hotel Name");
			
			for (int i = 1; i <= 160; i++)
			{
				hotelDataHeader.add("x" + i);
			}
	//	hotelDataHeader.add("items");
			
			csvFilePrinter.printRecord(hotelDataHeader);

			// Create CSV file header
		// csvFilePrinter.printRecord(FILE_HEADER);

			// Write a new hotel object list to the CSV file
			for (HotelResult hotel : hotels) {
				List hotelDataRecord = new ArrayList();
				hotelDataRecord.add(hotel.getName());
				
				String curr = "";
				int i = 0;
				
				for (Integer currPrice : hotel.getNormalPrices())
				{
//					curr = curr + String.valueOf(currPrice);
//					if (i < 159)
//					{
//						curr = curr + ",";
//					}
//					i++;
					hotelDataRecord.add(String.valueOf(currPrice));
				}
				

				csvFilePrinter.printRecord(hotelDataRecord);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}

	public static void do31(String fileName, List<Hotel> hotels) {
		List<HotelResult> result = getHotelsFor31(hotels);
		
		writeFinish(fileName, result);
	}


	public static List<HotelDates> getHotelDates(Map<String, List<Hotel>> nameToRecored, List<String> dates) {
		List<HotelDates> result = new ArrayList<>();

		for (Map.Entry<String, List<Hotel>> entry : nameToRecored.entrySet()) {
			String key = entry.getKey();

			List<Hotel> value = entry.getValue();
			HotelDates hotelDate = new HotelDates(dates, key);

			for (CheckinDatePrices datePrice : hotelDate.getCheckinDates()) {
				Hotel code1 = getHotelForDateAndCode(datePrice.getCheckinDate(), 1, value);
				if (code1 != null) {
					datePrice.setPrice1(code1.getDiscountPrice());
				}

				Hotel code2 = getHotelForDateAndCode(datePrice.getCheckinDate(), 2, value);
				if (code2 != null) {
					datePrice.setPrice2(code2.getDiscountPrice());
				}

				Hotel code3 = getHotelForDateAndCode(datePrice.getCheckinDate(), 3, value);
				if (code3 != null) {
					datePrice.setPrice3(code3.getDiscountPrice());
				}

				Hotel code4 = getHotelForDateAndCode(datePrice.getCheckinDate(), 4, value);
				if (code4 != null) {
					datePrice.setPrice4(code4.getDiscountPrice());
				}
			}

			result.add(hotelDate);
		}

		return result;
	}

	public static Hotel getHotelForDateAndCode(String date, int code, List<Hotel> hotelData) {
		Hotel minPrice = null;

		for (Hotel hotel : hotelData) {
			if (hotel.getCheckinDateStr().equals(date) && hotel.getDiscountCode() == code) {
				if (minPrice == null) {
					minPrice = hotel;
				} else if (minPrice.getDiscountPrice() < hotel.getDiscountPrice()) {
					minPrice = hotel;
				}
			}
		}

		return minPrice;
	}

	public static List<HotelResult> getHotelsFor31(List<Hotel> hotels) {
		HashMap<String, List<Hotel>> hotelNameToRecoredMap = new HashMap<String, List<Hotel>>();

		for (Hotel hotel : hotels) {
			if (!hotelNameToRecoredMap.containsKey(hotel.getHotelName())) {
				hotelNameToRecoredMap.put(hotel.getHotelName(), new ArrayList<>());
			}

			hotelNameToRecoredMap.get(hotel.getHotelName()).add(hotel);
		}

		List<Hotel> result = new ArrayList<>();

		Map<String, List<Hotel>> list = sortByListSizeValue(hotelNameToRecoredMap);

		int i = 0;
		int max = 150;

		Map<String, List<Hotel>> resultMap = new HashMap<String, List<Hotel>>();

		for (Map.Entry<String, List<Hotel>> entry : list.entrySet()) {
			String key = entry.getKey();

			// List<Hotel> value = getHotelFor32(entry.getValue());
			List<Hotel> value = entry.getValue();

			if (i < max) {
				i++;

				resultMap.put(key, value);
				result.addAll(value);

			} else {

				continue;
			}
		}

		List<String> dates = getHotelsByFor32(result);

		List<HotelDates> hotelDates = getHotelDates(resultMap, dates);
		
		 List<HotelResult> finish = do34(hotelDates);

		return finish;
	}

	public static List<HotelResult> do34(List<HotelDates> data) {
		List<HotelResult> result = new ArrayList<>();
		
		for (HotelDates hotelDates : data) {
			HotelResult cuurr = new HotelResult(hotelDates.getHotelName(), hotelDates);
			result.add(cuurr);
		}

		return result;
	}

	public static List<String> getHotelsByFor32(List<Hotel> hotels) {
		HashMap<String, List<Hotel>> hotelCheckInToRecoredMap = new HashMap<String, List<Hotel>>();

		for (Hotel hotel : hotels) {
			if (!hotelCheckInToRecoredMap.containsKey(hotel.getCheckinDateStr())) {
				hotelCheckInToRecoredMap.put(hotel.getCheckinDateStr(), new ArrayList<>());
			}

			hotelCheckInToRecoredMap.get(hotel.getCheckinDateStr()).add(hotel);
		}

		List<Hotel> result = new ArrayList<>();

		Map<String, List<Hotel>> list = sortByListSizeValue(hotelCheckInToRecoredMap);
		List<String> checkInDates = new ArrayList<>();

		int i = 0;
		int max = 40;

		for (Map.Entry<String, List<Hotel>> entry : list.entrySet()) {
			String key = entry.getKey();

			List<Hotel> value = entry.getValue();

			if (i < max) {
				i++;
				checkInDates.add(key);

				result.addAll(value);
			} else {

				// if you need just the dates
				return checkInDates;

				// return result;
			}
		}

		return null;
	}

	private static Map<String, List<Hotel>> sortByListSizeValue(Map<String, List<Hotel>> unsortMap) {

		// 1. Convert Map to List of Map
		List<Map.Entry<String, List<Hotel>>> list = new LinkedList<Map.Entry<String, List<Hotel>>>(
				unsortMap.entrySet());

		// 2. Sort list with Collections.sort(), provide a custom Comparator
		// Try switch the o1 o2 position for a different order
		Collections.sort(list, new Comparator<Map.Entry<String, List<Hotel>>>() {
			public int compare(Map.Entry<String, List<Hotel>> o1, Map.Entry<String, List<Hotel>> o2) {
				if (o1.getValue().size() >= o2.getValue().size())
					return -1;
				else
					return 1;
			}
		});

		// 3. Loop the sorted list and put it into a new insertion order Map
		// LinkedHashMap
		Map<String, List<Hotel>> sortedMap = new LinkedHashMap<String, List<Hotel>>();
		for (Map.Entry<String, List<Hotel>> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		/*
		 * //classic iterator example for (Iterator<Map.Entry<String, Integer>>
		 * it = list.iterator(); it.hasNext(); ) { Map.Entry<String, Integer>
		 * entry = it.next(); sortedMap.put(entry.getKey(), entry.getValue()); }
		 */

		return sortedMap;
	}

	/*
	 * 
	 * public static List<Hotel> getHotelFor32(List<Hotel> hotels) { List<Hotel>
	 * result = new ArrayList<>();
	 * 
	 * HashMap<String, List<Hotel>> checkinDateToRecoredMap = new
	 * HashMap<String, List<Hotel>>();
	 * 
	 * for (Hotel hotel : hotels) { if
	 * (!checkinDateToRecoredMap.containsKey(hotel.getCheckinDateStr())) {
	 * checkinDateToRecoredMap.put(hotel.getCheckinDateStr(), new
	 * ArrayList<>()); }
	 * 
	 * checkinDateToRecoredMap.get(hotel.getCheckinDateStr()).add(hotel); }
	 * 
	 * 
	 * Map<String, List<Hotel>> list =
	 * sortByListSizeValue(checkinDateToRecoredMap);
	 * 
	 * int i = 0; int max = 40;
	 * 
	 * for (Map.Entry<String, List<Hotel>> entry : list.entrySet()) { String key
	 * = entry.getKey();
	 * 
	 * 
	 * // TODO:/ Get the value by 3.3 List<Hotel> value = entry.getValue();
	 * 
	 * if (i < max && i <list.size()) { i++;
	 * 
	 * result.addAll(value); } else { return result; } }
	 * 
	 * return result; }
	 */

}