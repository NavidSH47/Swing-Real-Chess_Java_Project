import Game.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Chess extends JFrame
{
    private static JFrame frame;
    private final JButton[] buttons;
    private final Board board;
    private boolean select = false;
    private final String[] wC;
    private final String[] bC;
    private int piece;
    private ArrayList<Color> tempColors;
    private ArrayList<Integer> tempButtons;
    private int previousButton;
    private final Color purple = new Color(152, 0, 241);
    private final Color orange = new Color(241, 152, 0);
    private int moves = 0;
    private boolean whiteTurn = true;
    private int killChecker = -10;

    public Chess()
    {
        buttons = new JButton[64];
        setButtons();
        wC = new String[16];
        bC = new String[16];
        setC();
        board = new Board();
        tempColors = new ArrayList<>();
        tempButtons = new ArrayList<>();

        b0.addActionListener(e -> action(0));
        b1.addActionListener(e -> action(1));
        b2.addActionListener(e -> action(2));
        b3.addActionListener(e -> action(3));
        b4.addActionListener(e -> action(4));
        b5.addActionListener(e -> action(5));
        b6.addActionListener(e -> action(6));
        b7.addActionListener(e -> action(7));
        b8.addActionListener(e -> action(8));
        b9.addActionListener(e -> action(9));
        b10.addActionListener(e -> action(10));
        b11.addActionListener(e -> action(11));
        b12.addActionListener(e -> action(12));
        b13.addActionListener(e -> action(13));
        b14.addActionListener(e -> action(14));
        b15.addActionListener(e -> action(15));
        b16.addActionListener(e -> action(16));
        b17.addActionListener(e -> action(17));
        b18.addActionListener(e -> action(18));
        b19.addActionListener(e -> action(19));
        b20.addActionListener(e -> action(20));
        b21.addActionListener(e -> action(21));
        b22.addActionListener(e -> action(22));
        b23.addActionListener(e -> action(23));
        b24.addActionListener(e -> action(24));
        b25.addActionListener(e -> action(25));
        b26.addActionListener(e -> action(26));
        b27.addActionListener(e -> action(27));
        b28.addActionListener(e -> action(28));
        b29.addActionListener(e -> action(29));
        b30.addActionListener(e -> action(30));
        b31.addActionListener(e -> action(31));
        b32.addActionListener(e -> action(32));
        b33.addActionListener(e -> action(33));
        b34.addActionListener(e -> action(34));
        b35.addActionListener(e -> action(35));
        b36.addActionListener(e -> action(36));
        b37.addActionListener(e -> action(37));
        b38.addActionListener(e -> action(38));
        b39.addActionListener(e -> action(39));
        b40.addActionListener(e -> action(40));
        b41.addActionListener(e -> action(41));
        b42.addActionListener(e -> action(42));
        b43.addActionListener(e -> action(43));
        b44.addActionListener(e -> action(44));
        b45.addActionListener(e -> action(45));
        b46.addActionListener(e -> action(46));
        b47.addActionListener(e -> action(47));
        b48.addActionListener(e -> action(48));
        b49.addActionListener(e -> action(49));
        b50.addActionListener(e -> action(50));
        b51.addActionListener(e -> action(51));
        b52.addActionListener(e -> action(52));
        b53.addActionListener(e -> action(53));
        b54.addActionListener(e -> action(54));
        b55.addActionListener(e -> action(55));
        b56.addActionListener(e -> action(56));
        b57.addActionListener(e -> action(57));
        b58.addActionListener(e -> action(58));
        b59.addActionListener(e -> action(59));
        b60.addActionListener(e -> action(60));
        b61.addActionListener(e -> action(61));
        b62.addActionListener(e -> action(62));
        b63.addActionListener(e -> action(63));
    }

    public void action(int indexOfButton)
    {
        if (whiteTurn)
        {
            if (!select)
            {
                piece = ifMan(indexOfButton);
                if (piece >= 16 && piece < 32)
                {
                    board.getWChessmen().get(piece - 16).validMoves(board.getLocations());
                    board.realValidMoves(piece);
                    if (board.isCheckMode())
                    {
                        board.WKillMoves(piece);
                        killChecker = board.killCheckW(piece - 16, board.checkKillW());
                    }
                    board.notCheckW(piece - 16);
                    if (!board.getWChessmen().get(piece - 16).getMoves().isEmpty() || killChecker != -10)
                    {
                        board.WKillMoves(piece);
                        if (!board.isCheckMode())
                            setRed(board.getKillList());
                        else
                            setRed(killChecker);
                        setGreen(board.getWChessmen().get(piece - 16).getMoves());
                        previousButton = indexOfButton;
                        select = true;
                    }
                }
            } else
            {
                int c = validButton(indexOfButton, piece);
                if (c == 2)
                {
                    validWhite(piece, indexOfButton, previousButton);
                    setDefault();
                    killChecker = -10;
                    if (board.isCheckMode())
                    {
                        board.setCheckMode(false);
                        board.setDoubleCheck(false);
                    }
                    if (board.checkP4W() != -1)
                    {
                        JOptionPane.showMessageDialog(null, "Black is in check!");
                        board.setCheckMode(true);
                        if (board.mateB())
                        {
                            JOptionPane.showMessageDialog(null, "Game Over! Black is checkmated!!\nWhite WON!!!");
                            frame.dispose();
                        }
                    }
                } else if (c == 4)
                {
                    killForWhite(piece, indexOfButton, previousButton);
                    setDefault();
                    killChecker = -10;
                    if (board.isCheckMode())
                    {
                        board.setCheckMode(false);
                        board.setDoubleCheck(false);
                    }
                    if (board.checkP4W() != -1)
                    {
                        JOptionPane.showMessageDialog(null, "Black is in check!");
                        board.setCheckMode(true);
                        if (board.mateB())
                        {
                            JOptionPane.showMessageDialog(null, "Game Over! Black is checkmated!!\nWhite WON!!!");
                            frame.dispose();
                        }
                    }
                } else if (c == 5)
                {
                    setDefault();
                    killChecker = -10;
                    select = false;
                }
            }
        } else
        {
            if (!select)
            {
                piece = ifMan(indexOfButton);
                if (piece < 16)
                {
                    board.getBChessmen().get(piece).validMoves(board.getLocations());
                    board.realValidMoves(piece);
                    if (board.isCheckMode())
                    {
                        board.BKillMoves(piece);
                        killChecker = board.checkKillB();
                    }
                    board.notCheckB(piece);
                    if (!board.getBChessmen().get(piece).getMoves().isEmpty() || killChecker != -10)
                    {
                        board.BKillMoves(piece);
                        if (!board.isCheckMode())
                            setRed(board.getKillList());
                        else
                            setRed(killChecker);
                        setGreen(board.getBChessmen().get(piece).getMoves());
                        previousButton = indexOfButton;
                        select = true;
                    }
                }
            } else
            {
                int c = validButton(indexOfButton, piece);
                if (c == 1)
                {
                    validBlack(piece, indexOfButton, previousButton);
                    setDefault();
                    killChecker = -10;
                    if (board.isCheckMode())
                    {
                        board.setCheckMode(false);
                        board.setDoubleCheck(false);
                    }
                    if (board.checkP4B() != -1)
                    {
                        JOptionPane.showMessageDialog(null, "White is in check!", "Check", JOptionPane.WARNING_MESSAGE);
                        board.setCheckMode(true);
                        if (board.mateW())
                        {
                            JOptionPane.showMessageDialog(null, "Game Over! White is checkmated!!\nBlack WON!!!");
                            frame.dispose();
                        }
                    }

                } else if (c == 3)
                {
                    killForBlack(piece, indexOfButton, previousButton);
                    setDefault();
                    killChecker = -10;
                    if (board.isCheckMode())
                    {
                        board.setCheckMode(false);
                        board.setDoubleCheck(false);
                    }
                    if (board.checkP4B() != -1)
                    {
                        JOptionPane.showMessageDialog(null, "White is in check!");
                        board.setCheckMode(true);
                        if (board.mateW())
                        {
                            JOptionPane.showMessageDialog(null, "Game Over! White is checkmated!!\nBlack WON!!!");
                            frame.dispose();
                        }
                    }
                } else if (c == 5)
                {
                    setDefault();
                    killChecker = -10;
                    select = false;
                }
            }
        }
    }

    public void validWhite(int piece, int indexOfButton, int previousButton)
    {
        board.getWChessmen().get(piece - 16).setXy(indexOfButton);
        buttons[previousButton].setText("");
        buttons[indexOfButton].setText(wC[piece - 16]);
        promoting(piece, indexOfButton);
        buttons[indexOfButton].setForeground(orange);
        board.updateLocations();
        Moves.setText("Moves Done: " + ++moves);
        select = false;
        whiteTurn = false;
        Player.setText("Purple's Turn");
        Player.setForeground(purple);
    }

    public void killForWhite(int piece, int indexOfButton, int previousButton)
    {
        board.getWChessmen().get(piece - 16).setXy(indexOfButton);
        buttons[previousButton].setText("");
        buttons[indexOfButton].setText(wC[piece - 16]);
        promoting(piece, indexOfButton);
        buttons[indexOfButton].setForeground(orange);
        board.getBChessmen().get(ifMan(indexOfButton)).setXy(-1);
        board.updateLocations();
        Moves.setText("Moves Done: " + ++moves);
        select = false;
        whiteTurn = false;
        Player.setText("Purple's Turn");
        Player.setForeground(purple);
    }

    public void validBlack(int piece, int indexOfButton, int previousButton)
    {
        board.getBChessmen().get(piece).setXy(indexOfButton);
        buttons[previousButton].setText("");
        buttons[indexOfButton].setText(bC[piece]);
        promoting(piece, indexOfButton);
        buttons[indexOfButton].setForeground(purple);
        board.updateLocations();
        Moves.setText("Moves Done: " + ++moves);
        select = false;
        whiteTurn = true;
        Player.setText("Orange's Turn");
        Player.setForeground(orange);
    }

    public void killForBlack(int piece, int indexOfButton, int previousButton)
    {
        board.getBChessmen().get(piece).setXy(indexOfButton);
        buttons[previousButton].setText("");
        buttons[indexOfButton].setText(bC[piece]);
        promoting(piece, indexOfButton);
        buttons[indexOfButton].setForeground(purple);
        board.getWChessmen().get(ifMan(indexOfButton) - 16).setXy(-1);
        board.updateLocations();
        Moves.setText("Moves Done: " + ++moves);
        select = false;
        whiteTurn = true;
        Player.setText("Orange's Turn");
        Player.setForeground(orange);
    }

    public void promoting(int piece, int indexOfButton)
    {
        if (piece < 16)
        {
            if (board.promotionCheck(piece) && !board.getBChessmen().get(piece).isPromoted())
            {
                String[] myChoices = {"Queen", "Rook", "Bishop", "Knight"};
                int selection = JOptionPane.showOptionDialog(null, "Promote your pawn to:", "Promotion", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, myChoices, myChoices[0]);
                board.promotion(piece, selection, indexOfButton);
            }
            if (board.getBChessmen().get(piece).isPromoted())
            {
                if (board.getBChessmen().get(piece) instanceof Queen)
                    buttons[indexOfButton].setText(bC[4]);
                else if (board.getBChessmen().get(piece) instanceof Rook)
                    buttons[indexOfButton].setText(bC[0]);
                else if (board.getBChessmen().get(piece) instanceof Bishop)
                    buttons[indexOfButton].setText(bC[2]);
                else if (board.getBChessmen().get(piece) instanceof Knight)
                    buttons[indexOfButton].setText(bC[1]);
            }
        } else if (piece < 32)
        {
            if (board.promotionCheck(piece) && !board.getWChessmen().get(piece - 16).isPromoted())
            {
                String[] myChoices = {"Queen", "Rook", "Bishop", "Knight"};
                int selection = JOptionPane.showOptionDialog(null, "Promote your pawn to:", "Promotion", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, myChoices, myChoices[0]);
                board.promotion(piece, selection, indexOfButton);
            }
            if (board.getWChessmen().get(piece - 16).isPromoted())
            {
                if (board.getWChessmen().get(piece - 16) instanceof Queen)
                    buttons[indexOfButton].setText(wC[4]);
                else if (board.getWChessmen().get(piece - 16) instanceof Rook)
                    buttons[indexOfButton].setText(wC[0]);
                else if (board.getWChessmen().get(piece - 16) instanceof Bishop)
                    buttons[indexOfButton].setText(wC[2]);
                else if (board.getWChessmen().get(piece - 16) instanceof Knight)
                    buttons[indexOfButton].setText(wC[1]);
            }
        }
    }

    private void setC()
    {
        for (int i = 0; i < 16; i++)
        {
            switch (i)
            {
                case 0, 7:
                    bC[i] = "♜";
                    wC[i] = "♖";
                    break;
                case 1, 6:
                    bC[i] = "♞";
                    wC[i] = "♘";
                    break;
                case 2, 5:
                    bC[i] = "♝";
                    wC[i] = "♗";
                    break;
                case 3:
                    bC[i] = "♚";
                    wC[i] = "♔";
                    break;
                case 4:
                    bC[i] = "♛";
                    wC[i] = "♕";
                    break;
                default:
                    bC[i] = "♟";
                    wC[i] = "♙";
                    break;
            }
        }
    }

    public int validButton(int indexOfButton, int piece)
    {
        if (indexOfButton == previousButton)
            return 5;
        else if (piece < 16)
        {
            if (board.isCheckMode() && indexOfButton == killChecker)
                return 3; //Kill the Checker for Black
            for (int i = 0; i < board.getBChessmen().get(piece).getMoves().size(); i++)
            {
                if (indexOfButton == board.getBChessmen().get(piece).getMoves().get(i))
                    return 1; //Valid Black
            }
            for (int i = 0; i < board.getKillList().size(); i++)
            {
                if (indexOfButton == board.getKillList().get(i))
                    return 3; //Kill for Black
            }
        } else
        {
            if (board.isCheckMode() && indexOfButton == killChecker)
                return 4; //Kill the Checker for White
            for (int i = 0; i < board.getWChessmen().get(piece - 16).getMoves().size(); i++)
            {
                if (indexOfButton == board.getWChessmen().get(piece - 16).getMoves().get(i))
                    return 2; //Valid White
            }
            for (int i = 0; i < board.getKillList().size(); i++)
            {
                if (indexOfButton == board.getKillList().get(i))
                    return 4; //Kill for White

            }
        }
        return 5;
    }

    public int ifMan(int indexOfButton)
    {
        for (int i = 0; i < board.getLocations().length; i++)
        {
            if (indexOfButton == board.getLocations()[i])
                return i;
        }
        return 33;
    }

    public void setGreen(ArrayList<Integer> moves)
    {
        for (Integer move : moves)
        {
            tempColors.add(buttons[move].getBackground());
            tempButtons.add(move);
            buttons[move].setBackground(Color.green);
        }
    }

    public void setRed(ArrayList<Integer> moves)
    {
        for (Integer move : moves)
        {
            tempColors.add(buttons[move].getBackground());
            tempButtons.add(move);
            buttons[move].setBackground(Color.red);
        }
    }

    public void setRed(int move)
    {
        if (move != -10)
        {
            tempColors.add(buttons[move].getBackground());
            tempButtons.add(move);
            buttons[move].setBackground(Color.red);
        }
    }

    public void setDefault()
    {
        for (int i = 0; i < tempButtons.size(); i++)
        {
            buttons[tempButtons.get(i)].setBackground(tempColors.get(i));
        }
        tempColors = new ArrayList<>();
        tempButtons = new ArrayList<>();
    }

    public void setButtons()
    {
        for (int i = 0; i < 64; i++)
        {
            switch (i)
            {
                case 0:
                    buttons[0] = b0;
                    break;
                case 1:
                    buttons[1] = b1;
                    break;
                case 2:
                    buttons[2] = b2;
                    break;
                case 3:
                    buttons[3] = b3;
                    break;
                case 4:
                    buttons[4] = b4;
                    break;
                case 5:
                    buttons[5] = b5;
                    break;
                case 6:
                    buttons[6] = b6;
                    break;
                case 7:
                    buttons[7] = b7;
                    break;
                case 8:
                    buttons[8] = b8;
                    break;
                case 9:
                    buttons[9] = b9;
                    break;
                case 10:
                    buttons[10] = b10;
                    break;
                case 11:
                    buttons[11] = b11;
                    break;
                case 12:
                    buttons[12] = b12;
                    break;
                case 13:
                    buttons[13] = b13;
                    break;
                case 14:
                    buttons[14] = b14;
                    break;
                case 15:
                    buttons[15] = b15;
                    break;
                case 16:
                    buttons[16] = b16;
                    break;
                case 17:
                    buttons[17] = b17;
                    break;
                case 18:
                    buttons[18] = b18;
                    break;
                case 19:
                    buttons[19] = b19;
                    break;
                case 20:
                    buttons[20] = b20;
                    break;
                case 21:
                    buttons[21] = b21;
                    break;
                case 22:
                    buttons[22] = b22;
                    break;
                case 23:
                    buttons[23] = b23;
                    break;
                case 24:
                    buttons[24] = b24;
                    break;
                case 25:
                    buttons[25] = b25;
                    break;
                case 26:
                    buttons[26] = b26;
                    break;
                case 27:
                    buttons[27] = b27;
                    break;
                case 28:
                    buttons[28] = b28;
                    break;
                case 29:
                    buttons[29] = b29;
                    break;
                case 30:
                    buttons[30] = b30;
                    break;
                case 31:
                    buttons[31] = b31;
                    break;
                case 32:
                    buttons[32] = b32;
                    break;
                case 33:
                    buttons[33] = b33;
                    break;
                case 34:
                    buttons[34] = b34;
                    break;
                case 35:
                    buttons[35] = b35;
                    break;
                case 36:
                    buttons[36] = b36;
                    break;
                case 37:
                    buttons[37] = b37;
                    break;
                case 38:
                    buttons[38] = b38;
                    break;
                case 39:
                    buttons[39] = b39;
                    break;
                case 40:
                    buttons[40] = b40;
                    break;
                case 41:
                    buttons[41] = b41;
                    break;
                case 42:
                    buttons[42] = b42;
                    break;
                case 43:
                    buttons[43] = b43;
                    break;
                case 44:
                    buttons[44] = b44;
                    break;
                case 45:
                    buttons[45] = b45;
                    break;
                case 46:
                    buttons[46] = b46;
                    break;
                case 47:
                    buttons[47] = b47;
                    break;
                case 48:
                    buttons[48] = b48;
                    break;
                case 49:
                    buttons[49] = b49;
                    break;
                case 50:
                    buttons[50] = b50;
                    break;
                case 51:
                    buttons[51] = b51;
                    break;
                case 52:
                    buttons[52] = b52;
                    break;
                case 53:
                    buttons[53] = b53;
                    break;
                case 54:
                    buttons[54] = b54;
                    break;
                case 55:
                    buttons[55] = b55;
                    break;
                case 56:
                    buttons[56] = b56;
                    break;
                case 57:
                    buttons[57] = b57;
                    break;
                case 58:
                    buttons[58] = b58;
                    break;
                case 59:
                    buttons[59] = b59;
                    break;
                case 60:
                    buttons[60] = b60;
                    break;
                case 61:
                    buttons[61] = b61;
                    break;
                case 62:
                    buttons[62] = b62;
                    break;
                case 63:
                    buttons[63] = b63;
                    break;
            }
        }
    }

    public static void main(String[] args)
    {
        frame = new JFrame("NSH Chess");
        frame.setContentPane(new Chess().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton b0;
    private JPanel panel1;
    private JLabel Title;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b56;
    private JButton b57;
    private JButton b58;
    private JButton b59;
    private JButton b60;
    private JButton b61;
    private JButton b62;
    private JButton b48;
    private JButton b40;
    private JButton b32;
    private JButton b24;
    private JButton b16;
    private JButton b8;
    private JButton b15;
    private JButton b23;
    private JButton b31;
    private JButton b39;
    private JButton b47;
    private JButton b55;
    private JButton b9;
    private JButton b17;
    private JButton b25;
    private JButton b33;
    private JButton b41;
    private JButton b49;
    private JButton b10;
    private JButton b18;
    private JButton b26;
    private JButton b34;
    private JButton b42;
    private JButton b50;
    private JButton b11;
    private JButton b19;
    private JButton b27;
    private JButton b35;
    private JButton b43;
    private JButton b51;
    private JButton b52;
    private JButton b44;
    private JButton b36;
    private JButton b28;
    private JButton b20;
    private JButton b12;
    private JButton b13;
    private JButton b21;
    private JButton b29;
    private JButton b37;
    private JButton b45;
    private JButton b53;
    private JButton b54;
    private JButton b46;
    private JButton b38;
    private JButton b30;
    private JButton b22;
    private JButton b14;
    private JLabel Moves;
    private JLabel Player;
    private JButton b63;
}
