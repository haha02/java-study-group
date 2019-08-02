# OOP in Java

>「類別就像是物件的設計圖，它定義了物件可操作的功能。」

>「物件就像是一件具體的工具，而類別定義了這個工具如何產生。」

## Encapsulation

![encapsulation](https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/CPT-OOP-interfaces.svg/2560px-CPT-OOP-interfaces.svg.png "encapsulation")
https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/CPT-OOP-interfaces.svg/2560px-CPT-OOP-interfaces.svg.png

封裝(Encapsulation)，一種抽象化的概念。

只需要瞭解怎麼用，不需要了解內部運作。Ex: 用手機的時候知道怎麼開關機，怎麼打電話，不需要知道內部是怎麼運作的。


### Class

* 定義 Class 來對物件資訊進行「封裝」（Encapsulation）
* 使用 Class 定義物件，類別是建構物件時所依賴的規格書
* 物件可能擁有「屬性」（Property）與「方法」（Method）

```java
public class Account {
    private String accountNumber;
    private double balance;

    public Account() {
        this("empty", 0.0);
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double money) {
        balance += money;
    }

    public double withdraw(double money) {
        balance -= money;
        return money;
    }
} 
```

<b>基本原則: 資訊的最小化公開</b>

* 透過方法 (public method) 來操作物件，不直接存取物件內部的資料成員

<b>Class member</b>

存取權限修飾詞
* public
* protected
* private

