package it.eng.idsa;

import java.net.URI;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import de.fraunhofer.iais.eis.TokenFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("ids:Token")
public class Token implements de.fraunhofer.iais.eis.Token {
	private String tokenValue;
	
	public Token (String token) {
		tokenValue=token;
	}
	
	@Override
	public String toRdf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTokenValue() {
		// TODO Auto-generated method stub
		return tokenValue;
	}

	@Override
	public TokenFormat getTokenFormat() {
		// TODO Auto-generated method stub
		return TokenFormat.JWT;
	}

	@Override
	public @NotNull URI getId() {
		// TODO Auto-generated method stub
		return null;
	}

}
