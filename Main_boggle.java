import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Stijn on 25-Mar-17.
 */
public class Main {

    public static void main(String[] args){

        ArrayList<String> dictionaryOfWords = new ArrayList<String>();
        myReader r = new myReader();
         /* read sorted list words (lower case)*/
        dictionaryOfWords = r.ReadText("src//dict.txt");
        int maxWordLength = getLongestWordSize(dictionaryOfWords);

         /* 2d array definition */
        int maxRow = 4;
        int maxCol = 4;
         /* Allocate 2d char array*/
        char [][] boardArray = new char [maxRow][maxCol];
        /* Fill 2d array at random with lower case chars */
        for (int row =0; row<maxRow;row++)
        {
            for (int col =0; col<maxCol;col++)
            {
                Random rand = new Random();
                char c = (char)(rand.nextInt(26) + 'a');
                boardArray[row][col]= c;
            }
        }

        /* find any string in boardArray existing also in dictionaryOfWords */
        boolean [][] visitedBoardArray = new boolean [maxRow][maxCol];
        /* Create WoordZoeker object */
        zoekClass woordZoeker =new zoekClass(dictionaryOfWords, boardArray, maxRow, maxCol);

        for (int row =0; row<maxRow;row++)
        {
            for (int col =0; col<maxCol;col++)
            {
                /* Find words from position  */
                String str = new String("");
                Point[] path = new Point[maxWordLength];
                woordZoeker.findWords(row, col, visitedBoardArray, str, path);

            }
        }

        /* ?? */
        ArrayList<Point[]> resultPaths = woordZoeker.GetResultList();
        System.out.println("The results of the Dutch jury are:");
        for (Point[] result: resultPaths) {
            for (int i=0; i< result.length;i++){
                System.out.print(boardArray[result[i].x][result[i].y]);
            }
            System.out.println("");
        }
        System.out.println(resultPaths.size() + " words found");
    }

    static int getLongestWordSize(ArrayList<String> words){
        int largestString = words.get(0).length();

        for(int i = 0; i < words.size(); i++) {
            if(words.get(i).length() > largestString) {
                largestString = words.get(i).length();
            }
        }

        return largestString;
    }
}
