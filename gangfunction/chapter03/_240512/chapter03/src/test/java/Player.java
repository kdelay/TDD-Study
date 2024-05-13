import java.util.HashMap;
import java.util.Map;

public class Player {

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  String name;

  public String getHand() {
    return hand;
  }

  public void setHand(String hand) {
    this.hand = hand;
  }

  String hand;

  public Map<Integer, Message> getResult() {
    return result;
  }

  public void setResult(Map<Integer, Message> result) {
    this.result = result;
  }

  Map<Integer, Message> result;

  public Player() {
    this.result = new HashMap<>();
  }

  public void duals(Player player) {
    if (this.hand.equals("Rock") && player.getHand().equals("Sissors")) {
      result.put(1, Message.WIN);
    }
    if(this.hand.equals("Sissors") && player.getHand().equals("Papers")) {
      result.put(1, Message.WIN);
    }
    if(this.hand.equals("Papers") && player.getHand().equals("Rock")) {
      result.put(1, Message.WIN);
    }
  }

}
