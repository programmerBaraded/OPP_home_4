import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;

/**
     Домашняя работа, задача:
     ========================

     a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
     b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта,
     поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
     c. Для хранения фруктов внутри коробки можно использовать ArrayList;
     d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество:
     вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
     e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую
     подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
     Можно сравнивать коробки с яблоками и апельсинами;
     f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
     Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
     Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в первой;
     g. Не забываем про метод добавления фрукта в коробку.
 */

public class Home_4 {
    public static void main(String[] args) {
        

        Random rand = new Random();
        ArrayList<Box> boxes = new ArrayList<>(6);

        for (int i = 0; i < 6; i++) {
            boxes.add(new Box<>(fillTheBox(), i));

        }

        for (Box box :boxes){
            System.out.println(box);
        }
        for (int i = 1; i < boxes.size(); i++) {
            System.out.println(boxes.get(0).compare1(boxes.get(i)));
        }


        Apple myApple = new Apple("свежий");
        Orange myOrange = new Orange("несвежий");

        boxes.get(0).addToBox(myApple);
        System.out.println(boxes.get(0));

        boxes.get(0).addToBox(myOrange);
        System.out.println(boxes.get(0));


        reFill(boxes.get(0), boxes.get(1));
        reFill(boxes.get(1), boxes.get(2));
        reFill(boxes.get(2), boxes.get(3));
    }

    public static ArrayList<? extends Fruit> fillTheBox(){
        Random rand = new Random();
        boolean type = rand.nextBoolean();
        int quant = rand.nextInt(5,15);

        if(type) {
            ArrayList<Apple> fruits = new ArrayList<>(quant);
            for (int i = 0; i < quant; i++) {
                int clr = rand.nextInt(1, 3);
                fruits.add(new Apple(clr == 1 ? "свежий" : "несвежий"));
            }
            System.out.println(fruits);
            return fruits;
        } else {
            ArrayList<Orange> fruits = new ArrayList<>(quant);
            for (int i = 0; i < quant; i++) {
                int clr = rand.nextInt(1, 3);
                fruits.add(new Orange(clr == 1 ? "свежий" : "несвежий"));
            }
            System.out.println(fruits);
            return  fruits;
        }
    }

    public static void reFill (Box b1, Box b2){
        if(b1.getFruits().get(0).getClass()==b2.getFruits().get(0).getClass()){
            b2.getFruits().addAll(b1.getFruits());
            b1.getFruits().clear();
            System.out.println("Ура! Всё получилось.");
            System.out.println(b1);
            System.out.println(b2);
        } else {
            System.out.println("Увы, не удалось.");
            System.out.println(b1);
            System.out.println(b2);
        }
    }

}

abstract class Fruit{

    private final float weight;

    public float getWeight() {
        return weight;
    }

    public Fruit(float weight) {
        this.weight = weight;
    }
}

class Apple extends Fruit{

    private final float weight = 1.0f;
    private String freshness;

    public Apple(String freshness) {
        super(1.0f);
        this.freshness = freshness;
    }

    @Override
    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("Яблоко %s.", freshness);
    }

}


class Orange extends Fruit{
    private final float weight = 1.5f;
    private String freshness;

    public Orange(String freshness){
        super(1.5f);
        this.freshness = freshness;
    }
    
    @Override
    public float getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return String.format("Апельсин %s.", freshness);
    }
}

class Box<T extends Fruit>{

    ArrayList<T> fruits;

    private final int boxNum;
    public Box(ArrayList<T> fruits, int boxNum) {
        this.fruits = fruits;
        this.boxNum = boxNum;
    }

    public boolean compare1(Box box) {
        if (getWeight()==box.getWeight()) {
            return true;
        }
        else
            return false;
    }

    public int getBoxNum() {
        return boxNum;
    }

    public ArrayList<T> getFruits() {
        return fruits;
    }

    public double getWeight(){
        double weight = 0.0;
        ArrayList<T> fruits = getFruits();
        if (fruits.size()>0)   
            weight = fruits.size()*fruits.get(0).getWeight();
        return weight;
    }


    @Override
    public String toString() {
        return String.format("Коробка № %d с весом %.1f", boxNum, getWeight());
    }

    public void addToBox(T fruit){
        if (getFruits().get(0).getClass() == fruit.getClass()){
            getFruits().add(fruit);
            System.out.println("Вы добавили фрукты");
        } else {
            System.out.println("Фрукты, которые вы хотите добавить разные.");
        }

    }

}