package dataHotels;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVHotelFileWriter {

	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	// CSV file header
	private static final String[] FILE_HEADER = { "Snapshot ID", "Snapshot Date", "Checkin Date", "Days",
			"Original Price", "Discount Price", "Discount Code", "Available Rooms", "Hotel Name", "Hotel Stars",
			"DayDiff", "WeekDay", "DiscountDiff", "DiscountPerc" };

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
				hotelDataRecord.add(String.valueOf(hotel.getDiscountCode()));
				hotelDataRecord.add(String.valueOf(hotel.getAvailableRooms()));
				hotelDataRecord.add(hotel.getHotelName());
				hotelDataRecord.add(String.valueOf(hotel.getHotelStars()));
				hotelDataRecord.add(String.valueOf(hotel.getDayDiff()));
				hotelDataRecord.add(hotel.getWeekDay());
				hotelDataRecord.add(String.valueOf(hotel.getDiscountDiff()));
				hotelDataRecord.add(String.valueOf(hotel.getDiscountPerc()));
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
}