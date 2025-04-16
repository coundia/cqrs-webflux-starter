package com.pcoundia.transactions.domain.valueObject;

import java.util.Objects;
import com.pcoundia.transactions.domain.exception.TransactionIdNotValid;
import com.pcoundia.transactions.domain.exception.TransactionReferenceNotValid;

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
public class TransactionReference implements Serializable {

private String reference;

public TransactionReference(String reference) {
this.reference = reference;
}

public static TransactionReference create(String reference) {
if (reference == null || reference.isBlank()) {
throw new TransactionReferenceNotValid("Reference is invalid");
}
return new TransactionReference(reference);
}

public String value() {
return this.reference;
}

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (!(o instanceof TransactionReference that)) return false;
return this.reference.equals(that.reference);
}

@Override
public int hashCode() {
return java.util.Objects.hash(reference);
}

@Override
public String toString() {
return String.valueOf(reference);
}
}
