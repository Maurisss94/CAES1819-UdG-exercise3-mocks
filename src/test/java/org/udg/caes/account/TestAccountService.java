package org.udg.caes.account;

import mockit.Expectations;
import mockit.Verifications;
import org.junit.jupiter.api.Test;

import mockit.Tested;
import mockit.Mocked;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountService {


  @Test
  void testTransfer(@Tested AccountService as, @Mocked AccountManager am)  {

    //Arrange
    Account acc1 = new Account("user1", 1000);
    Account acc2 = new Account("user2", 1000);
    as.setAccountManager(am);
    new Expectations() {{
      am.findAccount("user1"); result = acc1;
      am.findAccount("user2"); result = acc2;
    }};
    //Act
    as.transfer("user1", "user2", 250);

    //Assert
    assertEquals(750, acc1.getBalance());
    assertEquals(1250, acc2.getBalance());
    new Verifications(){{
      am.updateAccount(acc1); times = 1;
      am.updateAccount(acc2); times = 1;
    }};

  }
}