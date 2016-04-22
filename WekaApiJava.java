 import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import weka.core.converters.ConverterUtils.DataSource;
import weka.*;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

public class WekaApiJava {

	public static void main (String[] args) throws Exception{
		SimpleKMeans kmeans = new SimpleKMeans();

		kmeans.setSeed(10);
		//important parameter to set: preserver order, number of cluster.
				kmeans.setPreserveInstancesOrder(true);
				kmeans.setNumClusters(3);
		
System.out.println("Hello Weka Java API");
	BufferedReader reader = new BufferedReader(
                new FileReader("C:/Users/SCS_USER/Desktop/sampout.arff"));
		
		
System.out.println("Check .....1");		
		Instances data = new Instances(reader);
	
	// K mean 
		
		kmeans.buildClusterer(data);
		// This array returns the cluster number (starting with 0) for each instance
				// The array has as many elements as the number of instances
				int[] assignments = kmeans.getAssignments();
		 
				int i=0;
				for(int clusterNum : assignments) {
				    System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
				    i++;
	System.out.printf(" .. K EMAN CLUSTERING DONE ..." );
	
	//reader.close();
// setting class attribute
		
		data.setClassIndex(data.numAttributes() - 1); 
// Classifier 

String[] options = new String[1];
options[0] = "-U";            // unpruned tree
J48 tree = new J48();         // new instance of tree
tree.setOptions(options);     // set the options
tree.buildClassifier(data);   // build classifier
System.out.println(tree);


NaiveBayes nB = new NaiveBayes();
nB.buildClassifier(data);
System.out.println("Check .....3");	
Evaluation eval = new Evaluation(data);
eval.crossValidateModel(nB, data, 3, new Random(1));

System.out.println(eval.toSummaryString("\n Results\n  ...........\n",true));
System.out.println(eval.fMeasure(1)+ " "+ eval.precision(1) + eval.recall(1));

	}
}
	
}
