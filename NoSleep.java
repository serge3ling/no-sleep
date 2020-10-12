import java.awt.Robot;
import java.awt.Point;
import java.awt.MouseInfo;
import java.awt.AWTException;

public class NoSleep {
  public static final int MOUSE_STEP = 2;
  public static final int DELAY = 59000;
  public static final int DELAY_FACTOR = 4;
  
  private Robot robot;
  
  public NoSleep() {
    try {
      robot = new Robot();
    } catch (AWTException e) {
      System.err.println("Robot not made.");
    }
  }
  
  public static void main(String[] args) {
    System.out.println("NoSleep started.");
    NoSleep noSleep = new NoSleep();
    if (noSleep.canRunRobot()) {
      noSleep.mouseMoveLoop();
    }
  }
  
  private boolean canRunRobot() {
    return (robot != null);
  }
  
  public void mouseMoveLoop() {
    while (true) {
      Point point = MouseInfo.getPointerInfo().getLocation();
      int mouseX = makeCoordinate(point.x);
      int mouseY = makeCoordinate(point.y);
      robot.mouseMove(mouseX, mouseY);
      delay();
    }
  }
  
  private int makeCoordinate(int val) {
    return (val < MOUSE_STEP) ? (val - MOUSE_STEP) : (val + MOUSE_STEP);
  }
  
  private void delay() {
    for (int i = 0; i < DELAY_FACTOR; i++) {
      robot.delay(DELAY);
    }
  }
}

