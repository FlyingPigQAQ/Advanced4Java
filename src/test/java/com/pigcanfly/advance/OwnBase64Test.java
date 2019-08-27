package com.pigcanfly.advance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Tobby Quinn
 * @date 2019/08/27
 */
@DisplayName("测试自定义Base64算法准确性")
class OwnBase64Test {
    private static final OwnBase64 own = new OwnBase64();
    private static final Base64.Encoder encoder = Base64.getEncoder();


    @ParameterizedTest
    @ValueSource(strings={"","f","fo","foo","foob","fooba","foobar"})
    public void enc1TheirTest(String str) {
        byte[] bytes = str.getBytes();
        byte[] encode = encoder.encode(bytes);
        String ownEnc = own.encode(bytes);
        assertEquals(new String(encode), ownEnc);
    }

    @Test
    public void enc1Test() {
        byte[] bytes = "".getBytes();
        String enc = own.encode(bytes);
        assertEquals("", enc);
    }

    @Test
    public void enc2Test() {
        byte[] bytes = "f".getBytes();
        String enc = own.encode(bytes);
        assertEquals("Zg==", enc);
    }

    @Test
    public void enc3Test() {
        byte[] bytes = "fo".getBytes();
        String enc = own.encode(bytes);
        assertEquals("Zm8=", enc);
    }

    @Test
    public void enc4Test() {
        byte[] bytes = "foo".getBytes();
        String enc = own.encode(bytes);
        assertEquals("Zm9v", enc);
    }

    @Test
    public void enc5Test() {
        byte[] bytes = "foob".getBytes();
        String enc = own.encode(bytes);
        assertEquals("Zm9vYg==", enc);
    }
    @Test
    public void enc6Test() {
        byte[] bytes = "fooba".getBytes();
        String enc = own.encode(bytes);
        assertEquals("Zm9vYmE=", enc);
    }
    @Test
    public void enc7Test() {
        byte[] bytes = "foobar".getBytes();
        String enc = own.encode(bytes);
        assertEquals("Zm9vYmFy", enc);
    }

}