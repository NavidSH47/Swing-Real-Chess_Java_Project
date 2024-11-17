package Game;

import java.util.ArrayList;

public class Queen extends Chessman
{
    public Queen(int x, int y)
    {
        super(x, y );
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

            for (int i = x - 1; i >= 0; i--)
            {
                addMove(whatMove(i, y));
                if (check(whatMove(i, y), locations))
                    break;
            }
            for (int i = x + 1; i <= 7; i++)
            {
                addMove(whatMove(i, y));
                if (check(whatMove(i, y), locations))
                    break;
            }
            for (int i = y - 1; i >= 0; i--)
            {
                addMove(whatMove(x, i));
                if (check(whatMove(x, i), locations))
                    break;
            }
            for (int i = y + 1; i <= 7; i++)
            {
                addMove(whatMove(x, i));
                if (check(whatMove(x, i), locations))
                    break;
            }
        }
    }
}
