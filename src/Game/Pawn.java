package Game;

import java.util.ArrayList;

public class Pawn extends Chessman
{
    protected boolean white;
    public Pawn(int x, int y, boolean white)
    {
        super(x, y);
        this.white = white;
    }

    @Override
    public void validMoves(int[] locations)
    {
        clearMoves();
        if (alive)
        {
            if (!white)
            {
                if (check(whatMove(x - 1, y + 1), locations))
                {
                    if (y + 1 <= 7 && x - 1 >= 0)
                        addMove(whatMove(x - 1, y + 1));
                }
                if (check(whatMove(x + 1, y + 1), locations))
                {
                    if (y + 1 <= 7 && x + 1 <= 7)
                        addMove(whatMove(x + 1, y + 1));
                }
                if (y + 1 <= 7)
                    if (!check(whatMove(x, y + 1), locations))
                    {
                        addMove(whatMove(x, y + 1));
                        if (y == 1)
                            if (!check(whatMove(x, y + 2), locations))
                            {
                                addMove(whatMove(x, y + 2));
                            }
                    }
            } else
            {
                if (check(whatMove(x - 1, y - 1), locations))
                {
                    if (y - 1 >= 0 && x - 1 >= 0)
                        addMove(whatMove(x - 1, y - 1));
                }
                if (check(whatMove(x + 1, y - 1), locations))
                {
                    if (y - 1 >= 0 && x + 1 <= 7)
                        addMove(whatMove(x + 1, y - 1));
                }
                if (y - 1 >= 0)
                    if (!check(whatMove(x, y - 1), locations))
                    {
                        addMove(whatMove(x, y - 1));
                        if (y == 6)
                            if (!check(whatMove(x, y - 2), locations))
                            {
                                addMove(whatMove(x, y - 2));
                            }
                    }
            }
        }
    }
}
