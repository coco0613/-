-----------------------------------String start--------------------------------------
1、"==" 和 "equals" 的区别是什么?
答：1.1基本数据类型比较 要用==判断是否相等
    1.2引用数据类型： ==比较的是内存地址是否一样，不同对象的内存地址不一样，equals比较的是具体的内容，
        也可以让开发者去定义什么条件去判断两个对象是否一样
区别：当数据类型为基本数据类型时，==比较的是变量值是否相等，若为引用数据类型比较的是内存地址是否相等;
    而equals只能只能比较变量值是否相等，不能比较内存地址是否想等;
2、String str = new String("xdclass.net");创建了几个对象？
答：若常量池存在则存在一个，若常量池不存在，则存在两个（一个创建在常量池，一个创建在堆里）
3、写出下面代码的各个结果？如果需要两个都为true，应该怎么修改?
    String s1 = "xdclass";
    String s2 = s1 + ".net";  //变量 + 常量 = 来自堆
    String s3 = "xdclass" + ".net";  //常量 + 常量 = 来自常亮池
    System.out.println(s2 == "xdclass.net");
    System.out.println(s3 == "xdclass.net");
答案:
    第一条语句打印的结果为false， s2 = s1 + ".net",   //变量+常量=堆
    构建了一个新的string对象，并将对象引用赋予s2变量，常量池中的地址不一样，但是值一样;
    第二条语句打印的结果为true，javac编译可以对【字符串常量】直接相加的表达式进行优化;
    不用等到运行期再去进行加法运算处理，而是直接将其编译成一个这些常量相连的结果;
    如果需要第一个输出为true，只需要把变量改为常量即可 fianl String s1 = "xdclass";
    不管是new String("XXX")和直接常量赋值, 都会在字符串常量池创建,
    只是new String("XXX")方式会在堆中创建一个对象去指向常量池的对象, 普通的常量赋值是直接赋值给变量;
                                                                    时间：2021年8月5日22:10:36
---------------------------------------String end----------------------------------- 
---------------------------------------数组start-------------------------------------
1、什么是数组?
答：数组是相同类型数据的有序集合;
2、数组的特点?
答：2.1 数组会在“内存中”开辟一块连续的空间,每个空间之间相当于之前的一个变量,称为数组的元素element;
    2.2 元素的表示：数组名[下标] eg:arr[0];
    2.3 索引从0开始，不同类型含有默认值，double 0.0, boolean false, int 0;
    2.4 数组元素是有序的,不是大小顺序而是“下标顺序”;
    2.5 数组可存储基本数据类型,也可以存储引用数据类型,但声明一个数组时必须明确数组的类型和长度
        类型唯一,数组长度固定,不会变化,但可以进行数组的扩容和缩容;
3、数组扩容?
答：3.1 System.arrayCopy(arr[], srcPos, newArr[], descPos, length);
        arr[]:源数组
        srcPos: 源数组开始复制的起始位置
        newArr[]: 新数组
        descPos: 目标数组起始位置
        length: 复制的长度
3.2 Arrays.copyOf(array, pos);
    返回值为一个数组,pos为复制长度的下标
3.3 Arrays.fill(array, startPos, endPos);
    返回一个数组, startPos表示开始下标, endPos是结束下标
                                                        时间：2021年8月5日22:10:36
---------------------------------数组end--------------------------------
-------------------------------List start-------------------------------
1、vector、ArrayList、LinkedList联系和区别? （）
答：vector: 底层为数组, 线程安全的,使用synchronized进行加锁;
    ArrayList: 底层为数组, 线程不安全的, 数组特性(查询和修改速度快,增加和删除慢)
    LinkedList: 底层为双向链表, 线程不安全的, 链表特性(查询速度慢,增加和删除快)
使用场景：
    vector: 可用于线程安全, 但是由于性能差, 目前已很少使用;
    ArrayList: 当数据查询和修改多的情况时使用;
    LinkedList: 当数据增加和删除多的情况时使用;
2、如果需要保证线程安全, ArrayList应该如何做, 改如何使用?
答：2.1 可以自己写一个包装类,根据业务一般在add/update/remove时进行加锁;
    2.2 使用 Collections.synchronizedCollection(new ArrayList<>()); 使用synchronized加锁;
    2.3 new CopyOnWriteArrayList(); 使用ReentrantLock加锁; j.u.c并发包;
