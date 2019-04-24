package kv.key;

import kv.base.BaseDimension;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

/**
 * @description:
 * @time:2019/4/24  */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataDimension extends BaseDimension {

    private String year;
    private String month;
    private String day;


    @Override
    public int compareTo(BaseDimension o) {
        DataDimension anotherDataDimension = (DataDimension) o;
        int result = this.year.compareTo(anotherDataDimension.year);
        if (result!= 0){
            return result;
        }

        result = this.month.compareTo(anotherDataDimension.month);
        if (result != 0){
            return result;
        }


        result = this.day.compareTo(anotherDataDimension.day);
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.year);
        out.writeUTF(this.month);
        out.writeUTF(this.day);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.year = in.readUTF();
        this.month = in.readUTF();
        this.day = in.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataDimension that = (DataDimension) o;
        return Objects.equals(year, that.year) &&
                Objects.equals(month, that.month) &&
                Objects.equals(day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}
