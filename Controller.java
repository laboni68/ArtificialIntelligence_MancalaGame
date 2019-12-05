import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Asus on 12/8/2017.
 */
public class Controller {

    public MoveValue minMaxWithPruning(Node node, int depth, double alpha, double beta, boolean maxPlayer, int heuristic){
        ArrayList<Pair<Integer, Integer>> moves;
        if(maxPlayer)  moves = node.board.validMoves(1);
        else moves = node.board.validMoves(-1);

        Iterator<Pair<Integer,Integer>> movesIterator = moves.iterator();
        double value = 0.0;

        if(!node.canContinue(node,maxPlayer))
            return new MoveValue();

        if(depth == 0 || node.isTerminalNode(node)){
            switch(heuristic) {
                case 1:
                    value = node.heuristicDiscCount(node);
                    break;
                case 2:
                    value = node.heuristicPositional1(node);
                    break;
                case 3:
                    value = node.heuristicPositional2(node);
                    break;
                case 4:
                    value = node.heuristicMobility(node);
                    break;
                case 5:
                    value = node.heuristicMy(node);
                    break;
                case 6:
                    value = node.heuristicPositional3(node);
            }
            return new MoveValue(value);
        }

        MoveValue returnMove;
        MoveValue bestMove = null;

        if(maxPlayer){
            while(movesIterator.hasNext()){
                Pair<Integer,Integer> currentMove = movesIterator.next();
                Board temp = new Board();
                node.copyBoard(temp,node.board);
                temp.executeMove(currentMove.getKey(), currentMove.getValue(),1);
                returnMove = minMaxWithPruning(new Node(temp),depth - 1, alpha, beta, false, heuristic);
                if ((bestMove == null) || (bestMove.returnValue < returnMove.returnValue)) {
                    bestMove = returnMove;
                    bestMove.returnMove = currentMove;
                }
                if (returnMove.returnValue > alpha) {
                    alpha = returnMove.returnValue;
                    bestMove = returnMove;
                }
                if (beta <= alpha) {
                    bestMove.returnValue = beta;
                    bestMove.returnMove = null;
                    return bestMove; // pruning
                }
            }
            return bestMove;
        }
        else{
            while (movesIterator.hasNext()) {
                Pair<Integer,Integer> currentMove = movesIterator.next();
                Board temp = new Board();
                node.copyBoard(temp,node.board);
                temp.executeMove(currentMove.getKey(), currentMove.getValue(),-1);
                returnMove = minMaxWithPruning(new Node(temp),depth - 1, alpha, beta, true, heuristic);
                if ((bestMove == null) || (bestMove.returnValue > returnMove.returnValue)) {
                    bestMove = returnMove;
                    bestMove.returnMove = currentMove;
                }
                if (returnMove.returnValue < beta) {
                    beta = returnMove.returnValue;
                    bestMove = returnMove;
                }
                if (beta <= alpha) {
                    bestMove.returnValue = alpha;
                    bestMove.returnMove = null;
                    return bestMove; // pruning
                }
            }
            return bestMove;
        }
    }

}
