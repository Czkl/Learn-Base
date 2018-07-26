##CAS(Compare and Swap)
    Synchronized关键字会让没有得到锁资源的线程进入BLOCKED状态，而后在争夺到锁资源后恢复为RUNNABLE状态，这个过程中涉及到操作系统用户模式和内核模式的转换，代价比较高。
    
    
    尽管Java1.6为Synchronized做了优化，增加了从偏向锁到轻量级锁再到重量级锁的过度，但是在最终转变为重量级锁之后，性能仍然较低。


    Synchronized属于悲观锁，悲观地认为程序中的并发情况严重，所以严防死守。CAS属于乐观锁，乐观地认为程序中的并发情况不那么严重，所以让线程不断去尝试更新。

   



----------
    CAS是英文单词Compare And Swap的缩写，翻译过来就是比较并替换。

    CAS机制当中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。

    更新一个变量的时候，只有当变量的预期值A和内存地址V当中的实际值相同时，才会将内存地址V对应的值修改为B。

    CAS的缺点：
    
    1.CPU开销较大

    在并发量比较高的情况下，如果许多线程反复尝试更新某一个变量，却又一直更新不成功，循环往复，会给CPU带来很大的压力。

    2.不能保证代码块的原子性

    CAS机制所保证的只是一个变量的原子性操作，而不能保证整个代码块的原子性。比如需要保证3个变量共同进行原子性的更新，就不得不使用Synchronized了。

    3.ABA问题

    这是CAS机制最大的问题所在。


----------


首先看一看AtomicInteger当中常用的自增方法 incrementAndGet(1.7版本前)：

```java
public final int incrementAndGet() {
    for (;;) {
        int current = get();
        int next = current + 1;
        if (compareAndSet(current, next))
            return next;
    }
}
private volatile int value;
public final int get() {
    return value;
}
```

    这段代码是一个无限循环，也就是CAS的自旋。循环体当中做了三件事：
    1.获取当前值。
    2.当前值+1，计算出目标值。
    3.进行CAS操作，如果成功则跳出循环，如果失败则重复上述步骤
    
    这里需要注意的重点是 get 方法，这个方法的作用是获取变量的当前值。
    
    如何保证获得的当前值是内存中的最新值呢？很简单，用volatile关键字来保证。有关volatile关键字的知识，我们之前有介绍过，这里就不详细阐述了。。

1.8版本


    第一个参数var1为给定的对象，var2为对象内的偏移量（其实就是一个字段到对象头部的偏移量，通过这个偏移量可以快速定位字段），var4表示期望值，var5表示要设置的值。如果指定的字段的值等于var4，那么就会把它设置为var5.


接下来看一看compareAndSet方法的实现，以及方法所依赖对象的来历：
![image_1cj7j2sq419ru146t8bnmbb1dpfp.png-98.6kB][1]

    compareAndSet方法的实现很简单，只有一行代码。这里涉及到两个重要的对象，一个是unsafe，一个是valueOffset。
    
    什么是unsafe呢？Java语言不像C，C++那样可以直接访问底层操作系统，但是JVM为我们提供了一个后门，这个后门就是unsafe。unsafe为我们提供了硬件级别的原子操作。
    
    至于valueOffset对象，是通过unsafe.objectFieldOffset方法得到，所代表的是AtomicInteger对象value成员变量在内存中的偏移量。我们可以简单地把valueOffset理解为value变量的内存地址。
    
    我们在上一期说过，CAS机制当中使用了3个基本操作数：内存地址V，旧的预期值A，要修改的新值B。
    
    而unsafe的compareAndSwapInt方法参数包括了这三个基本元素：valueOffset参数代表了V，expect参数代表了A，update参数代表了B。
    
    正是unsafe的compareAndSwapInt方法保证了Compare和Swap操作之间的原子性操作。



什么是ABA呢？假设内存中有一个值为A的变量，存储在地址V当中。

![image_1cj7j98ca1udpaoe165o1un7qdj16.png-16.7kB][2]

