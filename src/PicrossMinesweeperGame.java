import java.util.Scanner;

public class PicrossMinesweeperGame {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String state = "";
        int[] crd;
        while(true){
            Picross.generateMines(5);
            Picross.showGrid();
            Hud.doubleSpace();
            Picross.showClues();
            Hud.doubleSpace();
            while(true){
                System.out.println(Hud.yellow+"Enter coordinate(A1,B2,C3...) "+Hud.white+": "+Hud.cyan);
                String str = sc.nextLine();
                crd = Picross.entry(str);
                if(crd[0]!=0 && crd[1]!=0)
                    break;
                System.out.println(Hud.red+"ERR: WRONG COORDINATE");
            }
            Picross.sweep(crd[0],crd[1]);
            boolean gameOver = Picross.isMine(crd[0],crd[1]);
            boolean winning = Picross.isWin();
            if(gameOver==true){
                state = "loose";
                break;
            }
            if(winning==true){
                state = "win";
                break;
            }
            Hud.spaceClear();
        }
        Hud.spaceClear();
        switch (state){
            case "win": Picross.showWinGrid(); break;
            case "loose": Picross.showGameOverGrid(); break;
        }
        sc.nextLine();
    }
}
