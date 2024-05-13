import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RockSissorPaperMachineTest_Original {

  @Test
  void Rock_Win_Sissors() {
    //given
    Player player = new Player();
    player.setName("player1");
    player.setHand("Rock");

    Player player2 = new Player();
    player2.setName("player2");
    player2.setHand("Sissors");
    //when
    player.duals(player2);
    //then
    Assertions.assertEquals(Message.WIN, player.getResult().get(1));
  }
  @Test
  void Sissors_Win_Papers(){
    //given
    Player player = new Player();
    player.setName("player1");
    player.setHand("Sissors");

    Player player2 = new Player();
    player2.setName("player2");
    player2.setHand("Papers");
    //when
    player.duals(player2);
    //then
    Assertions.assertEquals(Message.WIN, player.getResult().get(1));
  }
  @Test
  void Papers_Win_Rock(){
    //given
    Player player = new Player();
    player.setName("player1");
    player.setHand("Papers");

    Player player2 = new Player();
    player2.setName("player2");
    player2.setHand("Rock");
    //when
    player.duals(player2);
    //then
    Assertions.assertEquals(Message.WIN, player.getResult().get(1));
  }

}
