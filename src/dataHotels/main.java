package dataHotels;

import java.util.List;

public class main {

	public static void main(String[] args) {

		List<Hotel> hotels = CSVHotelFileReader.readCsvFile("hotels1.csv");
		//	CSVHotelFileWriter.writeCsvFile("Hotels_data_Changed.csv", hotels);
		CSVHotelFileWriter.do31(hotels);

		System.exit(0);
	}
}
