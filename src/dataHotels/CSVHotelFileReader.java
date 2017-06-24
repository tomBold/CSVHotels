package dataHotels;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHotelFileReader {

	// CSV file header
	private static final String[] FILE_HEADER_MAPPING = { "Snapshot ID", "Snapshot Date", "Checkin Date", "Days",
			"Original Price", "Discount Price", "Discount Code", "Available Rooms", "Hotel Name", "Hotel Stars" };

	public static List<Hotel> readCsvFile(String fileName) {

		FileReader fileReader = null;
		CSVParser csvFileParser = null;

		// Create the CSVFormat object with the header mapping
		CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

		try {

			// Create a new list of hotels to be filled by CSV file data
			List<Hotel> hotels = new ArrayList();

			// initialize FileReader object
			fileReader = new FileReader(fileName);

			// initialize CSVParser object
			csvFileParser = new CSVParser(fileReader, csvFileFormat);

			// Get a list of CSV file records
			List csvRecords = csvFileParser.getRecords();

			// Read the CSV file records starting from the second record to skip
			// the header
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = (CSVRecord) csvRecords.get(i);
				// Create a new hotel object and fill his data
				Hotel hotel = new Hotel(Integer.parseInt(record.get(FILE_HEADER_MAPPING[0])),
						record.get(FILE_HEADER_MAPPING[1]), record.get(FILE_HEADER_MAPPING[2]),
						Integer.parseInt(record.get(FILE_HEADER_MAPPING[3])),
						Integer.parseInt(record.get(FILE_HEADER_MAPPING[4])),
						Integer.parseInt(record.get(FILE_HEADER_MAPPING[5])),
						Integer.parseInt(record.get(FILE_HEADER_MAPPING[6])),
						Integer.parseInt(record.get(FILE_HEADER_MAPPING[7])), 
						record.get(FILE_HEADER_MAPPING[8]),
						Integer.parseInt(record.get(FILE_HEADER_MAPPING[9])));
				hotels.add(hotel);
			}

			return hotels;
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader/csvFileParser !!!");
				e.printStackTrace();
			}
		}
		return null;

	}

}