![privilege](https://i.stack.imgur.com/SFysv.jpg "privilege")
https://stackoverflow.com/questions/4361553/what-is-the-difference-between-public-private-and-protected

| Asscess  |public|protected|private|
|----------|------|---------|-------|
| The same class that declared it | V | V | V |
| The classes that inherit the above declared class | V | V | X |
| Any foreign elements outside this class can also access those things | V | X | X |



回到上面的例子，由於 ```accountNumber, balance``` 宣告為  ```private```，則其可視範圍（Scope）為整個類別內部，由於外界無法直接存取私用成員，所以必須要使用兩個公開方法 ```getAccountNumber()``` 與 ```getBalance()``` 分別傳回其這兩個成員的值

<b>Constructor</b>
* 類別名稱同名的方法
* 建構物件的同時，可以同時初始一些必要的資訊
* 建構方法可以被「重載」（Overload），滿足物件生成時各種不同的初始需求
```java
public class Account {
    private String accountNumber;
    private double balance;

    public Account() {
        this("empty", 0.0);
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
``` 
* 如果沒有定義任何的建構方法，則編譯器會自動配置一個無參數且沒有陳述內容的建構方法。


### static

* 靜態資料成員，靜態成員是屬於類別所擁有，而不是個別的物件
* 可以將靜態成員視為每個物件實例所共享的資料成員
* 靜態資料與靜態方法的作用通常是為了提供共享的資料或工具方法
* static attribute 通常使用 className.attribute，而不會宣告物件後使用 object.attribute

```java
public class Ball { 
    public static double PI = 3.14159; // 宣告 static 資料
    ...
}

System.out.println("PI = " + Ball.PI);
```

* 所以靜態方法中不會有 this 參考名稱，由於沒有 this 名稱
* 由於沒有 this 名稱，所以在 Java 的靜態方法中不允許使用非靜態成員，因為沒有 this 來參考至物件
```java
public class StaticDemo {
    public static void sayHello() {
        System.out.println("哈囉！");
    }

    public static void main(String[] args) {
        sayHello();
    }
}
```
*  上面的例子如果把 ```sayHello``` 的 ```static``` 拿掉就會出現以下錯誤
```
non-static method sayHello() cannot be referenced from a static context
```

### method

<b>Overload</b>

不同參數定義不同方法

```java
public class SomeClass {
    // 以下重載了someMethod()方法
    public void someMethod() {
        // ...
    }
    public void someMethod(int i) {
        // ...
    }
    public void someMethod(float f) {
        // ...
    }
    public void someMethod(int i, float f) {
        // ...
    }
}
```

返回值型態不可用作為方法重載的區別根據

```java
public class SomeClass {
    public int someMethod(int i) {
        // ...
        return 0;
    }
    public double someMethod(int i) {
        // ...
        return 0.0;
    }
}
```

### 不定長度引數
* 宣告的參數必須設定在參數列的最後一個
* 不能使用兩個以上的不定長度引數

```java
public void someMethod(int arg1, int arg2, int... varargs) {
     // ....
}
```
編譯器在處理重載方法、裝箱問題及「不定長度引數」時，會依下面的順序來尋找符合的方法：

* 找尋在還沒有裝箱動作前可以符合引數個數與型態的方法
* 嘗試裝箱動作後可以符合引數個數與型態的方法
* 嘗試設有「不定長度引數」並可以符合的方法
* 編譯器找不到合適的方法，回報編譯錯誤


### Garbage collection

不被任何名稱參考的物件將會被回收資源


## Inheritance

* 可以對父類別加以擴充(extends)，制訂出一個新的子類別
* 子類別可以繼承父類別原來的某些定義，也可能增加原來的父類別所沒有的定義，或者是重新定義父類別中的某些特性 

### extends

* 利用 Bird (父類別) extends 出 Chicken (子類別)
* Chicken 除了有父類別的 ```walk(), getName(), setName()```，也會有自己新的```crest, setCrest(), getCrest(), wu()```
* 父類別跟子類別之間，有「is a」的關係，例如雞「是一種」鳥（Chicken is a bird）
*  ```public``` 成員都可以在衍生類別中被呼叫使用，而 ```private``` 成員則不可以直接在衍生類別中被呼叫使用
    * Bird 類別中已經有 name 成員，Chicken 擴充 Bird 並新增了 crest 成員，而方法上新增 ```public``` 的 getCrest() 等方法，而 getName() 與 walk() 等方法則直接繼承父類別中已定義的內容

Bird.java
```java
public class Bird {
    private String name;

    public Bird() {
    }

    public Bird(String name) {
        this.name = name;
    }

    public void walk() {
        System.out.println("走路");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} 
```
Chicken.java

```java
public class Chicken extends Bird { // 擴充Bird類別
    private String crest; // 新增私有成員，雞冠描述

    public Chicken() {
        super();
    }

    // 定義建構方法
    public Chicken(String name, String crest) {
        super(name);
        this.crest = crest;
    }

    // 新增方法
    public void setCrest(String crest) {
        this.crest = crest;
    }

    public String getCrest() {
        return crest;
    }

    public void wu() {
       System.out.println("咕咕叫…");
    }
} 
```

<b>protected</b>

如果想讓子類別，也能夠直接存取父類別中的成員，而不是透過 ```public``` 方法成員，但也不是將資料成員宣告為 ```public```，可以使用 ```protected```

### Override

* 重新定義方法的實作內容、成員的存取權限，或是成員的返回值型態
* 無法重新定義 static 方法

<b>重新定義方法的實作內容</b>

```java
public class SimpleArray {
    protected int[] array;

    public SimpleArray(int i) {
        array = new int[i];
    }
    public void setElement(int i, int data) {
        array[i] = data;
    }
    ....
} 
```

```java
public class SafeArray extends SimpleArray {
    public SafeArray(int i) {
        super(i);
    }
    // 重新定義setElement()
    public void setElement(int i, int data) {
        if(i < array.length)
            super.setElement(i, data);
    }
    ....
}
```

```java
SimpleArray simpleArray = new SafeArray();
simpleArray.setElement();
```

<b>重新定義成員的存取權限</b>

* 可以``增大``父類別中的方法權限，但不可以``縮小``父類別的方法權限 => 編譯時會報錯
    * 原來成員是 ```public``` 的話，不可以在父類別中重新定義它為 ```private``` 或 ```protected```

<b>重新定義返回值型態</b>

重新定義的返回值型態必須是父類別中同一方法返回型態的子類別

```java
public class Bird {
    protected String name;

    public Bird(String name) {
        this.name = name;
    }
    public Bird getCopied() {
        return new Bird(name);
    }
}

public class Chicken extends Bird {
    protected String crest;

    public Point3D(String name, String crest) {
        super(name);
        this.crest = crest;
    }
    // 重新定義返回值型態為 Chicken
    public Chicken getCopied() {
        return new Chicken(name, crest);
    }
}
```

### Object 類別

* Object 類別是 Java 中所有類別的父類別
* 在 Java 中定義類別時如果沒有指定要繼承的類別，則自動繼承 Object 類別

```java
public class Foo { 
    ... 
} 
// 自動變成
public class Foo extends Object { 
    ...
} 
```

### final

在變數宣告時使用```final```，表示該變數一旦設定之後，就不可以再改變該變數的值

## Polymorphism 多型

* 使用同一個操作介面，以操作不同的物件實例

