package pzt.buffers.ringbuffer;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class RingBuffer{//RingBuffer无锁环形缓冲，一开始考虑错方向为生产者-消费者模型，但是经过分析发现不是，应该是考的是java高速缓存,时间有限，写了很多伪代码

    private final static int bufferSize = 1024;
    private String[] buffer = new String[bufferSize];
    private int head = 0;
    private int tail = 0;

    private Boolean empty() {
        return head == tail;
    }
    private Boolean full() {
        return (tail + 1) % bufferSize == head;
    }
    public Boolean analyse(String v) {//解析图片
        if (full()) {
            return false;
        }else {
            analyseImage();

        }
        buffer[tail] = v;
        tail = (tail + 1) % bufferSize;
        return true;
    }
    public String show(){//显示图片
        if (empty()) {
            return null;
        }
    }
        String result = buffer[head];
        if(result!=null){
            displaying frame 30;//显示图片
        }
        head = (head + 1) % bufferSize;
        return result;
    }
    public String[] showAll() {
        if (empty()) {
            return new String[0];
        }
        int copyTail = tail;
        int cnt = head < copyTail ? copyTail - head : bufferSize - head + copyTail;
        String[] result = new String[cnt];
        if (head < copyTail) {
            for (int i = head; i < copyTail; i++) {
                result[i - head] = buffer[i];
            }
        } else {
            for (int i = head; i < bufferSize; i++) {
                result[i - head] = buffer[i];
            }
            for (int i = 0; i < copyTail; i++) {
                result[bufferSize - head + i] = buffer[i];
            }
        }
        head = copyTail;
        return result;
    }
}