3、了解CopyOnWriteArrayList吗？和 Collections.synchronizedList实现线程安全有什么区别, 使用场景是怎样的？
答：CopyOnWriteArrayList中使用ReentrantLock进行加锁,保证线程安全; 在执行修改操作, add、remove、set等会拷贝一份新的数据
    (这个代价是非常昂贵的)在将修改完后会将新的数据赋值给原有的数据,然后进行unLook解锁; <当数组特别大时,性能较差>
与Collections.synchronizedList的区别：Collections.synchronizedList不管是add、update、remove、get等操作都进行了synchronized加锁;
    而CopyOnWriteArrayList只有在add、update、remove等修改操作进行了加锁,而进行get时无需加锁;
使用场景：CopyOnWriteArrayList适用于读操作大于写操作的场景
        Collections.synchronizedList线程安全,每个方法都使用synchronized同步锁,所以Collections.synchronizedList
        写操作性能比CopyOnWriteArrayList性能要好,但读操作不如CopyOnWriteArrayList;
3、CopyOnWriteArrayList的设计思想是什么?有什么缺点?
答：设计思想是读写分离,最终一致;缺点: 内存占用问题,由于写操作复制时,内存里面始终存在两个对象占用内存,若对象大,则容易发生YongGC或者FullGC;
4、ArrayList扩容机制是怎么样的?
答：注意: JDK1.7之前ArrayList的默认大小是10  JDK1.7之后默认是0
        未指定集合容量，默认是0，若已经指定大小则集合大小为指定的;
        当集合第一次添加元素的时候，集合大小扩容为10;
        ArrayList的元素个数大于其容量，扩容的大小= 原始大小+原始大小/2;
                                                                时间：2021年8月5日22:10:36
------------------------------------------List end-----------------------------------------
---------------------------------------Map start-------------------------------------------
1、了解hashMap吗?用过哪些hashMap的实现?
答：hashMap,hashTable,linkedHashMap,treeMap,currentHashMap
2、hashMap和hashTable有什么区别?
答：hashMap: 底层是数组+链表,JDK1.8及之后当链表长度超过8时,转化为红黑树。非线程安全, 默认容量是16,允许有空的键和值,扩容因子0.75,其中key值不能重复;
    hashTable: 底层是基于哈希表实现,线程安全,默认容量是11,不允许有空的键和值; (使用synchronized加锁)
3、介绍对象的hashCode()和equals()方法
答：hashCode()是Object类中的方法，返回一个int值，根据一定的hash规则(存储地址，长度，字段等)，映射成一个数组，集散列值;
    equals()也是Object类中的方法，返回boolean，用于匹配两个对象是否一样(判断地址是否一样，非空判断和Class判断，强转，对象里面的字段一一匹配)
4、hashMap和treeMap的区别?该如何选择?
答：hashMap(散列桶+链表)，存储的值是无序的，但是可以快速检索，使用与在map中插入、删除和定位元素;
    treeMap(平衡二叉树 + 红黑树) 可以自定义排序接口，需要细线comparator接口，能在内部实现排序，但是性能一般比hashMap差;
5、set和map的关系
    set不保存重复的元素，存储一组唯一的对象
    不重复原因：因为set底层的每一种实现都对应map的一种封装; hashSet->hashMap  treeSet->treeMap
6、List、set、Map的区别?
    参考：https://www.cnblogs.com/liangbaolong/p/12885025.html
    同一个Map可以put相同的键，但是之前相同键值的值会被覆盖;
    List、Set都继承于集合类Collection，Map是Map集合的顶级接口;
7、hashMap如何实现线程安全,且效率高?
答：多线程环境下可以使用juc包下的currentHashMap(),或则使用Collections.synchronizedMap();
    currentHashMap是线程安全的，相对于hashTable效率高很多; <因为currentHashMap采用是分段锁>
8、为什么Collections.synchronizedMap后是线程安全的?
答：Collections.synchronizedMap()包装之后返回的Map是加锁的 <synchronized加锁,效率不如currentHashMap>
9、hashMap底层原理，对hashMap有什么理解?
答：JDK1.7之前 底层采用数组 + 链表，JDK1.7之后为 数组 + 链表 + 红黑树;
    数组中每一项是一个链表;
    Node<K,V> table []是一个数组，数组的每个元素是Entry(JDK1.8之后是Node继承与Entry),每个Entry元素是一个key-value的键值对，
    他持有一个指向下一个Entry的应用，数组中的Entry元素也作为每个Entry头节点，也具有一个指向下一个Entry的引用;
10、了解hash碰撞吗？解释一下hash碰撞，有那些解决方案?
答：hash碰撞是插入元素时不同的Key通过哈希算法生成的hashCode与之前生成的某个hashCode一致,需要放到同一个桶中;
    hash碰撞解决方案：链表法,在哈希法,开放地址法; hashMap采用链表法;
