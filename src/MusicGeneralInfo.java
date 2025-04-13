public abstract class MusicGeneralInfo implements CollectionsOperations {
    private String name;
    private int year;

    public MusicGeneralInfo(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return this.name;
    }

}
