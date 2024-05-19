public class Snake extends Obstacle{
    public Snake() {
        super(4,"Yılan",snakeDamage(),12,0);
    }

    public static int snakeDamage(){
        return (int) (3 + Math.random() * 4);
    }

}