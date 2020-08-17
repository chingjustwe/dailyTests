package cn.com.nightfield.lambda;

import java.util.stream.Stream;

/**
 * @author: zhochi
 * @create: 2020/3/23
 **/
public class ImmutableTest {
    public static void main(String[] args) {
        String[] array = {"a", "b", "c"};
        for(int i = 1; i<4; i++){
            // should be declared as final
            int finalI = i;
            Stream.of(array).map(item -> finalI).forEach(System.out::println);
        }

        for(String i : array){
            // because i never changed here, so no need to declare a finalI
            Stream.of(array).map(item -> i).forEach(System.out::println);
        }
    }
}
