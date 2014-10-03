package pl.java.scalatech.domain;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "configs", type = "config")
@Data
public class Config {
@Id
private String key;
@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
private String value;

}