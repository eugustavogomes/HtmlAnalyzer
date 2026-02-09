import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;

public class HtmlAnalyzer {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("URL connection error");
            return;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new URL(args[0]).openStream()));
            String result = parseHtml(reader);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("URL connection error");
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {}
        }
    }

    static String parseHtml(BufferedReader reader) throws IOException {
        int depth = 0;
        int maxDepth = -1;
        String result = null;
        Stack<String> tagStack = new Stack<>();
        String line;
        boolean malformed = false;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            if (line.startsWith("<") && !line.startsWith("</")) {
                String tag = line.replaceAll("[<>]", "");
                tagStack.push(tag);
                depth++;
            } else if (line.startsWith("</")) {
                String tag = line.replaceAll("[</>]", "");
                if (tagStack.isEmpty() || !tagStack.peek().equals(tag)) {
                    malformed = true;
                    break;
                }
                tagStack.pop();
                depth--;
            } else {
                if (depth > maxDepth) {
                    maxDepth = depth;
                    result = line;
                }
            }
        }
        if (malformed || !tagStack.isEmpty()) return "malformed HTML";
        return result != null ? result : "";
    }
}