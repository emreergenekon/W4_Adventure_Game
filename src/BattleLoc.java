import java.util.*;
public abstract class BattleLoc extends Location{

    private Obstacle obstacle;
    private String award;
    private int maxObstacle; // canavar sayısı için
    public BattleLoc(Player player,String name,Obstacle obstacle,String award,int maxObstacle) {
        super(player,name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {

        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız : "+this.getName());
        System.out.println("Dikkatli ol burada " +obsNumber+" tane "+this.getObstacle().getName()+" yaşıyor");
        System.out.println("<S>avaş veya <K>aç");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S")){

            if (combat(obsNumber)){
                System.out.println(this.getName()+" tüm düşmanları yendiniz");
                return true;
            }
        }

        if (this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber){

        for (int i=1;i<= obsNumber;i++){

            this.getObstacle().setHealth(this.getObstacle().getDefHealth());
            playerStats();
            obstacStats();

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("<V>ur veya <K>aç : ");
                String selectCombat = input.nextLine();
                if (selectCombat.equals("V")){

                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    System.out.println("Siz vurdunuz");
                    afterHit();

                    if (this.getObstacle().getHealth() > 0){

                        System.out.println();
                        System.out.println("Canavar size vurdu");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }

                } else {
                    return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz");
                System.out.println(this.getObstacle().getAward()+" para kazandınız");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getObstacle().getAward());
                System.out.println("Güncel paranız : "+this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Canınız : "+this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName()+"Canı : "+this.getObstacle().getHealth());
        System.out.println();
    }


    public void playerStats(){

        System.out.println("Oyuncu Değerleri");
        System.out.println("----------------");
        System.out.println("Sağlık "+this.getPlayer().getHealth());
        System.out.println("Silah "+this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar "+this.getPlayer().getTotalDamage());
        System.out.println("Zırh "+this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama "+this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para "+this.getPlayer().getMoney());
    }

    public void obstacStats(){

        System.out.println(this.getObstacle().getName()+" Değerleri");
        System.out.println("---------------------------------------");
        System.out.println("Sağlık "+this.getObstacle().getHealth());
        System.out.println("Hasar "+this.getObstacle().getDamage());
        System.out.println("Ödülü "+this.getObstacle().getAward());
    }


    public int randomObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}