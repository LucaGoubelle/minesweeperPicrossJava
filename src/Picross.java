import java.util.Locale;
import java.util.Random;

public class Picross {
    public static String mine = Hud.red +"*";
    public static String hider = Hud.green+"#";
    public static String disabled = Hud.green+"v";
    public static String[][] matrix = {
            {" "," "," "," "," "},
            {" "," "," "," "," "},
            {" "," "," "," "," "},
            {" "," "," "," "," "},
            {" "," "," "," "," "}
    };
    public static String[][] winMatrix = {
            {" "," "," "," "," "},
            {" "," "," "," "," "},
            {" "," "," "," "," "},
            {" "," "," "," "," "},
            {" "," "," "," "," "}
    };
    public static String[][] hiddenMatrix = {
            {hider,hider,hider,hider,hider},
            {hider,hider,hider,hider,hider},
            {hider,hider,hider,hider,hider},
            {hider,hider,hider,hider,hider},
            {hider,hider,hider,hider,hider}
    };
    public static String[][] gameOverMatrix = {
            {hider,hider,hider,hider,hider},
            {hider,hider,hider,hider,hider},
            {hider,hider,hider,hider,hider},
            {hider,hider,hider,hider,hider},
            {hider,hider,hider,hider,hider}
    };

    public static void generateMines(int nbMines){
        for(int i=0;i<nbMines;i++){
            int x = new Random().nextInt(5), y = new Random().nextInt(5);
            matrix[y][x] = mine; gameOverMatrix[y][x] = mine; winMatrix[y][x] = disabled;
        }
    }

    public static boolean isMine(int x, int y){
        if(matrix[y-1][x-1]==mine)
            return true;
        else
            return false;
    }

    public static void sweep(int x, int y){
        hiddenMatrix[y-1][x-1] = matrix[y-1][x-1];
        gameOverMatrix[y-1][x-1] = matrix[y-1][x-1];
    }

    public static int[] entry(String ip){
        int x=0,y=0;
        int[] coord={0,0};
        ip = ip.toUpperCase(Locale.ROOT);
        switch(ip){
            case "A1": x=1;y=1; break;
            case "A2": x=1;y=2; break;
            case "A3": x=1;y=3; break;
            case "A4": x=1;y=4; break;
            case "A5": x=1;y=5; break;
            case "B1": x=2;y=1; break;
            case "B2": x=2;y=2; break;
            case "B3": x=2;y=3; break;
            case "B4": x=2;y=4; break;
            case "B5": x=2;y=5; break;
            case "C1": x=3;y=1; break;
            case "C2": x=3;y=2; break;
            case "C3": x=3;y=3; break;
            case "C4": x=3;y=4; break;
            case "C5": x=3;y=5; break;
            case "D1": x=4;y=1; break;
            case "D2": x=4;y=2; break;
            case "D3": x=4;y=3; break;
            case "D4": x=4;y=4; break;
            case "D5": x=4;y=5; break;
            case "E1": x=5;y=1; break;
            case "E2": x=5;y=2; break;
            case "E3": x=5;y=3; break;
            case "E4": x=5;y=4; break;
            case "E5": x=5;y=5; break;
            default: x=0;y=0; break;
        }
        coord[0] = x;
        coord[1] = y;
        return coord;
    }

    public static int countMines(){
        int nbMineInField=0;
        for(String[] row: matrix){
            for(String place: row){
                if(place==mine)
                    nbMineInField++;
            }
        }
        return nbMineInField;
    }

    public static boolean isWin(){
        int nbMinesInField = Picross.countMines(), nbPlace = 25;
        int nbPlaceToFlush = nbPlace - nbMinesInField, nbPlaceFlushed=0;
        for(String[] row: hiddenMatrix){
            for(String place: row){
                if(place==" ")
                    nbPlaceFlushed++;
            }
        }
        if(nbPlaceFlushed==nbPlaceToFlush)
            return true;
        else
            return false;
    }

    public static void showClues(){
        int r1=0, r2=0, r3=0, r4=0, r5=0;
        int c1=0, c2=0, c3=0, c4=0, c5=0;
        //rows
        for(int i=0;i<5;i++){
            if(matrix[0][i]==mine)
                r1++;
            if(matrix[1][i]==mine)
                r2++;
            if(matrix[2][i]==mine)
                r3++;
            if(matrix[3][i]==mine)
                r4++;
            if(matrix[4][i]==mine)
                r5++;
        }
        //cols
        for(int i=0;i<5;i++){
            if(matrix[i][0]==mine)
                c1++;
            if(matrix[i][1]==mine)
                c2++;
            if(matrix[i][2]==mine)
                c3++;
            if(matrix[i][3]==mine)
                c4++;
            if(matrix[i][4]==mine)
                c5++;
        }
        System.out.println(Hud.white+"ROW1 : "+Hud.cyan+String.valueOf(r1));
        System.out.println(Hud.white+"ROW2 : "+Hud.cyan+String.valueOf(r2));
        System.out.println(Hud.white+"ROW3 : "+Hud.cyan+String.valueOf(r3));
        System.out.println(Hud.white+"ROW4 : "+Hud.cyan+String.valueOf(r4));
        System.out.println(Hud.white+"ROW5 : "+Hud.cyan+String.valueOf(r5));
        System.out.print("\n");
        System.out.println(Hud.white+"COL1 : "+Hud.cyan+String.valueOf(c1));
        System.out.println(Hud.white+"COL2 : "+Hud.cyan+String.valueOf(c2));
        System.out.println(Hud.white+"COL3 : "+Hud.cyan+String.valueOf(c3));
        System.out.println(Hud.white+"COL4 : "+Hud.cyan+String.valueOf(c4));
        System.out.println(Hud.white+"COL5 : "+Hud.cyan+String.valueOf(c5));
    }

