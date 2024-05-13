import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RockSissorPaperMachineTest_BeforeEach {
  private Player player1;
  private Player player2;

  @BeforeEach
  void setUp() {
    player1 = new Player();
    player1.setName("player1");
    player2 = new Player();
    player2.setName("player2");
  }

  @Test
  void Rock_Win_Sissors() {
    //given
    player1.setHand("Rock");
    player2.setHand("Sissors");
    //when
    player1.duals(player2);
    //then
    Assertions.assertEquals(Message.WIN, player1.getResult().get(1));
  }
  @Test
  void Sissors_Win_Papers(){
    //given
    player1.setHand("Sissors");
    player2.setHand("Papers");
    //when
    player1.duals(player2);
    //then
    Assertions.assertEquals(Message.WIN, player1.getResult().get(1));
  }
  @Test
  void Papers_Win_Rock(){
    //given
    player1.setHand("Papers");
    player2.setHand("Rock");
    //when
    player1.duals(player2);
    //then
    Assertions.assertEquals(Message.WIN, player1.getResult().get(1));
  }

}
