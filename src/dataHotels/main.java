package dataHotels;

import java.util.List;

public class main {

	public static void main(String[] args) {

		List<Hotel> hotels = CSVHotelFileReader.readCsvFile("hotels1.csv");
		//	CSVHotelFileWriter.writeCsvFile("Hotels_data_Changed.csv", hotels);
		List<Hotel> hotels31  = CSVHotelFileWriter.do31(hotels);
		CSVHotelFileWriter.writeCsvFile("Hotels_data_Changed3.csv", hotels31);
		

		System.exit(0);
	}
}
