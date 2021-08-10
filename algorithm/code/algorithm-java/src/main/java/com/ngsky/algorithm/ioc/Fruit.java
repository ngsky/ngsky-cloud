package com.ngsky.algorithm.ioc;

public interface Fruit {
    public abstract void eat();
}

class Apple implements Fruit {
    public void eat(){
        System.out.println("Apple");
    }
}

class Orange implements Fruit{
    public void eat(){
        System.out.println("Orange");
    }
}

class Factory{
    public static Fruit getInstance(String className){
        Fruit f = null;
        try{
            f = (Fruit)Class.forName(className).newInstance();
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }
}

class Client{
    public static void main(String[] args){
        Fruit f = Factory.getInstance("com.ngsky.algorithm.ioc.Apple");
        if(f!=null){
            f.eat();
        }
    }
}




