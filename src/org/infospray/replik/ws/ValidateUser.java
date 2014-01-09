package org.infospray.replik.ws;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ValidateUser")
public class ValidateUser extends ResponseContext {

	String securityString;

	public String getSecurityString() {
		return securityString;
	}

	public void setSecurityString(String securityString) {
		this.securityString = securityString;
	}
	
	
}
