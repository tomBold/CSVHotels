package dataHotels;

import java.util.List;

public class main {

	public static void main(String[] args) {
		List<Hotel> hotels = CSVHotelFileReader.readCsvFile("hotels1.csv");
		CSVHotelFileWriter.writeCsvFile("hotels_changed.csv", hotels);

		System.exit(0);
	}
}
