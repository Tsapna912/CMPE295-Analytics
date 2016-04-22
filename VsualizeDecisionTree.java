import weka.classifiers.trees.J48;
import weka.core.*;

import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

 
import java.awt.*;
import java.io.*;

 
/**
 * Runs a clusterer on a dataset and visualizes the cluster assignments, 
 * like with right-click menu in Explorer.
 * <p/>
 * Takes two arguments:
 * <ol>
 *   <li>-t dataset</li>
 *   <li>-W cluster algorithm with options</li>
 * </ol>
 *
 * Note: code should work with Weka 3.6.0 and 3.5.8.
 * 
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class VisualizeWeka {
  public static void main(String[] args) throws Exception {

	  BufferedReader reader = new BufferedReader(
	            new FileReader("C:/Users/SCS_USER/Desktop/test.arff"));
	  J48 cls = new J48();
		Instances data = new Instances(reader);
		reader.close();
		
		data.setClassIndex(data.numAttributes() - 1); 
	  cls.buildClassifier(data);
	  
	     // display classifier
	     final javax.swing.JFrame jf = 
	       new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
	     jf.setSize(500,400);
	     jf.getContentPane().setLayout(new BorderLayout());
	     TreeVisualizer tv = new TreeVisualizer(null,
	         cls.graph(),
	         new PlaceNode2());
	     jf.getContentPane().add(tv, BorderLayout.CENTER);
	     jf.addWindowListener(new java.awt.event.WindowAdapter() {
	       public void windowClosing(java.awt.event.WindowEvent e) {
	         jf.dispose();
	       }
	     });

	 	String[] options = new String[1];
	 	options[0] = "-U";            // unpruned tree
	 	J48 tree = new J48();         // new instance of tree
	 	tree.setOptions(options);     // set the options
	 	tree.buildClassifier(data);   // build classifier
	 	System.out.println(tree);
	     jf.setVisible(true);
	     tv.fitToScreen();
  }
}
