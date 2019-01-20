//Names: Michael Selvaggi and Kenny Kim
//Description: CPT : 2 Player Chess on and 8 by 8 board
//Date Created: May 11, 2017
//Date Last Modified: June 19, 2017

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class CPT extends JFrame implements MouseListener, MouseMotionListener
{
    public static int menu = 1, mouseX, mouseY, menuMoved = 0, playMoved = 0, instructionMoved = 0, instructionMoved2 = 0, instructionMoved3 = 0;
    //CREATE FRAME
    public CPT() {
        //create frame
        setTitle("CHESS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 800);
        setVisible(true);
        setResizable(false);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    //CREATE GRAPHICS
    public void paint(Graphics g)
    {
        if (menu == 1 && menuMoved <= 2){
            //initial menu screen
            int xWidth = getWidth(), yHeight = getHeight();
            Font tRb = new Font("TimesRoman", Font.BOLD,72);
            Color b = new Color (160, 136, 69);
            g.setColor (b);
            g.fillRect(0,0,xWidth,yHeight);
            g.setColor(Color.WHITE);
            g.setFont(tRb);
            g.drawString("CHESS", 400,100);
            g.drawString("Play", 700,300);
            g.drawString("Instructions", 500, 500);
            g.drawImage(menuImage,0,50,this);
            g.drawImage(menuImage2 ,855,250,this);
            g.drawImage(menuImage2 ,635,250,this);
            g.drawImage(menuImage2 ,430,450,this);
            g.drawImage(menuImage2 ,920,450,this);
            tRb = new Font("TimesRoman", Font.BOLD,30);
            g.setFont(tRb);
            g.drawString("BY: MICHELE SELVAGGI AND KENNETH KIM", 210, 700);
        }
        if(menu == 2 && playMoved <= 1) {
            //main game
            int x = 60, y = 60;
            Color brown = new Color (139,69,19);

            g.clearRect(0, 0, 1000, 800);
            //Draw the board
            for(int row  = 0; row < gridLayout.length; row++){
                for (int col = 0; col < gridLayout[row].length - 1; col ++){
                    if(gridLayout[row][col] == 0){
                        g.setColor(Color.WHITE);
                    }
                    else
                    {
                        g.setColor(brown);
                    }
                    g.fillRect(col * 70 + 220, row * 70 + 110, 70, 70);
                }
            }
            //Draw the pieces
            for (int i = 0; i < board.length; i++) {
                for (int a = 0; a < board[0].length; a++) {

                    if (board[i][a].owner == 1) {
                        g.setColor(Color.red);
                    } else if (board[i][a].owner == 2) {
                        g.setColor(Color.blue);
                    }

                    g.drawImage(board[i][a].image, a * 70 + 238, i * 70 + 110, this);
                }
            }

            //Draw the captured piece for player 2
            for (int i = 1; i < player2CapturedPieces.length + 1; i++) {
                g.drawImage(player2CapturedPieces[i - 1].image, x + 10, y + 40, this);
                if (i % 2 == 0) {
                    y += 60;
                }

                if (x == 60) {
                    x = 120;
                } else if (x == 120) {
                    x = 60;
                }
            }

            x = 60;
            y = 60;

            //Draw the captured piece for player 2
            for (int i = 1; i < player1CapturedPieces.length + 1; i++) {
                g.drawImage(player1CapturedPieces[i - 1].image, x + 770, y + 40, this);
                if (i % 2 == 0) {
                    y += 60;
                }

                if (x == 60) {
                    x = 120;
                } else if (x == 120) {
                    x = 60;
                }
            }

            x = 260;
            y = 100;
            for (int i = 1; i < 9; i++) //DRAW BOARD COLUMN NUMBERS
            {
                g.drawString(i + " ", x, y);
                x += 69;
            }
            x = 205;
            y = 148;
            for (int i = 1; i < 9; i++) //DRAW BOARD ROW NUMBERS
            {
                g.drawString (i + " ", x, y);
                y += 69;
            }
        }
        if(menu == 3 && instructionMoved <= 1){
            //instructions page 1
            int xWidth = getWidth(), yHeight = getHeight();
            Font tRb = new Font("TimesRoman", Font.BOLD, 72);
            Color b = new Color(160, 136, 69);
            g.setColor(b);
            g.fillRect(0, 0, xWidth, yHeight);
            g.setColor(Color.WHITE);
            g.setFont(tRb);
            g.drawString("Instructions", 300, 100);
            tRb = new Font("TimesRoman", Font.BOLD, 30);
            g.setFont(tRb);
            g.drawString("Objective: To place the opponents king under checkmate. This ", 30, 150);
            g.drawString("occurs when the opponents king cannot avoid capture", 30, 180);
            g.drawString("Rules: Normal rules follow in this game", 30, 250);
            g.drawString("No time limit.", 30, 290);
            g.drawString("White goes first.", 30, 330);
            g.drawString("NEXT PAGE", 800, 500);

        }
        if(menu == 4 && instructionMoved2 <= 1){
            //instructions page 2
            int xWidth = getWidth(), yHeight = getHeight();
            Color b = new Color(160, 136, 69);
            g.setColor(b);
            g.fillRect(0, 0, xWidth, yHeight);
            g.setColor(Color.WHITE);
            Font tRb = new Font("TimesRoman", Font.BOLD, 72);
            g.setFont(tRb);
            g.drawString("Instructions", 300, 100);
            tRb = new Font("TimesRoman", Font.BOLD, 15);
            g.setFont(tRb);
            //PAWN
            g.drawImage(pawnImage,50,130,this);
            g.drawString("Pawns can move 2 or 1 squares in the beginning and", 135, 150);
            g.drawString("only 1 after that. They can attack diagonally. ", 135, 170);
            g.drawString("Pawn promotion and En Passant available (see next page)", 135, 190);
            // Horse
            g.drawImage(knightImage,50,280,this);
            g.drawString("Horses can only move or attack in a 4 square 'L' shape ", 135, 300);
            // Rook
            g.drawImage(rookImage,50,430,this);
            g.drawString("Rooks can only move or attack vertically or horizontally", 135, 450);
            g.drawString("for any number of squares", 135, 470);
            g.drawString("Castle available (see next page)", 135, 490);
            // Bishop
            g.drawImage(bishopImage,570,130,this);
            g.drawString("Bishops can only move or attack diagonally ", 655, 150);
            g.drawString("for any number of squares", 655, 170);
            // Queen
            g.drawImage(queenImage,570,280,this);
            g.drawString("Queen can move in any direction for any  ", 655, 300);
            g.drawString("number of squares", 655, 320);
            // King
            g.drawImage(kingImage,570,430,this);
            g.drawString("King can move in any direction for only", 655, 450);
            g.drawString("one square", 655, 470);
            g.drawString("NEXT PAGE", 800, 600);
        }
        if (menu == 5 && instructionMoved3 <= 1){
            //instructions page 3
            int xWidth = getWidth(), yHeight = getHeight();
            Color b = new Color(160, 136, 69);
            g.setColor(b);
            g.fillRect(0, 0, xWidth, yHeight);
            g.setColor(Color.WHITE);
            Font tRb = new Font("TimesRoman", Font.BOLD, 72);
            g.setFont(tRb);
            g.drawString("Instructions", 300, 100);
            tRb = new Font("TimesRoman", Font.BOLD, 18);
            g.setFont(tRb);
            //Pawn Promotion
            g.drawImage(pawnPromoteImage,30,150,this);
            g.drawString("Pawn Promotion allows for a pawn to turn into",185,165);
            g.drawString("any other piece in the game except another pawn",185,185);
            g.drawString("and a king piece. This only occurs when the ",185,205);
            g.drawString("player's pawn reaches the opponent's end. ",185,225);
            // En Passant
            g.drawImage(enPassantImage,30,360,this);
            g.drawString("En Passant allows for the taking of a  ",185,375);
            g.drawString("pawn when the opponent moves their  ",185,395);
            g.drawString("pawn 2 spaces and the players pawn is   ",185,415);
            g.drawString("right beside it.Only available in   ",185,435);
            g.drawString("succession with the opponents pawn ",185,455);
            g.drawString("movement otherwise the 'En Passant' ",185,475);
            g.drawString("is now over.",185,495);
            // Castle
            g.drawImage(castleImage,540,250,this);
            g.drawString("The Castle move in chess allows  ",700,260);
            g.drawString("for the King and the Rook to  ",700,280);
            g.drawString("change spots. This move is only ",700,300);
            g.drawString("available when both the king   ",700,320);
            g.drawString("and the rook have not been  ",700,340);
            g.drawString("moved, King is not in check  ",700,360);
            g.drawString("both before and after castle   ",700,380);
            g.drawString("and the spaces between the ",700,400);
            g.drawString("pieces are empty. ",700,420);
            g.drawString("Back to Menu" , 700, 700);
        }
    }
    static Scanner input = new Scanner(System.in); //CREATE SYSTEM INPUT

    //COLOURS
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    //PIECES
    static piece pawn1Player1;
    static piece pawn2Player1;
    static piece pawn3Player1;
    static piece pawn4Player1;
    static piece pawn5Player1;
    static piece pawn6Player1;
    static piece pawn7Player1;
    static piece pawn8Player1;
    static piece rook1Player1;
    static piece rook2Player1;
    static piece knight1Player1;
    static piece knight2Player1;
    static piece bishop1Player1;
    static piece bishop2Player1;
    static piece queenPlayer1;
    static piece kingPlayer1;
    static piece pawn1Player2;
    static piece pawn2Player2;
    static piece pawn3Player2;
    static piece pawn4Player2;
    static piece pawn5Player2;
    static piece pawn6Player2;
    static piece pawn7Player2;
    static piece pawn8Player2;
    static piece rook1Player2;
    static piece rook2Player2;
    static piece knight1Player2;
    static piece knight2Player2;
    static piece bishop1Player2;
    static piece bishop2Player2;
    static piece queenPlayer2;
    static piece kingPlayer2;
    static piece emptyPiece;

    //Images
    public static BufferedImage pawnImage,knightImage,rookImage,kingImage,queenImage,bishopImage,pawnPromoteImage,
            castleImage,enPassantImage, menuImage, menuImage2;

    //BOARD ARRAYS --------------------------------------------------------
    static piece[][] board;

    static int gridLayout[][] =
            {{0, 1, 0, 1, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1, 0, 1, 0, 1},
            {0, 1, 0, 1, 0, 1, 0, 1, 0},
            {1, 0, 1, 0, 1, 0, 1, 0, 1}};


    static piece[] player1CapturedPieces;
    static piece[] player2CapturedPieces;

    //GLOBAL GAME VARIABLES -------------------------------------------------------
    static int lastPawnMoveValue = 0;
    static boolean check = false, staleMate = false, checkMate = false, enPassent = false;
    static piece selectedPiece = null, previousSelectedPiece = null, displacedPiece = null;
    static int [] selectedPlace = null, previousSelectedPlace = null;
    static int [] currentPlace = new int [2];
    static int player = 1;
    static int move = 0;

    public static void main (String [] args)
    {
        initiatePieces();
        while (true)
        {
            mainGame();

        }
    }

    private static void initiatePieces()
    {
        try {

            BufferedImage whiteRook = ImageIO.read(new File("wr.gif"));
            BufferedImage whiteQueen = ImageIO.read(new File("wq.gif"));
            BufferedImage whitePawn = ImageIO.read(new File("wp.gif"));
            BufferedImage whiteKnight = ImageIO.read(new File("wn.gif"));
            BufferedImage whiteKing = ImageIO.read(new File("wk.gif"));
            BufferedImage whiteBishop = ImageIO.read(new File("wb.gif"));

            BufferedImage blackRook = ImageIO.read(new File("br.gif"));
            BufferedImage blackQueen = ImageIO.read(new File("bq.gif"));
            BufferedImage blackPawn = ImageIO.read(new File("bp.gif"));
            BufferedImage blackKnight = ImageIO.read(new File("bn.gif"));
            BufferedImage blackKing = ImageIO.read(new File("bk.gif"));
            BufferedImage blackBishop = ImageIO.read(new File("bb.gif"));

            pawnPromoteImage = ImageIO.read(new File("pawnPromote.png"));
            enPassantImage = ImageIO.read(new File("enPassant.png"));
            castleImage = ImageIO.read(new File("castle.png"));
            menuImage = ImageIO.read(new File("menuPawn2.png"));
            menuImage2 = ImageIO.read(new File("menuPawn.png"));

                pawn1Player1 = new piece("pawn", true, false, 1, 2, "white", 6, 0, whitePawn);
                pawn2Player1 = new piece("pawn", true, false, 1, 2, "black", 6, 1, whitePawn);
                pawn3Player1 = new piece("pawn", true, false, 1, 2, "white", 6, 2, whitePawn);
                pawn4Player1 = new piece("pawn", true, false, 1, 2, "black", 6, 3, whitePawn);
                pawn5Player1 = new piece("pawn", true, false, 1, 2, "white", 6, 4, whitePawn);
                pawn6Player1 = new piece("pawn", true, false, 1, 2, "black", 6, 5, whitePawn);
                pawn7Player1 = new piece("pawn", true, false, 1, 2, "white", 6, 6, whitePawn);
                pawn8Player1 = new piece("pawn", true, false, 1, 2, "black", 6, 7, whitePawn);
                rook1Player1 = new piece("rook", true, false, 1, 7, "black", 7, 0, whiteRook);
                rook2Player1 = new piece("rook", true, false, 1, 7, "white", 7, 7, whiteRook);
            knight1Player1 = new piece("knight", true, false, 1, 4, "white", 7, 1, whiteKnight);
            knight2Player1 = new piece("knight", true, false, 1, 4, "black", 7, 6, whiteKnight);
            bishop1Player1 = new piece("bishop", true, false, 1, 7, "black", 7, 2, whiteBishop);
            bishop2Player1 = new piece("bishop", true, false, 1, 7, "white", 7, 5, whiteBishop);
               queenPlayer1 = new piece("queen", true, false, 1, 7, "white", 7, 3, whiteQueen);
                 kingPlayer1 = new piece("king", true, false, 1, 1, "black", 7, 4, whiteKing);

                pawn1Player2 = new piece("pawn", true, false, 2, 2, "black", 1, 0, blackPawn);
                pawn2Player2 = new piece("pawn", true, false, 2, 2, "white", 1, 1, blackPawn);
                pawn3Player2 = new piece("pawn", true, false, 2, 2, "black", 1, 2, blackPawn);
                pawn4Player2 = new piece("pawn", true, false, 2, 2, "white", 1, 3, blackPawn);
                pawn5Player2 = new piece("pawn", true, false, 2, 2, "black", 1, 4, blackPawn);
                pawn6Player2 = new piece("pawn", true, false, 2, 2, "white", 1, 5, blackPawn);
                pawn7Player2 = new piece("pawn", true, false, 2, 2, "black", 1, 6, blackPawn);
                pawn8Player2 = new piece("pawn", true, false, 2, 2, "white", 1, 7, blackPawn);
                rook1Player2 = new piece("rook", true, false, 2, 7, "white", 0, 0, blackRook);
                rook2Player2 = new piece("rook", true, false, 2, 7, "black", 0, 7, blackRook);
            knight1Player2 = new piece("knight", true, false, 2, 4, "black", 0, 1, blackKnight);
            knight2Player2 = new piece("knight", true, false, 2, 4, "white", 0, 6, blackKnight);
            bishop1Player2 = new piece("bishop", true, false, 2, 7, "white", 0, 2, blackBishop);
            bishop2Player2 = new piece("bishop", true, false, 2, 7, "black", 0, 5, blackBishop);
               queenPlayer2 = new piece("queen", true, false, 2, 7, "black", 0, 3, blackQueen);
                 kingPlayer2 = new piece("king", true, false, 2, 1, "white", 0, 4, blackKing);
                emptyPiece = new piece("empty", false, false, 0, 0, "empty", 0, 0, null);

            pawnImage = ImageIO.read(new File("bp.gif"));
            rookImage = ImageIO.read(new File("wr.gif"));
            knightImage = ImageIO.read(new File("bn.gif"));
            bishopImage = ImageIO.read(new File("wb.gif"));
            queenImage = ImageIO.read(new File("bq.gif"));
            kingImage = ImageIO.read(new File("wk.gif"));

            board = new piece[][]{{rook1Player2, knight1Player2, bishop1Player2, queenPlayer2, kingPlayer2, bishop2Player2, knight2Player2, rook2Player2},
                    {pawn1Player2, pawn2Player2, pawn3Player2, pawn4Player2, pawn5Player2, pawn6Player2, pawn7Player2, pawn8Player2},
                    {emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece},
                    {emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece},
                    {emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece},
                    {emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece},
                    {pawn1Player1, pawn2Player1, pawn3Player1, pawn4Player1, pawn5Player1, pawn6Player1, pawn7Player1, pawn8Player1},
                    {rook1Player1, knight1Player1, bishop1Player1, queenPlayer1, kingPlayer1, bishop2Player1, knight2Player1, rook2Player1}};

            player1CapturedPieces = new piece[]{emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece};
            player2CapturedPieces = new piece[]{emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece};

        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public static void mainGame () //Main game loop
    {
        CPT c = new CPT ();

        while (!checkMate || !staleMate) //Main game loop that runs until checkmate or stalemate is reached
        {
            c.repaint();
            while (true) //Secondary loop that breaks only when values chosen by player would not put them in check
            {
                while (true) //Input loop for getting values from user
                {
                    if (checkIfCheck(player, board)) //Check if the current player is in check
                    {
                        check = true;
                        System.out.println(ANSI_RED + "\nPlayer " + player + ", you are in check.");
                        if (checkIfAllMovesResultInCheck(player, board)) //If all possible moves cannot bring the current player out of check, they are in checkmate
                        {
                            checkMate = true;
                            System.out.print("The game ended in check mate. Congratulations to player ");
                            if (player == 1)
                            {
                                System.out.println("2");
                            }
                            else
                            {
                                System.out.println("1");
                            }
                        }
                        System.out.println("Thanks for playing.");
                        System.exit(1);
                    }
                    else //If the player is not in check
                    {
                        check = false;
                        if (checkIfAllMovesResultInCheck(player, board)) //If the player is not in check, but all their possible moves would put them in check, they are in stalemate
                        {
                            staleMate = true;
                            System.out.print("The game ended in stale mate.");
                            System.out.println("Thanks for playing.");
                            System.exit(1);

                        }
                    }

                    checkCastle(player, board); //Check if a castle can be executed by the current player, and execute it if possible on the request of the player
                    c.repaint(); //Update the display to show the castled pieces

                    selectedPiece = selectPiece(player, board); //Player selects a piece
                    currentPlace[0] = selectedPiece.row;
                    currentPlace[1] = selectedPiece.col;
                    selectedPlace = selectPlace(player, board); //Player selects a desired place to move the piece to

                    if (checkMove(player, board, selectedPiece, selectedPlace)) //Checks if the desired move is possible, breaks the input loop
                    {
                        selectedPiece.moved = true;
                        displacedPiece = board[selectedPlace[0]][selectedPlace[1]];
                        break;
                    }
                    else //If the move is invalid, continue the input loop and repromt player
                    {
                        System.out.println(ANSI_RED + "\nInvalid Move. Try Again.");
                    }
                }

                updateBoard(player, board, selectedPiece, selectedPlace, currentPlace); //Update the board arrays based on player's chosen piece and chosen place

                if (checkIfCheck(player, board)) //Checks if the chosen placement of pieces on the board array puts the current player in check
                {
                    selectedPiece.row = currentPlace[0]; //Reset the chosen piece's internal values
                    selectedPiece.col = currentPlace[1];
                    board[currentPlace[0]][currentPlace[1]] = selectedPiece; //Reset the board array to the state it was in during the previous turn
                    board[selectedPlace[0]][selectedPlace[1]] = displacedPiece;
                    if (!displacedPiece.type.equals("empty"))
                    {
                        removeCapturedPiece(player);
                    }
                    System.out.println(ANSI_RED + "\nMove would put you in check or would fail to take you out of check. Try again."); //Repromt user to select a move that would not put them in check
                }
                else //If the player's desired move does not put them into check, or (if they are currently in check) removes them from check, break the input look and procceed to next turn
                {
                    break;
                }

            }

            executeEnPassent (player, previousSelectedPiece, previousSelectedPlace, selectedPiece, currentPlace); //Method to check if enPassent has occured during the turn, award the player the captured pawn if enPassent was executed

            changePawnMovement(selectedPiece); //IF A PAWN HAS MOVED DURING THIS TURN, DO NOT ALLOW IT TO MOVE 2 SPACES IN THE FUTURE
            previousSelectedPiece = selectedPiece;
            previousSelectedPlace = selectedPlace;

            if (selectedPiece.type.equals("pawn")) //CHECK IF PIECE MOVED IS A PAWN AND HAS MOVED 2 SPACES DURING THAT MOVE (Essential for enPassent)
            {
                if (player == 1 && currentPlace[0] - selectedPlace[0] == 2)
                {
                    lastPawnMoveValue = 2;
                }
                else if (player == 2 && selectedPlace[0] - currentPlace[0] == 2)
                {
                    lastPawnMoveValue = 2;
                }
                else
                {
                    lastPawnMoveValue = 1;
                }
            }
            else
            {
                lastPawnMoveValue = 0;
            }

            if (player == 1) //SWITCH PLAYER TURNS
            {
                player = 2;
            }
            else
            {
                player = 1;
            }

            checkPromotion(selectedPiece, selectedPlace); //Check if a pawn has reached the end of the board, and give the opportunity for a promotion

            move += 1;
            enPassent = false;
            System.out.println (""); //Set the next console output on a new line for easier reading

        }
    }

    public static piece selectPiece(int player, piece[][] board) //Method to allow player to select a piece for movement
    {
        int row, col;
        piece selectedPiece;

        while (true)
        {
            System.out.print(ANSI_RESET + "Player " + player + ": " + ANSI_BLUE + "Select Piece Row: "); //Select the row corresponing to the desired piece
            row = input.nextInt() - 1;
            System.out.print(ANSI_RESET + "Player " + player + ": " + ANSI_BLUE + "Select Piece Column: "); //Select the column corresponding to the desired piece
            col = input.nextInt() - 1;

            if (row < 0 || col < 0 || row > 7 || col > 7) //Check if the selected column and row are within the range of the board array, repromt user if they are not
            {
                System.out.println(ANSI_RED + "\nEnter a valid selection.");
            }
            else if (board[row][col].type.equals("empty")) //Check if the selected column and row contain a piece, repromt user if they do not
            {
                System.out.println(ANSI_RED + "\nSelect a piece, not an empty space.");
            }
            else if (board[row][col].owner != player) //If the selected piece belongs to the opposite player, repromt the player
            {
                System.out.println(ANSI_RED + "\nSelect one of your own pieces.");
            }
            else //If the selected column & row are within range, the spot is not empty, and the selected piece belongs to the current player: if all are met, break the loop and continue
            {
                break;
            }
        }

        selectedPiece = board[row][col];
        return selectedPiece;
    }

    public static int[] selectPlace(int player, piece[][] board) //Method to allow player to select a place to put the selected piece
    {
        int[] selectedPlace = {0, 0};
        int row, col;
        while (true) //Complete input loop that breaks when selected place is valid
        {
            while (true) //Loop that reprompts user for a selected row if not valid, breaks if selection is valid
            {
                System.out.print(ANSI_RESET + "Player " + player + ": " + ANSI_PURPLE + "Enter desired row: ");
                row = input.nextInt() - 1;

                if (row > 7 || row < 0)
                {
                    System.out.println(ANSI_RED + "\nEnter a valid row.");
                }
                else
                {
                    selectedPlace[0] = row;
                    break;
                }
            }
            while (true) //Loop that reprompts user for a selected column if not valid, breaks if selection is valid
            {
                System.out.print(ANSI_RESET + "Player " + player + ": " + ANSI_PURPLE + "Enter desired column: ");
                col = input.nextInt() - 1;

                if (col > 7 || col < 0)
                {
                    System.out.println(ANSI_RED + "\nEnter a valid column.");
                }
                else
                {
                    selectedPlace[1] = col;
                    break;
                }
            }
            if (board[row][col].owner == player) //If the selected place to move to contains a piece belonging to the current player, repromt
            {
                System.out.println(ANSI_RED + "\nYou cannot attack your own piece");
            }
            else //If the selection is valid: row and column are within range and desired places does not contain piece of current player, break loop and continue
            {
                break;
            }
        }
        return selectedPlace;
    }

    public static boolean checkMove(int player, piece[][] board, piece selectedPiece, int[] selectedPlace) //Redirective method that checks if the selected move is possible, redirects to a method specific to the type of piece
    {
        boolean checkMove = true;

        //Redirect to specifc method, depending on the type of piece chosen, for checking if the chosen move is possible
        if (selectedPiece.type.equals("pawn"))
        {
            checkMove = checkIfPawnMove(player, board, selectedPiece, selectedPlace);
        }
        else if (selectedPiece.type.equals("rook"))
        {
            checkMove = checkIfRookMove(player, board, selectedPiece, selectedPlace);
        }
        else if (selectedPiece.type.equals("knight"))
        {
            checkMove = checkIfKnightMove(player, board, selectedPiece, selectedPlace);
        }
        else if (selectedPiece.type.equals("bishop"))
        {
            checkMove = checkIfBishopMove(player, board, selectedPiece, selectedPlace);
        }
        else if (selectedPiece.type.equals("queen"))
        {
            checkMove = checkIfQueenMove(player, board, selectedPiece, selectedPlace);
        }
        else if (selectedPiece.type.equals("king"))
        {
            checkMove = checkIfKingMove(player, board, selectedPiece, selectedPlace);
        }
        return checkMove;
    }

    public static boolean checkIfPawnMove(int player, piece[][] board, piece selectedPiece, int[] selectedPlace) //Check if selected movement is possible when selected piece is a pawn
    {
        int row = selectedPlace[0], col = selectedPlace[1];
        boolean canMove = true;

        if (player == 1) //Check if current player is player 1
        {
            if (col == selectedPiece.col) //Check if movement is straight up only
            {
                if (row == selectedPiece.row - 2 && selectedPiece.moveValue == 2 && board[selectedPiece.row - 1][selectedPiece.col].type.equals("empty") && board[selectedPiece.row - 2][selectedPiece.col].type.equals("empty"))
                {
                    canMove = true;  //Check if movement is straight up 2 places, check if pawn can move 2 places (ie. if pawn has already moved, do not let it move 2 spaces), and check if the spot is empty
                }
                else //Check if movement is straight up one space, check if the desired spot is empty
//Movement is not allowed if the desired spot is filled
                    canMove = row == selectedPiece.row - 1 && board[selectedPiece.row - 1][selectedPiece.col].type.equals("empty");
            }
            else if (col != selectedPiece.col) //Check if movement is diagonal from current position and check if an attack can be made (ex. a piece of the opposite player is directly one space diagonal from the pawn)
            {
                if ((row == selectedPiece.row - 1 && col == selectedPiece.col + 1 && board[selectedPiece.row - 1][selectedPiece.col + 1].owner == 2) || row == selectedPiece.row - 1 && col == selectedPiece.col + 1 && checkEnPassant(player, previousSelectedPiece, previousSelectedPlace, selectedPiece) && board[selectedPiece.row][selectedPiece.col + 1] == previousSelectedPiece)
                {
                    canMove = true;
                }
                else //Movement is not allowed if the spot is not filled with a piece of the opposite player
                    canMove = (row == selectedPiece.row - 1 && col == selectedPiece.col - 1 && board[selectedPiece.row - 1][selectedPiece.col - 1].owner == 2) || row == selectedPiece.row - 1 && col == selectedPiece.col - 1 && checkEnPassant(player, previousSelectedPiece, previousSelectedPlace, selectedPiece) && board[selectedPiece.row][selectedPiece.col - 1] == previousSelectedPiece;
            }
        }
        else if (player == 2) //Check if current player is player 2
        {
            if (col == selectedPiece.col) //Check if movement is straight down only
            {
                if (row == selectedPiece.row + 2 && selectedPiece.moveValue == 2 && board[selectedPiece.row + 1][selectedPiece.col].type.equals("empty") && board[selectedPiece.row + 2][selectedPiece.col].type.equals("empty"))
                {
                    canMove = true; //Check if movement is straight down 2 places, check if pawn can move 2 places (ie. if pawn has already moved, do not let it move 2 spaces), and check if the spot is empty
                }
                else //Movement is not allowed if the desired spot is filled
                    canMove = row == selectedPiece.row + 1 && board[selectedPiece.row + 1][selectedPiece.col].type.equals("empty");
            }
            else if (col != selectedPiece.col) //Check if movement is diagonal from current position and check if an attack can be made (ex. a piece of the opposite player is directly one space diagonal from the pawn)
            {
                if ((row == selectedPiece.row + 1 && col == selectedPiece.col + 1 && board[selectedPiece.row + 1][selectedPiece.col + 1].owner == 1) || row == selectedPiece.row + 1 && col == selectedPiece.col + 1 && checkEnPassant(player, previousSelectedPiece, previousSelectedPlace, selectedPiece) && board[selectedPiece.row][selectedPiece.col + 1] == previousSelectedPiece)
                {
                    canMove = true;
                }
                else //Movement is not allowed if the spot is not filled with a piece of the opposite player
                    canMove = (row == selectedPiece.row + 1 && col == selectedPiece.col - 1 && board[selectedPiece.row + 1][selectedPiece.col - 1].owner == 1) || row == selectedPiece.row + 1 && col == selectedPiece.col - 1 && checkEnPassant(player, previousSelectedPiece, previousSelectedPlace, selectedPiece) && board[selectedPiece.row][selectedPiece.col - 1] == previousSelectedPiece;
            }
        }
        return canMove;
    }

    public static boolean checkIfRookMove(int player, piece[][] board, piece selectedPiece, int[] selectedPlace) //Check if the selected movement is possible when the selected piece is a rook
    {
        int row = selectedPlace[0], col = selectedPlace[1];
        boolean canMove = true;

        if (selectedPiece.row == row) //Checks if the movement runs horizontally from selected piece
        {
            if (col > selectedPiece.col) //Checks if the movement is horizontally to the right of the player
            {
                for (int i = 1; i < (col - selectedPiece.col); i++) //Check all spaces between the selected piece and selected place
                {
                    if (board[selectedPiece.row][selectedPiece.col + i].type.equals("empty")) //Checks if the area between the piece and the desired place contains no pieces
                    {
                        canMove = true;
                    }
                    else if (!board[selectedPiece.row][selectedPiece.col + i].type.equals("empty"))
                    {
                        canMove = false;
                        break;
                    }
                }
            }
            else //Checks if the movement is horizontally to the left of the player
            {
                for (int i = 1; i < (selectedPiece.col - col); i++) //Check all spaces between the selected piece and selected place
                {
                    if (board[selectedPiece.row][selectedPiece.col - i].type.equals("empty")) //Checks if the area between the piece and the desired place contains no pieces
                    {
                        canMove = true;
                    }
                    else if (!board[selectedPiece.row][selectedPiece.col - i].type.equals("empty"))
                    {
                        canMove = false;
                        break;
                    }
                }
            }
        }
        else if (selectedPiece.col == col) //Checks if the movement runs vertically from the selected piece
        {
            if (row > selectedPiece.row) //Checks if the desired movement is vertically below the player
            {
                for (int i = 1; i < (row - selectedPiece.row); i++) //Check all spaces between the selected piece and selected place
                {
                    if (board[selectedPiece.row + i][selectedPiece.col].type.equals("empty"))  //Checks if the area between the piece and the desired place contains no pieces
                    {
                        canMove = true;
                    }
                    else if (!board[selectedPiece.row + i][selectedPiece.col].type.equals("empty"))
                    {
                        canMove = false;
                        break;
                    }
                }
            }
            else //Checks if the movement is vertically above the player
            {
                for (int i = 1; i < (selectedPiece.row - row); i++) //Check all spaces between the selected piece and selected place
                {
                    if (board[selectedPiece.row - i][selectedPiece.col].type.equals("empty")) //Checks if the area between the piece and the desired place contains no pieces
                    {
                        canMove = true;
                    }
                    else if (!board[selectedPiece.row - i][selectedPiece.col].type.equals("empty"))
                    {
                        canMove = false;
                        break;
                    }
                }
            }
        }
        else //If the desired move does not fall directly vertical or horizontal to the selected piece, do not let the piece move
        {
            return false;
        }
        return canMove;
    }

    public static boolean checkIfKnightMove(int player, piece[][] board, piece selectedPiece, int[] selectedPlace) //Check if the selected movement is possible when the selected piece is a knight
    {
        int row = selectedPlace[0], col = selectedPlace[1];
        boolean canMove = true;

        //Check if the desired movement falls on any of the 8 "L" patterns around the selected piece
        if (row == selectedPiece.row - 2 && col == selectedPiece.col + 1)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row - 1 && col == selectedPiece.col + 2)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row + 1 && col == selectedPiece.col + 2)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row + 2 && col == selectedPiece.col + 1)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row + 2 && col == selectedPiece.col - 1)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row + 1 && col == selectedPiece.col - 2)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row - 1 && col == selectedPiece.col - 2)
        {
            canMove = true;
        }
        else //If the movement does not follow the "L" pattern, do not let the movement execute
            canMove = row == selectedPiece.row - 2 && col == selectedPiece.col - 1;

        return canMove;
    }

    public static boolean checkIfBishopMove(int player, piece[][] board, piece selectedPiece, int[] selectedPlace) //Checks if the selected movement is possible when the selected piece is a bishop
    {
        int row = selectedPlace[0], col = selectedPlace[1];
        boolean canMove = true;

        if (selectedPiece.row - row == col - selectedPiece.col) //Checks if desired movement is diagonally upwards and to the right of the player
        {
            for (int i = 1; i < (selectedPiece.row - row); i++) //Checks spaces in between selected piece and the selected place
            {
                if (board[selectedPiece.row - i][selectedPiece.col + i].type.equals("empty")) //Makes sure there are no obstacles
                {
                    canMove = true;
                }
                else
                {
                    canMove = false;
                    break;
                }
            }
        }
        else if (row - selectedPiece.row == col - selectedPiece.col) //Checks if desired movement is diagonally downwards and to the right of the player
        {
            for (int i = 1; i < (row - selectedPiece.row); i++) //Checks spaces in between selected piece and the selected place
            {
                if (board[selectedPiece.row + i][selectedPiece.col + i].type.equals("empty")) //Makes sure there are no obstacles
                {
                    canMove = true;
                }
                else
                {
                    canMove = false;
                    break;
                }
            }
        }
        else if (row - selectedPiece.row == selectedPiece.col - col) //Checks if desired movement is diagonally downwards and to the left of the player
        {
            for (int i = 1; i < (row - selectedPiece.row); i++) //Checks spaces in between selected piece and the selected place
            {
                if (board[selectedPiece.row + i][selectedPiece.col - i].type.equals("empty")) //Makes sure there are no obstacles
                {
                    canMove = true;
                }
                else
                {
                    canMove = false;
                    break;
                }
            }
        }
        else if (selectedPiece.row - row == selectedPiece.col - col) //Checks if desired movement is diagonally upwards and to the left of the player
        {
            for (int i = 1; i < (selectedPiece.row - row); i++) //Checks spaces in between selected piece and the selected place
            {
                if (board[selectedPiece.row - i][selectedPiece.col - i].type.equals("empty")) //Makes sure there are no obstacles
                {
                    canMove = true;
                }
                else
                {
                    canMove = false;
                    break;
                }
            }
        }
        else //If the desired movement does not fall diagonal to the selected piece, do not execute the movement
        {
            canMove = false;
        }

        return canMove;
    }

    public static boolean checkIfQueenMove(int player, piece[][] board, piece selectedPiece, int[] selectedPlace) //Checks if the selected movement is possible when the selected piece is a queen
    {
        int row = selectedPlace[0], col = selectedPlace[1];
        boolean canMove = true;

        if (selectedPiece.row == row) //Checks if the movement is horizontal to the selected piece
        {
            if (col > selectedPiece.col) //Checks if the horizontal movement is to the right of the piece
            {
                for (int i = 1; i < (col - selectedPiece.col); i++) //Checks if there is an obstacle in the way of movement
                {
                    if (board[selectedPiece.row][selectedPiece.col + i].type.equals("empty"))
                    {
                        canMove = true;
                    }
                    else if (!board[selectedPiece.row][selectedPiece.col + i].type.equals("empty"))
                    {
                        canMove = false;
                        break;
                    }
                }
            }
            else //Checks if the horizontal movement is to the left of the piece
            {
                for (int i = 1; i < (selectedPiece.col - col); i++) //Checks if there is an obstacle in the way of movement
                {
                    if (board[selectedPiece.row][selectedPiece.col - i].type.equals("empty"))
                    {
                        canMove = true;
                    }
                    else if (!board[selectedPiece.row][selectedPiece.col - i].type.equals("empty"))
                    {
                        canMove = false;
                        break;
                    }
                }
            }
        }
        else if (selectedPiece.col == col) //Checks if the movement is vertical to the selected piece
        {
            if (row > selectedPiece.row) //Checks if the movement is vertically below player piece
            {
                for (int i = 1; i < (row - selectedPiece.row); i++) //Checks if there is an obstacle in the way of movement
                {
                    if (board[selectedPiece.row + i][selectedPiece.col].type.equals("empty"))
                    {
                        canMove = true;
                    }
                    else if (!board[selectedPiece.row + i][selectedPiece.col].type.equals("empty"))
                    {
                        canMove = false;
                        break;
                    }
                }
            }
            else //Checks if the movement is vertically above player's piece
            {
                for (int i = 1; i < (selectedPiece.row - row); i++) //Checks if there is an obstacle in the way of movement
                {
                    if (board[selectedPiece.row - i][selectedPiece.col].type.equals("empty"))
                    {
                        canMove = true;
                    }
                    else if (!board[selectedPiece.row - i][selectedPiece.col].type.equals("empty"))
                    {
                        canMove = false;
                        break;
                    }
                }
            }
        }
        else if (row < selectedPiece.row && col > selectedPiece.col) //Checks if the movement is diagonally above and to the right of the selected piece
        {
            for (int i = 1; i < (selectedPiece.row - row); i++) //Checks if there is an obstacle in the way of movement
            {
                if (board[selectedPiece.row - i][selectedPiece.col + i].type.equals("empty") && (selectedPiece.row - row == col - selectedPiece.col))
                {
                    canMove = true;
                }
                else
                {
                    canMove = false;
                    break;
                }
            }
        }
        else if (row > selectedPiece.row && col > selectedPiece.col) //Checks if the movement is diagonally below and to the right of the selected piece
        {
            for (int i = 1; i < (row - selectedPiece.row); i++) //Checks if there is an obstacle in the way of movement
            {
                if (board[selectedPiece.row + i][selectedPiece.col + i].type.equals("empty") && (row - selectedPiece.row == col - selectedPiece.col))
                {
                    canMove = true;
                }
                else
                {
                    canMove = false;
                    break;
                }
            }
        }
        else if (row > selectedPiece.row && col < selectedPiece.col) //Checks if the movement is diagonally below and to the left of the selected piece
        {
            for (int i = 1; i < (row - selectedPiece.row); i++) //Checks if there is an obstacle in the way of movement
            {
                if (board[selectedPiece.row + i][selectedPiece.col - i].type.equals("empty") && (row - selectedPiece.row == selectedPiece.col - col))
                {
                    canMove = true;
                }
                else
                {
                    canMove = false;
                    break;
                }
            }
        }
        else if (row < selectedPiece.row && col < selectedPiece.col) //Checks if the movement is diagonally above and to the left of the selected piece
        {
            for (int i = 1; i < (selectedPiece.row - row); i++) //Checks if there is an obstacle in the way of movement
            {
                if (board[selectedPiece.row - i][selectedPiece.col - i].type.equals("empty") && (selectedPiece.row - row == selectedPiece.col - col))
                {
                    canMove = true;
                }
                else
                {
                    canMove = false;
                    break;
                }
            }
        }
        else //If the movement is not horizontal, vertical, or diagonal from the selected piece, do not let the piece move
        {
            canMove = false;
        }

        return canMove;
    }

    public static boolean checkIfKingMove(int player, piece[][] board, piece selectedPiece, int[] selectedPlace) //Checks if the selected movement is possible when the selected piece is a king
    {
        int row = selectedPlace[0], col = selectedPlace[1];
        boolean canMove = true;

        //Checks if the movement falls on any of the 8 immediate locations around the selected piece (king) ex. one space up from selected piece location or one space diagonal from selected piece location etc...
        if (row == selectedPiece.row - 1 && col == selectedPiece.col)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row + 1 && col == selectedPiece.col)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row && col == selectedPiece.col + 1)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row && col == selectedPiece.col - 1)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row - 1 && col == selectedPiece.col + 1)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row + 1 && col == selectedPiece.col + 1)
        {
            canMove = true;
        }
        else if (row == selectedPiece.row + 1 && col == selectedPiece.col - 1)
        {
            canMove = true;
        }
        else canMove = row == selectedPiece.row - 1 && col == selectedPiece.col - 1;

        return canMove;
    }

    public static void updateBoard(int player, piece[][] board, piece selectedPiece, int[] selectedPlace, int[] currentPlace) //Upates the main board array and the two arrays that hold the pieces captured by the players
    {
        selectedPiece.row = selectedPlace[0]; //Update the selected piece's internal row value to the new row value
        selectedPiece.col = selectedPlace[1]; //Update the selected piece's internal column value to the new column value

        if (!board[selectedPlace[0]][selectedPlace[1]].type.equals("empty")) //Checks if the selected piece displaces or makes and attack on another piece
        {
            if (player == 1) //If it is player 1's turn
            {
                for (int i = 0; i < player1CapturedPieces.length; i++) //Append the captured piece to player one's array for captured pieces
                {
                    if (player1CapturedPieces[i].type.equals("empty"))
                    {
                        player1CapturedPieces[i] = board[selectedPlace[0]][selectedPlace[1]];
                        break;
                    }
                }
            }
            else if (player == 2) //If it is player 2's turn
            {
                for (int i = 0; i < player2CapturedPieces.length; i++) //Append the captured piece to player two's array for captured pieces
                {
                    if (player2CapturedPieces[i].type.equals("empty"))
                    {
                        player2CapturedPieces[i] = board[selectedPlace[0]][selectedPlace[1]];
                        break;
                    }
                }
            }
        }

        board[selectedPlace[0]][selectedPlace[1]] = selectedPiece; //Update the board to place the selected piece in the desired place
        board[currentPlace[0]][currentPlace[1]] = emptyPiece; //Replace the selected piece's original spot with an emptyPiece object [Essential for making sure the board array functions properly and is traversed fully]
    }

    public static void removeCapturedPiece (int player) //If a move is unsuccessful or is invalid, but a piece would have been captured if the move was possible, remove the captured piece from the player's captured piece array
    {
        if (player == 1) //If it's player 1's turn
        {
            for (int i = 0; i < player1CapturedPieces.length; i++) //Remove last captured piece from array
            {
                if (player1CapturedPieces[i].type.equals("empty") && i != 0)
                {
                    player1CapturedPieces[i - 1] = emptyPiece;
                    break;
                }
            }
        }
        else if (player == 2) //If it's player 2's turn
        {
            for (int i = 0; i < player2CapturedPieces.length; i++) //Remove last captured piece from array
            {
                if (player2CapturedPieces[i].type.equals("empty") && i != 0)
                {
                    player2CapturedPieces[i - 1] = emptyPiece;
                    break;
                }
            }
        }
    }

    public static boolean checkIfCheck (int player, piece [][] board) //Redirective method that checks all possible areas around the player's king, and checks if any piece puts the player's king in check
    {
        boolean isCheck = false;
        int kingRow, kingCol;

        if (checkIfPawnCheck (player, board)) //Checks if any pawn puts the player's king in check
        {
            isCheck = true;
        }
        else if (checkIfRookCheck (player, board)) //Checks if any rook puts the player's king in check
        {
            isCheck = true;
        }
        else if (checkIfKnightCheck (player, board)) //Checks if any knight puts the player's king in check
        {
            isCheck = true;
        }
        else if (checkIfBishopCheck(player, board)) //Checks if any bishop puts the player's king in check
        {
            isCheck = true;
        }
        else //Checks if any queen puts the player's king in check
//If no pieces put the king in check, the king is not in check
            isCheck = checkIfQueenCheck(player, board);
        return isCheck;
    }

    public static boolean checkIfPawnCheck (int player, piece [][] board) //Checks if there is a pawn that puts the player's king in check
    {
        boolean isCheck = false;
        int kingRow, kingCol;

        if (player == 1) //If player is player 1
        {
            kingRow = kingPlayer1.row;
            kingCol = kingPlayer1.col;

            if (board[kingRow - 1][kingCol + 1].type.equals("pawn") && board[kingRow - 1][kingCol + 1].owner == 2) //Checks if a pawn is one space diagonally up and to the right of the player's king
            {
                isCheck = true;
            }
            else //Checks if a pawn is one space diagonally up and to the left of the player's king
                isCheck = board[kingRow - 1][kingCol - 1].type.equals("pawn") && board[kingRow - 1][kingCol - 1].owner == 2;
        }
        else if (player == 2) //If player is player 2
        {
            kingRow = kingPlayer2.row;
            kingCol = kingPlayer2.col;

            if (board[kingRow + 1][kingCol + 1].type.equals("pawn") && board[kingRow + 1][kingCol + 1].owner == 1) //Checks if a pawn is one space diagonally down and to the right of the player's king
            {
                isCheck = true;
            }
            else //Checks if a pawn is one space diagonally down and to the left of the player's king
                isCheck = board[kingRow + 1][kingCol - 1].type.equals("pawn") && board[kingRow + 1][kingCol - 1].owner == 1;
        }
        return isCheck;
    }

    public static boolean checkIfRookCheck (int player, piece [][] board) //Checks if there is a rook that puts the player's king in check
    {
        boolean isCheck = false, check1 = false, check2 = false, check3 = false, check4 = false;
        int kingRow = 0, kingCol = 0;
        int opponent = 0;

        if (player == 1) //Set up values according to player 1's turn
        {
            kingRow = kingPlayer1.row;
            kingCol = kingPlayer1.col;
            opponent = 2;
        }
        else if (player == 2) //Set up values accoring to player 2's turn
        {
            kingRow = kingPlayer2.row;
            kingCol = kingPlayer2.col;
            opponent = 1;
        }

        //CHECK 1
        for (int i = 1; i <= kingRow; i++) //Check vertically upwards from the player's king
        {
            if(board[kingRow - i][kingCol].type.equals("rook") && board[kingRow - i][kingCol].owner == opponent) //Checks if there is a rook in the king's path
            {
                for (int a = 1; a < i; a++) //Checks if there are any pieces between the king and the rook
                {
                    if (board[kingRow - a][kingCol].type.equals("empty"))
                    {
                        check1 = true;
                    }
                    else if (! board[kingRow - a][kingCol].type.equals("empty"))
                    {
                        check1 = false;
                        break;
                    }
                }
                break;
            }
        }
        //CHECK 2
        for (int i = 1; i <= 7 - kingCol; i++) //Check horizontally to the right of the player's king
        {
            if(board[kingRow][kingCol + i].type.equals("rook") && board[kingRow][kingCol + i].owner == opponent) //Checks if there is a rook in the king's path
            {
                for (int a = 1; a < i; a++) //Checks if there are any pieces between the king and the rook
                {
                    if (board[kingRow][kingCol + a].type.equals("empty"))
                    {
                        check2 = true;
                    }
                    else if (! board[kingRow][kingCol + a].type.equals("empty"))
                    {
                        check2 = false;
                        break;
                    }
                }
                break;
            }
        }
        //CHECK 3
        for (int i = 1; i <= 7 - kingRow; i++) //Check vertically downwards from the player's king
        {
            if(board[kingRow + i][kingCol].type.equals("rook") && board[kingRow + i][kingCol].owner == opponent) //Checks if there is a rook in the king's path
            {
                for (int a = 1; a < i; a++) //Checks if there are any pieces between the king and the rook
                {
                    if (board[kingRow + a][kingCol].type.equals("empty"))
                    {
                        check3 = true;
                    }
                    else if (! board[kingRow + a][kingCol].type.equals("empty"))
                    {
                        check3 = false;
                        break;
                    }
                }
                break;
            }
        }
        //CHECK 4
        for (int i = 1; i <= kingCol; i++) //Check horizontally to the left of the player's king
        {
            if(board[kingRow][kingCol - i].type.equals("rook") && board[kingRow][kingCol - i].owner == opponent) //Checks if there is a rook in the king's path
            {
                for (int a = 1; a < i; a++) //Checks if there are any pieces between the king and the rook
                {
                    if (board[kingRow][kingCol - a].type.equals("empty"))
                    {
                        check4 = true;
                    }
                    else if (! board[kingRow][kingCol - a].type.equals("empty"))
                    {
                        check4 = false;
                        break;
                    }
                }
                break;
            }
        }

        //If any of the 4 possibilities are met, the player is in check
        isCheck = check1 || check2 || check3 || check4;

        return isCheck;

    }

    public static boolean checkIfKnightCheck (int player, piece [][] board) //Checks if there is a knight that puts the player's king in check
    {
        boolean isCheck = false, check1 = false, check2 = false, check3 = false, check4 = false, check5 = false, check6 = false, check7 = false, check8 = false;
        int kingRow = 0, kingCol = 0;
        int opponent = 0;

        if (player == 1) //Set up values according to player 1
        {
            kingRow = kingPlayer1.row;
            kingCol = kingPlayer1.col;
            opponent = 2;
        }
        else if (player == 2) //Set up values according to player 2
        {
            kingRow = kingPlayer2.row;
            kingCol = kingPlayer2.col;
            opponent = 1;
        }

        //Check all 8 "L" patterns around the player's king for any of the opposite player's knights

        //CHECK 1
        if (kingRow > 1 && kingCol < 7)
        {
            check1 = board[kingRow - 2][kingCol + 1].type.equals("knight") && board[kingRow - 2][kingCol + 1].owner == opponent;
        }

        //CHECK 2
        if (kingRow > 0 && kingCol < 6)
        {
            check2 = board[kingRow - 1][kingCol + 2].type.equals("knight") && board[kingRow - 1][kingCol + 2].owner == opponent;
        }

        //CHECK 3
        if (kingRow < 7 && kingCol < 6)
        {
            check3 = board[kingRow + 1][kingCol + 2].type.equals("knight") && board[kingRow + 1][kingCol + 2].owner == opponent;
        }

        //CHECK 4
        if (kingRow < 6 && kingCol < 7)
        {
            check4 = board[kingRow + 2][kingCol + 1].type.equals("knight") && board[kingRow + 2][kingCol + 1].owner == opponent;
        }

        //CHECK 5
        if (kingRow < 6 && kingCol > 0)
        {
            check5 = board[kingRow + 2][kingCol - 1].type.equals("knight") && board[kingRow + 2][kingCol - 1].owner == opponent;
        }


        //CHECK 6
        if (kingRow < 7 && kingCol > 1)
        {
            check6 = board[kingRow + 1][kingCol - 2].type.equals("knight") && board[kingRow + 1][kingCol - 2].owner == opponent;
        }

        //CHECK 7
        if (kingRow > 0 && kingCol > 1)
        {
            check7 = board[kingRow - 1][kingCol - 2].type.equals("knight") && board[kingRow - 1][kingCol - 2].owner == opponent;
        }

        //CHECK 8
        if (kingRow > 1 && kingCol > 0)
        {
            check8 = board[kingRow - 2][kingCol - 1].type.equals("knight") && board[kingRow - 2][kingCol - 1].owner == opponent;
        }

        //If any of the "L" patterns around the player's king contain a knight of the opponent, the king is in check
        isCheck = check1 || check2 || check3 || check4 || check5 || check6 || check7 || check8;

        return isCheck;
    }

    public static boolean checkIfBishopCheck (int player, piece [][] board) //Checks if there is a bishop that puts the player's king in check
    {
        boolean isCheck = false, check1 = false, check2 = false, check3 = false, check4 = false;
        int kingRow = 0, kingCol = 0;
        int placesChecked = 0;
        int opponent = 0;

        if (player == 1) //Set up values according to player 1
        {
            kingRow = kingPlayer1.row;
            kingCol = kingPlayer1.col;
            opponent = 2;
        }
        else if (player == 2) //Set up values according to player 2
        {
            kingRow = kingPlayer2.row;
            kingCol = kingPlayer2.col;
            opponent = 1;
        }

        //CHECK 1
        if (kingRow <= 7 - kingCol) //Determine the number of places to be checked
        {
            placesChecked = kingRow;
        }
        else
        {
            placesChecked = 7 - kingCol;
        }
        for (int i = 1; i <= placesChecked; i++) //Check if there is a bishop diagonally upwards and to the right of the king
        {
            if(board[kingRow - i][kingCol + i].type.equals("bishop") && board[kingRow - i][kingCol + i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Check if there are any pieces between the bishop and the player's king (if there are none, the king is in check)
                {
                    if (board[kingRow - a][kingCol + a].type.equals("empty"))
                    {
                        check1 = true;
                    }
                    else if (! board[kingRow - a][kingCol + a].type.equals("empty"))
                    {
                        check1 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 2
        if (7 - kingRow <= 7 - kingCol) //Determine the number of places to be checked
        {
            placesChecked = 7 - kingRow;
        }
        else
        {
            placesChecked = 7 - kingCol;
        }
        for (int i = 1; i <= placesChecked; i++) //Check if there is a bishop diagonally downwards and to the right of the king
        {
            if(board[kingRow + i][kingCol + i].type.equals("bishop") && board[kingRow + i][kingCol + i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Check if there are any pieces between the bishop and the player's king (if there are none, the king is in check)
                {
                    if (board[kingRow + a][kingCol + a].type.equals("empty"))
                    {
                        check2 = true;
                    }
                    else if (! board[kingRow + a][kingCol + a].type.equals("empty"))
                    {
                        check2 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 3
        if (7 - kingRow <= kingCol) //Determine the number of places to be checked
        {
            placesChecked = 7 - kingRow;
        }
        else
        {
            placesChecked = kingCol;
        }
        for (int i = 1; i <= placesChecked; i++) //Check if there is a bishop diagonally downwards and to the left of the king
        {
            if(board[kingRow + i][kingCol - i].type.equals("bishop") && board[kingRow + i][kingCol - i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Check if there are any pieces between the bishop and the player's king (if there are none, the king is in check)
                {
                    if (board[kingRow + a][kingCol - a].type.equals("empty"))
                    {
                        check3 = true;
                    }
                    else if (! board[kingRow + a][kingCol - a].type.equals("empty"))
                    {
                        check3 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 4
        if (kingRow <= kingCol) //Determine the number of places to be checked
        {
            placesChecked = kingRow;
        }
        else
        {
            placesChecked = kingCol;
        }
        for (int i = 1; i <= placesChecked; i++) //Check if there is a bishop diagonally upwards and to the left of the king
        {
            if(board[kingRow - i][kingCol - i].type.equals("bishop") && board[kingRow - i][kingCol - i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Check if there are any pieces between the bishop and the player's king (if there are none, the king is in check)
                {
                    if (board[kingRow - a][kingCol - a].type.equals("empty"))
                    {
                        check4 = true;
                    }
                    else if (! board[kingRow - a][kingCol - a].type.equals("empty"))
                    {
                        check4 = false;
                        break;
                    }
                }
                break;
            }
        }

        //If any of the possibilities are met, the player's king is in check
        isCheck = check1 || check2 || check3 || check4;

        return isCheck;
    }

    public static boolean checkIfQueenCheck (int player, piece [][] board) //Checks if there is a queen that puts the player's king in check
    {
        boolean isCheck = false, check1 = false, check2 = false, check3 = false, check4 = false, check5 = false, check6 = false, check7 = false, check8 = false;
        int kingRow = 0, kingCol = 0;
        int placesChecked = 0;
        int opponent = 0;

        if (player == 1) //Set up values accoring to player 1
        {
            kingRow = kingPlayer1.row;
            kingCol = kingPlayer1.col;
            opponent = 2;
        }
        else if (player == 2) //Set up values according to player 2
        {
            kingRow = kingPlayer2.row;
            kingCol = kingPlayer2.col;
            opponent = 1;
        }

        //CHECK 1
        for (int i = 1; i <= kingRow; i++) //Checks if there is a queen vertically above the player's king
        {
            if(board[kingRow - i][kingCol].type.equals("queen") && board[kingRow - i][kingCol].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Checks if there are pieces between the king and queen that protect the king from being in check
                {
                    if (board[kingRow - a][kingCol].type.equals("empty"))
                    {
                        check1 = true;
                    }
                    else if (! board[kingRow - a][kingCol].type.equals("empty"))
                    {
                        check1 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 2
        for (int i = 1; i <= 7 - kingCol; i++) //Checks if there is a queen horizontally to the right of the player's king
        {
            if(board[kingRow][kingCol + i].type.equals("queen") && board[kingRow][kingCol + i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Checks if there are pieces between the king and queen that protect the king from being in check
                {
                    if (board[kingRow][kingCol + a].type.equals("empty"))
                    {
                        check2 = true;
                    }
                    else if (! board[kingRow][kingCol + a].type.equals("empty"))
                    {
                        check2 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 3
        for (int i = 1; i <= 7 - kingRow; i++) //Checks if there is a queen vertically below the player's king
        {
            if(board[kingRow + i][kingCol].type.equals("queen") && board[kingRow + i][kingCol].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Checks if there are pieces between the king and queen that protect the king from being in check
                {
                    if (board[kingRow + a][kingCol].type.equals("empty"))
                    {
                        check3 = true;
                    }
                    else if (! board[kingRow + a][kingCol].type.equals("empty"))
                    {
                        check3 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 4
        for (int i = 1; i <= kingCol; i++) //Checks if there is a queen horizontally to the left of the player's king
        {
            if(board[kingRow][kingCol - i].type.equals("queen") && board[kingRow][kingCol - i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Checks if there are pieces between the king and queen that protect the king from being in check
                {
                    if (board[kingRow][kingCol - a].type.equals("empty"))
                    {
                        check4 = true;
                    }
                    else if (! board[kingRow][kingCol - a].type.equals("empty"))
                    {
                        check4 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 5
        if (kingRow <= 7 - kingCol) //Determines the number of diagonal places to be checked
        {
            placesChecked = kingRow;
        }
        else
        {
            placesChecked = 7 - kingCol;
        }
        for (int i = 1; i <= placesChecked; i++) //Checks if there is a queen diagonally above and to the right of the player's king
        {
            if(board[kingRow - i][kingCol + i].type.equals("queen") && board[kingRow - i][kingCol + i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Checks if there are pieces between the king and queen that protect the king from being in check
                {
                    if (board[kingRow - a][kingCol + a].type.equals("empty"))
                    {
                        check1 = true;
                    }
                    else if (! board[kingRow - a][kingCol + a].type.equals("empty"))
                    {
                        check1 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 6
        if (7 - kingRow <= 7 - kingCol) //Determines the number of diagonal places to be checked
        {
            placesChecked = 7 - kingRow;
        }
        else
        {
            placesChecked = 7 - kingCol;
        }
        for (int i = 1; i <= placesChecked; i++) //Checks if there is a queen diagonally below and to the right of the player's king
        {
            if(board[kingRow + i][kingCol + i].type.equals("queen") && board[kingRow + i][kingCol + i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Checks if there are pieces between the king and queen that protect the king from being in check
                {
                    if (board[kingRow + a][kingCol + a].type.equals("empty"))
                    {
                        check2 = true;
                    }
                    else if (! board[kingRow + a][kingCol + a].type.equals("empty"))
                    {
                        check2 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 7
        if (7 - kingRow <= kingCol) //Determines the number of diagonal places to be checked
        {
            placesChecked = 7 - kingRow;
        }
        else
        {
            placesChecked = kingCol;
        }
        for (int i = 1; i <= placesChecked; i++) //Checks if there is a queen diagonally below and to the left of the player's king
        {
            if(board[kingRow + i][kingCol - i].type.equals("queen") && board[kingRow + i][kingCol - i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Checks if there are pieces between the king and queen that protect the king from being in check
                {
                    if (board[kingRow + a][kingCol - a].type.equals("empty"))
                    {
                        check3 = true;
                    }
                    else if (! board[kingRow + a][kingCol - a].type.equals("empty"))
                    {
                        check3 = false;
                        break;
                    }
                }
                break;
            }
        }

        //CHECK 8
        if (kingRow <= kingCol) //Determines the number of diagonal places to be checked
        {
            placesChecked = kingRow;
        }
        else
        {
            placesChecked = kingCol;
        }
        for (int i = 1; i <= placesChecked; i++) //Checks if there is a queen diagonally above and to the left of the player's king
        {
            if(board[kingRow - i][kingCol - i].type.equals("queen") && board[kingRow - i][kingCol - i].owner == opponent)
            {
                for (int a = 1; a < i; a++) //Checks if there are pieces between the king and queen that protect the king from being in check
                {
                    if (board[kingRow - a][kingCol - a].type.equals("empty"))
                    {
                        check4 = true;
                    }
                    else if (! board[kingRow - a][kingCol - a].type.equals("empty"))
                    {
                        check4 = false;
                        break;
                    }
                }
                break;
            }
        }

        //If any of the possibilites are met, the player's king is in check
        isCheck = check1 || check2 || check3 || check4 || check5 || check6 || check7 || check8;
        return isCheck;

    }

    public static void changePawnMovement (piece selectedPiece) //Checks if a pawn has moved during the player's turn, and if it has, change its move value from 2 to 1 (don't allow it to move 2 spaces if it has moved at all)
    {
        if (selectedPiece.type.equals("pawn"))
        {
            selectedPiece.moveValue = 1;
        }
    }

    public static boolean checkEnPassant (int player, piece previousSelectedPiece, int[] previousSelectedPlace, piece selectedPiece) //Check if the current player can execute enPassent on the opponent
    {
        boolean checkPassant = false;
        if (player == 1)
        {
            if (previousSelectedPiece.type.equals("pawn") && selectedPiece.type.equals("pawn")) //Checks that the last moved piece was a pawn and the current chosen piece is a pawn
            {
                if (previousSelectedPlace[0] == selectedPiece.row && (lastPawnMoveValue == 2)) //Checks that the last moved pawn moved 2 spaces and the last moved pawn is in the same row as the chosen pawn
                {
                    if (selectedPiece.col != 0 && board[selectedPiece.row][selectedPiece.col - 1] == previousSelectedPiece) //If all is met, enPassent can be made
                    {
                        checkPassant = true;
                    }
                    else if (selectedPiece.col != 7 && board[selectedPiece.row][selectedPiece.col + 1] == previousSelectedPiece)
                    {
                        checkPassant = true;
                    }
                }
                else
                {
                    checkPassant = false;
                }
            }
            else
            {
                checkPassant = false;
            }
        }
        else
        {
            if (previousSelectedPiece.type.equals("pawn") && selectedPiece.type.equals("pawn")) //Checks that the last moved piece was a pawn and the current chosen piece is a pawn
            {
                if (previousSelectedPlace[0] == selectedPiece.row && lastPawnMoveValue == 2) //Checks that the last moved pawn moved 2 spaces and the last moved pawn is in the same row as the chosen pawn
                {
                    if (selectedPiece.col != 0 && board[selectedPiece.row][selectedPiece.col - 1] == previousSelectedPiece) //If all is met, enPassent can be made
                    {
                        checkPassant = true;
                    }
                    else if (selectedPiece.col != 7 && board[selectedPiece.row][selectedPiece.col + 1] == previousSelectedPiece)
                    {
                        checkPassant = true;
                    }

                }
                else
                {
                    checkPassant = false;
                }
            }
            else
            {
                checkPassant = false;
            }
        }

        enPassent = checkPassant;
        return checkPassant;
    }

    public static void executeEnPassent (int player, piece previousSelectedPiece, int[] previousSelectedPlace, piece selectedPiece, int [] currentPlace) //If enpassent
    {
        if (move != 0 && enPassent) //If an enPassent can be made and the move is not the first move, continue
        {
            if (player == 1) //If player is player 1
            {
                if (currentPlace[1] + 1 == selectedPiece.col || currentPlace[1] - 1 == selectedPiece.col) //Checks that the selected movement during that turn resulted in an enPassent
                {
                    for (int i = 0; i < player1CapturedPieces.length; i++) //Append the captured piece to player 1's array for captured pieces
                    {
                        if (player1CapturedPieces[i].type.equals("empty"))
                        {
                            player1CapturedPieces[i] = board[selectedPiece.row + 1][selectedPiece.col];
                            board[selectedPiece.row + 1][selectedPiece.col] = emptyPiece;
                            break;
                        }
                    }
                }
            }
            if (player == 2) //If player is player 2
            {
                if (currentPlace[1] + 1 == selectedPiece.col || currentPlace[1] - 1 == selectedPiece.col) //Checks that the selected movement during that turn resulted in an enPassent
                {
                    for (int i = 0; i < player2CapturedPieces.length; i++) //Append the captured piece to player 2's array for captured pieces
                    {
                        if (player1CapturedPieces[i].type.equals("empty"))
                        {
                            player2CapturedPieces[i] = board[selectedPiece.row - 1][selectedPiece.col];
                            board[selectedPiece.row - 1][selectedPiece.col] = emptyPiece;
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void checkCastle (int player, piece[][] board) //Method to check if a castle can be executed during the player's turn
    {
        boolean getCastle = false;
        int chooseCastle;

        if (player == 1 && !kingPlayer1.moved && !check) //Checks that the player's king has not moved and is not currently in check
        {
            if (!rook1Player1.moved && board[7][1].type.equals("empty") && board[7][2].type.equals("empty") && board[7][3].type.equals("empty")) //Checks that the queenside rook has not been moved, and the places between the king and rook are empty
            {
                //Temporarily updates board to see if a castle would result in check
                board[7][2] = kingPlayer1;
                board[7][3] = rook1Player1;
                board[kingPlayer1.row][kingPlayer1.col] = emptyPiece;
                board[rook1Player1.row][rook1Player1.col] = emptyPiece;

                getCastle = !checkIfCheck(player, board); //Checks if a castle can actually be made

                //Resets board values until user decides that they want to castle
                board[7][2] = emptyPiece;
                board[7][3] = emptyPiece;
                board[kingPlayer1.row][kingPlayer1.col] = kingPlayer1;
                board[rook1Player1.row][rook1Player1.col] = rook1Player1;

                if (getCastle) //If a castle can be made, continue
                {
                    System.out.print("Player 1, you have the opportunity to castle queenside. Would you like to? Enter [0] for yes, any other number for no: "); //Prompt user
                    chooseCastle = input.nextInt();
                    if (chooseCastle == 0) //If player chooses to castle
                    {
                        //Update board and object values to reflect the castle
                        board[7][2] = kingPlayer1;
                        board[7][3] = rook1Player1;
                        board[kingPlayer1.row][kingPlayer1.col] = emptyPiece;
                        board[rook1Player1.row][rook1Player1.col] = emptyPiece;
                        kingPlayer1.col = 2;
                        rook1Player1.col = 3;
                    }
                }
            }

            else if (!rook2Player1.moved && board[7][5].type.equals("empty") && board[7][6].type.equals("empty")) //Checks that the kingside rook has not been moved, and the places between the king and rook are empty
            {
                //Temporarily updates board to see if a castle would result in check
                board[7][6] = kingPlayer1;
                board[7][5] = rook2Player1;
                board[kingPlayer1.row][kingPlayer1.col] = emptyPiece;
                board[rook2Player1.row][rook2Player1.col] = emptyPiece;

                getCastle = !checkIfCheck(player, board); //Checks if a castle can actually be made

                //Resets board values until user decides that they want to castle
                board[7][6] = emptyPiece;
                board[7][5] = emptyPiece;
                board[kingPlayer1.row][kingPlayer1.col] = kingPlayer1;
                board[rook2Player1.row][rook2Player1.col] = rook2Player1;

                if (getCastle) //If a castle can be made, continue
                {
                    System.out.print("Player 1, you have the opportunity to castle kingside. Would you like to? Enter [0] for yes, any other number for no: "); //Prompt user
                    chooseCastle = input.nextInt();
                    if (chooseCastle == 0) //If player chooses to castle
                    {
                        //Update board and object values to reflect the castle
                        board[7][6] = kingPlayer1;
                        board[7][5] = rook2Player1;
                        board[kingPlayer1.row][kingPlayer1.col] = emptyPiece;
                        board[rook2Player1.row][rook2Player1.col] = emptyPiece;
                        kingPlayer1.col = 6;
                        rook2Player1.col = 5;
                    }
                }
            }
        }

        else if (player == 2 && !kingPlayer2.moved && !check) //Checks that the player's king has not moved and is not currently in check
        {
            if (!rook1Player2.moved && board[0][1].type.equals("empty") && board[0][2].type.equals("empty") && board[0][3].type.equals("empty")) //Checks that the queenside rook has not been moved, and the places between the king and rook are empty
            {
                //Temporarily updates board to see if a castle would result in check
                board[0][2] = kingPlayer2;
                board[0][3] = rook1Player2;
                board[kingPlayer2.row][kingPlayer2.col] = emptyPiece;
                board[rook1Player2.row][rook1Player2.col] = emptyPiece;

                getCastle = !checkIfCheck(player, board); //Checks if a castle can actually be made

                //Resets board values until user decides that they want to castle
                board[0][2] = emptyPiece;
                board[0][3] = emptyPiece;
                board[kingPlayer2.row][kingPlayer2.col] = kingPlayer2;
                board[rook1Player2.row][rook1Player2.col] = rook1Player2;

                if (getCastle) //If a castle can be made, continue
                {
                    System.out.print("Player 2, you have the opportunity to castle queenside. Would you like to? Enter [0] for yes, any other number for no: "); //Prompt user
                    chooseCastle = input.nextInt();
                    if (chooseCastle == 0) //If player chooses to castle
                    {
                        //Update board and object values to reflect the castle
                        board[0][2] = kingPlayer2;
                        board[0][3] = rook1Player2;
                        board[kingPlayer2.row][kingPlayer2.col] = emptyPiece;
                        board[rook1Player2.row][rook1Player2.col] = emptyPiece;
                        kingPlayer2.col = 2;
                        rook1Player2.col = 3;
                    }
                }
            }

            else if (!rook2Player2.moved && board[0][5].type.equals("empty") && board[0][6].type.equals("empty")) //Checks that the kingside rook has not been moved, and the places between the king and rook are empty
            {
                //Temporarily updates board to see if a castle would result in check
                board[0][6] = kingPlayer2;
                board[0][5] = rook2Player2;
                board[kingPlayer2.row][kingPlayer2.col] = emptyPiece;
                board[rook2Player2.row][rook2Player2.col] = emptyPiece;

                getCastle = !checkIfCheck(player, board); //Checks if a castle can actually be made

                //Resets board values until user decides that they want to castle
                board[0][6] = emptyPiece;
                board[0][5] = emptyPiece;
                board[kingPlayer2.row][kingPlayer2.col] = kingPlayer2;
                board[rook2Player2.row][rook2Player2.col] = rook2Player2;

                if (getCastle) //If a castle can be made, continue
                {
                    System.out.print("Player 2, you have the opportunity to castle kingside. Would you like to? Enter [0] for yes, any other number for no: "); //Prompt user
                    chooseCastle = input.nextInt();
                    if (chooseCastle == 0) //If player chooses to castle
                    {
                        //Update board and object values to reflect the castle
                        board[0][6] = kingPlayer2;
                        board[0][5] = rook2Player2;
                        board[kingPlayer2.row][kingPlayer2.col] = emptyPiece;
                        board[rook2Player2.row][rook2Player2.col] = emptyPiece;
                        kingPlayer2.col = 6;
                        rook2Player2.col = 5;
                    }
                }
            }
        }
    }

    public static void checkPromotion (piece selectedPiece, int[] selectedPlace) //Method to check and execute the promotion when a players pawn has reached the end of the board
    {
        int row = selectedPlace[0];
        int choice = 1;
        boolean getPromote;

        if (selectedPiece.type.equals("pawn") && row == 0) //Check if player 1's pawn has reached the end of the board
        {
            getPromote = true;
        }
        else //Check if player 2's pawn has reached the end of the board
            getPromote = selectedPiece.type.equals("pawn") && row == 7;

        if (getPromote) //If pawn has reached end of the board, give player opportunity to change what the pawn will act as within the game
        {
            System.out.println (ANSI_GREEN + "\nPlayer " + player + ", you have the opportunity to promote the pawn you just moved.");
            System.out.println (ANSI_RESET + "Enter the corresponding number to promote the pawn to a: Queen [1], Knight [2], Rook [3], Bishop [4]");
            do
            {
                choice = input.nextInt();
                if (choice > 1 || choice > 4)
                {
                    System.out.println (ANSI_RED + "Please enter a valid number." + ANSI_RESET);
                }
            } while (choice > 1 || choice > 4);

            if (choice == 1)
            {
                selectedPiece.type = "queen";
                System.out.println("The pawn will now act as a queen.");
            }
            else if (choice == 2)
            {
                selectedPiece.type = "knight";
                System.out.println("The pawn will now act as a knight.");
            }
            else if (choice == 3)
            {
                selectedPiece.type = "rook";
                System.out.println("The pawn will now act as a rook.");
            }
            else
            {
                selectedPiece.type = "bishop";
                System.out.println("The pawn will now act as a bishop.");
            }
        }
    }

    public static boolean checkIfAllMovesResultInCheck (int player, piece [][] board) //Redirective method that checks if all moves possible will put the player in or out of check (essential for checking for Check Mate and Stale Mate)
    {
        boolean allMovesResultInCheck = true, pieceMovesResultInCheck = false;
        piece checkedPiece;
        piece [] checkedPieces = {emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece, emptyPiece};

        if (player == 1) //Set up values according to player 1's turn
        {
            for (int i = 0; i < board.length; i++) //Traverse board to find all player 1's pieces
            {
                for (int a = 0; a < board[0].length; a++)
                {
                    if (board[i][a].owner == 1) //If a piece owned by player 1 is found
                    {
                        for (int index = 0; index < checkedPieces.length; index++)
                        {
                            if (checkedPieces[index].type.equals("empty"))
                            {
                                checkedPieces[index] = board[i][a]; //Append player 1's pieces to a list to be checked one by one
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if (player == 2) //Set up values according to player 2's turn
        {
            for (int i = 0; i < board.length; i++) //Traverse board to find all player 2's pieces
            {
                for (int a = 0; a < board[0].length; a++)
                {
                    if (board[i][a].owner == 2) //If a piece owned by player 2 is found
                    {
                        for (int index = 0; index < checkedPieces.length; index++)
                        {
                            if (checkedPieces[index].type.equals("empty"))
                            {
                                checkedPieces[index] = board[i][a]; //Append player 2's pieces to a list to be checked one by one
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < checkedPieces.length; i++) //Traverse the checkedPieces list to check all of the current player's pieces, one by one, to see if all of their possible movements will put the player in or out of check
        {
            checkedPiece = checkedPieces[i];

            //Redirect to a method specific to the type of piece currently being checked
            if (checkedPieces[i].type.equals("pawn"))
            {
                pieceMovesResultInCheck = checkAllPawnMoves (player, checkedPiece);
            }
            else if (checkedPieces[i].type.equals("rook"))
            {
                pieceMovesResultInCheck = checkAllRookMoves (player, checkedPiece);
            }
            else if (checkedPieces[i].type.equals("knight"))
            {
                pieceMovesResultInCheck = checkAllKnightMoves (player, checkedPiece);
            }
            else if (checkedPieces[i].type.equals("bishop"))
            {
                pieceMovesResultInCheck =  checkAllBishopMoves (player, checkedPiece);
            }
            else if (checkedPieces[i].type.equals("queen"))
            {
                pieceMovesResultInCheck = checkAllQueenMoves (player, checkedPiece);
            }
            else if (checkedPieces[i].type.equals("king"))
            {
                pieceMovesResultInCheck = checkAllKingMoves (player, checkedPiece);
            }

            if (! pieceMovesResultInCheck) //If any of the currently checked piece's possible moves do not result in check or do put the current player out of check, allMovesResultInCheck is not true (no Check Mate or Stale Mate)
            {
                allMovesResultInCheck = false;
                break;
            }
        }

        return allMovesResultInCheck;
    }

    public static boolean checkAllPawnMoves (int player, piece checkedPiece) //Redirects from checkIfAllMovesResultInCheck (if the checked piece is a pawn, check if all their possible moves will put or keep the current player in check)
    {
        boolean allPawnMovesResultInCheck = true, breakLoop = false;
        int [] checkedPlace = new int [2];
        piece displacedPiece = null;

        for (int i = 0; i < board.length; i++)
        {
            checkedPlace[0] = i;
            for (int a = 0; a < board[0].length; a++) //Traverse all squares on the board
            {
                checkedPlace[1] = a;

                if (checkIfPawnMove(player, board, checkedPiece, checkedPlace)) //Check if the pawn can move to the currently checked space on the board
                {
                    //If the pawn can move, update the board temporarily
                    board[checkedPiece.row][checkedPiece.col] = emptyPiece;
                    displacedPiece = board[checkedPlace[0]][checkedPlace[1]];
                    board[checkedPlace[0]][checkedPlace[1]] = checkedPiece;

                    if (!checkIfCheck(player, board)) //Check if the new position of the checked pawn will put the player in check
                    {
                        allPawnMovesResultInCheck = false;
                        breakLoop = true;
                        //Reset the board back to what it was
                        board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                        board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                        break;
                    }

                    //Reset the board back to what it was before the checking
                    board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                    board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                }
            }
            if (breakLoop)
            {
                break;
            }
        }

        return allPawnMovesResultInCheck;
    }

    public static boolean checkAllRookMoves (int player, piece checkedPiece) //Redirects from checkIfAllMovesResultInCheck (if the checked piece is a rook, check if all their possible moves will put or keep the current player in check)
    {
        boolean allRookMovesResultInCheck = true, breakLoop = false;
        int [] checkedPlace = new int [2];
        piece displacedPiece;

        for (int i = 0; i < board.length; i++)
        {
            checkedPlace[0] = i;
            for (int a = 0; a < board[0].length; a++) //Traverse all squares on the board
            {
                checkedPlace[1] = a;

                if (!(checkedPlace[0] == checkedPiece.row || checkedPlace[1] == checkedPiece.col))
                {
                    if (checkIfRookMove(player, board, checkedPiece, checkedPlace)) //Check if the rook can move to the currently checked space on the board
                    {
                        //If the rook can move, update the board temporarily
                        board[checkedPiece.row][checkedPiece.col] = emptyPiece;
                        displacedPiece = board[checkedPlace[0]][checkedPlace[1]];
                        board[checkedPlace[0]][checkedPlace[1]] = checkedPiece;

                        if (!checkIfCheck(player, board)) //Check if the new position of the checked rook will put the player in check
                        {
                            allRookMovesResultInCheck = false;
                            breakLoop = true;
                            //Reset the board back to what it was before the checking
                            board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                            board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                            break;
                        }

                        //Reset the board back to what it was before the checking
                        board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                        board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                    }
                }
            }
            if (breakLoop)
            {
                break;
            }
        }

        return allRookMovesResultInCheck;
    }

    public static boolean checkAllKnightMoves (int player, piece checkedPiece) //Redirects from checkIfAllMovesResultInCheck (if the checked piece is a knight, check if all their possible moves will put or keep the current player in check)
    {
        boolean allKnightMovesResultInCheck = true, breakLoop = false;
        int [] checkedPlace = new int [2];
        piece displacedPiece;

        for (int i = 0; i < board.length; i++)
        {
            checkedPlace[0] = i;
            for (int a = 0; a < board[0].length; a++) //Traverse all squares on board
            {
                checkedPlace[1] = a;

                if (!(checkedPlace[0] == checkedPiece.row || checkedPlace[1] == checkedPiece.col))
                {
                    if (checkIfKnightMove(player, board, checkedPiece, checkedPlace)) //Check if the knight can actually move to the currently checked space on the board
                    {
                        //If the knight can move, update the board temporarily
                        board[checkedPiece.row][checkedPiece.col] = emptyPiece;
                        displacedPiece = board[checkedPlace[0]][checkedPlace[1]];
                        board[checkedPlace[0]][checkedPlace[1]] = checkedPiece;

                        if (!checkIfCheck(player, board)) //Check if the new position of the checked knight will put the player in check
                        {
                            allKnightMovesResultInCheck = false;
                            breakLoop = true;
                            //Reset the board to what is was before checking
                            board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                            board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                            break;
                        }

                        //Reset the board to what is was before checking
                        board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                        board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                    }
                }
            }
            if (breakLoop)
            {
                break;
            }
        }

        return allKnightMovesResultInCheck;
    }

    public static boolean checkAllBishopMoves (int player, piece checkedPiece) //Redirects from checkIfAllMovesResultInCheck (if the checked piece is a pawn, check if all their possible moves will put or keep the current player in check)
    {
        boolean allBishopMovesResultInCheck = true, breakLoop = false;
        int [] checkedPlace = new int [2];
        piece displacedPiece;

        for (int i = 0; i < board.length; i++)
        {
            checkedPlace[0] = i;
            for (int a = 0; a < board[0].length; a++) //Traverse all squares on the board
            {
                checkedPlace[1] = a;

                if (!(checkedPlace[0] == checkedPiece.row || checkedPlace[1] == checkedPiece.col))
                {
                    if (checkIfBishopMove(player, board, checkedPiece, checkedPlace)) //Check if the bishop can move to the currently checked square on the board
                    {
                        //If the bishop can move, temporarily update the board
                        board[checkedPiece.row][checkedPiece.col] = emptyPiece;
                        displacedPiece = board[checkedPlace[0]][checkedPlace[1]];
                        board[checkedPlace[0]][checkedPlace[1]] = checkedPiece;

                        if (!checkIfCheck(player, board)) //Check if the new position of the bishop will put the player in check
                        {
                            allBishopMovesResultInCheck = false;
                            breakLoop = true;
                            //Reset the board to what it was before checking
                            board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                            board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                            break;
                        }

                        //Reset the board to what it was before checking
                        board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                        board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                    }
                }
            }
            if (breakLoop)
            {
                break;
            }
        }

        return allBishopMovesResultInCheck;
    }

    public static boolean checkAllQueenMoves (int player, piece checkedPiece) //Redirects from checkIfAllMovesResultInCheck (if the checked piece is a queen, check if all their possible moves will put or keep the current player in check)
    {
        boolean allQueenMovesResultInCheck = true, breakLoop = false;
        int [] checkedPlace = new int [2];
        piece displacedPiece;

        for (int i = 0; i < board.length; i++)
        {
            checkedPlace[0] = i;
            for (int a = 0; a < board[0].length; a++) //Traverse all squares on the board
            {
                checkedPlace[1] = a;

                if (!(checkedPlace[0] == checkedPiece.row || checkedPlace[1] == checkedPiece.col))
                {
                    if (checkIfQueenMove(player, board, checkedPiece, checkedPlace)) //Check if the queen can actually move to the checked square on the board
                    {
                        //If the queen can move to the space, update the board temporarily
                        board[checkedPiece.row][checkedPiece.col] = emptyPiece;
                        displacedPiece = board[checkedPlace[0]][checkedPlace[1]];
                        board[checkedPlace[0]][checkedPlace[1]] = checkedPiece;

                        if (!checkIfCheck(player, board)) //Check if the new position of the queen will put the player in check
                        {
                            allQueenMovesResultInCheck = false;
                            breakLoop = true;
                            //Reset the board to what it was before checking
                            board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                            board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                            break;
                        }

                        //Reset the board to what it was before checking
                        board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                        board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                    }
                }
            }
            if (breakLoop)
            {
                break;
            }
        }

        return allQueenMovesResultInCheck;
    }

    public static boolean checkAllKingMoves (int player, piece checkedPiece) //Redirects from checkIfAllMovesResultInCheck (if the checked piece is a king, check if all their possible moves will put or keep the current player in check)
    {
        boolean allKingMovesResultInCheck = true, breakLoop = false;
        int [] checkedPlace = new int [2];
        piece displacedPiece;

        for (int i = 0; i < board.length; i++)
        {
            checkedPlace[0] = i;
            for (int a = 0; a < board[0].length; a++) //Traverse all squares on the board
            {
                checkedPlace[1] = a;

                if (!(checkedPlace[0] == checkedPiece.row || checkedPlace[1] == checkedPiece.col))
                {
                    if (checkIfKingMove(player, board, checkedPiece, checkedPlace)) //Check if the king can move to the checked square on the board
                    {
                        //If the king can move, update the board temporarily
                        board[checkedPiece.row][checkedPiece.col] = emptyPiece;
                        displacedPiece = board[checkedPlace[0]][checkedPlace[1]];
                        board[checkedPlace[0]][checkedPlace[1]] = checkedPiece;

                        if (!checkIfCheck(player, board)) //Check if the new position of the king will put the player in check
                        {
                            allKingMovesResultInCheck = false;
                            breakLoop = true;
                            //Reset the board to what is was before the checking
                            board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                            board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                            break;
                        }

                        //Reset the board to what is was before the checking
                        board[checkedPiece.row][checkedPiece.col] = checkedPiece;
                        board[checkedPlace[0]][checkedPlace[1]] = displacedPiece;
                    }
                }
            }
            if (breakLoop)
            {
                break;
            }
        }

        return allKingMovesResultInCheck;
    }

    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited (MouseEvent e){}

    public void mouseDragged(MouseEvent e){
        setMousePosition(e);
    }

    public void mouseMoved(MouseEvent e){}

    public void mouseClicked(MouseEvent e){
        setMousePosition(e);
        repaint();
        if(mouseX > 700 && mouseY > 250 && mouseX < 800 && mouseY < 300){

            menu = 2;
            playMoved += 1;
        }
        if(mouseX > 500 && mouseY > 450 &&  mouseX < 900 && mouseY < 500){

            menu = 3;
            instructionMoved += 1;
        }
        if(mouseX > 700 && mouseY > 650 &&  mouseX < 900 && mouseY < 750){

            menu = 1;
            menuMoved += 1;
        }
        if (mouseX > 795 && mouseY > 480 && mouseX < 980 && mouseY < 505){
            menu = 4;
            instructionMoved2 += 1;
        }
        if (mouseX > 795 && mouseY > 580 && mouseX < 980 && mouseY < 605){
            menu = 5;
            instructionMoved3 += 1;
        }

    }

    public void setMousePosition(MouseEvent e){
        mouseX = e.getX();
        mouseY = e.getY();
    }


} //PIECES CLASS

class piece
{
    String type;
    boolean alive;
    boolean moved;
    int owner;
    int moveValue;
    String colourSquare;
    int row;
    int col;
    BufferedImage image;


    public piece(String type, boolean alive, boolean moved, int owner, int moveValue, String colourSquare, int row, int col, BufferedImage image)
    {
        this.type = type;
        this.alive = alive;
        this.owner = owner;
        this.moved = moved;
        this.moveValue = moveValue;
        this.colourSquare = colourSquare;
        this.row = row;
        this.col = col;
        this.image = image;
    }
}