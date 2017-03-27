import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Stijn on 25-Mar-17.
 */
public class myReader {

    myReader()
    {
    }

    public ArrayList<String> ReadText(String filename)
    {
        ArrayList<String> result = new ArrayList<String>();
        FileReader fr;

        // open the file "Filename" which is a ascii-text file with headers
        try {
            fr = new FileReader(filename);
        }
        catch (Exception ex) {
            return result;
        }
        BufferedReader br = new BufferedReader(fr);
        String s;

        try {
            while((s = br.readLine()) != null) {
                result.add(s.toLowerCase());
            }

            /* Sorting of result using Collections.sort*/
            Collections.sort(result);
            fr.close();
        }
        catch (Exception ex) {
            return result;
        }

        return result;
    }
}
