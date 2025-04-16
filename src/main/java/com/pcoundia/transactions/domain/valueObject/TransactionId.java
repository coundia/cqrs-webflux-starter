package com.pcoundia.transactions.domain.valueObject;

import java.util.Objects;
import com.pcoundia.transactions.domain.exception.TransactionIdNotValid;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class TransactionId implements Serializable {

private String id;

public TransactionId(String id) {
this.id = id;
}

public static TransactionId create(String id) {
if (id == null || id.isBlank()) {
throw new TransactionIdNotValid("Id is invalid");
}
return new TransactionId(id);
}

public String value() {
return this.id;
}

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (!(o instanceof TransactionId that)) return false;
return this.id.equals(that.id);
}

@Override
public int hashCode() {
return java.util.Objects.hash(id);
}

@Override
public String toString() {
return String.valueOf(id);
}
}
