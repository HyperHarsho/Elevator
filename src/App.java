import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    int startFloor;
    int currentFloor;
    int floors;
    List<Integer> starts = new ArrayList<>();
    List<Integer> destination = new ArrayList<>();

    private void input(App app) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the number of floors");
        app.floors = in.nextInt();
        while (true) {
            System.out.println("Enter the starting floor of the elevator");
            int f = in.nextInt();
            if (f > app.floors || f < 0) {
                System.out.println(
                        "Starting floor of the elevator should be between 0 and " + app.floors + "(including both)");
                continue;
            } else {
                app.startFloor = f;
                break;
            }
        }
        System.out.println("Enter number of travels");
        int t = in.nextInt();
        int i = 1;
        while (i <= t) {
            System.out.println(i + ") Enter the start and destination floor of humans(Format- start end)");
            int start = in.nextInt();
            int end = in.nextInt();
            if (start < 0 || start > app.floors || end < 0 || end > app.floors) {
                System.out.println(
                        "Start and destination floor should be between 0 and " + app.floors + "(including both)");
                continue;
            }
            starts.add(start);
            destination.add(end);
            if (t > 10) {
                System.out.println("Do you want to continue to enter more(y/n)");
                char c = in.next().charAt(0);
                if (c == 'n') {
                    break;
                }
            }
            i++;
        }
        in.close();
    }

    public String complie(App app) {
        String ans = String.valueOf(app.startFloor);
        while (!(app.starts.isEmpty() && app.destination.isEmpty())) {
            int nearestStart = 0, destination = 0;
            if (!app.starts.isEmpty()) {
                nearestStart = app.starts.iterator().next();
                for (int i = 0; i < app.starts.size(); i++) {
                    if (Math.abs(app.currentFloor - nearestStart) > Math.abs(nearestStart - app.starts.get(i))) {
                        nearestStart = app.starts.get(i);
                    }
                }
                while (app.currentFloor != nearestStart) {
                    if (nearestStart > app.currentFloor) {
                        app.currentFloor += 1;
                    } else {
                        app.currentFloor -= 1;
                    }
                    if (app.starts.contains(Integer.valueOf(app.currentFloor))) {
                        app.starts.remove(Integer.valueOf(app.currentFloor));
                    }
                    ans += "->" + String.valueOf(app.currentFloor);
                }
            }
            if (!app.destination.isEmpty()) {
                destination = app.destination.iterator().next();
                for (int i = 0; i < app.destination.size(); i++) {
                    if (Math.abs(app.currentFloor - destination) > Math.abs(destination - app.destination.get(i))) {
                        destination = app.destination.get(i);
                    }
                }
                while (app.currentFloor != destination) {
                    if (destination > app.currentFloor) {
                        app.currentFloor += 1;
                    } else {
                        app.currentFloor -= 1;
                    }
                    if (app.starts.contains(Integer.valueOf(app.currentFloor))) {
                        app.starts.remove(Integer.valueOf(app.currentFloor));
                    }
                    ans += "->" + String.valueOf(app.currentFloor);
                }
                app.destination.remove(Integer.valueOf(destination));
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        app.input(app);
        System.out.println(app.complie(app));
    }
}
