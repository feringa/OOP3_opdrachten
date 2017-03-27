import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Stijn on 25-Mar-17.
 */
public class zoekClass {
    private ArrayList<String> dictionaryWithWords = new ArrayList<String>();
    private char [][] boardArray;
    private int maxCol;
    private int maxRow;
    public ArrayList<Point[]> resultList = new ArrayList<Point[]>();

    zoekClass(ArrayList<String> dictionaryToLookIn, char [][] board, int maxrow, int maxcol){
        dictionaryWithWords = dictionaryToLookIn;
        maxRow = maxrow;
        maxCol = maxcol;
        boardArray = new char [maxRow][maxCol];
        boardArray = board;
    }

        public ArrayList<Point[]> GetResultList(){
        return this.resultList;
    }

    public void findWords(int row, int col, boolean [][] visited, String result, Point[] path){

        // Mark current position as visited and append current character to result
        visited[row][col] = true;
        result += boardArray[row][col];
        path[result.length()-1] = new Point(row, col);

        // If result is present in dictionaryWithWords, then save to XXXXXXXXX
        if (isExistingSubstring(result)){

            /* If complete result -> stop looking */
            if (dictionaryWithWords.contains(result)) {
                /* bewaar in result lijst */
                Point[] tmp = new Point[result.length()];
                System.arraycopy(path, 0, tmp, 0, result.length());
                resultList.add(tmp);
//                for (int i=0; i<result.length(); i++){
//                    System.out.print(boardArray[path[i].x][path[i].y]);
//                }
//                System.out.println(" recovered");
                //System.out.println(result);
            }

            // Traverse 8 adjacent positions of boardArray[row][col]
            for (int newRow=row-1; newRow<=row+1 && newRow<maxRow; newRow++)
                for (int newCol=col-1; newCol<=col+1 && newCol<maxCol; newCol++)
                    if (newRow>=0 && newCol>=0 && !visited[newRow][newCol])
                        findWords(newRow, newCol, visited, result, path);
        }

        // Erase current character from result and mark visited of current cell as false
        result = result.substring(0, result.length()-1);
        path[path.length-1] = null;
        visited[row][col] = false;
    }

    private boolean isExistingSubstring(String str){
        // Linearly search all words
        for (int i=0; i<dictionaryWithWords.size(); i++)
            if(dictionaryWithWords.get(i).startsWith(str)){
                return true;
            }
        return false;
    }

}


