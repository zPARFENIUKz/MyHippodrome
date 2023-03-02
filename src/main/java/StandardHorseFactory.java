import java.util.Random;

public class StandardHorseFactory implements HorseFactory {

    protected final static Integer MIN_SPEED = 100;
    protected final static Integer MAX_SPEED = 300;
    protected static Integer num = 0;
    protected final static Random random = new Random();
    @Override
    public Horse getHorse() {
        ++num;
        return new Horse(num.toString(), random.nextInt(MIN_SPEED, MAX_SPEED + 1));
    }
}
