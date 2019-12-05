import java.util.Scanner;

/**
 * Created by Laboni on 12/7/2017.
 */
public class GameStart {
   // public static int doubleTurn;
    public static void main(String[] args) {
        int turn=1;
        int[] player1=new int[7];
        int[] player2=new int[7];
        for(int i=0;i<6;i++)
        {
            player1[i]=4;
            player2[i]=4;
        }
        player1[6]=0;
        player2[6]=0;
        ShowBoard showBoard=new ShowBoard();
        showBoard.showingBoard(player1,player2);
        EmptyCheck emptyCheck=new EmptyCheck();
        while(true)
        {
            if(turn==1&&emptyCheck.empty(player1)==true)
            {
                System.out.println("The end of game");
                for(int i=0;i<6;i++)
                    player2[6]=player2[6]+player2[i];
            }
            System.out.print("Turn for player "+turn+" : ");
            //System.out.print("Turn for player 1 :");
            Scanner scanner=new Scanner(System.in);
            int move= scanner.nextInt();
            if(move>=6||move<0)
            {
                System.out.println("Invalid move;try again");
                continue;
            }
            BoardSetUp boardSetUp=new BoardSetUp();
            boardSetUp.setBoard(player1,player2,move,turn,0);
            //if(boardSetUp.doubleTurn==false) System.out.println("false");
            //else System.out.println("true");
            if(turn==1&&boardSetUp.doubleTurn==false)turn=2;
            else turn=1;
           // int numberOfStone=player1[move];
            //System.out.println("number of stone "+numberOfStone);
           // player1[move]=0;
          /*  int k=0;
            for(int i=1;i<=numberOfStone;i++)
            {
                if(move+i<=6)
                    player1[move+i]=player1[move+i]+1;
                else
                {
                    player2[k]=player2[k]+1;
                    k++;
                    if(k==6) break;
                }

            }*/
          /*  int t=1,t2=0;
            for(int i=0;i<numberOfStone;i++)
            {
                if(move+t<=6)
                {
                    player1[move+t]=player1[move+t]+1;
                    t++;
                }
                else
                {
                    player2[t2]=player2[t2]+1;
                    t2++;
                    if(t2>=6)
                    {
                        t2=0;
                        t=0;
                        move=0;
                    }
                }

            }*/
            showBoard.showingBoard(player1,player2);
            //System.out.println(move);

        }

    }
}
