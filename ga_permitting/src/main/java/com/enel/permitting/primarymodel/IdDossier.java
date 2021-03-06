package com.enel.permitting.primarymodel;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdDossier implements Serializable {


	private static final long serialVersionUID = 5054569026230669270L;
	private Long idFascicolo;
	private String country;
	
	@Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
