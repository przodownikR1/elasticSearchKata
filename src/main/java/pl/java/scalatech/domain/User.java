package pl.java.scalatech.domain;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Builder;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Sławomir Borowiec 
 * Module name : bootSetting
 * Creating time :  17 wrz 2014 09:49:34
 
 */
@Data
@NoArgsConstructor
@Document(indexName = "user")
@ToString(callSuper=true)
@Builder
public class User extends PKEntity<Long>{
    private static final long serialVersionUID = -6567709458397827407L;
    @Field(type = FieldType.String,index = FieldIndex.not_analyzed)
    private String name;
    @Field(type = FieldType.String,index = FieldIndex.not_analyzed)
    private String login;
    private BigDecimal salary;

    public User(String name, String login, BigDecimal salary) {
        super();
        this.name = name;
        this.login = login;
        this.salary = salary;
    }


}
