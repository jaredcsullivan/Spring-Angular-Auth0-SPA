package com.example.springdemo.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public final class LeadDTO {
 
    private String id;
 
    @NotEmpty
    @Size(max = Lead.MAX_FIELD_LENGTH)
    private String firstName;
 
    @NotEmpty
    @Size(max = Lead.MAX_FIELD_LENGTH)
    private String lastName;
    
    @NotEmpty
    @Size(max = Lead.MAX_FIELD_LENGTH)
    private String companyName;
    
    @NotEmpty
    @Size(max = Lead.MAX_FIELD_LENGTH)
    private String emailAddress;
    
    @NotEmpty
    @Size(max = Lead.MAX_FIELD_LENGTH)
    private String jobTitle;
    
    @Size(max = Lead.MAX_FIELD_LENGTH)
    private String username;
 
   @NotEmpty
    @Size(max = Lead.MAX_FIELD_LENGTH)
    private String phoneNumber;
 
    public LeadDTO() {

    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getFirstName() {
 		return firstName;
 	}

 	public void setFirstName(String firstName) {
 		this.firstName = firstName;
 	}

 	public String getLastName() {
 		return lastName;
 	}

 	public void setLastName(String lastName) {
 		this.lastName = lastName;
 	}
 	
 	public String getCompanyName() {
 		return companyName;
 	}
 	
 	public void setCompanyName(String companyName) {
 		this.companyName = companyName;
 	}
 	
 	public String getEmailAddress() {
 		return emailAddress;
 	}

 	public void setEmailAddress(String emailAddress) {
 		this.emailAddress = emailAddress;
 	}
 	
 	public String getJobTitle() {
 		return jobTitle;
 	}
 	
 	public void setJobTitle(String jobTitle) {
 		this.jobTitle = jobTitle;
 	}
 	
 	public String getPhoneNumber() {
 		return phoneNumber;
 	}

 	public void setPhoneNumber(String phoneNumber) {
 		this.phoneNumber = phoneNumber;
 	}
 	
 	public String getUsername() {
 		return username;
 	}

 	public void setUsername(String username) {
 		this.username = username;
 	}
 	
     @Override
     public String toString() {
         return String.format(
                 "Customer[id='%s', firstName='%s', lastName='%s', companyName='%s', emailAddress='%s', jobTitle='%s', phoneNumber='%s', username='%s']",
                 id, firstName, lastName, companyName, emailAddress, jobTitle, phoneNumber, username);
     }
    
}