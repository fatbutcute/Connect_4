package org.example;

import java.util.Scanner;
public class Connect_4 {

    //A játékosnév megadása!//
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Adjon meg egy játékos nevet: ");
        String jatekos = in.nextLine();

        char[][] racs = new char[6][7];

        //Ez felel a táblák elkészítéséért.//
        for (int sorok = 0; sorok < racs.length; sorok++){
            for (int oszlop = 0; oszlop < racs[0].length; oszlop++){
                racs[sorok][oszlop] = ' ';
            }
        }

        int turn = 1;
        char player = 'R';
        boolean winner = false;

        //Ez felel a kör inditásáért, illetve, hogy az tovább haladjon!//
        while (winner == false && turn <= 42){
            boolean legalismozdulat;
            int play;
            do {
                display(racs);

                System.out.print("Játékos" + jatekos + ", válasszon egy oszlopot: ");
                play = in.nextInt();

                //Helyes volt-e az adott mozdulat?//
                legalismozdulat = validate(play,racs);

            }while (legalismozdulat == false);

            for (int sorok = racs.length-1; sorok >= 0; sorok--){
                if(racs[sorok][play] == ' '){
                    racs[sorok][play] = player;
                    break;
                }
            }

            //Ellenőrzi, hogy van-e nyertes?//
            winner = isWinner(player,racs);

            //Játékosok cseréje.//
            if (player == 'R'){
                player = 'B';
            }else{
                player = 'R';
            }

            turn++;
        }
        display(racs);

        if (winner){
            if (player=='R'){
                System.out.println("A gép nyert!");
            }else{
                System.out.println("A játékos nyert!");
            }
        }else{
            System.out.println("Döntetlen mérkőzés!");
        }

    }

   //A racsos megjelenéséért felelő kódrészlet!//
    public static void display(char[][] racs){
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println("---------------");
        for (int sorok = 0; sorok < racs.length; sorok++){
            System.out.print("|");
            for (int oszlop = 0; oszlop < racs[0].length; oszlop++){
                System.out.print(racs[sorok][oszlop]);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("---------------");
        }
        System.out.println(" 0 1 2 3 4 5 6");
        System.out.println();
    }

    public static boolean validate(int oszlopumn, char[][] racs){
        //Használható-e a megadott szám, (0 és 7 között kell lennie)?
        if (oszlopumn < 0 || oszlopumn > racs[0].length){
            return false;
        }

        //Ellenőrzi hogy tele van-e az adott oszlop?
        if (racs[0][oszlopumn] != ' '){
            return false;
        }

        return true;
    }

    public static boolean isWinner(char player, char[][] racs){
        //Lefut, ellenőrzi, hogy van-e épp győztes, ha nincs, a játék folytatódik!//
        for(int sorok = 0; sorok<racs.length; sorok++){
            for (int oszlop = 0;oszlop < racs[0].length - 3;oszlop++){
                if (racs[sorok][oszlop] == player   &&
                        racs[sorok][oszlop+1] == player &&
                        racs[sorok][oszlop+2] == player &&
                        racs[sorok][oszlop+3] == player){
                    return true;
                }
            }
        }
        //függőlegesen ellenőrzi, hogy a gép vagy a játékosnak függőlegesen megvan-e a 4 korong.//
        for(int sorok = 0; sorok < racs.length - 3; sorok++){
            for(int oszlop = 0; oszlop < racs[0].length; oszlop++){
                if (racs[sorok][oszlop] == player   &&
                        racs[sorok+1][oszlop] == player &&
                        racs[sorok+2][oszlop] == player &&
                        racs[sorok+3][oszlop] == player){
                    return true;
                }
            }
        }
        //átlós ellenőzés, hogy megvan-e a 4db korong (felfele)!//
        for(int sorok = 3; sorok < racs.length; sorok++){
            for(int oszlop = 0; oszlop < racs[0].length - 3; oszlop++){
                if (racs[sorok][oszlop] == player   &&
                        racs[sorok-1][oszlop+1] == player &&
                        racs[sorok-2][oszlop+2] == player &&
                        racs[sorok-3][oszlop+3] == player){
                    return true;
                }
            }
        }
        //átlósan ellenőriz, hogy meg van-e a 4 db korong (lefele)!//
        for(int sorok = 0; sorok < racs.length - 3; sorok++){
            for(int oszlop = 0; oszlop < racs[0].length - 3; oszlop++){
                if (racs[sorok][oszlop] == player   &&
                        racs[sorok+1][oszlop+1] == player &&
                        racs[sorok+2][oszlop+2] == player &&
                        racs[sorok+3][oszlop+3] == player){
                    return true;
                }
            }
        }
        return false;
    }
}