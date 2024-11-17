package Game;

import java.util.ArrayList;

public class King extends Chessman
{

    public King(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void validMoves(int[] locations)
    {
        clearMoves();
        if (y - 1 >= 0)
        {
            if (x - 1 >= 0)
                addMove(whatMove(x - 1, y - 1));
            if (x >= 0)
                addMove(whatMove(x, y - 1));
            if (x + 1 <= 7)
                addMove(whatMove(x + 1, y - 1));
        }
        if (x - 1 >= 0)
            addMove(whatMove(x - 1, y));
        if (x + 1 <= 7)
            addMove(whatMove(x + 1, y));
        if (y + 1 <= 7)
        {
            if (x - 1 >= 0)
                addMove(whatMove(x - 1, y + 1));
            if (x >= 0)
                addMove(whatMove(x, y + 1));
            if (x + 1 <= 7)
                addMove(whatMove(x + 1, y + 1));
        }
    }
}
