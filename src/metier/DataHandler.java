package metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@SuppressWarnings("unused")
public class DataHandler {

	public DataHandler() {

	}

	private void readfile() {
	
		
		 // Data file path is passed as parameter
        File file = new File("src\\metier\\data.txt");

        try {   BufferedReader br
            = new BufferedReader(new FileReader(file));
        String st;
     
			while ((st = br.readLine()) != null)
			    System.out.println(st);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		new DataHandler().readfile();

	}
	


}
