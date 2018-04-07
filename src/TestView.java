import View.GameView;
import java.io.IOException;
import java.util.List;

public class TestView {

	public static void main(String[] args)
			throws IOException, InterruptedException {
		GameView view = new GameView();

        view.getTextPanel().getTextField().setEditable(true);
		view.getTextPanel().printToTextArea("Hello World!");

        // Blocking synchronized code. Makes program wait for textField Input
        final List<String> holder = view.getTextPanel().getHolder();
        synchronized (holder) {

            // wait for input from field
            while (holder.isEmpty()) {
                holder.wait();
            }
            holder.remove(0);
        }
        view.getTextPanel().printToTextArea("Done");

    }
}
