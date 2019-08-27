package com.pigcanfly.advance;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Tobby Quinn
 * @date 2019/08/13
 */
public class HashCodeAndEquals {

    static class PersonWithoutOverrideHashCodeAndEquals {
        String name;
        int id;

        public PersonWithoutOverrideHashCodeAndEquals(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }

    static class PersonOnlyOverrideHashCode {
        String name;
        int id;

        public PersonOnlyOverrideHashCode(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public int hashCode() {
            //hashcode 默认取得是对象地址，是native方法
            return id + name.hashCode();
        }
    }

    static class PersonOnlyOverrideEquals {
        String name;
        int id;

        public PersonOnlyOverrideEquals(String name, int id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            //如果要比较的对象为空，或者对象本身不属于该类实例，则返回false
            if (obj == null || !obj.getClass().equals(PersonOnlyOverrideEquals.class)) {
                return false;
            }
            //如果比较的对象引用和对象本身引用相同，则返回true
            if (this == obj) {
                return true;
            }
            PersonOnlyOverrideEquals pe = (PersonOnlyOverrideEquals) obj;
            if (this.id == pe.id && this.name.equals(pe.name)) {
                return true;
            }
            return false;

        }
    }

    static class PersonStandard {
        String name;
        int id;
        public PersonStandard() {
        }
        public PersonStandard(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            if(name==null){
                return id;
            }
            return id+name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            //如果要比较的对象为空，或者对象本身不属于该类实例，则返回false
            if (obj == null || !obj.getClass().equals(PersonStandard.class)) {
                return false;
            }
            //如果比较的对象引用和对象本身引用相同，则返回true
            if (this == obj) {
                return true;
            }
            PersonStandard pe = (PersonStandard) obj;
            if(this.name==null){
                if(this.id == pe.id){
                    return true;
                }
            }
            if (this.id == pe.id && this.name.equals(pe.name)) {
                return true;
            }
            return false;

        }
    }
    /**
     * 将各个类对象放入Set去重集合中，并查看hashCode() 和 equals() 方法对结果的影响
     * Set以及HashMap去重规则，如果hash不同，则直接返回不执行equals().代码如下：
     * if (e.hash == hash &&  ((k = e.key) == key || (key != null && key.equals(k))))
     *
     * 规则：
     * ①hashcode相同，不代表equals相同
     * ②equals相同，则hashcode必然相同
     * ③重写equals()的同时，必须重写hashCode()
     * @param args
     */
    public static void main(String[] args) {
        //结果为3，在未重写hashCode()和equals()方法时候，Set集合无法理想的去重
        Set nonOverrideHashCodeAndEquals = new HashSet();
        nonOverrideHashCodeAndEquals.add(new PersonWithoutOverrideHashCodeAndEquals("张三", 1));
        nonOverrideHashCodeAndEquals.add(new PersonWithoutOverrideHashCodeAndEquals("张三", 1));
        nonOverrideHashCodeAndEquals.add(new PersonWithoutOverrideHashCodeAndEquals("张三", 1));
        System.out.println(nonOverrideHashCodeAndEquals.size());

        //结果为3，虽然重写了hashCode(),但当各对象的hashcode值冲突时候，没有办法使用equals()做比较
        Set onlyOverrideHashCode = new HashSet();
        onlyOverrideHashCode.add(new PersonOnlyOverrideHashCode("张三", 1));
        onlyOverrideHashCode.add(new PersonOnlyOverrideHashCode("张三", 1));
        onlyOverrideHashCode.add(new PersonOnlyOverrideHashCode("张三", 1));
        System.out.println(onlyOverrideHashCode.size());

        //结果为3，虽然重写了equals(),但各对象的hashcode值不同
        Set personOnlyOverrideEquals = new HashSet();
        personOnlyOverrideEquals.add(new PersonOnlyOverrideEquals("张三", 1));
        personOnlyOverrideEquals.add(new PersonOnlyOverrideEquals("张三", 1));
        personOnlyOverrideEquals.add(new PersonOnlyOverrideEquals("张三", 1));
        System.out.println(personOnlyOverrideEquals.size());

        //结果为1
        Set personStandard = new HashSet();
        personStandard.add(new PersonStandard("张三", 1));
        personStandard.add(new PersonStandard("张三", 1));
        personStandard.add(new PersonStandard("张三", 1));
        System.out.println(personStandard.size());

        Set personStandard1 = new HashSet();
        PersonStandard p1 = new PersonStandard();
        p1.setId(1);
        personStandard1.add(p1);

        PersonStandard p2 = new PersonStandard();
        p2.setId(1);
        personStandard1.add(p2);



        System.out.println(personStandard1.size());
    }
}
