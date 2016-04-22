import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
 
// Converts the .csv file into Arff format 

import java.io.File;

public class CSV2Arff {

	public static void main(String[] args) throws Exception {
	    if (args.length != 2) {
	      System.out.println("\nUsage: CSV2Arff <input.csv> <output.arff>\n");
	      //System.exit(1);
	    }
	 
	    // load CSV
	    CSVLoader loader = new CSVLoader();
	    loader.setSource(new File("C:/Users/SCS_USER/Desktop/test.csv"));
	    Instances data = loader.getDataSet();
	    System.out.println("Loaded CSV ");
	    // save ARFF
	    ArffSaver saver = new ArffSaver();
	    saver.setInstances(data);
	    saver.setFile(new File("C:/Users/SCS_USER/Desktop/test.arff"));
	    //saver.setDestination(new File(args[1]));
	    saver.writeBatch();
	    System.out.println("Created Arff file ");
	  }
	
	// Build Classifier & Naive base
	
	
	
}
