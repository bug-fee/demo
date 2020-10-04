<!--测试if else show-->
<template>
    <!--只能有唯一一个入口div-->
    <div>
        <div v-if="flag">今晚要上课！</div>
        <!--<div v-else-if="flag">今晚要上课！</div>-->
        <div v-else>今晚不上课！</div>
        <p></p>
        <!--事件内支持JS语法-->
        <button @click="flag = !flag">切换</button>

        <p>----------------------------------</p>
        <div v-show="flag">今晚讲Vue！</div>
        <div v-show="!flag">今晚不讲Vue！</div>
        <button @click="flag = !flag">切换</button>
    </div>
    <pre>
        v-if是“真正”的条件渲染（删除dom和创建dom），因为他会确保在切换过程中条件块内的事件监听器和字组件适当地被销毁和重建
        V-show不管初始条件是什么，元素总是会被渲染（隐藏dom），并且只是简单基于CSS进行切换
        如果要频繁切换v-show较好
    </pre>
</template>

<script>
    //VM定义
    export default {
        // 声明当前组件名称
        name: 'IfAndShow',
        //model定义
        data() {
            return {
                // 上课 不上课
                flag: true
            }
        }
    }
</script>