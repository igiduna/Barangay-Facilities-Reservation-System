public class ClearConsole {
    public static void clearConsole() {
        try {

            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", "cls");
            processBuilder.inheritIO().start().waitFor();

        } catch (Exception e) {

        }
    }
}
