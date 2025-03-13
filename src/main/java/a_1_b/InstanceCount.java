package a_1_b;

public class InstanceCount {

    // Static variable to keep track of the number of instances.
    private static int instanceCount = 0;

    // Constructor
    public InstanceCount() {
        // Increment the instance count whenever a new instance is created.
        instanceCount++;
    }

    // Static method to get the number of instances.
    public static int getInstanceCount() {
        return instanceCount;
    }

    // Optionally, if you need to manage object deletion manually, consider using a finalize method.
    // Though, be cautious because relying on finalize() can be problematic and is generally discouraged.
    @Override
    protected void finalize() throws Throwable {
        instanceCount--;
        super.finalize();
    }

    // Main method for testing.
    public static void main(String[] args) {
        new InstanceCount();
        new InstanceCount();
        new InstanceCount();

        System.out.println("Number of instances: " + InstanceCount.getInstanceCount());
    }
}

