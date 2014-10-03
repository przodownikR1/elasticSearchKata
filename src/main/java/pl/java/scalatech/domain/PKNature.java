package pl.java.scalatech.domain;

import java.io.Serializable;

@FunctionalInterface
public interface PKNature<T> extends Serializable {
   T getId();
}