package a_0_curr.Others_Done.package_private.pkg_2;
// cannot remove pkg_2, IDE won't allow and compile,
// so package structure and folder structure cannot be different, contrary to ChatGPT examples

public class HelperClass {
    public void publicMessage() {
        System.out.println("Hello from publicMessage - HelperClass!");
    }
    void packagePrivateMessage() { // packagePrivate
        System.out.println("Hello from packagePrivateMessage - HelperClass!");
    }
}
