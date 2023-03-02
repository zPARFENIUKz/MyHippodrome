public class Horse {
    protected String name;
    protected Integer speed;
    protected Integer distance = 0;

    public Horse(String name, Integer speed) {
        this.name = name;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
