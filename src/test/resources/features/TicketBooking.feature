Feature: Ticket Booking and Verification

  Scenario: Book and verify a ticket on the agent-stage platform
    Given I open the agent-stage yolobus
    And I log in with the phone number "9761325797" and OTP "1234"
    When I select source city "Delhi" with code "DEL"
    And I select destination city "Lucknow" with code "LUCK"
    And I choose the travel date "18"
    And I select 1 service
    And I select a seat
    And I confirm the seat selection
    Then I add passenger details name "Test", age "24", phonenumber "8178258097" and email "hs8091@gmail.com"
    Then I verify the total fare and seat tax
    And I confirm the booking
    And I verify the amount being paid
    And I proceed to the payment gateway
    Then I capture the ticket details




  Scenario: Book a ticket on the rider-stage platform
    Given I open the stage yolobus
    And I select source city "Delhi" with code "DEL" rider
    And I select destination city "Lucknow" with code "LUCK" rider
    And I choose the travel date "17" rider
    And I select 1 service rider
    And I select a seat rider
    When I log in with the phone number "9761325797" and OTP "1234" on rider
    Then I add passenger details name "Test", age "24", phonenumber "8178258074" and email "hs8091@gmail.com" rider
    Then I verify the total fare and seat tax rider
    And I confirm the booking rider
    And I verify the amount being paid rider
    And I proceed to the payment gateway rider
    Then I capture the ticket details rider




