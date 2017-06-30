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
				// hotelDataRecord.add("'" + hotel.getHotelName() + "'");
				hotelDataRecord.add(hotel.getHotelName());
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

	public static List<Hotel> do31(List<Hotel> hotels) {
		List<Hotel> hotels31 = getHotelsFor31(hotels);

		return hotels31;
	}

	public static List<Hotel> do32(List<Hotel> hotels) {
		List<Hotel> hotels32 = getHotelsFor32(hotels);
		return hotels32;
	}
	
	public static void do33(HashMap<String, List<Hotel>> checkinDateToHotels)
	{
		
	}

	public static List<Hotel> getHotelsFor31(List<Hotel> hotels) {
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

		for (Map.Entry<String, List<Hotel>> entry : list.entrySet()) {
			String key = entry.getKey();
			List<Hotel> value = entry.getValue();

			if (i < max) {
				i++;

				result.addAll(value);
			} else {
				return result;
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
				if (o1.getValue().size() > o2.getValue().size())
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

	public static List<Hotel> getHotelsFor32(List<Hotel> hotels) {
		HashMap<String, List<Hotel>> checkinToRecoredMap = new HashMap<String, List<Hotel>>();

		for (Hotel hotel : hotels) {
			if (!checkinToRecoredMap.containsKey(hotel.getCheckinDateStr())) {
				checkinToRecoredMap.put(hotel.getCheckinDateStr(), new ArrayList<>());
			}

			checkinToRecoredMap.get(hotel.getCheckinDateStr()).add(hotel);
		}

		List<Hotel> result = new ArrayList<>();

		Map<String, List<Hotel>> list = sortByListSizeValue(checkinToRecoredMap);

		int i = 0;
		int max = 40;

		HashMap<String, List<Hotel>> mapFor33 = new HashMap<>();
		
		
		for (Map.Entry<String, List<Hotel>> entry : list.entrySet()) {
			String key = entry.getKey();
			List<Hotel> value = entry.getValue();

			if (i < max) {
				i++;

				result.addAll(value);
				mapFor33.put(key, value);
				
			} else {
				return result;
			}
		}
		
		do33(mapFor33);

		return null;

	}
}