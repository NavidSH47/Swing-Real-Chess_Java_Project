package Game;

import java.util.ArrayList;

public abstract class Chessman
{
    protected int x;
    protected int y;
    protected boolean alive = true;
    protected int xy;
    protected ArrayList<Integer> moves;
    protected boolean promoted;

    public Chessman(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.xy = whatMove(x, y);
        moves = new ArrayList<>();
    }

    public boolean check(int m, int[] locations)
    {
        for (int location : locations)
        {
            if (m == location)
                return true; //blocked
        }
        return false; //free
    }

    public int whatMove(int x, int y)
    {
        return (y * 8) + x;
    }

    public abstract void validMoves(int[] locations);

    public void clearMoves()
    {
        moves = new ArrayList<>();
    }

    public void addMove(int move)
    {
        moves.add(move);
    }

    public void setXy(int xy)
    {
        this.xy = xy;
        if (xy == -1)
        {
            alive = false;
            x = y = -1;
        } else
        {
            y = xy / 8;
            x = xy - (y * 8);
        }
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }

    @Override
    public String toString()
    {
        return "Chessman{" +
                "x=" + x +
                ", y=" + y +
                ", xy=" + xy +
                '}';
    }

    public boolean isPromoted()
    {
        return promoted;
    }

    public ArrayList<Integer> getMoves()
    {
        return moves;
    }
}
