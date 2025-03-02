public class Question6a {

    // NumberPrinter class contains methods to print 0, even, and odd numbers
    static class NumberPrinter {
        // Method to print 0
        public synchronized void printZero() {
            System.out.print("0");
        }

        // Method to print even numbers
        public synchronized void printEven(int number) {
            System.out.print(number);
        }

        // Method to print odd numbers
        public synchronized void printOdd(int number) {
            System.out.print(number);
        }
    }

    // ZeroThread class to handle printing of 0
    static class ZeroThread extends Thread {
        private final NumberPrinter printer;

        public ZeroThread(NumberPrinter printer) {
            this.printer = printer;
        }

        @Override
        public void run() {
            // Print 0 first
            printer.printZero();
        }
    }

    // EvenThread class to handle printing of even numbers
    static class EvenThread extends Thread {
        private final NumberPrinter printer;
        private final int evenNumber;

        public EvenThread(NumberPrinter printer, int evenNumber) {
            this.printer = printer;
            this.evenNumber = evenNumber;
        }

        @Override
        public void run() {
            // Print the even number
            printer.printEven(evenNumber);
        }
    }

    // OddThread class to handle printing of odd numbers
    static class OddThread extends Thread {
        private final NumberPrinter printer;
        private final int oddNumber;

        public OddThread(NumberPrinter printer, int oddNumber) {
            this.printer = printer;
            this.oddNumber = oddNumber;
        }

        @Override
        public void run() {
            // Print the odd number
            printer.printOdd(oddNumber);
        }
    }

    // ThreadController to manage and synchronize the threads
    static class ThreadController {
        private final NumberPrinter printer;
        private int n;

        public ThreadController(NumberPrinter printer, int n) {
            this.printer = printer;
            this.n = n;
        }

        public void startPrinting() throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                // ZeroThread: Print 0 first
                ZeroThread zeroThread = new ZeroThread(printer);
                zeroThread.start();
                zeroThread.join(); // Wait for ZeroThread to finish before moving on

                // EvenThread: Print the even number (if it's even)
                if (i % 2 == 0) {
                    EvenThread evenThread = new EvenThread(printer, i);
                    evenThread.start();
                    evenThread.join();
                }

                // OddThread: Print the odd number (if it's odd)
                if (i % 2 != 0) {
                    OddThread oddThread = new OddThread(printer, i);
                    oddThread.start();
                    oddThread.join();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NumberPrinter printer = new NumberPrinter();
        int n = 5; // You can change n to test with other values
        ThreadController controller = new ThreadController(printer, n);
        controller.startPrinting();
    }
}