11、hashMap负载(扩容)因子为什么是0.75?
答：首先，因为1.8之后hashMap的底层数组+链表+红黑树，如果我们将扩容因子设置为1，意味着只有容量被占用完了才会进行扩容，而hashMap中无法避免
    的hash碰撞，当容量占满时再扩容会产生大量的hash碰撞，底层的红黑树会变得异常的复杂，对查询效率有很大的影响;这种方式是牺牲时间来保证空间利用率;
    若负载因子为0.5,当占满一般容量便开始扩容，当然会减少hash冲突，产生的链表以及红黑树的长度会降低，查询效率会提高，但是这样的做法牺牲了大量的空间
    来保证时间效率，空间利用率降低，导致原本只要1M的空间，实际上使用2M的空间;总的来说就是,若负载因子过大,会产生大量的hash碰撞，影响时间相率，
    若过小，则会导致空间利用率降低;当然为什么是0.75应该是作者通过实践与测试得出的最佳值吧;如同(hashMap中为什么链表长度为8时才开始转化为红黑树)
                                                                        时间：2021年8月5日22:10:36
12、为什么hashMap要用数组+链表+红黑树这几种数据结构呢?
答：数组：Node<K,V> table[]，根据对象的key值生成的hash值进行判断是数组中的那个节点;
    链表的作用是：当发生hash冲突时，将hash值相同的的对象放在hash值相同的槽位形成链表;
    红黑树：在源码中，当链表长度大于8时，链表转换为红黑树(从原来的O(n)转变成O(log(n))，
    使用红黑树原因：当hash碰撞的不断发生，链表多长时，查询数据会遍历一个超级大链表，性能降低，所以
    改用红黑树数据结构，防止链表过长，导致查询效率降低;
13、了解currentHashMap吗？为什么性能比hashTable高,说下原理?
答：currentHashMap是线程安全的map, hashTable中的每个方法基本上都采用了synchronized进行线程安全控制，高并发场景下效率较低，   
    而currentHashMap采用分段锁的思想，分段存储数据进而提高性能，锁粒度更细化; JDK1.8开始currentHashMap采用数组+链表+红黑树
    的数据结构，从而实现对每段数据加锁，减少了并发冲突概率,并且采用CAS(保证读)，synchronized保证写，从而提高效率;
14、JDK1.7和JDK1.8中currentHashMap有什么区别?
    JDK1.8之前，currentHashMap使用分段锁技术,将数据分为一段段存储，每个数据配置一把锁，采用segment类，这个类继承ReentrantLock类
    保证线程安全;
    JDK1.8之后取消了segment这个分段锁数据结构，采用数组+链表+红黑树结构，从而实现对每段数据加锁，减少了并发冲突概率
    并且采用CAS(保证读)，synchronized保证写，从而提高效率;
                                                                        时间：2021年8月6日00:46:36
--------------------------------Map end-----------------------------------------
--------------------------------Queue start--------------------------------------
1、什么是队列Queue? ---先进先出
答：队列是一种比较特殊的线性结构。它只允许在表的前端（front）进行删除操作，而在表的后端（rear）进行插入操作,
    进行插入操作的端称为队尾，进行删除操作的端称为队头;
    队列中最先插入的元素也将最先被删除，对应的最后插入的元素将最后被删除,
    因此队列又称为“先进先出”（FIFO—first in first out）的线性表，与栈(FILO-first in last out)刚好相反;
    相关API: offer()添加元素、poll()删除元素,peek()弹出元素不会删除,
        尽量别用add()、remove()进行添加和删除,该方法在失败的时候会抛出异常;
                                                                        时间：2021年8月6日00:56:58
--------------------------------Queue end----------------------------------------
--------------------------------Stack start----------------------------------------  
1、什么是栈Stack? ---先进后出
答：栈是Vector的一个子类，它实现了一个标准的”后进先出“的栈; 堆栈只定义了默认构造函数，用来创建一个空栈;
    堆栈除了包括由Vector定义的所有方法，也定义了自己的一些方法。
    相关API:Object peek()查看堆栈顶部的对象，但不从堆栈中移除它、Object pop()移除堆栈顶部的对象，并作为此函数的值返回该对象、
        Object push(Object element)把项压入堆栈顶部;
                                                                        时间：2021年8月6日01:01:05    
--------------------------------Stack end-----------------------------------------
--------------------------------树 start------------------------------------------
二叉树
平衡二叉树
红黑树
B-tree树
B+tree数
--------------------------------树 end--------------------------------------------