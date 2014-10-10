package pl.java.scalatech.rest.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.http.HttpStatus;

@XmlRootElement(name = "errorHolder")
@Data
@NoArgsConstructor
public class ErrorHolder implements Serializable {
    private static final long serialVersionUID = 2864366350107886961L;
    private HttpStatus status;
    private String errorCode;
    private String errorMessage;
    private String url;
    private String enityName;
    private Long entityId;



    public ErrorHolder(String message) {
        this.errorMessage = message;
    }

    public ErrorHolder(String message, HttpStatus status) {
        this.errorMessage = message;
        this.status = status;
    }

    public ErrorHolder(HttpStatus status, String errorCode, String errorMessage, String url) {
        super();
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.url = url;
    }

}
