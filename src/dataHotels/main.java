package dataHotels;

import java.util.List;
//import weka.core.Instances;
//import weka.core.converters.ArffSaver;
//import weka.core.converters.CSVLoader;

import java.io.File;

public class main {

	public static void main(String[] args) {

		List<Hotel> hotels = CSVHotelFileReader.readCsvFile("hotels1.csv");
		// CSVHotelFileWriter.writeCsvFile("Hotels_data_Changed.csv", hotels);
		CSVHotelFileWriter.do31("result.csv", hotels);
		//List<Hotel> hotels32 = CSVHotelFileWriter.getHotelsByFor32(hotels31);
		//CSVHotelFileWriter.writeCsvFile("Hotels_data_Changed3.csv", hotels31);

		System.exit(0);
	}

//	public static void CSV2Arff() throws Exception {
//		// load CSV
//		CSVLoader loader = new CSVLoader();
//		loader.setSource(new File(args[0]));
//		Instances data = loader.getDataSet();
//
//		// save ARFF
//		ArffSaver saver = new ArffSaver();
//		saver.setInstances(data);
//		saver.setFile(new File(args[1]));
//		saver.setDestination(new File(args[1]));
//		saver.writeBatch();
//	}

}