此时有三个线程想使用CAS的方式更新这个变量值，每个线程的执行时间有略微的偏差。线程1和线程2已经获得当前值，线程3还未获得当前值。
![image_1cj7j9m9mrcm17lo189ld6u15tt1j.png-66.9kB][3]
接下来，线程1先一步执行成功，把当前值成功从A更新为B；同时线程2因为某种原因被阻塞住，没有做更新操作；线程3在线程1更新之后，获得了当前值B。
![image_1cj7ja34a1osjmvr1b731eq51it320.png-80.1kB][4]

再之后，线程2仍然处于阻塞状态，线程3继续执行，成功把当前值从B更新成了A。![image_1cj7jae73f0q5mtkbu1h6r1l7i2d.png-80kB][5]

最后，线程2终于恢复了运行状态，由于阻塞之前已经获得了“当前值”A，并且经过compare检测，内存地址V中的实际值也是A，所以成功把变量值A更新成了B。![image_1cj7jarth17khj1hjlettposg2q.png-83kB][6]
这个过程中，线程2获取到的变量值A是一个旧值，尽管和当前的实际值相同，但内存地址V中的变量已经经历了A->B->A的改变。


----------

### 怎么解决ABA问题呢？

加一个版本号就可以了
什么意思呢？真正要做到严谨的CAS机制，我们在Compare阶段不仅要比较期望值A和地址V中的实际值，还要比较变量的版本号是否一致。

我们仍然以最初的例子来说明一下，假设地址V中存储着变量值A，当前版本号是01。线程1获得了当前值A和版本号01，想要更新为B，但是被阻塞了。

![image_1cj7jct7qd5ptp212ot1l9rbgd37.png-52kB][7]

这时候，内存地址V中的变量发生了多次改变，版本号提升为03，但是变量值仍然是A。![image_1cj7jdc6njbo6k67n419a318tm44.png-50.5kB][8]

随后线程1恢复运行，进行Compare操作。经过比较，线程1所获得的值和地址V的实际值都是A，但是版本号不相等，所以这一次更新失败。
![image_1cj7jdm9h1qv71lf81o83kh1vhl4h.png-66.4kB][9]

在Java当中，`AtomicStampedReference类就实现了用版本号做比较的CAS机制。

##总结

 - Java语言CAS底层如何实现？
    - 利用unsafe提供了原子性操作方法
 - 什么是ABA问题？怎么解决？
    - 当一个值从A更新成B，又更新会A，普通CAS机制会误判通过检测。利用版本号比较可以有效解决ABA问题。

[原文][10]https://mp.weixin.qq.com/s/nRnQKhiSUrDKu3mz3vItWg


  [1]: http://static.zybuluo.com/c102zkl/b10bu3kd3vhzub5e1fzoo5nf/image_1cj7j2sq419ru146t8bnmbb1dpfp.png
  [2]: http://static.zybuluo.com/c102zkl/ofxzahr7ddtvtkjhn1eiz8g2/image_1cj7j98ca1udpaoe165o1un7qdj16.png
  [3]: http://static.zybuluo.com/c102zkl/y2jkopcsszvjbnfjrztzb4os/image_1cj7j9m9mrcm17lo189ld6u15tt1j.png
  [4]: http://static.zybuluo.com/c102zkl/ic3qi8zzpj2wpzacj6o64r09/image_1cj7ja34a1osjmvr1b731eq51it320.png
  [5]: http://static.zybuluo.com/c102zkl/rvjm1mp8nd1wfb9cnjfxc81c/image_1cj7jae73f0q5mtkbu1h6r1l7i2d.png
  [6]: http://static.zybuluo.com/c102zkl/8vsa95vg3aym25uc6ouc58x0/image_1cj7jarth17khj1hjlettposg2q.png
  [7]: http://static.zybuluo.com/c102zkl/3f6ah8szoj7gipx1evm2hedq/image_1cj7jct7qd5ptp212ot1l9rbgd37.png
  [8]: http://static.zybuluo.com/c102zkl/79bz8hwyp48lie3aec9n2s40/image_1cj7jdc6njbo6k67n419a318tm44.png
  [9]: http://static.zybuluo.com/c102zkl/1uf3plr42dtqynarsowv75hk/image_1cj7jdm9h1qv71lf81o83kh1vhl4h.png
  [10]: https://mp.weixin.qq.com/s/nRnQKhiSUrDKu3mz3vItWg