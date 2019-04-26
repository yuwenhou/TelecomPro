package bean;

/**
 * 该类用于存放用户请求的数据
 */
public class QueryInfo {
    private String telephone;
    private String year;
    private String month;
    private String day;

    public QueryInfo() {
        super();
    }

    public QueryInfo(String telephone, String year, String month, String day) {
        super();
        this.telephone = telephone;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
