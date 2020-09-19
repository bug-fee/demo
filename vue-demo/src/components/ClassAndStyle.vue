<!--测试属性绑定-->
<template>
    <!--只能有唯一一个入口div-->
    <div>
        <h3>class的使用</h3>
        <!--使用model中的key作为映射-->
        <div :class="oneClass">样式类可以是字符串</div>
        <!--注意，此处直接使用类名-->
        <div :class="{classOne:true,classTwo:true}">样式类可以是对象</div>
        <!--注意，此处直接使用类名-->
        <div :class="['classOne','classTwo']">样式类可以是数组</div>
        <!--此处使用model中的key作为映射-->
        <div :class="[oneClass,twoClass]">样式类可以是数组</div>

        <p>--------------------------------------------------</p>

        <div
            style="width: 300px;height: 200px;margin: 10px auto;"
            :style="{backgroundColor:bgColor,fontSize:fSize}">样式类可以是字符串
        </div>
    </div>
</template>

<script>
    //VM定义
    export default {
        // 声明当前组件名称
        name: 'ClassAndStyle',
        //model定义
        data() {
            return {
                oneClass: 'classOne',
                twoClass: 'classTwo',
                bgColor: 'red',
                fSize:'20px'
            }
        }
    }
</script>
<style scoped>
    .classOne {
        font-size: 30px;
        color: red;
    }

    .classTwo {
        width: 300px;
        height: 100px;
        margin: auto;
        margin-top: 10px;
        background-color: deepskyblue;
    }
</style>