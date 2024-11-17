package Game;

import java.util.ArrayList;

public class Knight extends Chessman
{
    public Knight(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void validMoves(int[] locations)
    {
        clearMoves();
        if (alive)
        {
            if (y + 2 <= 7)
            {
                if (x - 1 >= 0)
                    addMove(whatMove(x - 1, y + 2));
                if (x + 1 <= 7)
                    addMove(whatMove(x + 1, y + 2));
            }
            if (y - 2 >= 0)
            {
                if (x - 1 >= 0)
                    addMove(whatMove(x - 1, y - 2));
                if (x + 1 <= 7)
                    addMove(whatMove(x + 1, y - 2));
            }
            if (x + 2 <= 7)
            {
                if (y - 1 >= 0)
                    addMove(whatMove(x + 2, y - 1));
                if (y + 1 <= 7)
                    addMove(whatMove(x + 2, y + 1));
            }
            if (x - 2 >= 0)
            {
                if (y - 1 >= 0)
                    addMove(whatMove(x - 2, y - 1));
                if (y + 1 <= 7)
                    addMove(whatMove(x - 2, y + 1));
            }
        }
    }
}
