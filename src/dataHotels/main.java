package dataHotels;

import java.util.List;

public class main {

	public static void main(String[] args) {

		List<Hotel> hotels = CSVHotelFileReader.readCsvFile("hotels1.csv");
		//	CSVHotelFileWriter.writeCsvFile("Hotels_data_Changed.csv", hotels);
		List<Hotel> hotels31  = CSVHotelFileWriter.do31(hotels);
		List<Hotel> hotels32 = CSVHotelFileWriter.getHotelsByFor32(hotels31);
		CSVHotelFileWriter.writeCsvFile("Hotels_data_Changed3.csv", hotels32);
		

		System.exit(0);
	}
}
