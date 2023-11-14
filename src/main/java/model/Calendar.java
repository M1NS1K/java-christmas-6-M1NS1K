package model;

public enum Calendar {
    SPECIAL_DAY("특별 할인", new int[]{3, 10, 17, 24, 25, 31});



    private final String EVENT;
    private final int[] DAYS;
    Calendar(String event, int[] days) {
        this.EVENT = event;
        this.DAYS = days;
    }

    public int getDiscount(int day) {

    }


}
