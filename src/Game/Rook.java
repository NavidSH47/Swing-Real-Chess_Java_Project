package Game;

import java.util.ArrayList;

public class Rook extends Chessman
{
    public Rook(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void validMoves(int[] locations)
    {
        clearMoves();
        if (alive)
        {
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
