public class TimeWastingInjectedRepository {

  public TimeWastingInjectedRepository() {
    System.out.println("2nd Repository called");
  }
  boolean run(){
    try{
      Thread.sleep(100000);
      return true;
    }
    catch(InterruptedException e){
      System.out.println("Thread interrupted");
      return false;
    }
  }
}
