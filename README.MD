 随着项目的不断迭代，加上产品经理大法（这里加一个弹窗提示，这里加一个引导....）各种弹窗在应用启动时候需要展示， 然而它们出现的时机还有可能重叠。我勒个擦。。。有没有一种优（tou）雅(lan)的方式来完成这部分的需求呢？没错，这就是我们今天要介绍的东西.

  我们想的是需要一个队列来管理弹窗，在各种请求回来之后去往队列中添加我们需要显示的弹窗，第一个弹窗消失了之后在显示下一个弹窗，往复下去直到任务完成。

   [!弹窗图](./1.png)


  为了演示这个Demo, 我搬出了珍藏多年的Dialog封装。。。里面包含了一些常用Dialog的封装。具体可以看Demo代码。
 现在开始正式逻辑功能的实现，我们创建一个DialogManager的单例用于管理弹窗的各种显示。在此之上我们需要定义一个队列管理弹窗显示：

  Queue<BuildBean> queue = new ConcurrentLinkedQueue<>(); //弹窗队列(线程安全)

为了防止多个线程同时操作DialogManager中的queue对象，所以我们采用线程安全的ConcurrentLinkedQueue，<br>这里简单的介绍下ConcurrentLinkedQueue实现和数据结构：由head节点和tair节点组成，每个节点（Node）由节点元素（item）和指向下一个节点的引用(next)组成，节点与节点之间就是通过这个next关联起来，从而组成一张链表结构的队列。默认情况下head节点存储的元素为空，tair节点等于head节点。

为了尽可能简单的调用，我们只用提供一个push到队列的方法即可：

```
  /**
     * 每次弹窗调用PushQueue方法
     *
     * @param dialogBase
     */
    public void pushToQueue(BuildBean dialogBase) {
        //添加到队列中
        if (dialogBase != null) {
            dialogBase.setDissMissListener(() -> {
                        Log.e(TAG, "nextTask");
                        nextTask();
                    }
            );
            Log.e(TAG, "add..");
            queue.add(dialogBase);
            //只有当前队列数量为1时才能进行下一步操作
            if (canShow()) {
                startNextIf();
            }
        }
    }
```
startNextIf 中进行队列任务的执行操作，如果队列不为空则进行显示。


```
 private void startNextIf() {
        if (queue != null && queue.isEmpty()) {
            return;
        }
        //Todo 可在此处对弹窗进行排序
        oderDialog();
        mDialogBase = queue.element();
        if (mDialogBase != null) {
            mDialogBase.show();
        } else {
            Log.e(TAG, "任务队列为空...");
        }
    }

```

需要注意的是我们在显示之前可以对显示的弹窗优先级别进行扩展，可将优先级别高的弹窗显示在最前面，BuildBean已有
相关扩展，可自行设置扩展。


