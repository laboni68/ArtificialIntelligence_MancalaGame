/**
 * Created by Laboni on 12/8/2017.
 */
public class GameBoard {
    int[][]board=new int[2][7];
    public GameBoard()
    {
        board[0][6]=0;
        board[1][6]=0;
        for(int i=0;i<6;i++)
        {
            board[0][i]=4;
            board[1][i]=4;
        }
    }
}
