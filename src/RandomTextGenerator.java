/*
1) Moby Dick
2) Sherlock Holmes
3) The Great Gatsby
4) A Doll's House
5) Ulysses
Enter the number of the text you want to generate a random string from: 1

Enter the length of the return text: 50

Enter the level of analysis: 20

(in result file)
ection that he has a nose to be pulled. A pestilen


1) Moby Dick
2) Sherlock Holmes
3) The Great Gatsby
4) A Doll's House
5) Ulysses
Enter the number of the text you want to generate a random string from: 2

Enter the length of the return text: 384

Enter the level of analysis: 64

(in result file)
ld inform me whether it answered
to the description of any of their travellers. I had already noticed
the peculiarities of the typewriter, and I wrote to the man himself at
his business address asking him if he would come here. As I expected,
his reply was typewritten and revealed the same trivial but
characteristic defects. The same post brought me a letter from
Westhouse &


1) Moby Dick
2) Sherlock Holmes
3) The Great Gatsby
4) A Doll's House
5) Ulysses
Enter the number of the text you want to generate a random string from: 4

Enter the length of the return text: 500

Enter the level of analysis: 30

(in result file)
 bit. But perhaps I had better go—for ever?

NORA.
No, indeed, you shall not. Of course you must come here just as before.
You know very well Torvald can’t do without you.

RANK.
Yes, but you?

NORA.
Oh, I am always tremendously pleased when you come.

RANK.
It is just that, that put me on the wrong track. You are a riddle to
me. I have often thought that you would almost as soon be in my company
as in Helmer’s.

NORA.
Yes—you see there are some people one loves best, and other

 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class RandomTextGenerator {

    public static void generateText(int k, int length, String source, String result) throws FileNotFoundException {
        if (k <= 0 || length <= 0)
            throw new RuntimeException("Invalid Parameters");
        String content = new Scanner(new File(source)).useDelimiter("\\Z").next();
        PrintWriter fWriter = new PrintWriter(result);
        int bookLength = content.length();
        if (bookLength <= k)
            throw new RuntimeException("Book length has to be greater than level of analysis");
        int startIndex = (int) (Math.random() * bookLength);
        String seed = content.substring(startIndex, startIndex + k);
        fWriter.print(seed);
        int i = k;
        while (i < length) {
            ArrayList<Integer> follows = new ArrayList<>();
            for (int j = 0; j < bookLength - k; j++) {
                if (content.substring(j, j + k).equals(seed)) {
                    follows.add(j + k);
                }
            }
            if (follows.size() == 0) {
                startIndex = (int) (Math.random() * bookLength);
                seed = content.substring(startIndex, startIndex + k);
                if (k < length - i) {
                    fWriter.print(seed);
                    i += k;
                } else {
                    fWriter.print(seed.substring(0, length - k));
                    i += k;
                }
            } else {
                int chosenIndex = (int) (Math.random() * follows.size());
                seed = seed.substring(1) + content.substring(follows.get(chosenIndex), follows.get(chosenIndex) + 1);
                fWriter.print(seed.substring(k - 1));
                i++;
            }
        }
        fWriter.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        System.out.println("1) Moby Dick");
        System.out.println("2) Sherlock Holmes");
        System.out.println("3) The Great Gatsby");
        System.out.println("4) A Doll's House");
        System.out.println("5) Ulysses");
        System.out.print("Enter the number of the text you want to generate a random string from: ");
        int choice = console.nextInt();
        System.out.println();
        System.out.print("Enter the length of the return text: ");
        int length = console.nextInt();
        System.out.println();
        System.out.print("Enter the level of analysis: ");
        int level = console.nextInt();
        if (choice == 1) {
            generateText(level, length, "MobyDick.txt", "resultFile.txt");
        } else if (choice == 2) {
            generateText(level, length, "SherlockHolmes.txt", "resultFile.txt");
        } else if (choice == 3) {
            generateText(level, length, "TheGreatGatsby.txt", "resultFile.txt");
        } else if (choice == 4) {
            generateText(level, length, "ADollsHouse.txt", "resultFile.txt");
        } else if (choice == 5) {
            generateText(level, length, "Ulysses.txt", "resultFile.txt");
        }
    }
}
