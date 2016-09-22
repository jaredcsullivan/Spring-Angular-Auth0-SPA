package com.example.springdemo.domain;

import static com.example.springdemo.PreCondition.isTrue;
import static com.example.springdemo.PreCondition.notEmpty;
import static com.example.springdemo.PreCondition.notNull;

import org.springframework.data.annotation.Id;

final class Lead {

    public static final int MAX_FIELD_LENGTH = 50;
    
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String companyName;
    private String emailAddress;
    private String jobTitle;
    private String phoneNumber;
    private String username;
	
    public Lead() {}

    public Lead(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.companyName = builder.companyName;
        this.emailAddress = builder.emailAddress;
        this.jobTitle = builder.jobTitle;
        this.phoneNumber = builder.phoneNumber;
        this.username = builder.username;
    }

    static Builder getBuilder() {
        return new Builder();
    }
    
    public String getId() {
		return id;
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
	
    public void update(String firstName, String lastName, String companyName, String emailAddress, String jobTitle, String phoneNumber) {
    	serverSideInputValidation(firstName, lastName);

        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.emailAddress = emailAddress;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
    }
	
    @Override
    public String toString() {
        return String.format(
                "Customer[id='%s', firstName='%s', lastName='%s', companyName='%s', emailAddress='%s', jobTitle='%s', phoneNumber='%s', username='%s']",
                id, firstName, lastName, companyName, emailAddress, jobTitle, phoneNumber, username);
    }
    
    static class Builder {

        private String firstName;
        private String lastName;
        private String companyName;
        private String emailAddress;
        private String jobTitle;
        private String phoneNumber;
        private String username;

        private Builder() {}

        Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        Builder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }
        Builder jobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        Builder username(String username) {
            this.username = username;
            return this;
        }
        
        Lead build() {
        	Lead build = new Lead(this);

            build.serverSideInputValidation(build.getFirstName(), build.getLastName());

            return build;
        }
    }

    private void serverSideInputValidation(String firstName, String lastName) {
        notNull(firstName, "First name cannot be null");
        notEmpty(firstName, "First name cannot be empty");
        isTrue(firstName.length() <= MAX_FIELD_LENGTH,
                "First name cannot be longer than %d characters",
                MAX_FIELD_LENGTH
        );

        if (lastName != null) {
            isTrue(lastName.length() <= MAX_FIELD_LENGTH,
                    "Last name cannot be longer than %d characters",
                    MAX_FIELD_LENGTH
            );
        }
    }
    

}