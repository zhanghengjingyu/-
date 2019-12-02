package TaskSchdeuler;

public class SchoolRoom {
    private int id;
    private long schoolId;
    private String place;
    private int SeatNum;
    private long paperId;
    public SchoolRoom() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getSeatNum() {
        return SeatNum;
    }

    public void setSeatNum(int seatNum) {
        SeatNum = seatNum;
    }

    public long getPaperId() {
        return paperId;
    }

    public void setPaperId(long paperId) {
        this.paperId = paperId;
    }
}
