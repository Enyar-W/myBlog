package org.tl.blog;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tl.blog.admin.entity.BlogCategoryPostRel;
import org.tl.blog.admin.entity.BlogPost;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.admin.mapper.BlogCategoryPostRelMapper;
import org.tl.blog.admin.service.BlogPostService;
import org.tl.blog.common.upload.UploadService;
import org.tl.blog.common.utils.Pager;

import java.util.ArrayList;
import java.util.List;

public class BlogPostTest extends BlogApplicationTests {

    @Autowired
    BlogPostService postService;

    @Autowired
    UploadService uploadService;

    @Autowired
    private BlogCategoryPostRelMapper categoryPostRelMapper;

    @Test
    public void insert() {
        //SysUser sysUser = sysUserMapper.selectByPrimaryKey(1);
        BlogPost blogPost = new BlogPost();
       // blogPost.setPostId(1);
        blogPost.setPostContent("fffffffff");
        postService.insert(blogPost);

    }

    @Test
    public void pager() {
        BlogPost blogPost = new BlogPost();
        Pager<BlogPost> pager = postService.pager(2, 5, blogPost);
        System.out.println(pager.getTotalPage());
        for (BlogPost blogPost1: pager.getResult()) {
            System.out.println(blogPost1.getPostId());
        }

    }

