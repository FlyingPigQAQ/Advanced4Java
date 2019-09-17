package com.pigcanfly.advance.rxjava2.scheduler;


import io.reactivex.Observable;

import java.util.ArrayList;

/**
 * @author tobbyquinn
 * @date 2019/09/11
 */
public class RxFlatmap {

     static class  User{
         private String name;

         public User(String name) {
             this.name = name;
         }

         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }
     }
    public static void main(String[] args) {
        ArrayList<User> oris = new ArrayList<>();
        User u1 = new User("zhangsan");
        User u2 = new User("List");
        User u3 = new User("wangwu");
        oris.add(u1);
        oris.add(u2);
        oris.add(u3);
        Observable.just(oris).flatMap(arr->{
            return Observable.fromIterable(arr);
        }).subscribe(user->{
            System.out.println(user.getName());
        });
    }
}
