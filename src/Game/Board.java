package Game;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Board
{
    private ArrayList<Chessman> wChessmen;
    private ArrayList<Chessman> bChessmen;
    private int[] locations;
    private ArrayList<Integer> killList;
    private boolean checkMode;
    private boolean doubleCheck;
    private int checkerIndex;

    public Board()
    {
        wChessmen = new ArrayList<>(16);
        bChessmen = new ArrayList<>(16);
        locations = new int[32];
        setChessmen();
        updateLocations();
        checkMode = false;
        doubleCheck = false;
    }

    public boolean mateB()
    {
        boolean mate = true;
        for (int i = 0; i < bChessmen.size(); i++)
        {
            int killChecker = -10;
            bChessmen.get(i).validMoves(locations);
            realValidMoves(i);
            BKillMoves(i);
            if (!doubleCheck)
                killChecker = killCheckB(i, checkKillB());
            notCheckB(i);
            if (doubleCheck)
            {
                if (!bChessmen.get(i).moves.isEmpty())
                    mate = false;
            } else
            {
                if (!bChessmen.get(i).moves.isEmpty() || killChecker != -10)
                    mate = false;
            }
        }
        return mate;
    }

    public boolean mateW()
    {
        boolean mate = true;
        for (int i = 0; i < wChessmen.size(); i++)
        {
            int killChecker = -10;
            wChessmen.get(i).validMoves(locations);
            realValidMoves(i + 16);
            WKillMoves(i + 16);
            if (!doubleCheck)
                killChecker = killCheckW(i, checkKillW());
            notCheckW(i);
            if (doubleCheck)
            {
                if (!wChessmen.get(i).moves.isEmpty())
                    mate = false;
            } else
            {
                if (!wChessmen.get(i).moves.isEmpty() || killChecker != -10)
                    mate = false;
            }
        }
        return mate;
    }

    public int checkKillW()
    {
        for (Integer integer : killList)
        {
            if (integer == bChessmen.get(checkerIndex).xy)
            {
                return integer;
            }
        }
        return -10;
    }

    public int checkKillB()
    {
        for (Integer integer : killList)
        {
            if (integer == wChessmen.get(checkerIndex).xy)
            {
                return integer;
            }
        }
        return -10;
    }

    public void realValidMoves(int index)
    {
        if (index < 16)
        {
            for (int i = 0; i < bChessmen.get(index).moves.size(); i++)
            {
                if (bChessmen.get(index).moves.isEmpty())
                    break;
                for (Chessman bChessman : bChessmen)
                {
                    if (bChessmen.get(index).moves.get(i) == bChessman.xy)
                    {
                        bChessmen.get(index).moves.remove(i);
                        i--;
                        break;
                    }
                }
            }
        } else
        {
            for (int i = 0; i < wChessmen.get(index - 16).moves.size(); i++)
            {
                if (wChessmen.get(index - 16).moves.isEmpty())
                    break;
                for (Chessman wChessman : wChessmen)
                {
                    if (wChessmen.get(index - 16).moves.get(i) == wChessman.xy)
                    {
                        wChessmen.get(index - 16).moves.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
    }

    public boolean promotionCheck(int piece)
    {
        if (piece > 7 && piece < 16)
        {
            return IntStream.of(56, 57, 58, 59, 60, 61, 62, 63).anyMatch(i -> bChessmen.get(piece).xy == i);
        } else if (piece > 23 && piece < 32)
        {
            return IntStream.of(0, 1, 2, 3, 4, 5, 6, 7).anyMatch(i -> wChessmen.get(piece - 16).xy == i);
        }
        return false;
    }

    public void notCheckB(int piece)
    {
        int temp = bChessmen.get(piece).xy;
        int temp2 = checkerIndex;
        for (int i = 0; i < bChessmen.get(piece).moves.size(); i++)
        {
            bChessmen.get(piece).setXy(bChessmen.get(piece).moves.get(i));
            locations[piece] = bChessmen.get(piece).moves.get(i);
            if (checkP4W() != -1)
            {
                checkerIndex = temp2;
                bChessmen.get(piece).moves.remove(i);
                i--;
            }
        }
        bChessmen.get(piece).setXy(temp);
        locations[piece] = temp;
    }

    public void notCheckW(int piece)
    {
        int temp = wChessmen.get(piece).xy;
        int temp2 = checkerIndex;
        for (int i = 0; i < wChessmen.get(piece).moves.size(); i++)
        {
            wChessmen.get(piece).setXy(wChessmen.get(piece).moves.get(i));
            locations[piece + 16] = wChessmen.get(piece).moves.get(i);
            if (checkP4B() != -1)
            {
                checkerIndex = temp2;
                wChessmen.get(piece).moves.remove(i);
                i--;
            }
        }
        wChessmen.get(piece).setXy(temp);
        locations[piece + 16] = temp;
    }

    public void promotion(int piece, int selection, int xy)
    {

        switch (selection)
        {
            case 0:
                if (piece < 16)
                {
                    bChessmen.set(piece, new Queen(0, 0));
                    bChessmen.get(piece).setXy(xy);
                    bChessmen.get(piece).promoted = true;
                } else
                {
                    wChessmen.set(piece - 16, new Queen(0, 0));
                    wChessmen.get(piece - 16).setXy(xy);
                    wChessmen.get(piece - 16).promoted = true;
                }
                break;
            case 1:
                if (piece < 16)
                {
                    bChessmen.set(piece, new Rook(0, 0));
                    bChessmen.get(piece).setXy(xy);
                    bChessmen.get(piece).promoted = true;
                } else
                {
                    wChessmen.set(piece - 16, new Rook(0, 0));
                    wChessmen.get(piece - 16).setXy(xy);
                    wChessmen.get(piece - 16).promoted = true;
                }
                break;
            case 2:
                if (piece < 16)
                {
                    bChessmen.set(piece, new Bishop(0, 0));
                    bChessmen.get(piece).setXy(xy);
                    bChessmen.get(piece).promoted = true;
                } else
                {
                    wChessmen.set(piece - 16, new Bishop(0, 0));
                    wChessmen.get(piece - 16).setXy(xy);
                    wChessmen.get(piece - 16).promoted = true;
                }
                break;
            case 3:
                if (piece < 16)
                {
                    bChessmen.set(piece, new Knight(0, 0));
                    bChessmen.get(piece).setXy(xy);
                    bChessmen.get(piece).promoted = true;
                } else
                {
                    wChessmen.set(piece - 16, new Knight(0, 0));
                    wChessmen.get(piece - 16).setXy(xy);
                    wChessmen.get(piece - 16).promoted = true;
                }
                break;
        }
    }

    public void updateLocations()
    {
        locations = new int[32];
        for (int i = 0; i < 32; i++)
        {
            if (i < 16)
                locations[i] = bChessmen.get(i).xy;
            else
                locations[i] = wChessmen.get(i - 16).xy;
        }
    }

    public int checkP4B()
    {
        int count = 0;
        for (int i = 0; i < bChessmen.size(); i++)
        {
            bChessmen.get(i).validMoves(locations);
            realValidMoves(i);
            BKillMoves(i);
            for (Integer integer : killList)
            {
                if (integer == wChessmen.get(3).xy)
                {
                    checkerIndex = i;
                    count++;
                }
            }
        }
        if (count == 0)
            return -1;
        else if (count == 1)
            return checkerIndex;
        else
        {
            doubleCheck = true;
            return checkerIndex;
        }
    }

    public int checkP4W()
    {
        int count = 0;
        for (int i = 0; i < wChessmen.size(); i++)
        {
            wChessmen.get(i).validMoves(locations);
            realValidMoves(i + 16);
            WKillMoves(i + 16);
            for (Integer integer : killList)
            {
                if (integer == bChessmen.get(3).xy)
                {
                    checkerIndex = i;
                    count++;
                }
            }
        }
        if (count == 0)
            return -1;
        else if (count == 1)
            return checkerIndex;
        else
        {
            doubleCheck = true;
            return checkerIndex;
        }
    }

    public void BKillMoves(int index)
    {
        killList = new ArrayList<>();
        for (int i = 0; i < bChessmen.get(index).moves.size(); i++)
        {
            for (Chessman wChessman : wChessmen)
            {
                if (bChessmen.get(index).moves.get(i) == wChessman.xy)
                {
                    killList.add(wChessman.xy);
                    bChessmen.get(index).moves.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    public void WKillMoves(int index)
    {
        killList = new ArrayList<>();
        index = index - 16;
        for (int i = 0; i < wChessmen.get(index).moves.size(); i++)
        {
            for (Chessman bChessman : bChessmen)
            {
                if (wChessmen.get(index).moves.get(i) == bChessman.xy)
                {
                    killList.add(bChessman.xy);
                    wChessmen.get(index).moves.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

    public int killCheckW(int piece, int newLocation)
    {
        boolean sw = false;
        int temp = wChessmen.get(piece).xy;
        int temp2 = bChessmen.get(checkerIndex).xy;
        int temp3 = checkerIndex;
        wChessmen.get(piece).setXy(newLocation);
        locations[piece + 16] =  newLocation;
        bChessmen.get(checkerIndex).setXy(-1);
        locations[checkerIndex] = -1;
        if (checkP4B() != -1)
            sw = true;
        checkerIndex = temp3;
        wChessmen.get(piece).setXy(temp);
        locations[piece + 16] = temp;
        bChessmen.get(checkerIndex).setXy(temp2);
        locations[checkerIndex] = temp2;
        bChessmen.get(checkerIndex).setAlive(true);
        if (sw)
            return -10;
        else
            return newLocation;
    }
    public int killCheckB(int piece, int newLocation)
    {
        boolean sw = false;
        int temp = bChessmen.get(piece).xy;
        int temp2 = wChessmen.get(checkerIndex).xy;
        int temp3 = checkerIndex;
        bChessmen.get(piece).setXy(newLocation);
        locations[piece] =  newLocation;
        wChessmen.get(checkerIndex).setXy(-1);
        locations[checkerIndex + 16] = -1;
        if (checkP4W() != -1)
            sw = true;
        checkerIndex = temp3;
        bChessmen.get(piece).setXy(temp);
        locations[piece] = temp;
        wChessmen.get(checkerIndex).setXy(temp2);
        locations[checkerIndex + 16] = temp2;
        wChessmen.get(checkerIndex).setAlive(true);
        if (sw)
            return -10;
        else
            return newLocation;
    }
    public ArrayList<Integer> getKillList()
    {
        return killList;
    }

    public ArrayList<Chessman> getWChessmen()
    {
        return wChessmen;
    }

    public ArrayList<Chessman> getBChessmen()
    {
        return bChessmen;
    }

    public int[] getLocations()
    {
        return locations;
    }

    public boolean isCheckMode()
    {
        return checkMode;
    }

    public void setCheckMode(boolean checkMode)
    {
        this.checkMode = checkMode;
    }

    public void setDoubleCheck(boolean doubleCheck)
    {
        this.doubleCheck = doubleCheck;
    }

    public void setChessmen()
    {
        int x = 0;
        int y = 0;
        for (int i = 0; i < 16; i++)
        {
            switch (i)
            {
                case 0:
                    bChessmen.add(new Rook(0, 0));
                    wChessmen.add(new Rook(0, 7));
                    break;
                case 1:
                    bChessmen.add(new Knight(1, 0));
                    wChessmen.add(new Knight(1, 7));
                    break;
                case 2:
                    bChessmen.add(new Bishop(2, 0));
                    wChessmen.add(new Bishop(2, 7));
                    break;
                case 3:
                    bChessmen.add(new King(4, 0));
                    wChessmen.add(new King(4, 7));
                    break;
                case 4:
                    bChessmen.add(new Queen(3, 0));
                    wChessmen.add(new Queen(3, 7));
                    break;
                case 5:
                    bChessmen.add(new Bishop(5, 0));
                    wChessmen.add(new Bishop(5, 7));
                    break;
                case 6:
                    bChessmen.add(new Knight(6, 0));
                    wChessmen.add(new Knight(6, 7));
                    break;
                case 7:
                    bChessmen.add(new Rook(7, 0));
                    wChessmen.add(new Rook(7, 7));
                    break;
                default:
                    bChessmen.add(new Pawn(x++, 1, false));
                    wChessmen.add(new Pawn(y++, 6, true));
                    break;
            }
        }
    }
}
