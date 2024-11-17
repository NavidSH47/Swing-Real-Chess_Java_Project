package Game;

import java.util.ArrayList;

public class Bishop extends Chessman
{

    public Bishop(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void validMoves(int[] locations)
    {
        clearMoves();
        if (alive)
        {
            for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--)
            {
                addMove(whatMove(i, j));
                if (check(whatMove(i, j), locations))
                    break;
            }
            for (int i = x - 1, j = y + 1; i >= 0 && j <= 7; i--, j++)
            {
                addMove(whatMove(i, j));
                if (check(whatMove(i, j), locations))
                    break;
            }
            for (int i = x + 1, j = y + 1; i <= 7 && j <= 7; i++, j++)
            {
                addMove(whatMove(i, j));
                if (check(whatMove(i, j), locations))
                    break;
            }
            for (int i = x + 1, j = y - 1; i <= 7 && j >= 0; i++, j--)
            {
                addMove(whatMove(i, j));
                if (check(whatMove(i, j), locations))
                    break;
            }
        }
    }
}
