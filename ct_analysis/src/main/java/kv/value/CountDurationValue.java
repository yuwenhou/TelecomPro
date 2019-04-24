package kv.value;

import kv.base.BaseValue;
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
public class CountDurationValue extends BaseValue {
    private String callSum;
    private String callDurationSum;

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.callSum);
        dataOutput.writeUTF(this.callDurationSum);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.callSum = dataInput.readUTF();
        this.callDurationSum = dataInput.readUTF();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountDurationValue that = (CountDurationValue) o;
        return Objects.equals(callSum, that.callSum) &&
                Objects.equals(callDurationSum, that.callDurationSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(callSum, callDurationSum);
    }
}
