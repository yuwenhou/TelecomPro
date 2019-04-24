package kv.key;

import kv.base.BaseDimension;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @description:
 * @time:2019/4/24 联系人表
 * 下面是java简化bean
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDimension extends BaseDimension {

    private String telephone;
    private String name;

    @Override
    public int compareTo(BaseDimension o) {
        ContactDimension anotherContactDimension = (ContactDimension) o;
        int result = this.name.compareTo(anotherContactDimension.name);
        if (result != 0){
            return  result;
        }
        result = this.telephone.compareTo(anotherContactDimension.telephone);
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(this.telephone);
        out.writeUTF(this.name);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.telephone = in.readUTF();
        this.name = in.readUTF();
    }
}
