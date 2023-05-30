# Encrypter
It is a final project of Java Syntax course (JavaRush).

My project implements classic Caesar cipher, there are only Latin letters in the alphabet.

BRUTE FORCE has been the most difficult thing which I had to do in this project, that is why it looks so difficult in my code.

In this project I tried to implement all my knowledge which I got from JavaRush and another courses.

I have made emphasis on project reliability. I tried to achieve this purpose by implementing the "Validate" class and many test classes. Also it is a cause why I have made BRUTE FORCE implementation by analyze key words and writing to file all possible variants, because if the source file contains only one word, or several word, e.g. "Hello world", our program won't be working correctly.

My project has builded with implementation of SOLID principles.

JUnit tests and Mockito have been implemented in this project.

Oleksandr, could you pay attention especially on project structure and variables and method names, because it is my weakness, I think. It will be really helpful for me, if you give me some hint, how I can improve my skills. If you will have some time and good mood I would be thanking for you for checking test class with Mockito:)

There is some problem which I haven't solved. It is passing a key to the method when a BRUTE FORCE command is needed. Especially since we don't need any key with brute force command starts, but my method is universal for all commands ENCRYPT, DECRYPT and BRUTE FORCE and I have just passed some random number at all. Please, could you give me some hint how make it better. You can see this code in main method - line 41 - (int key = 1; // it doesn't matter what number provided here, it doesn't take part in Brute Force process, but method needs this parameter.)
