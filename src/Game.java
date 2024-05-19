import java.util.*;
public class Game {

    private Scanner input = new Scanner(System.in);

    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz");
        System.out.print("Lütfen bir isim giriniz : ");
        //String playerName = input.nextLine();
        Player player = new Player("Fuat");
        System.out.println(player.getName()+" Hoşgeldin");
        System.out.println("Lütfen bir karakter seçiniz");
        player.selectChar();

        Location location = null;
        while (true){
            player.printInfo();
            System.out.println();
            System.out.println("Bölgeler");
            System.out.println();
            System.out.println("1- Güvenli Ev");
            System.out.println("2- Mağaza");
            System.out.println("3- Mağaraya Git");
            System.out.println("4- Ormana Git");
            System.out.println("5- Nehire Git");
            System.out.println("6- Zindana Git");
            System.out.println("0- Çıkış Yap");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();

            switch (selectLoc){

                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                case 6:
                    location = new Dungeon(player);
                    break;

                default:
                    location = new SafeHouse(player);
            }

            if (location == null){
                System.out.println("Oyun bitti");
                break;
            }

            if (!location.onLocation()){
                System.out.println("Game Over");
                break;
            }
        }

    }

}