    @Test
    public void replaceImage(){
        String content = "<h2 id=\"-threadlocal-\">【转】ThreadLocal 源码解读</h2>\n" +
                "<p>活在夢裡 <a href=\"javascript:void(0\">Java知音</a>;) <em>今天</em></p>\n" +
                "<blockquote>\n" +
                "<p>作者：活在夢裡</p>\n" +
                "<p>链接：cnblogs.com/micrari/p/6790229.html</p>\n" +
                "</blockquote>\n" +
                "<h3 id=\"1-\">1.背景</h3>\n" +
                "<p>ThreadLocal源码解读，网上面早已经泛滥了，大多比较浅，甚至有的连基本原理都说的很有问题,包括百度搜索出来的第一篇高访问量博文，说ThreadLocal内部有个map，键为线程对象，太误导人了。</p>\n" +
                "<p>ThreadLocal非常适合对Java多线程编程感兴趣的程序员作为入门类阅读，原因两方面：</p>\n" +
                "<ol>\n" +
                "<li>加上注释源码也不过七八百行。</li>\n" +
                "<li>结构清晰，代码简洁。</li>\n" +
                "</ol>\n" +
                "<p>本文重点导读ThreadLocal中的嵌套内部类ThreadLocalMap，对ThreadLocal本身API的介绍简略带过。</p>\n" +
                "<p>读ThreadLocal源码，不读ThreadLocalMap的实现，和没看过没多大差别。</p>\n" +
                "<h3 id=\"2-\">2.两个问题</h3>\n" +
                "<p>先回答两个问题：</p>\n" +
                "<h5 id=\"-threadlocal-\">什么是ThreadLocal？</h5>\n" +
                "<p>ThreadLocal类顾名思义可以理解为线程本地变量。也就是说如果定义了一个ThreadLocal，每个线程往这个ThreadLocal中读写是线程隔离，互相之间不会影响的。它提供了一种将可变数据通过每个线程有自己的独立副本从而实现线程封闭的机制。</p>\n" +
                "<h5 id=\"-\">它大致的实现思路是怎样的？</h5>\n" +
                "<p>Thread类有一个类型为ThreadLocal.ThreadLocalMap的实例变量threadLocals，也就是说每个线程有一个自己的ThreadLocalMap。ThreadLocalMap有自己的独立实现，可以简单地将它的key视作ThreadLocal，value为代码中放入的值（实际上key并不是ThreadLocal本身，而是它的一个弱引用）。</p>\n" +
                "<p>每个线程在往某个ThreadLocal里塞值的时候，都会往自己的ThreadLocalMap里存，读也是以某个ThreadLocal作为引用，在自己的map里找对应的key，从而实现了线程隔离。</p>\n" +
                "<h3 id=\"3-threadlocal-api\">3.ThreadLocal的API</h3>\n" +
                "<p>ThreadLocal的API其实没多少好介绍的，这些API介绍网上早已烂大街了。</p>\n" +
                "<p><img src=\"http://img.terrylam.cn/eQPyBffYbufJaqQkvibaMUhGxB1NLwZGSw5iccsM2n5SCDk92HXLMY4mhkqzHcMp2j2w0sic0vYnnat5zyCCApBAg/640?wx_fmt=png&amp;tp=webp&amp;wxfrom=5&amp;wx_lazy=1&amp;wx_co=1\" alt=\"img\"></p>\n" +
                "<h3 id=\"4-threadlocalmap-\">4.ThreadLocalMap的源码实现</h3>\n" +
                "<p>ThreadLocalMap的源码实现，才是我们读ThreadLocal源码真正要领悟的。看看大师Doug Lea和Joshua Bloch的鬼斧神工之作。</p>\n" +
                "<p><img src=\"https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbufJaqQkvibaMUhGxB1NLwZGSrFEHKRvzT6mQaV5djEXYsRKYepKKVQEaJIakbLX9W9ZcFzDKFY53tg/640?wx_fmt=png&amp;tp=webp&amp;wxfrom=5&amp;wx_lazy=1&amp;wx_co=1\" alt=\"img\"></p>\n" +
                "<p>ThreadLocalMap提供了一种为ThreadLocal定制的高效实现，并且自带一种基于弱引用的垃圾清理机制。</p>\n" +
                "<p>下面从基本结构开始一点点解读。</p>\n" +
                "<h6 id=\"4-1-\">4.1 存储结构</h6>\n" +
                "<p>既然是个map（注意不要与java.util.map混为一谈，这里指的是概念上的map），当然得要有自己的key和value，上面回答的问题2中也已经提及，我们可以将其简单视作key为ThreadLocal，value为实际放入的值。</p>\n" +
                "<p>之所以说是简单视作，因为实际上ThreadLocal中存放的是ThreadLocal的弱引用。我们来看看ThreadLocalMap里的节点是如何定义的。</p>\n" +
                "<pre><code>static class Entry extends WeakReference&lt;java.lang.ThreadLocal&lt;?&gt;&gt; {\n" +
                "    // 往ThreadLocal里实际塞入的值\n" +
                "    Object value;\n" +
                "\n" +
                "    Entry(java.lang.ThreadLocal&lt;?&gt; k, Object v) {\n" +
                "        super(k);\n" +
                "        value = v;\n" +
                "    }\n" +
                "}\n" +
                "</code></pre><p>Entry便是ThreadLocalMap里定义的节点，它继承了WeakReference类，定义了一个类型为Object的value，用于存放塞到ThreadLocal里的值。</p>\n" +
                "<h5 id=\"4-2-\">4.2 为什么要弱引用</h5>\n" +
                "<p>读到这里，如果不问不答为什么是这样的定义形式，为什么要用弱引用，等于没读懂源码。</p>\n" +
                "<p>因为如果这里使用普通的key-value形式来定义存储结构，实质上就会造成节点的生命周期与线程强绑定，只要线程没有销毁，那么节点在GC分析中一直处于可达状态，没办法被回收，而程序本身也无法判断是否可以清理节点。</p>\n" +
                "<p>弱引用是Java中四档引用的第三档，比软引用更加弱一些，如果一个对象没有强引用链可达，那么一般活不过下一次GC。当某个ThreadLocal已经没有强引用可达，则随着它被垃圾回收，在ThreadLocalMap里对应的Entry的键值会失效，这为ThreadLocalMap本身的垃圾清理提供了便利。</p>\n" +
                "<h5 id=\"4-3-\">4.3 类成员变量与相应方法</h5>\n" +
                "<pre><code>/**\n" +
                " * 初始容量，必须为2的幂\n" +
                " */\n" +
                "private static final int INITIAL_CAPACITY = 16;\n" +
                "\n" +
                "/**\n" +
                " * Entry表，大小必须为2的幂\n" +
                " */\n" +
                "private Entry[] table;\n" +
                "\n" +
                "/**\n" +
                " * 表里entry的个数\n" +
                " */\n" +
                "private int size = 0;\n" +
                "\n" +
                "/**\n" +
                " * 重新分配表大小的阈值，默认为0\n" +
                " */\n" +
                "private int threshold;\n" +
                "</code></pre><p>可以看到，ThreadLocalMap维护了一个Entry表或者说Entry数组，并且要求表的大小必须为2的幂，同时记录表里面entry的个数以及下一次需要扩容的阈值。</p>\n" +
                "<p>显然这里会产生一个问题，为什么必须是2的幂？很好，但是目前还无法回答，带着问题接着往下读。</p>\n" +
                "<pre><code>/**\n" +
                " * 设置resize阈值以维持最坏2/3的装载因子\n" +
                " */\n" +
                "private void setThreshold(int len) {\n" +
                "    threshold = len * 2 / 3;\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * 环形意义的下一个索引\n" +
                " */\n" +
                "private static int nextIndex(int i, int len) {\n" +
                "    return ((i + 1 &lt; len) ? i + 1 : 0);\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * 环形意义的上一个索引\n" +
                " */\n" +
                "private static int prevIndex(int i, int len) {\n" +
                "    return ((i - 1 &gt;= 0) ? i - 1 : len - 1);\n" +
                "}\n" +
                "</code></pre><p>ThreadLocal需要维持一个最坏2/3的负载因子，对于负载因子相信应该不会陌生，在HashMap中就有这个概念。</p>\n" +
                "<p>ThreadLocal有两个方法用于得到上一个/下一个索引，注意这里实际上是环形意义下的上一个与下一个。</p>\n" +
                "<p>由于ThreadLocalMap使用线性探测法来解决散列冲突，所以实际上Entry[]数组在程序逻辑上是作为一个环形存在的。</p>\n" +
                "<p>关于开放寻址、线性探测等内容，可以参考网上资料或者TAOCP（《计算机程序设计艺术》）第三卷的6.4章节。</p>\n" +
                "<p>至此，我们已经可以大致勾勒出ThreadLocalMap的内部存储结构。下面是我绘制的示意图。虚线表示弱引用，实线表示强引用。</p>\n" +
                "<p><img src=\"https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbufJaqQkvibaMUhGxB1NLwZGS3MiaicydvPqZJ3oibMhGCU2QLrfYElKk2Ue70iaYLyE4icojoe49w4tibiajg/640?wx_fmt=png&amp;tp=webp&amp;wxfrom=5&amp;wx_lazy=1&amp;wx_co=1\" alt=\"img\"></p>\n" +
                "<p>ThreadLocalMap维护了Entry环形数组，数组中元素Entry的逻辑上的key为某个ThreadLocal对象（实际上是指向该ThreadLocal对象的弱引用），value为代码中该线程往该ThreadLoacl变量实际塞入的值。</p>\n" +
                "<h5 id=\"4-4-\">4.4 构造函数</h5>\n" +
                "<p>好的，接下来再来看看ThreadLocalMap的一个构造函数</p>\n" +
                "<pre><code>/**\n" +
                " * 构造一个包含firstKey和firstValue的map。\n" +
                " * ThreadLocalMap是惰性构造的，所以只有当至少要往里面放一个元素的时候才会构建它。\n" +
                " */\n" +
                "ThreadLocalMap(java.lang.ThreadLocal&lt;?&gt; firstKey, Object firstValue) {\n" +
                "    // 初始化table数组\n" +
                "    table = new Entry[INITIAL_CAPACITY];\n" +
                "    // 用firstKey的threadLocalHashCode与初始大小16取模得到哈希值\n" +
                "    int i = firstKey.threadLocalHashCode &amp; (INITIAL_CAPACITY - 1);\n" +
                "    // 初始化该节点\n" +
                "    table[i] = new Entry(firstKey, firstValue);\n" +
                "    // 设置节点表大小为1\n" +
                "    size = 1;\n" +
                "    // 设定扩容阈值\n" +
                "    setThreshold(INITIAL_CAPACITY);\n" +
                "}\n" +
                "</code></pre><p>这个构造函数在set和get的时候都可能会被间接调用以初始化线程的ThreadLocalMap。</p>\n" +
                "<h5 id=\"4-5-\">4.5 哈希函数</h5>\n" +
                "<p>重点看一下上面构造函数中的</p>\n" +
                "<blockquote>\n" +
                "<p>int i = firstKey.threadLocalHashCode &amp; (INITIAL_CAPACITY - 1);</p>\n" +
                "</blockquote>\n" +
                "<p>这一行代码。</p>\n" +
                "<p>ThreadLocal类中有一个被final修饰的类型为int的threadLocalHashCode，它在该ThreadLocal被构造的时候就会生成，相当于一个ThreadLocal的ID，而它的值来源于</p>\n" +
                "<pre><code>/*\n" +
                " * 生成hash code间隙为这个魔数，可以让生成出来的值或者说ThreadLocal的ID较为均匀地分布在2的幂大小的数组中。\n" +
                " */\n" +
                "private static final int HASH_INCREMENT = 0x61c88647;\n" +
                "\n" +
                "private static int nextHashCode() {\n" +
                "    return nextHashCode.getAndAdd(HASH_INCREMENT);\n" +
                "}\n" +
                "</code></pre><p>可以看出，它是在上一个被构造出的ThreadLocal的ID/threadLocalHashCode的基础上加上一个魔数0x61c88647的。这个魔数的选取与斐波那契散列有关，0x61c88647对应的十进制为1640531527。斐波那契散列的乘数可以用(long) ((1L &lt;&lt; 31) * (Math.sqrt(5) - 1))可以得到2654435769，如果把这个值给转为带符号的int，则会得到-1640531527。</p>\n" +
                "<p>换句话说<br>(1L &lt;&lt; 32) - (long) ((1L &lt;&lt; 31) * (Math.sqrt(5) - 1))得到的结果就是1640531527也就是0x61c88647。通过理论与实践，当我们用0x61c88647作为魔数累加为每个ThreadLocal分配各自的ID也就是threadLocalHashCode再与2的幂取模，得到的结果分布很均匀。</p>\n" +
                "<p>ThreadLocalMap使用的是线性探测法，均匀分布的好处在于很快就能探测到下一个临近的可用slot，从而保证效率。这就回答了上文抛出的为什么大小要为2的幂的问题。为了优化效率。</p>\n" +
                "<p>对于&amp; (INITIAL_CAPACITY - 1)，相信有过算法竞赛经验或是阅读源码较多的程序员，一看就明白，对于2的幂作为模数取模，可以用&amp;(2^n-1)来替代%2^n，位运算比取模效率高很多。至于为什么，因为对2^n取模，只要不是低n位对结果的贡献显然都是0，会影响结果的只能是低n位。</p>\n" +
                "<p>可以说在ThreadLocalMap中，形如key.threadLocalHashCode &amp; (table.length - 1)（其中key为一个ThreadLocal实例）这样的代码片段实质上就是在求一个ThreadLocal实例的哈希值，只是在源码实现中没有将其抽为一个公用函数。</p>\n" +
                "<h5 id=\"4-6-getentry-\">4.6 getEntry方法</h5>\n" +
                "<p>这个方法会被ThreadLocal的get方法直接调用，用于获取map中某个ThreadLocal存放的值。</p>\n" +
                "<pre><code>private Entry getEntry(ThreadLocal&lt;?&gt; key) {\n" +
                "    // 根据key这个ThreadLocal的ID来获取索引，也即哈希值\n" +
                "    int i = key.threadLocalHashCode &amp; (table.length - 1);\n" +
                "    Entry e = table[i];\n" +
                "    // 对应的entry存在且未失效且弱引用指向的ThreadLocal就是key，则命中返回\n" +
                "    if (e != null &amp;&amp; e.get() == key) {\n" +
                "        return e;\n" +
                "    } else {\n" +
                "        // 因为用的是线性探测，所以往后找还是有可能能够找到目标Entry的。\n" +
                "        return getEntryAfterMiss(key, i, e);\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "/*\n" +
                " * 调用getEntry未直接命中的时候调用此方法\n" +
                " */\n" +
                "private Entry getEntryAfterMiss(ThreadLocal&lt;?&gt; key, int i, Entry e) {\n" +
                "    Entry[] tab = table;\n" +
                "    int len = tab.length;\n" +
                "\n" +
                "\n" +
                "    // 基于线性探测法不断向后探测直到遇到空entry。\n" +
                "    while (e != null) {\n" +
                "        ThreadLocal&lt;?&gt; k = e.get();\n" +
                "        // 找到目标\n" +
                "        if (k == key) {\n" +
                "            return e;\n" +
                "        }\n" +
                "        if (k == null) {\n" +
                "            // 该entry对应的ThreadLocal已经被回收，调用expungeStaleEntry来清理无效的entry\n" +
                "            expungeStaleEntry(i);\n" +
                "        } else {\n" +
                "            // 环形意义下往后面走\n" +
                "            i = nextIndex(i, len);\n" +
                "        }\n" +
                "        e = tab[i];\n" +
                "    }\n" +
                "    return null;\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * 这个函数是ThreadLocal中核心清理函数，它做的事情很简单：\n" +
                " * 就是从staleSlot开始遍历，将无效（弱引用指向对象被回收）清理，即对应entry中的value置为null，将指向这个entry的table[i]置为null，直到扫到空entry。\n" +
                " * 另外，在过程中还会对非空的entry作rehash。\n" +
                " * 可以说这个函数的作用就是从staleSlot开始清理连续段中的slot（断开强引用，rehash slot等）\n" +
                " */\n" +
                "private int expungeStaleEntry(int staleSlot) {\n" +
                "    Entry[] tab = table;\n" +
                "    int len = tab.length;\n" +
                "\n" +
                "    // 因为entry对应的ThreadLocal已经被回收，value设为null，显式断开强引用\n" +
                "    tab[staleSlot].value = null;\n" +
                "    // 显式设置该entry为null，以便垃圾回收\n" +
                "    tab[staleSlot] = null;\n" +
                "    size--;\n" +
                "\n" +
                "    Entry e;\n" +
                "    int i;\n" +
                "    for (i = nextIndex(staleSlot, len); (e = tab[i]) != null; i = nextIndex(i, len)) {\n" +
                "        ThreadLocal&lt;?&gt; k = e.get();\n" +
                "        // 清理对应ThreadLocal已经被回收的entry\n" +
                "        if (k == null) {\n" +
                "            e.value = null;\n" +
                "            tab[i] = null;\n" +
                "            size--;\n" +
                "        } else {\n" +
                "            /*\n" +
                "             * 对于还没有被回收的情况，需要做一次rehash。\n" +
                "             * \n" +
                "             * 如果对应的ThreadLocal的ID对len取模出来的索引h不为当前位置i，\n" +
                "             * 则从h向后线性探测到第一个空的slot，把当前的entry给挪过去。\n" +
                "             */\n" +
                "            int h = k.threadLocalHashCode &amp; (len - 1);\n" +
                "            if (h != i) {\n" +
                "                tab[i] = null;\n" +
                "\n" +
                "                /*\n" +
                "                 * 在原代码的这里有句注释值得一提，原注释如下：\n" +
                "                 *\n" +
                "                 * Unlike Knuth 6.4 Algorithm R, we must scan until\n" +
                "                 * null because multiple entries could have been stale.\n" +
                "                 *\n" +
                "                 * 这段话提及了Knuth高德纳的著作TAOCP（《计算机程序设计艺术》）的6.4章节（散列）\n" +
                "                 * 中的R算法。R算法描述了如何从使用线性探测的散列表中删除一个元素。\n" +
                "                 * R算法维护了一个上次删除元素的index，当在非空连续段中扫到某个entry的哈希值取模后的索引\n" +
                "                 * 还没有遍历到时，会将该entry挪到index那个位置，并更新当前位置为新的index，\n" +
                "                 * 继续向后扫描直到遇到空的entry。\n" +
                "                 *\n" +
                "                 * ThreadLocalMap因为使用了弱引用，所以其实每个slot的状态有三种也即\n" +
                "                 * 有效（value未回收），无效（value已回收），空（entry==null）。\n" +
                "                 * 正是因为ThreadLocalMap的entry有三种状态，所以不能完全套高德纳原书的R算法。\n" +
                "                 *\n" +
                "                 * 因为expungeStaleEntry函数在扫描过程中还会对无效slot清理将之转为空slot，\n" +
                "                 * 如果直接套用R算法，可能会出现具有相同哈希值的entry之间断开（中间有空entry）。\n" +
                "                 */\n" +
                "                while (tab[h] != null) {\n" +
                "                    h = nextIndex(h, len);\n" +
                "                }\n" +
                "                tab[h] = e;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    // 返回staleSlot之后第一个空的slot索引\n" +
                "    return i;\n" +
                "}\n" +
                "</code></pre><p>我们来回顾一下从ThreadLocal读一个值可能遇到的情况：</p>\n" +
                "<blockquote>\n" +
                "<p>根据入参threadLocal的threadLocalHashCode对表容量取模得到index</p>\n" +
                "</blockquote>\n" +
                "<ol>\n" +
                "<li>如果index对应的slot就是要读的threadLocal，则直接返回结果</li>\n" +
                "<li>调用getEntryAfterMiss线性探测，过程中每碰到无效slot，调用expungeStaleEntry进行段清理；如果找到了key，则返回结果entry</li>\n" +
                "<li>没有找到key，返回null</li>\n" +
                "</ol>\n" +
                "<h5 id=\"4-7-set-\">4.7 set方法</h5>\n" +
                "<pre><code>private void set(ThreadLocal&lt;?&gt; key, Object value) {\n" +
                "\n" +
                "    Entry[] tab = table;\n" +
                "    int len = tab.length;\n" +
                "    int i = key.threadLocalHashCode &amp; (len - 1);\n" +
                "    // 线性探测\n" +
                "    for (Entry e = tab[i]; e != null; e = tab[i = nextIndex(i, len)]) {\n" +
                "        ThreadLocal&lt;?&gt; k = e.get();\n" +
                "        // 找到对应的entry\n" +
                "        if (k == key) {\n" +
                "            e.value = value;\n" +
                "            return;\n" +
                "        }\n" +
                "        // 替换失效的entry\n" +
                "        if (k == null) {\n" +
                "            replaceStaleEntry(key, value, i);\n" +
                "            return;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    tab[i] = new Entry(key, value);\n" +
                "    int sz = ++size;\n" +
                "    if (!cleanSomeSlots(i, sz) &amp;&amp; sz &gt;= threshold) {\n" +
                "        rehash();\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "private void replaceStaleEntry(ThreadLocal&lt;?&gt; key, Object value,\n" +
                "                               int staleSlot) {\n" +
                "    Entry[] tab = table;\n" +
                "    int len = tab.length;\n" +
                "    Entry e;\n" +
                "\n" +
                "    // 向前扫描，查找最前的一个无效slot\n" +
                "    int slotToExpunge = staleSlot;\n" +
                "    for (int i = prevIndex(staleSlot, len);\n" +
                "         (e = tab[i]) != null;\n" +
                "         i = prevIndex(i, len)) {\n" +
                "        if (e.get() == null) {\n" +
                "            slotToExpunge = i;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // 向后遍历table\n" +
                "    for (int i = nextIndex(staleSlot, len);\n" +
                "         (e = tab[i]) != null;\n" +
                "         i = nextIndex(i, len)) {\n" +
                "        ThreadLocal&lt;?&gt; k = e.get();\n" +
                "\n" +
                "        // 找到了key，将其与无效的slot交换\n" +
                "        if (k == key) {\n" +
                "            // 更新对应slot的value值\n" +
                "            e.value = value;\n" +
                "\n" +
                "            tab[i] = tab[staleSlot];\n" +
                "            tab[staleSlot] = e;\n" +
                "\n" +
                "            /*\n" +
                "             * 如果在整个扫描过程中（包括函数一开始的向前扫描与i之前的向后扫描）\n" +
                "             * 找到了之前的无效slot则以那个位置作为清理的起点，\n" +
                "             * 否则则以当前的i作为清理起点\n" +
                "             */\n" +
                "            if (slotToExpunge == staleSlot) {\n" +
                "                slotToExpunge = i;\n" +
                "            }\n" +
                "            // 从slotToExpunge开始做一次连续段的清理，再做一次启发式清理\n" +
                "            cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);\n" +
                "            return;\n" +
                "        }\n" +
                "\n" +
                "        // 如果当前的slot已经无效，并且向前扫描过程中没有无效slot，则更新slotToExpunge为当前位置\n" +
                "        if (k == null &amp;&amp; slotToExpunge == staleSlot) {\n" +
                "            slotToExpunge = i;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    // 如果key在table中不存在，则在原地放一个即可\n" +
                "    tab[staleSlot].value = null;\n" +
                "    tab[staleSlot] = new Entry(key, value);\n" +
                "\n" +
                "    // 在探测过程中如果发现任何无效slot，则做一次清理（连续段清理+启发式清理）\n" +
                "    if (slotToExpunge != staleSlot) {\n" +
                "        cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * 启发式地清理slot,\n" +
                " * i对应entry是非无效（指向的ThreadLocal没被回收，或者entry本身为空）\n" +
                " * n是用于控制控制扫描次数的\n" +
                " * 正常情况下如果log n次扫描没有发现无效slot，函数就结束了\n" +
                " * 但是如果发现了无效的slot，将n置为table的长度len，做一次连续段的清理\n" +
                " * 再从下一个空的slot开始继续扫描\n" +
                " * \n" +
                " * 这个函数有两处地方会被调用，一处是插入的时候可能会被调用，另外个是在替换无效slot的时候可能会被调用，\n" +
                " * 区别是前者传入的n为元素个数，后者为table的容量\n" +
                " */\n" +
                "private boolean cleanSomeSlots(int i, int n) {\n" +
                "    boolean removed = false;\n" +
                "    Entry[] tab = table;\n" +
                "    int len = tab.length;\n" +
                "    do {\n" +
                "        // i在任何情况下自己都不会是一个无效slot，所以从下一个开始判断\n" +
                "        i = nextIndex(i, len);\n" +
                "        Entry e = tab[i];\n" +
                "        if (e != null &amp;&amp; e.get() == null) {\n" +
                "            // 扩大扫描控制因子\n" +
                "            n = len;\n" +
                "            removed = true;\n" +
                "            // 清理一个连续段\n" +
                "            i = expungeStaleEntry(i);\n" +
                "        }\n" +
                "    } while ((n &gt;&gt;&gt;= 1) != 0);\n" +
                "    return removed;\n" +
                "}\n" +
                "\n" +
                "\n" +
                "private void rehash() {\n" +
                "    // 做一次全量清理\n" +
                "    expungeStaleEntries();\n" +
                "\n" +
                "    /*\n" +
                "     * 因为做了一次清理，所以size很可能会变小。\n" +
                "     * ThreadLocalMap这里的实现是调低阈值来判断是否需要扩容，\n" +
                "     * threshold默认为len*2/3，所以这里的threshold - threshold / 4相当于len/2\n" +
                "     */\n" +
                "    if (size &gt;= threshold - threshold / 4) {\n" +
                "        resize();\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "/*\n" +
                " * 做一次全量清理\n" +
                " */\n" +
                "private void expungeStaleEntries() {\n" +
                "    Entry[] tab = table;\n" +
                "    int len = tab.length;\n" +
                "    for (int j = 0; j &lt; len; j++) {\n" +
                "        Entry e = tab[j];\n" +
                "        if (e != null &amp;&amp; e.get() == null) {\n" +
                "            /*\n" +
                "             * 个人觉得这里可以取返回值，如果大于j的话取了用，这样也是可行的。\n" +
                "             * 因为expungeStaleEntry执行过程中是把连续段内所有无效slot都清理了一遍了。\n" +
                "             */\n" +
                "            expungeStaleEntry(j);\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "/**\n" +
                " * 扩容，因为需要保证table的容量len为2的幂，所以扩容即扩大2倍\n" +
                " */\n" +
                "private void resize() {\n" +
                "    Entry[] oldTab = table;\n" +
                "    int oldLen = oldTab.length;\n" +
                "    int newLen = oldLen * 2;\n" +
                "    Entry[] newTab = new Entry[newLen];\n" +
                "    int count = 0;\n" +
                "\n" +
                "    for (int j = 0; j &lt; oldLen; ++j) {\n" +
                "        Entry e = oldTab[j];\n" +
                "        if (e != null) {\n" +
                "            ThreadLocal&lt;?&gt; k = e.get();\n" +
                "            if (k == null) {\n" +
                "                e.value = null; \n" +
                "            } else {\n" +
                "                // 线性探测来存放Entry\n" +
                "                int h = k.threadLocalHashCode &amp; (newLen - 1);\n" +
                "                while (newTab[h] != null) {\n" +
                "                    h = nextIndex(h, newLen);\n" +
                "                }\n" +
                "                newTab[h] = e;\n" +
                "                count++;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    setThreshold(newLen);\n" +
                "    size = count;\n" +
                "    table = newTab;\n" +
                "}\n" +
                "</code></pre><p>我们来回顾一下ThreadLocal的set方法可能会有的情况</p>\n" +
                "<ol>\n" +
                "<li>探测过程中slot都不无效，并且顺利找到key所在的slot，直接替换即可</li>\n" +
                "<li>探测过程中发现有无效slot，调用replaceStaleEntry，效果是最终一定会把key和value放在这个slot，并且会尽可能清理无效slot</li>\n" +
                "</ol>\n" +
                "<blockquote>\n" +
                "<p>在replaceStaleEntry过程中，如果找到了key，则做一个swap把它放到那个无效slot中，value置为新值<br>在replaceStaleEntry过程中，没有找到key，直接在无效slot原地放entry</p>\n" +
                "</blockquote>\n" +
                "<ol>\n" +
                "<li>探测没有发现key，则在连续段末尾的后一个空位置放上entry，这也是线性探测法的一部分。放完后，做一次启发式清理，如果没清理出去key，并且当前table大小已经超过阈值了，则做一次rehash，rehash函数会调用一次全量清理slot方法也即expungeStaleEntries，如果完了之后table大小超过了threshold - threshold / 4，则进行扩容2倍</li>\n" +
                "</ol>\n" +
                "<h5 id=\"4-8-remove-\">4.8 remove方法</h5>\n" +
                "<pre><code>/**\n" +
                " * 从map中删除ThreadLocal\n" +
                " */\n" +
                "private void remove(ThreadLocal&lt;?&gt; key) {\n" +
                "    Entry[] tab = table;\n" +
                "    int len = tab.length;\n" +
                "    int i = key.threadLocalHashCode &amp; (len - 1);\n" +
                "    for (Entry e = tab[i];\n" +
                "         e != null;\n" +
                "         e = tab[i = nextIndex(i, len)]) {\n" +
                "        if (e.get() == key) {\n" +
                "            // 显式断开弱引用\n" +
                "            e.clear();\n" +
                "            // 进行段清理\n" +
                "            expungeStaleEntry(i);\n" +
                "            return;\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "</code></pre><p>remove方法相对于getEntry和set方法比较简单，直接在table中找key，如果找到了，把弱引用断了做一次段清理。</p>\n" +
                "<h3 id=\"5-threadlocal-\">5.ThreadLocal与内存泄漏</h3>\n" +
                "<p>关于ThreadLocal是否会引起内存泄漏也是一个比较有争议性的问题，其实就是要看对内存泄漏的准确定义是什么。</p>\n" +
                "<p>认为ThreadLocal会引起内存泄漏的说法是因为如果一个ThreadLocal对象被回收了，我们往里面放的value对于【当前线程-&gt;当前线程的threadLocals(ThreadLocal.ThreadLocalMap对象）-&gt;Entry数组-&gt;某个entry.value】这样一条强引用链是可达的，因此value不会被回收。</p>\n" +
                "<p>认为ThreadLocal不会引起内存泄漏的说法是因为ThreadLocal.ThreadLocalMap源码实现中自带一套自我清理的机制。</p>\n" +
                "<p>之所以有关于内存泄露的讨论是因为在有线程复用如线程池的场景中，一个线程的寿命很长，大对象长期不被回收影响系统运行效率与安全。如果线程不会复用，用完即销毁了也不会有ThreadLocal引发内存泄露的问题。《Effective Java》一书中的第6条对这种内存泄露称为unintentional object retention(无意识的对象保留）。</p>\n" +
                "<p>当我们仔细读过ThreadLocalMap的源码，我们可以推断，如果在使用的ThreadLocal的过程中，显式地进行remove是个很好的编码习惯，这样是不会引起内存泄漏。</p>\n" +
                "<p>那么如果没有显式地进行remove呢？只能说如果对应线程之后调用ThreadLocal的get和set方法都有很高的概率会顺便清理掉无效对象，断开value强引用，从而大对象被收集器回收。</p>\n" +
                "<p>但无论如何，我们应该考虑到何时调用ThreadLocal的remove方法。一个比较熟悉的场景就是对于一个请求一个线程的server如tomcat，在代码中对web api作一个切面，存放一些如用户名等用户信息，在连接点方法结束后，再显式调用remove。</p>\n" +
                "<h3 id=\"6-inheritablethreadlocal-\">6.InheritableThreadLocal原理</h3>\n" +
                "<p>对于InheritableThreadLocal，本文不作过多介绍，只是简单略过。<br>ThreadLocal本身是线程隔离的，InheritableThreadLocal提供了一种父子线程之间的数据共享机制。</p>\n" +
                "<p>它的具体实现是在Thread类中除了threadLocals外还有一个inheritableThreadLocals对象。</p>\n" +
                "<p><img src=\"https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbufJaqQkvibaMUhGxB1NLwZGSvffuK64YweaYDbQFfGcCzB7T8iadP5qX9WgGg8rMfoaaZjdlxwChGCA/640?wx_fmt=png&amp;tp=webp&amp;wxfrom=5&amp;wx_lazy=1&amp;wx_co=1\" alt=\"img\"></p>\n" +
                "<p>在线程对象初始化的时候，会调用ThreadLocal的createInheritedMap从父线程的inheritableThreadLocals中把有效的entry都拷过来</p>\n" +
                "<p><img src=\"https://mmbiz.qpic.cn/mmbiz_png/eQPyBffYbufJaqQkvibaMUhGxB1NLwZGSGCy7Zees8MQzDzu6zedMvKRibz3WUAPySPfKwiaHyicSUTtFmdgzbRJ3g/640?wx_fmt=png&amp;tp=webp&amp;wxfrom=5&amp;wx_lazy=1&amp;wx_co=1\" alt=\"img\"></p>\n" +
                "<p>可以看一下其中的具体实现</p>\n" +
                "<pre><code>private ThreadLocalMap(ThreadLocalMap parentMap) {\n" +
                "    Entry[] parentTable = parentMap.table;\n" +
                "    int len = parentTable.length;\n" +
                "    setThreshold(len);\n" +
                "    table = new Entry[len];\n" +
                "\n" +
                "    for (int j = 0; j &lt; len; j++) {\n" +
                "        Entry e = parentTable[j];\n" +
                "        if (e != null) {\n" +
                "            @SuppressWarnings(&quot;unchecked&quot;)\n" +
                "            ThreadLocal&lt;Object&gt; key = (ThreadLocal&lt;Object&gt;) e.get();\n" +
                "            if (key != null) {\n" +
                "                // 这里的childValue方法在InheritableThreadLocal中默认实现为返回本身值，可以被重写\n" +
                "                Object value = key.childValue(e.value);\n" +
                "                Entry c = new Entry(key, value);\n" +
                "                int h = key.threadLocalHashCode &amp; (len - 1);\n" +
                "                while (table[h] != null)\n" +
                "                    h = nextIndex(h, len);\n" +
                "                table[h] = c;\n" +
                "                size++;\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n" +
                "</code></pre><p>还是比较简单的，做的事情就是以父线程的inheritableThreadLocalMap为数据源，过滤出有效的entry，初始化到自己的inheritableThreadLocalMap中。其中childValue可以被重写。</p>\n" +
                "<blockquote>\n" +
                "<p>需要注意的地方是InheritableThreadLocal只是在子线程创建的时候会去拷一份父线程的inheritableThreadLocals。如果父线程是在子线程创建后再set某个InheritableThreadLocal对象的值，对子线程是不可见的。</p>\n" +
                "</blockquote>\n" +
                "<h3 id=\"7-\">7. 总结</h3>\n" +
                "<p>本博文重点介绍了ThreadLocal中ThreadLocalMap的大致实现原理以及ThreadLocal内存泄露的问题以及简略介绍InheritableThreadLocal。作为Josh Bloch和Doug Lea两位大师之作，ThreadLocal本身实现的算法与技巧还是很优雅的。</p>\n" +
                "<p>在开发过程中，ThreadLocal用到恰到好处的话，可以消除一些代码的重复。但也要注意过度使用ThreadLocal很容易加大类之间的耦合度与依赖关系（开发过程可能会不得不过度考虑某个ThreadLocal在调用时是否已有值，存放的是哪个类放的什么值）。</p>\n";
        //String s = uploadService.handlerOuterImage(content);
        BlogPost blogPost = new BlogPost();
        blogPost.setPostId(3);
        blogPost.setPostContent(content);
        List<Integer> tagIds = new ArrayList<>();
        List<Integer> cateIds = new ArrayList<>();
        for(int i = 1 ;i< 10;i++){
            tagIds.add(i);
            //cateIds.add(i);
        }
        cateIds.add(5);
        cateIds.add(4);
        cateIds.add(11);
        cateIds.add(6);
        cateIds.add(1);
        cateIds.add(20);
        //postService.savePost(blogPost,cateIds,tagIds);
        postService.updatePost(blogPost,cateIds,tagIds);
        //System.out.println(s);
    }

}
