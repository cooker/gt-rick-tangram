package com.github.cooker;

import com.sun.xml.internal.txw2.annotation.XmlCDATA;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * grant
 * 3/6/2020 10:09 上午
 * 描述：
 */
public class JVMTest {
    public static void main(String[] args) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(A.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
        A a = new A();
        a.setA("xxx");
        marshaller.marshal(a, System.out);
    }


    @XmlRootElement(name = "A")
    public static class A {
        private String a;

        @XmlElement
        public String getA() {
            return a;
        }
        @XmlCDATA
        public void setA(String a) {
            this.a = a;
        }
    }


    @Test
    public void sub(){
        //1 到 9 的数组
        List<Integer> datas = IntStream.range(1, 10).mapToObj(a->Integer.valueOf(a)).collect(
                Collectors.toList()
        );
        List<Integer> xx = datas.subList(0,8);
        System.out.println(xx);
        xx.clear();
        System.out.println(datas);
    }
}
