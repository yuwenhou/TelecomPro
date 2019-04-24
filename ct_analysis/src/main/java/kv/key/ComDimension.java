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
 * @time:2019/4/24
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ComDimension extends BaseDimension {

    private ContactDimension contactDimension = new ContactDimension();
    private DataDimension dataDimension = new DataDimension();


    @Override
    public int compareTo(BaseDimension o) {
        ComDimension anotherComDimension = (ComDimension) o;
        int result = this.dataDimension.compareTo(anotherComDimension.dataDimension);
        if (result!= 0){
            return result;
        }
        result = this.contactDimension.compareTo(anotherComDimension.contactDimension);
        return result;

    }

    @Override
    public void write(DataOutput out) throws IOException {
        contactDimension.write(out);
        dataDimension.write(out);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        contactDimension.readFields(in);
        dataDimension.readFields(in);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComDimension that = (ComDimension) o;
        return Objects.equals(contactDimension, that.contactDimension) &&
                Objects.equals(dataDimension, that.dataDimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactDimension, dataDimension);
    }
}
