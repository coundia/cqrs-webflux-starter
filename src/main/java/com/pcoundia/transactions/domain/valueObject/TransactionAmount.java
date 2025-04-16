package com.pcoundia.transactions.domain.valueObject;

import java.util.Objects;
import com.pcoundia.transactions.domain.exception.TransactionIdNotValid;
import com.pcoundia.transactions.domain.exception.TransactionReferenceNotValid;
import com.pcoundia.transactions.domain.exception.TransactionAmountNotValid;

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
public class TransactionAmount implements Serializable {

private Double amount;

public TransactionAmount(Double amount) {
this.amount = amount;
}

public static TransactionAmount create(Double amount) {
if (amount == null) {
throw new TransactionAmountNotValid("Amount is invalid");
}
return new TransactionAmount(amount);
}

public Double value() {
return this.amount;
}

@Override
public boolean equals(Object o) {
if (this == o) return true;
if (!(o instanceof TransactionAmount that)) return false;
return this.amount.equals(that.amount);
}

@Override
public int hashCode() {
return java.util.Objects.hash(amount);
}

@Override
public String toString() {
return String.valueOf(amount);
}
}
