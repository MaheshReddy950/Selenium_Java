@SingupFlow
Feature: Member Sign up Flow

  Scenario: New Member Sign up Flow
    Given User launches chrome browser
    Then User enters email address
      | Email | mahesh1@yopmail.com |
    Then Click on Mail Button
    Then Enters signup details
      | First Name       | Mahesh    |
      | Last Name        | Reddy     |
      | Password         | Mahesh123 |
      | Re-type password | Mahesh123 |
    Then Click on Signup Button
    Then User enters OTP
    Then Click on Verify Button
    Then Enter role details
      | Role            | Current Student                           |
      | Joining Year    | 2003                                      |
      | Graduation Year | 2022                                      |
      | Checkbox1       | I have read and agree to the              |
      | Checkbox2       | I confirm that I have read and understood |
    Then Click on Join Alumni Network Button
    Then Enter Basic Details
      | City         | Hyderabad |
      | Country Code | +91 India |
      | Phone Num    | 6434647534 |
    Then Click on Next Step Button
    Then Add Profile Photo
      | Path | C:\Users\Admin\OneDrive\Documents\mahesh.jpg |
    Then Click on Save Button
    Then Click on Save Changes Button
    Then Click on Go To Your Profile Button
    Then User clicks on Three Dots Menu
    Then User clicks on Logout Button

  Scenario: Login Existing User Flow
    Then Click on join button
    Then User enters email address
      | Email |  |
    Then Click on Mail Button
    Then User enters Password address
    Then Click on Login Button
    Then User closes browser
    
    
    