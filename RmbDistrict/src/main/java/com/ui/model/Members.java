package com.ui.model;

public class Members {
  
      public Members(int memberId, String status, int createdBy, String ipAddress) {
		super();
		this.memberId = memberId;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Members(int memberId, String memberOccupation, String memberDesignation, String memberCompanyName, String memberBusinessNature,
            String memberCompanyMobileNumber, String memberFaxNumber, String memberCompanyEmail,
            String memberWebsiteName, String memberCompanyDescription, String memberCompanyKeywords,
            String memberCompanyAddress1, String memberCompanyAddress2, String memberCompanyAddress3,
            int memberCompanyStateId, String memberCompanyCityName, String memberCompanyPincode,
            String memberCommunicationAddress, String businessGoals, String accomplishments, String interests,
            String networks, String skills, String idealReferral, String topProduct, String topProblemSolved,
            String ipAddress) {
        super();
        this.memberId = memberId;        
        this.memberOccupation = memberOccupation;
        this.memberDesignation = memberDesignation;
        this.memberCompanyName = memberCompanyName;
        this.memberBusinessNature = memberBusinessNature;
        this.memberCompanyMobileNumber = memberCompanyMobileNumber;
        this.memberFaxNumber = memberFaxNumber;
        this.memberCompanyEmail = memberCompanyEmail;
        this.memberWebsiteName = memberWebsiteName;
        this.memberCompanyDescription = memberCompanyDescription;
        this.memberCompanyKeywords = memberCompanyKeywords;
        this.memberCompanyAddress1 = memberCompanyAddress1;
        this.memberCompanyAddress2 = memberCompanyAddress2;
        this.memberCompanyAddress3 = memberCompanyAddress3;
        this.memberCompanyStateId = memberCompanyStateId;
        this.memberCompanyCityName = memberCompanyCityName;
        this.memberCompanyPincode = memberCompanyPincode;
        this.memberCommunicationAddress = memberCommunicationAddress;
        this.businessGoals = businessGoals;
        this.accomplishments = accomplishments;
        this.interests = interests;
        this.networks = networks;
        this.skills = skills;
        this.idealReferral = idealReferral;
        this.topProduct = topProduct;
        this.topProblemSolved = topProblemSolved;
        this.ipAddress = ipAddress;
    }
  
      public Members(int memberId, String memberAddress1, String memberAddress2, String memberAddress3, int memberStateId,
            String memberCityName, String memberPincode, String memberMobileNumber, String ipAddress) {
        super();
        this.memberId = memberId;
        this.memberAddress1 = memberAddress1;
        this.memberAddress2 = memberAddress2;
        this.memberAddress3 = memberAddress3;
        this.memberStateId = memberStateId;
        this.memberCityName = memberCityName;
        this.memberPincode = memberPincode;
        this.memberMobileNumber = memberMobileNumber;        
        this.ipAddress = ipAddress;
    }
  
      public Members(int memberId, String memberNameTitle, String memberFirstName, String memberMiddleName, String memberLastName, String memberGender,
            String memberDateOfBirth, String memberAnniversaryDate, String memberBloodGroup, String memberAadharNumber,
            int memberCountryIdCitizenship, String memberPassportNumber, String memberPanNumber,
            String memberEmail, int sequence, String ipAddress) {
        super();
        this.memberId = memberId;        
        this.memberNameTitle = memberNameTitle;
        this.memberFirstName = memberFirstName;
        this.memberMiddleName = memberMiddleName;
        this.memberLastName = memberLastName;
        this.memberGender = memberGender;
        this.memberDateOfBirth = memberDateOfBirth;
        this.memberAnniversaryDate = memberAnniversaryDate;
        this.memberBloodGroup = memberBloodGroup;
        this.memberAadharNumber = memberAadharNumber;
        this.memberCountryIdCitizenship = memberCountryIdCitizenship;
        this.memberPassportNumber = memberPassportNumber;
        this.memberPanNumber = memberPanNumber;       
        this.memberEmail = memberEmail;       
        this.sequence = sequence;
        this.ipAddress = ipAddress;
    }
  
  
    public Members(int memberId, String memberFirstName, String memberLastName, String memberProfilePicture) {
        super();
        this.memberId = memberId;
        this.memberFirstName = memberFirstName;       
        this.memberLastName = memberLastName;
        this.memberProfilePicture = memberProfilePicture;
    }
	/*public Members(int memberId, String memberFirstName, String memberMiddleName, String memberLastName) {
		super();
		this.memberId = memberId;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
	}*/

    public Members(String memberEmail, String memberPassword, String ipAddress, int createdBy) {
      super();
      this.memberEmail = memberEmail;
      this.memberPassword = memberPassword;
      this.ipAddress = ipAddress;
      this.createdBy = createdBy;
  }
    
    
    public Members(int memberId, String memberPassword, String ipAddress) {
      super();
      this.memberId = memberId;
      this.memberPassword = memberPassword;
      this.ipAddress = ipAddress;
    }
    
	public Members(String memberFirstName, String memberLastName, int memberId, String memberProfilePicture,
			String memberEmail, String memberMobileNumber, String businessCategoryName, String membertype ) {
		super();
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.memberId = memberId;
		this.memberProfilePicture = memberProfilePicture;
		this.memberEmail = memberEmail;
		this.memberMobileNumber = memberMobileNumber;
		this.businessCategoryName = businessCategoryName;
		this.memberType = membertype;
	}

	public Members(int memberId, String memberFirstName, String memberMiddleName, String memberLastName,
			String memberProfilePicture) {
		super();
		this.memberId = memberId;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.memberProfilePicture = memberProfilePicture;
	}

	public Members(int memberId, String memberFirstName, String memberMiddleName, String memberLastName,
			int referenceGivenInside, int referenceGivenOutside, int referenceReceivedInside,
			int referenceReceivedOutside, int memberMeetingCount, int memberBusinessTransactionCount) {
		super();
		this.memberId = memberId;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.referenceGivenInside = referenceGivenInside;
		this.referenceGivenOutside = referenceGivenOutside;
		this.referenceReceivedInside = referenceReceivedInside;
		this.referenceReceivedOutside = referenceReceivedOutside;
		this.memberMeetingCount = memberMeetingCount;
		this.memberBusinessTransactionCount = memberBusinessTransactionCount;
	}

	public void setMembershipNumber(String membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	public Members(int memberId, int userTypeId, String membershipNumber, int memberCategoryId, int memberTypeId,
			int businessCategoryId, String tenurePlan, String joiningDate, String memberNameTitle,
			String memberFirstName, String memberMiddleName, String memberLastName, String memberGender,
			String memberDateOfBirth, String memberAnniversaryDate, String memberBloodGroup, String memberAadharNumber,
			int memberCountryIdCitizenship, String memberPassportNumber, String memberPanNumber,
			String memberProfilePicture, String memberEmail, String memberPassword, String memberAddress1,
			String memberAddress2, String memberAddress3, int memberStateId, String memberCityName,
			String memberPincode, String memberMobileNumber, String memberPhoneNumber, String memberBarcode,
			String memberQrcode, String companyLogo, String memberOccupation, String memberDesignation,
			String memberCompanyName, String memberBusinessNature, String memberCompanyMobileNumber,
			String memberCompanyPhoneNumber, String memberFaxNumber, String memberCompanyEmail,
			String memberWebsiteName, String memberCompanyDescription, String memberCompanyKeywords,
			String memberCompanyAddress1, String memberCompanyAddress2, String memberCompanyAddress3,
			int memberCompanyStateId, String memberCompanyCityName, String memberCompanyPincode,
			String memberCommunicationAddress, String businessGoals, String accomplishments, String interests,
			String networks, String skills, String idealReferral, String topProduct, String topProblemSolved,
			String businessCategoryName) {
		super();
		this.memberId = memberId;
		this.userTypeId = userTypeId;
		this.membershipNumber = membershipNumber;
		this.memberCategoryId = memberCategoryId;
		this.memberTypeId = memberTypeId;
		this.businessCategoryId = businessCategoryId;
		this.tenurePlan = tenurePlan;
		this.joiningDate = joiningDate;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.memberGender = memberGender;
		this.memberDateOfBirth = memberDateOfBirth;
		this.memberAnniversaryDate = memberAnniversaryDate;
		this.memberBloodGroup = memberBloodGroup;
		this.memberAadharNumber = memberAadharNumber;
		this.memberCountryIdCitizenship = memberCountryIdCitizenship;
		this.memberPassportNumber = memberPassportNumber;
		this.memberPanNumber = memberPanNumber;
		this.memberProfilePicture = memberProfilePicture;
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberAddress1 = memberAddress1;
		this.memberAddress2 = memberAddress2;
		this.memberAddress3 = memberAddress3;
		this.memberStateId = memberStateId;
		this.memberCityName = memberCityName;
		this.memberPincode = memberPincode;
		this.memberMobileNumber = memberMobileNumber;
		this.memberPhoneNumber = memberPhoneNumber;
		this.memberBarcode = memberBarcode;
		this.memberQrcode = memberQrcode;
		this.companyLogo = companyLogo;
		this.memberOccupation = memberOccupation;
		this.memberDesignation = memberDesignation;
		this.memberCompanyName = memberCompanyName;
		this.memberBusinessNature = memberBusinessNature;
		this.memberCompanyMobileNumber = memberCompanyMobileNumber;
		this.memberCompanyPhoneNumber = memberCompanyPhoneNumber;
		this.memberFaxNumber = memberFaxNumber;
		this.memberCompanyEmail = memberCompanyEmail;
		this.memberWebsiteName = memberWebsiteName;
		this.memberCompanyDescription = memberCompanyDescription;
		this.memberCompanyKeywords = memberCompanyKeywords;
		this.memberCompanyAddress1 = memberCompanyAddress1;
		this.memberCompanyAddress2 = memberCompanyAddress2;
		this.memberCompanyAddress3 = memberCompanyAddress3;
		this.memberCompanyStateId = memberCompanyStateId;
		this.memberCompanyCityName = memberCompanyCityName;
		this.memberCompanyPincode = memberCompanyPincode;
		this.memberCommunicationAddress = memberCommunicationAddress;
		this.businessGoals = businessGoals;
		this.accomplishments = accomplishments;
		this.interests = interests;
		this.networks = networks;
		this.skills = skills;
		this.idealReferral = idealReferral;
		this.topProduct = topProduct;
		this.topProblemSolved = topProblemSolved;
		this.businessCategoryName = businessCategoryName;
	}

	public Members(int memberId, String memberFirstName, String memberLastName, int rotaryYearId,
			String rotaryYearTitle, int memberCategoryId, String memberCategoryName, float billingAmount,
			int currencyId, String currencyCode) {
		super();
		this.memberId = memberId;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.rotaryYearId = rotaryYearId;
		this.rotaryYearTitle = rotaryYearTitle;
		this.memberCategoryName = memberCategoryName;
		this.memberCategoryId = memberCategoryId;
		this.billingAmount = billingAmount;
		this.currencyId = currencyId;
		this.currencyCode = currencyCode;
	}

	public Members(String id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Members(int memberId, String membershipNumber, String memberFirstName, String memberLastName,
			String memberEmail, String memberAddress1, String memberAddress2, String memberAddress3,
			String memberCityName, String memberMobileNumber, String businessCategoryName) {
		super();
		this.memberId = memberId;
		this.membershipNumber = membershipNumber;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.memberEmail = memberEmail;
		this.memberAddress1 = memberAddress1;
		this.memberAddress2 = memberAddress2;
		this.memberAddress3 = memberAddress3;
		this.memberCityName = memberCityName;
		this.memberMobileNumber = memberMobileNumber;
		this.businessCategoryName = businessCategoryName;
	}

	public Members(int memberId, String membershipNumber, int clubId, int memberCategoryId, int memberTypeId,
			int businessCategoryId, String tenurePlan, String joiningDate, String memberNameTitle,
			String memberFirstName, String memberMiddleName, String memberLastName, String memberGender,
			String memberDateOfBirth, String memberAnniversaryDate, String memberBloodGroup, String memberAadharNumber,
			int memberCountryIdCitizenship, String memberPassportNumber, String memberPanNumber,
			String memberProfilePicture, String memberEmail, String memberBarcode, String memberQrcode, int sequence,
			String ipAddress) {
		super();
		this.memberId = memberId;
		this.membershipNumber = membershipNumber;
		this.clubId = clubId;
		this.memberCategoryId = memberCategoryId;
		this.memberTypeId = memberTypeId;
		this.businessCategoryId = businessCategoryId;
		this.tenurePlan = tenurePlan;
		this.joiningDate = joiningDate;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.memberGender = memberGender;
		this.memberDateOfBirth = memberDateOfBirth;
		this.memberAnniversaryDate = memberAnniversaryDate;
		this.memberBloodGroup = memberBloodGroup;
		this.memberAadharNumber = memberAadharNumber;
		this.memberCountryIdCitizenship = memberCountryIdCitizenship;
		this.memberPassportNumber = memberPassportNumber;
		this.memberPanNumber = memberPanNumber;
		this.memberProfilePicture = memberProfilePicture;
		this.memberEmail = memberEmail;
		this.memberBarcode = memberBarcode;
		this.memberQrcode = memberQrcode;
		this.sequence = sequence;
		this.ipAddress = ipAddress;
	}

	public Members(int memberId, String memberAddress1, String memberAddress2, String memberAddress3, int memberStateId,
			String memberCityName, String memberPincode, String memberMobileNumber, String companyLogo,
			String memberOccupation, String memberDesignation, String memberCompanyName, String memberBusinessNature,
			String memberCompanyMobileNumber, String memberFaxNumber, String memberCompanyEmail,
			String memberWebsiteName, String memberCompanyDescription, String memberCompanyKeywords,
			String memberCompanyAddress1, String memberCompanyAddress2, String memberCompanyAddress3,
			int memberCompanyStateId, String memberCompanyCityName, String memberCompanyPincode,
			String memberCommunicationAddress, String businessGoals, String accomplishments, String interests,
			String networks, String skills, String idealReferral, String topProduct, String topProblemSolved,
			String ipAddress) {
		super();
		this.memberId = memberId;
		this.memberAddress1 = memberAddress1;
		this.memberAddress2 = memberAddress2;
		this.memberAddress3 = memberAddress3;
		this.memberStateId = memberStateId;
		this.memberCityName = memberCityName;
		this.memberPincode = memberPincode;
		this.memberMobileNumber = memberMobileNumber;
		this.companyLogo = companyLogo;
		this.memberOccupation = memberOccupation;
		this.memberDesignation = memberDesignation;
		this.memberCompanyName = memberCompanyName;
		this.memberBusinessNature = memberBusinessNature;
		this.memberCompanyMobileNumber = memberCompanyMobileNumber;
		this.memberFaxNumber = memberFaxNumber;
		this.memberCompanyEmail = memberCompanyEmail;
		this.memberWebsiteName = memberWebsiteName;
		this.memberCompanyDescription = memberCompanyDescription;
		this.memberCompanyKeywords = memberCompanyKeywords;
		this.memberCompanyAddress1 = memberCompanyAddress1;
		this.memberCompanyAddress2 = memberCompanyAddress2;
		this.memberCompanyAddress3 = memberCompanyAddress3;
		this.memberCompanyStateId = memberCompanyStateId;
		this.memberCompanyCityName = memberCompanyCityName;
		this.memberCompanyPincode = memberCompanyPincode;
		this.memberCommunicationAddress = memberCommunicationAddress;
		this.businessGoals = businessGoals;
		this.accomplishments = accomplishments;
		this.interests = interests;
		this.networks = networks;
		this.skills = skills;
		this.idealReferral = idealReferral;
		this.topProduct = topProduct;
		this.topProblemSolved = topProblemSolved;
		this.ipAddress = ipAddress;
	}
	
	public Members(int memberId, int userTypeId, String membershipNumber, int clubId, int memberCategoryId,
			int memberTypeId, int businessCategoryId, String tenurePlan, String joiningDate, String memberNameTitle,
			String memberFirstName, String memberMiddleName, String memberLastName, String memberGender,
			String memberDateOfBirth, String memberAnniversaryDate, String memberBloodGroup, String memberAadharNumber,
			int memberCountryIdCitizenship, String memberPassportNumber, String memberPanNumber,
			String memberProfilePicture, String memberEmail, String memberAddress1, String memberAddress2,
			String memberAddress3, int memberStateId, String memberCityName, String memberPincode,
			String memberMobileNumber, String memberPhoneNumber, String memberBarcode, String memberQrcode,
			String companyLogo, String memberOccupation, String memberDesignation, String memberCompanyName,
			String memberBusinessNature, String memberCompanyMobileNumber, String memberCompanyPhoneNumber,
			String memberFaxNumber, String memberCompanyEmail, String memberWebsiteName,
			String memberCompanyDescription, String memberCompanyKeywords, String memberCompanyAddress1,
			String memberCompanyAddress2, String memberCompanyAddress3, int memberCompanyStateId,
			String memberCompanyCityName, String memberCompanyPincode, String memberCommunicationAddress,
			String businessGoals, String accomplishments, String interests, String networks, String skills,
			String idealReferral, String topProduct, String topProblemSolved, int sequence, String status,
			String businessCategoryName) {
		super();
		this.memberId = memberId;
		this.userTypeId = userTypeId;
		this.membershipNumber = membershipNumber;
		this.clubId = clubId;
		this.memberCategoryId = memberCategoryId;
		this.memberTypeId = memberTypeId;
		this.businessCategoryId = businessCategoryId;
		this.tenurePlan = tenurePlan;
		this.joiningDate = joiningDate;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.memberGender = memberGender;
		this.memberDateOfBirth = memberDateOfBirth;
		this.memberAnniversaryDate = memberAnniversaryDate;
		this.memberBloodGroup = memberBloodGroup;
		this.memberAadharNumber = memberAadharNumber;
		this.memberCountryIdCitizenship = memberCountryIdCitizenship;
		this.memberPassportNumber = memberPassportNumber;
		this.memberPanNumber = memberPanNumber;
		this.memberProfilePicture = memberProfilePicture;
		this.memberEmail = memberEmail;
		this.memberAddress1 = memberAddress1;
		this.memberAddress2 = memberAddress2;
		this.memberAddress3 = memberAddress3;
		this.memberStateId = memberStateId;
		this.memberCityName = memberCityName;
		this.memberPincode = memberPincode;
		this.memberMobileNumber = memberMobileNumber;
		this.memberPhoneNumber = memberPhoneNumber;
		this.memberBarcode = memberBarcode;
		this.memberQrcode = memberQrcode;
		this.companyLogo = companyLogo;
		this.memberOccupation = memberOccupation;
		this.memberDesignation = memberDesignation;
		this.memberCompanyName = memberCompanyName;
		this.memberBusinessNature = memberBusinessNature;
		this.memberCompanyMobileNumber = memberCompanyMobileNumber;
		this.memberCompanyPhoneNumber = memberCompanyPhoneNumber;
		this.memberFaxNumber = memberFaxNumber;
		this.memberCompanyEmail = memberCompanyEmail;
		this.memberWebsiteName = memberWebsiteName;
		this.memberCompanyDescription = memberCompanyDescription;
		this.memberCompanyKeywords = memberCompanyKeywords;
		this.memberCompanyAddress1 = memberCompanyAddress1;
		this.memberCompanyAddress2 = memberCompanyAddress2;
		this.memberCompanyAddress3 = memberCompanyAddress3;
		this.memberCompanyStateId = memberCompanyStateId;
		this.memberCompanyCityName = memberCompanyCityName;
		this.memberCompanyPincode = memberCompanyPincode;
		this.memberCommunicationAddress = memberCommunicationAddress;
		this.businessGoals = businessGoals;
		this.accomplishments = accomplishments;
		this.interests = interests;
		this.networks = networks;
		this.skills = skills;
		this.idealReferral = idealReferral;
		this.topProduct = topProduct;
		this.topProblemSolved = topProblemSolved;
		this.sequence = sequence;
		this.status = status;
		this.businessCategoryName = businessCategoryName;
	}
	public Members(int memberId, int userTypeId, String membershipNumber, int clubId, int memberCategoryId,
			int memberTypeId, int businessCategoryId, String tenurePlan, String joiningDate, String memberNameTitle,
			String memberFirstName, String memberMiddleName, String memberLastName, String memberGender,
			String memberDateOfBirth, String memberAnniversaryDate, String memberBloodGroup, String memberAadharNumber,
			int memberCountryIdCitizenship, String memberPassportNumber, String memberPanNumber,
			String memberProfilePicture, String memberEmail, String memberAddress1, String memberAddress2,
			String memberAddress3, int memberStateId, String memberCityName, String memberPincode,
			String memberMobileNumber, String memberPhoneNumber, String memberBarcode, String memberQrcode,
			String companyLogo, String memberOccupation, String memberDesignation, String memberCompanyName,
			String memberBusinessNature, String memberCompanyMobileNumber, String memberCompanyPhoneNumber,
			String memberFaxNumber, String memberCompanyEmail, String memberWebsiteName,
			String memberCompanyDescription, String memberCompanyKeywords, String memberCompanyAddress1,
			String memberCompanyAddress2, String memberCompanyAddress3, int memberCompanyStateId,
			String memberCompanyCityName, String memberCompanyPincode, String memberCommunicationAddress,
			String businessGoals, String accomplishments, String interests, String networks, String skills,
			String idealReferral, String topProduct, String topProblemSolved, int sequence, String status,
			String businessCategoryName,String clubName,String fellowship_name) {
		super();
		this.memberId = memberId;
		this.userTypeId = userTypeId;
		this.membershipNumber = membershipNumber;
		this.clubId = clubId;
		this.memberCategoryId = memberCategoryId;
		this.memberTypeId = memberTypeId;
		this.businessCategoryId = businessCategoryId;
		this.tenurePlan = tenurePlan;
		this.joiningDate = joiningDate;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.memberGender = memberGender;
		this.memberDateOfBirth = memberDateOfBirth;
		this.memberAnniversaryDate = memberAnniversaryDate;
		this.memberBloodGroup = memberBloodGroup;
		this.memberAadharNumber = memberAadharNumber;
		this.memberCountryIdCitizenship = memberCountryIdCitizenship;
		this.memberPassportNumber = memberPassportNumber;
		this.memberPanNumber = memberPanNumber;
		this.memberProfilePicture = memberProfilePicture;
		this.memberEmail = memberEmail;
		this.memberAddress1 = memberAddress1;
		this.memberAddress2 = memberAddress2;
		this.memberAddress3 = memberAddress3;
		this.memberStateId = memberStateId;
		this.memberCityName = memberCityName;
		this.memberPincode = memberPincode;
		this.memberMobileNumber = memberMobileNumber;
		this.memberPhoneNumber = memberPhoneNumber;
		this.memberBarcode = memberBarcode;
		this.memberQrcode = memberQrcode;
		this.companyLogo = companyLogo;
		this.memberOccupation = memberOccupation;
		this.memberDesignation = memberDesignation;
		this.memberCompanyName = memberCompanyName;
		this.memberBusinessNature = memberBusinessNature;
		this.memberCompanyMobileNumber = memberCompanyMobileNumber;
		this.memberCompanyPhoneNumber = memberCompanyPhoneNumber;
		this.memberFaxNumber = memberFaxNumber;
		this.memberCompanyEmail = memberCompanyEmail;
		this.memberWebsiteName = memberWebsiteName;
		this.memberCompanyDescription = memberCompanyDescription;
		this.memberCompanyKeywords = memberCompanyKeywords;
		this.memberCompanyAddress1 = memberCompanyAddress1;
		this.memberCompanyAddress2 = memberCompanyAddress2;
		this.memberCompanyAddress3 = memberCompanyAddress3;
		this.memberCompanyStateId = memberCompanyStateId;
		this.memberCompanyCityName = memberCompanyCityName;
		this.memberCompanyPincode = memberCompanyPincode;
		this.memberCommunicationAddress = memberCommunicationAddress;
		this.businessGoals = businessGoals;
		this.accomplishments = accomplishments;
		this.interests = interests;
		this.networks = networks;
		this.skills = skills;
		this.idealReferral = idealReferral;
		this.topProduct = topProduct;
		this.topProblemSolved = topProblemSolved;
		this.sequence = sequence;
		this.status = status;
		this.businessCategoryName = businessCategoryName;
		this.clubName=clubName;
		this.fellowship_name=fellowship_name;
	}
	public Members(int memberId, int userTypeId, String membershipNumber, int clubId, int memberCategoryId,
			int memberTypeId, int businessCategoryId, String tenurePlan, String joiningDate, String memberNameTitle,
			String memberFirstName, String memberMiddleName, String memberLastName, String memberGender,
			String memberDateOfBirth, String memberAnniversaryDate, String memberBloodGroup, String memberAadharNumber,
			int memberCountryIdCitizenship, String memberPassportNumber, String memberPanNumber,
			String memberProfilePicture, String memberEmail, String memberAddress1, String memberAddress2,
			String memberAddress3, int memberStateId, String memberCityName, String memberPincode,
			String memberMobileNumber, String memberPhoneNumber, String memberBarcode, String memberQrcode,
			String companyLogo, String memberOccupation, String memberDesignation, String memberCompanyName,
			String memberBusinessNature, String memberCompanyMobileNumber, String memberCompanyPhoneNumber,
			String memberFaxNumber, String memberCompanyEmail, String memberWebsiteName,
			String memberCompanyDescription, String memberCompanyKeywords, String memberCompanyAddress1,
			String memberCompanyAddress2, String memberCompanyAddress3, int memberCompanyStateId,
			String memberCompanyCityName, String memberCompanyPincode, String memberCommunicationAddress,
			String businessGoals, String accomplishments, String interests, String networks, String skills,
			String idealReferral, String topProduct, String topProblemSolved, int sequence, String status,
			String businessCategoryName,String clubName,String fellowship_name,int fellowship_id) {
		super();
		this.memberId = memberId;
		this.userTypeId = userTypeId;
		this.membershipNumber = membershipNumber;
		this.clubId = clubId;
		this.memberCategoryId = memberCategoryId;
		this.memberTypeId = memberTypeId;
		this.businessCategoryId = businessCategoryId;
		this.tenurePlan = tenurePlan;
		this.joiningDate = joiningDate;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.memberGender = memberGender;
		this.memberDateOfBirth = memberDateOfBirth;
		this.memberAnniversaryDate = memberAnniversaryDate;
		this.memberBloodGroup = memberBloodGroup;
		this.memberAadharNumber = memberAadharNumber;
		this.memberCountryIdCitizenship = memberCountryIdCitizenship;
		this.memberPassportNumber = memberPassportNumber;
		this.memberPanNumber = memberPanNumber;
		this.memberProfilePicture = memberProfilePicture;
		this.memberEmail = memberEmail;
		this.memberAddress1 = memberAddress1;
		this.memberAddress2 = memberAddress2;
		this.memberAddress3 = memberAddress3;
		this.memberStateId = memberStateId;
		this.memberCityName = memberCityName;
		this.memberPincode = memberPincode;
		this.memberMobileNumber = memberMobileNumber;
		this.memberPhoneNumber = memberPhoneNumber;
		this.memberBarcode = memberBarcode;
		this.memberQrcode = memberQrcode;
		this.companyLogo = companyLogo;
		this.memberOccupation = memberOccupation;
		this.memberDesignation = memberDesignation;
		this.memberCompanyName = memberCompanyName;
		this.memberBusinessNature = memberBusinessNature;
		this.memberCompanyMobileNumber = memberCompanyMobileNumber;
		this.memberCompanyPhoneNumber = memberCompanyPhoneNumber;
		this.memberFaxNumber = memberFaxNumber;
		this.memberCompanyEmail = memberCompanyEmail;
		this.memberWebsiteName = memberWebsiteName;
		this.memberCompanyDescription = memberCompanyDescription;
		this.memberCompanyKeywords = memberCompanyKeywords;
		this.memberCompanyAddress1 = memberCompanyAddress1;
		this.memberCompanyAddress2 = memberCompanyAddress2;
		this.memberCompanyAddress3 = memberCompanyAddress3;
		this.memberCompanyStateId = memberCompanyStateId;
		this.memberCompanyCityName = memberCompanyCityName;
		this.memberCompanyPincode = memberCompanyPincode;
		this.memberCommunicationAddress = memberCommunicationAddress;
		this.businessGoals = businessGoals;
		this.accomplishments = accomplishments;
		this.interests = interests;
		this.networks = networks;
		this.skills = skills;
		this.idealReferral = idealReferral;
		this.topProduct = topProduct;
		this.topProblemSolved = topProblemSolved;
		this.sequence = sequence;
		this.status = status;
		this.businessCategoryName = businessCategoryName;
		this.clubName=clubName;
		this.fellowship_name=fellowship_name;
		this.fellowship_id=fellowship_id;
	}
	public Members(int memberId, int userTypeId, String membershipNumber,int vocation_id, int clubId, int memberCategoryId,
			int memberTypeId, int businessCategoryId, String tenurePlan, String joiningDate, String memberNameTitle,
			String memberFirstName, String memberMiddleName, String memberLastName, String memberGender,
			String memberDateOfBirth, String memberAnniversaryDate, String memberBloodGroup, String memberAadharNumber,
			int memberCountryIdCitizenship, String memberPassportNumber, String memberPanNumber,
			String memberProfilePicture, String memberEmail, String memberAddress1, String memberAddress2,
			String memberAddress3, int memberStateId, String memberCityName, String memberPincode,
			String memberMobileNumber, String memberPhoneNumber, String memberBarcode, String memberQrcode,
			String companyLogo, String memberOccupation, String memberDesignation, String memberCompanyName,
			String memberBusinessNature, String memberCompanyMobileNumber, String memberCompanyPhoneNumber,
			String memberFaxNumber, String memberCompanyEmail, String memberWebsiteName,
			String memberCompanyDescription, String memberCompanyKeywords, String memberCompanyAddress1,
			String memberCompanyAddress2, String memberCompanyAddress3, int memberCompanyStateId,
			String memberCompanyCityName, String memberCompanyPincode, String memberCommunicationAddress,
			String businessGoals, String accomplishments, String interests, String networks, String skills,
			String idealReferral, String topProduct, String topProblemSolved, int sequence, String status,
			String businessCategoryName, String memberType, String rotaryMemberId, String rotaryClubName, String rotaryChapName, String clubName,int fellowship_id) {
		super();
		this.memberId = memberId;
		this.userTypeId = userTypeId;
		this.membershipNumber = membershipNumber;
		this.VocationId = vocation_id;
		this.clubId = clubId;
		this.memberCategoryId = memberCategoryId;
		this.memberTypeId = memberTypeId;
		this.businessCategoryId = businessCategoryId;
		this.tenurePlan = tenurePlan;
		this.joiningDate = joiningDate;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.memberGender = memberGender;
		this.memberDateOfBirth = memberDateOfBirth;
		this.memberAnniversaryDate = memberAnniversaryDate;
		this.memberBloodGroup = memberBloodGroup;
		this.memberAadharNumber = memberAadharNumber;
		this.memberCountryIdCitizenship = memberCountryIdCitizenship;
		this.memberPassportNumber = memberPassportNumber;
		this.memberPanNumber = memberPanNumber;
		this.memberProfilePicture = memberProfilePicture;
		this.memberEmail = memberEmail;
		this.memberAddress1 = memberAddress1;
		this.memberAddress2 = memberAddress2;
		this.memberAddress3 = memberAddress3;
		this.memberStateId = memberStateId;
		this.memberCityName = memberCityName;
		this.memberPincode = memberPincode;
		this.memberMobileNumber = memberMobileNumber;
		this.memberPhoneNumber = memberPhoneNumber;
		this.memberBarcode = memberBarcode;
		this.memberQrcode = memberQrcode;
		this.companyLogo = companyLogo;
		this.memberOccupation = memberOccupation;
		this.memberDesignation = memberDesignation;
		this.memberCompanyName = memberCompanyName;
		this.memberBusinessNature = memberBusinessNature;
		this.memberCompanyMobileNumber = memberCompanyMobileNumber;
		this.memberCompanyPhoneNumber = memberCompanyPhoneNumber;
		this.memberFaxNumber = memberFaxNumber;
		this.memberCompanyEmail = memberCompanyEmail;
		this.memberWebsiteName = memberWebsiteName;
		this.memberCompanyDescription = memberCompanyDescription;
		this.memberCompanyKeywords = memberCompanyKeywords;
		this.memberCompanyAddress1 = memberCompanyAddress1;
		this.memberCompanyAddress2 = memberCompanyAddress2;
		this.memberCompanyAddress3 = memberCompanyAddress3;
		this.memberCompanyStateId = memberCompanyStateId;
		this.memberCompanyCityName = memberCompanyCityName;
		this.memberCompanyPincode = memberCompanyPincode;
		this.memberCommunicationAddress = memberCommunicationAddress;
		this.businessGoals = businessGoals;
		this.accomplishments = accomplishments;
		this.interests = interests;
		this.networks = networks;
		this.skills = skills;
		this.idealReferral = idealReferral;
		this.topProduct = topProduct;
		this.topProblemSolved = topProblemSolved;
		this.sequence = sequence;
		this.status = status;
		this.businessCategoryName = businessCategoryName;
		this.memberType = memberType;
		this.rotaryMemberId = rotaryMemberId;
		this.rotaryClubName = rotaryClubName;
		this.rotaryChapName = rotaryChapName;
		this.clubName = clubName;
		this.fellowship_id=fellowship_id;
		
	}

	public Members(int memberId, String memberNameTitle, String memberFirstName, String memberLastName,
			String memberDateOfBirth, String memberProfilePicture, String memberCompanyName) {
		super();
		this.memberId = memberId;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.memberDateOfBirth = memberDateOfBirth;
		this.memberProfilePicture = memberProfilePicture;
		this.memberCompanyName = memberCompanyName;
	}

	public Members(String memberNameTitle, String memberFirstName, String memberLastName, String memberAnniversaryDate,
			String memberFamilyTitleName, String memberFamilyFirstName, String memberFamilyLastName) {
		super();
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberLastName = memberLastName;
		this.memberAnniversaryDate = memberAnniversaryDate;
		this.memberFamilyTitleName = memberFamilyTitleName;
		this.memberFamilyFirstName = memberFamilyFirstName;
		this.memberFamilyLastName = memberFamilyLastName;

	}
//yuvi
	public Members(int userTypeId, String membershipNumber, int clubId, int memberCategoryId, int memberTypeId,
			int businessCategoryId, String tenurePlan, String joiningDate, String memberNameTitle,
			String memberFirstName, String memberMiddleName, String memberLastName, String memberGender,
			String memberDateOfBirth, String memberAnniversaryDate, String memberBloodGroup, String memberAadharNumber,
			int memberCountryIdCitizenship, String memberPassportNumber, String memberPanNumber,
			String memberProfilePicture, String memberMobileNumber, String memberEmail, String memberPassword, String memberBarcode,
			String memberQrcode, int sequence, String status, int createdBy, String ipAddress) {
		super();
		this.userTypeId = userTypeId;
		this.membershipNumber = membershipNumber;
		this.clubId = clubId;
		this.memberCategoryId = memberCategoryId;
		this.memberTypeId = memberTypeId;
		this.businessCategoryId = businessCategoryId;
		this.tenurePlan = tenurePlan;
		this.joiningDate = joiningDate;
		this.memberNameTitle = memberNameTitle;
		this.memberFirstName = memberFirstName;
		this.memberMiddleName = memberMiddleName;
		this.memberLastName = memberLastName;
		this.memberGender = memberGender;
		this.memberDateOfBirth = memberDateOfBirth;
		this.memberBloodGroup = memberBloodGroup;
		this.memberAnniversaryDate = memberAnniversaryDate;
		this.memberAadharNumber = memberAadharNumber;
		this.memberCountryIdCitizenship = memberCountryIdCitizenship;
		this.memberPassportNumber = memberPassportNumber;
		this.memberPanNumber = memberPanNumber;
		this.memberProfilePicture = memberProfilePicture;
		this.memberMobileNumber = memberMobileNumber;
		this.memberEmail = memberEmail;
		this.memberPassword = memberPassword;
		this.memberBarcode = memberBarcode;
		this.memberQrcode = memberQrcode;
		this.sequence = sequence;
		this.status = status;
		this.createdBy = createdBy;
		this.ipAddress = ipAddress;
	}

	public Members(int sequence) {
		super();
		this.sequence = sequence;
	}

	public Members() {
		// TODO Auto-generated constructor stub
	}

	private int memberId;
	private int userTypeId;
	private String membershipNumber;
	private int clubId;
	private int memberCategoryId;
	private int memberTypeId;
	private int businessCategoryId;
	private String tenurePlan;
	private String joiningDate;
	private String memberNameTitle;
	private String memberFirstName;
	private String memberMiddleName;
	private String memberLastName;
	private String memberGender;
	private String memberMaritalStatus;
	private String memberDateOfBirth;
	private String memberAnniversaryDate;
	private String memberBloodGroup;
	private String memberAadharNumber;
	private int memberCountryIdCitizenship;
	private String memberPassportNumber;
	private String memberPanNumber;
	private String memberProfilePicture;
	private String memberEmail;
	private String memberPassword;
	private String memberAddress1;
	private String memberAddress2;
	private String memberAddress3;
	private int memberStateId;
	private String memberCityName;
	private String memberPincode;
	private String memberMobileNumber;
	private String memberPhoneNumber;
	private String memberBarcode;
	private String memberQrcode;
	private String companyLogo;
	private String memberOccupation;
	private String memberDesignation;
	private String memberCompanyName;
	private String memberBusinessNature;
	private String memberCompanyMobileNumber;
	private String memberCompanyPhoneNumber;
	private String memberFaxNumber;
	private String memberCompanyEmail;
	private String memberWebsiteName;
	private String memberCompanyDescription;
	private String memberCompanyKeywords;
	private String memberCompanyAddress1;
	private String memberCompanyAddress2;
	private String memberCompanyAddress3;
	private int memberCompanyStateId;
	private String memberCompanyCityName;
	private String memberCompanyPincode;
	private String memberCommunicationAddress;
	private String businessGoals;
	private String accomplishments;
	private String interests;
	private String networks;
	private String skills;
	private String idealReferral;
	private String topProduct;
	private String topProblemSolved;
	private String biomatric;
	private int sequence;
	private String status;
	private int createdBy;
	private String createdDate;
	private String ipAddress;
	private String memberFamilyTitleName;
	private String memberFamilyFirstName;
	private String memberFamilyLastName;
	private String id;
	private String firstName;
	private String lastName;
	private int rotaryYearId;
	private String rotaryYearTitle;
	private float billingAmount;
	private int currencyId;
	private String currencyCode;
	private String memberCategoryName;
	private String businessCategoryName;
	private int referenceGivenInside;
	private int referenceGivenOutside;
	private int referenceReceivedInside;
	private int referenceReceivedOutside;
	private int memberMeetingCount;
	private int memberBusinessTransactionCount;
	private String memberType;
	private String rotaryMemberId;
	private String rotaryClubName;
	private String rotaryChapName;
	private String clubName;
	
	private int VocationId;
	private String VocationTitle;
	private String VocationDesc;
	
	private int fellowship_id;
	private String fellowship_name;
	
	private int user_role_id;
	
	

	public int getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	public String getFellowship_name() {
		return fellowship_name;
	}

	public void setFellowship_name(String fellowship_name) {
		this.fellowship_name = fellowship_name;
	}

	public int getFellowship_id() {
		return fellowship_id;
	}

	public void setFellowship_id(int fellowship_id) {
		this.fellowship_id = fellowship_id;
	}

	public int getMemberId() {
		return memberId;
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public String getMembershipNumber() {
		return membershipNumber;
	}

	public int getMemberCategoryId() {
		return memberCategoryId;
	}

	public int getMemberTypeId() {
		return memberTypeId;
	}

	public String getTenurePlan() {
		return tenurePlan;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public String getMemberNameTitle() {
		return memberNameTitle;
	}

	public String getMemberFirstName() {
		return memberFirstName;
	}

	public String getMemberMiddleName() {
		return memberMiddleName;
	}

	public String getMemberLastName() {
		return memberLastName;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public String getMemberMaritalStatus() {
		return memberMaritalStatus;
	}

	public String getMemberDateOfBirth() {
		return memberDateOfBirth;
	}

	public String getMemberAnniversaryDate() {
		return memberAnniversaryDate;
	}

	public String getMemberBloodGroup() {
		return memberBloodGroup;
	}

	public String getMemberAadharNumber() {
		return memberAadharNumber;
	}

	public int getMemberCountryIdCitizenship() {
		return memberCountryIdCitizenship;
	}

	public String getMemberPassportNumber() {
		return memberPassportNumber;
	}

	public String getMemberPanNumber() {
		return memberPanNumber;
	}

	public String getMemberProfilePicture() {
		return memberProfilePicture;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public String getMemberPassword() {
		return memberPassword;
	}

	public String getMemberAddress1() {
		return memberAddress1;
	}

	public String getMemberAddress2() {
		return memberAddress2;
	}

	public String getMemberAddress3() {
		return memberAddress3;
	}

	public int getMemberStateId() {
		return memberStateId;
	}

	public String getMemberCityName() {
		return memberCityName;
	}

	public String getMemberPincode() {
		return memberPincode;
	}

	public String getMemberMobileNumber() {
		return memberMobileNumber;
	}

	public String getMemberPhoneNumber() {
		return memberPhoneNumber;
	}

	public String getMemberBarcode() {
		return memberBarcode;
	}

	public String getMemberQrcode() {
		return memberQrcode;
	}

	public String getMemberOccupation() {
		return memberOccupation;
	}

	public String getMemberDesignation() {
		return memberDesignation;
	}

	public String getMemberCompanyName() {
		return memberCompanyName;
	}

	public String getMemberBusinessNature() {
		return memberBusinessNature;
	}

	public String getMemberCompanyMobileNumber() {
		return memberCompanyMobileNumber;
	}

	public String getMemberCompanyPhoneNumber() {
		return memberCompanyPhoneNumber;
	}

	public String getMemberFaxNumber() {
		return memberFaxNumber;
	}

	public String getMemberCompanyEmail() {
		return memberCompanyEmail;
	}

	public String getMemberWebsiteName() {
		return memberWebsiteName;
	}

	public String getMemberCompanyAddress1() {
		return memberCompanyAddress1;
	}

	public String getMemberCompanyAddress2() {
		return memberCompanyAddress2;
	}

	public String getMemberCompanyAddress3() {
		return memberCompanyAddress3;
	}

	public int getMemberCompanyStateId() {
		return memberCompanyStateId;
	}

	public String getMemberCompanyCityName() {
		return memberCompanyCityName;
	}

	public String getMemberCompanyPincode() {
		return memberCompanyPincode;
	}

	public String getMemberCommunicationAddress() {
		return memberCommunicationAddress;
	}

	public String getBiomatric() {
		return biomatric;
	}

	public int getSequence() {
		return sequence;
	}

	public String getStatus() {
		return status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getMemberFamilyTitleName() {
		return memberFamilyTitleName;
	}

	public String getMemberFamilyFirstName() {
		return memberFamilyFirstName;
	}

	public String getMemberFamilyLastName() {
		return memberFamilyLastName;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRotaryYearId() {
		return rotaryYearId;
	}

	public String getRotaryYearTitle() {
		return rotaryYearTitle;
	}

	public float getBillingAmount() {
		return billingAmount;
	}

	public int getCurrencyId() {
		return currencyId;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public String getMemberCategoryName() {
		return memberCategoryName;
	}

	public String getMemberCompanyDescription() {
		return memberCompanyDescription;
	}

	public String getMemberCompanyKeywords() {
		return memberCompanyKeywords;
	}

	public String getBusinessGoals() {
		return businessGoals;
	}

	public String getAccomplishments() {
		return accomplishments;
	}

	public String getInterests() {
		return interests;
	}

	public String getNetworks() {
		return networks;
	}

	public String getSkills() {
		return skills;
	}

	public String getIdealReferral() {
		return idealReferral;
	}

	public String getTopProduct() {
		return topProduct;
	}

	public String getTopProblemSolved() {
		return topProblemSolved;
	}

	public int getBusinessCategoryId() {
		return businessCategoryId;
	}

	public String getBusinessCategoryName() {
		return businessCategoryName;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public int getClubId() {
		return clubId;
	}

	public void setMemberProfilePicture(String memberProfilePicture) {
		this.memberProfilePicture = memberProfilePicture;
	}

	public int getMemberMeetingCount() {
		return memberMeetingCount;
	}

	public int getMemberBusinessTransactionCount() {
		return memberBusinessTransactionCount;
	}

	public int getReferenceGivenInside() {
		return referenceGivenInside;
	}

	public int getReferenceGivenOutside() {
		return referenceGivenOutside;
	}

	public int getReferenceReceivedInside() {
		return referenceReceivedInside;
	}

	public int getReferenceReceivedOutside() {
		return referenceReceivedOutside;
	}

  public void setMemberEmail(String memberEmail) {
    this.memberEmail = memberEmail;
  }

  public void setMemberMobileNumber(String memberMobileNumber) {
    this.memberMobileNumber = memberMobileNumber;
  }

  public void setMemberLastName(String memberLastName) {
    this.memberLastName = memberLastName;
  }
  
  public void setMemberPassword(String memberPassword) {
    this.memberPassword = memberPassword;
  }

public String getMemberType() {
	return memberType;
}

public String getRotaryMemberId() {
	return rotaryMemberId;
}

public String getRotaryClubName() {
	return rotaryClubName;
}

public String getRotaryChapName() {
	return rotaryChapName;
}

public void setStatus(String status) {
	this.status = status;
}

public String getClubName() {
	return clubName;
}

public void setMemberType(String memberType) {
	this.memberType = memberType;
}

public void setUserTypeId(int userTypeId) {
	this.userTypeId = userTypeId;
}

public void setClubId(int clubId) {
	this.clubId = clubId;
}

public void setMemberCategoryId(int memberCategoryId) {
	this.memberCategoryId = memberCategoryId;
}

public void setMemberTypeId(int memberTypeId) {
	this.memberTypeId = memberTypeId;
}

public void setBusinessCategoryId(int businessCategoryId) {
	this.businessCategoryId = businessCategoryId;
}

public void setTenurePlan(String tenurePlan) {
	this.tenurePlan = tenurePlan;
}

public void setJoiningDate(String joiningDate) {
	this.joiningDate = joiningDate;
}

public void setMemberNameTitle(String memberNameTitle) {
	this.memberNameTitle = memberNameTitle;
}

public void setMemberMiddleName(String memberMiddleName) {
	this.memberMiddleName = memberMiddleName;
}

public void setMemberGender(String memberGender) {
	this.memberGender = memberGender;
}

public void setMemberMaritalStatus(String memberMaritalStatus) {
	this.memberMaritalStatus = memberMaritalStatus;
}

public void setMemberDateOfBirth(String memberDateOfBirth) {
	this.memberDateOfBirth = memberDateOfBirth;
}

public void setMemberAnniversaryDate(String memberAnniversaryDate) {
	this.memberAnniversaryDate = memberAnniversaryDate;
}

public void setMemberBloodGroup(String memberBloodGroup) {
	this.memberBloodGroup = memberBloodGroup;
}

public void setMemberAadharNumber(String memberAadharNumber) {
	this.memberAadharNumber = memberAadharNumber;
}

public void setMemberCountryIdCitizenship(int memberCountryIdCitizenship) {
	this.memberCountryIdCitizenship = memberCountryIdCitizenship;
}

public void setMemberPassportNumber(String memberPassportNumber) {
	this.memberPassportNumber = memberPassportNumber;
}

public void setMemberPanNumber(String memberPanNumber) {
	this.memberPanNumber = memberPanNumber;
}

public void setMemberAddress1(String memberAddress1) {
	this.memberAddress1 = memberAddress1;
}

public void setMemberAddress2(String memberAddress2) {
	this.memberAddress2 = memberAddress2;
}

public void setMemberAddress3(String memberAddress3) {
	this.memberAddress3 = memberAddress3;
}

public void setMemberStateId(int memberStateId) {
	this.memberStateId = memberStateId;
}

public void setMemberCityName(String memberCityName) {
	this.memberCityName = memberCityName;
}

public void setMemberPincode(String memberPincode) {
	this.memberPincode = memberPincode;
}

public void setMemberPhoneNumber(String memberPhoneNumber) {
	this.memberPhoneNumber = memberPhoneNumber;
}

public void setMemberBarcode(String memberBarcode) {
	this.memberBarcode = memberBarcode;
}

public void setMemberQrcode(String memberQrcode) {
	this.memberQrcode = memberQrcode;
}

public void setCompanyLogo(String companyLogo) {
	this.companyLogo = companyLogo;
}

public void setMemberOccupation(String memberOccupation) {
	this.memberOccupation = memberOccupation;
}

public void setMemberDesignation(String memberDesignation) {
	this.memberDesignation = memberDesignation;
}

public void setMemberCompanyName(String memberCompanyName) {
	this.memberCompanyName = memberCompanyName;
}

public void setMemberBusinessNature(String memberBusinessNature) {
	this.memberBusinessNature = memberBusinessNature;
}

public void setMemberCompanyMobileNumber(String memberCompanyMobileNumber) {
	this.memberCompanyMobileNumber = memberCompanyMobileNumber;
}

public void setMemberCompanyPhoneNumber(String memberCompanyPhoneNumber) {
	this.memberCompanyPhoneNumber = memberCompanyPhoneNumber;
}

public void setMemberFaxNumber(String memberFaxNumber) {
	this.memberFaxNumber = memberFaxNumber;
}

public void setMemberCompanyEmail(String memberCompanyEmail) {
	this.memberCompanyEmail = memberCompanyEmail;
}

public void setMemberWebsiteName(String memberWebsiteName) {
	this.memberWebsiteName = memberWebsiteName;
}

public void setMemberCompanyDescription(String memberCompanyDescription) {
	this.memberCompanyDescription = memberCompanyDescription;
}

public void setMemberCompanyKeywords(String memberCompanyKeywords) {
	this.memberCompanyKeywords = memberCompanyKeywords;
}

public void setMemberCompanyAddress1(String memberCompanyAddress1) {
	this.memberCompanyAddress1 = memberCompanyAddress1;
}

public void setMemberCompanyAddress2(String memberCompanyAddress2) {
	this.memberCompanyAddress2 = memberCompanyAddress2;
}

public void setMemberCompanyAddress3(String memberCompanyAddress3) {
	this.memberCompanyAddress3 = memberCompanyAddress3;
}

public void setMemberCompanyStateId(int memberCompanyStateId) {
	this.memberCompanyStateId = memberCompanyStateId;
}

public void setMemberCompanyCityName(String memberCompanyCityName) {
	this.memberCompanyCityName = memberCompanyCityName;
}

public void setMemberCompanyPincode(String memberCompanyPincode) {
	this.memberCompanyPincode = memberCompanyPincode;
}

public void setMemberCommunicationAddress(String memberCommunicationAddress) {
	this.memberCommunicationAddress = memberCommunicationAddress;
}

public void setBusinessGoals(String businessGoals) {
	this.businessGoals = businessGoals;
}

public void setAccomplishments(String accomplishments) {
	this.accomplishments = accomplishments;
}

public void setInterests(String interests) {
	this.interests = interests;
}

public void setNetworks(String networks) {
	this.networks = networks;
}

public void setSkills(String skills) {
	this.skills = skills;
}

public void setIdealReferral(String idealReferral) {
	this.idealReferral = idealReferral;
}

public void setTopProduct(String topProduct) {
	this.topProduct = topProduct;
}

public void setTopProblemSolved(String topProblemSolved) {
	this.topProblemSolved = topProblemSolved;
}

public void setBiomatric(String biomatric) {
	this.biomatric = biomatric;
}

public void setSequence(int sequence) {
	this.sequence = sequence;
}

public void setCreatedBy(int createdBy) {
	this.createdBy = createdBy;
}

public void setCreatedDate(String createdDate) {
	this.createdDate = createdDate;
}

public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
}

public void setMemberFamilyTitleName(String memberFamilyTitleName) {
	this.memberFamilyTitleName = memberFamilyTitleName;
}

public void setMemberFamilyFirstName(String memberFamilyFirstName) {
	this.memberFamilyFirstName = memberFamilyFirstName;
}

public void setMemberFamilyLastName(String memberFamilyLastName) {
	this.memberFamilyLastName = memberFamilyLastName;
}

public void setId(String id) {
	this.id = id;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public void setRotaryYearId(int rotaryYearId) {
	this.rotaryYearId = rotaryYearId;
}

public void setRotaryYearTitle(String rotaryYearTitle) {
	this.rotaryYearTitle = rotaryYearTitle;
}

public void setBillingAmount(float billingAmount) {
	this.billingAmount = billingAmount;
}

public void setCurrencyId(int currencyId) {
	this.currencyId = currencyId;
}

public void setCurrencyCode(String currencyCode) {
	this.currencyCode = currencyCode;
}

public void setMemberCategoryName(String memberCategoryName) {
	this.memberCategoryName = memberCategoryName;
}

public void setBusinessCategoryName(String businessCategoryName) {
	this.businessCategoryName = businessCategoryName;
}

public void setReferenceGivenInside(int referenceGivenInside) {
	this.referenceGivenInside = referenceGivenInside;
}

public void setReferenceGivenOutside(int referenceGivenOutside) {
	this.referenceGivenOutside = referenceGivenOutside;
}

public void setReferenceReceivedInside(int referenceReceivedInside) {
	this.referenceReceivedInside = referenceReceivedInside;
}

public void setReferenceReceivedOutside(int referenceReceivedOutside) {
	this.referenceReceivedOutside = referenceReceivedOutside;
}

public void setMemberMeetingCount(int memberMeetingCount) {
	this.memberMeetingCount = memberMeetingCount;
}

public void setMemberBusinessTransactionCount(int memberBusinessTransactionCount) {
	this.memberBusinessTransactionCount = memberBusinessTransactionCount;
}

public void setRotaryMemberId(String rotaryMemberId) {
	this.rotaryMemberId = rotaryMemberId;
}

public void setRotaryClubName(String rotaryClubName) {
	this.rotaryClubName = rotaryClubName;
}

public void setRotaryChapName(String rotaryChapName) {
	this.rotaryChapName = rotaryChapName;
}

public void setClubName(String clubName) {
	this.clubName = clubName;
}

public int getVocationId() {
	return VocationId;
}

public void setVocationId(int vocationId) {
	VocationId = vocationId;
}

public String getVocationTitle() {
	return VocationTitle;
}

public void setVocationTitle(String vocationTitle) {
	VocationTitle = vocationTitle;
}

public String getVocationDesc() {
	return VocationDesc;
}

public void setVocationDesc(String vocationDesc) {
	VocationDesc = vocationDesc;
}




}
