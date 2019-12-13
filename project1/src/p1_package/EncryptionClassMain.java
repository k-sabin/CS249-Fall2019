package p1_package;

public class EncryptionClassMain
{
    public static void main(String[] args)
    {
        EncryptionClass ec = new EncryptionClass();
        EncryptionClass ec2 = new EncryptionClass();

        System.out.println("Encrypting String: \"We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness.\"");
        ec.encryptData("We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness.");
        System.out.println("displaying char array\n");

        ec.displayCharArray();

        ec.downloadData("TestFile.txt");

        ec2.uploadData("TestFile.txt");

        java.lang.String testString = ec2.decryptData();

        System.out.println(testString);

    }
}
