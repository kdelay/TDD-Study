
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RockSissorPaperMachineTest_Mockito {

  @Spy
  private Player player1;
  @Spy
  private Player player2;


  @Test
  void Winning_Condition() {
    //arrange
    when(player1.getResult()).thenReturn(Map.of(1, Message.WIN));
    //act
    player1.duals(player2);
    //assert
    verify(player1).duals(player2);
    Assertions.assertEquals(Message.WIN, player1.getResult().get(1));
  }

  @Test
  void Rock_Win_Sissors() {
    //Arrange
    Map<Integer, Message> dummyResult = new HashMap<>();
    dummyResult.put(1, Message.WIN);
    // Act
    player1.setHand("Rock");
    player2.setHand("Sissors");
    player1.duals(player2);

    // Assert
    verify(player1).setHand("Rock");
    verify(player2).setHand("Sissors");
    verify(player1).duals(player2);
    Assertions.assertEquals(dummyResult, player1.getResult());
  }

  @Test
  void Sissors_Win_Papers() {
    Map<Integer, Message> dummyResult = new HashMap<>();
    dummyResult.put(1, Message.WIN);
    // Act
    player1.setHand("Sissors");
    player2.setHand("Papers");
    player1.duals(player2);

    // Assert
    verify(player1).setHand("Sissors");
    verify(player2).setHand("Papers");
    verify(player1).duals(player2);
    Assertions.assertEquals(dummyResult, player1.getResult());
  }

  @Test
  void Papers_Win_Rock() {
    Map<Integer, Message> dummyResult = new HashMap<>();
    dummyResult.put(1, Message.WIN);
    // Act
    player1.setHand("Papers");
    player2.setHand("Rock");
    player1.duals(player2);

    // Assert
    verify(player1).setHand("Papers");
    verify(player2).setHand("Rock");
    verify(player1).duals(player2);
    Assertions.assertEquals(dummyResult, player1.getResult());
  }

}
