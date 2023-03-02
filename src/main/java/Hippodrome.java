import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Hippodrome {
    protected final HorseFactory horseFactory;
    protected final Integer countOfMembers;
    protected final Integer distance;
    protected final AtomicInteger number = new AtomicInteger(1);

    protected final Integer HORSE_SLEEP_DELAY = 100;


    public Hippodrome(HorseFactory horseFactory, Integer countOfMembers, Integer distance) {
        this.horseFactory = horseFactory;
        this.countOfMembers = countOfMembers;
        if (distance <= 0) throw new IllegalArgumentException("distance must be greater than 0");
        this.distance = distance;
    }

    @SuppressWarnings("BusyWait")
    public void start() throws InterruptedException {
        final ExecutorService executorService = Executors.newFixedThreadPool(countOfMembers);
        final List<Callable<HorsePosition>> horsesRacesTasks = new ArrayList<>(countOfMembers);
        for (int i = 0; i < countOfMembers; ++i) {
            final Horse horse = horseFactory.getHorse();
            final Callable<HorsePosition> task = () -> {
                while (true) {
                    try {
                        if (horse.getDistance() >= distance) {
                            break;
                        }
                        horse.setDistance(horse.getDistance() + horse.getSpeed());
                        Thread.sleep(HORSE_SLEEP_DELAY);
                    } catch (InterruptedException e) {
                        System.out.printf("Лошадь %s не добежала до финиша\n", horse.getName());
                    }
                }
                final HorsePosition horsePosition = new HorsePosition(horse, number.getAndIncrement());
                System.out.println(horsePosition);
                return horsePosition;
            };
            horsesRacesTasks.add(task);
        }
        executorService.invokeAll(horsesRacesTasks);
        executorService.shutdown();
    }

    private static class HorsePosition {
        private final Horse horse;
        private final Integer position;

        public HorsePosition(Horse horse, Integer position) {
            this.horse = horse;
            this.position = position;
        }

        @SuppressWarnings("unused")
        public Horse getHorse() {
            return horse;
        }

        @Override
        public String toString() {
            return "" +
                    "Лошадь " + horse.getName() +
                    " пришла " + position +
                    'й';
        }
    }


}
