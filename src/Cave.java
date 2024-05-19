public class Cave extends BattleLoc{

    public Cave(Player player) {
        super(player,"Mağara",new Zombie(),"food",6);
    }

    public static String Weapon(){
        int randNumber = (int) (0 + Math.random() * 100);

        if (randNumber < 20){
            return "Tüfek";
        } else if (randNumber < 50) {
            return "Kılıç";
        } else if (randNumber <= 100) {
            return "Tabanca";
        }

        return "Weapon Yok";
    }






}