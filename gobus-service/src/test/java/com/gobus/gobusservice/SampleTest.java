package com.gobus.gobusservice;

import com.gobus.gobusservice.service.UserUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class SampleTest {

    @Test
    public void JunitMethod(){
        System.out.println("Executing junit test cases");
    }

    @Test
    @SneakyThrows
    void passwordEncode() {
        System.out.println(UserUtils.convertToMD5("1234"));
    }

    public static void main(String[] args) {

    }
}
