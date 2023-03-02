public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Hippodrome hippodrome = new Hippodrome(new StandardHorseFactory(), 10, 10000);
        hippodrome.start();
    }
}