    public static void showGrid(){
        System.out.print("\n");
        System.out.println(Hud.white+"   A   B   C   D   E  x");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"1| "+hiddenMatrix[0][0]+Hud.white+" | "+hiddenMatrix[0][1]+Hud.white+" | "+hiddenMatrix[0][2]+Hud.white+" | "+hiddenMatrix[0][3]+Hud.white+" | "+hiddenMatrix[0][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"2| "+hiddenMatrix[1][0]+Hud.white+" | "+hiddenMatrix[1][1]+Hud.white+" | "+hiddenMatrix[1][2]+Hud.white+" | "+hiddenMatrix[1][3]+Hud.white+" | "+hiddenMatrix[1][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"3| "+hiddenMatrix[2][0]+Hud.white+" | "+hiddenMatrix[2][1]+Hud.white+" | "+hiddenMatrix[2][2]+Hud.white+" | "+hiddenMatrix[2][3]+Hud.white+" | "+hiddenMatrix[2][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"4| "+hiddenMatrix[3][0]+Hud.white+" | "+hiddenMatrix[3][1]+Hud.white+" | "+hiddenMatrix[3][2]+Hud.white+" | "+hiddenMatrix[3][3]+Hud.white+" | "+hiddenMatrix[3][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"5| "+hiddenMatrix[4][0]+Hud.white+" | "+hiddenMatrix[4][1]+Hud.white+" | "+hiddenMatrix[4][2]+Hud.white+" | "+hiddenMatrix[4][3]+Hud.white+" | "+hiddenMatrix[4][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"y");
        System.out.print("\n");
    }

    public static void showGameOverGrid(){
        System.out.print("\n");
        System.out.println(Hud.red+"GAME OVER");
        System.out.print("\n");
        System.out.println(Hud.white+"   A   B   C   D   E  x");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"1| "+gameOverMatrix[0][0]+Hud.white+" | "+gameOverMatrix[0][1]+Hud.white+" | "+gameOverMatrix[0][2]+Hud.white+" | "+gameOverMatrix[0][3]+Hud.white+" | "+gameOverMatrix[0][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"2| "+gameOverMatrix[1][0]+Hud.white+" | "+gameOverMatrix[1][1]+Hud.white+" | "+gameOverMatrix[1][2]+Hud.white+" | "+gameOverMatrix[1][3]+Hud.white+" | "+gameOverMatrix[1][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"3| "+gameOverMatrix[2][0]+Hud.white+" | "+gameOverMatrix[2][1]+Hud.white+" | "+gameOverMatrix[2][2]+Hud.white+" | "+gameOverMatrix[2][3]+Hud.white+" | "+gameOverMatrix[2][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"4| "+gameOverMatrix[3][0]+Hud.white+" | "+gameOverMatrix[3][1]+Hud.white+" | "+gameOverMatrix[3][2]+Hud.white+" | "+gameOverMatrix[3][3]+Hud.white+" | "+gameOverMatrix[3][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"5| "+gameOverMatrix[4][0]+Hud.white+" | "+gameOverMatrix[4][1]+Hud.white+" | "+gameOverMatrix[4][2]+Hud.white+" | "+gameOverMatrix[4][3]+Hud.white+" | "+gameOverMatrix[4][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"y");
        System.out.print("\n");
    }

    public static void showWinGrid(){
        System.out.print("\n");
        System.out.println(Hud.green+"CONGRATULATION !");
        System.out.print("\n");
        System.out.println(Hud.white+"   A   B   C   D   E  x");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"1| "+winMatrix[0][0]+Hud.white+" | "+winMatrix[0][1]+Hud.white+" | "+winMatrix[0][2]+Hud.white+" | "+winMatrix[0][3]+Hud.white+" | "+winMatrix[0][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"2| "+winMatrix[1][0]+Hud.white+" | "+winMatrix[1][1]+Hud.white+" | "+winMatrix[1][2]+Hud.white+" | "+winMatrix[1][3]+Hud.white+" | "+winMatrix[1][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"3| "+winMatrix[2][0]+Hud.white+" | "+winMatrix[2][1]+Hud.white+" | "+winMatrix[2][2]+Hud.white+" | "+winMatrix[2][3]+Hud.white+" | "+winMatrix[2][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"4| "+winMatrix[3][0]+Hud.white+" | "+winMatrix[3][1]+Hud.white+" | "+winMatrix[3][2]+Hud.white+" | "+winMatrix[3][3]+Hud.white+" | "+winMatrix[3][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"5| "+winMatrix[4][0]+Hud.white+" | "+winMatrix[4][1]+Hud.white+" | "+winMatrix[4][2]+Hud.white+" | "+winMatrix[4][3]+Hud.white+" | "+winMatrix[4][4]+Hud.white+" |");
        System.out.println(Hud.white+" |---|---|---|---|---|");
        System.out.println(Hud.white+"y");
        System.out.print("\n");
    }

}