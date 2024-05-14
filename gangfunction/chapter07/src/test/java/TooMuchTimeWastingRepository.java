public class TooMuchTimeWastingRepository {
  TimeWastingInjectedRepository timeWastingInjectedRepository;

  public TooMuchTimeWastingRepository(TimeWastingInjectedRepository timeWastingInjectedRepository) {
    this.timeWastingInjectedRepository = timeWastingInjectedRepository;
    System.out.println("Repository called");
  }
  boolean run() {
    try {
      if (timeWastingInjectedRepository.run()) {
        Thread.sleep(100000);
        return true;
      }
    } catch (InterruptedException e) {
      System.out.println("Thread interrupted");
      return false;
    }
    return false;
  }
}

