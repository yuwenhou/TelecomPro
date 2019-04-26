package bean;

/**
 * 用于存放返回给用户的数据
 */
public class CallLog {
    private String id_date_contact;
    private String id_date_dimension;
    private String id_contact;
    private String call_sum;
    private String call_duration_sum;
    private String telephone;
    private String name;
    private String year;
    private String month;
    private String day;

    public String getId_date_contact() {
        return id_date_contact;
    }

    public void setId_date_contact(String id_date_contact) {
        this.id_date_contact = id_date_contact;
    }

    public String getId_date_dimension() {
        return id_date_dimension;
    }

    public void setId_date_dimension(String id_date_dimension) {
        this.id_date_dimension = id_date_dimension;
    }

    public String getId_contact() {
        return id_contact;
    }

    public void setId_contact(String id_contact) {
        this.id_contact = id_contact;
    }

    public String getCall_sum() {
        return call_sum;
    }

    public void setCall_sum(String call_sum) {
        this.call_sum = call_sum;
    }

    public String getCall_duration_sum() {
        return call_duration_sum;
    }

    public void setCall_duration_sum(String call_duration_sum) {
        this.call_duration_sum = call_duration_sum;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "CallLog{" +
                "call_sum='" + call_sum + '\'' +
                ", call_duration_sum='" + call_duration_sum + '\'' +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
