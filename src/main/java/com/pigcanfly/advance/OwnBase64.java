package com.pigcanfly.advance;

/**
 * @author Tobby Quinn
 * @date 2019/08/26
 */
public class OwnBase64 {
    private final static char[] DICTS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private final static char PADDING = '=';

    public String encode(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte remain = 0;
        int remainSize = bytes.length % 3;
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            int cal = 0, tmp = 0;
            switch (i % 3) {
                case 0:
                    cal = b >> 2;
                    sb.append(DICTS[cal]);
                    remain = (byte) (b & 0x3);
                    break;
                case 1:
                    cal = b >> 4;
                    tmp = (remain << 4) + cal;
                    sb.append(DICTS[tmp]);
                    remain = (byte) (b & 0xF);
                    break;
                case 2:
                    cal = b >> 6;
                    tmp = (remain << 2) + cal;
                    sb.append(DICTS[tmp]);
                    //剩余三个字节为一个整体，第三个字节的后6个bit
                    sb.append(DICTS[b&0x3F]);
                    break;
                default:
                    break;
            }

        }
        switch (remainSize) {
            case 1:
                sb.append(DICTS[remain << 4]);
                sb.append("=");
                sb.append("=");
                break;
            case 2:
                sb.append(DICTS[remain << 2]);
                sb.append("=");
                break;
            default:
                break;
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        OwnBase64 obj = new OwnBase64();
//        byte[] bytes = {0x14,0b11111111,(byte)0x9c,0x03,(byte)0xd9,0x7e};
        byte[] bytes = "f".getBytes();
        System.out.println(obj.encode(bytes));
    }

}
