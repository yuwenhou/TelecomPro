package kv.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description:
 * @time:2019/4/24
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDimension implements WritableComparable<BaseDimension> {

    public abstract int compareTo(BaseDimension o);
    public abstract void write(DataOutput out) throws IOException;
    public abstract void readFields(DataInput in) throws IOException;



}
