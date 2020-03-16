package aop;

public class TargetInterfaceImpl implements TargetInterface {

    @Override
    public String rockyTest1() {
        System.out.println("rocky test 1");
        return "rocky result";
    }

    @Override
    final public void rockyTest2() {
        System.out.println("rocky test 2");
    }

}
