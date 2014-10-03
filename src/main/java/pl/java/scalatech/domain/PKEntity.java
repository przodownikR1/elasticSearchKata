package pl.java.scalatech.domain;


import lombok.Data;

import org.springframework.data.annotation.Id;



@Data
public abstract class PKEntity<T> implements PKNature<T>  {
    /**
	 * 
	 */
    private static final long serialVersionUID = 7669211182758111346L;
    @Id
    protected T id;

  


}